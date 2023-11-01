package logica.manejadores;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import logica.clases.OfertaLaboral;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class OfertaLaboralHandler {
    private static OfertaLaboralHandler OLHandler = null;
   // private Map<String, OfertaLaboral> ofertasLaborales;
    private static EntityManager database;
    ;

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
            if(!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }
            database.persist(tipoOfertaL);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Map<String, OfertaLaboral> obtener() {
        Map<String, OfertaLaboral> ofertasLaborales = new TreeMap<>();
        try {
            TypedQuery<OfertaLaboral> query = database.createQuery("SELECT o FROM OfertaLaboral o", OfertaLaboral.class);
            List<OfertaLaboral> resultados = query.getResultList();

            for (OfertaLaboral oferta : resultados) {
                ofertasLaborales.put(oferta.getNombre(), oferta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ofertasLaborales;
    }


    public boolean existe(String nombre) {
        try {
            TypedQuery<Long> query = database.createQuery("SELECT COUNT(o) FROM OfertaLaboral o WHERE o.nombre = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            Long count = query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public OfertaLaboral buscar(String nombre) {
        try {
            TypedQuery<OfertaLaboral> query = database.createQuery("SELECT o FROM OfertaLaboral o WHERE o.nombre = :nombre", OfertaLaboral.class);
            query.setParameter("nombre", nombre);
            List<OfertaLaboral> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                // Devuelve la primera oferta laboral encontrada con ese nombre.
                return resultados.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Devuelve null si no se encontró ninguna oferta laboral con ese nombre.
    }


    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}