package logica.servidor;


import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionValidezNegativa;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.servidor.bean.WrapperLista;

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
    public void publicar(){
        String address = "http://localhost:"+ WSUtils.obtenerPuerto() + "/webservices";
        endpoint = Endpoint.publish(address, this);
        System.out.println("Se publico el servicio en " + address);
        System.out.println("WSDL : " + address + "?wsdl" );
        System.out.println("Tipos de Datos : " + address + "?xsd=1" );
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
    public DTUsuario obtenerDatosUsuario(String nickname){
        return ctrlUsuario.obtenerDatosUsuario(nickname);
    }
    @WebMethod
    public WrapperLista listarNicknamesUsuarios() {
        return WSUtils.envolverLista(ctrlUsuario.listarNicknamesUsuarios());
    }

    @WebMethod
    public WrapperLista listarComprasPaquete(String nickname) {
        return WSUtils.envolverLista(ctrlOferta.listarComprasPaquete(nickname));
    }

    @WebMethod
    public WrapperLista listarTipoDePublicaciones() {
        return WSUtils.envolverLista(ctrlOferta.listarTipoDePublicaciones());
    }



}
