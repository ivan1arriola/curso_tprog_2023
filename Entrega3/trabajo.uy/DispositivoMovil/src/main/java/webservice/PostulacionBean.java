/**
 * PostulacionBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class PostulacionBean  implements java.io.Serializable {
    private java.lang.String CVitae;

    private webservice.LocalDate fecha;

    private java.lang.String motivacion;

    private java.lang.String nicknamePostulante;

    private java.lang.String nombreOfertaLaboral;

    private java.lang.String URLDocExtras;

    public PostulacionBean() {
    }

    public PostulacionBean(
           java.lang.String CVitae,
           webservice.LocalDate fecha,
           java.lang.String motivacion,
           java.lang.String nicknamePostulante,
           java.lang.String nombreOfertaLaboral,
           java.lang.String URLDocExtras) {
           this.CVitae = CVitae;
           this.fecha = fecha;
           this.motivacion = motivacion;
           this.nicknamePostulante = nicknamePostulante;
           this.nombreOfertaLaboral = nombreOfertaLaboral;
           this.URLDocExtras = URLDocExtras;
    }


    /**
     * Gets the CVitae value for this PostulacionBean.
     * 
     * @return CVitae
     */
    public java.lang.String getCVitae() {
        return CVitae;
    }


    /**
     * Sets the CVitae value for this PostulacionBean.
     * 
     * @param CVitae
     */
    public void setCVitae(java.lang.String CVitae) {
        this.CVitae = CVitae;
    }


    /**
     * Gets the fecha value for this PostulacionBean.
     * 
     * @return fecha
     */
    public webservice.LocalDate getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this PostulacionBean.
     * 
     * @param fecha
     */
    public void setFecha(webservice.LocalDate fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the motivacion value for this PostulacionBean.
     * 
     * @return motivacion
     */
    public java.lang.String getMotivacion() {
        return motivacion;
    }


    /**
     * Sets the motivacion value for this PostulacionBean.
     * 
     * @param motivacion
     */
    public void setMotivacion(java.lang.String motivacion) {
        this.motivacion = motivacion;
    }


    /**
     * Gets the nicknamePostulante value for this PostulacionBean.
     * 
     * @return nicknamePostulante
     */
    public java.lang.String getNicknamePostulante() {
        return nicknamePostulante;
    }


    /**
     * Sets the nicknamePostulante value for this PostulacionBean.
     * 
     * @param nicknamePostulante
     */
    public void setNicknamePostulante(java.lang.String nicknamePostulante) {
        this.nicknamePostulante = nicknamePostulante;
    }


    /**
     * Gets the nombreOfertaLaboral value for this PostulacionBean.
     * 
     * @return nombreOfertaLaboral
     */
    public java.lang.String getNombreOfertaLaboral() {
        return nombreOfertaLaboral;
    }


    /**
     * Sets the nombreOfertaLaboral value for this PostulacionBean.
     * 
     * @param nombreOfertaLaboral
     */
    public void setNombreOfertaLaboral(java.lang.String nombreOfertaLaboral) {
        this.nombreOfertaLaboral = nombreOfertaLaboral;
    }


    /**
     * Gets the URLDocExtras value for this PostulacionBean.
     * 
     * @return URLDocExtras
     */
    public java.lang.String getURLDocExtras() {
        return URLDocExtras;
    }


    /**
     * Sets the URLDocExtras value for this PostulacionBean.
     * 
     * @param URLDocExtras
     */
    public void setURLDocExtras(java.lang.String URLDocExtras) {
        this.URLDocExtras = URLDocExtras;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostulacionBean)) return false;
        PostulacionBean other = (PostulacionBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CVitae==null && other.getCVitae()==null) || 
             (this.CVitae!=null &&
              this.CVitae.equals(other.getCVitae()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.motivacion==null && other.getMotivacion()==null) || 
             (this.motivacion!=null &&
              this.motivacion.equals(other.getMotivacion()))) &&
            ((this.nicknamePostulante==null && other.getNicknamePostulante()==null) || 
             (this.nicknamePostulante!=null &&
              this.nicknamePostulante.equals(other.getNicknamePostulante()))) &&
            ((this.nombreOfertaLaboral==null && other.getNombreOfertaLaboral()==null) || 
             (this.nombreOfertaLaboral!=null &&
              this.nombreOfertaLaboral.equals(other.getNombreOfertaLaboral()))) &&
            ((this.URLDocExtras==null && other.getURLDocExtras()==null) || 
             (this.URLDocExtras!=null &&
              this.URLDocExtras.equals(other.getURLDocExtras())));
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
        if (getCVitae() != null) {
            _hashCode += getCVitae().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getMotivacion() != null) {
            _hashCode += getMotivacion().hashCode();
        }
        if (getNicknamePostulante() != null) {
            _hashCode += getNicknamePostulante().hashCode();
        }
        if (getNombreOfertaLaboral() != null) {
            _hashCode += getNombreOfertaLaboral().hashCode();
        }
        if (getURLDocExtras() != null) {
            _hashCode += getURLDocExtras().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostulacionBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "postulacionBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CVitae");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CVitae"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "localDate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "motivacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nicknamePostulante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nicknamePostulante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreOfertaLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreOfertaLaboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URLDocExtras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "URLDocExtras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
