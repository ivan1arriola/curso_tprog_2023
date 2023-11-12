package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.servidor.adapter.DTHorarioAdapter;
import logica.servidor.adapter.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaLaboral {
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
    private byte[] imagen;
    private EstadoOL estado;
    private Integer cantFavs;
    private String tipoOferta;


    public DTOfertaLaboral(String nomb, String desc, LocalDate fechaA, float cost, float remu, DTHorario horario, DepUY dep, String ciu, EstadoOL estadoOL, byte[] imagenBytes, Integer cantF, String tipoOfer) {
        nombre = nomb;
        descripcion = desc;
        fechaDeAlta = fechaA;
        costo = cost;
        remuneracion = remu;
        this.horario = horario;
        departamento = dep;
        ciudad = ciu;
        estado = estadoOL;
        imagen = imagenBytes;
        setCantFavs(cantF);
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

    public byte[] getImagen() {
        return imagen;
    }

    public EstadoOL getestado() {
        return estado;
    }


    @Override
    public String toString() {

        return nombre + " - " + descripcion + "\n" + fechaDeAlta + "\n" + costo + " - " + remuneracion
                + " - " + horario + "\n" + departamento + ", " + ciudad;

    }


	public Integer getCantFavs() {
		return cantFavs;
	}


	public void setCantFavs(Integer cantFavs) {
		this.cantFavs = cantFavs;
	}


	public String getTipoOferta() {
		return tipoOferta;
	}


	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
}
