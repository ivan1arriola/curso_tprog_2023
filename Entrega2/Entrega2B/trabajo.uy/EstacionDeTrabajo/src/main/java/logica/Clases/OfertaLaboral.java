package main.java.logica.Clases;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTPostulacion;
import main.java.logica.Enumerados.DepUY;

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
	private List<Postulacion> postulaciones;
	private Paquete paq;
	
	// asociado a
	private TipoOferta tOferta;
	private List<Keyword> keywords;
	

	// constructor
	public OfertaLaboral(List<Keyword> atrkeywords,TipoOferta atrtOferta,String atrnombre,String atrdescripcion,String atrciudad,DepUY atrdepartamento,DTHorario atrhorario,Float atrremuneracion,LocalDate atrfecha_de_alta) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
	    this.costo = tOferta.getCosto();
		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
	}
	
	public OfertaLaboral(List<Keyword> atrkeywords,TipoOferta atrtOferta,String atrnombre,String atrdescripcion,String atrciudad,DepUY atrdepartamento,DTHorario atrhorario,Float atrremuneracion,LocalDate atrfecha_de_alta, Paquete paq) {
		this.nombre = atrnombre;
		this.descripcion = atrdescripcion;
		this.ciudad = atrciudad;
		this.departamento = atrdepartamento;
		this.horario = atrhorario;
		this.remuneracion = atrremuneracion; 
		this.tOferta = atrtOferta;
	    this.costo = tOferta.getCosto();
		this.fecha_de_alta = atrfecha_de_alta;
		this.keywords = atrkeywords; // la lista de keywords
		this.postulaciones = new ArrayList<>(); // originalmente vacio
		this.paq = paq;
	}
	 
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
	
	
	// -------------- funciones ---------------------
	public DTOfertaExtendido obtenerDatosOferta(){
		Set<DTPostulacion> posts = new HashSet<>();
		for(int i = 0; i < postulaciones.size(); i++) {
			Postulacion elem = postulaciones.get(i);
			posts.add(elem.getDTPostulacion());
		}
		DTOfertaExtendido dtoe = new DTOfertaExtendido(getNombre(),getDescripcion(),getFecha_de_alta(),getCosto(),getRemuneracion(),getHorario(),getDepartamento(), getCiudad(), posts);
		return dtoe;
    }
	
	public void registrarPostulacion(Postulacion p) {
		postulaciones.add(p);
	}
	
}
