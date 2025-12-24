package com.vektar.controllers.QuizController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class QIController {

    @FXML
    private void handleMenu(MouseEvent event) {
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        VistaUtil.cambiarSiguiente("quiz/quiz1");
    }

    @FXML
    private void handleInicio(MouseEvent event) {
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleMD(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleMagnitud(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleOB(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handleEV(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/EspVect");
    }

    @FXML
    private void handlePP(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/PP");
    }

    @FXML
    private void handlePC(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/PC");
    }

    @FXML
    private void handleFisica(MouseEvent event) {
        VistaUtil.cambiarContenido("theory/Fisica");
    }

    @FXML
    private void handleQuiz(MouseEvent event) {
        VistaUtil.cambiarContenido("quiz/quiz2");
    }

    @FXML
    private void handleQI(MouseEvent event) {
        VistaUtil.cambiarContenido("quiz/quiz2");
    }
}
