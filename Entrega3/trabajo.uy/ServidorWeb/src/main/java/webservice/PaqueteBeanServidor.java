
package webservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para paqueteBeanServidor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="paqueteBeanServidor">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="descuento" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="validez" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tiposDePub" type="{http://webservice/}cantTipoPublicacionBeanServidor" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="fechaA" type="{http://webservice/}dateBean" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paqueteBeanServidor", propOrder = {
    "nombre",
    "costo",
    "descuento",
    "validez",
    "descripcion",
    "tiposDePub",
    "fechaA",
    "imagen"
})
public class PaqueteBeanServidor {

    protected String nombre;
    protected float costo;
    protected float descuento;
    protected int validez;
    protected String descripcion;
    @XmlElement(nillable = true)
    protected List<CantTipoPublicacionBeanServidor> tiposDePub;
    protected DateBean fechaA;
    protected String imagen;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(float value) {
        this.descuento = value;
    }

    /**
     * Obtiene el valor de la propiedad validez.
     * 
     */
    public int getValidez() {
        return validez;
    }

    /**
     * Define el valor de la propiedad validez.
     * 
     */
    public void setValidez(int value) {
        this.validez = value;
    }

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
     * Gets the value of the tiposDePub property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the tiposDePub property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTiposDePub().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CantTipoPublicacionBeanServidor }
     * 
     * 
     * @return
     *     The value of the tiposDePub property.
     */
    public List<CantTipoPublicacionBeanServidor> getTiposDePub() {
        if (tiposDePub == null) {
            tiposDePub = new ArrayList<>();
        }
        return this.tiposDePub;
    }

    /**
     * Obtiene el valor de la propiedad fechaA.
     * 
     * @return
     *     possible object is
     *     {@link DateBean }
     *     
     */
    public DateBean getFechaA() {
        return fechaA;
    }

    /**
     * Define el valor de la propiedad fechaA.
     * 
     * @param value
     *     allowed object is
     *     {@link DateBean }
     *     
     */
    public void setFechaA(DateBean value) {
        this.fechaA = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

}
