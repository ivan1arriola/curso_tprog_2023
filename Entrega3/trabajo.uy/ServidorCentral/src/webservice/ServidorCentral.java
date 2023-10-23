package webservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionValidezNegativa;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import javabeans.CantTipoPublicacionBeanServidor;
import javabeans.DateBean;
import javabeans.ListaBean;
import javabeans.OfertaLaboralBeanServidor;
import javabeans.PaqueteBeanServidor;
import javabeans.TipoPublicacionBeanServidor;
import javabeans.UsuarioBeanServidor;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTOfertaExtendidoSinPConK;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulacion;
import logica.datatypes.DTPostulante;
import logica.datatypes.DTTipoOferta;
import logica.datatypes.DTUsuario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.enumerados.TipoUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.utils.Fabrica;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ServidorCentral {

    private Endpoint endpoint = null;
	private ICtrlOferta ctrlOferta;
	private ICtrlUsuario ctrlUsuario;
	
	public DTHora dtHora;

	private static final String SERVICIO_WEB_URL = "http://localhost:9128/webservices";

	
    public ServidorCentral(){
    	
    	Fabrica fabrica = Fabrica.getInstance();
		ctrlOferta = fabrica.getICtrlOferta();
		ctrlUsuario = fabrica.getICtrlUsuario();
    	
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish(SERVICIO_WEB_URL, this);
        System.out.println("Servicio web publicado y funcionando en " + SERVICIO_WEB_URL);
    }


    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    private ListaBean envolverLista(ArrayList<String> strings) {
        ListaBean listaBean = new ListaBean();
        listaBean.setListaString(strings);
        return listaBean;
    }
    
    private ListaBean envolverLista(Set<String> strings) {
        return envolverLista(new ArrayList<>(strings));
    }

    
    @WebMethod
    public ListaBean listarPaquetesDeEmpresa(
        @WebParam(name = "nickname") String nickname
    ) {
    	
        return envolverLista( new ArrayList<>(ctrlOferta.listarComprasPaquete(nickname)));
    }

    @WebMethod
    public ListaBean listarTipoDePublicaciones() {
        return envolverLista( new ArrayList<>(ctrlOferta.listarTipoDePublicaciones()));
    }

    @WebMethod
    public ListaBean listarNicknamesUsuarios() {
    	ArrayList<String> result = new ArrayList<>(ctrlUsuario.listarNicknamesUsuarios());
        return envolverLista(result);
    }


    @WebMethod
    public ListaBean listarPostulacionesPostulante(
        @WebParam(name = "nickname") String nickname
    ) {
    	ArrayList<String> result = new ArrayList<>(ctrlUsuario.listarPostulacionesPostulante(nickname));
        return envolverLista( result);
    }


    
    @WebMethod
    public DTPostulacion obtenerDatosPostulacion(
        @WebParam(name = "nicknameParametro") String nicknameParametro,
        @WebParam(name = "nombreOferta") String nombreOferta
    ) {
        return ctrlUsuario.obtenerDatosPostulacionW(nicknameParametro, nombreOferta);
    }
    

    @WebMethod
    public boolean validarCredenciales(
        @WebParam(name = "identificador") String identificador,
        @WebParam(name = "contrasenia") String contrasenia
    ) {
        System.out.println("Se llamó a la función validarCredenciales"); 
        return ctrlUsuario.validarCredenciales(identificador, contrasenia);
    }
    
    private String imagenAString(byte[] bytes) {
        if (bytes != null) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }
    
    @WebMethod
    public UsuarioBeanServidor obtenerDatosUsuario(
        @WebParam(name = "nickname") String nickname
    ) {
    	UsuarioBeanServidor usuario = new UsuarioBeanServidor();
	    try {
	        DTUsuario dtUsuario = ctrlUsuario.obtenerDatosUsuario(nickname);
	        usuario.setNickname(dtUsuario.getNickname());
	        usuario.setNombre(dtUsuario.getNombre());
	        usuario.setApellido(dtUsuario.getApellido());
	        usuario.setContrasenia(dtUsuario.getcontrasenia());
	        usuario.setCorreoElectronico(dtUsuario.getcorreoElectronico());
			usuario.setImagen(imagenAString(dtUsuario.getImagen()));
			
	        if(dtUsuario instanceof DTEmpresa) {
	        	DTEmpresa empresa = (DTEmpresa) dtUsuario;
	        	usuario.setDescripcion(empresa.getDescripcion());
	        	usuario.setUrl(empresa.getUrl());
	        	usuario.setTipo(TipoUsuario.Empresa);
	        } else {
	        	DTPostulante postulante = (DTPostulante) dtUsuario;	        	
	        	usuario.setFechaNac(fromLocalDate(postulante.getFechaNac()));
	        	usuario.setNacionalidad(postulante.getNacionalidad());
	        	usuario.setTipo(TipoUsuario.Postulante);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        usuario.setError("Se produjo un error al obtener los datos del usuario");
	    }
	    return usuario;
    }
    
    @WebMethod
    public boolean altaEmpresaURLyImagen(
        @WebParam(name = "nick") String nick,
        @WebParam(name = "contrasenia") String contrasenia,
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "apellido") String apellido,
        @WebParam(name = "mail") String mail,
        @WebParam(name = "desc") String desc,
        @WebParam(name = "URL") String URL,
        @WebParam(name = "imagen") byte[] imagen
    ) {
        // Llame a la lógica para realizar la operación de alta de empresa con URL e imagen.
		ctrlUsuario.altaEmpresaURLyImagen(nick, contrasenia, nombre, apellido, mail, desc, URL, imagen);
		return true; // Alta exitosa
    }
    
    @WebMethod
    public void altaOfertaLaboral(
        @WebParam(name = "nickname_e") String nickname_e,
        @WebParam(name = "tipo") String tipo,
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "descripcion") String descripcion,
        @WebParam(name = "horario") DTHorario horario,
        @WebParam(name = "remun") float remun,
        @WebParam(name = "ciu") String ciu,
        @WebParam(name = "departamento") DepUY departamento,
        @WebParam(name = "fechaA") LocalDate fechaA,
        @WebParam(name = "keys") HashSet<String> keys,
        @WebParam(name = "estadoOL") EstadoOL estadoOL,
        @WebParam(name = "img") byte[] img,
        @WebParam(name = "paquete") String paquete
    ) throws ExceptionRemuneracionOfertaLaboralNegativa {
        ctrlOferta.altaOfertaLaboral(nickname_e, tipo, nombre, descripcion, horario, remun, ciu, departamento, fechaA, keys, estadoOL, img, paquete);
    }

    
    @WebMethod
    public void compraPaquetes(
        @WebParam(name = "nickname") String nickname,
        @WebParam(name = "paquete") String paquete,
        @WebParam(name = "valor") int valor
    ) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa {

            ctrlOferta.compraPaquetes(nickname, paquete, LocalDate.now(), valor);
      
    }

    
    @WebMethod
    public TipoPublicacionBeanServidor obtenerDatosTipoPublicacion(
        @WebParam(name = "tipo") String tipo
    ) throws ExcepcionTipoOfertaNoExistente {
		
		DTTipoOferta dtOferta = ctrlOferta.obtenerDatosTO(tipo);
		TipoPublicacionBeanServidor bean = new TipoPublicacionBeanServidor();
		
		bean.setNombre(dtOferta.getNombre());
        bean.setFechaAlta(fromLocalDate(dtOferta.getFechaAlta()));
        bean.setCosto(dtOferta.getCosto());
        bean.setDuracion(dtOferta.getDuracion());
        bean.setExposicion(dtOferta.getExposicion());
        bean.setDescripcion(dtOferta.getDescripcion());
    	
		return bean;
    }

    @WebMethod
    public PaqueteBeanServidor obtenerDatosPaquete(
        @WebParam(name = "paquete") String paquete
    ) {
    	try {
    		
    	
	    	DTPaquete dtPaquete = ctrlUsuario.obtenerDatosPaquete(paquete);
			PaqueteBeanServidor paqueteBean = new PaqueteBeanServidor();
			paqueteBean.setCosto(dtPaquete.getCosto());
			paqueteBean.setDescripcion(dtPaquete.getDescripcion());
			paqueteBean.setDescuento(dtPaquete.getDescuento());
			paqueteBean.setFechaA(fromLocalDate( dtPaquete.getFechaAlta()));
			
			paqueteBean.setImagen(imagenAString(dtPaquete.getImagen()));
			paqueteBean.setNombre(dtPaquete.getNombre());
			paqueteBean.setValidez(dtPaquete.getValidez());
			
			ArrayList<CantTipoPublicacionBeanServidor> cantidades = new ArrayList<CantTipoPublicacionBeanServidor>();
			
			ArrayList<DTCantTO> dtCantidades = new ArrayList<>(dtPaquete.getTiposDePub());
			
			for( DTCantTO cant : dtCantidades) {
				CantTipoPublicacionBeanServidor cantidadBean = new CantTipoPublicacionBeanServidor();
				cantidadBean.setCantidad(cant.getCantidad());
				cantidadBean.setNombre(cant.getNombre());
				cantidades.add(cantidadBean);
			}
			
			paqueteBean.setTiposDePub(cantidades);
	
			return paqueteBean;
    	} catch (Exception e) {
    		return new PaqueteBeanServidor();
    	}
    }

    @WebMethod
    public ListaBean listarOfertasConfirmadasDeEmpresa(
        @WebParam(name = "nickname") String nicknameParametro
    ) {
        return envolverLista( new ArrayList<>(ctrlOferta.listarOfertasLaboralesConfirmadas(nicknameParametro)));
    }

    
    @WebMethod
    public DTOfertaExtendido obtenerOfertaLaboralExtendida(
        @WebParam(name = "nombreOferta") String nombreOferta
    ) {
        return ctrlOferta.obtenerOfertaLaboral(nombreOferta);
    }
    
    @WebMethod
    public OfertaLaboralBeanServidor obtenerDatosOfertaLaboral(String nombreOferta) {
        OfertaLaboralBeanServidor ofertaLaboral = new OfertaLaboralBeanServidor();
        DTOfertaExtendido dtOferta = ctrlOferta.obtenerOfertaLaboral(nombreOferta);

        ofertaLaboral.setNombre(dtOferta.getNombre());
        ofertaLaboral.setDescripcion(dtOferta.getDescripcion());
        ofertaLaboral.setCiudad(dtOferta.getCiudad());
        ofertaLaboral.setCosto(dtOferta.getCosto());
        ofertaLaboral.setDepartamento(dtOferta.getDepartamento());
        ofertaLaboral.setFechaDeAlta(fromLocalDate(dtOferta.getFechaDeAlta()));
        ofertaLaboral.setHorario(dtOferta.getHorario());
        ofertaLaboral.setImagen(imagenAString(dtOferta.getImagen()));
        ofertaLaboral.setRemuneracion(dtOferta.getRemuneracion());
        ofertaLaboral.setEstado(EstadoOL.valueOf(dtOferta.getEstado().name()));
        ofertaLaboral.setNicknameEmpresa(dtOferta.getNicknameEmpresaPublicadora());

        DTOfertaExtendidoSinPConK nuevoDatos = ctrlOferta.infoOfertaLaboralVisitante(nombreOferta);
        ofertaLaboral.setKeywords(new ArrayList<>(nuevoDatos.getKeywords()));

        return ofertaLaboral;
    }


    @WebMethod
    public DTOfertaExtendidoSinPConK obtenerInformacionOfertaLaboralVisitante(
        @WebParam(name = "nombreOferta") String nombreOferta
    ) {
        return ctrlOferta.infoOfertaLaboralVisitante(nombreOferta);
    }


    @WebMethod
    public ListaBean listarTodasLasOfertasLaborales(
        @WebParam(name = "nickname") String nicknameParametro
    ) {
        return envolverLista( new ArrayList<>(ctrlOferta.listarTodasLasOfertasLaborales(nicknameParametro)));
    }



    @WebMethod
    public DTOfertaExtendidoSinPConK obtenerInformacionOfertaLaboralEmpresa(
        @WebParam(name = "empresaNickname") String empresaNickname,
        @WebParam(name = "nombreOferta") String nombreOferta
    ) {
        return ctrlOferta.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
    }
    
    @WebMethod
    public DTOfertaExtendidoSinPConK obtenerInformacionOfertaLaboralPostulante(
        @WebParam(name = "postulanteNickname") String postulanteNickname,
        @WebParam(name = "nombreOferta") String nombreOferta
    ) {
        return ctrlOferta.infoOfertaLaboralPostulante(postulanteNickname, nombreOferta);
    }
    
    @WebMethod
    public ListaBean listarTodasLasOfertasLaboralesConfirmadas() {
    	ListaBean listaBean = new ListaBean();
    	listaBean.setOfertasExtendido(new ArrayList<>(ctrlOferta.listarOfertasLaboralesConfirmadas()));
        return listaBean;
    }

    @WebMethod
    public ListaBean listarOfertasLaboralesKeywords(
        @WebParam(name = "keyword") String keyword
    ) {
        ArrayList<String> result = new ArrayList<>(ctrlOferta.listarOfertasLaboralesKeywords(keyword));
        return envolverLista( result );
    }

    @WebMethod
    public ListaBean listarOfertasLaboralesConfirmadasConsulta(
        @WebParam(name = "consulta") String consulta
    ) {
        ArrayList<String> result = new ArrayList<>(ctrlOferta.listarOfertasLaboralesConfirmadas(consulta));
        return envolverLista( result );
    }

    @WebMethod
    public ListaBean listarPaquetes() {
        ArrayList<String> result = new ArrayList<>(ctrlOferta.listarPaquetes());
        return envolverLista( result);
    }

    private DateBean fromLocalDate(LocalDate localDate) {
        DateBean dateBean = new DateBean();
        dateBean.setDia(localDate.getDayOfMonth());
        dateBean.setMes(localDate.getMonthValue());
        dateBean.setAnio(localDate.getYear());
        return dateBean;
    }
    
    @WebMethod
    public ListaBean listarKeywords() {
    	return envolverLista(ctrlOferta.listarKeywords());
    }


  
}