package logica.manejadores;

import jakarta.persistence.EntityManager;
import logica.clases.Paquete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaqueteHandler {
    private static PaqueteHandler instancia = null;
    private Map<String, Paquete> paq;

    private static EntityManager database = null;

    private PaqueteHandler() {
        paq = new HashMap<String, Paquete>();
        cargarPaquetes();
    }

    public static PaqueteHandler getInstance() {
        if (instancia == null) {
            instancia = new PaqueteHandler();
        }
        return instancia;
    }

    public boolean existe(String nombre) {
        return paq.containsKey(nombre);
    }

    public Paquete buscar(String nombre) {
        if (!paq.containsKey(nombre)) {
            throw new IllegalArgumentException("Paquete no encontrado");
        }
        return paq.get(nombre);
    }

    public void agregar(Paquete paquete) {
        if (paquete != null) {
            paq.put(paquete.getNombre(), paquete); // Agregar a la colección en memoria
            database.getTransaction().begin();
            database.persist(paquete); // Agregar a la base de datos
            database.getTransaction().commit();
        }
    }

    public Map<String, Paquete> obtener() {
        return paq;
    }


    private void cargarPaquetes() {
        database.getTransaction().begin();
        List<Paquete> paqueteList = database.createQuery("SELECT p FROM Paquete p", Paquete.class).getResultList();
        database.getTransaction().commit();

        for (Paquete paquete : paqueteList) {
            paq.put(paquete.getNombre(), paquete);
        }

    }

    public void actualizarPaquete(Paquete paquete){
        database.getTransaction().begin();
        Paquete paqueteActualizado = database.merge(paquete);
        database.getTransaction().commit();
        paq.put(paqueteActualizado.getNombre(), paqueteActualizado);
    }


    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}
