package com.vektar.controllers.MenuController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class InicioController {
    @FXML
    private void handleEmpezar(MouseEvent event) {
        VistaUtil.cambiarContenido("menu/menu");
    }
}
