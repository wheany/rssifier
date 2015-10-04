
package com.wheany.generated.rss;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "category")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Category {

    @XmlAttribute(name = "domain")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String domain;
    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String value;

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getvalue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setvalue(String value) {
        this.value = value;
    }

}
