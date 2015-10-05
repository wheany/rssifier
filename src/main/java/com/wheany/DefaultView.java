package com.wheany;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SystemError;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.wheany.generated.rss.Rss;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    private TextField urlField;
    private Button fetchButton;

    private VerticalLayout selectorUI;

    private TextField selectorField;
    private Label itemPreview;
    private TextField linkSelectorField;
    private TextField linkAttributeField;
    private Label linkPreview;
    private Label urlPreview;
    private TextField nextPageSelectorField;
    private TextField nextPageAttributeField;
    private Label nextPagePreview;
    private Label nextPageUrlPreview;

    private RssGenerator generator;

    @PostConstruct
    void init() {
        HorizontalLayout urlBar = new HorizontalLayout();

        urlField = new TextField("Url to fetch");
        urlField.setWidth(50, Unit.EM);
        urlField.setValue("");

        urlField.addTextChangeListener(event -> {
            urlField.setComponentError(null);
            fetchButton.setEnabled(true);
        });

        fetchButton = new Button("Fetch");
        fetchButton.setDisableOnClick(true);

        fetchButton.addClickListener(clickEvent -> {
            try {
                Downloader downloader = new Downloader(urlField.getValue());

                generator = new RssGenerator(
                        Jsoup.parse(downloader.download().toFile(), null, urlField.getValue())
                );

                urlField.setReadOnly(true);
                fetchButton.setVisible(false);
                selectorUI.setEnabled(true);

            } catch (MalformedURLException e) {
                urlField.setComponentError(new UserError("Malformed URL"));
            } catch (IOException e) {
                urlField.setComponentError(new SystemError("Error downloading URL:" + e.getMessage()));
            }
        });

        urlBar.addComponent(urlField);
        urlBar.addComponent(fetchButton);
        urlBar.setComponentAlignment(fetchButton, Alignment.BOTTOM_LEFT);

        addComponent(urlBar);

        selectorUI = new VerticalLayout();

        HorizontalLayout selectorBar = new HorizontalLayout();

        selectorField = new TextField("Selector for items");
        selectorField.setValue("");
        selectorField.setWidth(20, Unit.EM);
        selectorField.addTextChangeListener(event1 -> selectorField.setComponentError(null));

        Button selectorTestButton = new Button("Test selector");

        selectorBar.addComponent(selectorField);
        selectorBar.addComponent(selectorTestButton);
        selectorBar.setComponentAlignment(selectorTestButton, Alignment.BOTTOM_LEFT);
        selectorUI.addComponent(selectorBar);

        Label previewHeading = new Label("<strong>Item preview:</strong>", ContentMode.HTML);
        selectorUI.addComponent(previewHeading);

        itemPreview = new Label("", ContentMode.PREFORMATTED);

        selectorTestButton.addClickListener(event -> {
            generator.setItemSelector(selectorField.getValue());
            Element element = generator.getItemElement(0);
            if (element != null) {
                itemPreview.setValue(element.outerHtml());
            } else {
                itemPreview.setValue("");
                selectorField.setComponentError(new UserError("No elements found with selector"));
            }
        });

        selectorUI.addComponent(itemPreview);

        HorizontalLayout linkSelectorBar = new HorizontalLayout();

        linkSelectorField = new TextField("Selector for link");
        linkSelectorField.setValue("a");
        linkSelectorField.setWidth(20, Unit.EM);
        linkSelectorField.addTextChangeListener(event1 -> linkSelectorField.setComponentError(null));

        linkAttributeField = new TextField("Attribute for link");
        linkAttributeField.setValue("href");
        linkAttributeField.setWidth(10, Unit.EM);

        Button linkSelectorTestButton = new Button("Test link and attribute selector");

        linkSelectorBar.addComponent(linkSelectorField);
        linkSelectorBar.addComponent(linkAttributeField);
        linkSelectorBar.addComponent(linkSelectorTestButton);
        linkSelectorBar.setComponentAlignment(linkSelectorTestButton, Alignment.BOTTOM_LEFT);
        selectorUI.addComponent(linkSelectorBar);

        Label linkPreviewHeading = new Label("<strong>Link element preview:</strong>", ContentMode.HTML);
        selectorUI.addComponent(linkPreviewHeading);

        linkPreview = new Label("", ContentMode.PREFORMATTED);
        selectorUI.addComponent(linkPreview);

        Label urlPreviewHeading = new Label("<strong>URL preview:</strong>", ContentMode.HTML);
        selectorUI.addComponent(urlPreviewHeading);

        urlPreview = new Label("http://example.com/news-item1.html");
        selectorUI.addComponent(urlPreview);

        linkSelectorTestButton.addClickListener(event -> {
            generator.setLinkSelector(linkSelectorField.getValue());
            generator.setLinkAttribute(linkAttributeField.getValue());
            Element linkElement = generator.getLinkElement(0);
            if (linkElement != null) {
                linkPreview.setValue(linkElement.outerHtml());
                urlPreview.setValue(generator.getLink(0));
            } else {
                linkPreview.setValue("");
                urlPreview.setValue("");
                linkSelectorField.setComponentError(new UserError("No elements found with selector"));
            }
        });

        HorizontalLayout nextPageSelectorBar = new HorizontalLayout();

        nextPageSelectorField = new TextField("Selector for next page link");
        nextPageSelectorField.setValue("a[rel=next]");
        nextPageSelectorField.setWidth(20, Unit.EM);
        nextPageSelectorField.addTextChangeListener(event1 -> nextPageSelectorField.setComponentError(null));

        nextPageAttributeField = new TextField("Attribute for link");
        nextPageAttributeField.setValue("href");
        nextPageAttributeField.setWidth(10, Unit.EM);

        Button nextPageSelectorTestButton = new Button("Test link and attribute selector");

        nextPageSelectorBar.addComponent(nextPageSelectorField);
        nextPageSelectorBar.addComponent(nextPageAttributeField);
        nextPageSelectorBar.addComponent(nextPageSelectorTestButton);
        nextPageSelectorBar.setComponentAlignment(nextPageSelectorTestButton, Alignment.BOTTOM_LEFT);

        selectorUI.addComponent(nextPageSelectorBar);

        Label nextPagePreviewHeading = new Label("<strong>Next page link preview:</strong>", ContentMode.HTML);
        selectorUI.addComponent(nextPagePreviewHeading);

        nextPagePreview = new Label("", ContentMode.PREFORMATTED);
        selectorUI.addComponent(nextPagePreview);

        Label nextPageUrlPreviewHeading = new Label("<strong>Next page URL preview:</strong>", ContentMode.HTML);
        selectorUI.addComponent(nextPageUrlPreviewHeading);

        nextPageUrlPreview = new Label("");
        selectorUI.addComponent(nextPageUrlPreview);

        nextPageSelectorTestButton.addClickListener(event -> {
            generator.setNextPageSelector(nextPageSelectorField.getValue());
            generator.setNextPageAttribute(nextPageAttributeField.getValue());
            Element nextPageLink = generator.getNextPageElement();
            if (nextPageLink != null) {
                nextPagePreview.setValue(nextPageLink.outerHtml());
                nextPageUrlPreview.setValue(generator.getNextPageLink());
            } else {
                nextPagePreview.setValue("");
                nextPageUrlPreview.setValue("");
                nextPageSelectorField.setComponentError(new UserError("No elements found with selector"));
            }
        });

        Button generateButton = new Button("Generate");
        generateButton.addClickListener(event -> {
            Rss rss = generator.generate();

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
                Marshaller marshaller = jaxbContext.createMarshaller();

                final Path workDir = Paths.get("generated-rss");
                Files.createDirectories(workDir);

                Path workFile = Files.createTempFile(workDir, "rss", ".xml");

                marshaller.marshal(rss, workFile.toFile());

            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        selectorUI.addComponent(generateButton);

        selectorUI.setEnabled(false);

        addComponent(selectorUI);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
