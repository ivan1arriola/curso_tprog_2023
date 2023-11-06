
package logica.servidor;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import javabeans.OfertaLaboralBean;


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
@XmlType(name = "dtPostulante", propOrder = {
    "fechanac",
    "nacionalidad",
    "fechaNac"
})
public class DtPostulante
    extends DtUsuario
{

    protected String fechanac;
    protected String nacionalidad;
    protected String fechaNac;
    protected Set<OfertaLaboralBean> oferFavs;

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

	public Set<OfertaLaboralBean> getOfertasFavoritas() {
		return oferFavs;
	}

	public void setOfertasFavoritas(Set<OfertaLaboralBean> oferF) {
		oferFavs = oferF;
	}

	
}
