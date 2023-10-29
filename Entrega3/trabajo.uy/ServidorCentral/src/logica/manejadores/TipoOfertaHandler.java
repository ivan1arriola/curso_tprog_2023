package logica.manejadores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import logica.clases.TipoOferta;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

public class TipoOfertaHandler {
    private static TipoOfertaHandler tOfertaHandler = null;
    private static EntityManager database;

    private TipoOfertaHandler() {
    }

    public static TipoOfertaHandler getInstance() {
        if (tOfertaHandler == null) {
            tOfertaHandler = new TipoOfertaHandler();
        }
        return tOfertaHandler;
    }

    public boolean existe(String nombre) {
        try {
            TypedQuery<Long> query = database.createQuery("SELECT COUNT(t) FROM TipoOferta t WHERE t.nombre = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            Long count = query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void agregar(TipoOferta tipoOferta) {
        EntityTransaction transaction = database.getTransaction();
        try {
            if(!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }
            database.persist(tipoOferta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public TipoOferta buscar(String nombre) {
        try {
            TypedQuery<TipoOferta> query = database.createQuery("SELECT t FROM TipoOferta t WHERE t.nombre = :nombre", TipoOferta.class);
            query.setParameter("nombre", nombre);
            List<TipoOferta> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map<String, TipoOferta> obtener() {
        Map<String, TipoOferta> tipoOfertas = new TreeMap<>();
        try {
            TypedQuery<TipoOferta> query = database.createQuery("SELECT t FROM TipoOferta t", TipoOferta.class);
            List<TipoOferta> resultados = query.getResultList();

            for (TipoOferta tipoOferta : resultados) {
                tipoOfertas.put(tipoOferta.getNombre(), tipoOferta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoOfertas;
    }

    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}
