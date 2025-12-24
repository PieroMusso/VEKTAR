package com.vektar.controllers;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.vektar.util.VistaUtil;

public class MainController {

    @FXML
    private AnchorPane contenidoPrincipal;

    @FXML
    public void initialize() {
        System.out.println("MainController inicializado");
        VistaUtil.setContenedorPrincipal(contenidoPrincipal);
        VistaUtil.cambiarContenido("menu/inicio");
    }
}
