package utils;

import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;

import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import enumeration.TipoUsuario;
import interfaces.ILogica;
import javabeans.CantTipoPublicacionBean;
import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import javabeans.UsuarioSinInfoSocialBean;

import logica.servidor.*;

public class Logica implements ILogica {

	Servidor servidor;
	
	public Logica(){
		
		
		ServidorService service = new ServidorService();
		servidor = service.getServidorPort();
		
    }



    

	public Set<DtTipoOferta> obtenerTipoOfertas(){
		Set<String> lista =  new TreeSet<>( servidor.listarTipoDePublicaciones().getListaString());
		Set<DtTipoOferta> tipoOfertas = new TreeSet<>(new ComparadorDtTipoOferta());
		for (String nombreTipoOferta : lista) {
			try {
				tipoOfertas.add(servidor.obtenerDatosTO(nombreTipoOferta));
			} catch (ExcepcionTipoOfertaNoExistente_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return tipoOfertas;

	}

	@Override
	public DtTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente_Exception {
		return servidor.obtenerDatosTO(nombre);
	}

	@Override
	public void altaEmpresa(String nickname, String password, String nombre, String apellido, String email, String descripcionEmpresa, String sitioWebEmpresa, byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception, ExceptionUsuarioCorreoRepetido_Exception, ExceptionUsuarioNickYCorreoRepetidos_Exception {

		if (sitioWebEmpresa == null) {
			sitioWebEmpresa = ""; // Reemplazar URL nula con una cadena vacía
		}

		if (imagenBytes == null) {
			servidor.altaEmpresaURL(nickname, password, nombre, apellido, email, descripcionEmpresa, sitioWebEmpresa);
		} else {
			servidor.altaEmpresaURLyImagen(nickname, password, nombre, apellido, email, descripcionEmpresa, sitioWebEmpresa, imagenBytes);
		}


	}

	@Override
	public void altaPostulante(String nickname, String password, String nombre, String apellido, String email, LocalDate parse, String nacionalidad, byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception, ExceptionUsuarioCorreoRepetido_Exception, ExceptionUsuarioNickYCorreoRepetidos_Exception {
		if (imagenBytes == null) {
			servidor.altaPostulante(nickname, password, nombre, apellido, parse.toString(), email, nacionalidad);
		} else {
			servidor.altaPostulanteImagen(nickname, password, nombre, apellido, parse.toString(), email, nacionalidad, imagenBytes);
		}

	}

	@Override
	public void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String password, byte[] imagen, LocalDate fecha, String nacionalidad) {

	}

	@Override
	public void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String password, String link, byte[] imagen, String descripcion) {

	}

	@Override
	public PostulacionBean obtenerDatosPostulacionW(String nickname, String nombreOferta) throws ExceptionUsuarioNoEncontrado_Exception {
		PostulacionBean postulacion = new PostulacionBean();

		DtPostulacion dtPostulacion = servidor.obtenerDatosPostulacionW(nickname, nombreOferta);

		postulacion.setNicknamePostulante(dtPostulacion.getNombrePostulante());
		postulacion.setFecha(LocalDate.parse(dtPostulacion.getFecha()));
		postulacion.setCVitae(dtPostulacion.getCVitae());
		postulacion.setMotivacion(dtPostulacion.getMotivacion());
		postulacion.setNombreOfertaLaboral(nombreOferta);
		postulacion.setURLDocExtras(dtPostulacion.getURLDocExtras());
		postulacion.setVideo(dtPostulacion.getUrlVideo());

		return postulacion;
	}

	@Override
	public void altaPostulacion(String nombreOferta, String nickname, String curriculumAbreviado, String motivacion, String url, LocalDate fecha, String video) throws ExceptionUsuarioNoEncontrado_Exception, OfertaLaboralNoEncontrada_Exception {
		servidor.altaPostulacion(nombreOferta, nickname,curriculumAbreviado, motivacion, url, fecha.toString(), video   );
	}

	@Override
	public boolean nicknameDisponible(String nickname) {
		return !servidor.existeUsuarioConNickname(nickname);
	}

	@Override
	public boolean emailDisponible(String email) {
		return !servidor.existeUsuarioConEmail(email);
	}


	@Override
	public void cargarDatos() {
		try {
			servidor.cargarDatos();
		} catch (ExcepcionKeywordVacia_Exception | ExceptionValidezNegativa_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String imagenAString(byte[] bytes) {
        if (bytes != null) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }

	@Override
	public boolean validarCredenciales(String identificador, String contraseña) throws ExceptionUsuarioNoEncontrado_Exception {
		return servidor.validarCredenciales(identificador, contraseña);
	}

	@Override
	public UsuarioBean obtenerDatosUsuario(String nickname) {
	    UsuarioBean usuario = new UsuarioBean();
	        DtUsuario DtUsuario;
		try {
			DtUsuario = servidor.obtenerDatosUsuario(nickname);
	        usuario.setNickname(DtUsuario.getNickname());
	        usuario.setNombre(DtUsuario.getNombre());
	        usuario.setApellido(DtUsuario.getApellido());
	        usuario.setContrasenia(DtUsuario.getContrasenia());
	        usuario.setCorreoElectronico(DtUsuario.getCorreoElectronico());
			usuario.setImagen(imagenAString(DtUsuario.getImagen()));
			Set<DtUsuarioSinInfoSocial> S1 = new HashSet<>(DtUsuario.getSeguidores());
			Set<DtUsuarioSinInfoSocial> S2 = new HashSet<>(DtUsuario.getSeguidos());
			Set<UsuarioSinInfoSocialBean> seguidores = new HashSet<>();
			Set<UsuarioSinInfoSocialBean> seguidos = new HashSet<>();
			
			for (DtUsuarioSinInfoSocial elem : S1) {
				UsuarioSinInfoSocialBean u1 = new UsuarioSinInfoSocialBean();
				u1.setNickname(elem.getNickname());
				u1.setImagen(elem.getImagen().toString());
				u1.setNombre(elem.getNombre());
				u1.setApellido(elem.getApellido());
				u1.setContrasenia(elem.getContrasenia());
				u1.setCorreoElectronico(elem.getCorreoElectronico());
				seguidores.add(u1);
			}
			
			for (DtUsuarioSinInfoSocial elem : S2) {
				UsuarioSinInfoSocialBean u1 = new UsuarioSinInfoSocialBean();
				u1.setNickname(elem.getNickname());
				u1.setImagen(elem.getImagen().toString());
				u1.setNombre(elem.getNombre());
				u1.setApellido(elem.getApellido());
				u1.setContrasenia(elem.getContrasenia());
				u1.setCorreoElectronico(elem.getCorreoElectronico());
				seguidos.add(u1);
			}
			
			usuario.setSeguidores(seguidores);
			usuario.setSeguidos(seguidos);
			
	        if(DtUsuario instanceof DtEmpresa empresa) {
				usuario.setDescripcion(empresa.getDescripcion());
	        	usuario.setUrl(empresa.getUrl());
	        	usuario.setTipo(TipoUsuario.Empresa);
	        } else if(DtUsuario instanceof DtPostulante postulante){
	        	usuario.setFechaNac(LocalDate.parse(postulante.getFechaNac()));
	        	usuario.setNacionalidad(postulante.getNacionalidad());
	        	usuario.setOferFavs(postulante.getOfertasFavoritas());
	        	usuario.setTipo(TipoUsuario.Postulante);
	        } else {
				throw new IllegalArgumentException("El usuario no es ni empresa ni postulante");
			}
		} catch (Exception e) {
			usuario.setError("Se produjo un error al obtener los datos del usuario");
		}
	    
	    return usuario;
	}

	
	@Override
	public HashSet<String> obtenerSeguidores(String nickname) throws ExceptionUsuarioNoEncontrado_Exception {
		return new HashSet<String> (servidor.obtenerSeguidoresUsuario(nickname).getListaString());
	}	
	
	@Override
	public HashSet<String> obtenerSeguidos(String nickname) throws ExceptionUsuarioNoEncontrado_Exception {
		return new HashSet<String> (servidor.obtenerSeguidosUsuario(nickname).getListaString());
	}

	@Override
	public void modificarDatosUsuario(String nickname, UsuarioBean usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<UsuarioBean> listarUsuarios() {
    	 Set<String> nicknames = listarNicknamesUsuario();
         Set<UsuarioBean> usuarios = new TreeSet<>();
         for(String usuario : nicknames) {
         	usuarios.add(obtenerDatosUsuario(usuario));
         }
		return usuarios;
    	
    }

	@Override
	public Set<String> listarNicknamesUsuario() {
		List<String> nicknames = (List<String>) servidor.listarNicknamesUsuarios().getListaString();
		return new TreeSet<>(nicknames);
	}

	@Override
	public void altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, String horario, float remun, String ciu, Departamento dep, LocalDate fechaA, Set<String> keys, EstadoOfertaLaboral estado, String img, String paquete) {

	}


	@Override
	public Set<String> listarKeywords() {
		List<String> listaKeywords = servidor.listarKeywords().getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listaKeywords);
	}

	@Override
	public Set<String> listarPaquetesDeEmpresa(String nickname) throws ExceptionUsuarioNoEncontrado_Exception {
		List<String> listaPaquetes = servidor.listarComprasPaquete(nickname).getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listaPaquetes);
	}




	@Override
	public Set<String> listarTipoDePublicaciones() {
		List<String> listaTipoPublicaciones = servidor.listarTipoDePublicaciones().getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listaTipoPublicaciones);
	}



	



	@Override
	public void compraPaquetes(String nickname, String paquete, LocalDate now, int valor) {
		/*try {
			servidor.compraPaquetes(nickname, paquete, now, valor);
		} catch (ExceptionCompraPaqueteConValorNegativo
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	

	@Override
	public PaqueteBean obtenerDatosPaquete(String paquete) {
		DtPaquete dtPaquete = servidor.obtenerDatosPaquete(paquete);
		if(dtPaquete == null)
			return null;
		PaqueteBean paqueteBean = new PaqueteBean();
		paqueteBean.setCosto(dtPaquete.getCosto());
		paqueteBean.setDescripcion(dtPaquete.getDescripcion());
		paqueteBean.setDescuento(dtPaquete.getDescuento());
		paqueteBean.setFechaAlta(LocalDate.parse(dtPaquete.getFechaA()));
		
		paqueteBean.setImagen(imagenAString(dtPaquete.getImagen()));
		paqueteBean.setNombre(dtPaquete.getNombre());
		paqueteBean.setValidez(dtPaquete.getValidez());
		
		Set<CantTipoPublicacionBean> cantidades = new TreeSet<CantTipoPublicacionBean>();
		
		List<DtCantTO> DtCantidades = dtPaquete.getTiposDePub();
		
		for( DtCantTO cant : DtCantidades) {
			CantTipoPublicacionBean cantidadBean = new CantTipoPublicacionBean();
			
			cantidadBean.setCantidad(cant.getCantidad());
			cantidadBean.setNombre(cant.getNombre());
			
			cantidades.add(cantidadBean);
		}
		
		paqueteBean.setTiposDePub(cantidades);

		return paqueteBean;
	}

	@Override
	public Set<String> listarOfertasConfirmadasDeEmpresa(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception {
		List<String> listarOfertasLaboralesConfirmadas = servidor.listarOfertasLaboralesConfirmadas(nicknameParametro).getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listarOfertasLaboralesConfirmadas);
	}

	@Override
	public OfertaLaboralBean obtenerDatosOfertaLaboral(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception {
		OfertaLaboralBean ofertaLaboral = new OfertaLaboralBean();
		DtOfertaExtendido DtOferta = servidor.obtenerOfertaLaboral(nombreOferta);
		
		ofertaLaboral.setNombre(DtOferta.getNombre());
        ofertaLaboral.setDescripcion(DtOferta.getDescripcion());
        ofertaLaboral.setCiudad(DtOferta.getCiudad());
        ofertaLaboral.setCosto(DtOferta.getCosto());
        ofertaLaboral.setDepartamento(DtOferta.getDepartamento());
        ofertaLaboral.setFechaDeAlta(LocalDate.parse(DtOferta.getFechaDeAlta()));
        ofertaLaboral.setHorario(DtOferta.getHorario());
        ofertaLaboral.setImagen(imagenAString(DtOferta.getImagen()));
        ofertaLaboral.setRemuneracion(DtOferta.getRemuneracion());
        ofertaLaboral.setEstado(DtOferta.getEstado());
        ofertaLaboral.setNicknameEmpresa(DtOferta.getNicknameEmpresaPublicadora());
        ofertaLaboral.setCantFavs(DtOferta.getCantFavs());
        
        DtOfertaExtendidoSinPConK nuevoDatos = servidor.infoOfertaLaboralVisitante(nombreOferta);
        ofertaLaboral.setKeywords(new TreeSet<>(nuevoDatos.getKeywords()));


        return ofertaLaboral;
	}

	@Override
	public Set<String> listarOfertasLaboralesDeEmpresa(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception {
		List<String> todasLasOfertas = servidor.listarTodasLasOfertasLaborales(nicknameParametro).getListaString();
		return new TreeSet<>(todasLasOfertas);
	}

	@Override
	public Set<String> listarPostulacionesDePostulante(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception {
		List<String> listaPostulaciones = servidor.listarPostulacionesPostulante(nicknameParametro).getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listaPostulaciones);
	}


	@Override
	public PostulacionBean obtenerDatosPostulacion(String nombreOferta, String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception {
		PostulacionBean postulacion = new PostulacionBean();
		DtPostulacion DtPostulacion = servidor.obtenerDatosPostulacionW(nicknameParametro, nombreOferta);
		
		postulacion.setCVitae(DtPostulacion.getCVitae());
		postulacion.setFecha(LocalDate.parse(DtPostulacion.getFecha()));
		postulacion.setMotivacion(DtPostulacion.getMotivacion());
		postulacion.setNombreOfertaLaboral(nombreOferta);
		postulacion.setNicknamePostulante(nicknameParametro);
		postulacion.setURLDocExtras(DtPostulacion.getURLDocExtras());
		
		
		return postulacion;
	}

	@Override
	public List<UsuarioBean> obtenerPostulantesDeOferta(String nombreOferta, String empresaNickname) throws OfertaLaboralNoEncontrada_Exception, ExceptionUsuarioNoEncontrado_Exception {
		DtOfertaExtendidoSinPConK info = servidor.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
		List<UsuarioBean> postulantes = new ArrayList<>();
		if ( info instanceof DtOfertaExtendidoConKeywordsTit masData) {
			List<String> nicknamesDePostulantes = masData.getPostulaciones();
			for(String nickPostulante : nicknamesDePostulantes){
				postulantes.add(this.obtenerDatosUsuario(nickPostulante));

			}
		}

		return postulantes;
	}

	@Override
	public List<String> obtenerPostulantesDeOfertaString(String nombreOferta, String empresaNickname) throws OfertaLaboralNoEncontrada_Exception, ExceptionUsuarioNoEncontrado_Exception {
		DtOfertaExtendidoSinPConK info = servidor.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
		List<String> postulantes = new ArrayList<>();
		if ( info instanceof DtOfertaExtendidoConKeywordsTit masData) {
			postulantes = masData.getPostulaciones();

		}
		return postulantes;
	}
	
	



	
	
	
	@Override
	public PaqueteBean obtenerPaqueteDeOferta(String nombreOferta, String empresaNickname) throws Exception {

	    if (nombreOferta == null) {
	        throw new Exception("Oferta no tiene nombre");
	    }

		PaqueteBean paquete = null;

		// Obtener información de la oferta laboral de la empresa
		DtOfertaExtendidoSinPConK info = servidor.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);

	    if (info instanceof DtOfertaExtendidoConKeywordsTit masData) {
	        // La oferta contiene un paquete
			DtPaquete dtPaquete = masData.getPaq();
			if(dtPaquete == null) return null;
			paquete = obtenerDatosPaquete(dtPaquete.getNombre());

	    }

	    return paquete;
	}

	@Override
	public OfertaLaboralBean cargarDatosDePostulante(OfertaLaboralBean ofertaBean, String postulanteNickname)
	        throws Exception {
	    // Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }

	    // Obtener nuevos datos de la oferta para el postulante
	    DtOfertaExtendidoSinPConK nuevoDatos = servidor.infoOfertaLaboralPostulante(postulanteNickname, nombreOferta);
	    Set<UsuarioBean> postulantes = new TreeSet<>();

	    if (nuevoDatos instanceof DtOfertaExtendidoConKeywordsPostulante) {
	        // Si la oferta contiene información de postulantes
	        DtOfertaExtendidoConKeywordsPostulante conPostulantes = (DtOfertaExtendidoConKeywordsPostulante) nuevoDatos;
	        DtPostulacion postulacion = conPostulantes.getDatospostulacion();
	        String nicknamePostulante = postulacion.getNombrePostulante();

	        // Obtener información del postulante y agregarlo al conjunto de postulantes
	        postulantes.add(this.obtenerDatosUsuario(nicknamePostulante));
	    }

	    // Establecer el conjunto de postulantes en la oferta
	    ofertaBean.setPostulantes(postulantes);

	    return ofertaBean;
	}

	@Override
	public Set<OfertaLaboralBean> listarDatosOfertas() throws OfertaLaboralNoEncontrada_Exception {
		List<DtOfertaExtendido> DtOfertas = servidor.obtenerDTOfertasLaboralesConfirmadas().getOfertasExtendido();
		Set<OfertaLaboralBean> ofertasLaborales = new TreeSet<>();
		for (DtOfertaExtendido Dtoferta: DtOfertas) {
			ofertasLaborales.add(this.obtenerDatosOfertaLaboral(Dtoferta.getNombre()));
		}
		return ofertasLaborales;
	}
	
	@Override
	public OfertaLaboralBean DatosOferta(String nombre_oferta) throws OfertaLaboralNoEncontrada_Exception {
		DtOfertaExtendido ofertaLaboral = servidor.obtenerOfertaLaboral(nombre_oferta);
		OfertaLaboralBean olb = new OfertaLaboralBean();
		olb.setNombre(ofertaLaboral.getNombre());
		olb.setDescripcion(ofertaLaboral.getDescripcion());
		olb.setFechaDeAlta(LocalDate.parse(ofertaLaboral.getFechaDeAlta()));
		olb.setCosto(ofertaLaboral.getCosto());
		olb.setRemuneracion(ofertaLaboral.getRemuneracion());
		olb.setHorario(ofertaLaboral.getHorario());
		olb.setDepartamento(ofertaLaboral.getDepartamento());
		olb.setCiudad(ofertaLaboral.getCiudad());
		olb.setEstado(ofertaLaboral.getEstado());
		olb.setNicknameEmpresa(ofertaLaboral.getNicknameEmpresaPublicadora());
		return olb;
	}

	@Override
	public Set<OfertaLaboralBean> buscarOfertasPorKeyword(String keyword) throws OfertaLaboralNoEncontrada_Exception {
		Set<String> ofertas = new TreeSet<>(servidor.listarOfertasLaboralesKeywords(keyword).getListaString());
		
		if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	Set<OfertaLaboralBean> DtOfertas = new TreeSet<>();
    	
    	for( String nombreOferta : ofertas) {
    		OfertaLaboralBean DtOferta = this.obtenerDatosOfertaLaboral(nombreOferta);
    		DtOfertas.add(DtOferta);
    	}
    	
    	
		return DtOfertas;
	}

	@Override
	public Set<OfertaLaboralBean> buscarOfertasPorInput(String consulta) throws OfertaLaboralNoEncontrada_Exception, ExceptionUsuarioNoEncontrado_Exception {
		Set<String> ofertas = new TreeSet<>(servidor.listarOfertasLaboralesConfirmadas(consulta).getListaString());
    	
    	if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	Set<OfertaLaboralBean> DtOfertas = new TreeSet<OfertaLaboralBean>();
    	
    	for( String nombreOferta : ofertas) {
    		OfertaLaboralBean DtOferta = this.obtenerDatosOfertaLaboral(nombreOferta);
    		DtOfertas.add(DtOferta);
    	}
    	
    	
		return DtOfertas;	
	}

	@Override
	public Set<PaqueteBean> obtenerPaquetes() {
        Set<String> lista = new TreeSet<>(servidor.listarPaquetes().getListaString());
        
        Set<PaqueteBean> paquetes = new TreeSet<PaqueteBean>();
        for(String nombrePaquete : lista) {
        	paquetes.add(obtenerDatosPaquete(nombrePaquete));
        }
		return paquetes;
	}

	
	

}
