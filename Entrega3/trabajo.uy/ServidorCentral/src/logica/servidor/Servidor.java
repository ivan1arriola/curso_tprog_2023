package logica.servidor;


import excepciones.ExcepcionKeywordVacia;
import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.*;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.servidor.bean.WrapperLista;

import java.time.LocalDate;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Servidor {
    private Endpoint endpoint = null;
    private ICtrlUsuario ctrlUsuario;
    private ICtrlOferta ctrlOferta;

    public Servidor() {
        Fabrica fabrica = Fabrica.getInstance();
        ctrlOferta = fabrica.getICtrlOferta();
        ctrlUsuario = fabrica.getICtrlUsuario();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        String address = "http://" + WSUtils.obtenerIp() + ":" + WSUtils.obtenerPuerto() + "/webservices";
        endpoint = Endpoint.publish(address, this);
        System.out.println("Se publico el servicio en " + address);
        System.out.println("WSDL : " + address + "?wsdl");
        System.out.println("Tipos de Datos : " + address + "?xsd=1");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void cargarDatos() throws ExceptionValidezNegativa, ExcepcionKeywordVacia {
        Fabrica.getInstance().getICtrlCargaDeDatos().cargarDatos();
    }

    @WebMethod
    public boolean validarCredenciales(String identificador, String contrasenia) {
        return ctrlUsuario.validarCredenciales(identificador, contrasenia);
    }

    @WebMethod
    public DTUsuario obtenerDatosUsuario(String nickname) {
        return ctrlUsuario.obtenerDatosUsuario(nickname);
    }

    @WebMethod
    public DTTipoOferta obtenerDatosTO(String nickname) throws ExcepcionTipoOfertaNoExistente {
        return ctrlOferta.obtenerDatosTO(nickname);
    }
    @WebMethod
    public WrapperLista listarOfertasLaboralesConfirmadas(String nicknameParametro) {
        return WSUtils.envolverLista(ctrlOferta.listarOfertasLaboralesConfirmadas(nicknameParametro));
    }
    @WebMethod
    public WrapperLista listarNicknamesUsuarios() {
        return WSUtils.envolverLista(ctrlUsuario.listarNicknamesUsuarios());
    }

    @WebMethod
    public WrapperLista listarOfertasLaboralesKeywords(String keyword) {
        return WSUtils.envolverLista(ctrlOferta.listarOfertasLaboralesKeywords(keyword));
    }

    @WebMethod
    public WrapperLista listarPaquetes() {
        return WSUtils.envolverLista(ctrlOferta.listarPaquetes());
    }

    @WebMethod
    public WrapperLista listarTodasLasOfertasLaborales(String nicknameParametro) {
        return WSUtils.envolverLista(ctrlOferta.listarTodasLasOfertasLaborales(nicknameParametro));
    }
    @WebMethod
    public DTPostulacion obtenerDatosPostulacionW(String nicknameParametro, String nombreOferta){
        return ctrlUsuario.obtenerDatosPostulacionW(nicknameParametro, nombreOferta);
    }

    @WebMethod
    public DTOfertaExtendido obtenerOfertaLaboral(String nombreOferta) {
        return ctrlOferta.obtenerOfertaLaboral(nombreOferta);
    }

    @WebMethod
    public DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String empresaNickname, String nombreOferta) {
        return ctrlOferta.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
    }

    @WebMethod
    public WrapperLista obtenerDTOfertasLaboralesConfirmadas() {
        return WSUtils.envolverDTOfertaExtendido(ctrlOferta.listarOfertasLaboralesConfirmadas());
    }

    @WebMethod
    public DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String postulanteNickname, String nombreOferta) {
        return ctrlOferta.infoOfertaLaboralPostulante(postulanteNickname, nombreOferta);
    }

    @WebMethod
    public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombreOferta) {
        return ctrlOferta.infoOfertaLaboralVisitante(nombreOferta);
    }





    @WebMethod
    public WrapperLista listarComprasPaquete(String nickname) {
        return WSUtils.envolverLista(ctrlOferta.listarComprasPaquete(nickname));
    }

    @WebMethod
    public WrapperLista listarTipoDePublicaciones() {
        return WSUtils.envolverLista(ctrlOferta.listarTipoDePublicaciones());
    }

    @WebMethod
    public WrapperLista listarKeywords() {
        return WSUtils.envolverLista(ctrlOferta.listarKeywords());
    }

    public DTPaquete obtenerDatosPaquete(String nombrePaquete){
        return ctrlOferta.obtenerDatosPaquete(nombrePaquete);
    }

    @WebMethod
    public void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, LocalDate fechanac, String nacionalidad) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosPostulanteImg(nickname, nombre, apellido, correo, contraseña, imagen, fechanac, nacionalidad);
    }

    @WebMethod
    public void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, LocalDate fechanac, String nacionalidad) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosPostulante(nickname, nombre, apellido, correo, contraseña, fechanac, nacionalidad);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, String descripcion) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosEmpresaURL(nickname, nombre, apellido, correo, contraseña, URL, descripcion);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String descripcion) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosEmpresa(nickname, nombre, apellido, correo, contraseña, descripcion);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, byte[] imagen, String descripcion) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosEmpresaURLImg(nickname, nombre, apellido, correo, contraseña, URL, imagen, descripcion);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, String descripcion) {
        Fabrica.getInstance().getICtrlUsuario().ingresarDatosEditadosEmpresaImg(nickname, nombre, apellido, correo, contraseña, imagen, descripcion);
    }

    @WebMethod
    public boolean tieneURL(String nickname) {
        return Fabrica.getInstance().getICtrlUsuario().tieneURL(nickname);
    }

    @WebMethod
    public boolean hayPostulacionW(String postulante_nick, String ofer) {
        return Fabrica.getInstance().getICtrlUsuario().hayPostulacionW(postulante_nick, ofer);
    }

    @WebMethod
    public boolean altaEmpresaURLyImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL, byte[] imagen) {
        return Fabrica.getInstance().getICtrlUsuario().altaEmpresaURLyImagen(nick, contraseña, nombre, apellido, mail, desc, URL, imagen);
    }

    @WebMethod
    public boolean altaPostulanteImagen(String nick, String contraseña, String nombre, String apellido, LocalDate fechanac, String mail, String nacionalidad, byte[] imagen) {
        return Fabrica.getInstance().getICtrlUsuario().altaPostulanteImagen(nick, contraseña, nombre, apellido, fechanac, mail, nacionalidad, imagen);
    }

    @WebMethod
    public boolean altaEmpresaImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, byte[] imagen) {
        return Fabrica.getInstance().getICtrlUsuario().altaEmpresaImagen(nick, contraseña, nombre, apellido, mail, desc, imagen);
    }

    @WebMethod
    public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) {
        return Fabrica.getInstance().getICtrlUsuario().modificarPostulacion(nombre, nick, cvAbreviado, motivacion);
    }

    @WebMethod
    public WrapperLista listarPostulacionesPostulante(String nickname) {
        return WSUtils.envolverLista(ctrlUsuario.listarPostulacionesPostulante(nickname));
    }

    @WebMethod
    public boolean existeUsuarioConNickname(String nickname) {
        return Fabrica.getInstance().getICtrlUsuario().existeUsuarioConNickname(nickname);
    }

    @WebMethod
    public boolean existeUsuarioConEmail(String correo) {
        return Fabrica.getInstance().getICtrlUsuario().existeUsuarioConEmail(correo);
    }

    @WebMethod
    public void seguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo {
        Fabrica.getInstance().getICtrlUsuario().seguirUsuario(usuario, usuario_seguido);
    }

    @WebMethod
    public void dejarDeseguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo {
        Fabrica.getInstance().getICtrlUsuario().dejarDeseguirUsuario(usuario, usuario_seguido);
    }


}
