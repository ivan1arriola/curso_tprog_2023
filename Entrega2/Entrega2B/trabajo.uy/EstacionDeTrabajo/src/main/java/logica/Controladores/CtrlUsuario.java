package main.java.logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.clases.Empresa;
import main.java.logica.clases.Keyword;
import main.java.logica.clases.OfertaLaboral;
import main.java.logica.clases.Paquete;
import main.java.logica.clases.Postulacion;
import main.java.logica.clases.Postulante;
import main.java.logica.clases.Usuario;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlUsuario;
import main.java.logica.manejadores.KeywordHandler;
import main.java.logica.manejadores.OfertaLaboralHandler;
import main.java.logica.manejadores.PaqueteHandler;
import main.java.logica.manejadores.TipoOfertaHandler;
import main.java.logica.manejadores.UsuarioHandler;

// import main.java.logica.Controladores.*; NO SE USA (CHECKSTYLE)

public class CtrlUsuario implements ICtrlUsuario {
	// empresa con URL y sin imagen	
	public boolean altaEmpresaURL(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
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
			Empresa e = new Empresa(nick, nombre, apellido, mail, contraseña, desc, URL);
			UH.agregar(e);
		}
		
		return !b1 && !b2;
	}

	// empresa sin URL ni imagen
	public boolean altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
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
			Empresa e = new Empresa(nick, nombre, apellido, mail, contraseña, desc);
			UH.agregar(e);
		}
		return !b1 && !b2;
	}

	public boolean altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
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
			Postulante p = new Postulante(nick, contraseña, nombre, apellido, mail, fecha_nac, nacionalidad);
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
			// Usuario u = entry.getValue(); NO SE USA (CHECKSTYLE)
			res.add(entry.getKey()); 
		}
		return res;
	}

	public boolean existePostulacion(String nickname, String nombre) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante p = (Postulante) UH.buscarNick(nickname);
		if(p != null) {
			return p.existePostulacion(nombre);
		}
		else {
			// throw new IllegalArgumentException("Usuario " + nick + " no existe");
			return false;
		}
	}

	public Postulacion crearPostulacion(String nick, String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		
		Postulante p = (Postulante) UH.buscarNick(nick);
		if (p == null) { throw new IllegalArgumentException("Usuario " + nick + " no existe"); }
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

	public boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA,List<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida{
		List<Keyword> keywords = new ArrayList<>();
		
		UsuarioHandler UH = UsuarioHandler.getInstance();
		KeywordHandler KH = KeywordHandler.getInstance();
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		
		HashMap<String,Keyword> k = KH.obtener();
		for (Map.Entry<String, Keyword> entry : k.entrySet()) {
			if(keys.contains(entry.getKey())) {
				keywords.add(entry.getValue());
			}
		}
		
		if(UH.existeNick(nickname_e)) {
			Empresa e = (Empresa) UH.buscarNick(nickname_e);
			
			if(e != null) {
				CtrlOferta CO = new CtrlOferta();
				boolean ofer = CO.existeOferta(nombre);
				if(!ofer) {
					PaqueteHandler PH = PaqueteHandler.getInstance();
					Paquete paq;
					if(paquete != null) {
						paq = PH.buscar(paquete);
					}
					else { 
						paq = null;
					}
					
					OfertaLaboral ol = e.altaOfertaLaboral(TOH.buscar(tipo), nombre, descripcion, horario, remun, ciu, dep, FechaA, keywords, estado, img, paq);
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


	// -------------------------------------------------------------------------------------
	// ################################  NUEVAS OPERACIONES ################################ 
	// -------------------------------------------------------------------------------------
	
	public HashSet<String> listarKeywords(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		List<Keyword> keywords = ol.getKeywords();
		HashSet<String> res = new HashSet<String>();
		for (int i = 0; i < keywords.size(); i++) {
			res.add(keywords.get(i).getNombre());
		}
		return res;
	}

	public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname, String nick) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Usuario u = UH.buscarNick(nick);
		if (nick.equals(UsuarioNickname)) {
			DTUsuario user = u.obtenerDatosUsuario();
			return user;
		}
		else {
			DTUsuario userEsp = u.obtenerDatosUsuarioEspecial(UsuarioNickname, nick);
			return userEsp;
		}	
	}

	public DTUsuario obtenerDatosUsuarioVisitantes(String nick) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Usuario u = UH.buscarNick(nick);
		DTUsuario user = u.obtenerDatosUsuario();
		return user;
	}



	// public void cerrarSesion(String nickname) {    } NO EXISTE


	public  DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) {
		CtrlOferta COL = new CtrlOferta();
		DTOfertaExtendidoSinPConK infoOLVisitante = COL.infoOfertaLaboralVisitante(nombre_oferta);
		return infoOLVisitante;
	}


	public DTPostulacion obtenerDatosPostulacionW(String postulante_nick, String ofer) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante u = (Postulante) UH.buscarNick(postulante_nick);
		DTPostulacion datosPostu = u.obtenerDatosPostulacion(postulante_nick, ofer);
		return datosPostu;
	}


	public DTPaquete obtenerDatosPaquete(String paq) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete p = PH.buscar(paq);
		DTPaquete datosPaq = p.getDTPaquete();
		return datosPaq;
		
	}

	// NO EXISTEN MAS
	// public  boolean  iniciarSesionCorreo(String email, String contrasenia); 
	// public  boolean iniciarSesionNickname(String nickname, String contrasenia); 


	public boolean validarCredenciales(String id, String contraseña) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Usuario u;
		// Verificar si 'id' es un correo electrónico. Poner la er que sigue el correo
		if (id.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			u = UH.buscarCorreo(id);
			if (u.getContraseña().equals(contraseña)) { return true; }
			else 									  { return false; }
		} 
		else {
			u = UH.buscarNick(id); 
			if (u.getContraseña().equals(contraseña)) { return true; }
			else 									  {return false; }
			
		}
		
	}




	public void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, LocalDate fecha_nac, String nacionalidad) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UH.buscarNick(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setCorreo_electronico(correo);
		postulante.setContraseña(contraseña); 
		postulante.setImagen(imagen);
		postulante.setFecha_nac(fecha_nac);
		postulante.setNacionalidad(nacionalidad);
	}

	public void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, LocalDate fecha_nac, String nacionalidad) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UH.buscarNick(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setCorreo_electronico(correo);
		postulante.setContraseña(contraseña); 
		postulante.setFecha_nac(fecha_nac);
		postulante.setNacionalidad(nacionalidad);
	}


	public void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, String descripcion) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setContraseña(contraseña); 
		empresa.setCorreo_electronico(correo);
		empresa.seturl(URL); 
		empresa.setDescripcion(descripcion);
		
	}

	public void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String descripcion) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setCorreo_electronico(correo);
		empresa.setContraseña(contraseña); 
		empresa.setDescripcion(descripcion);
	}

	public void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, byte[] imagen, String descripcion) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setContraseña(contraseña);
		empresa.setCorreo_electronico(correo);
		empresa.seturl(URL); 
		empresa.setImagen(imagen);
		empresa.setDescripcion(descripcion);
	}

	public void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, String descripcion) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setCorreo_electronico(correo);
		empresa.setContraseña(contraseña); 
		empresa.setImagen(imagen);
		empresa.setDescripcion(descripcion);
	}

	public boolean tieneURL(String nickname) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname);
		boolean tiene = empresa.tieneURL();
		return tiene;
	}

	public boolean hayPostulacionW(String postulante_nick, String ofer) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UH.buscarNick(postulante_nick);
		boolean existe = postulante.existePostulacion(ofer);
		return existe;
	}

	
	public boolean altaEmpresaURLyImagen(String nick, String contraseña, String nombre, String ap, String mail, String desc, String URL, byte[] imagen) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		boolean existe = (UH.existeNick(nick) || UH.existeCorreo(mail));
		if (!existe) {
			Empresa e = new Empresa(nick, nombre, ap, mail, contraseña, imagen, desc, URL); // falta agregarle el parametro img
			UH.agregar(e);
			return true;
		}
		else { return false; }
		
	}


	// alta postulante con imagen
	public boolean altaPostulanteImagen(String nick, String contraseña, String nombre, String apellido, LocalDate fecha_nac, String mail, String nacionalidad, byte[] imagen) { 
		UsuarioHandler UH = UsuarioHandler.getInstance();
		boolean existe = (UH.existeNick(nick) || UH.existeCorreo(mail));
		if (!existe) {
			Postulante p = new Postulante(nick, contraseña, nombre, apellido, mail, fecha_nac, nacionalidad, imagen); // falta agregarle el parametro img
			UH.agregar(p);
			return true;
		}
		else { return false; }
	}

	// necesito otro constructor?
	public boolean altaEmpresaImagen(String nick, String contraseña, String nombre, String ap, String mail, String desc, byte[] imagen) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		boolean existe = (UH.existeNick(nick) || UH.existeCorreo(mail));
		if (!existe) {
			Empresa e = new Empresa(nick, nombre, ap, mail, contraseña, imagen, desc); //  agregarle el parametro img
			UH.agregar(e);
			return true;
		}
		else { return false; }
		
	}


	public HashSet<String> listarPostulantesDeOfertas(String nickname_e, String oferta) {
		HashSet<String> res = new HashSet<>();
		UsuarioHandler UH = UsuarioHandler.getInstance();
		HashMap<String, Usuario> usuarios = UH.obtenerNick();
		for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
			Usuario u = entry.getValue();
			if (!(u.esEmpresa())) {
				Postulante p = (Postulante) u;
				boolean esta = p.existePostulacion(oferta); // CUIDADO CON ESTA OPERACION DEVUELVE BOOLEANO!!
				if (esta) {
					res.add(p.getNickname());
				}
			}
		}
		return res;
		
	}

	public HashSet<String> listarOfertasLaboralesConfirmadas(String nickname_e) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UH.buscarNick(nickname_e);
		HashSet<String> OLConfirmadas = empresa.listarOfertasLaboralesConfirmadas(); // falta implementar la operacion
		return OLConfirmadas;
	}

	public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante postulante= (Postulante) UH.buscarNick(nick);
		boolean edito = postulante.editarPostulacion(nombre, cvAbreviado, motivacion); // falta operacion
		return edito;
	}

}
