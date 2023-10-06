package main.java.logica.Clases;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import main.java.logica.Clases.*;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTOfertaLaboral;
import main.java.logica.Datatypes.DTUsuario;
import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;

public class Empresa extends Usuario {

    private String descripcion;
    private String url;
    private HashSet<OfertaLaboral> ofertasLaborales;
    private HashSet<InfoCompra> infoCompras;
    
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
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico, contrasena);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = urlE;
    }
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, String desc) {
        super(nickname, nombre, apellido, correo_electronico, contrasena);
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

    public HashSet<String> listarOfertasLaborales(){
        HashSet<String> lista = new HashSet<String>();
        
        if(ofertasLaborales.size() != 0) {
	        for( OfertaLaboral ol : ofertasLaborales){
	            lista.add(ol.getNombre());
	        }
        }

        return lista;
    }
    
    @Override
    public boolean esEmpresa() {
        return true;
    }

    public OfertaLaboral altaOfertaLaboral(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, EstadoOL.Ingresada);
        ofertasLaborales.add(ol);
        return ol;
    }
    
    public OfertaLaboral altaOfertaLaboralConPaquete(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords, Paquete paquete){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, EstadoOL.Ingresada, paquete);
        ofertasLaborales.add(ol);
        return ol;
    }
    
    public OfertaLaboral altaOfertaLaboralImagen(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remu, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, byte[] img) {
    	OfertaLaboral ol = new OfertaLaboral(keyw, tipo, nombre, descripcion, ciu, dep, horario, remu, fechaA, EstadoOL.Ingresada,img);
    	ofertasLaborales.add(ol);
    	return ol;
    }
    
    public OfertaLaboral altaOfertaLaboralImagenPaquete(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remu, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, byte[] img, Paquete paquete) {
    	OfertaLaboral ol = new OfertaLaboral(keyw, tipo, nombre, descripcion, ciu, dep, horario, remu, fechaA, EstadoOL.Ingresada, img, paquete);
    	ofertasLaborales.add(ol);
    	return ol;	
    }
    
    public DTUsuario obtenerDatosUsuario() { // obtenerDatosUsuario(): DTUsuario
    	String nickname =  getNickname();
    	String nombre = getNombre();
        String apellido = getApellido();
        String correoElectronico = getCorreo_electronico();
        byte[] imagen = getImagen();
        			       
        HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
        
        for (OfertaLaboral oferta : ofertasLaborales) {
        	DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
            dtOfertas.add(dtOferta);   
        }
        
        return new DTEmpresa(nickname, correoElectronico, apellido, nombre, descripcion, url, dtOfertas, imagen);
        
    }
    
    public HashSet<String> listarOfertasLaboralesConfirmadas(){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if(ol.getEstado() == EstadoOL.Confirmada)
            	res.add(ol.getNombre());
        }
        return res;
    }
    
    public HashSet<String> listarOfertasLaboralesConfirmadasKeyword(String ks){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if(ol.getEstado() == EstadoOL.Confirmada && ol.tieneKeyword(ks))
            	res.add(ol.getNombre());
        }
        return res;
    }
    
    public HashSet<String> listarOfertasLaboralesIngresadas(){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if(ol.getEstado() == EstadoOL.Ingresada)
            	res.add(ol.getNombre());
        }
        return res;
    }
    
    public boolean existeOfertaLaboral(String nombre_oferta) {
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if(ol.getNombre().equals(nombre_oferta))
            	return true;
        }
        
    	return false;
    }
    
    public boolean tieneURL() {
    	return url != null;
    }
    
    public boolean compraPaquetes(Paquete paq) {
        for (InfoCompra ic : infoCompras) {
        	if((ic.getPaquete()).getNombre().equals(paq.getNombre())) {
        		return false;
        	}
        }
    	float costo = paq.getCosto();
    	LocalDate fa = paq.getfechaAlta();
    	int val = paq.getValidez();
    	HashSet<DTCantTO> S = paq.obtenerDTSCantTO();
    	InfoCompra io = new InfoCompra(fa,costo,fa.plusDays(val),S);
    	infoCompras.add(io);
    	return true;
    }

	@Override
    // corregido, se pasan mas parametros para la ejecucion
    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual,String UsuarioQueSeHaceConsulta) {
        if (UsuarioRegistradoActual.equals(UsuarioQueSeHaceConsulta)) {
            System.out.println("The strings are equal.");
        } else {
            return obtenerDatosUsuarioEspecial(String UsuarioQueSeHaceConsulta); 
        }
    
    // esto es para el caso visitantes 
    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioQueSeHaceConsulta) {
        String nickname =  getNickname();
        String nombre = getNombre();
        String apellido = getApellido();
        String correoElectronico = getCorreo_electronico();
        byte[] imagen = getImagen(); 
        HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
        
        for (OfertaLaboral oferta : ofertasLaborales) {
            if (oferta.getEstado() == EstadoOL.Confirmada) {
                DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
                dtOfertas.add(dtOferta);   
            }// si oferta laboral confirmada se muestra
        }
        
        return new DTEmpresa(nickname, correoElectronico, apellido, nombre, descripcion, url, dtOfertas, imagen);                                                                       
    }


}
