package com.wheany;

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
    private Elements items;

    private String linkSelector = "";
    private String linkAttribute = "";
    private Elements links;

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
        this.itemSelector = selector;
        refreshItems();
    }

    private void refreshItems() {
        this.items = document.select(itemSelector);
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
        this.linkSelector = selector;
        refreshLinks();
    }

    private void refreshLinks() {
        this.links = new Elements();
        for(Element item: items) {
            Elements links = item.select(linkSelector);
            this.links.add(links.first());
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
        if (attribute != null) {
            this.linkAttribute = attribute;
        } else {
            this.linkAttribute = "";
        }
    }

    public String getLink(int index) {
        if(index < 0 || index >= links.size()) {
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
        Elements elements = document.select(this.nextPageSelector);

        nextPageElement = elements.first();
    }

    public Element getNextPageElement() {
        return nextPageElement;
    }

    public void setNextPageAttribute(String nextPageAttribute) {
        if(nextPageAttribute != null) {
            this.nextPageAttribute = nextPageAttribute;
        } else {
            this.nextPageAttribute = "";
        }
    }

    public String getNextPageLink() {
        if (nextPageElement != null) {
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
            Element linkElement = element.select(linkSelector).first();
            Link itemLink = new Link();
            String url = "";
            Guid guid = new Guid();

            itemDescription.setvalue(element.html());

            if(linkElement != null) {
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
