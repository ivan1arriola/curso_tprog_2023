
package logica.servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOfertaExtendido complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtOfertaExtendido">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaDeAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulaciones" type="{http://servidor.logica/}arrayList" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaExtendido", propOrder = {
    "fechaDeAlta",
    "postulaciones"
})
public class DtOfertaExtendido {

    protected String fechaDeAlta;
    protected ArrayList postulaciones;

    /**
     * Obtiene el valor de la propiedad fechaDeAlta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeAlta() {
        return fechaDeAlta;
    }

    /**
     * Define el valor de la propiedad fechaDeAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeAlta(String value) {
        this.fechaDeAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad postulaciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayList }
     *     
     */
    public ArrayList getPostulaciones() {
        return postulaciones;
    }

    /**
     * Define el valor de la propiedad postulaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayList }
     *     
     */
    public void setPostulaciones(ArrayList value) {
        this.postulaciones = value;
    }

}
