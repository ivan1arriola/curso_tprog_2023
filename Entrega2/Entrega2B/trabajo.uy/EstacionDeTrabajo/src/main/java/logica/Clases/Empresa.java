package main.java.logica.Clases;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import main.java.logica.Clases.OfertaLaboral;

import main.java.logica.Clases.*;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTOfertaLaboral;
import main.java.logica.Datatypes.DTUsuario;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Enumerados.DepUY;

public class Empresa extends Usuario {

    private String descripcion;
    private String url;
    private HashSet<OfertaLaboral> ofertasLaborales;
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = urlE;
    }
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc) {
        super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String urlE) {
        this.url = urlE;
    }

    @Override
    public boolean esEmpresa() {
        return true;
    }


    public HashSet<String> listarOfertasLaborales(){
        HashSet<String> lista = new HashSet<String>();
        
        if(ofertasLaborales.size() != 0) {
	        for( OfertaLaboral ol : ofertasLaborales){
	            lista.add(ol.getNombre());
	        }
        }

        return lista;
    }


    public OfertaLaboral altaOfertaLaboral(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA);
        ofertasLaborales.add(ol);
        return ol;
    }
    
    public OfertaLaboral altaOfertaLaboralConPaquete(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords, String paquete){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, paquete);
        ofertasLaborales.add(ol);
        return ol;
    }

    public DTUsuario obtenerDatosUsuario() { // obtenerDatosUsuario(): DTUsuario
    	String nickname =  getNickname();
    	String nombre = getNombre();
        String apellido = getApellido();
        String correoElectronico = getCorreo_electronico();
        byte[] imagen = getImagen();
        			       
        HashSet<DTOfertaLaboral> dtOfertas = new HashSet<DTOfertaLaboral>();
        
        for (OfertaLaboral oferta : ofertasLaborales) {
        	DTOfertaLaboral dtOferta = oferta.obtenerDatosOferta();
            dtOfertas.add(dtOferta);   
        }
        
        return new DTEmpresa(nickname, correoElectronico, apellido, nombre, descripcion, url, dtOfertas, imagen);
        
    }


}
