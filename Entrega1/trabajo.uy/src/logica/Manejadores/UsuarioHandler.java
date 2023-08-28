package logica.Manejadores;

import logica.Clases.Usuario;

import java.util.HashMap;

import excepciones.ExceptionUsuarioNoEncontrado;

public class UsuarioHandler {

    private HashMap<String, Usuario> nickUsuariosMap;
    private HashMap<String, Usuario> correoUsuariosMap;

    private static UsuarioHandler instancia;

    private UsuarioHandler() {
    	nickUsuariosMap = new HashMap<String, Usuario>();
        correoUsuariosMap = new HashMap<String, Usuario>();
    }

    public static UsuarioHandler getInstance() {
        if (instancia == null) {
            instancia = new UsuarioHandler();
        }
        return instancia;
    }

    public HashMap<String, Usuario> obtenerNick(){
        return nickUsuariosMap;
    }
    
    public HashMap<String, Usuario> obtenerCorreo(){
        return correoUsuariosMap;
    }

    public boolean existeNick(String nombre){
        return nickUsuariosMap.containsKey(nombre);
    }
    
    public boolean existeCorreo(String mail){
    	return correoUsuariosMap.containsKey(mail);
    }

    public void agregar(Usuario usuario){
        nickUsuariosMap.put(usuario.getNickname(), usuario);
        correoUsuariosMap.put(usuario.getCorreo_electronico(), usuario);
    }

    public Usuario buscarNick(String nombre) {
        return nickUsuariosMap.get(nombre);
    }
    
    public Usuario buscarCorreo(String mail){
        if (!correoUsuariosMap.containsKey(mail)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return correoUsuariosMap.get(mail);
    }
}