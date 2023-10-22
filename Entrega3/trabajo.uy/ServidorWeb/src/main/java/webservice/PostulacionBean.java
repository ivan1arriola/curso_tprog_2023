
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para postulacionBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="postulacionBean">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nicknamePostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://webservice/}localDate" minOccurs="0"/>
 *         <element name="uRLDocExtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cVitae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="motivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreOfertaLaboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postulacionBean", propOrder = {
    "nicknamePostulante",
    "fecha",
    "urlDocExtras",
    "cVitae",
    "motivacion",
    "nombreOfertaLaboral"
})
public class PostulacionBean {

    protected String nicknamePostulante;
    protected LocalDate fecha;
    @XmlElement(name = "uRLDocExtras")
    protected String urlDocExtras;
    protected String cVitae;
    protected String motivacion;
    protected String nombreOfertaLaboral;

    /**
     * Obtiene el valor de la propiedad nicknamePostulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknamePostulante() {
        return nicknamePostulante;
    }

    /**
     * Define el valor de la propiedad nicknamePostulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknamePostulante(String value) {
        this.nicknamePostulante = value;
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

    /**
     * Obtiene el valor de la propiedad nombreOfertaLaboral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOfertaLaboral() {
        return nombreOfertaLaboral;
    }

    /**
     * Define el valor de la propiedad nombreOfertaLaboral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOfertaLaboral(String value) {
        this.nombreOfertaLaboral = value;
    }

}
