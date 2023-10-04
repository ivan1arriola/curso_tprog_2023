package main.java.logica.Clases;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.Datatypes.DTOfertaExtendidoConKeywordsTit;
import main.java.logica.Datatypes.DTPostulacion;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;

import java.time.LocalDate; // import logica.Datatypes.DTFecha;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class OfertaLaboral {
	private String nombre;
	private String descripcion;
	private LocalDate fecha_de_alta;
	private Float costo; // atributo calculado
	private Float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado; //
	private byte[] imagen; //
	
	// asociado a
	private TipoOferta tOferta; 
	private List<Keyword> keywords;
	private Paquete paqueteAsoc; //
	private List<Postulacion> postulaciones;
	

	// constructor
	public OfertaLaboral(List<Keyword> atrkeywords,TipoOferta atrtOferta,String atrnombre,String atrdescripcion, String atrciudad,DepUY atrdepartamento,DTHorario atrhorario,Float atrremuneracion,LocalDate atrfecha_de_alta,EstadoOL estadoNuevo,byte[] imagennueva,Paquete paq) {
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
		if ( this.paqueteAsoc != null ) {
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
	public String getNombre() 					{ return nombre; 		}
	public String getDescripcion() 				{ return descripcion;   }
	public String getCiudad() 					{ return ciudad; 		}
	public DepUY getDepartamento() 				{ return departamento; 	}
	public DTHorario getHorario() 				{ return horario; 		}
	public Float getRemuneracion() 				{ return remuneracion; 	}
	public LocalDate getFecha_de_alta() 		{ return fecha_de_alta; }
	public Float getCosto() 					{ return costo; 		}
	public List<Postulacion> getPostulaciones() { return postulaciones; }
	public TipoOferta getTipoOferta() 			{ return tOferta; 		}
	public List<Keyword> getKeywords()			{ return keywords; 		}
	public EstadoOL getEstado()                 { return estado;        }
	public byte[] getImagen()                   { return imagen;        }
	public Paquete getPaquete()					{ return paqueteAsoc;   }
	
	// Setters
	public void setNombre(String nomb) 						{ nombre = nomb; 			}
	public void setDescripcion(String Desc) 				{ descripcion = Desc; 		}
	public void setCiudad(String Ciud) 						{ ciudad = Ciud;			}
	public void setDepartamento(DepUY Departa) 				{ departamento = Departa; 	}
	public void setHorario(DTHorario Horar) 				{ horario = Horar; 			}
	public void setRemuneracion(Float Remunera) 			{ remuneracion = Remunera;	}
	public void setFecha_de_alta(LocalDate fecha) 			{ fecha_de_alta = fecha; 	}
	public void setCosto(float c)							{ costo = c; 				}
	public void setPostulaciones(List<Postulacion> posts) 	{ postulaciones = posts; 	}
	public void setTipoOferta(TipoOferta to) 				{ tOferta = to; 			}
	public void setKeywords(List<Keyword> ks)				{ keywords = ks; 			}
	public void setEstado(EstadoOL estad)                   {  estado = estad;          }
	public void setImagen(byte[] imagenNueva)               { imagen = imagenNueva;   	}
	public void setPaquete(Paquete paqueteA)                {paqueteAsoc = paqueteA;    }
	
	// -------------- funciones ---------------------
	public DTOfertaExtendido obtenerDatosOferta(){
		Set<DTPostulacion> posts = new HashSet<>();
		for(int i = 0; i < postulaciones.size(); i++) {
			Postulacion elem = postulaciones.get(i);
			posts.add(elem.getDTPostulacion());
		}
		DTOfertaExtendido dtoe = new DTOfertaExtendido(getNombre(),getDescripcion(),getFecha_de_alta(),getCosto(),getRemuneracion(),getHorario(),getDepartamento(), getCiudad(), getEstado() , posts);
		

		
		return dtoe;
    }
	
	public void registrarPostulacion(Postulacion p) {
		postulaciones.add(p);
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
    	HashSet<String> nuevo = new HashSet<>();
    	for (Keyword item : keys) {
    		nuevo.add(item.getNombre());
    	}
    	DTOfertaExtendidoSinPConK dtoe = new DTOfertaExtendidoSinPConK(getNombre(),getDescripcion(),getFecha_de_alta(),getCosto(),getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado() , getImagen(), nuevo);
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
    
//    public Set<DTOfertaExtendidoConKeywordsTit> infoOfertaLaboralPropietario(){
//    	Set<DTOfertaExtendidoConKeywordsTit> mySet = new HashSet<DTOfertaExtendidoConKeywordsTit>();
//    	
//    	List<Postulacion> lista =	getPostulaciones();
//    	HashSet<String> postulaciones = new HashSet<String>();
//    	for (Postulacion item : lista) {
//    		postulaciones.add((item.getDTPostulacion()).getPostulante());
//    	}
//    	
//    	List<Keyword> keys = getKeywords();
//    	HashSet<String> nuevo = new HashSet<>();
//    	for (Keyword item : keys) {
//    		nuevo.add(item.getNombre());
//    	}
//    	
//    	DTOfertaExtendidoSinPConK unDt = new DTOfertaExtendidoConKeywordsTit(getNombre(),getDescripcion(),getFecha_de_alta(),getCosto(),getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado() , getImagen(), nuevo,getPaquete(),postulaciones);
//    		
//    	
//    	
//    	return mySet;
//    }
    
    public DTPostulacion obtenerDatosPostulacion(String nombre_empresa) {
    	List<Postulacion> lista =	getPostulaciones();
    	for (Postulacion item : lista) {
    		item.obtenerNicknamePostulante();
    	}
    	return datatype;
    }
    
}
