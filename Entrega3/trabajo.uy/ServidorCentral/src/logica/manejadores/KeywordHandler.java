package logica.manejadores;

import jakarta.persistence.*;
import logica.clases.Keyword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeywordHandler {
    private static KeywordHandler instancia = null;
    private Map<String, Keyword> keys;
    private static EntityManager database = null;

    private KeywordHandler() {
        keys = new HashMap<String, Keyword>();
        cargarKeywordsDesdeBaseDeDatos();
    } // obtener instancia con getInstance()

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
            tx.begin();
            database.persist(key);
            tx.commit();
            keys.put(key.getNombre(), key);

    }

    private void cargarKeywordsDesdeBaseDeDatos() {
            // Realizar una consulta JPA para obtener todos los registros de la entidad Keyword
            TypedQuery<Keyword> query = database.createQuery("SELECT k FROM Keyword k", Keyword.class);
            List<Keyword> keywordList = query.getResultList();

            for (Keyword keyword : keywordList) {
                keys.put(keyword.getNombre(), keyword);
            }

    }


    public boolean existe(String key) {
        return keys.containsKey(key);
    }

    public Map<String, Keyword> obtener() {
        return keys;
    }

    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}
