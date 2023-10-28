package logica.manejadores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.clases.TipoOferta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoOfertaHandler {
    private static TipoOfertaHandler tOfertaHandler = null;
    private Map<String, TipoOferta> tipoOfertas;

    private EntityManagerFactory emf = null;
    private static EntityManager database =  null;


    private TipoOfertaHandler() {
        tipoOfertas = new HashMap<>();
        cargarTipoOfertas();
    } //Constructor privado,  inicializa colección de ofertas



    private void cargarTipoOfertas() {
        database.getTransaction().begin();
        List<TipoOferta> tipoOfertaList = database.createQuery("SELECT t FROM TipoOferta t", TipoOferta.class).getResultList();
        database.getTransaction().commit();

        for (TipoOferta tipoOferta : tipoOfertaList) {
            tipoOfertas.put(tipoOferta.getNombre(), tipoOferta);
        }

    }


    public static TipoOfertaHandler getInstance() {
        if (tOfertaHandler == null) {
            tOfertaHandler = new TipoOfertaHandler();
        }
        return tOfertaHandler;
    }

    public boolean existe(String nombre) {
        return tipoOfertas.containsKey(nombre);
    }

    public void agregar(TipoOferta tipoOferta) {
        if (tipoOferta != null) {
            tipoOfertas.put(tipoOferta.getNombre(), tipoOferta); // Agregar a la colección en memoria
            database.getTransaction().begin();
            database.persist(tipoOferta); // Agregar a la base de datos
            database.getTransaction().commit();
        }
    }


    public TipoOferta buscar(String nombre) {
        return tipoOfertas.get(nombre);
    }

    public Map<String, TipoOferta> obtener() {
        return tipoOfertas;
    }

    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}
