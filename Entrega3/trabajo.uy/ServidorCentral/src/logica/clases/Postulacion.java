package logica.clases;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.Lob;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.DiscriminatorColumn;
//import jakarta.persistence.DiscriminatorType;
import logica.datatypes.DTPostulacion;
import logica.dto.OfertaLaboralDTO;
import logica.dto.PostulacionDTO;
import logica.dto.PostulanteDTO;

import java.time.LocalDate;

@Entity
public class Postulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iden;
    // -----------
    // atributos
    private LocalDate fecha;
    private String curriculumVitae;
    private String motivacion;
    private String uRLDocExtras;
    // relaciones

    @ManyToOne
    @JoinColumn(name = "ofertaLaboral_id")
    private OfertaLaboral oferLab;

    @ManyToOne
    @JoinColumn(name = "postulante_id")
    private Postulante postulante;
    private String urlVideo;
    // evaluacion empresa
    private int clasificacion;


    // constructor
    public Postulacion(Postulante postulante, String curriculumVitae, String motivacion, LocalDate fecha, String uRLDocExtras, OfertaLaboral oferLab, String urlVid) {
        this.postulante = postulante;
        this.fecha = fecha;
        this.curriculumVitae = curriculumVitae;
        this.motivacion = motivacion;
        this.uRLDocExtras = uRLDocExtras;
        this.oferLab = oferLab;
        this.urlVideo = urlVid;
        this.clasificacion = 0; // no ha sido clasificado
        System.out.println("Se ha creado una postulacion. - " + postulante.getNombre() + " - " + oferLab.getNombre());
    }

    public Postulacion() {

    }


    // GETTERS
    public Integer getClasificacion() {
        return clasificacion; // si es cero no es clasificado
    }

    // SETTERS
    public void setClasificacion(Integer posicion) {
        this.clasificacion = posicion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCV() {
        return curriculumVitae;
    }

    public void setCV(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public String getuRLDocExtras() {
        return uRLDocExtras;
    }

    public void setuRLDocExtras(String uRLDocExtras) {
        this.uRLDocExtras = uRLDocExtras;
    }

    public OfertaLaboral getOfertaLaboral() {
        return oferLab;
    }

    public void setOfertaLaboral(OfertaLaboral oferLab) {
        this.oferLab = oferLab;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public String getuRLVideo() {
        return urlVideo;
    }

    public void seturlVideo(String vid) {
        this.urlVideo = vid;
    }

    // METODOS
    public DTPostulacion obtenerDT() {
        DTPostulacion dtpostu;
        //if (urlVideo!=null) {
        dtpostu = new DTPostulacion(postulante.getNickname(), fecha, uRLDocExtras, curriculumVitae, motivacion, urlVideo);
    	/*} else {
    		dtpostu = new DTPostulacion(postulante.getNickname(),   fecha,   uRLDocExtras,   curriculumVitae,   motivacion, "Sin Video disponible");
    	}*/
        return dtpostu;
    }

    public String obtenerNombreOfertaLaboral() {
        return oferLab.getNombre();
    }

    public void editarPostulacion(String cvAbreviado, String motivacion) {
        this.curriculumVitae = cvAbreviado;
        this.motivacion = motivacion;
    }

    // dentro caso uso postulacion a oferta laboral
    public Boolean esPostulacion(String nombre) {
        return oferLab.getNombre().equals(nombre); // retorna true si el nombre de la oferta es igual al nombre que se le pasa por parametro
    }

    public String obtenerNicknamePostulante() {
        return postulante.getNickname();
    }

    public PostulacionDTO getDTO(OfertaLaboralDTO ofertaLaboralDTO) {
//    	System.out.println("no persisti ni al postulante");
        PostulanteDTO postulanteDTO = (PostulanteDTO) postulante.getDTO();
//        System.out.println("llegue a postulacion oferta laboral , pase al postulante");
        return new PostulacionDTO(getCV(), getMotivacion(), fecha, ofertaLaboralDTO, postulanteDTO);
    }
}
