package logica.manejadores;

import jakarta.persistence.*;
import logica.clases.Keyword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class KeywordHandler {
    private static KeywordHandler instancia = null;
    private static EntityManager database = null;

    private KeywordHandler() {
    }

    public static KeywordHandler getInstance() {
        if (instancia == null) {
            instancia = new KeywordHandler();
        }
        return instancia;
    }

    public void agregar(Keyword key) {
        if (key == null) {
            throw new IllegalArgumentException("La keyword a agregar no puede ser vacía");
        }
            EntityTransaction tx = database.getTransaction();
            if(!database.getTransaction().isActive()) {
                database.getTransaction().begin();
            }
            database.persist(key);
            tx.commit();
    }



    public boolean existe(String nombre) {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        TypedQuery<Long> query = database.createQuery("SELECT COUNT(k) FROM Keyword k WHERE k.nombre = :nombre", Long.class)
                .setParameter("nombre", nombre);
        return query.getSingleResult() > 0;
    }

    public Map<String, Keyword> obtener() {
        if (database == null) {
            throw new IllegalStateException("EntityManager no configurado.");
        }

        TypedQuery<Keyword> query = database.createQuery("SELECT k FROM Keyword k", Keyword.class);
        List<Keyword> keywordList = query.getResultList();

        Map<String, Keyword> keywordMap = new TreeMap<>();
        for (Keyword keyword : keywordList) {
            keywordMap.put(keyword.getNombre(), keyword);
        }

        return keywordMap;
    }


    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}
