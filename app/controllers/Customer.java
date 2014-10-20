package controllers;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for Customer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BornDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", namespace = "http://schemas.datacontract.org/2004/07/Task", propOrder = {
        "bornDate",
        "id",
        "surname"
})

@Entity
public class Customer extends Model {

    @XmlElement(name = "BornDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bornDate;
    @XmlElement(name = "ID")
    protected Integer id;
    @XmlElementRef(name = "Surname", namespace = "http://schemas.datacontract.org/2004/07/Task", type = JAXBElement.class, required = false)
    protected JAXBElement<String> surname;



    /**
     * Gets the value of the bornDate property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getBornDate() {
        return bornDate;
    }

    /**
     * Sets the value of the bornDate property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *
     */
    public void setBornDate(XMLGregorianCalendar value) {
        this.bornDate = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setID(Integer value) {
        this.id = value;
    }



    /**
     * Gets the value of the surname property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSurname(JAXBElement<String> value) {
        this.surname = value;
    }




    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Integer,Customer> find = new Finder<Integer, Customer>(Integer.class, Customer.class);



}

