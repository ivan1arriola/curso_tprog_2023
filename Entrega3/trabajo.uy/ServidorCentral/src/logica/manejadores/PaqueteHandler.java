package logica.manejadores;

import excepciones.NoExistePaquete;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import logica.clases.Paquete;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PaqueteHandler {
    private static PaqueteHandler instancia = null;

    private static EntityManager database = null;

    private PaqueteHandler() {
    }

    public static PaqueteHandler getInstance() {
        if (instancia == null) {
            instancia = new PaqueteHandler();
        }
        return instancia;
    }

    public Map<String, Paquete> obtener() {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        if (!database.getTransaction().isActive()) {
            database.getTransaction().begin();
        }
        List<Paquete> paqueteList = database.createQuery("SELECT p FROM Paquete p", Paquete.class).getResultList();
        database.getTransaction().commit();

        Map<String, Paquete> paquetes = new TreeMap<>();
        for (Paquete paquete : paqueteList) {
            paquetes.put(paquete.getNombre(), paquete);
        }

        return paquetes;
    }


    public static void setBaseDatos(EntityManager emm) {
        database = emm;
    }
 
    public  boolean existe(String nombre) {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        if (!database.getTransaction().isActive()) {
            database.getTransaction().begin();
        }
        boolean existe = database.createQuery("SELECT COUNT(p) FROM Paquete p WHERE p.nombre = :nombre", Long.class)
                .setParameter("nombre", nombre)
                .getSingleResult() > 0;
        database.getTransaction().commit();

        return existe;
    }

    public Paquete buscar(String nombre) throws NoExistePaquete {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }
        if (!database.getTransaction().isActive()) {
            database.getTransaction().begin();
        }

        Paquete paquete;
        try {
            paquete = database.createQuery("SELECT p FROM Paquete p WHERE p.nombre = :nombre", Paquete.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            database.getTransaction().commit();
        } catch (NoResultException ex) {
            database.getTransaction().rollback();
            throw new NoExistePaquete("El paquete con el nombre '" + nombre + "' no existe.");
        }

        return paquete;
    }


    public void agregar(Paquete paquete) {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        if (!database.getTransaction().isActive()) {
            database.getTransaction().begin();
        }
        database.persist(paquete);
        database.getTransaction().commit();
    }

    public void actualizarPaquete(Paquete paquete) {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        if (!database.getTransaction().isActive()) {
            database.getTransaction().begin();
        }
        database.merge(paquete);

    }

}
