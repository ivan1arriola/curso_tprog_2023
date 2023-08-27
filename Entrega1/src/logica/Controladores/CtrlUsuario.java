	package logica.Controladores;

import java.time.LocalDate;
import java.util.*;

import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import logica.Clases.Empresa;
import logica.Clases.Keyword;
import logica.Clases.OfertaLaboral;
import logica.Clases.Postulante;
import logica.Clases.TipoOferta;
import logica.Clases.Usuario;
import logica.Clases.Postulacion;
import logica.Datatypes.*;
import logica.Enumerados.DepUY;
import logica.Interfaces.*;
import logica.Manejadores.*;

public class CtrlUsuario implements ICtrlUsuario {
	
    public boolean altaEmpresaURL(String nick, String nombre, String apellido, String mail, String nombreE, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	boolean b1 = UH.existeNick(nick);
    	boolean b2 = UH.existeCorreo(mail);

    	if(b1 && b2) {
    		throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
    	}
    	else {
    		if(b1) {
    			throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
    		}
    		else if(b2) {
    			throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
    		}
    	}
    	
    	if (!b1 && !b2) {
    		Empresa e = new Empresa(nick, nombre, apellido, mail, nombreE, desc, URL);
    		UH.agregar(e);
    	}
    	
    	return !b1 && !b2;
    }

    public boolean altaEmpresa(String nick, String nombre, String apellido, String mail, String nombreE, String desc) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	boolean b1 = UH.existeNick(nick);
    	boolean b2 = UH.existeCorreo(mail);
    	
    	if(b1 && b2) {
    		throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
    	}
    	else {
    		if(b1) {
    			throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
    		}
    		else if(b2) {
    			throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
    		}
    	}
    	
    	if (!b1 && !b2) {
    		Empresa e = new Empresa(nick, nombre, apellido, mail, nombreE, desc);
    		UH.agregar(e);
    	}
    	return !b1 && !b2;
    }
    
    public boolean altaPostulante(String nick, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	boolean b1 = UH.existeNick(nick);
    	boolean b2 = UH.existeCorreo(mail);

    	if(b1 && b2) {
    		throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
    	}
    	else {
    		if(b1) {
    			throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
    		}
    		else if(b2) {
    			throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
    		}
    	}
    	
    	if (!b1 && !b2) {
    		Postulante p = new Postulante(nick, nombre, apellido, mail, fecha_nac, nacionalidad);
    		UH.agregar(p);
    	}
    	
    	return !b1 && !b2;
    }
    
    public HashSet<String> listarEmpresas(){
    	HashSet<String> res = new HashSet<>();
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	HashMap<String,Usuario> usuarios = UH.obtenerNick();
    	for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
    	    Usuario u = entry.getValue();
    	    boolean b = u.esEmpresa();
    	    if(b) {
    	    	res.add(u.getNickname());
    	    }
    	}
    	return res;
    }
    
    public DTOfertaExtendido consultaOfertaLaboral(String nombre) {
    	CtrlOferta CO = new CtrlOferta();
    	return CO.obtenerOfertaLaboral(nombre);
    }
    
    public DTUsuario obtenerDatosUsuario(String nick) {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Usuario u = UH.buscarNick(nick);
    	return u.obtenerDatosUsuario();
    }
    
    public HashSet<String> listarNicknamesUsuarios(){
    	HashSet<String> res = new HashSet<>();
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	HashMap<String, Usuario> usuarios = UH.obtenerNick();
    	for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
    	    Usuario u = entry.getValue();
    	    res.add(entry.getKey());
    	}
    	return res;
    }
    
    public boolean existePostulacion(String nickname, String nombre) {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Postulante p = (Postulante) UH.buscarNick(nickname);
    	return p.existePostulacion(nombre);
    }
    
    public Postulacion crearPostulacion(String nick, String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	
    	Postulante p = (Postulante) UH.buscarNick(nick);
    	return p.crearPostulacion(cv, motivacion, fecha, URLDocExtras, OferLab);
    }
    
    public HashSet<String> obtenerNicknamesPostulantes() {
    	HashSet<String> res = new HashSet<>();
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	HashMap<String,Usuario> usuarios = UH.obtenerNick();
    	for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
    	    Usuario u = entry.getValue();
    	    boolean b = u.esEmpresa();
    	    if(!b) {
    	    	res.add(entry.getKey());
    	    }
    	}
    	return res;
    }

    public void ingresarDatosEditados(String nickname, String nombre, String apellido) {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Usuario u = UH.buscarNick(nickname);
    	u.setNombre(nombre);
    	u.setApellido(apellido);
    }
    
    public boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA,List<String> keys) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida{
    	List<Keyword> keywords = new ArrayList<>();
    	
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	KeywordHandler KH = KeywordHandler.getInstance();
    	TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	
    	HashMap<String,Keyword> k = KH.obtener();
    	for (Map.Entry<String, Keyword> entry : k.entrySet()) {
    	    if(keys.contains(entry.getKey()))
    	    	keywords.add(entry.getValue());
    	}
    	
    	
    	if(UH.existeNick(nickname_e)) {
        	Empresa e = (Empresa) UH.buscarNick(nickname_e);
        	
        	if(e != null) {
            	CtrlOferta CO = new CtrlOferta();
            	boolean ofer = CO.existeOferta(nombre);
            	if(!ofer) {
            		OfertaLaboral ol = e.altaOfertaLaboral(TOH.buscar(tipo), nombre, descripcion, horario, remun, ciu, dep, FechaA, keywords);
            		OLH.agregar(ol);
            	}
            	return !ofer;
        	}
        	else {
        		throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
        	}
    	}
    	else {
    		throw new ExceptionUsuarioNoEncontrado("No existe un usuario con el nickname indicado.");
    	}

    	
    }
    
    public Set<String> listarOfertasLaborales(String nickname){
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Usuario u = UH.buscarNick(nickname);
    	return u.listarOfertasLaborales();
    }
}
