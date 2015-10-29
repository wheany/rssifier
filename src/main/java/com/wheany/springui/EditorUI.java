package com.wheany.springui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wheany.view.EditorView;
import com.wheany.view.NewView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Theme("rssifier")
@SpringUI(path="rssifier/edit")
public class EditorUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        String id = request.getParameter("id");
        if (id == null) {
            getPage().open("/rssifier", null, false);
        } else {
            System.out.println("EditorUI init, ID:" + id);
        }

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(new Navigator.StaticViewProvider("", new EditorView(id)));

    }
}
