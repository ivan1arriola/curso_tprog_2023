/**
 * DepUY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class DepUY implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DepUY(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Artigas = "Artigas";
    public static final java.lang.String _Salto = "Salto";
    public static final java.lang.String _Paysandú = "Paysandú";
    public static final java.lang.String _RioNegro = "RioNegro";
    public static final java.lang.String _Soriano = "Soriano";
    public static final java.lang.String _Colonia = "Colonia";
    public static final java.lang.String _Rivera = "Rivera";
    public static final java.lang.String _Tacuarembo = "Tacuarembo";
    public static final java.lang.String _Durazno = "Durazno";
    public static final java.lang.String _Flores = "Flores";
    public static final java.lang.String _Florida = "Florida";
    public static final java.lang.String _SanJosé = "SanJosé";
    public static final java.lang.String _Canelones = "Canelones";
    public static final java.lang.String _Montevideo = "Montevideo";
    public static final java.lang.String _CerroLargo = "CerroLargo";
    public static final java.lang.String _TreintaYTres = "TreintaYTres";
    public static final java.lang.String _Lavalleja = "Lavalleja";
    public static final java.lang.String _Rocha = "Rocha";
    public static final java.lang.String _Maldonado = "Maldonado";
    public static final DepUY Artigas = new DepUY(_Artigas);
    public static final DepUY Salto = new DepUY(_Salto);
    public static final DepUY Paysandú = new DepUY(_Paysandú);
    public static final DepUY RioNegro = new DepUY(_RioNegro);
    public static final DepUY Soriano = new DepUY(_Soriano);
    public static final DepUY Colonia = new DepUY(_Colonia);
    public static final DepUY Rivera = new DepUY(_Rivera);
    public static final DepUY Tacuarembo = new DepUY(_Tacuarembo);
    public static final DepUY Durazno = new DepUY(_Durazno);
    public static final DepUY Flores = new DepUY(_Flores);
    public static final DepUY Florida = new DepUY(_Florida);
    public static final DepUY SanJosé = new DepUY(_SanJosé);
    public static final DepUY Canelones = new DepUY(_Canelones);
    public static final DepUY Montevideo = new DepUY(_Montevideo);
    public static final DepUY CerroLargo = new DepUY(_CerroLargo);
    public static final DepUY TreintaYTres = new DepUY(_TreintaYTres);
    public static final DepUY Lavalleja = new DepUY(_Lavalleja);
    public static final DepUY Rocha = new DepUY(_Rocha);
    public static final DepUY Maldonado = new DepUY(_Maldonado);
    public java.lang.String getValue() { return _value_;}
    public static DepUY fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DepUY enumeration = (DepUY)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DepUY fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DepUY.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "depUY"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
