package com.wheany.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.wheany.ui.EditorUI;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringView(name = EditorView.VIEW_NAME)
public class EditorView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "edit";

    @PostConstruct
    void init() {
        Path path = Paths.get(".");//FIXME
        addComponent(new EditorUI(path));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        event.getParameters();
        // the view is constructed in the init() method()
    }
}
