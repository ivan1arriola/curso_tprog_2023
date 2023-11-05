package logica.servidor;


import excepciones.*;
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
import logica.manejadores.UsuarioHandler;
import logica.servidor.bean.WrapperLista;

import java.time.LocalDate;
import java.util.List;

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
    public WrapperLista obtenerSeguidoresUsuario(String nickname) {
        return WSUtils.envolverLista(ctrlUsuario.obtenerSeguidoresUsuario(nickname));
    }
    
    @WebMethod
    public WrapperLista obtenerSeguidosUsuario(String nickname) {
        return WSUtils.envolverLista(ctrlUsuario.obtenerSeguidosUsuario(nickname));
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
    public void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String password, byte[] imagen, LocalDate fechanac, String nacionalidad) {
        ctrlUsuario.ingresarDatosEditadosPostulanteImg(nickname, nombre, apellido, correo, password, imagen, fechanac, nacionalidad);
    }

    @WebMethod
    public void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String password, LocalDate fechanac, String nacionalidad) {
        ctrlUsuario.ingresarDatosEditadosPostulante(nickname, nombre, apellido, correo, password, fechanac, nacionalidad);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String password, String URL, String descripcion) {
        ctrlUsuario.ingresarDatosEditadosEmpresaURL(nickname, nombre, apellido, correo, password, URL, descripcion);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String password, String descripcion) {
        ctrlUsuario.ingresarDatosEditadosEmpresa(nickname, nombre, apellido, correo, password, descripcion);
    }

    @WebMethod
    public void altaEmpresaURL(String nickname, String password, String nombre, String apellido, String correo, String description, String url) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
        ctrlUsuario.altaEmpresaURL(nickname, password, nombre, apellido, correo, description, url);
    }



    @WebMethod
    public void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String password, String URL, byte[] imagen, String descripcion) {
        ctrlUsuario.ingresarDatosEditadosEmpresaURLImg(nickname, nombre, apellido, correo, password, URL, imagen, descripcion);
    }

    @WebMethod
    public void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String password, byte[] imagen, String descripcion) {
        ctrlUsuario.ingresarDatosEditadosEmpresaImg(nickname, nombre, apellido, correo, password, imagen, descripcion);
    }

    @WebMethod
    public boolean tieneURL(String nickname) {
        return ctrlUsuario.tieneURL(nickname);
    }

    @WebMethod
    public boolean hayPostulacionW(String postulante_nick, String ofer) {
        return ctrlUsuario.hayPostulacionW(postulante_nick, ofer);
    }

    @WebMethod
    public boolean altaEmpresaURLyImagen(String nick, String password, String nombre, String apellido, String mail, String desc, String URL, byte[] imagen) {
        return ctrlUsuario.altaEmpresaURLyImagen(nick, password, nombre, apellido, mail, desc, URL, imagen);
    }

    @WebMethod
    public boolean altaPostulanteImagen(String nick, String password, String nombre, String apellido, String fechanac, String mail, String nacionalidad, byte[] imagen) {
        // Se asume que fechanac esta correctamente formateada
        LocalDate fechaNacimiento = LocalDate.parse(fechanac);
        return ctrlUsuario.altaPostulanteImagen(nick, password, nombre, apellido, fechaNacimiento, mail, nacionalidad, imagen);
    }

    @WebMethod
    public boolean altaPostulante(String nick, String password, String nombre, String apellido, String fechanac, String mail, String nacionalidad) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
        // Se asume que fechanac esta correctamente formateada
        LocalDate fechaNacimiento = LocalDate.parse(fechanac);
        return ctrlUsuario.altaPostulante(nick, password, nombre, apellido, mail, fechaNacimiento, nacionalidad);
    }

    @WebMethod
    public boolean altaEmpresaImagen(String nick, String password, String nombre, String apellido, String mail, String desc, byte[] imagen) {
        return ctrlUsuario.altaEmpresaImagen(nick, password, nombre, apellido, mail, desc, imagen);
    }

    @WebMethod
    public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) {
        return ctrlUsuario.modificarPostulacion(nombre, nick, cvAbreviado, motivacion);
    }
    @WebMethod
    public void modificarDatosUsuario(DTUsuario usuario) throws TipoUsuarioNoValido {
        if(usuario instanceof DTEmpresa empresa){
            ctrlUsuario.ingresarDatosEditadosEmpresaURLImg(
                    empresa.getNickname(),
                    empresa.getNombre(),
                    empresa.getApellido(),
                    empresa.getcontrasenia(),
                    empresa.getcontrasenia(),
                    empresa.getUrl(),
                    empresa.getImagen(),
                    empresa.getDescripcion()
            );
        } else if (usuario instanceof DTPostulante postulante){
            ctrlUsuario.ingresarDatosEditadosPostulanteImg(
                    postulante.getNickname(),
                    postulante.getNombre(),
                    postulante.getApellido(),
                    postulante.getcorreoElectronico(),
                    postulante.getcontrasenia(),
                    postulante.getImagen(),
                    postulante.getFechaNac(),
                    postulante.getNacionalidad()
            );
        } else {
            throw new TipoUsuarioNoValido("DTUsuario debe ser del tipo Empresa o Postulante");
        }
    }

    @WebMethod
    public WrapperLista listarPostulacionesPostulante(String nickname) {
        return WSUtils.envolverLista(ctrlUsuario.listarPostulacionesPostulante(nickname));
    }

    @WebMethod
    public boolean existeUsuarioConNickname(String nickname) {
        return ctrlUsuario.existeUsuarioConNickname(nickname);
    }

    @WebMethod
    public boolean existeUsuarioConEmail(String correo) {
        return ctrlUsuario.existeUsuarioConEmail(correo);
    }

    @WebMethod
    public void seguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo, ExceptionUsuarioNoEncontrado {
        ctrlUsuario.seguirUsuario(usuario, usuario_seguido);
    }

    @WebMethod
    public void dejarDeseguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo {
        ctrlUsuario.dejarDeseguirUsuario(usuario, usuario_seguido);
    }


}
