
package com.wheany.generated.rss;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "guid")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Guid {

    @XmlAttribute(name = "isPermaLink")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String isPermaLink;
    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String value;

    /**
     * Gets the value of the isPermaLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getIsPermaLink() {
        if (isPermaLink == null) {
            return "true";
        } else {
            return isPermaLink;
        }
    }

    /**
     * Sets the value of the isPermaLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIsPermaLink(String value) {
        this.isPermaLink = value;
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