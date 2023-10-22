/**
 * WebServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface WebServices extends java.rmi.Remote {
    public webservice.UsuarioBean obtenerDatosUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean validarCredenciales(java.lang.String identificador, java.lang.String contraseña) throws java.rmi.RemoteException;
    public webservice.HashSet listarNicknamesUsuario() throws java.rmi.RemoteException;
}
