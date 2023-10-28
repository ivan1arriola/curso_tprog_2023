package utils;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

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
		String ubicacion = System.getProperty("user.home");
		
		ServidorService service = new ServidorService();
		servidor = service.getServidorPort();

        // Crea las carpetas necesarias si no existen
        crearDirectorio(ubicacion + "/resources/Usuario");
        crearDirectorio(ubicacion + "/resources/OfertaLaboral");
        crearDirectorio(ubicacion + "/resources/Paquete");
    }

    private void crearDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + rutaDirectorio);
            } else {
                System.err.println("No se pudo crear el directorio: " + rutaDirectorio);
            }
        } 
    }

	private Set<DTTipoOferta> obtenerTipoOfertas(){
		ICtrlOferta ctrl = Fabrica.getInstance().getICtrlOferta();
		Set<String> lista =  (HashSet<String>) ctrl.listarTipoDePublicaciones();
		Set<DTTipoOferta> tipoOfertas = new HashSet<DTTipoOferta>();
		for (String nombreTipoOferta : lista) {
			try {
				tipoOfertas.add(ctrl.obtenerDatosTO(nombreTipoOferta));
			} catch (ExcepcionTipoOfertaNoExistente e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return tipoOfertas;

	}

	@Override
	public void cargarDatos() {
		try {
			servidor.cargarDatos();
		} catch (ExcepcionKeywordVacia_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionValidezNegativa_Exception e) {
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
	public boolean validarCredenciales(String identificador, String contraseña) {
		return servidor.validarCredenciales(identificador, contraseña);
	}

	@Override
	public UsuarioBean obtenerDatosUsuario(String nickname) {
	    UsuarioBean usuario = new UsuarioBean();
	    try {
	        DtUsuario dtUsuario = servidor.obtenerDatosUsuario(nickname);
	        usuario.setNickname(dtUsuario.getNickname());
	        usuario.setNombre(dtUsuario.getNombre());
	        usuario.setApellido(dtUsuario.getApellido());
	        usuario.setContrasenia(dtUsuario.getContrasenia());
	        usuario.setCorreoElectronico(dtUsuario.getCorreoElectronico());
			usuario.setImagen(imagenAString(dtUsuario.getImagen()));
			//Set<DtUsuarioSinInfoSocial> S1 = dtUsuario.getSeguidores();
			//Set<DTUsuarioSinInfoSocial> S2 = dtUsuario.getSeguidores();
			Set<UsuarioSinInfoSocialBean> seguidores = new HashSet<>();
			Set<UsuarioSinInfoSocialBean> seguidos = new HashSet<>();
			
			/*for (DTUsuarioSinInfoSocial elem : S1) {
				UsuarioSinInfoSocialBean u1 = new UsuarioSinInfoSocialBean();
				u1.setNickname(elem.getNickname());
				u1.setApellido(elem.getApellido());
				u1.setContrasenia(elem.getcontrasenia());
				u1.setCorreoElectronico(elem.getcorreoElectronico());
				seguidores.add(u1);
			}
			
			for (DTUsuarioSinInfoSocial elem : S2) {
				UsuarioSinInfoSocialBean u1 = new UsuarioSinInfoSocialBean();
				u1.setNickname(elem.getNickname());
				u1.setApellido(elem.getApellido());
				u1.setContrasenia(elem.getcontrasenia());
				u1.setCorreoElectronico(elem.getcorreoElectronico());
				seguidos.add(u1);
			}*/
			
			usuario.setSeguidores(seguidores);
			usuario.setSeguidos(seguidos);
			
	        if(dtUsuario instanceof DtEmpresa empresa) {
				usuario.setDescripcion(empresa.getDescripcion());
	        	usuario.setUrl(empresa.getUrl());
	        	usuario.setTipo(TipoUsuario.Empresa);
	        } else if(dtUsuario instanceof DtPostulante postulante){
	        	usuario.setFechaNac(LocalDate.parse(postulante.getFechaNac()));
	        	usuario.setNacionalidad(postulante.getNacionalidad());
	        	usuario.setTipo(TipoUsuario.Postulante);
	        } else {
				throw new IllegalArgumentException("El usuario no es ni empresa ni postulante");
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        usuario.setError("Se produjo un error al obtener los datos del usuario");
	    }
	    return usuario;
	}

	


	@Override
	public void modificarDatosUsuario(String nickname, UsuarioBean usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<UsuarioBean> listarUsuarios() {
    	 Set<String> nicknames = listarNicknamesUsuario();
         Set<UsuarioBean> usuarios = new HashSet<UsuarioBean>();
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
	public Set<String> listarKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> listarPaquetesDeEmpresa(String nickname) {
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
	public void altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL, byte[] imagenBytes) {
	  /*  if (URL == null) {
            URL = ""; // Reemplazar URL nula con una cadena vacía
        }
	    try {
	        if (imagenBytes == null) {
	            servidor.altaEmpresaURL(nick, contraseña, nombre, apellido, mail, desc, URL);
	        } else {
	            servidor.altaEmpresaURLyImagen(nick, contraseña, nombre, apellido, mail, desc, URL, imagenBytes);
	        }
	    } catch (Exception e) {
	       
	    }*/
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
		/*DtPaquete DtPaquete = servidor.obtenerDatosPaquete(paquete);
		PaqueteBean paqueteBean = new PaqueteBean();
		paqueteBean.setCosto(DtPaquete.getCosto());
		paqueteBean.setDescripcion(DtPaquete.getDescripcion());
		paqueteBean.setDescuento(DtPaquete.getDescuento());
		paqueteBean.setFechaAlta(DtPaquete.getFechaAlta());
		
		paqueteBean.setImagen(imagenAString(DtPaquete.getImagen()));
		paqueteBean.setNombre(DtPaquete.getNombre());
		paqueteBean.setValidez(DtPaquete.getValidez());
		
		Set<CantTipoPublicacionBean> cantidades = new HashSet<CantTipoPublicacionBean>();
		
		Set<DTCantTO> dtCantidades = DtPaquete.getTiposDePub();
		
		for( DTCantTO cant : dtCantidades) {
			CantTipoPublicacionBean cantidadBean = new CantTipoPublicacionBean();
			
			cantidadBean.setCantidad(cant.getCantidad());
			cantidadBean.setNombre(cant.getNombre());
			
			cantidades.add(cantidadBean);
		}
		
		paqueteBean.setTiposDePub(cantidades);

		return paqueteBean;*/
		return new PaqueteBean();
	}

	@Override
	public Set<String> listarOfertasConfirmadasDeEmpresa(String nicknameParametro) {
		return servidor.listarOfertasLaboralesConfirmadas(nicknameParametro);
	}

	@Override
	public OfertaLaboralBean obtenerDatosOfertaLaboral(String nombreOferta) {
		OfertaLaboralBean ofertaLaboral = new OfertaLaboralBean();
		DtOfertaExtendido dtOferta = servidor.obtenerOfertaLaboral(nombreOferta);
		
		ofertaLaboral.setNombre(dtOferta.getNombre());
        ofertaLaboral.setDescripcion(dtOferta.getDescripcion());
        ofertaLaboral.setCiudad(dtOferta.getCiudad());
        ofertaLaboral.setCosto(dtOferta.getCosto());
        ofertaLaboral.setDepartamento(dtOferta.getDepartamento());
        ofertaLaboral.setFechaDeAlta(dtOferta.getFechaDeAlta());
        ofertaLaboral.setHorario(dtOferta.getHorario());
        ofertaLaboral.setImagen(imagenAString(dtOferta.getImagen()));
        ofertaLaboral.setRemuneracion(dtOferta.getRemuneracion());
        ofertaLaboral.setEstado(dtOferta.getEstado());
        ofertaLaboral.setNicknameEmpresa(dtOferta.getNicknameEmpresaPublicadora());
        
        DTOfertaExtendidoSinPConK nuevoDatos = servidor.infoOfertaLaboralVisitante(nombreOferta);
        ofertaLaboral.setKeywords(nuevoDatos.getKeywords());

        return ofertaLaboral;
	}

	@Override
	public Set<String> listarOfertasLaboralesDeEmpresa(String nicknameParametro) {
		Set<String> todasLasOfertas = servidor.listarTodasLasOfertasLaborales(nicknameParametro);
		return todasLasOfertas;
	}

	@Override
	public Set<String> listarPostulacionesDePostulante(String nicknameParametro) {
		List<String> listaPostulaciones = servidor.listarPostulacionesPostulante(nicknameParametro).getListaString();
		// Crear un TreeSet a partir de la lista para obtener un Set ordenado.
		return new TreeSet<>(listaPostulaciones);
	}


	@Override
	public PostulacionBean obtenerDatosPostulacion(String nombreOferta, String nicknameParametro) {
		PostulacionBean postulacion = new PostulacionBean();
		DTPostulacion dtPostulacion = servidor.obtenerDatosPostulacionW(nicknameParametro, nombreOferta);
		
		postulacion.setCVitae(dtPostulacion.getcVitae());
		postulacion.setFecha(dtPostulacion.getFecha());
		postulacion.setMotivacion(dtPostulacion.getMotivacion());
		postulacion.setNombreOfertaLaboral(nombreOferta);
		postulacion.setNicknamePostulante(nicknameParametro);
		postulacion.setURLDocExtras(dtPostulacion.getuRLDocExtras());
		
		
		return postulacion;
	}

	
	

	@Override
	public OfertaLaboralBean cargarPostulantes(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception {
		
		// Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }
		
		DTOfertaExtendidoSinPConK info = servidor.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
	
		Set<String> nicknamesDePostulantes = null;
		
		if ( info instanceof DTOfertaExtendidoConKeywordsTit) {
			ofertaBean.setMostrarPostulantes(true);
			DTOfertaExtendidoConKeywordsTit masData = (DTOfertaExtendidoConKeywordsTit) info;
			nicknamesDePostulantes = masData.getpostulaciones();
		} else {
			ofertaBean.setMostrarPostulantes(false);
			return ofertaBean;
		}
		
		
		
		Set<UsuarioBean> postulantes = new HashSet<UsuarioBean>();
		
		for(String nickname : nicknamesDePostulantes ) {
			postulantes.add(this.obtenerDatosUsuario(nickname));	
		}
		
		ofertaBean.setPostulantes(postulantes);
		return ofertaBean;
	}

	
	
	
	@Override
	public OfertaLaboralBean cargarPaquete(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception {
	    // Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }

	    // Obtener información de la oferta laboral de la empresa
	    DTOfertaExtendidoSinPConK info = servidor.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);

	    DtPaquete DtPaquete = null;

	    if (info instanceof DTOfertaExtendidoConKeywordsTit) {
	        // La oferta contiene un paquete
	        ofertaBean.setMostrarPaquete(true);
	        DTOfertaExtendidoConKeywordsTit masData = (DTOfertaExtendidoConKeywordsTit) info;
	        DtPaquete = masData.getPaquete();
	    } else {
	        // La oferta no contiene un paquete, se establece mostrarPaquete a falso y se retorna la oferta sin cambios
	        ofertaBean.setMostrarPaquete(false);
	        return ofertaBean;
	    }

	    // Obtener datos del paquete
	    PaqueteBean paquete = this.obtenerDatosPaquete(DtPaquete.getNombre());

	    ofertaBean.setPaquete(paquete);
	    return ofertaBean;
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
	    DTOfertaExtendidoSinPConK nuevoDatos = servidor.infoOfertaLaboralPostulante(postulanteNickname, nombreOferta);
	    Set<UsuarioBean> postulantes = new HashSet<UsuarioBean>();

	    if (nuevoDatos instanceof DTOfertaExtendidoConKeywordsPostulante) {
	        // Si la oferta contiene información de postulantes
	        DTOfertaExtendidoConKeywordsPostulante conPostulantes = (DTOfertaExtendidoConKeywordsPostulante) nuevoDatos;
	        DTPostulacion postulacion = conPostulantes.getDatosPostulacion();
	        String nicknamePostulante = postulacion.getPostulante();

	        // Obtener información del postulante y agregarlo al conjunto de postulantes
	        postulantes.add(this.obtenerDatosUsuario(nicknamePostulante));
	    }

	    // Establecer el conjunto de postulantes en la oferta
	    ofertaBean.setPostulantes(postulantes);

	    return ofertaBean;
	}

	@Override
	public Set<OfertaLaboralBean> listarDatosOfertas() {
		Set<DTOfertaExtendido> dtOfertas = servidor.listarOfertasLaboralesConfirmadas();
		Set<OfertaLaboralBean> ofertasLaborales = new HashSet<OfertaLaboralBean>();
		for (DTOfertaExtendido dtoferta: dtOfertas) {
			ofertasLaborales.add(this.obtenerDatosOfertaLaboral(dtoferta.getNombre()));
		}
		return ofertasLaborales;
	}
	
	@Override
	public OfertaLaboralBean DatosOferta(String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		DTOfertaExtendido ofertaLaboral = ol.obtenerDatosOferta();
		OfertaLaboralBean olb = new OfertaLaboralBean();
		olb.setNombre(ofertaLaboral.getNombre());
		olb.setDescripcion(ofertaLaboral.getDescripcion());
		olb.setFechaDeAlta(ofertaLaboral.getFechaDeAlta());
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
	public Set<OfertaLaboralBean> buscarOfertasPorKeyword(String keyword) {
		Set<String> ofertas = (HashSet<String>) servidor.listarOfertasLaboralesKeywords(keyword);
		
		if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	Set<OfertaLaboralBean> dtOfertas = new HashSet<OfertaLaboralBean>();
    	
    	for( String nombreOferta : ofertas) {
    		OfertaLaboralBean dtOferta = this.obtenerDatosOfertaLaboral(nombreOferta);
    		dtOfertas.add(dtOferta);
    	}
    	
    	
		return dtOfertas;
	}

	@Override
	public Set<OfertaLaboralBean> buscarOfertasPorInput(String consulta) {
		Set<String> ofertas = (HashSet<String>) servidor.listarOfertasLaboralesConfirmadas(consulta);
    	
    	if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	Set<OfertaLaboralBean> dtOfertas = new HashSet<OfertaLaboralBean>();
    	
    	for( String nombreOferta : ofertas) {
    		OfertaLaboralBean dtOferta = this.obtenerDatosOfertaLaboral(nombreOferta);
    		dtOfertas.add(dtOferta);
    	}
    	
    	
		return dtOfertas;	
	}

	@Override
	public Set<PaqueteBean> obtenerPaquetes() {
        Set<String> lista = servidor.listarPaquetes();
        
        Set<PaqueteBean> paquetes = new HashSet<PaqueteBean>();
        for(String nombrePaquete : lista) {
        	paquetes.add(obtenerDatosPaquete(nombrePaquete));
        }
		return paquetes;
	}

	
	

}
