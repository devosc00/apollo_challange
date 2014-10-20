
package controllers;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCustomerDataResult" type="{http://schemas.datacontract.org/2004/07/Task}Customer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCustomerDataResult"
})
@XmlRootElement(name = "GetCustomerDataResponse")
public class GetCustomerDataResponse {

    @XmlElementRef(name = "GetCustomerDataResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Customer> getCustomerDataResult;

    /**
     * Gets the value of the getCustomerDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link controllers.Customer }{@code >}
     *     
     */
    public JAXBElement<Customer> getGetCustomerDataResult() {
        return getCustomerDataResult;
    }

    /**
     * Sets the value of the getCustomerDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link controllers.Customer }{@code >}
     *     
     */
    public void setGetCustomerDataResult(JAXBElement<Customer> value) {
        this.getCustomerDataResult = value;
    }

}
