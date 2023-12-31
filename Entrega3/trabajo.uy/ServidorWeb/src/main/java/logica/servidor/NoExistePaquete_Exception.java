
package logica.servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "NoExistePaquete", targetNamespace = "http://servidor.logica/")
public class NoExistePaquete_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private NoExistePaquete faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public NoExistePaquete_Exception(String message, NoExistePaquete faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public NoExistePaquete_Exception(String message, NoExistePaquete faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: logica.servidor.NoExistePaquete
     */
    public NoExistePaquete getFaultInfo() {
        return faultInfo;
    }

}
