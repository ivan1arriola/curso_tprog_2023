package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.servidor.adapter.LocalDateAdapter;
import logica.servidor.adapter.SetDTPostulacionAdapter;
import logica.servidor.adapter.SetStringAdapter;

import java.time.LocalDate;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaExtendidoConKeywords {
    private String nombre;
    private String descripcion;
    private LocalDate fechaDeAlta;
    private float costo;
    private float remuneracion;
    private DTHorario horario;
    private DepUY departamento;
    private String ciudad;
    private EstadoOL estado;
    @XmlJavaTypeAdapter(SetDTPostulacionAdapter.class)
    private Set<DTPostulacion> postulaciones;
    private byte[] imagen;
    @XmlJavaTypeAdapter(SetStringAdapter.class)
    private Set<String> keywords;
    private Integer cantFavs;
    private Integer cantVisitas;
    private String tipoOferta;

    public DTOfertaExtendidoConKeywords(
    	    String nomb,
    	    String desc,
    	    LocalDate fechaA,
    	    float cost,
    	    float remu,
    	    DTHorario horario,
    	    DepUY dep,
    	    String ciu,
    	    EstadoOL est,
    	    Set<DTPostulacion> post,
    	    byte[] img,
    	    Set<String> keys,
    	    Integer cantF,
    	    Integer cantV,
    	    String tipoOfer
    	) {
    	    nombre = nomb;
    	    descripcion = desc;
    	    fechaDeAlta = fechaA;
    	    costo = cost;
    	    remuneracion = remu;
    	    this.horario = horario;
    	    departamento = dep;
    	    ciudad = ciu;
    	    estado = est;
    	    postulaciones = post;
    	    imagen = img;
    	    keywords = keys;
    	    setCantFavs(cantF);
    	    cantVisitas = cantV;
    	    setTipoOferta(tipoOfer);
    	}


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public float getCosto() {
        return costo;
    }

    public float getRemuneracion() {
        return remuneracion;
    }

    public DTHorario getHorario() {
        return horario;
    }

    public DepUY getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public EstadoOL getEstado() {
        return estado;
    }

    public Set<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public Set<String> getKeywords() {
        return keywords;
    }


    @Override
    public String toString() {

        String texto = "Nombre: " + nombre + "\n" + "Descripción: " + descripcion + "\n" + "Fecha de alta: " + fechaDeAlta
                + "\n" + "Costo: " + (int) costo + "\n" + "Remuneración: " + (int) remuneracion + "\n"
                + "Horario de Entrada: " + horario.getDesde() + "\n" + "Horario de Salida: " + horario.getHasta()
                + "\n" + ciudad + ",  " + departamento + "\n"
                + "Estado: " + estado;

        return texto;
    }

	public Integer getCantFavs() {
		return cantFavs;
	}

	public void setCantFavs(Integer cantFavs) {
		this.cantFavs = cantFavs;
	}


	public Integer getCantVisitas() {
		return cantVisitas;
	}


	public void setCantVisitas(Integer cantVisitas) {
		this.cantVisitas = cantVisitas;
	}


	public String getTipoOferta() {
		return tipoOferta;
	}


	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

}
 