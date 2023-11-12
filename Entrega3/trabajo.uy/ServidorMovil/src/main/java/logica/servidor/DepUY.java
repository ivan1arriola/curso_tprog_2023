
package logica.servidor;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para depUY.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="depUY">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Artigas"/>
 *     <enumeration value="Salto"/>
 *     <enumeration value="Paysandu"/>
 *     <enumeration value="RioNegro"/>
 *     <enumeration value="Soriano"/>
 *     <enumeration value="Colonia"/>
 *     <enumeration value="Rivera"/>
 *     <enumeration value="Tacuarembo"/>
 *     <enumeration value="Durazno"/>
 *     <enumeration value="Flores"/>
 *     <enumeration value="Florida"/>
 *     <enumeration value="SanJose"/>
 *     <enumeration value="Canelones"/>
 *     <enumeration value="Montevideo"/>
 *     <enumeration value="CerroLargo"/>
 *     <enumeration value="TreintaYTres"/>
 *     <enumeration value="Lavalleja"/>
 *     <enumeration value="Rocha"/>
 *     <enumeration value="Maldonado"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "depUY")
@XmlEnum
public enum DepUY {

    @XmlEnumValue("Artigas")
    ARTIGAS("Artigas"), 
    @XmlEnumValue("Salto")
    SALTO("Salto"), 
    @XmlEnumValue("Paysandu")
    PAYSANDU("Paysandu"), 
    @XmlEnumValue("RioNegro")
    RIO_NEGRO("RioNegro"), 
    @XmlEnumValue("Soriano")
    SORIANO("Soriano"), 
    @XmlEnumValue("Colonia")
    COLONIA("Colonia"), 
    @XmlEnumValue("Rivera")
    RIVERA("Rivera"), 
    @XmlEnumValue("Tacuarembo")
    TACUAREMBO("Tacuarembo"), 
    @XmlEnumValue("Durazno")
    DURAZNO("Durazno"), 
    @XmlEnumValue("Flores")
    FLORES("Flores"), 
    @XmlEnumValue("Florida")
    FLORIDA("Florida"), 
    @XmlEnumValue("SanJose")
    SAN_JOSE("SanJose"), 
    @XmlEnumValue("Canelones")
    CANELONES("Canelones"), 
    @XmlEnumValue("Montevideo")
    MONTEVIDEO("Montevideo"), 
    @XmlEnumValue("CerroLargo")
    CERRO_LARGO("CerroLargo"), 
    @XmlEnumValue("TreintaYTres")
    TREINTA_Y_TRES("TreintaYTres"), 
    @XmlEnumValue("Lavalleja")
    LAVALLEJA("Lavalleja"), 
    @XmlEnumValue("Rocha")
    ROCHA("Rocha"), 
    @XmlEnumValue("Maldonado")
    MALDONADO("Maldonado");
    private final String value;

    DepUY(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DepUY fromValue(String v) {
        for (DepUY c: DepUY.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
