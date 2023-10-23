
package webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "ServidorCentralService", targetNamespace = "http://webservice/", wsdlLocation = "http://localhost:9128/webservices?wsdl")
public class ServidorCentralService
    extends Service
{

    private final static URL SERVIDORCENTRALSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVIDORCENTRALSERVICE_EXCEPTION;
    private final static QName SERVIDORCENTRALSERVICE_QNAME = new QName("http://webservice/", "ServidorCentralService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9128/webservices?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVIDORCENTRALSERVICE_WSDL_LOCATION = url;
        SERVIDORCENTRALSERVICE_EXCEPTION = e;
    }

    public ServidorCentralService() {
        super(__getWsdlLocation(), SERVIDORCENTRALSERVICE_QNAME);
    }

    public ServidorCentralService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVIDORCENTRALSERVICE_QNAME, features);
    }

    public ServidorCentralService(URL wsdlLocation) {
        super(wsdlLocation, SERVIDORCENTRALSERVICE_QNAME);
    }

    public ServidorCentralService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVIDORCENTRALSERVICE_QNAME, features);
    }

    public ServidorCentralService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServidorCentralService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServidorCentral
     */
    @WebEndpoint(name = "ServidorCentralPort")
    public ServidorCentral getServidorCentralPort() {
        return super.getPort(new QName("http://webservice/", "ServidorCentralPort"), ServidorCentral.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServidorCentral
     */
    @WebEndpoint(name = "ServidorCentralPort")
    public ServidorCentral getServidorCentralPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice/", "ServidorCentralPort"), ServidorCentral.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVIDORCENTRALSERVICE_EXCEPTION!= null) {
            throw SERVIDORCENTRALSERVICE_EXCEPTION;
        }
        return SERVIDORCENTRALSERVICE_WSDL_LOCATION;
    }

}