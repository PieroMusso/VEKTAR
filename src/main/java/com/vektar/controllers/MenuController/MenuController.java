package com.vektar.controllers.MenuController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class MenuController {

    @FXML
    private void handleSalir(MouseEvent event) {
        // Redirigir a la vista inicio.fxml
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleTeoria(MouseEvent event) {
        // Redirigir a la vista MagYDirecc.fxml
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleCalculadora(MouseEvent event) {
        // Redirigir a la vista OpBasic.fxml
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handlePractica(MouseEvent event) {
        // Redirigir a la vista quiz.fxml
        VistaUtil.cambiarContenido("quiz/quiz2");
    }
}
