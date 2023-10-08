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
	public boolean altaEmpresaURL(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc,  String URL) throws ExceptionUsuarioCorreoRepetido,  ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existeNick = UsuarioH.existeNick(nick);
		boolean existeCorreo = UsuarioH.existeCorreo(mail);

		if(existeNick && existeCorreo) {
			throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
		}
		else {
			if(existeNick) {
				throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
			}
			else if(existeCorreo) {
				throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
			}
		}
		
		if (!existeNick && !existeCorreo) {
			Empresa empresa = new Empresa(nick,  nombre,  apellido,  mail,  contraseña,  desc,  URL);
			UsuarioH.agregar(empresa);
		}
		
		return !existeNick && !existeCorreo;
	}

	// empresa sin URL ni imagen
	public boolean altaEmpresa(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc) throws ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido,  ExceptionUsuarioCorreoRepetido {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existeNick = UsuarioH.existeNick(nick);
		boolean existeCorreo = UsuarioH.existeCorreo(mail);

		if(existeNick && existeCorreo) {
			throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
		}
		else {
			if(existeNick) {
				throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
			}
			else if(existeCorreo) {
				throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
			}
		}
		
		if (!existeNick && !existeCorreo) {
			Empresa empresa = new Empresa(nick,  nombre,  apellido,  mail,  contraseña,  desc);
			UsuarioH.agregar(empresa);
		}
		
		return !existeNick && !existeCorreo;
	}

	public boolean altaPostulante(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  LocalDate fecha_nac,  String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido,  ExceptionUsuarioCorreoRepetido {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existeNick = UsuarioH.existeNick(nick);
		boolean existeCorreo = UsuarioH.existeCorreo(mail);

		if(existeNick && existeCorreo) {
			throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
		}
		else {
			if(existeNick) {
				throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
			}
			else if(existeCorreo) {
				throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
			}
		}
		
		if (!existeNick && !existeCorreo) {
			Postulante postulante = new Postulante(nick,  contraseña,  nombre,  apellido,  mail,  fecha_nac,  nacionalidad);
			UsuarioH.agregar(postulante);
		}
		
		return !existeNick && !existeCorreo;
	}

	public Set<String> listarEmpresas(){
		Set<String> res = new HashSet<>();
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
		for (Map.Entry<String,  Usuario> entry : usuarios.entrySet()) {
			Usuario user = entry.getValue();
			boolean esEmp = user.esEmpresa();
			if(esEmp) {
				res.add(user.getNickname());
			}
		}
		return res;
	}

	public DTOfertaExtendido consultaOfertaLaboral(String nombre) {
		CtrlOferta CtrlOfer = new CtrlOferta();
		return CtrlOfer.obtenerOfertaLaboral(nombre);
	}

	public DTUsuario obtenerDatosUsuario(String nick) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario user = UsuarioH.buscarNick(nick);
		return user.obtenerDatosUsuario();
	}

	public Set<String> listarNicknamesUsuarios(){
		Set<String> res = new HashSet<>();
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Map<String,  Usuario> usuarios = UsuarioH.obtenerNick(); 
		for (Map.Entry<String,  Usuario> entry : usuarios.entrySet()) {
			// Usuario u = entry.getValue(); NO SE USA (CHECKSTYLE)
			res.add(entry.getKey()); 
		}
		return res;
	}

	public boolean existePostulacion(String nickname,  String nombre) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
		if(postulante != null) {
			return postulante.existePostulacion(nombre);
		}
		else {
			// throw new IllegalArgumentException("Usuario " + nick + " no existe");
			return false;
		}
	}

	public Postulacion crearPostulacion(String nick,  String curriculumVitae,  String motivacion,  LocalDate fecha,  String URLDocExtras,  OfertaLaboral OferLab) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		
		Postulante postulante = (Postulante) UsuarioH.buscarNick(nick);
		if (postulante == null) { 
			throw new IllegalArgumentException("Usuario " + nick + " no existe"); }
		return postulante.crearPostulacion(curriculumVitae,  motivacion,  fecha,  URLDocExtras,  OferLab);
	}

	public Set<String> obtenerNicknamesPostulantes() {
		Set<String> res = new HashSet<>();
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
		for (Map.Entry<String,  Usuario> entry : usuarios.entrySet()) {
			Usuario user = entry.getValue();
			boolean esEmp = user.esEmpresa();
			if(!esEmp) {
				res.add(entry.getKey());
			}
		}
		return res;
	}

	public void ingresarDatosEditados(String nickname,  String nombre,  String apellido) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario user = UsuarioH.buscarNick(nickname);
		user.setNombre(nombre);
		user.setApellido(apellido);
	}

	public boolean altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  String descripcion,  DTHorario horario,  float remun,  String ciu,  DepUY dep,  LocalDate FechaA, List<String> keys,  EstadoOL estado,  byte[] img,  String paquete) throws ExceptionUsuarioNoEncontrado,  ExceptionEmpresaInvalida{
		List<Keyword> keywords = new ArrayList<>();
		
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		KeywordHandler KeywordH = KeywordHandler.getInstance();
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		
		Map<String, Keyword> keyw = KeywordH.obtener();
		for (Map.Entry<String,  Keyword> entry : keyw.entrySet()) {
			if(keys.contains(entry.getKey())) {
				keywords.add(entry.getValue());
			}
		}
		
		if(UsuarioH.existeNick(nickname_e)) {
			Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
			
			if(empresa != null) {
				CtrlOferta CtrlOfer = new CtrlOferta();
				boolean ofer = CtrlOfer .existeOferta(nombre);
				if(!ofer) {
					PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
					Paquete paq;
					if(paquete != null) {
						paq = PaqueteH.buscar(paquete);
					}
					else { 
						paq = null;
					}
					
					OfertaLaboral oferL = empresa.altaOfertaLaboral(TOH.buscar(tipo),  nombre,  descripcion,  horario,  remun,  ciu,  dep,  FechaA,  keywords,  estado,  img,  paq);
					OLH.agregar(oferL);
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
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario user = UsuarioH.buscarNick(nickname);
		return user.listarOfertasLaborales();
	}


	// -------------------------------------------------------------------------------------
	// ################################  NUEVAS OPERACIONES ################################ 
	// -------------------------------------------------------------------------------------
	
	public Set<String> listarKeywords(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
		List<Keyword> keywords = oferLab.getKeywords();
		Set<String> res = new HashSet<String>();
		for (int i = 0; i < keywords.size(); i++) {
			res.add(keywords.get(i).getNombre());
		}
		return res;
	}

	public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname,  String nick) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario usuario = UsuarioH.buscarNick(nick);
		if (nick.equals(UsuarioNickname)) {
			DTUsuario user = usuario.obtenerDatosUsuario();
			return user;
		}
		else {
			DTUsuario userEsp = usuario.obtenerDatosUsuarioEspecial(UsuarioNickname,  nick);
			return userEsp;
		}	
	}

	public DTUsuario obtenerDatosUsuarioVisitantes(String nick) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario usuario = UsuarioH.buscarNick(nick);
		DTUsuario user = usuario.obtenerDatosUsuario();
		return user;
	}



	// public void cerrarSesion(String nickname) {    } NO EXISTE


	public  DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) {
		CtrlOferta COL = new CtrlOferta();
		DTOfertaExtendidoSinPConK infoOLVisitante = COL.infoOfertaLaboralVisitante(nombre_oferta);
		return infoOLVisitante;
	}


	public DTPostulacion obtenerDatosPostulacionW(String postulante_nick,  String ofer) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante user = (Postulante) UsuarioH.buscarNick(postulante_nick);
		DTPostulacion datosPostu = user.obtenerDatosPostulacion(postulante_nick,  ofer);
		return datosPostu;
	}


	public DTPaquete obtenerDatosPaquete(String paq) {
		PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
		Paquete paquete = PaqueteH.buscar(paq);
		DTPaquete datosPaq = paquete.getDTPaquete();
		return datosPaq;
		
	}

	// NO EXISTEN MAS
	// public  boolean  iniciarSesionCorreo(String email,  String contrasenia); 
	// public  boolean iniciarSesionNickname(String nickname,  String contrasenia); 


	public boolean validarCredenciales(String identificador,  String contraseña) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Usuario user;
		// Verificar si 'id' es un correo electrónico. Poner la er que sigue el correo
		if (identificador.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			user = UsuarioH.buscarCorreo(identificador);
			if (user.getcontrasenia().equals(contraseña)) { 
				return true; }
			else 									  {
				return false; }
		} 
		else {
			user = UsuarioH.buscarNick(identificador); 
			if (user.getcontrasenia().equals(contraseña)) { 
				return true; }
			else 									  {
				return false; }
			
		}
		
	}




	public void ingresarDatosEditadosPostulanteImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  byte[] imagen,  LocalDate fecha_nac,  String nacionalidad) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setCorreoElectronico(correo);
		postulante.setContrasenia(contraseña); 
		postulante.setImagen(imagen);
		postulante.setFechaNac(fecha_nac);
		postulante.setNacionalidad(nacionalidad);
	}

	public void ingresarDatosEditadosPostulante(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  LocalDate fecha_nac,  String nacionalidad) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setCorreoElectronico(correo);
		postulante.setContrasenia(contraseña); 
		postulante.setFechaNac(fecha_nac);
		postulante.setNacionalidad(nacionalidad);
	}


	public void ingresarDatosEditadosEmpresaURL(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String URL,  String descripcion) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setContrasenia(contraseña); 
		empresa.setCorreoElectronico(correo);
		empresa.seturl(URL); 
		empresa.setDescripcion(descripcion);
		
	}

	public void ingresarDatosEditadosEmpresa(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String descripcion) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setCorreoElectronico(correo);
		empresa.setContrasenia(contraseña); 
		empresa.setDescripcion(descripcion);
	}

	public void ingresarDatosEditadosEmpresaURLImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String URL,  byte[] imagen,  String descripcion) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setContrasenia(contraseña); 
		empresa.setCorreoElectronico(correo);
		empresa.seturl(URL); 
		empresa.setImagen(imagen);
		empresa.setDescripcion(descripcion);
	}

	public void ingresarDatosEditadosEmpresaImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  byte[] imagen,  String descripcion) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setCorreoElectronico(correo);
		empresa.setContrasenia(contraseña); 
		empresa.setImagen(imagen);
		empresa.setDescripcion(descripcion);
	}

	public boolean tieneURL(String nickname) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
		boolean tiene = empresa.tieneURL();
		return tiene;
	}

	public boolean hayPostulacionW(String postulante_nick,  String ofer) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante postulante = (Postulante) UsuarioH.buscarNick(postulante_nick);
		boolean existe = postulante.existePostulacion(ofer);
		return existe;
	}

	
	public boolean altaEmpresaURLyImagen(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc,  String URL,  byte[] imagen) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
		if (!existe) {
			Empresa empresa = new Empresa(nick,  nombre,  apellido,  mail,  contraseña,  imagen,  desc,  URL); // falta agregarle el parametro img
			UsuarioH.agregar(empresa);
			return true;
		}
		else { 
			return false; }
		
	}


	// alta postulante con imagen
	public boolean altaPostulanteImagen(String nick,  String contraseña,  String nombre,  String apellido,  LocalDate fecha_nac,  String mail,  String nacionalidad,  byte[] imagen) { 
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
		if (!existe) {
			Postulante postulante = new Postulante(nick,  contraseña,  nombre,  apellido,  mail,  fecha_nac,  nacionalidad,  imagen); // falta agregarle el parametro img
			UsuarioH.agregar(postulante);
			return true;
		}
		else { 
			return false; }
	}

	// necesito otro constructor?
	public boolean altaEmpresaImagen(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc,  byte[] imagen) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
		if (!existe) {
			Empresa empresa = new Empresa(nick,  nombre,  apellido,  mail,  contraseña,  imagen,  desc); //  agregarle el parametro img
			UsuarioH.agregar(empresa);
			return true;
		}
		else { 
			return false; }
		
	}


	public Set<String> listarPostulantesDeOfertas(String nickname_e,  String oferta) {
		Set<String> res = new HashSet<>();
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Map<String,  Usuario> usuarios = UsuarioH.obtenerNick();
		for (Map.Entry<String,  Usuario> entry : usuarios.entrySet()) {
			Usuario user = entry.getValue();
			if (!(user.esEmpresa())) {
				Postulante postulante = (Postulante) user;
				boolean esta = postulante.existePostulacion(oferta); // CUIDADO CON ESTA OPERACION DEVUELVE BOOLEANO!!
				if (esta) {
					res.add(postulante.getNickname());
				}
			}
		}
		return res;
		
	}

	public Set<String> listarOfertasLaboralesConfirmadas(String nickname_e) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
		Set<String> OLConfirmadas = empresa.listarOfertasLaboralesConfirmadas(); // falta implementar la operacion
		return OLConfirmadas;
	}

	public boolean modificarPostulacion(String nombre,  String nick,  String cvAbreviado,  String motivacion) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Postulante postulante= (Postulante) UsuarioH.buscarNick(nick);
		boolean edito = postulante.editarPostulacion(nombre,  cvAbreviado,  motivacion); // falta operacion
		return edito;
	}

}
