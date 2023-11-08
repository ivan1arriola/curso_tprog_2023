package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.servidor.adapter.DTHorarioAdapter;
import logica.servidor.adapter.LocalDateAdapter;
import logica.servidor.adapter.SetDTPostulacionAdapter;

import java.time.LocalDate;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaExtendido {
    private String nombre;
    private String descripcion;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaDeAlta;
    private float costo;
    private float remuneracion;
    @XmlJavaTypeAdapter(DTHorarioAdapter.class)
    private DTHorario horario;
    private DepUY departamento;
    private String ciudad;
    private EstadoOL estado;
    @XmlJavaTypeAdapter(SetDTPostulacionAdapter.class)
    private Set<DTPostulacion> postulaciones;
    private byte[] imagen;
    private String paq;
    private String nicknameEmpresaPublicadora;
    private Integer cantFavs;
    private Integer cantVisitas;
    private String tipoOferta;


    public DTOfertaExtendido(
    	    String empresaPublicadora,
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
    	    String paquete,
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
    	    paq = paquete;
    	    nicknameEmpresaPublicadora = empresaPublicadora;
    	    cantFavs = cantF;
    	    cantVisitas = cantV;
    	    setTipoOferta(tipoOfer);
    	}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

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

    public String getPaquete() {
        return paq;
    }

    @Override
    public String toString() {
        String texto = "Nombre: " + nombre + "\n" + "Descripción: " + descripcion + "\n"
                + "Fecha de alta: " + fechaDeAlta + "\n" + "Costo: "
                + (int) costo + "\n" + "Remuneración: " + (int) remuneracion + "\n"
                + "Horario de Entrada: " + horario.getDesde() + "\n"
                + "Horario de Salida: " + horario.getHasta() + "\n" + ciudad
                + ",  " + departamento + "\n" + "Estado: " + estado;

        return texto;
    }

    public String getNicknameEmpresaPublicadora() {
        return nicknameEmpresaPublicadora;
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
//