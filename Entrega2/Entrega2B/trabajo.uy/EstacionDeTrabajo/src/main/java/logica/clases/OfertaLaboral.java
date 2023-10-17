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
	private LocalDate fechaAlta;
	private Float costo; // atributo calculado
	private Float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado; 
	private String imagen; 
	
	// relaciones
	private TipoOferta tOferta; 
	private List<Keyword> keywords;
	private Paquete paqueteAsoc; 
	private List<Postulacion> postulaciones;
	private Empresa empresaPublicadora;
	

	public OfertaLaboral(
		    Empresa empresaPublicadora,
		    List<Keyword> atrkeywords,
		    TipoOferta atrtOferta,
		    String atrnombre,
		    String atrdescripcion,
		    String atrciudad,
		    DepUY atrdepartamento,
		    DTHorario atrhorario,
		    Float atrremuneracion,
		    LocalDate atrfechaAlta,
		    EstadoOL estadoNuevo,
		    String imagennueva,
		    Paquete paq
		) {
		
		    this.nombre = atrnombre;
		    this.descripcion = atrdescripcion;
		    this.ciudad = atrciudad;
		    this.departamento = atrdepartamento;
		    this.horario = atrhorario;
		    
		    if (atrremuneracion <=0) { 
		    	throw new IllegalArgumentException("La remuneración debe ser mayor que 0");
		    } else {
		    	this.remuneracion = atrremuneracion; 
		    }
		    
		    this.tOferta = atrtOferta;
		    this.estado = estadoNuevo;
		    this.imagen = imagennueva;
		    
	
		    if (paq.getfechaAlta().plusDays(paq.getValidez()).isBefore(LocalDate.now())) {
		    	throw new IllegalArgumentException("El paquete no se encuentra vigente");
		    } else {
		    this.paqueteAsoc = paq;
		    }
		    
		  
		    this.empresaPublicadora = empresaPublicadora;

		    if (this.paqueteAsoc != null) {
		    	
		    	if (tOferta.getCosto()<=0) {
		    		throw new IllegalArgumentException("El costo del paquete debe ser mayor que 0"); }
		        float costodadoPaq = tOferta.getCosto();
		    	
		    	if (paqueteAsoc.getDescuento()<0) { 
		    		throw new IllegalArgumentException("El descuento debe ser mayor o igual a 0"); }
		    	
		        float descuento = paqueteAsoc.getDescuento();
		    	
		    	
		    	if (paqueteAsoc.getDescuento()==0) { 
		    		this.costo = tOferta.getCosto(); }
		    	else {
		        //this.costo = costodadoPaq - costodadoPaq * (1 / descuento);
		    		this.costo = costodadoPaq * (1 - descuento/100);
		        
		    	}
		        
		    } else {
		        this.costo = tOferta.getCosto();
		    }

		    this.fechaAlta = atrfechaAlta;
		    this.keywords = atrkeywords; // la lista de keywords
		    this.postulaciones = new ArrayList<>(); // originalmente vacío
		}

		// Constructor sin imagen ni paquete
		public OfertaLaboral(
		    Empresa empresaPublicadora,
		    List<Keyword> atrkeywords,
		    TipoOferta atrtOferta,
		    String atrnombre,
		    String atrdescripcion,
		    String atrciudad,
		    DepUY atrdepartamento,
		    DTHorario atrhorario,
		    Float atrremuneracion,
		    LocalDate atrfechaAlta,
		    EstadoOL estadoNuevo
		) {
		    this(
		        empresaPublicadora,
		        atrkeywords,
		        atrtOferta,
		        atrnombre,
		        atrdescripcion,
		        atrciudad,
		        atrdepartamento,
		        atrhorario,
		        atrremuneracion,
		        atrfechaAlta,
		        estadoNuevo,
		        null, // Imagen nula
		        null  // Paquete nulo
		    );
		}

		// Constructor sin imagen pero con paquete
		public OfertaLaboral(
		    Empresa empresaPublicadora,
		    List<Keyword> atrkeywords,
		    TipoOferta atrtOferta,
		    String atrnombre,
		    String atrdescripcion,
		    String atrciudad,
		    DepUY atrdepartamento,
		    DTHorario atrhorario,
		    Float atrremuneracion,
		    LocalDate atrfechaAlta,
		    EstadoOL estadoNuevo,
		    Paquete paq
		) {
		    this(
		        empresaPublicadora,
		        atrkeywords,
		        atrtOferta,
		        atrnombre,
		        atrdescripcion,
		        atrciudad,
		        atrdepartamento,
		        atrhorario,
		        atrremuneracion,
		        atrfechaAlta,
		        estadoNuevo,
		        null,  // Imagen nula
		        paq
		    );
		}

		// Constructor sin paquete y con imagen
		public OfertaLaboral(
		    Empresa empresaPublicadora,
		    List<Keyword> atrkeywords,
		    TipoOferta atrtOferta,
		    String atrnombre,
		    String atrdescripcion,
		    String atrciudad,
		    DepUY atrdepartamento,
		    DTHorario atrhorario,
		    Float atrremuneracion,
		    LocalDate atrfechaAlta,
		    EstadoOL estadoNuevo,
		    String imagennueva
		) {
		    this(
		        empresaPublicadora,
		        atrkeywords,
		        atrtOferta,
		        atrnombre,
		        atrdescripcion,
		        atrciudad,
		        atrdepartamento,
		        atrhorario,
		        atrremuneracion,
		        atrfechaAlta,
		        estadoNuevo,
		        imagennueva,
		        null  // Paquete nulo
		    );
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
	
	public LocalDate getFechaAlta() {
		return fechaAlta; 
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
	
	public String getImagen()  {
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
		if (Ciud.matches("^[a-zA-Z][a-zA-Z\\s+]*$")) {
		ciudad = Ciud;
		} else {
			throw new IllegalArgumentException("Ciudad incorrecta");
		}
	}
	
	public void setDepartamento(DepUY Departa) {
		departamento = Departa;
	}
	
	public void setHorario(DTHorario Horar) {
		horario = Horar; 
	}
	
	public void setRemuneracion(Float Remunera) {
		if (Remunera<=0) {
			throw new IllegalArgumentException("Remuneración debe ser mayor que 0");
		} else {
		remuneracion = Remunera;
		}
	}
	
	public void setFechaAlta(LocalDate fecha) {
		if (this.paqueteAsoc!=null) {
			LocalDate paqAlta = this.paqueteAsoc.getfechaAlta();
			LocalDate fechaLimite = paqAlta.plusDays(this.paqueteAsoc.getValidez());
			if (LocalDate.now().isAfter(fechaLimite)) {
				throw new IllegalArgumentException("En la fecha seleccionada, el paquete no está vigente");
			}
		}
		fechaAlta = fecha; 
	}
	
	public void setCosto(float cost) {
		if (cost<=0) {
			throw new IllegalArgumentException("El costo debe ser mayor que 0");
		}
		costo = cost;
	}
	
	public void setPostulaciones(List<Postulacion> posts) {
		postulaciones = posts;
	}
	
	public void setTipoOferta(TipoOferta tipoOfer) {
		if (this.paqueteAsoc!=null) {
			Set<OfertaPaquete> restantes = this.paqueteAsoc.getOfertaPaquete();
			
			for (OfertaPaquete offer : restantes) {

			    if (offer.getDTCantTO().getNombre().equals(tipoOfer.getNombre())) {
			        int cantidadAsociada = offer.getDTCantTO().getCantidad();
			        if ( cantidadAsociada == 0) { 
			        	throw new IllegalArgumentException("No hay disponibilidad del Tipo de Oferta seleccionado en el Paquete Elegido");
			        	}
			    
			    }
			} //cierra for			
				
		}
		
		tOferta = tipoOfer;
	}
	
	public void setKeywords(List<Keyword> keys) {
		keywords = keys;
	}
	
	public void setEstado(EstadoOL estad) {
		estado = estad; 
	}
	
	public void setImagen(String imagenNueva) {
		imagen = imagenNueva;
	}
	
	public void setPaquete(Paquete paqueteA) {
		
			Set<OfertaPaquete> restantes = paqueteA.getOfertaPaquete();
			
			for (OfertaPaquete offer : restantes) {

			    if (offer.getDTCantTO().getNombre().equals(this.getTipoOferta().getNombre())) {
			        int cantidadAsociada = offer.getDTCantTO().getCantidad();
			        if ( cantidadAsociada == 0) { 
			        	throw new IllegalArgumentException("No hay disponibilidad del Tipo de Oferta seleccionado en el Paquete Elegido");
			        	}
			    
			    }
			} //cierra for			
				
			paqueteAsoc = paqueteA;    
	}
	
	// -------------- funciones ---------------------

	public void registrarPostulacion(Postulacion post) {
		
		int dura = this.getTipoOferta().getDuracion();
		LocalDate altaOferta = this.getTipoOferta().getFechaAlta();
		if (altaOferta.plusDays(dura).isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Oferta no vigente");
		}
	
		postulaciones.add(post);
	} // registra postulacion a la lista de postulaciones	

	public DTOfertaExtendido obtenerDatosOferta(){
		Set<DTPostulacion> posts = new HashSet<>();
		// muestro todas las postulaciones
		for (int i = 0; i < postulaciones.size(); i++) {
			Postulacion elem = postulaciones.get(i);
			// obtengo DTPostulacion para cada una
			posts.add(elem.obtenerDT());
		}
		Paquete paq = getPaquete();
		String paq_nomb = null;
		if (paq != null) {
			paq_nomb = paq.getNombre();
		}
		DTOfertaExtendido dtoe = new DTOfertaExtendido(getEmpresaPublicadora().getNickname(), getNombre(),   getDescripcion(),   getFechaAlta(),   getCosto(),   getRemuneracion(),   getHorario(),   getDepartamento(),   getCiudad(),   getEstado(),   posts,   getImagen(),   paq_nomb);
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
		DTOfertaExtendidoSinPConK dtoe = new DTOfertaExtendidoSinPConK(getEmpresaPublicadora().getNickname(), getNombre(),   getDescripcion(),   getFechaAlta(),   getCosto(),   getRemuneracion(),   getHorario(),   getDepartamento(),   getCiudad(),   getEstado(),   getImagen(),   nuevo);
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
		DTOfertaExtendidoConKeywordsTit dtoe;
		if (getPaquete() != null) {
			dtoe = new DTOfertaExtendidoConKeywordsTit(getEmpresaPublicadora().getNickname(),  getNombre(),  getDescripcion(),  getFechaAlta(),  getCosto(),  getRemuneracion(),  getHorario(),  getDepartamento(),  getCiudad(),  getEstado(),  getImagen(),  nuevo,   getPaquete().getDTPaquete(),   nuevo);
		} else {
			dtoe = new DTOfertaExtendidoConKeywordsTit(getEmpresaPublicadora().getNickname(), getNombre(),  getDescripcion(),  getFechaAlta(),  getCosto(),  getRemuneracion(),  getHorario(),  getDepartamento(),  getCiudad(),  getEstado(),  getImagen(),  nuevo,   null,   nuevo);
		}
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
			if (postulaciones.get(i).obtenerNicknamePostulante().equals(nombre_postulante)) {
				indicebuscado = i;
				salir = true;
			}
		}
		DTPostulacion dtPost = postulaciones.get(indicebuscado).obtenerDT();
		Set<String> keys = new HashSet<String>();
		for (int i = 0; i < keywords.size() && !salir; i++) {
			keys.add(keywords.get(i).getNombre());
		}
		DTOfertaExtendidoConKeywordsPostulante entregar = new DTOfertaExtendidoConKeywordsPostulante(getEmpresaPublicadora().getNickname(), getNombre(),  getDescripcion(),  getFechaAlta(),  getCosto(),   getRemuneracion(),  getHorario(),  getDepartamento(),  getCiudad(),  getEstado(),  getImagen(),  keys,  dtPost);

		
		return 	entregar;	
	}

	public Empresa getEmpresaPublicadora() {
		return empresaPublicadora;
	}

	public void setEmpresaPublicadora(Empresa empresaPublicadora) {
		this.empresaPublicadora = empresaPublicadora;
	} 
	
	public boolean estaVencida() {
		return tOferta.estaVencida();
	}
}
