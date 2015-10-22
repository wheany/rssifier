package com.wheany;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.wheany.generated.rss.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class RssGenerator {
    private Document document;
    private String itemSelector = "";
    private Elements items = new Elements();

    private String linkSelector = "";
    private String linkAttribute = "";
    private Elements links = new Elements();

    private String nextPageSelector = "";
    private String nextPageAttribute = "";
    private Element nextPageElement;

    public void setDocument(@NotNull Document document) {
        this.document = Objects.requireNonNull(document);
        setDocumentOutputSettings();
        refreshItems();
        refreshLinks();
        refreshNextPageElement();
    }

    public RssGenerator() {
        setDocument(new Document(""));
    }

    public RssGenerator(@NotNull Document doc) {
        setDocument(doc);
    }

    private void setDocumentOutputSettings() {
        Document.OutputSettings outputSettings = new Document.OutputSettings();

        outputSettings.indentAmount(4).prettyPrint(true);
        this.document.outputSettings(outputSettings);
    }

    public void setItemSelector(String selector) {
        this.itemSelector = Objects.requireNonNull(selector);
        refreshItems();
    }

    private void refreshItems() {
        if (!Strings.isNullOrEmpty(itemSelector)) {
            this.items = document.select(itemSelector);
        }
        refreshLinks();
    }

    public Element getItemElement(int index) {
        if(index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }
    public int getNumElements() {
        return items.size();
    }

    public void setLinkSelector(String selector) {
        this.linkSelector = Objects.requireNonNull(selector);
        refreshLinks();
    }

    private void refreshLinks() {
        this.links = new Elements();
        for(Element item: items) {
            if (!Strings.isNullOrEmpty(linkSelector)) {
                Elements links = item.select(linkSelector);
                if (links.size() > 0) {
                    this.links.add(links.first());
                }
            }
        }
    }

    public int getNumLinks() {
        return links.size();
    }

    public Element getLinkElement(int index) {
        if(index < 0 || index >= links.size()) {
            return null;
        }
        return links.get(index);
    }

    public void setLinkAttribute(String attribute) {
        this.linkAttribute = Objects.requireNonNull(attribute);
    }

    public String getLink(int index) {
        if(index < 0 || index >= links.size() || Strings.isNullOrEmpty(linkAttribute)) {
            return "";
        }
        Element link = links.get(index);

        if(link != null) {
            return link.attr("abs:" + linkAttribute);
        }

        return "";
    }

    public void setNextPageSelector(String nextPageSelector) {
        this.nextPageSelector = nextPageSelector;
        refreshNextPageElement();
    }

    private void refreshNextPageElement() {
        if (!Strings.isNullOrEmpty(nextPageSelector)) {
            Elements elements = document.select(this.nextPageSelector);
            if(elements.size() > 0) {
                nextPageElement = elements.first();
            }
        }
    }

    public Element getNextPageElement() {
        return nextPageElement;
    }

    public void setNextPageAttribute(String nextPageAttribute) {
        this.nextPageAttribute = Objects.requireNonNull(nextPageAttribute);
        refreshNextPageElement();
    }

    public String getNextPageLink() {
        if (nextPageElement != null && !Strings.isNullOrEmpty(nextPageAttribute)) {
            return nextPageElement.attr("abs:" + nextPageAttribute);
        }
        return "";
    }

    public Rss generate() {
        Rss feed = new Rss();
        feed.setVersion("2.0");

        Channel channel = new Channel();
        feed.setChannel(channel);

        List<Object> content = channel.getItemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays();

        Title title = new Title();
        title.setvalue(document.title());
        content.add(title);

        Link link = new Link();
        link.setvalue(document.baseUri());
        content.add(link);

        Generator generator = new Generator();
        generator.setvalue("RSSifier");
        content.add(generator);

        Description description = new Description();
        description.setvalue("Generated feed from " + document.baseUri());
        content.add(description);

        items.stream().forEach(element -> {
            Item item = new Item();
            Title itemTitle = new Title();
            Description itemDescription = new Description();

            Element linkElement = null;
            if (!Strings.isNullOrEmpty(linkSelector)) {
                linkElement = element.select(linkSelector).first();
            }

            Link itemLink = new Link();
            String url = "";
            Guid guid = new Guid();

            itemDescription.setvalue(element.html());

            if (linkElement != null && !Strings.isNullOrEmpty(linkAttribute)) {
                itemTitle.setvalue(linkElement.text());
                url = linkElement.attr("abs:" + linkAttribute);
            } else {
                itemTitle.setvalue("[no title]");
            }
            itemLink.setvalue(url);
            guid.setvalue(url);

            item.getTitleOrDescription().add(itemTitle);
            item.getTitleOrDescription().add(itemDescription);
            item.setLink(itemLink);
            item.getAuthorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource().add(guid);

            content.add(item);
        });

        return feed;
    }
}
