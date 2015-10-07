package com.wheany;

import com.wheany.generated.rss.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class RssGenerator {
    private final Document document;
    private Elements items;
    private String linkSelector;
    private String linkAttribute;
    private String nextPageAttribute = "";
    private Element nextPageElement;

    public RssGenerator(Document doc) {
        this.document = doc;
        Document.OutputSettings outputSettings = new Document.OutputSettings();

        outputSettings.indentAmount(4).prettyPrint(true);
        doc.outputSettings(outputSettings);
    }

    public void setItemSelector(String selector) {
        this.items = document.select(selector);
    }

    public Element getItemElement(int index) {
        if(index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public void setLinkSelector(String selector) {
        this.linkSelector = selector;
    }

    public Element getLinkElement(int index) {
        if(index < 0 || index >= items.size()) {
            return null;
        }
        Elements elements = items.get(index).select(linkSelector);
        return elements.first();
    }

    public void setLinkAttribute(String attribute) {
        this.linkAttribute = attribute;
    }

    public String getLink(int index) {
        if(index < 0 || index >= items.size()) {
            return "";
        }
        Elements elements = items.get(index).select(linkSelector);
        Element firstLink = elements.first();

        if(firstLink != null) {
            return firstLink.attr("abs:" + linkAttribute);
        }

        return "";
    }

    public void setNextPageSelector(String nextPageSelector) {
        Elements elements = document.select(nextPageSelector);

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
