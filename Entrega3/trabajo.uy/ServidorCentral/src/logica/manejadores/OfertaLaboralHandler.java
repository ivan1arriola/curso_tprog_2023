package logica.manejadores;


import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import logica.clases.OfertaLaboral;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class OfertaLaboralHandler {
    private static OfertaLaboralHandler OLHandler = null;
   // private Map<String,  OfertaLaboral> ofertasLaborales;
    private static EntityManager database;


    private OfertaLaboralHandler() {

    }


    public static OfertaLaboralHandler getInstance() {
        if (OLHandler == null) {
            OLHandler = new OfertaLaboralHandler();
        }
        return OLHandler;
    }

    public void agregar(OfertaLaboral tipoOfertaL) {
        EntityTransaction transaction = database.getTransaction();
        try {
            if (!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }
            database.persist(tipoOfertaL);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Map<String,  OfertaLaboral> obtener() {
        Map<String,  OfertaLaboral> ofertasLaborales = new TreeMap<>();
        try {
            TypedQuery<OfertaLaboral> query = database.createQuery("SELECT o FROM OfertaLaboral o",  OfertaLaboral.class);
            List<OfertaLaboral> resultados = query.getResultList();

            for (OfertaLaboral oferta : resultados) {
                ofertasLaborales.put(oferta.getNombre(),  oferta);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return ofertasLaborales;
    }


    public boolean existe(String nombre) {
        try {
            TypedQuery<Long> query = database.createQuery("SELECT COUNT(o) FROM OfertaLaboral o WHERE o.nombre = :nombre",  Long.class);
            query.setParameter("nombre",  nombre);
            Long count = query.getSingleResult();

            return count > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }


    public OfertaLaboral buscar(String nombre) throws OfertaLaboralNoEncontrada {

            TypedQuery<OfertaLaboral> query = database.createQuery("SELECT o FROM OfertaLaboral o WHERE o.nombre = :nombre",  OfertaLaboral.class);
            query.setParameter("nombre",  nombre);
            List<OfertaLaboral> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                // Devuelve la primera oferta laboral encontrada con ese nombre.
                return resultados.get(0);
            } else throw new OfertaLaboralNoEncontrada("No se encontro la oferta laboral");
    }

    public void actualizar(OfertaLaboral ofertaActualizada) {
        EntityTransaction transaction = database.getTransaction();

        try {
            if (!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }

            database.merge(ofertaActualizada);
            transaction.commit();

        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
 
    public static void setBaseDatos(EntityManager emm) {
        database = emm;
    }
}