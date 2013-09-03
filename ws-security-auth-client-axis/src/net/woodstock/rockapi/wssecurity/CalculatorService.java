/**
 * CalculatorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.woodstock.rockapi.wssecurity;

public interface CalculatorService extends javax.xml.rpc.Service {
    public java.lang.String getCalculatorPortAddress();

    public net.woodstock.rockapi.wssecurity.Calculator getCalculatorPort() throws javax.xml.rpc.ServiceException;

    public net.woodstock.rockapi.wssecurity.Calculator getCalculatorPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
