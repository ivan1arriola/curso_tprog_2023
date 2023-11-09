
package logica.servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "ExceptionCompraPaqueteConValorNegativo", targetNamespace = "http://servidor.logica/")
public class ExceptionCompraPaqueteConValorNegativo_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExceptionCompraPaqueteConValorNegativo faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ExceptionCompraPaqueteConValorNegativo_Exception(String message, ExceptionCompraPaqueteConValorNegativo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public ExceptionCompraPaqueteConValorNegativo_Exception(String message, ExceptionCompraPaqueteConValorNegativo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: logica.servidor.ExceptionCompraPaqueteConValorNegativo
     */
    public ExceptionCompraPaqueteConValorNegativo getFaultInfo() {
        return faultInfo;
    }

}