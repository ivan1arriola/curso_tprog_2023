package main.java.logica.clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import main.java.logica.datatypes.DTCantTO;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;


public class Empresa extends Usuario {

    private String descripcion;
    private String url;
    private HashSet<OfertaLaboral> ofertasLaborales;
    private HashSet<InfoCompra> infoCompras;
    
    // constructor empresa con imagen y url 
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = urlE;
    }

    // constructor empresa con imagen sin url 
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc) {
        super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = null;
    }

    // constructor empresa con url sin imagen 
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico, contrasena);
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = urlE;
    }

    // constructor empresa sin imagen ni url 
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
        
        if (ofertasLaborales.size() != 0) {
	        for (OfertaLaboral ol : ofertasLaborales){
	            lista.add(ol.getNombre());
	        }
        }

        return lista;
    }
    
    @Override
    public boolean esEmpresa() {
        return true;
    }

    public OfertaLaboral altaOfertaLaboral(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords, EstadoOL estado, byte[] img, Paquete paq){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, estado, paq);
        ofertasLaborales.add(ol);
        return ol;
    }

    
    public OfertaLaboral altaOfertaLaboralImagen(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remu, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, EstadoOL estado, byte[] img) {
    	OfertaLaboral ol = new OfertaLaboral(keyw, tipo, nombre, descripcion, ciu, dep, horario, remu, fechaA, estado, img);
    	ofertasLaborales.add(ol);
    	return ol;
    }
    
    public OfertaLaboral altaOfertaLaboralImagenPaquete(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remu, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, EstadoOL estado, byte[] img, Paquete paquete) {
    	OfertaLaboral ol = new OfertaLaboral(keyw, tipo, nombre, descripcion, ciu, dep, horario, remu, fechaA, estado, img, paquete);
    	ofertasLaborales.add(ol);
    	return ol;	
    }
    
    public DTUsuario obtenerDatosUsuario() { // obtenerDatosUsuario(): DTUsuario
    	String nickname =  getNickname();
    	String nombre = getNombre();
        String apellido = getApellido();
        String correoElectronico = getCorreo_electronico();
        String contraseña = getContraseña();
        byte[] imagen = getImagen();
        			       
        HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
        
        for (OfertaLaboral oferta : ofertasLaborales) {
        	DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
            dtOfertas.add(dtOferta);   
        }
        
        return new DTEmpresa(nickname, correoElectronico, apellido, nombre, contraseña, descripcion, url, dtOfertas, imagen);
        
    }
    
    public HashSet<String> listarOfertasLaboralesConfirmadas(){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if (ol.getEstado() == EstadoOL.Confirmada) {
            	res.add(ol.getNombre());
            }
        }
        return res;
    }
    
    public HashSet<String> listarOfertasLaboralesConfirmadasKeyword(String ks){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if (ol.getEstado() == EstadoOL.Confirmada && ol.tieneKeyword(ks)) {
            	res.add(ol.getNombre());
            }
        }
        return res;
    }
    
    public HashSet<String> listarOfertasLaboralesIngresadas(){
    	HashSet<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if (ol.getEstado() == EstadoOL.Ingresada) {
            	res.add(ol.getNombre());
            }
        }
        return res;
    }
    
    public boolean existeOfertaLaboral(String nombre_oferta) {
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ol = iterator.next();
            if (ol.getNombre().equals(nombre_oferta)) {
            	return true;
            }
        }
        
    	return false;
    }
    
    public boolean tieneURL() {
    	return url != null;
    }
    
    public boolean compraPaquetes(Paquete paq) {
        for (InfoCompra ic : infoCompras) {
        	if ((ic.getPaquete()).getNombre().equals(paq.getNombre())) {
        		return false;
        	}
        }
    	float costo = paq.getCosto();
    	LocalDate fa = paq.getfechaAlta();
    	// int val = paq.getValidez();
    	HashSet<DTCantTO> S = paq.obtenerDTSCantTO();
    	
    	InfoCompra io = new InfoCompra(fa, costo, paq, this, S);
    	infoCompras.add(io);
    	return true;
    }

	@Override
    // corregido, se pasan mas parametros para la ejecucion
    public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual, String UsuarioQueSeHaceConsulta) {
		DTEmpresa empre;
		if (UsuarioRegistradoActual.equals(UsuarioQueSeHaceConsulta)) {
            String nickname =  getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getCorreo_electronico();
            String contraseña = getContraseña();
            byte[] imagen = getImagen(); 
            HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
            
            for (OfertaLaboral oferta : ofertasLaborales) {
                DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
                dtOfertas.add(dtOferta);
                // muestro toda oferta laboral 
            }
            empre = new DTEmpresa(nickname, correoElectronico, apellido, nombre, contraseña, descripcion, url, dtOfertas, imagen);   
        } else {
            String nickname =  getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getCorreo_electronico();
            String contraseña = getContraseña();
            byte[] imagen = getImagen(); 
            HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
            
            for (OfertaLaboral oferta : ofertasLaborales) {
                if (oferta.getEstado() == EstadoOL.Confirmada) {
                    DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
                    dtOfertas.add(dtOferta);   
                }// si oferta laboral confirmada se muestra
            }
            empre = new DTEmpresa(nickname, correoElectronico, apellido, nombre, contraseña, descripcion, url, dtOfertas, imagen); 
        }
        return empre;
    }
}
