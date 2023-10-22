
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombrePostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://webservice/}localDate" minOccurs="0"/>
 *         <element name="uRLDocExtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cVitae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="motivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "nombrePostulante",
    "fecha",
    "urlDocExtras",
    "cVitae",
    "motivacion"
})
public class DtPostulacion {

    protected String nombrePostulante;
    protected LocalDate fecha;
    @XmlElement(name = "uRLDocExtras")
    protected String urlDocExtras;
    protected String cVitae;
    protected String motivacion;

    /**
     * Obtiene el valor de la propiedad nombrePostulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePostulante() {
        return nombrePostulante;
    }

    /**
     * Define el valor de la propiedad nombrePostulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePostulante(String value) {
        this.nombrePostulante = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad urlDocExtras.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURLDocExtras() {
        return urlDocExtras;
    }

    /**
     * Define el valor de la propiedad urlDocExtras.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURLDocExtras(String value) {
        this.urlDocExtras = value;
    }

    /**
     * Obtiene el valor de la propiedad cVitae.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVitae() {
        return cVitae;
    }

    /**
     * Define el valor de la propiedad cVitae.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVitae(String value) {
        this.cVitae = value;
    }

    /**
     * Obtiene el valor de la propiedad motivacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivacion() {
        return motivacion;
    }

    /**
     * Define el valor de la propiedad motivacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivacion(String value) {
        this.motivacion = value;
    }

}
