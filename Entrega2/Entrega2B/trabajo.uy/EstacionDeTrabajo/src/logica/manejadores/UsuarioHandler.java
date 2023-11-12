package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Usuario;

//import main.java.excepciones.ExceptionUsuarioNoEncontrado;

public class UsuarioHandler {

    private Map<String,  Usuario> nickUsuariosMap;
    private Map<String,  Usuario> correoUsuariosMap;

    private static UsuarioHandler instancia;

    public boolean existeNick(String nombre) {
        return nickUsuariosMap.containsKey(nombre);
    }
    
    public boolean existeCorreo(String mail) {
    	return correoUsuariosMap.containsKey(mail);
    }
    
    public void agregar(Usuario usuario) {
        nickUsuariosMap.put(usuario.getNickname(),  usuario);
        correoUsuariosMap.put(usuario.getcorreoElectronico(),  usuario);
    }
    
    public Usuario buscarNick(String nombre) {
        return nickUsuariosMap.get(nombre);
    }
    
    public Usuario buscarCorreo(String mail) {
        if (!correoUsuariosMap.containsKey(mail)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return correoUsuariosMap.get(mail);
    }
    
    private UsuarioHandler() {
    	nickUsuariosMap = new HashMap<String,  Usuario>();
        correoUsuariosMap = new HashMap<String,  Usuario>();
    }
    
    public static UsuarioHandler getInstance() {
        if (instancia == null) {
            instancia = new UsuarioHandler();
        }
        return instancia;
    }

    public Map<String,  Usuario> obtenerNick() {
        return nickUsuariosMap;
    }
    
    public Map<String,  Usuario> obtenerCorreo() {
        return correoUsuariosMap;
    }
}