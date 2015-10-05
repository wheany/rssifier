package com.wheany;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";
    private TextField urlField;
    private Button fetchButton;
    private TextField selectorField;
    private Button selectorTestButton;
    private Label itemPreview;
    private TextField linkSelectorField;
    private TextField linkAttributeField;
    private Button linkSelectorTestButton;
    private Label urlPreview;
    private TextField nextPageSelectorField;
    private TextField nextPageAttributeField;
    private Button nextPageSelectorTestButton;
    private Label nextPagePreview;

    @PostConstruct
    void init() {
        HorizontalLayout urlBar = new HorizontalLayout();

        urlField = new TextField("Url to fetch");
        urlField.setWidth(50, Unit.EM);
        urlField.setValue("http://example.com/news");

        fetchButton = new Button("Fetch");

        urlBar.addComponent(urlField);
        urlBar.addComponent(fetchButton);
        urlBar.setComponentAlignment(fetchButton, Alignment.BOTTOM_LEFT);
        addComponent(urlBar);

        HorizontalLayout selectorBar = new HorizontalLayout();

        selectorField = new TextField("Selector for items");
        selectorField.setValue("ul.news li");
        selectorField.setWidth(20, Unit.EM);

        selectorTestButton = new Button("Test selector");

        selectorBar.addComponent(selectorField);
        selectorBar.addComponent(selectorTestButton);
        selectorBar.setComponentAlignment(selectorTestButton, Alignment.BOTTOM_LEFT);
        addComponent(selectorBar);

        Label previewHeading = new Label("<strong>Item preview:</strong>", ContentMode.HTML);
        addComponent(previewHeading);

        itemPreview = new Label("<li class=\"news-item\">\n" +
                "\t<a class=\"news-link\" href=\"http://example.com/news-item1.html\">This is an interesting link</a>\n" +
                "\t<a class=\"unrelated-link\" href=\"http://example.com/pictures-of-kittens\">This is an unrelated link</a>\n" +
                "</li>", ContentMode.PREFORMATTED);
        addComponent(itemPreview);

        HorizontalLayout linkSelectorBar = new HorizontalLayout();

        linkSelectorField = new TextField("Selector for link");
        linkSelectorField.setValue("a.news-link");
        linkSelectorField.setWidth(20, Unit.EM);

        linkAttributeField = new TextField("Attribute for link");
        linkAttributeField.setValue("href");
        linkAttributeField.setWidth(10, Unit.EM);

        linkSelectorTestButton = new Button("Test selector");

        linkSelectorBar.addComponent(linkSelectorField);
        linkSelectorBar.addComponent(linkAttributeField);
        linkSelectorBar.addComponent(linkSelectorTestButton);
        linkSelectorBar.setComponentAlignment(linkSelectorTestButton, Alignment.BOTTOM_LEFT);
        addComponent(linkSelectorBar);

        Label urlPreviewHeading = new Label("<strong>URL preview:</strong>", ContentMode.HTML);
        addComponent(urlPreviewHeading);

        urlPreview = new Label("http://example.com/news-item1.html", ContentMode.PREFORMATTED);
        addComponent(urlPreview);

        HorizontalLayout nextPageSelectorBar = new HorizontalLayout();

        nextPageSelectorField = new TextField("Selector for link");
        nextPageSelectorField.setValue("a[rel=next]");
        nextPageSelectorField.setWidth(20, Unit.EM);

        nextPageAttributeField = new TextField("Attribute for link");
        nextPageAttributeField.setValue("href");
        nextPageAttributeField.setWidth(10, Unit.EM);

        nextPageSelectorTestButton = new Button("Test selector");

        nextPageSelectorBar.addComponent(nextPageSelectorField);
        nextPageSelectorBar.addComponent(nextPageSelectorTestButton);
        nextPageSelectorBar.setComponentAlignment(nextPageSelectorTestButton, Alignment.BOTTOM_LEFT);

        addComponent(nextPageSelectorBar);

        Label nextPagePreviewHeading = new Label("<strong>Next page link preview:</strong>", ContentMode.HTML);
        addComponent(nextPagePreviewHeading);

        nextPagePreview = new Label("<a class=\"next-page\" href=\"news-page2.html\" rel=\"next\">", ContentMode.PREFORMATTED);
        addComponent(nextPagePreview);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
