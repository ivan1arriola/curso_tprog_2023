
package webservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="listaBean">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaString" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ofertasExtendido" type="{http://webservice/}dtOfertaExtendido" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaBean", propOrder = {
    "listaString",
    "ofertasExtendido"
})
public class ListaBean {

    @XmlElement(nillable = true)
    protected List<String> listaString;
    @XmlElement(nillable = true)
    protected List<DtOfertaExtendido> ofertasExtendido;

    /**
     * Gets the value of the listaString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the listaString property.
     */
    public List<String> getListaString() {
        if (listaString == null) {
            listaString = new ArrayList<>();
        }
        return this.listaString;
    }

    /**
     * Gets the value of the ofertasExtendido property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ofertasExtendido property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfertasExtendido().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtOfertaExtendido }
     * 
     * 
     * @return
     *     The value of the ofertasExtendido property.
     */
    public List<DtOfertaExtendido> getOfertasExtendido() {
        if (ofertasExtendido == null) {
            ofertasExtendido = new ArrayList<>();
        }
        return this.ofertasExtendido;
    }

}
