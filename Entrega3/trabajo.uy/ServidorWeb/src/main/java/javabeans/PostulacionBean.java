package javabeans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostulacionBean {
    private String nicknamePostulante;
    private LocalDate fecha;
    private String uRLDocExtras;
    private String cVitae;
    private String motivacion;
	private String nombreOfertaLaboral;
	private String estado;

    private String video;
    
    private Integer clasificacion;
    private LocalDate fechaResu;

    public PostulacionBean() {
        this.setNicknamePostulante(null);
        this.nombreOfertaLaboral = null;
        this.fecha = null;
        this.uRLDocExtras = null;
        this.cVitae = null;
        this.motivacion = null;
        this.video = null;
    }

    public String getuRLDocExtras() {
        return uRLDocExtras;
    }

    public void setuRLDocExtras(String uRLDocExtras) {
        this.uRLDocExtras = uRLDocExtras;
    }

    public String getcVitae() {
        return cVitae;
    }

    public void setcVitae(String cVitae) {
        this.cVitae = cVitae;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        // Asegurémonos de que la URL del video sea válida
        if (esURLValida(video)) {
            if (video.contains("embed")){
                this.video = video;
            } else {
                this.video = transformURL(video);
            }

        } else {
            // Manejar el caso en el que la URL no sea válida (puedes lanzar una excepción, establecer un valor predeterminado, etc.)
            this.video = null;
        }
    }

    private String transformURL(String originalURL) {
        // Expresiones regulares para extraer el identificador del video
        String regex1 = "https://youtu.be/(\\w+)";
        String regex2 = "https://www.youtube.com/watch\\?v=(\\w+)";

        String videoId = null;

        // Intenta hacer coincidir con la primera expresión regular
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(originalURL);
        if (matcher1.find()) {
            videoId = matcher1.group(1);
        } else {
            // Si no coincide con la primera, intenta la segunda expresión regular
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(originalURL);
            if (matcher2.find()) {
                videoId = matcher2.group(1);
            }
        }
        if (videoId != null) {
            return "https://www.youtube.com/embed/" + videoId;
        } else {
            // Si no se encontró un identificador válido, retorna la URL original
            return originalURL;
        }
    }

    private boolean esURLValida(String url) {
        return url != null && (url.contains("youtube.com") || url.contains("youtu.be"));
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getFechaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return fecha.format(formatter);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getURLDocExtras() {
        return uRLDocExtras;
    }

    public void setURLDocExtras(String uRLDocExtras) {
        this.uRLDocExtras = uRLDocExtras;
    }

    public String getCVitae() {
        return cVitae;
    }

    public void setCVitae(String cVitae) {
        this.cVitae = cVitae;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

	public String getNombreOfertaLaboral() {
		return nombreOfertaLaboral;
	}

	public void setNombreOfertaLaboral(String nombreOfertaLaboral) {
		this.nombreOfertaLaboral = nombreOfertaLaboral;
	}



	public String getNicknamePostulante() {
		return nicknamePostulante;
	}



	public void setNicknamePostulante(String nicknamePostulante) {
		this.nicknamePostulante = nicknamePostulante;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Integer clasificacion) {
		this.clasificacion = clasificacion;
	}
	
    public String getFechaResuString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return fechaResu.format(formatter);
    }
    
    public LocalDate getfechaResu() {
    	return fechaResu;
    }
    
    public void setfechaResu(LocalDate fechaR) {
    	fechaResu = fechaR;
    }
}
