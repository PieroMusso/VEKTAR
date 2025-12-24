package com.vektar.controllers.MenuController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class MenuController {

    @FXML
    private void handleSalir(MouseEvent event) {
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleTeoria(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleCalculadora(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handlePractica(MouseEvent event) {
        VistaUtil.cambiarContenido("quiz/quiz2");
    }
}
