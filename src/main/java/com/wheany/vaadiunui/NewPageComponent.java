package com.wheany.vaadiunui;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.wheany.Downloader;
import com.wheany.URLValidator;
import com.wheany.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewPageComponent extends CustomComponent {
    private static final Logger logger = Logger.getLogger(NewPageComponent.class.getName());

    private final ObjectProperty<String> urlProperty = new ObjectProperty<>("");

    public NewPageComponent() {
        PropertysetItem data = new PropertysetItem();

        data.addItemProperty("url", urlProperty);

        FormLayout form = new FormLayout();

        FieldGroup binder = new FieldGroup(data);
        Field urlField = binder.buildAndBind("Page URL", "url");
        urlField.addValidator(new URLValidator());
        form.addComponent(urlField);
        final Button downloadButton = new Button("Download URL", event -> download());
        form.addComponent(downloadButton);

        binder.setBuffered(false);

        setCompositionRoot(form);
    }

    private void download() {
        Downloader downloader;
        String baseUrl = urlProperty.getValue();

        Util.NamedPath namedPath = Util.makeWorkDir();
        if (namedPath == null) {
            throw new NullPointerException("Failed to create work dir");
        }

        try {
            URL url = new URL(baseUrl);
            downloader = new Downloader(namedPath.getPath());
            downloader.download(url);
        } catch (MalformedURLException mue) {
            logger.log(Level.SEVERE, "Malformed url:" + baseUrl, mue);
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "IOException while downloading:", ioe);
        }

        Properties config = new Properties();
        config.put("url", baseUrl);
        try(OutputStream os = Files.newOutputStream(namedPath.getPath().resolve("config.properties"))){
            config.store(os, "Parsing settings");
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "IOException while writing config:", ioe);
        }

        URI location = getUI().getPage().getLocation();
        String newLocation = location.toString() + "edit?id=" + namedPath.getName();
        getUI().getPage().open(newLocation, null, false);
    }
}
