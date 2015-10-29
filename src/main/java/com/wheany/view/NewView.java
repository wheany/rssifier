package com.wheany.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.wheany.ui.EditorUI;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringView(name = NewView.VIEW_NAME)
public class NewView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    void init() {
        Path path = Paths.get(".");//FIXME
        addComponent(new EditorUI(path));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
