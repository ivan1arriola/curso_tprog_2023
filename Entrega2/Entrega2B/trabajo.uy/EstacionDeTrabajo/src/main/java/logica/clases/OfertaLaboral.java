package main.java.logica.clases;

import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
import main.java.logica.datatypes.DTOfertaExtendidoConKeywordsTit;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

import java.time.LocalDate; // import logica.Datatypes.DTFecha;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class OfertaLaboral {
	// atributos
	private String nombre;
	private String descripcion;
	private LocalDate fecha_de_alta;
	private Float costo; // atributo calculado
	private Float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado; 
	private byte[] imagen; 
	
	// relaciones
	private TipoOferta tOferta; 
	private List<Keyword> keywords;
	private Paquete paqueteAsoc; 
	private List<Postulacion> postulaciones;
	

	// constructor con paquete y imagen
	public OfertaLaboral(List<Keyword> atrkeywords, TipoOferta atrtOferta, String atrnombre, String atrdescripcion,  String atrciudad, DepUY atrdepartamento, DTHorario atrhorario, Float atrremuneracion, LocalDate atrfecha_de_alta, EstadoOL estadoNuevo, byte[] imagennueva, Paquete paq) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
		this.estado = estadoNuevo;
		this.imagen = imagennueva;
		this.paqueteAsoc = paq;
		
		float costodadoPaq = tOferta.getCosto();
		if (this.paqueteAsoc != null) {
			float descuento = paqueteAsoc.getDescuento();
			this.costo = costodadoPaq - costodadoPaq*descuento;
		} else {
			this.costo = costodadoPaq;
		}		
		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
	}

	// constructor sin paquete y con imagen
	public OfertaLaboral(List<Keyword> atrkeywords, TipoOferta atrtOferta, String atrnombre, String atrdescripcion,  String atrciudad, DepUY atrdepartamento, DTHorario atrhorario, Float atrremuneracion, LocalDate atrfecha_de_alta, EstadoOL estadoNuevo, byte[] imagennueva) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
		this.estado = estadoNuevo;
		this.imagen = imagennueva;
		this.paqueteAsoc = null;	
		float costodadoPaq = tOferta.getCosto();
		if (this.paqueteAsoc != null) {
			float descuento = paqueteAsoc.getDescuento();
			this.costo = costodadoPaq - costodadoPaq*descuento;
		} else {
			this.costo = costodadoPaq;
		}
		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
	}

	// constructor sin imagen pero con paquete	
	public OfertaLaboral(List<Keyword> atrkeywords, TipoOferta atrtOferta, String atrnombre, String atrdescripcion,  String atrciudad, DepUY atrdepartamento, DTHorario atrhorario, Float atrremuneracion, LocalDate atrfecha_de_alta, EstadoOL estadoNuevo,  Paquete paq) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
		this.estado = estadoNuevo;
		this.paqueteAsoc = paq;
		this.imagen = null;
		
		float costodadoPaq = tOferta.getCosto();
		if (this.paqueteAsoc != null) {
			float descuento = paqueteAsoc.getDescuento();
			this.costo = costodadoPaq - costodadoPaq*descuento;
		} else {
			this.costo = costodadoPaq;
		}
			
		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
	}

	// constructor sin imagen ni paquete	
	public OfertaLaboral(List<Keyword> atrkeywords, TipoOferta atrtOferta, String atrnombre, String atrdescripcion,  String atrciudad, DepUY atrdepartamento, DTHorario atrhorario, Float atrremuneracion, LocalDate atrfecha_de_alta, EstadoOL estadoNuevo) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
		this.estado = estadoNuevo;
		this.paqueteAsoc = null;
		this.imagen = null;
	
		float costodadoPaq = tOferta.getCosto();
		if (this.paqueteAsoc != null) {
			float descuento = paqueteAsoc.getDescuento();
			this.costo = costodadoPaq - costodadoPaq*descuento;
		} else {
			this.costo = costodadoPaq;
		}

		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
	}
	
	// Getters
	public String getNombre() {
		return nombre; 	
	}
	
	public String getDescripcion() { 
		return descripcion;
	}
	
	public String getCiudad() { 
		return ciudad;
	}
	
	public DepUY getDepartamento() { 
		return departamento; 
	}	
	
	public DTHorario getHorario() {
		return horario;
	}
	
	public Float getRemuneracion() {
		return remuneracion;
	}
	
	public LocalDate getFecha_de_alta() {
		return fecha_de_alta; 
	}
	
	public Float getCosto() { 
		return costo; 
	}
	
	public List<Postulacion> getPostulaciones() {
		return postulaciones; 
	}
	
	public TipoOferta getTipoOferta() {
		return tOferta;
	}
	
	public List<Keyword> getKeywords() { 
		return keywords;
	}
	
	public EstadoOL getEstado() { 
		return estado; 
	}
	
	public byte[] getImagen()  {
		return imagen;
	}
	
	public Paquete getPaquete() {
		return paqueteAsoc;   
	}
	
	// Setters
	public void setNombre(String nomb) {
		nombre = nomb;
	}
	
	public void setDescripcion(String Desc) {
		descripcion = Desc;
	}
	
	public void setCiudad(String Ciud) 	{
		ciudad = Ciud;
	}
	
	public void setDepartamento(DepUY Departa) {
		departamento = Departa;
	}
	
	public void setHorario(DTHorario Horar) {
		horario = Horar; 
	}
	
	public void setRemuneracion(Float Remunera) {
		remuneracion = Remunera;
	}
	
	public void setFecha_de_alta(LocalDate fecha) {
		fecha_de_alta = fecha; 
	}
	
	public void setCosto(float cost) {
		costo = cost;
	}
	
	public void setPostulaciones(List<Postulacion> posts) {
		postulaciones = posts;
	}
	
	public void setTipoOferta(TipoOferta tipoOfer) {
		tOferta = tipoOfer;
	}
	
	public void setKeywords(List<Keyword> keys) {
		keywords = keys;
	}
	
	public void setEstado(EstadoOL estad) {
		estado = estad; 
	}
	
	public void setImagen(byte[] imagenNueva) {
		imagen = imagenNueva;
	}
	
	public void setPaquete(Paquete paqueteA) {
		paqueteAsoc = paqueteA;    
	}
	
	// -------------- funciones ---------------------

	public void registrarPostulacion(Postulacion post) {
		postulaciones.add(post);
	} // registra una postulacion a la lista de postulaciones	

	public DTOfertaExtendido obtenerDatosOferta(){
		Set<DTPostulacion> posts = new HashSet<>();
		// muestro todas las postulaciones
		for(int i = 0; i < postulaciones.size(); i++) {
			Postulacion elem = postulaciones.get(i);
			// obtengo DTPostulacion para cada una
			posts.add(elem.obtenerDT());
		}
		Paquete paq = getPaquete();
		String paq_nomb = null;
		if(paq != null) {
			paq_nomb = paq.getNombre();
		}
		DTOfertaExtendido dtoe = new DTOfertaExtendido(getNombre(),  getDescripcion(),  getFecha_de_alta(),  getCosto(),  getRemuneracion(),  getHorario(),  getDepartamento(),  getCiudad(),  getEstado(),  posts,  getImagen(),  paq_nomb);
		return dtoe;
	}
	
	public boolean	tieneKeyword(String keyword) {
		for (Keyword item : keywords) {
			if (item.getNombre().equals(keyword)) {
				return true;
			}   
		}
		return false;
	}
	
	public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante() {
		List<Keyword> keys = getKeywords();
		Set<String> nuevo = new HashSet<>();
		for (Keyword item : keys) {
			nuevo.add(item.getNombre());
		}
		DTOfertaExtendidoSinPConK dtoe = new DTOfertaExtendidoSinPConK(getNombre(),  getDescripcion(),  getFecha_de_alta(),  getCosto(),  getRemuneracion(),  getHorario(),  getDepartamento(),  getCiudad(),  getEstado(),  getImagen(),  nuevo);
		return dtoe;
	}
	

	public boolean existePostulacion(String nombre_postulante) {
		for (Postulacion item : postulaciones) {
			if (nombre_postulante.equals(item.obtenerNicknamePostulante())) {
				return true;
			}
		}
	return false;
	}

	public DTOfertaExtendidoConKeywordsTit infoOfertaLaboralPropietario(){
		List<Keyword> keys = getKeywords();
		Set<String> nuevo = new HashSet<>();
		for (Keyword item : keys) {
			nuevo.add(item.getNombre());
		}
		DTOfertaExtendidoConKeywordsTit dtoe = new DTOfertaExtendidoConKeywordsTit(getNombre(), getDescripcion(), getFecha_de_alta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), nuevo,  getPaquete(),  nuevo);
		return dtoe;
	} 

	public DTPostulacion obtenerDatosPostulacion(String nombre_empresa) {
		List<Postulacion> lista = getPostulaciones();
		for (Postulacion item : lista) {
			item.obtenerNicknamePostulante();
		}
		return null; // FALTA ENCONTRAR EL DCOM
	}

	public DTOfertaExtendidoConKeywordsPostulante infoOfertaLaboralPost(String nombre_postulante) {
		int indicebuscado = 0;
		boolean salir = false;
		for (int i = 0; i < postulaciones.size() && !salir; i++) {
			if(postulaciones.get(i).obtenerNicknamePostulante().equals(nombre_postulante)) {
				indicebuscado = i;
				salir = true;
			}
		}
		DTPostulacion dtPost = postulaciones.get(indicebuscado).obtenerDT();
		Set<String> keys = new HashSet<String>();
		for (int i = 0; i < keywords.size() && !salir; i++) {
			keys.add(keywords.get(i).getNombre());
		}
		DTOfertaExtendidoConKeywordsPostulante entregar = new DTOfertaExtendidoConKeywordsPostulante(getNombre(), getDescripcion(), getFecha_de_alta(), getCosto(),  getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), keys, dtPost);

		
		return 	entregar;	
		} 
}
