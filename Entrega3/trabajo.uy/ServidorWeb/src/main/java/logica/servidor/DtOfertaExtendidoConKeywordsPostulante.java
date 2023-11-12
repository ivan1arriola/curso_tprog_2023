
package logica.servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOfertaExtendidoConKeywordsPostulante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtOfertaExtendidoConKeywordsPostulante">
 *   <complexContent>
 *     <extension base="{http://servidor.logica/}dtOfertaExtendidoSinPConK">
 *       <sequence>
 *         <element name="datospostulacion" type="{http://servidor.logica/}dtPostulacion" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaExtendidoConKeywordsPostulante",  propOrder = {
    "datospostulacion"
})
public class DtOfertaExtendidoConKeywordsPostulante
    extends DtOfertaExtendidoSinPConK
{

    protected DtPostulacion datospostulacion;

    /**
     * Obtiene el valor de la propiedad datospostulacion.
     * 
     * @return
     *     possible object is
     *     {@link DtPostulacion }
     *     
     */
    public DtPostulacion getDatospostulacion() {
        return datospostulacion;
    }

    /**
     * Define el valor de la propiedad datospostulacion.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPostulacion }
     *     
     */
    public void setDatospostulacion(DtPostulacion value) {
        this.datospostulacion = value;
    }

}
