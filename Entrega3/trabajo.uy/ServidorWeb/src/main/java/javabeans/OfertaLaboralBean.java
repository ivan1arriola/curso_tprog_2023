package javabeans;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import logica.servidor.DepUY;
import logica.servidor.EstadoOL;

public class OfertaLaboralBean implements  Comparable<OfertaLaboralBean>{
	private String nombre;
	private String descripcion;
	private LocalDate fechaDeAlta;
	private float costo;
	private float remuneracion;
	private String horario;
	private Departamento departamento;
	private String ciudad;
	private EstadoOfertaLaboral estado;
	private Set<UsuarioBean> postulantes;
	private String imagen; 
	private String paq;
	private PaqueteBean paquete;
	private Set<String> keywords;
	private String nicknameEmpresa;
	private Integer cantFavs;
	private Integer cantVisitas;
	private String tipoOferta;


	
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
        setCantVisitas(0);
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
		Departamento departamento = null;

		switch (depUY) {
			case ARTIGAS:
				departamento = Departamento.Artigas;
				break;
			case SALTO:
				departamento = Departamento.Salto;
				break;
			case PAYSANDU:
				departamento = Departamento.Paysandú;
				break;
			case RIO_NEGRO:
				departamento = Departamento.RioNegro;
				break;
			case SORIANO:
				departamento = Departamento.Soriano;
				break;
			case COLONIA:
				departamento = Departamento.Colonia;
				break;
			case RIVERA:
				departamento = Departamento.Rivera;
				break;
			case TACUAREMBO:
				departamento = Departamento.Tacuarembo;
				break;
			case DURAZNO:
				departamento = Departamento.Durazno;
				break;
			case FLORES:
				departamento = Departamento.Flores;
				break;
			case FLORIDA:
				departamento = Departamento.Florida;
				break;
			case SAN_JOSE:
				departamento = Departamento.SanJosé;
				break;
			case CANELONES:
				departamento = Departamento.Canelones;
				break;
			case MONTEVIDEO:
				departamento = Departamento.Montevideo;
				break;
			case CERRO_LARGO:
				departamento = Departamento.CerroLargo;
				break;
			case TREINTA_Y_TRES:
				departamento = Departamento.TreintaYTres;
				break;
			case LAVALLEJA:
				departamento = Departamento.Lavalleja;
				break;
			case ROCHA:
				departamento = Departamento.Rocha;
				break;
			case MALDONADO:
				departamento = Departamento.Maldonado;
				break;
			default:
				// Manejar un caso predeterminado si es necesario
				break;
		}

		this.departamento = departamento;
	}


	// Setter para estado con enumeración EstadoOfertaLaboral
    public void setEstado(EstadoOfertaLaboral estado) {
        this.estado = estado;
    }

    // Setter para estado con enumeración EstadoOL (compatible)
	public void setEstado(EstadoOL estadoOL) {
		EstadoOfertaLaboral estado = null;

		switch (estadoOL) {
			case INGRESADA:
				estado = EstadoOfertaLaboral.Ingresada;
				break;
			case CONFIRMADA:
				estado = EstadoOfertaLaboral.Confirmada;
				break;
			case RECHAZADA:
				estado = EstadoOfertaLaboral.Rechazada;
				break;
			case FINALIZADA:
				estado = EstadoOfertaLaboral.Finalizada;
				break;
		}

		this.estado = estado;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
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
	        DecimalFormat decimalFormat = new DecimalFormat("#, ###.##"); // Define el formato deseado (por ejemplo,  con comas como separador de miles)
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
	        DecimalFormat decimalFormat = new DecimalFormat("#, ###.##"); // Define el formato deseado (por ejemplo,  con comas como separador de miles)
	        return decimalFormat.format(costo);
	    }

	public String getNicknameEmpresa() {
		return nicknameEmpresa;
	}

	public void setNicknameEmpresa(String nicknameEmpresa) {
		this.nicknameEmpresa = nicknameEmpresa;
	}

	@Override
	public int compareTo(OfertaLaboralBean otraOferta) {
		return this.nombre.compareTo(otraOferta.getNombre());
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
