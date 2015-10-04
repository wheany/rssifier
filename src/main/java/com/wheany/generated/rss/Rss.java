
package com.wheany.generated.rss;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channel"
})
@XmlRootElement(name = "rss")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Rss {

    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String version;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Channel channel;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getVersion() {
        if (version == null) {
            return "2.0";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link Channel }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Channel }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setChannel(Channel value) {
        this.channel = value;
    }

}
