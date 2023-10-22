package utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import interfaces.ILogica;

import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import logica.datatypes.DTHorario;
import webservice.DtOfertaExtendido;
import webservice.DtOfertaExtendidoSinPConK;
import webservice.DtPostulacion;
import webservice.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception;
import webservice.ExceptionCompraPaqueteConValorNegativo_Exception;
import webservice.ExceptionValidezNegativa_Exception;
import webservice.ListaBean;
import webservice.OfertaLaboralBeanServidor;
import webservice.ServidorCentral;
import webservice.UsuarioBeanServidor;

public class ConexionServidor implements ILogica {
	
	ServidorCentral servidor;
	
	public ConexionServidor(){
		servidor = FabricaWeb.getInstance().getServidor();
    }

	

	@Override
	public boolean validarCredenciales(String identificador, String contraseña) {
		return servidor.validarCredenciales(identificador, contraseña);
	}
	
	

	@Override
	public UsuarioBean obtenerDatosUsuario(String nickname) {
	    UsuarioBean usuario = null;
	    try {
	        UsuarioBeanServidor dtUsuario = servidor.obtenerDatosUsuario(nickname);
	        usuario = UsuarioBean.fromUsuarioBeanServidor(dtUsuario);
	    } catch (Exception e) {
	        e.printStackTrace();
	        usuario = new UsuarioBean();
	        usuario.setError("Se produjo un error al obtener los datos del usuario");
	    }
	    return usuario;
	}

	


	@Override
	public void modificarDatosUsuario(String nickname, UsuarioBean usuario) {

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
	
	private Set<String> obtenerListaString(ListaBean bean){
		List<String> lista = bean.getListaString();
		Set<String> setString = new HashSet<>(lista);
		return setString;
	}

	@Override
	public Set<String> listarNicknamesUsuario() {
	    return obtenerListaString(servidor.listarNicknamesUsuarios());
	}

	@Override
	public Set<String> listarKeywords() {
		return obtenerListaString(servidor.listarKeywords());
	}

	@Override
	public Set<String> listarPaquetesDeEmpresa(String nickname) {
		return obtenerListaString(servidor.listarPaquetesDeEmpresa(nickname));
	}
	


	@Override
	public Set<String> listarTipoDePublicaciones() {
		return obtenerListaString(servidor.listarTipoDePublicaciones());
	}

	@Override
	public void altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) {
        servidor.altaEmpresaURLyImagen(nick, contraseña, nombre, apellido, mail, desc, URL, null); // no puedo mandar un null;
    }
	

