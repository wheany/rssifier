package com.wheany.ui;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.wheany.URLValidator;
import com.wheany.Util;

import java.util.logging.Logger;

public class NewUI extends CustomComponent {
    private static final Logger logger = Logger.getLogger(NewUI.class.getName());

    private final ObjectProperty<String> urlProperty = new ObjectProperty<>("");

    private Util.NamedPath workPath;

    public NewUI() {
        PropertysetItem data = new PropertysetItem();

        data.addItemProperty("url", urlProperty);

        FormLayout form = new FormLayout();

        FieldGroup binder = new FieldGroup(data);
        Field urlField = binder.buildAndBind("Page URL", "url");
        urlField.addValidator(new URLValidator());
        form.addComponent(urlField);
        final Button downloadButton = new Button("Download URL", event -> downloadAndParse());
        form.addComponent(downloadButton);

        binder.setBuffered(false);

        setCompositionRoot(form);
    }

    private void downloadAndParse() {
        String url = urlProperty.getValue();
    }
}
