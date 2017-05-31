/**
 * DNAProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.packt.webservice.jaxws.dto;

public class DNAProfile  implements java.io.Serializable {
    private com.packt.webservice.jaxws.dto.ProfileElement[] dnaElements;

    public DNAProfile() {
    }

    public DNAProfile(
           com.packt.webservice.jaxws.dto.ProfileElement[] dnaElements) {
           this.dnaElements = dnaElements;
    }


    /**
     * Gets the dnaElements value for this DNAProfile.
     * 
     * @return dnaElements
     */
    public com.packt.webservice.jaxws.dto.ProfileElement[] getDnaElements() {
        return dnaElements;
    }


    /**
     * Sets the dnaElements value for this DNAProfile.
     * 
     * @param dnaElements
     */
    public void setDnaElements(com.packt.webservice.jaxws.dto.ProfileElement[] dnaElements) {
        this.dnaElements = dnaElements;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DNAProfile)) return false;
        DNAProfile other = (DNAProfile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dnaElements==null && other.getDnaElements()==null) || 
             (this.dnaElements!=null &&
              java.util.Arrays.equals(this.dnaElements, other.getDnaElements())));
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
        if (getDnaElements() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDnaElements());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDnaElements(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DNAProfile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "DNAProfile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dnaElements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "dnaElements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "ProfileElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://service.jaxws.webservice.packt.com", "item"));
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
