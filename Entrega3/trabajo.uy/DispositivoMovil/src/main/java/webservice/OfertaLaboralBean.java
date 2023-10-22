/**
 * OfertaLaboralBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class OfertaLaboralBean  implements java.io.Serializable {
    private java.lang.String ciudad;

    private float costo;

    private webservice.DepUY departamento;

    private java.lang.String descripcion;

    private webservice.EstadoOL estado;

    private webservice.LocalDate fechaDeAlta;

    private webservice.DtHorario horario;

    private java.lang.String imagen;

    private java.lang.String[] keywords;

    private boolean mostrarPaquete;

    private boolean mostrarPostulantes;

    private java.lang.String nicknameEmpresa;

    private java.lang.String nombre;

    private java.lang.String paq;

    private webservice.PaqueteBean paquete;

    private webservice.UsuarioBean[] postulantes;

    private float remuneracion;

    public OfertaLaboralBean() {
    }

    public OfertaLaboralBean(
           java.lang.String ciudad,
           float costo,
           webservice.DepUY departamento,
           java.lang.String descripcion,
           webservice.EstadoOL estado,
           webservice.LocalDate fechaDeAlta,
           webservice.DtHorario horario,
           java.lang.String imagen,
           java.lang.String[] keywords,
           boolean mostrarPaquete,
           boolean mostrarPostulantes,
           java.lang.String nicknameEmpresa,
           java.lang.String nombre,
           java.lang.String paq,
           webservice.PaqueteBean paquete,
           webservice.UsuarioBean[] postulantes,
           float remuneracion) {
           this.ciudad = ciudad;
           this.costo = costo;
           this.departamento = departamento;
           this.descripcion = descripcion;
           this.estado = estado;
           this.fechaDeAlta = fechaDeAlta;
           this.horario = horario;
           this.imagen = imagen;
           this.keywords = keywords;
           this.mostrarPaquete = mostrarPaquete;
           this.mostrarPostulantes = mostrarPostulantes;
           this.nicknameEmpresa = nicknameEmpresa;
           this.nombre = nombre;
           this.paq = paq;
           this.paquete = paquete;
           this.postulantes = postulantes;
           this.remuneracion = remuneracion;
    }


    /**
     * Gets the ciudad value for this OfertaLaboralBean.
     * 
     * @return ciudad
     */
    public java.lang.String getCiudad() {
        return ciudad;
    }


    /**
     * Sets the ciudad value for this OfertaLaboralBean.
     * 
     * @param ciudad
     */
    public void setCiudad(java.lang.String ciudad) {
        this.ciudad = ciudad;
    }


    /**
     * Gets the costo value for this OfertaLaboralBean.
     * 
     * @return costo
     */
    public float getCosto() {
        return costo;
    }


    /**
     * Sets the costo value for this OfertaLaboralBean.
     * 
     * @param costo
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }


    /**
     * Gets the departamento value for this OfertaLaboralBean.
     * 
     * @return departamento
     */
    public webservice.DepUY getDepartamento() {
        return departamento;
    }


    /**
     * Sets the departamento value for this OfertaLaboralBean.
     * 
     * @param departamento
     */
    public void setDepartamento(webservice.DepUY departamento) {
        this.departamento = departamento;
    }


    /**
     * Gets the descripcion value for this OfertaLaboralBean.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this OfertaLaboralBean.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the estado value for this OfertaLaboralBean.
     * 
     * @return estado
     */
    public webservice.EstadoOL getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this OfertaLaboralBean.
     * 
     * @param estado
     */
    public void setEstado(webservice.EstadoOL estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechaDeAlta value for this OfertaLaboralBean.
     * 
     * @return fechaDeAlta
     */
    public webservice.LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }


    /**
     * Sets the fechaDeAlta value for this OfertaLaboralBean.
     * 
     * @param fechaDeAlta
     */
    public void setFechaDeAlta(webservice.LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }


    /**
     * Gets the horario value for this OfertaLaboralBean.
     * 
     * @return horario
     */
    public webservice.DtHorario getHorario() {
        return horario;
    }


    /**
     * Sets the horario value for this OfertaLaboralBean.
     * 
     * @param horario
     */
    public void setHorario(webservice.DtHorario horario) {
        this.horario = horario;
    }


    /**
     * Gets the imagen value for this OfertaLaboralBean.
     * 
     * @return imagen
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this OfertaLaboralBean.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the keywords value for this OfertaLaboralBean.
     * 
     * @return keywords
     */
    public java.lang.String[] getKeywords() {
        return keywords;
    }


    /**
     * Sets the keywords value for this OfertaLaboralBean.
     * 
     * @param keywords
     */
    public void setKeywords(java.lang.String[] keywords) {
        this.keywords = keywords;
    }

    public java.lang.String getKeywords(int i) {
        return this.keywords[i];
    }

    public void setKeywords(int i, java.lang.String _value) {
        this.keywords[i] = _value;
    }


    /**
     * Gets the mostrarPaquete value for this OfertaLaboralBean.
     * 
     * @return mostrarPaquete
     */
    public boolean isMostrarPaquete() {
        return mostrarPaquete;
    }


    /**
     * Sets the mostrarPaquete value for this OfertaLaboralBean.
     * 
     * @param mostrarPaquete
     */
    public void setMostrarPaquete(boolean mostrarPaquete) {
        this.mostrarPaquete = mostrarPaquete;
    }


    /**
     * Gets the mostrarPostulantes value for this OfertaLaboralBean.
     * 
     * @return mostrarPostulantes
     */
    public boolean isMostrarPostulantes() {
        return mostrarPostulantes;
    }


    /**
     * Sets the mostrarPostulantes value for this OfertaLaboralBean.
     * 
     * @param mostrarPostulantes
     */
    public void setMostrarPostulantes(boolean mostrarPostulantes) {
        this.mostrarPostulantes = mostrarPostulantes;
    }


    /**
     * Gets the nicknameEmpresa value for this OfertaLaboralBean.
     * 
     * @return nicknameEmpresa
     */
    public java.lang.String getNicknameEmpresa() {
        return nicknameEmpresa;
    }


    /**
     * Sets the nicknameEmpresa value for this OfertaLaboralBean.
     * 
     * @param nicknameEmpresa
     */
    public void setNicknameEmpresa(java.lang.String nicknameEmpresa) {
        this.nicknameEmpresa = nicknameEmpresa;
    }


    /**
     * Gets the nombre value for this OfertaLaboralBean.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this OfertaLaboralBean.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the paq value for this OfertaLaboralBean.
     * 
     * @return paq
     */
    public java.lang.String getPaq() {
        return paq;
    }


    /**
     * Sets the paq value for this OfertaLaboralBean.
     * 
     * @param paq
     */
    public void setPaq(java.lang.String paq) {
        this.paq = paq;
    }


    /**
     * Gets the paquete value for this OfertaLaboralBean.
     * 
     * @return paquete
     */
    public webservice.PaqueteBean getPaquete() {
        return paquete;
    }


    /**
     * Sets the paquete value for this OfertaLaboralBean.
     * 
     * @param paquete
     */
    public void setPaquete(webservice.PaqueteBean paquete) {
        this.paquete = paquete;
    }


    /**
     * Gets the postulantes value for this OfertaLaboralBean.
     * 
     * @return postulantes
     */
    public webservice.UsuarioBean[] getPostulantes() {
        return postulantes;
    }


    /**
     * Sets the postulantes value for this OfertaLaboralBean.
     * 
     * @param postulantes
     */
    public void setPostulantes(webservice.UsuarioBean[] postulantes) {
        this.postulantes = postulantes;
    }

    public webservice.UsuarioBean getPostulantes(int i) {
        return this.postulantes[i];
    }

    public void setPostulantes(int i, webservice.UsuarioBean _value) {
        this.postulantes[i] = _value;
    }


    /**
     * Gets the remuneracion value for this OfertaLaboralBean.
     * 
     * @return remuneracion
     */
    public float getRemuneracion() {
        return remuneracion;
    }


    /**
     * Sets the remuneracion value for this OfertaLaboralBean.
     * 
     * @param remuneracion
     */
    public void setRemuneracion(float remuneracion) {
        this.remuneracion = remuneracion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OfertaLaboralBean)) return false;
        OfertaLaboralBean other = (OfertaLaboralBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ciudad==null && other.getCiudad()==null) || 
             (this.ciudad!=null &&
              this.ciudad.equals(other.getCiudad()))) &&
            this.costo == other.getCosto() &&
            ((this.departamento==null && other.getDepartamento()==null) || 
             (this.departamento!=null &&
              this.departamento.equals(other.getDepartamento()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fechaDeAlta==null && other.getFechaDeAlta()==null) || 
             (this.fechaDeAlta!=null &&
              this.fechaDeAlta.equals(other.getFechaDeAlta()))) &&
            ((this.horario==null && other.getHorario()==null) || 
             (this.horario!=null &&
              this.horario.equals(other.getHorario()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.keywords==null && other.getKeywords()==null) || 
             (this.keywords!=null &&
              java.util.Arrays.equals(this.keywords, other.getKeywords()))) &&
            this.mostrarPaquete == other.isMostrarPaquete() &&
            this.mostrarPostulantes == other.isMostrarPostulantes() &&
            ((this.nicknameEmpresa==null && other.getNicknameEmpresa()==null) || 
             (this.nicknameEmpresa!=null &&
              this.nicknameEmpresa.equals(other.getNicknameEmpresa()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.paq==null && other.getPaq()==null) || 
             (this.paq!=null &&
              this.paq.equals(other.getPaq()))) &&
            ((this.paquete==null && other.getPaquete()==null) || 
             (this.paquete!=null &&
              this.paquete.equals(other.getPaquete()))) &&
            ((this.postulantes==null && other.getPostulantes()==null) || 
             (this.postulantes!=null &&
              java.util.Arrays.equals(this.postulantes, other.getPostulantes()))) &&
            this.remuneracion == other.getRemuneracion();
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
        if (getCiudad() != null) {
            _hashCode += getCiudad().hashCode();
        }
        _hashCode += new Float(getCosto()).hashCode();
        if (getDepartamento() != null) {
            _hashCode += getDepartamento().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFechaDeAlta() != null) {
            _hashCode += getFechaDeAlta().hashCode();
        }
        if (getHorario() != null) {
            _hashCode += getHorario().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getKeywords() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeywords());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeywords(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isMostrarPaquete() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isMostrarPostulantes() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNicknameEmpresa() != null) {
            _hashCode += getNicknameEmpresa().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getPaq() != null) {
            _hashCode += getPaq().hashCode();
        }
        if (getPaquete() != null) {
            _hashCode += getPaquete().hashCode();
        }
        if (getPostulantes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPostulantes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPostulantes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Float(getRemuneracion()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OfertaLaboralBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "ofertaLaboralBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ciudad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "costo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "departamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "depUY"));
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
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "estadoOL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDeAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaDeAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "localDate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "horario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "dtHorario"));
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
        elemField.setFieldName("keywords");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keywords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarPaquete");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mostrarPaquete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarPostulantes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mostrarPostulantes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nicknameEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nicknameEmpresa"));
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
        elemField.setFieldName("paq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paquete");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paquete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "paqueteBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postulantes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postulantes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservice/", "usuarioBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remuneracion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "remuneracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
