/**
 * WebServicesPortBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class WebServicesPortBindingSkeleton implements webservice.WebServices, org.apache.axis.wsdl.Skeleton {
    private webservice.WebServices impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("obtenerDatosUsuario", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice/", "usuarioBean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice/", "obtenerDatosUsuario"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("obtenerDatosUsuario") == null) {
            _myOperations.put("obtenerDatosUsuario", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("obtenerDatosUsuario")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "contraseña"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("validarCredenciales", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice/", "validarCredenciales"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("validarCredenciales") == null) {
            _myOperations.put("validarCredenciales", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("validarCredenciales")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("listarNicknamesUsuario", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://webservice/", "hashSet"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://webservice/", "listarNicknamesUsuario"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listarNicknamesUsuario") == null) {
            _myOperations.put("listarNicknamesUsuario", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listarNicknamesUsuario")).add(_oper);
    }

    public WebServicesPortBindingSkeleton() {
        this.impl = new webservice.WebServicesPortBindingImpl();
    }

    public WebServicesPortBindingSkeleton(webservice.WebServices impl) {
        this.impl = impl;
    }
    public webservice.UsuarioBean obtenerDatosUsuario(java.lang.String arg0) throws java.rmi.RemoteException
    {
        webservice.UsuarioBean ret = impl.obtenerDatosUsuario(arg0);
        return ret;
    }

    public boolean validarCredenciales(java.lang.String identificador, java.lang.String contraseña) throws java.rmi.RemoteException
    {
        boolean ret = impl.validarCredenciales(identificador, contraseña);
        return ret;
    }

    public webservice.HashSet listarNicknamesUsuario() throws java.rmi.RemoteException
    {
        webservice.HashSet ret = impl.listarNicknamesUsuario();
        return ret;
    }

}
