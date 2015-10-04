package com.wheany;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    void init() {
        HorizontalLayout urlBar = new HorizontalLayout();
        TextField urlField = new TextField("Url to fetch");
        urlField.setWidth(50, Unit.EM);
        urlField.setValue("http://example.com/news");
        urlBar.addComponent(urlField);
        Button fetchButton = new Button("Fetch");
        urlBar.addComponent(fetchButton);
        urlBar.setComponentAlignment(fetchButton, Alignment.BOTTOM_LEFT);
        addComponent(urlBar);

        HorizontalLayout selectorBar = new HorizontalLayout();
        TextField selectorField = new TextField("Selector for items");
        selectorField.setValue("ul.news li");
        selectorField.setWidth(50, Unit.EM);
        selectorBar.addComponent(selectorField);
        Button selectorTestButton = new Button("Test selector");
        selectorBar.addComponent(selectorTestButton);
        selectorBar.setComponentAlignment(selectorTestButton, Alignment.BOTTOM_LEFT);
        addComponent(selectorBar);

        TextArea itemPreview = new TextArea();
        itemPreview.setValue("<li class=\"news-item\">\n" +
                "\t<a class=\"news-link\" href=\"http://example.com/news-item1.html\">This is an interesting link</a>\n" +
                "\t<a class=\"unrelated-link\" href=\"http://example.com/pictures-of-kittens\">This is an unrelated link</a>\n" +
                "</li>");
        itemPreview.setReadOnly(true);
        itemPreview.setWidth(50, Unit.EM);
        addComponent(itemPreview);

        HorizontalLayout linkSelectorBar = new HorizontalLayout();
        TextField linkSelectorField = new TextField("Selector for link");
        linkSelectorField.setValue("a.news-link");
        linkSelectorField.setWidth(50, Unit.EM);
        linkSelectorBar.addComponent(linkSelectorField);
        Button linkSelectorTestButton = new Button("Test selector");
        linkSelectorBar.addComponent(linkSelectorTestButton);
        linkSelectorBar.setComponentAlignment(linkSelectorTestButton, Alignment.BOTTOM_LEFT);
        addComponent(linkSelectorBar);

        TextField urlPreview = new TextField();
        urlPreview.setValue("http://example.com/news-item1.html");
        urlPreview.setReadOnly(true);
        urlPreview.setWidth(50, Unit.EM);
        addComponent(urlPreview);

        HorizontalLayout nextPageSelectorBar = new HorizontalLayout();
        TextField nextPageSelectorField = new TextField("Selector for link");
        nextPageSelectorField.setValue("a.next-page");
        nextPageSelectorField.setWidth(50, Unit.EM);
        nextPageSelectorBar.addComponent(nextPageSelectorField);
        Button nextPageSelectorTestButton = new Button("Test selector");
        nextPageSelectorBar.addComponent(nextPageSelectorTestButton);
        nextPageSelectorBar.setComponentAlignment(nextPageSelectorTestButton, Alignment.BOTTOM_LEFT);

        addComponent(nextPageSelectorBar);

        TextField nextPagePreview = new TextField();
        nextPagePreview.setValue("<a class=\"next-page\" href=\"news-page2.html\">");
        nextPagePreview.setReadOnly(true);
        nextPagePreview.setWidth(50, Unit.EM);
        addComponent(nextPagePreview);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
