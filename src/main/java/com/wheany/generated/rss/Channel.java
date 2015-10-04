
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
    "itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays"
})
@XmlRootElement(name = "channel")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Channel {

    @XmlElements({
        @XmlElement(name = "item", required = true, type = Item.class),
        @XmlElement(name = "title", required = true, type = Title.class),
        @XmlElement(name = "link", required = true, type = Link.class),
        @XmlElement(name = "description", required = true, type = Description.class),
        @XmlElement(name = "language", required = true, type = Language.class),
        @XmlElement(name = "copyright", required = true, type = Copyright.class),
        @XmlElement(name = "managingEditor", required = true, type = ManagingEditor.class),
        @XmlElement(name = "webMaster", required = true, type = WebMaster.class),
        @XmlElement(name = "pubDate", required = true, type = PubDate.class),
        @XmlElement(name = "lastBuildDate", required = true, type = LastBuildDate.class),
        @XmlElement(name = "category", required = true, type = Category.class),
        @XmlElement(name = "generator", required = true, type = Generator.class),
        @XmlElement(name = "docs", required = true, type = Docs.class),
        @XmlElement(name = "cloud", required = true, type = Cloud.class),
        @XmlElement(name = "ttl", required = true, type = Ttl.class),
        @XmlElement(name = "image", required = true, type = Image.class),
        @XmlElement(name = "textInput", required = true, type = TextInput.class),
        @XmlElement(name = "skipHours", required = true, type = SkipHours.class),
        @XmlElement(name = "skipDays", required = true, type = SkipDays.class)
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Object> itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays;

    /**
     * Gets the value of the itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * {@link Title }
     * {@link Link }
     * {@link Description }
     * {@link Language }
     * {@link Copyright }
     * {@link ManagingEditor }
     * {@link WebMaster }
     * {@link PubDate }
     * {@link LastBuildDate }
     * {@link Category }
     * {@link Generator }
     * {@link Docs }
     * {@link Cloud }
     * {@link Ttl }
     * {@link Image }
     * {@link TextInput }
     * {@link SkipHours }
     * {@link SkipDays }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2015-10-04T07:23:45+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Object> getItemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays() {
        if (itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays == null) {
            itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays = new ArrayList<Object>();
        }
        return this.itemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays;
    }

}
