/**
 * UsuarioBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class UsuarioBean  implements java.io.Serializable {
    private java.lang.String apellido;

    private java.lang.String contrasenia;

    private java.lang.String correoElectronico;

    private java.lang.String descripcion;

    private java.lang.String error;

    private webservice.LocalDate fechaNac;

    private java.lang.String imagen;

    private java.lang.String nacionalidad;

    private java.lang.String nickname;

    private java.lang.String nombre;

    private webservice.OfertaLaboralBean[] ofertasLaborales;

    private webservice.PaqueteBean[] paquetes;

    private webservice.PostulacionBean[] postulaciones;

    private webservice.TipoUsuario tipo;

    private java.lang.String url;

    public UsuarioBean() {
    }

    public UsuarioBean(
           java.lang.String apellido,
           java.lang.String contrasenia,
           java.lang.String correoElectronico,
           java.lang.String descripcion,
           java.lang.String error,
           webservice.LocalDate fechaNac,
           java.lang.String imagen,
           java.lang.String nacionalidad,
           java.lang.String nickname,
           java.lang.String nombre,
           webservice.OfertaLaboralBean[] ofertasLaborales,
           webservice.PaqueteBean[] paquetes,
           webservice.PostulacionBean[] postulaciones,
           webservice.TipoUsuario tipo,
           java.lang.String url) {
           this.apellido = apellido;
           this.contrasenia = contrasenia;
           this.correoElectronico = correoElectronico;
           this.descripcion = descripcion;
           this.error = error;
           this.fechaNac = fechaNac;
           this.imagen = imagen;
           this.nacionalidad = nacionalidad;
           this.nickname = nickname;
           this.nombre = nombre;
           this.ofertasLaborales = ofertasLaborales;
           this.paquetes = paquetes;
           this.postulaciones = postulaciones;
           this.tipo = tipo;
           this.url = url;
    }


    /**
     * Gets the apellido value for this UsuarioBean.
     * 
     * @return apellido
     */
    public java.lang.String getApellido() {
        return apellido;
    }


    /**
     * Sets the apellido value for this UsuarioBean.
     * 
     * @param apellido
     */
    public void setApellido(java.lang.String apellido) {
        this.apellido = apellido;
    }


    /**
     * Gets the contrasenia value for this UsuarioBean.
     * 
     * @return contrasenia
     */
    public java.lang.String getContrasenia() {
        return contrasenia;
    }


    /**
     * Sets the contrasenia value for this UsuarioBean.
     * 
     * @param contrasenia
     */
    public void setContrasenia(java.lang.String contrasenia) {
        this.contrasenia = contrasenia;
    }


    /**
     * Gets the correoElectronico value for this UsuarioBean.
     * 
     * @return correoElectronico
     */
    public java.lang.String getCorreoElectronico() {
        return correoElectronico;
    }


    /**
     * Sets the correoElectronico value for this UsuarioBean.
     * 
     * @param correoElectronico
     */
    public void setCorreoElectronico(java.lang.String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    /**
     * Gets the descripcion value for this UsuarioBean.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this UsuarioBean.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the error value for this UsuarioBean.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this UsuarioBean.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the fechaNac value for this UsuarioBean.
     * 
     * @return fechaNac
     */
    public webservice.LocalDate getFechaNac() {
        return fechaNac;
    }


    /**
     * Sets the fechaNac value for this UsuarioBean.
     * 
     * @param fechaNac
     */
    public void setFechaNac(webservice.LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }


    /**
     * Gets the imagen value for this UsuarioBean.
     * 
     * @return imagen
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this UsuarioBean.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the nacionalidad value for this UsuarioBean.
     * 
     * @return nacionalidad
     */
    public java.lang.String getNacionalidad() {
        return nacionalidad;
    }


    /**
     * Sets the nacionalidad value for this UsuarioBean.
     * 
     * @param nacionalidad
     */
    public void setNacionalidad(java.lang.String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    /**
     * Gets the nickname value for this UsuarioBean.
     * 
     * @return nickname
     */
    public java.lang.String getNickname() {
        return nickname;
    }


    /**
     * Sets the nickname value for this UsuarioBean.
     * 
     * @param nickname
     */
    public void setNickname(java.lang.String nickname) {
        this.nickname = nickname;
    }


    /**
     * Gets the nombre value for this UsuarioBean.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this UsuarioBean.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the ofertasLaborales value for this UsuarioBean.
     * 
     * @return ofertasLaborales
     */
    public webservice.OfertaLaboralBean[] getOfertasLaborales() {
        return ofertasLaborales;
    }


    /**
     * Sets the ofertasLaborales value for this UsuarioBean.
     * 
     * @param ofertasLaborales
     */
    public void setOfertasLaborales(webservice.OfertaLaboralBean[] ofertasLaborales) {
        this.ofertasLaborales = ofertasLaborales;
    }

    public webservice.OfertaLaboralBean getOfertasLaborales(int i) {
        return this.ofertasLaborales[i];
    }

    public void setOfertasLaborales(int i, webservice.OfertaLaboralBean _value) {
        this.ofertasLaborales[i] = _value;
    }


    /**
     * Gets the paquetes value for this UsuarioBean.
     * 
     * @return paquetes
     */
    public webservice.PaqueteBean[] getPaquetes() {
        return paquetes;
    }


    /**
     * Sets the paquetes value for this UsuarioBean.
     * 
     * @param paquetes
     */
    public void setPaquetes(webservice.PaqueteBean[] paquetes) {
        this.paquetes = paquetes;
    }

    public webservice.PaqueteBean getPaquetes(int i) {
        return this.paquetes[i];
    }

    public void setPaquetes(int i, webservice.PaqueteBean _value) {
        this.paquetes[i] = _value;
    }


    /**
     * Gets the postulaciones value for this UsuarioBean.
     * 
     * @return postulaciones
     */
    public webservice.PostulacionBean[] getPostulaciones() {
        return postulaciones;
    }


    /**
     * Sets the postulaciones value for this UsuarioBean.
     * 
     * @param postulaciones
     */
    public void setPostulaciones(webservice.PostulacionBean[] postulaciones) {
        this.postulaciones = postulaciones;
    }

    public webservice.PostulacionBean getPostulaciones(int i) {
        return this.postulaciones[i];
    }

    public void setPostulaciones(int i, webservice.PostulacionBean _value) {
        this.postulaciones[i] = _value;
    }


    /**
     * Gets the tipo value for this UsuarioBean.
     * 
     * @return tipo
     */
    public webservice.TipoUsuario getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this UsuarioBean.
     * 
     * @param tipo
     */
    public void setTipo(webservice.TipoUsuario tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the url value for this UsuarioBean.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this UsuarioBean.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsuarioBean)) return false;
        UsuarioBean other = (UsuarioBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.apellido==null && other.getApellido()==null) || 
             (this.apellido!=null &&
              this.apellido.equals(other.getApellido()))) &&
            ((this.contrasenia==null && other.getContrasenia()==null) || 
             (this.contrasenia!=null &&
              this.contrasenia.equals(other.getContrasenia()))) &&
            ((this.correoElectronico==null && other.getCorreoElectronico()==null) || 
             (this.correoElectronico!=null &&
              this.correoElectronico.equals(other.getCorreoElectronico()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.fechaNac==null && other.getFechaNac()==null) || 
             (this.fechaNac!=null &&
              this.fechaNac.equals(other.getFechaNac()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.nacionalidad==null && other.getNacionalidad()==null) || 
             (this.nacionalidad!=null &&
              this.nacionalidad.equals(other.getNacionalidad()))) &&
            ((this.nickname==null && other.getNickname()==null) || 
             (this.nickname!=null &&
              this.nickname.equals(other.getNickname()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.ofertasLaborales==null && other.getOfertasLaborales()==null) || 
             (this.ofertasLaborales!=null &&
              java.util.Arrays.equals(this.ofertasLaborales, other.getOfertasLaborales()))) &&
            ((this.paquetes==null && other.getPaquetes()==null) || 
             (this.paquetes!=null &&
              java.util.Arrays.equals(this.paquetes, other.getPaquetes()))) &&
            ((this.postulaciones==null && other.getPostulaciones()==null) || 
             (this.postulaciones!=null &&
              java.util.Arrays.equals(this.postulaciones, other.getPostulaciones()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl())));
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
        if (getApellido() != null) {
            _hashCode += getApellido().hashCode();
        }
        if (getContrasenia() != null) {
            _hashCode += getContrasenia().hashCode();
        }
        if (getCorreoElectronico() != null) {
            _hashCode += getCorreoElectronico().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getFechaNac() != null) {
            _hashCode += getFechaNac().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getNacionalidad() != null) {
            _hashCode += getNacionalidad().hashCode();
        }
        if (getNickname() != null) {
            _hashCode += getNickname().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getOfertasLaborales() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOfertasLaborales());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOfertasLaborales(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaquetes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaquetes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaquetes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPostulaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPostulaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPostulaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UsuarioBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "usuarioBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contrasenia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contrasenia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correoElectronico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correoElectronico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaNac"));
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
        elemField.setFieldName("nacionalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nacionalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nickname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nickname"));
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
        elemField.setFieldName("ofertasLaborales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ofertasLaborales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "ofertaLaboralBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paquetes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paquetes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "paqueteBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postulaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postulaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "postulacionBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "tipoUsuario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
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
