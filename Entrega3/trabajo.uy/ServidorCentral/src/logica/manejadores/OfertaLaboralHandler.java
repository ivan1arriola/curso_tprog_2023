package logica.manejadores;


import jakarta.persistence.EntityManager;
import logica.clases.OfertaLaboral;

import java.util.HashMap;
import java.util.Map;


public class OfertaLaboralHandler {
    private static OfertaLaboralHandler OLHandler = null;
    private Map<String, OfertaLaboral> ofertasLaborales;
    private static EntityManager database;
    ;

    private OfertaLaboralHandler() {
        ofertasLaborales = new HashMap<>();
    } //Constructor privado,  inicializa colección de ofertas laborales


    public static OfertaLaboralHandler getInstance() {
        if (OLHandler == null) {
            OLHandler = new OfertaLaboralHandler();
        }
        return OLHandler;
    }

    public void agregar(OfertaLaboral tipoOfertaL) {
        ofertasLaborales.put(tipoOfertaL.getNombre(), tipoOfertaL);
    }

    public Map<String, OfertaLaboral> obtener() {
        return ofertasLaborales;
    }

    public boolean existe(String nombre) {
        if (ofertasLaborales.containsKey(nombre)) {
            return true;
        }
        return false;
    }

    public OfertaLaboral buscar(String nombre) {
        return ofertasLaborales.get(nombre);
    }

    public static void setBaseDatos(EntityManager em) {
        database = em;
    }
}