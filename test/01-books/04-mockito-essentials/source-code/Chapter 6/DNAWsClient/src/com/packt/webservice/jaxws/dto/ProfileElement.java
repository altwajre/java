/**
 * ProfileElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.packt.webservice.jaxws.dto;

public class ProfileElement  implements java.io.Serializable {
    private java.lang.String alleleA;

    private java.lang.String alleleB;

    private java.lang.String geneticMarker;

    public ProfileElement() {
    }

    public ProfileElement(
           java.lang.String alleleA,
           java.lang.String alleleB,
           java.lang.String geneticMarker) {
           this.alleleA = alleleA;
           this.alleleB = alleleB;
           this.geneticMarker = geneticMarker;
    }


    /**
     * Gets the alleleA value for this ProfileElement.
     * 
     * @return alleleA
     */
    public java.lang.String getAlleleA() {
        return alleleA;
    }


    /**
     * Sets the alleleA value for this ProfileElement.
     * 
     * @param alleleA
     */
    public void setAlleleA(java.lang.String alleleA) {
        this.alleleA = alleleA;
    }


    /**
     * Gets the alleleB value for this ProfileElement.
     * 
     * @return alleleB
     */
    public java.lang.String getAlleleB() {
        return alleleB;
    }


    /**
     * Sets the alleleB value for this ProfileElement.
     * 
     * @param alleleB
     */
    public void setAlleleB(java.lang.String alleleB) {
        this.alleleB = alleleB;
    }


    /**
     * Gets the geneticMarker value for this ProfileElement.
     * 
     * @return geneticMarker
     */
    public java.lang.String getGeneticMarker() {
        return geneticMarker;
    }


    /**
     * Sets the geneticMarker value for this ProfileElement.
     * 
     * @param geneticMarker
     */
    public void setGeneticMarker(java.lang.String geneticMarker) {
        this.geneticMarker = geneticMarker;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProfileElement)) return false;
        ProfileElement other = (ProfileElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.alleleA==null && other.getAlleleA()==null) || 
             (this.alleleA!=null &&
              this.alleleA.equals(other.getAlleleA()))) &&
            ((this.alleleB==null && other.getAlleleB()==null) || 
             (this.alleleB!=null &&
              this.alleleB.equals(other.getAlleleB()))) &&
            ((this.geneticMarker==null && other.getGeneticMarker()==null) || 
             (this.geneticMarker!=null &&
              this.geneticMarker.equals(other.getGeneticMarker())));
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
        if (getAlleleA() != null) {
            _hashCode += getAlleleA().hashCode();
        }
        if (getAlleleB() != null) {
            _hashCode += getAlleleB().hashCode();
        }
        if (getGeneticMarker() != null) {
            _hashCode += getGeneticMarker().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProfileElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "ProfileElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alleleA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "alleleA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alleleB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "alleleB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("geneticMarker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.jaxws.webservice.packt.com", "geneticMarker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
