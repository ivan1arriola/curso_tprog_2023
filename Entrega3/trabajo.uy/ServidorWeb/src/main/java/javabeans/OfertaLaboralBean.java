package javabeans;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

public class OfertaLaboralBean {
	private String nombre;
	private String descripcion;
	private LocalDate fechaDeAlta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private Departamento departamento;
	private String ciudad;
	private EstadoOfertaLaboral estado;
	private Set<UsuarioBean> postulantes;
	private String imagen; 
	private String paq;
	private PaqueteBean paquete;
	private Set<String> keywords;
	private String nicknameEmpresa;
	
	
	private boolean mostrarPostulantes;
	private boolean mostrarPaquete;

	
	public OfertaLaboralBean() {
        this.nombre = null;
        this.descripcion = null;
        this.fechaDeAlta = null;
        this.costo = 0.0f;
        this.remuneracion = 0.0f;
        this.horario = null;
        this.departamento = null;
        this.ciudad = null;
        this.estado = null;
        this.setImagen(null);
        this.paq = null;
        this.setPaquete(null);
        this.keywords = new HashSet<String>();
        this.setPostulantes(new HashSet<UsuarioBean>());
        this.mostrarPostulantes = false;
        this.mostrarPaquete = false;
    }
	
	public Departamento getDepartamento() {
	    return departamento;
	}

	public EstadoOfertaLaboral getEstado() {
	    return estado;
	}
	
	 // Setter para departamento con enumeración Departamento
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Setter para departamento con enumeración DepUY (compatible)
    public void setDepartamento(DepUY depUY) {
        this.departamento = Departamento.valueOf(depUY.name());
    }

    // Setter para estado con enumeración EstadoOfertaLaboral
    public void setEstado(EstadoOfertaLaboral estado) {
        this.estado = estado;
    }

    // Setter para estado con enumeración EstadoOL (compatible)
    public void setEstado(EstadoOL estadoOL) {
        this.estado = EstadoOfertaLaboral.valueOf(estadoOL.name());
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

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
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



	public Set<String> getKeywords() {
		return keywords;
	}


	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}



	 public String getRemuneracionString() {
	        DecimalFormat decimalFormat = new DecimalFormat("#,###.##"); // Define el formato deseado (por ejemplo, con comas como separador de miles)
	        return decimalFormat.format(remuneracion);
	    }
	 public String getFechaDeAltaString() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); // Define el formato deseado (dd / mm / aaaa)
	        return fechaDeAlta.format(formatter);
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

	public PaqueteBean getPaquete() {
		return paquete;
	}

	public void setPaquete(PaqueteBean paquete) {
		this.paquete = paquete;
	}

	public Set<UsuarioBean> getPostulantes() {
		return postulantes;
	}

	public void setPostulantes(Set<UsuarioBean> postulantes) {
		this.postulantes = postulantes;
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




}
