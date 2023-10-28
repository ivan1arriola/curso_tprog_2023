package logica.clases;

import jakarta.persistence.*;
import logica.Utils;
import logica.datatypes.DTUsuario;

import java.util.Base64;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// declaro entidad
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // -----------
    private String nickname;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasenia;
    private String imagen;
    // relaciones
    @ManyToMany
    @JoinTable(
            name = "usuario_seguidores",
            joinColumns = @JoinColumn(name = "seguido_id"),
            inverseJoinColumns = @JoinColumn(name = "seguidores")
    )
    private Set<Usuario> seguidores = new HashSet<>();

    // Define the @ManyToMany relationship for seguidos (followed users)
    @ManyToMany(mappedBy = "seguidores") // Refers to the seguidores property in the Usuario class
    private Set<Usuario> seguidos;

    public Usuario(String nickname, String nombre, String apellido, String correo_electronico, String contrasenia) {
        this(nickname, nombre, apellido, correo_electronico, contrasenia, null);
    }

    public Usuario(String nickname, String nombre, String apellido, String correo_electronico, String contrasenia, byte[] imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correo_electronico;
        this.contrasenia = contrasenia;
        this.setImagen(imagen);
        this.seguidores = new LinkedHashSet<Usuario>();
        this.seguidos = new LinkedHashSet<Usuario>();

        if (imagen != null)
            Utils.guardarImagen("Usuarios", nickname, "jpg", imagen);

        System.out.println("Se ha creado un usuario. - " + nickname);
    }

    //Getters
    public String getNickname() {
        return nickname;
    }

    // Setters
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getcorreoElectronico() {
        return correoElectronico;
    }

    public String getcontrasenia() {
        return contrasenia;
    }

    public byte[] getImagen() {
        byte[] base64DecodedBytes = Base64.getDecoder().decode(imagen);
        return base64DecodedBytes;
    }

    public void setImagen(byte[] imagen) {
        byte[] base64EncodedBytes = Base64.getEncoder().encode(imagen);
        // Convert the byte array to a Base64 string
        String base64EncodedString = new String(base64EncodedBytes);

        this.imagen = base64EncodedString;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    // OPERACIONES

    public abstract boolean esEmpresa();

    public abstract DTUsuario obtenerDatosUsuario();


    // corregido,  se pasan mas parametros para la ejecucion
    // para visitantes colocar en usuario registrado actual 'nada'
    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual, String UsuarioQueSeHaceConsulta); // operacion implementada en las subclases


    // NO ESTA EN EL DCD
    public abstract Set<String> listarOfertasLaborales();

    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido;
    }

    public Set<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Set<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public Set<Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(Set<Usuario> seguidos) {
        this.seguidos = seguidos;
    }

    public void seguirUsuario(Usuario usuario_seguido) {
        seguidos.add(usuario_seguido);
    }

    public void dejarDeSeguirUsuario(Usuario usuario_seguido) {
        seguidos.remove(usuario_seguido);
    }


}
