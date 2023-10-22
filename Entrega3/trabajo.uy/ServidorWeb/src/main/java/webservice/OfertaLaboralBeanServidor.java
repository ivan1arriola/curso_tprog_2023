
package webservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ofertaLaboralBeanServidor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="ofertaLaboralBeanServidor">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaDeAlta" type="{http://webservice/}dateBean" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="horario" type="{http://webservice/}dtHorario" minOccurs="0"/>
 *         <element name="departamento" type="{http://webservice/}depUY" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estado" type="{http://webservice/}estadoOL" minOccurs="0"/>
 *         <element name="postulantes" type="{http://webservice/}usuarioBeanServidor" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://webservice/}paqueteBeanServidor" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="nicknameEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="mostrarPostulantes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="mostrarPaquete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ofertaLaboralBeanServidor", propOrder = {
    "nombre",
    "descripcion",
    "fechaDeAlta",
    "costo",
    "remuneracion",
    "horario",
    "departamento",
    "ciudad",
    "estado",
    "postulantes",
    "imagen",
    "paq",
    "paquete",
    "keywords",
    "nicknameEmpresa",
    "mostrarPostulantes",
    "mostrarPaquete"
})
public class OfertaLaboralBeanServidor {

    protected String nombre;
    protected String descripcion;
    protected DateBean fechaDeAlta;
    protected float costo;
    protected float remuneracion;
    protected DtHorario horario;
    @XmlSchemaType(name = "string")
    protected DepUY departamento;
    protected String ciudad;
    @XmlSchemaType(name = "string")
    protected EstadoOL estado;
    @XmlElement(nillable = true)
    protected List<UsuarioBeanServidor> postulantes;
    protected String imagen;
    protected String paq;
    protected PaqueteBeanServidor paquete;
    @XmlElement(nillable = true)
    protected List<String> keywords;
    protected String nicknameEmpresa;
    protected boolean mostrarPostulantes;
    protected boolean mostrarPaquete;

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
     * Obtiene el valor de la propiedad fechaDeAlta.
     * 
     * @return
     *     possible object is
     *     {@link DateBean }
     *     
     */
    public DateBean getFechaDeAlta() {
        return fechaDeAlta;
    }

    /**
     * Define el valor de la propiedad fechaDeAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link DateBean }
     *     
     */
    public void setFechaDeAlta(DateBean value) {
        this.fechaDeAlta = value;
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
     * Obtiene el valor de la propiedad remuneracion.
     * 
     */
    public float getRemuneracion() {
        return remuneracion;
    }

    /**
     * Define el valor de la propiedad remuneracion.
     * 
     */
    public void setRemuneracion(float value) {
        this.remuneracion = value;
    }

    /**
     * Obtiene el valor de la propiedad horario.
     * 
     * @return
     *     possible object is
     *     {@link DtHorario }
     *     
     */
    public DtHorario getHorario() {
        return horario;
    }

    /**
     * Define el valor de la propiedad horario.
     * 
     * @param value
     *     allowed object is
     *     {@link DtHorario }
     *     
     */
    public void setHorario(DtHorario value) {
        this.horario = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link DepUY }
     *     
     */
    public DepUY getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link DepUY }
     *     
     */
    public void setDepartamento(DepUY value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Define el valor de la propiedad ciudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoOL }
     *     
     */
    public EstadoOL getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoOL }
     *     
     */
    public void setEstado(EstadoOL value) {
        this.estado = value;
    }

    /**
     * Gets the value of the postulantes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the postulantes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostulantes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsuarioBeanServidor }
     * 
     * 
     * @return
     *     The value of the postulantes property.
     */
    public List<UsuarioBeanServidor> getPostulantes() {
        if (postulantes == null) {
            postulantes = new ArrayList<>();
        }
        return this.postulantes;
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

    /**
     * Obtiene el valor de la propiedad paq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaq() {
        return paq;
    }

    /**
     * Define el valor de la propiedad paq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaq(String value) {
        this.paq = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link PaqueteBeanServidor }
     *     
     */
    public PaqueteBeanServidor getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link PaqueteBeanServidor }
     *     
     */
    public void setPaquete(PaqueteBeanServidor value) {
        this.paquete = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the keywords property.
     */
    public List<String> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        return this.keywords;
    }

    /**
     * Obtiene el valor de la propiedad nicknameEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameEmpresa() {
        return nicknameEmpresa;
    }

    /**
     * Define el valor de la propiedad nicknameEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameEmpresa(String value) {
        this.nicknameEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad mostrarPostulantes.
     * 
     */
    public boolean isMostrarPostulantes() {
        return mostrarPostulantes;
    }

    /**
     * Define el valor de la propiedad mostrarPostulantes.
     * 
     */
    public void setMostrarPostulantes(boolean value) {
        this.mostrarPostulantes = value;
    }

    /**
     * Obtiene el valor de la propiedad mostrarPaquete.
     * 
     */
    public boolean isMostrarPaquete() {
        return mostrarPaquete;
    }

    /**
     * Define el valor de la propiedad mostrarPaquete.
     * 
     */
    public void setMostrarPaquete(boolean value) {
        this.mostrarPaquete = value;
    }

}
