
package logica.servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "ErrorAgregarUsuario", targetNamespace = "http://servidor.logica/")
public class ErrorAgregarUsuario_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ErrorAgregarUsuario faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ErrorAgregarUsuario_Exception(String message, ErrorAgregarUsuario faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public ErrorAgregarUsuario_Exception(String message, ErrorAgregarUsuario faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: logica.servidor.ErrorAgregarUsuario
     */
    public ErrorAgregarUsuario getFaultInfo() {
        return faultInfo;
    }

}
