package com.vektar.controllers.TheoryController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;
public class EVController {

    @FXML
    private void handleMenu(MouseEvent event) {
        // Redirigir a la vista menu.fxml
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        // Avanzar según el orden del menú
        VistaUtil.cambiarSiguiente("theory/EspVect");
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
    private void handleEV(MouseEvent event) {
        // Alias - recargar o mantener EspVect
        System.out.println("handleEV invoked in EVController");
        VistaUtil.cambiarContenido("theory/EspVect");
    }

    @FXML
    private void handleOB(MouseEvent event) {
        // Redirigir a la vista OpBasic.fxml
        VistaUtil.cambiarContenido("theory/OpBasic");
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
        // Ir a quiz1
        VistaUtil.cambiarContenido("quiz/quiz1");
    }

}
