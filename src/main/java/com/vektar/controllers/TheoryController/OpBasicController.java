package com.vektar.controllers.TheoryController;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class OpBasicController {

    @FXML private Slider sliderAX;
    @FXML private Slider sliderAY;
    @FXML private Slider sliderBX;
    @FXML private Slider sliderBY;
    @FXML private Label lblAX;
    @FXML private Label lblAY;
    @FXML private Label lblBX;
    @FXML private Label lblBY;
    @FXML private Label lblAHeader;
    @FXML private Label lblBHeader;
    @FXML private Label lblAValue;
    @FXML private Label lblBValue;
    @FXML private Label lblSum;
    @FXML private Label lblDiff;
    @FXML private Label lbl2A;

    @FXML
    public void initialize() {
        
        if (sliderAX != null) {
            sliderAX.setMin(-10);
            sliderAX.setMax(10);
            sliderAX.setValue(0);
            sliderAX.setMajorTickUnit(1);
            sliderAX.setBlockIncrement(1);
        }
        if (sliderAY != null) {
            sliderAY.setMin(-10);
            sliderAY.setMax(10);
            sliderAY.setValue(0);
            sliderAY.setMajorTickUnit(1);
            sliderAY.setBlockIncrement(1);
        }
        if (sliderBX != null) {
            sliderBX.setMin(-10);
            sliderBX.setMax(10);
            sliderBX.setValue(0);
            sliderBX.setMajorTickUnit(1);
            sliderBX.setBlockIncrement(1);
        }
        if (sliderBY != null) {
            sliderBY.setMin(-10);
            sliderBY.setMax(10);
            sliderBY.setValue(0);
            sliderBY.setMajorTickUnit(1);
            sliderBY.setBlockIncrement(1);
        }

        if (lblAX != null && sliderAX != null) lblAX.textProperty().bind(Bindings.format("%.2f", sliderAX.valueProperty()));
        if (lblAY != null && sliderAY != null) lblAY.textProperty().bind(Bindings.format("%.2f", sliderAY.valueProperty()));
        if (lblBX != null && sliderBX != null) lblBX.textProperty().bind(Bindings.format("%.2f", sliderBX.valueProperty()));
        if (lblBY != null && sliderBY != null) lblBY.textProperty().bind(Bindings.format("%.2f", sliderBY.valueProperty()));

        if (lblAValue != null && sliderAX != null && sliderAY != null) {
            lblAValue.textProperty().bind(Bindings.format("(%.0f, %.0f)", sliderAX.valueProperty(), sliderAY.valueProperty()));
        }
        if (lblBValue != null && sliderBX != null && sliderBY != null) {
            lblBValue.textProperty().bind(Bindings.format("(%.0f, %.0f)", sliderBX.valueProperty(), sliderBY.valueProperty()));
        }

        
        if (sliderAX != null && sliderAY != null && sliderBX != null && sliderBY != null) {
            sliderAX.valueProperty().addListener((o,old,v) -> updateSummary());
            sliderAY.valueProperty().addListener((o,old,v) -> updateSummary());
            sliderBX.valueProperty().addListener((o,old,v) -> updateSummary());
            sliderBY.valueProperty().addListener((o,old,v) -> updateSummary());
            updateSummary();
        }
    }

    private void updateSummary() {
        double ax = sliderAX.getValue();
        double ay = sliderAY.getValue();
        double bx = sliderBX.getValue();
        double by = sliderBY.getValue();

        double sx = ax + bx;
        double sy = ay + by;
        double dx = ax - bx;
        double dy = ay - by;
        double twoax = 2 * ax;
        double twoay = 2 * ay;

        if (lblSum != null) lblSum.setText(String.format("(%.0f, %.0f)", sx, sy));
        if (lblDiff != null) lblDiff.setText(String.format("(%.0f, %.0f)", dx, dy));
        if (lbl2A != null) lbl2A.setText(String.format("(%.0f, %.0f)", twoax, twoay));
    }

    @FXML
    private void handleMenu(MouseEvent event) {
        System.out.println("handleMenu invoked in OpBasicController");
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        System.out.println("handleSiguiente invoked in OpBasicController");
        VistaUtil.cambiarSiguiente("theory/OpBasic");
    }

    @FXML
    private void handleInicio(MouseEvent event) {
        System.out.println("handleInicio invoked in OpBasicController");
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleMagnitud(MouseEvent event) {
        System.out.println("handleMagnitud invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleOB(MouseEvent event) {
        System.out.println("handleOB invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handleEV(MouseEvent event) {
        System.out.println("handleEV invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/EspVect");
    }

    @FXML
    private void handlePP(MouseEvent event) {
        System.out.println("handlePP invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/PP");
    }

    @FXML
    private void handlePC(MouseEvent event) {
        System.out.println("handlePC invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/PC");
    }

    @FXML
    private void handleFisica(MouseEvent event) {
        System.out.println("handleFisica invoked in OpBasicController");
        VistaUtil.cambiarContenido("theory/Fisica");
    }

    @FXML
    private void handleQuiz(MouseEvent event) {
        System.out.println("handleQuiz invoked in OpBasicController");
        VistaUtil.cambiarContenido("quiz/quiz1");
    }
}