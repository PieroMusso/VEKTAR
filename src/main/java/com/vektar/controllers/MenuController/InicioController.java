package com.vektar.controllers.MenuController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class InicioController {
    @FXML
    private void handleEmpezar(MouseEvent event) {
        // Usar VistaUtil para cambiar el contenido al archivo menu.fxml
        VistaUtil.cambiarContenido("menu/menu");
    }
}