	@Override
	public void altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
        //Fabrica.getInstance().getICtrlUsuario().altaPostulante(nick, contraseña, nombre, apellido, mail, fecha_nac, nacionalidad);
    }

	public void altaOfertaLaboral1(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario,
			float remun, String ciu, Departamento dep, LocalDate fechaA, Set<String> keys, EstadoOfertaLaboral estado, String img,
			String paquete) {
		/*try {
			DepUY departamento = DepUY.values()[Departamento.valueOf(dep.toString()).ordinal()];
			EstadoOL estadoOL = EstadoOL.valueOf(estado.toString());
			servidor.altaOfertaLaboral(nickname_e, tipo, nombre, descripcion, horario, remun, ciu, departamento, fechaA, keys, estadoOL, null, paquete);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
	}

	@Override
	public void compraPaquetes(String nickname, String paquete, LocalDate now, int valor) {
		try {
			servidor.compraPaquetes(nickname, paquete, valor);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception
				| ExceptionCompraPaqueteConValorNegativo_Exception | ExceptionValidezNegativa_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	public PaqueteBean obtenerDatosPaquete(String paquete) {
		PaqueteBean paqueteBean = PaqueteBean.convertFromServidor(servidor.obtenerDatosPaquete(paquete));
		return paqueteBean;
	}

	@Override
	public Set<String> listarOfertasConfirmadasDeEmpresa(String nicknameParametro) {
		return obtenerListaString(servidor.listarOfertasConfirmadasDeEmpresa(nicknameParametro));
	}

	@Override
	public OfertaLaboralBean obtenerDatosOfertaLaboral(String nombreOferta) {
		OfertaLaboralBeanServidor dtOferta = servidor.obtenerDatosOfertaLaboral(nombreOferta);
		OfertaLaboralBean ofertaLaboral = OfertaLaboralBean.fromOfertaLaboralBeanServidor(dtOferta);
        return ofertaLaboral;
	}

	@Override
	public Set<String> listarOfertasLaboralesDeEmpresa(String nicknameParametro) {
		Set<String> todasLasOfertas = obtenerListaString(servidor.listarTodasLasOfertasLaborales(nicknameParametro));
		return todasLasOfertas;
	}

	@Override
	public Set<String> listarPostulacionesDePostulante(String nicknameParametro) {
		return obtenerListaString(servidor.listarPostulacionesPostulante(nicknameParametro));
	}

	@Override
	public PostulacionBean obtenerDatosPostulacion(String nombreOferta, String nicknameParametro) {
		PostulacionBean postulacion = new PostulacionBean();
		DtPostulacion dtPostulacion = servidor.obtenerDatosPostulacion(nicknameParametro, nombreOferta);
		
		postulacion.setCVitae(dtPostulacion.getCVitae());
		//postulacion.setFecha(dtPostulacion.getFecha());
		postulacion.setMotivacion(dtPostulacion.getMotivacion());
		postulacion.setNombreOfertaLaboral(nombreOferta);
		postulacion.setNicknamePostulante(nicknameParametro);
		postulacion.setURLDocExtras(dtPostulacion.getURLDocExtras());
		
		
		return postulacion;
	}

	
	

	@Override
	public OfertaLaboralBean cargarPostulantes(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception {
		return ofertaBean;
	/*	
		// Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }
		
		DTOfertaExtendidoSinPConK info = servidor.obtenerInformacionOfertaLaboralEmpresa(empresaNickname, nombreOferta);
	
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
		return ofertaBean;*/
	}

	
	
	
	@Override
	public OfertaLaboralBean cargarPaquete(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception {
		return ofertaBean;
	  /*  // Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }

	    // Obtener información de la oferta laboral de la empresa
	    DTOfertaExtendidoSinPConK info = servidor.obtenerInformacionOfertaLaboralEmpresa(empresaNickname, nombreOferta);

	    DTPaquete dtPaquete = null;

	    if (info instanceof DTOfertaExtendidoConKeywordsTit) {
	        // La oferta contiene un paquete
	        ofertaBean.setMostrarPaquete(true);
	        DTOfertaExtendidoConKeywordsTit masData = (DTOfertaExtendidoConKeywordsTit) info;
	        dtPaquete = masData.getPaquete();
	    } else {
	        // La oferta no contiene un paquete, se establece mostrarPaquete a falso y se retorna la oferta sin cambios
	        ofertaBean.setMostrarPaquete(false);
	        return ofertaBean;
	    }

	    // Obtener datos del paquete
	    PaqueteBean paquete = this.obtenerDatosPaquete(dtPaquete.getNombre());

	    ofertaBean.setPaquete(paquete);
	    return ofertaBean;*/
	}

	@Override
	public OfertaLaboralBean cargarDatosDePostulante(OfertaLaboralBean ofertaBean, String postulanteNickname)
	        throws Exception {
				return ofertaBean;
	  /*  // Verificar si el nombre de la oferta es nulo
	    String nombreOferta = ofertaBean.getNombre();
	    if (nombreOferta == null) {
	        throw new Exception("ofertaBean no tiene nombre");
	    }

	    // Obtener nuevos datos de la oferta para el postulante
	    DTOfertaExtendidoSinPConK nuevoDatos = servidor.obtenerInformacionOfertaLaboralPostulante(postulanteNickname, nombreOferta);
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

	    return ofertaBean;*/
	}

	@Override
	public Set<OfertaLaboralBean> listarDatosOfertas() {
		return null;
		/*Set<DTOfertaExtendido> dtOfertas = servidor.listarOfertasLaboralesConfirmadas();
		Set<OfertaLaboralBean> ofertasLaborales = new HashSet<OfertaLaboralBean>();
		for (DTOfertaExtendido dtoferta: dtOfertas) {
			ofertasLaborales.add(this.obtenerDatosOfertaLaboral(dtoferta.getNombre()));
		}
		return ofertasLaborales; */
	}

	@Override
	public Set<OfertaLaboralBean> buscarOfertasPorKeyword(String keyword) {
		Set<String> ofertas = (Set<String>) servidor.listarOfertasLaboralesKeywords(keyword);
		
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
		Set<String> ofertas = (Set<String>) servidor.listarOfertasLaboralesConfirmadasConsulta(consulta);
    	
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
        Set<String> lista = Convertidor.obtenerListaString( servidor.listarPaquetes() );
        
        Set<PaqueteBean> paquetes = new HashSet<PaqueteBean>();
        for(String nombrePaquete : lista) {
        	paquetes.add(obtenerDatosPaquete(nombrePaquete));
        }
		return paquetes;
	}


	@Override
	public void altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario,
			float remun, String ciu, Departamento dep, LocalDate fechaA, Set<String> keys, EstadoOfertaLaboral estado,
			String img, String paquete) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	
	

}
