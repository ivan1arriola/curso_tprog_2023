package logica.clases;

import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionValidezNegativa;
//import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
//import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.Lob;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.DiscriminatorColumn;
//import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
//import logica.datatypes.DTCantTO;
//import logica.datatypes.DTCompraPaquetes;
//import logica.datatypes.DTEmpresa;
//import logica.datatypes.DTEmpresaConCompras;
//import logica.datatypes.DTHora;
//import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
//import logica.datatypes.DTOfertaExtendidoConKeywords;
//import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
//import logica.datatypes.DTOfertaExtendidoConKeywordsTit;
//import logica.datatypes.DTOfertaExtendidoSinPConK;
//import logica.datatypes.DTOfertaLaboral;
//import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulacion;
import logica.datatypes.DTPostulante;
import logica.datatypes.DTPostulanteExtendido;
//import logica.datatypes.DTTipoOferta;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTUsuarioSinInfoSocial;
//import logica.dto.PostulacionDTO;
import logica.dto.PostulanteDTO;
import logica.dto.UsuarioDTO;
//import logica.persistencia.TrabajoUyHistoricoManager;

import java.time.LocalDate;
import java.util.HashSet;
//import java.util.Iterator;
import java.util.Set;

@Entity
@DiscriminatorValue("P")
public class Postulante extends Usuario {
    private LocalDate fechaNac;
    private String nacionalidad;
    // foreign key
    @OneToMany(cascade = CascadeType.PERSIST,  mappedBy = "postulante")
    private Set<Postulacion> postulaciones;
    private Set<OfertaLaboral> ofertasFavoritas;

    // constructor con imagen
    public Postulante(String nickname,  String contrasena,  String nombre,  String apellido,  String correo_electronico,  LocalDate fechaNac,  String nacionalidad,  byte[] img) throws ExceptionFechaInvalida {
    	
        super(nickname,  nombre,  apellido,  correo_electronico,  contrasena,  img); // super es para llamar al constructor de la clase padre
        ofertasFavoritas = new HashSet<OfertaLaboral>();
        try {
            if (LocalDate.now().isAfter(fechaNac)) {
                this.fechaNac = fechaNac;
            } else {
                throw new ExceptionFechaInvalida("La fecha de Nacimiento debe ser anterior a la actual");
            }
            this.nacionalidad = nacionalidad;
            this.postulaciones = new HashSet<Postulacion>();
        } catch (ExceptionFechaInvalida e) {
            System.out.println(e);
            throw e;
        }
    }

    // constructor  sin imagen
    public Postulante(String nickname,  String contrasena,  String nombre,  String apellido,  String correo_electronico,  LocalDate fechaNac,  String nacionalidad) throws ExceptionFechaInvalida {
        super(nickname,  nombre,  apellido,  correo_electronico,  contrasena); // super es para llamar al constructor de la clase padre

        try {
            if (fechaNac.isBefore(LocalDate.now())) {
                this.fechaNac = fechaNac;
            } else {
                throw new ExceptionFechaInvalida("La fecha de Nacimiento debe ser anterior a la actual");
            }
            this.nacionalidad = nacionalidad;
            this.postulaciones = new HashSet<Postulacion>();
        } catch (ExceptionFechaInvalida e) {
            System.out.println(e);
            throw e;

        }
    }

    public Postulante() {

    }


    // Getters
    public LocalDate getFechaNac() {
        return fechaNac;
    }

    // Setters
    public void setFechaNac(LocalDate fechaNac) throws ExceptionFechaInvalida {

        try {
            if (fechaNac.isBefore(LocalDate.now())) {
                this.fechaNac = fechaNac;
            } else {
                throw new ExceptionFechaInvalida("La fecha de Nacimiento debe ser anterior a la actual");
            }
        } catch (ExceptionFechaInvalida e) {
            System.out.println(e);
            throw e;
        }

    }

    public Set<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Set<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    // Metodos
    public boolean esEmpresa() {
        return false; // es postulante,     no es empresa
    }

