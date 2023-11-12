
package logica.servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "OfertaLaboralNoEncontrada",  targetNamespace = "http://servidor.logica/")
public class OfertaLaboralNoEncontrada_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OfertaLaboralNoEncontrada faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoEncontrada_Exception(String message,  OfertaLaboralNoEncontrada faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoEncontrada_Exception(String message,  OfertaLaboralNoEncontrada faultInfo,  Throwable cause) {
        super(message,  cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: logica.servidor.OfertaLaboralNoEncontrada
     */
    public OfertaLaboralNoEncontrada getFaultInfo() {
        return faultInfo;
    }

}
