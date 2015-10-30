package com.wheany.vaadiunui;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.wheany.Downloader;
import com.wheany.RssGenerator;
import com.wheany.URLValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditPageComponent extends CustomComponent {
    private static final Logger logger = Logger.getLogger(EditPageComponent.class.getName());
    private final RssGenerator generator = new RssGenerator();

    private final ObjectProperty<String> urlProperty = new ObjectProperty<>("");
    private final ObjectProperty<String> itemPreviewProperty = new ObjectProperty<>("");
    private final ObjectProperty<String> itemPreviewIndexProperty = new ObjectProperty<>("0/0");
    private final Path baseDir;
    private final Properties config;
    private int itemPreviewIndex = 0;

    private final ObjectProperty<String> linkElementPreviewProperty = new ObjectProperty<>("");
    private final ObjectProperty<String> linkUrlPreviewProperty = new ObjectProperty<>("");
    private final ObjectProperty<String> linkPreviewIndexProperty = new ObjectProperty<>("0/0");

    private final ObjectProperty<String> nextPageLinkElementPreviewProperty = new ObjectProperty<>("");
    private final ObjectProperty<String> nextPageUrlPreviewProperty = new ObjectProperty<>("");

    private static Properties defaults = new Properties();

    static {
        defaults.put("itemSelector", "");
        defaults.put("linkSelector", "a");
        defaults.put("linkAttribute", "href");
        defaults.put("nextPageSelector", "a[rel=\"next\"]");
        defaults.put("nextPageAttribute", "href");
    }

    private void downloadAndParse() {
        Downloader downloader;
        Path documentPath;
        String baseUrl = urlProperty.getValue();

        try {
            URL url = new URL(baseUrl);
            downloader = new Downloader(baseDir);
            documentPath = downloader.download(url);
            putAndSave("url", baseUrl);
        } catch (MalformedURLException mue) {
            logger.log(Level.SEVERE, "Malformed url:" + baseUrl, mue);
            return;
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "IOException while downloading:", ioe);
            return;
        }
        try {
            generator.setDocument(Jsoup.parse(documentPath.toFile(), null, baseUrl));
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "IOException while parsing document:", ioe);
        }
    }

    private void refreshItemAndLinkPreview() {
        int numElements = generator.getNumElements() - 1;
        itemPreviewIndex = Math.min(Math.max(itemPreviewIndex, 0), numElements);

        if (numElements > 0) {
            itemPreviewIndexProperty.setValue(String.format("%d/%d", itemPreviewIndex + 1, generator.getNumElements()));
            linkPreviewIndexProperty.setValue(String.format("%d/%d", itemPreviewIndex + 1, generator.getNumLinks()));
        } else {
            itemPreviewIndexProperty.setValue(" — ");
            linkPreviewIndexProperty.setValue(" — ");
        }

        Element itemElement = generator.getItemElement(itemPreviewIndex);
        if (itemElement != null) {
            itemPreviewProperty.setValue(itemElement.outerHtml());
        } else {
            itemPreviewProperty.setValue("[Item element not found]");
        }
        Element linkElement = generator.getLinkElement(itemPreviewIndex);
        if (linkElement != null) {
            linkElementPreviewProperty.setValue(linkElement.outerHtml());
        } else {
            linkElementPreviewProperty.setValue("[Link element not found]");
        }
        String linkUrl = generator.getLink(itemPreviewIndex);
        if (linkUrl != null) {
            linkUrlPreviewProperty.setValue(linkUrl);
        } else {
            linkUrlPreviewProperty.setValue("");
        }
    }

    private void refreshNextPageLinkPreview() {
        Element nextPageLinkElement = generator.getNextPageElement();
        if (nextPageLinkElement != null) {
            nextPageLinkElementPreviewProperty.setValue(nextPageLinkElement.outerHtml());
        } else {
            nextPageLinkElementPreviewProperty.setValue("[Next page link element not found]");
        }
        nextPageUrlPreviewProperty.setValue(generator.getNextPageLink());
    }

    public EditPageComponent(Path baseDir) {
        this.baseDir = baseDir;
        this.config = new Properties(defaults);
        Path documentPath = baseDir.resolve("download/download.html");
        try (InputStream is = Files.newInputStream(baseDir.resolve("config.properties"))) {
            config.load(is);
            String baseUrl = config.getProperty("url");
            urlProperty.setValue(baseUrl);
            if (documentPath.toFile().exists()) {
                generator.setDocument(Jsoup.parse(documentPath.toFile(), null, baseUrl));
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        PropertysetItem data = new PropertysetItem();

        data.addItemProperty("url", urlProperty);

        final ObjectProperty<String> itemSelectorProperty = new ObjectProperty<>(config.getProperty("itemSelector"));

        itemSelectorProperty.addValueChangeListener(event -> {
            generator.setItemSelector(itemSelectorProperty.getValue());
            putAndSave("itemSelector", itemSelectorProperty.getValue());
            itemPreviewIndex = 0;
            refreshItemAndLinkPreview();
        });

        data.addItemProperty("itemSelector", itemSelectorProperty);

        data.addItemProperty("itemPreview", itemPreviewProperty);
        data.addItemProperty("itemPreviewIndex", itemPreviewIndexProperty);

        final ObjectProperty<String> linkSelectorProperty = new ObjectProperty<>(config.getProperty("linkSelector"));
        final ObjectProperty<String> linkAttributeProperty = new ObjectProperty<>(config.getProperty("linkAttribute"));

        Property.ValueChangeListener linkListener = event -> {
            generator.setLinkSelector(linkSelectorProperty.getValue());
            generator.setLinkAttribute(linkAttributeProperty.getValue());
            putAndSave("linkSelector", linkSelectorProperty.getValue());
            putAndSave("linkAttribute", linkAttributeProperty.getValue());
            itemPreviewIndex = 0;
            refreshItemAndLinkPreview();
        };
        linkSelectorProperty.addValueChangeListener(linkListener);
        linkAttributeProperty.addValueChangeListener(linkListener);

        data.addItemProperty("linkSelector", linkSelectorProperty);
        data.addItemProperty("linkAttribute", linkAttributeProperty);

        data.addItemProperty("linkPreview", linkElementPreviewProperty);
        data.addItemProperty("linkUrlPreview", linkUrlPreviewProperty);
        data.addItemProperty("linkPreviewIndex", linkPreviewIndexProperty);

        final ObjectProperty<String> nextPageSelectorProperty = new ObjectProperty<>(config.getProperty("nextPageSelector"));
        final ObjectProperty<String> nextPageAttributeProperty = new ObjectProperty<>(config.getProperty("nextPageAttribute"));

        data.addItemProperty("nextPageSelector", nextPageSelectorProperty);
        data.addItemProperty("nextPageAttribute", nextPageAttributeProperty);

        Property.ValueChangeListener nextPageLinkListener = event -> {
            generator.setNextPageSelector(nextPageSelectorProperty.getValue());
            generator.setNextPageAttribute(nextPageAttributeProperty.getValue());
            putAndSave("nextPageSelector", nextPageSelectorProperty.getValue());
            putAndSave("nextPageAttribute", nextPageAttributeProperty.getValue());

            refreshNextPageLinkPreview();
        };
        nextPageSelectorProperty.addValueChangeListener(nextPageLinkListener);
        nextPageAttributeProperty.addValueChangeListener(nextPageLinkListener);

        data.addItemProperty("nextPageLinkElementPreview", nextPageLinkElementPreviewProperty);
        data.addItemProperty("nextPageUrlPreview", nextPageUrlPreviewProperty);

        FormLayout form = new FormLayout();

        FieldGroup binder = new FieldGroup(data);
        Field urlField = binder.buildAndBind("Page URL", "url");
        urlField.addValidator(new URLValidator());
        form.addComponent(urlField);
        final Button downloadButton = new Button("Download URL", event -> downloadAndParse());
        form.addComponent(downloadButton);
        form.addComponent(binder.buildAndBind("Item selector", "itemSelector"));
        final Label itemPreviewComponent = new Label(itemPreviewProperty);
        itemPreviewComponent.setCaption("Item preview");
        itemPreviewComponent.setContentMode(ContentMode.PREFORMATTED);
        form.addComponent(itemPreviewComponent);
        final HorizontalLayout itemPreviewIndexUI = new HorizontalLayout();
        final Label itemPreviewIndexComponent = new Label(itemPreviewIndexProperty);
        itemPreviewIndexComponent.addStyleName("spaced-label");
        final Button decrementItemNumberButton = new Button("-", event -> {
            itemPreviewIndex--;
            refreshItemAndLinkPreview();
        });
        final Button incrementItemNumberButton = new Button("+", event -> {
            itemPreviewIndex++;
            refreshItemAndLinkPreview();
        });
        itemPreviewIndexUI.setCaption("Item number");
        itemPreviewIndexUI.addComponent(decrementItemNumberButton);
        itemPreviewIndexUI.addComponent(itemPreviewIndexComponent);
        itemPreviewIndexUI.addComponent(incrementItemNumberButton);
        form.addComponent(itemPreviewIndexUI);

        form.addComponent(binder.buildAndBind("Link selector", "linkSelector"));
        form.addComponent(binder.buildAndBind("Link attribute", "linkAttribute"));
        final Label linkElementPreviewComponent = new Label(linkElementPreviewProperty);
        linkElementPreviewComponent.setCaption("Link preview");
        linkElementPreviewComponent.setContentMode(ContentMode.PREFORMATTED);
        form.addComponent(linkElementPreviewComponent);
        final Label linkUrlPreviewComponent = new Label(linkUrlPreviewProperty);
        linkUrlPreviewComponent.setCaption("Link url preview");
        form.addComponent(linkUrlPreviewComponent);
        final Label linkPreviewIndexComponent = new Label(linkPreviewIndexProperty);
        linkPreviewIndexComponent.setCaption("Link number");
        form.addComponent(linkPreviewIndexComponent);
        form.addComponent(binder.buildAndBind("Next page link selector", "nextPageSelector"));
        form.addComponent(binder.buildAndBind("Next page link attribute", "nextPageAttribute"));
        final Label nextPageLinkElementPreviewComponent = new Label(nextPageLinkElementPreviewProperty);
        nextPageLinkElementPreviewComponent.setCaption("Next page link preview");
        nextPageLinkElementPreviewComponent.setContentMode(ContentMode.PREFORMATTED);
        form.addComponent(nextPageLinkElementPreviewComponent);
        final Label nextPageLinkUrlPreviewComponent = new Label(nextPageUrlPreviewProperty);
        nextPageLinkUrlPreviewComponent.setCaption("Next page link url preview");
        form.addComponent(nextPageLinkUrlPreviewComponent);

        final Button generateButton = new Button("Generate RSS feed now", event -> {
            generator.generateAndSave(baseDir.resolve("feed.xml"));
        });
        form.addComponent(generateButton);
        binder.setBuffered(false);

        setCompositionRoot(form);
    }

    private void putAndSave(String name, String value) {
        config.put(name, value);
        try (OutputStream os = Files.newOutputStream(baseDir.resolve("config.properties"))) {
            config.store(os, "Parsing settings");
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "IOException while writing config:", ioe);
        }
    }
}
