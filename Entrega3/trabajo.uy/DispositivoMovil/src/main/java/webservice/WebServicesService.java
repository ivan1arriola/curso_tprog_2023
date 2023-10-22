/**
 * WebServicesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface WebServicesService extends javax.xml.rpc.Service {
    public java.lang.String getWebServicesPortAddress();

    public webservice.WebServices getWebServicesPort() throws javax.xml.rpc.ServiceException;

    public webservice.WebServices getWebServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
