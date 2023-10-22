/**
 * PaqueteBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class PaqueteBean  implements java.io.Serializable {
    private float costo;

    private java.lang.String descripcion;

    private float descuento;

    private webservice.LocalDate fechaAlta;

    private java.lang.String imagen;

    private java.lang.String nombre;

    private webservice.CantTipoPublicacionBean[] tiposDePub;

    private int validez;

    public PaqueteBean() {
    }

    public PaqueteBean(
           float costo,
           java.lang.String descripcion,
           float descuento,
           webservice.LocalDate fechaAlta,
           java.lang.String imagen,
           java.lang.String nombre,
           webservice.CantTipoPublicacionBean[] tiposDePub,
           int validez) {
           this.costo = costo;
           this.descripcion = descripcion;
           this.descuento = descuento;
           this.fechaAlta = fechaAlta;
           this.imagen = imagen;
           this.nombre = nombre;
           this.tiposDePub = tiposDePub;
           this.validez = validez;
    }


    /**
     * Gets the costo value for this PaqueteBean.
     * 
     * @return costo
     */
    public float getCosto() {
        return costo;
    }


    /**
     * Sets the costo value for this PaqueteBean.
     * 
     * @param costo
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }


    /**
     * Gets the descripcion value for this PaqueteBean.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this PaqueteBean.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the descuento value for this PaqueteBean.
     * 
     * @return descuento
     */
    public float getDescuento() {
        return descuento;
    }


    /**
     * Sets the descuento value for this PaqueteBean.
     * 
     * @param descuento
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }


    /**
     * Gets the fechaAlta value for this PaqueteBean.
     * 
     * @return fechaAlta
     */
    public webservice.LocalDate getFechaAlta() {
        return fechaAlta;
    }


    /**
     * Sets the fechaAlta value for this PaqueteBean.
     * 
     * @param fechaAlta
     */
    public void setFechaAlta(webservice.LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    /**
     * Gets the imagen value for this PaqueteBean.
     * 
     * @return imagen
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this PaqueteBean.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the nombre value for this PaqueteBean.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PaqueteBean.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tiposDePub value for this PaqueteBean.
     * 
     * @return tiposDePub
     */
    public webservice.CantTipoPublicacionBean[] getTiposDePub() {
        return tiposDePub;
    }


    /**
     * Sets the tiposDePub value for this PaqueteBean.
     * 
     * @param tiposDePub
     */
    public void setTiposDePub(webservice.CantTipoPublicacionBean[] tiposDePub) {
        this.tiposDePub = tiposDePub;
    }

    public webservice.CantTipoPublicacionBean getTiposDePub(int i) {
        return this.tiposDePub[i];
    }

    public void setTiposDePub(int i, webservice.CantTipoPublicacionBean _value) {
        this.tiposDePub[i] = _value;
    }


    /**
     * Gets the validez value for this PaqueteBean.
     * 
     * @return validez
     */
    public int getValidez() {
        return validez;
    }


    /**
     * Sets the validez value for this PaqueteBean.
     * 
     * @param validez
     */
    public void setValidez(int validez) {
        this.validez = validez;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaqueteBean)) return false;
        PaqueteBean other = (PaqueteBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.costo == other.getCosto() &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.descuento == other.getDescuento() &&
            ((this.fechaAlta==null && other.getFechaAlta()==null) || 
             (this.fechaAlta!=null &&
              this.fechaAlta.equals(other.getFechaAlta()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tiposDePub==null && other.getTiposDePub()==null) || 
             (this.tiposDePub!=null &&
              java.util.Arrays.equals(this.tiposDePub, other.getTiposDePub()))) &&
            this.validez == other.getValidez();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Float(getCosto()).hashCode();
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += new Float(getDescuento()).hashCode();
        if (getFechaAlta() != null) {
            _hashCode += getFechaAlta().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTiposDePub() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiposDePub());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiposDePub(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getValidez();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaqueteBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "paqueteBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "costo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descuento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descuento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "localDate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiposDePub");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tiposDePub"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "cantTipoPublicacionBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validez");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validez"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
