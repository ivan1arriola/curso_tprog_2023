
package logica.servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulante">
 *   <complexContent>
 *     <extension base="{http://servidor.logica/}dtUsuario">
 *       <sequence>
 *         <element name="fechanac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="favs" type="{http://servidor.logica/}dtOfertaExtendido" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="fechaNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulante",  propOrder = {
    "fechanac", 
    "nacionalidad", 
    "favs", 
    "fechaNac"
})
public class DtPostulante
    extends DtUsuario
{

    protected String fechanac;
    protected String nacionalidad;
    @XmlElement(nillable = true)
    protected List<DtOfertaExtendido> favs;
    protected String fechaNac;

    /**
     * Obtiene el valor de la propiedad fechanac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanac() {
        return fechanac;
    }

    /**
     * Define el valor de la propiedad fechanac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanac(String value) {
        this.fechanac = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the favs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, 
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the favs property.
     * 
     * <p>
     * For example,  to add a new item,  do as follows:
     * <pre>
     *    getFavs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtOfertaExtendido }
     * 
     * 
     * @return
     *     The value of the favs property.
     */
    public List<DtOfertaExtendido> getFavs() {
        if (favs == null) {
            favs = new ArrayList<>();
        }
        return this.favs;
    }

    /**
     * Obtiene el valor de la propiedad fechaNac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Define el valor de la propiedad fechaNac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNac(String value) {
        this.fechaNac = value;
    }

}
