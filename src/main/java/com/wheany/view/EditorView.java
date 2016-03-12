package com.wheany.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.wheany.Util;
import com.wheany.vaadiunui.EditPageComponent;

import java.nio.file.Path;

@SpringView(name = EditorView.VIEW_NAME)
public class EditorView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "edit";

    private final String id;

    public EditorView(String id) {
        this.id = id;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println(EditorView.VIEW_NAME + " enter");
        Path path = Util.getPathFromName(id);
        addComponent(new EditPageComponent(path));
    }
}
