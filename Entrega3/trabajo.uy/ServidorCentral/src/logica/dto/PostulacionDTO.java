package logica.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Postulaciones")
public class PostulacionDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id",  nullable = false)
//    private Long id;

    @Column(name = "cv",  nullable = false)
    private String curriculumVitae;

    @Column(nullable = false)
    private String motivacion;


    @Column(nullable = false)
    private LocalDate fechapostulacion;

    @Id
    @ManyToOne
    @JoinColumn(name = "ofertalaboralid")
    private OfertaLaboralDTO ofertaLaboral;

    @Id
    @ManyToOne
    @JoinColumn(name = "postulanteid")
    private PostulanteDTO postulante;


    public PostulacionDTO(String curriculumVitae,  String motivacion,  LocalDate fechapostulacion,  OfertaLaboralDTO ofertaLaboral,  PostulanteDTO postulante) {
        this.curriculumVitae = curriculumVitae;
        this.motivacion = motivacion;
        this.fechapostulacion = fechapostulacion;
        this.ofertaLaboral = ofertaLaboral;
        this.postulante = postulante;
    }

    public PostulacionDTO() {

    }

    public String getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }
    
    public void setPostulante(PostulanteDTO postulante) {
    	this.postulante = postulante;
    }
}
