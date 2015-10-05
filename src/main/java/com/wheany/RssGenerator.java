package com.wheany;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RssGenerator {
    private final Document document;
    private Elements items;
    private String linkSelector;
    private String linkAttribute;
    private String nextPageSelector;
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
}
