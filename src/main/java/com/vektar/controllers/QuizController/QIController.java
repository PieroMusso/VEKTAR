package com.vektar.controllers.QuizController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class QIController {

    @FXML
    private void handleMenu(MouseEvent event) {
        // Redirigir a la vista menu.fxml
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        // Avanzar según el orden (si está al final volverá al menú)
        VistaUtil.cambiarSiguiente("quiz/quiz1");
    }

    @FXML
    private void handleInicio(MouseEvent event) {
        // Redirigir a la vista inicio.fxml
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleMD(MouseEvent event) {
        // Redirigir a la vista MagYDirecc.fxml
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleMagnitud(MouseEvent event) {
        // Alias para compatibilidad con FXML que usa handleMagnitud
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleOB(MouseEvent event) {
        // Redirigir a la vista OpBasic.fxml
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handleEV(MouseEvent event) {
        // Redirigir a la vista EspVect.fxml
        VistaUtil.cambiarContenido("theory/EspVect");
    }

    @FXML
    private void handlePP(MouseEvent event) {
        // Redirigir a la vista PP.fxml
        VistaUtil.cambiarContenido("theory/PP");
    }

    @FXML
    private void handlePC(MouseEvent event) {
        // Redirigir a la vista PC.fxml
        VistaUtil.cambiarContenido("theory/PC");
    }

    @FXML
    private void handleFisica(MouseEvent event) {
        // Redirigir a la vista Fisica.fxml
        VistaUtil.cambiarContenido("theory/Fisica");
    }

    @FXML
    private void handleQuiz(MouseEvent event) {
        // Redirigir a la vista quiz2.fxml
        VistaUtil.cambiarContenido("quiz/quiz2");
    }

    @FXML
    private void handleQI(MouseEvent event) {
        // Botón específico en quiz1 que inicia/va a quiz2
        VistaUtil.cambiarContenido("quiz/quiz2");
    }
}
