package com.wheany.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.wheany.vaadiunui.EditPageComponent;
import com.wheany.vaadiunui.NewPageComponent;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewView extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new NewPageComponent());
    }
}