    public DTUsuario obtenerDatosUsuario() {
        // hacer un DTPostulante
        Set<DTUsuarioSinInfoSocial> sdores = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTUsuarioSinInfoSocial> sdos = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTOfertaExtendido> ofer_favs = new HashSet<DTOfertaExtendido>();
        for (Usuario elemento : getSeguidores()) {
        	DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(),  elemento.getcorreoElectronico(),  elemento.getApellido(),  elemento.getNombre(),  elemento.getcontrasenia(),  elemento.getImagen());
            sdores.add(dtp);
        }
        for (Usuario elemento : getSeguidos()) {
            DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(),  elemento.getcorreoElectronico(),  elemento.getApellido(),  elemento.getNombre(),  elemento.getcontrasenia(),  elemento.getImagen());
            sdos.add(dtp);
        }
        for (OfertaLaboral elemento : getOfertasFavoritas()) {
        	DTOfertaExtendido dtp = elemento.obtenerDatosOferta();
        	ofer_favs.add(dtp);
        }
        DTPostulante postul = new DTPostulante(this.getNickname(),  this.getcorreoElectronico(),  this.getApellido(),  this.getNombre(),  this.getcontrasenia(),  this.getImagen(),  fechaNac,  nacionalidad,  sdos,  sdores,  ofer_favs);
        return postul;
    }

    public Postulacion crearPostulacion(String curriculumVitae,  String motivacion,  LocalDate fecha,  String URLDocExtras,  OfertaLaboral OferLab,  String urlVideo) throws ExceptionValidezNegativa {

        try {
            int dura = OferLab.getTipoOferta().getDuracion();
            LocalDate altaOferta = OferLab.getTipoOferta().getFechaAlta();
            if (altaOferta.plusDays(dura).isAfter(LocalDate.now())) {
                throw new ExceptionValidezNegativa("Oferta no vigente");
            }

            Postulacion postulacion = new Postulacion(this,  curriculumVitae,  motivacion,  fecha,  URLDocExtras,  OferLab,  urlVideo);
            postulaciones.add(postulacion);
            return postulacion;
        } catch (ExceptionValidezNegativa e) {
            System.out.println(e);
            throw e;
        }

    }

    public Postulacion crearPostulacionForzado(String curriculumVitae,  String motivacion,  LocalDate fecha,  String URLDocExtras,  OfertaLaboral OferLab,  String vid) throws ExceptionValidezNegativa {
        Postulacion postulacion = new Postulacion(this,  curriculumVitae,  motivacion,  fecha,  URLDocExtras,  OferLab,  vid);
        postulaciones.add(postulacion);
        return postulacion;


    }

    public boolean existePostulacion(String nombre) {
        for (Postulacion postulacion : postulaciones) {
            String nombreOferta = postulacion.obtenerNombreOfertaLaboral();
            if (nombreOferta.equals(nombre)) {
                return true; // si existe retorna true y sale de existepostulacion
            }
        }
        return false;
    }

    public boolean editarPostulacion(String nombre,  String cvAbreviado,  String motivacion) {
        for (Postulacion postulacion : postulaciones) {
            String nombreOferta = postulacion.obtenerNombreOfertaLaboral();
            if (nombreOferta.equals(nombre)) {
                postulacion.setCV(cvAbreviado);
                postulacion.setMotivacion(motivacion);
                return true;
            }
        }
        return false;
    }

    public DTPostulacion obtenerDatosPostulacion(String postulante_nick,  String ofer) {
        // obtener para este postulante la postulacion si trabaja en la oferta
        // si no existe retorno NULL
        DTPostulacion respuesta = null;
        for (Postulacion postulacion : postulaciones) {
            String nombreOferta = postulacion.obtenerNombreOfertaLaboral();
            if (nombreOferta.equals(ofer)) {
                respuesta = postulacion.obtenerDT();
                return respuesta;
            }
        }
        return respuesta;
    }

    // corregido,     se pasan mas parametros para la ejecucion
    public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual,  String UsuarioQueSeHaceConsulta) {
        DTPostulante postul;

        Set<DTUsuarioSinInfoSocial> sdores = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTUsuarioSinInfoSocial> sdos = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTOfertaExtendido> ofer_favs = new HashSet<DTOfertaExtendido>();

        for (Usuario elemento : getSeguidores()) {
        	DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(),  elemento.getcorreoElectronico(),  elemento.getApellido(),  elemento.getNombre(),  elemento.getcontrasenia(),  elemento.getImagen());
            sdores.add(dtp);
        }

        for (Usuario elemento : getSeguidos()) {
            DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(),  elemento.getcorreoElectronico(),  elemento.getApellido(),  elemento.getNombre(),  elemento.getcontrasenia(),  elemento.getImagen());
            sdos.add(dtp);
        }
        
        for (OfertaLaboral elemento : getOfertasFavoritas()) {
        	DTOfertaExtendido dtp = elemento.obtenerDatosOferta();
        	ofer_favs.add(dtp);
        }

        if (UsuarioRegistradoActual.equals(UsuarioQueSeHaceConsulta)) {
            String nickname = getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getcorreoElectronico();
            String contrasenia = getcontrasenia();
            byte[] imagen = getImagen();
            LocalDate fechaNac = getFechaNac();
            String nacionalidad = getNacionalidad();
            Set<Postulacion> posts = getPostulaciones();

            Set<DTPostulacion> postsDT = new HashSet<DTPostulacion>();

            for (Postulacion post : posts) {
                DTPostulacion paux = post.obtenerDT();
                postsDT.add(paux);
            }

            postul = new DTPostulanteExtendido(nickname,  correoElectronico,  apellido,  nombre,  contrasenia,  imagen,  fechaNac,  nacionalidad,  postsDT,  sdos,  sdores,  ofer_favs);
        } else {
            String nickname = getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getcorreoElectronico();
            String contraseña = getcontrasenia();
            byte[] imagen = getImagen();
            LocalDate fechaNac = getFechaNac();
            String nacionalidad = getNacionalidad();
            postul = new DTPostulante(nickname,  correoElectronico,  apellido,  nombre,  contraseña,  imagen,  fechaNac,  nacionalidad,  sdos,  sdores,  ofer_favs);
        }
        return postul;
    }

    public Set<String> listarOfertasLaborales() {
        Set<String> lista = new HashSet<String>();

        if (postulaciones != null) {
            for (Postulacion p : postulaciones) {
                lista.add(p.obtenerNombreOfertaLaboral());
            }
        }

        return lista;
    }

    @Override
    public UsuarioDTO getDTO() {

//        TrabajoUyHistoricoManager trabajoUyHistoricoManager = new TrabajoUyHistoricoManager();
        
//        if (usuarioDTO!= null){
//            usuarioDTO.setNombre(getNombre());
//            usuarioDTO.setApellido(getApellido());
//            if (usuarioDTO instanceof PostulanteDTO postulanteDTO){
//                postulanteDTO.setFecha_nacimiento(getFechaNac());
//                postulanteDTO.setNacionalidad(getNacionalidad());
//                System.out.println("se encontro en la base de datos: " + postulanteDTO.getNickname());
//                return postulanteDTO;
//            } else return usuarioDTO;
//        } else {
            return new PostulanteDTO(getNickname(),  getcorreoElectronico(),  getNombre(),  getApellido(),  getNacionalidad(),  getFechaNac() );
//        }
    }

    public Set<String> listarPostulaciones() {
        Set<String> postulacionesList = new HashSet<>();
        for (Postulacion postulacion : postulaciones) {
            postulacionesList.add(postulacion.obtenerNombreOfertaLaboral());
        }
        return postulacionesList;
    }
    
    
    public void marcarFavorita(OfertaLaboral ofer) {
    	ofertasFavoritas.add(ofer);
    }
    
    public void desmarcarFavorita(OfertaLaboral ofer) {
    	ofertasFavoritas.remove(ofer);
    }
    
    public Set<OfertaLaboral> getOfertasFavoritas(){
    	return ofertasFavoritas;
    }
    
    public void setOfertasFavoritas(Set<OfertaLaboral> oferLabs){
    	ofertasFavoritas = oferLabs;
    }

}
