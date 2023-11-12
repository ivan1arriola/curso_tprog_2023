
package logica.servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtEmpresa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtEmpresa">
 *   <complexContent>
 *     <extension base="{http://servidor.logica/}dtUsuario">
 *       <sequence>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ofertasLaborales" type="{http://servidor.logica/}arrayList" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtEmpresa",  propOrder = {
    "descripcion", 
    "url", 
    "ofertasLaborales"
})
public class DtEmpresa
    extends DtUsuario
{

    protected String descripcion;
    protected String url;
    protected ArrayList ofertasLaborales;

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad ofertasLaborales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayList }
     *     
     */
    public ArrayList getOfertasLaborales() {
        return ofertasLaborales;
    }

    /**
     * Define el valor de la propiedad ofertasLaborales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayList }
     *     
     */
    public void setOfertasLaborales(ArrayList value) {
        this.ofertasLaborales = value;
    }

}
