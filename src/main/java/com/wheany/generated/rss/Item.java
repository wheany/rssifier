
package com.wheany.generated.rss;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "titleOrDescription",
    "link",
    "authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource"
})
@XmlRootElement(name = "item")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Item {

    @XmlElements({
        @XmlElement(name = "title", required = true, type = Title.class),
        @XmlElement(name = "description", required = true, type = Description.class)
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Object> titleOrDescription;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Link link;
    @XmlElements({
        @XmlElement(name = "author", type = Author.class),
        @XmlElement(name = "category", type = Category.class),
        @XmlElement(name = "comments", type = Comments.class),
        @XmlElement(name = "enclosure", type = Enclosure.class),
        @XmlElement(name = "guid", type = Guid.class),
        @XmlElement(name = "pubDate", type = PubDate.class),
        @XmlElement(name = "source", type = Source.class)
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Object> authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource;

    /**
     * Gets the value of the titleOrDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the titleOrDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitleOrDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Title }
     * {@link Description }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Object> getTitleOrDescription() {
        if (titleOrDescription == null) {
            titleOrDescription = new ArrayList<Object>();
        }
        return this.titleOrDescription;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Link getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLink(Link value) {
        this.link = value;
    }

    /**
     * Gets the value of the authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * {@link Category }
     * {@link Comments }
     * {@link Enclosure }
     * {@link Guid }
     * {@link PubDate }
     * {@link Source }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Object> getAuthorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource() {
        if (authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource == null) {
            authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource = new ArrayList<Object>();
        }
        return this.authorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource;
    }

}
