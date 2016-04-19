/**
 * CompanyServiceSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osiristher.webapp.provider.soap.portal.company;

public class CompanyServiceSoapServiceLocator extends org.apache.axis.client.Service implements com.osiristher.webapp.provider.soap.portal.company.CompanyServiceSoapService {

    public CompanyServiceSoapServiceLocator() {
    }


    public CompanyServiceSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CompanyServiceSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Portal_CompanyService
    private java.lang.String Portal_CompanyService_address = "http://localhost:8008/api/axis/Portal_CompanyService";

    public java.lang.String getPortal_CompanyServiceAddress() {
        return Portal_CompanyService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Portal_CompanyServiceWSDDServiceName = "Portal_CompanyService";

    public java.lang.String getPortal_CompanyServiceWSDDServiceName() {
        return Portal_CompanyServiceWSDDServiceName;
    }

    public void setPortal_CompanyServiceWSDDServiceName(java.lang.String name) {
        Portal_CompanyServiceWSDDServiceName = name;
    }

    public com.osiristher.webapp.provider.soap.portal.company.CompanyServiceSoap getPortal_CompanyService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Portal_CompanyService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPortal_CompanyService(endpoint);
    }

    public com.osiristher.webapp.provider.soap.portal.company.CompanyServiceSoap getPortal_CompanyService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osiristher.webapp.provider.soap.portal.company.Portal_CompanyServiceSoapBindingStub _stub = new com.osiristher.webapp.provider.soap.portal.company.Portal_CompanyServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPortal_CompanyServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPortal_CompanyServiceEndpointAddress(java.lang.String address) {
        Portal_CompanyService_address = address;
    }

    /**
     * For the given interfaces, get the stub implementation.
     * If this service has no port for the given interfaces,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osiristher.webapp.provider.soap.portal.company.CompanyServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osiristher.webapp.provider.soap.portal.company.Portal_CompanyServiceSoapBindingStub _stub = new com.osiristher.webapp.provider.soap.portal.company.Portal_CompanyServiceSoapBindingStub(new java.net.URL(Portal_CompanyService_address), this);
                _stub.setPortName(getPortal_CompanyServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interfaces:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interfaces, get the stub implementation.
     * If this service has no port for the given interfaces,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Portal_CompanyService".equals(inputPortName)) {
            return getPortal_CompanyService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:http.service.portal.liferay.com", "CompanyServiceSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:http.service.portal.liferay.com", "Portal_CompanyService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Portal_CompanyService".equals(portName)) {
            setPortal_CompanyServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
