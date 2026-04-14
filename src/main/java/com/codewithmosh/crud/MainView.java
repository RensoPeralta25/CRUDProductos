package com.codewithmosh.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout{

    public MainView () {
        TextField ejemplo = new TextField("Ejemplo: ");
        Button button = new Button("Probar", event -> add("Hola " + ejemplo.getValue()));
        add(ejemplo, button);
    }
}
