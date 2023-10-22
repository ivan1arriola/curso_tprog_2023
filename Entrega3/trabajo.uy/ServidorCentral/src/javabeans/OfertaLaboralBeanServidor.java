package javabeans;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class OfertaLaboralBeanServidor {
	private String nombre;
	private String descripcion;
	private DateBean fechaDeAlta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado;
	private ArrayList<UsuarioBeanServidor> postulantes;
	private String imagen; 
	private String paq;
	private PaqueteBeanServidor paquete;
	private ArrayList<String> keywords;
	private String nicknameEmpresa;
	
	
	private boolean mostrarPostulantes;
	private boolean mostrarPaquete;

	
	 public OfertaLaboralBeanServidor() {
	        this.nombre = null;
	        this.descripcion = null;
	        this.fechaDeAlta = null;
	        this.costo = 0.0f;
	        this.remuneracion = 0.0f;
	        this.horario = null;
	        this.departamento = null;
	        this.ciudad = null;
	        this.estado = null;
	        this.postulantes = new ArrayList<>();
	        this.imagen = null;
	        this.paq = null;
	        this.paquete = null;
	        this.keywords = new ArrayList<>();
	        this.nicknameEmpresa = null;
	        this.mostrarPostulantes = false;
	        this.mostrarPaquete = false;
	    }



 
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DateBean getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(DateBean fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(float remuneracion) {
        this.remuneracion = remuneracion;
    }

    public DTHorario getHorario() {
        return horario;
    }

    public void setHorario(DTHorario horario) {
        this.horario = horario;
    }



    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }




    public String getPaq() {
        return paq;
    }

    public void setPaq(String paq) {
        this.paq = paq;
    }










	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isMostrarPostulantes() {
		return mostrarPostulantes;
	}

	public void setMostrarPostulantes(boolean mostrarPostulantes) {
		this.mostrarPostulantes = mostrarPostulantes;
	}

	public boolean isMostrarPaquete() {
		return mostrarPaquete;
	}

	public void setMostrarPaquete(boolean mostrarPaquete) {
		this.mostrarPaquete = mostrarPaquete;
	}

	public PaqueteBeanServidor getPaquete() {
		return paquete;
	}

	public void setPaquete(PaqueteBeanServidor paquete) {
		this.paquete = paquete;
	}


	
	 public String getCostoString() {
	        DecimalFormat decimalFormat = new DecimalFormat("#,###.##"); // Define el formato deseado (por ejemplo, con comas como separador de miles)
	        return decimalFormat.format(costo);
	    }

	public String getNicknameEmpresa() {
		return nicknameEmpresa;
	}

	public void setNicknameEmpresa(String nicknameEmpresa) {
		this.nicknameEmpresa = nicknameEmpresa;
	}




	public DepUY getDepartamento() {
		return departamento;
	}




	public void setDepartamento(DepUY departamento) {
		this.departamento = departamento;
	}




	public EstadoOL getEstado() {
		return estado;
	}




	public void setEstado(EstadoOL estado) {
		this.estado = estado;
	}




	public ArrayList<String> getKeywords() {
		return keywords;
	}




	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}




	public ArrayList<UsuarioBeanServidor> getPostulantes() {
		return postulantes;
	}




	public void setPostulantes(ArrayList<UsuarioBeanServidor> postulantes) {
		this.postulantes = postulantes;
	}




}
