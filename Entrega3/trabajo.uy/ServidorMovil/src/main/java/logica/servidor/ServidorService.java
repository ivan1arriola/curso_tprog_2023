
package logica.servidor;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;
import logica.Configuracion;
import logica.NoExisteArchivoDeConfiguacion;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */

public class ServidorService extends Service {

    private final static URL SERVIDORSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVIDORSERVICE_EXCEPTION;
    private final static QName SERVIDORSERVICE_QNAME = new QName("http://servidor.logica/", "ServidorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            Configuracion config = new Configuracion();
            String host = config.obtenerValor("servidor.ip");
            String port = config.obtenerValor("servidor.puerto");
            String wsdlLocation = String.format("http://%s:%s/webservices?wsdl", host, port);
            url = new URL(wsdlLocation);
        } catch (MalformedURLException | NoExisteArchivoDeConfiguacion ex) {
            e = new WebServiceException(ex);
        }
        SERVIDORSERVICE_WSDL_LOCATION = url;
        SERVIDORSERVICE_EXCEPTION = e;
    }

    public ServidorService() {
        super(__getWsdlLocation(), SERVIDORSERVICE_QNAME);
    }

    public ServidorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVIDORSERVICE_QNAME, features);
    }

    public ServidorService(URL wsdlLocation) {
        super(wsdlLocation, SERVIDORSERVICE_QNAME);
    }

    public ServidorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVIDORSERVICE_QNAME, features);
    }

    public ServidorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServidorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Servidor
     */
    @WebEndpoint(name = "ServidorPort")
    public Servidor getServidorPort() {
        return super.getPort(new QName("http://servidor.logica/", "ServidorPort"), Servidor.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  
     *     Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Servidor
     */
    @WebEndpoint(name = "ServidorPort")
    public Servidor getServidorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servidor.logica/", "ServidorPort"), Servidor.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVIDORSERVICE_EXCEPTION != null) {
            throw SERVIDORSERVICE_EXCEPTION;
        }
        return SERVIDORSERVICE_WSDL_LOCATION;
    }
}
