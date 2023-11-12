package logica.manejadores;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

//import jakarta.persistence.DiscriminatorColumn;

import logica.clases.Keyword;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class KeywordHandler {
    private static KeywordHandler instancia = null;
    private static EntityManager database = null;


    // constructor vacio
    private KeywordHandler() {
    }

    // singleton,  para el keyword handler
    public static KeywordHandler getInstance() {
        if (instancia == null) {
            instancia = new KeywordHandler();
        }
        return instancia;
    }


    // agregar a la base de datos
    public void agregar(Keyword key) {
        if (key == null) {
            throw new IllegalArgumentException("La keyword a agregar no puede ser vacía");
        }
            // obtengo instancia de la base de datos
            EntityTransaction trx = database.getTransaction();
            if (!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }
            // esto es lo que hace guardar la keyword
            // System.out.println("################## estoy agregando a base de datos ##################");
            database.persist(key);
            // tengo que hacer commit despues
            trx.commit();
    }



    public boolean existe(String nombre) {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        TypedQuery<Long> query = database.createQuery("SELECT COUNT(k) FROM Keyword k WHERE k.nombre = :nombre",  Long.class)
                .setParameter("nombre",  nombre);
        return query.getSingleResult() > 0;
    }

    public static Map<String,  Keyword> obtener() {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        TypedQuery<Keyword> query = database.createQuery("SELECT k FROM Keyword k",  Keyword.class);
        List<Keyword> keywordList = query.getResultList();

        Map<String,  Keyword> keywordMap = new TreeMap<>();
        for (Keyword keyword : keywordList) {
            keywordMap.put(keyword.getNombre(),  keyword);
        }

        return keywordMap;
    }


    public static void setBaseDatos(EntityManager emm) {
        database = emm;
    }
}
