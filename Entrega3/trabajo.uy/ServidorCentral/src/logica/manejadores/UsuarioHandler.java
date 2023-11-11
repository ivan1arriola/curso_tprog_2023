package logica.manejadores;

import excepciones.ErrorAgregarUsuario;
import excepciones.ExceptionUsuarioNoEncontrado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.PersistenceException;
import logica.clases.Usuario;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//import main.java.excepciones.ExceptionUsuarioNoEncontrado;

public class UsuarioHandler {

    private static UsuarioHandler instancia;
    private static EntityManager database;

    private UsuarioHandler() {
    }

    public static UsuarioHandler getInstance() {
        if (instancia == null) {
            instancia = new UsuarioHandler();
        }
        return instancia;
    }

    public boolean existeNick(String nombre) {
        try {
            TypedQuery<Long> query = database.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.nickname = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeCorreo(String mail) {
        try {
            TypedQuery<Long> query = database.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.correoElectronico = :mail", Long.class);
            query.setParameter("mail", mail);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void agregar(Usuario usuario) throws ErrorAgregarUsuario {
        try {
            database.getTransaction().begin();
            database.persist(usuario);
            database.getTransaction().commit();
        } catch (PersistenceException e) {
            if (database.getTransaction() != null && database.getTransaction().isActive()) {
                database.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new ErrorAgregarUsuario("No se logro agregar el usuario");
        }
    }

    public Usuario buscarNick(String nombre) throws ExceptionUsuarioNoEncontrado {
        try {
            TypedQuery<Usuario> query = database.createQuery("SELECT u FROM Usuario u WHERE u.nickname = :nombre", Usuario.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new ExceptionUsuarioNoEncontrado("Usuario no encontrado para el nombre: " + nombre);
        }
    }


    public Usuario buscarCorreo(String mail) throws ExceptionUsuarioNoEncontrado {
        try {
            TypedQuery<Usuario> query = database.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :mail", Usuario.class);
            query.setParameter("mail", mail);
            return query.getSingleResult();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionUsuarioNoEncontrado("Usuario no encontrado para el email: " + mail);
        }
    }

    public Map<String, Usuario> obtenerNick() {
        try {
            TypedQuery<Usuario> query = database.createQuery("SELECT u FROM Usuario u", Usuario.class);
            List<Usuario> usuarios = query.getResultList();

            Map<String, Usuario> nickUsuariosMap = new TreeMap<>();
            for (Usuario usuario : usuarios) {
                nickUsuariosMap.put(usuario.getNickname(), usuario);
            }

            return nickUsuariosMap;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null; // Devuelve null en caso de error
        }
    }

    public Map<String, Usuario> obtenerCorreo() {
        try {
            TypedQuery<Usuario> query = database.createQuery("SELECT u FROM Usuario u", Usuario.class);
            List<Usuario> usuarios = query.getResultList();

            Map<String, Usuario> correoUsuariosMap = new TreeMap<>();
            for (Usuario usuario : usuarios) {
                correoUsuariosMap.put(usuario.getcorreoElectronico(), usuario);
            }

            return correoUsuariosMap;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null; // Devuelve null en caso de error
        }
    }

    public static void setBaseDatos(EntityManager em) {

        database = em;
    }
}