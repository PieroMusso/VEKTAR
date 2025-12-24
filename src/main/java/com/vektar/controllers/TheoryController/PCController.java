package com.vektar.controllers.TheoryController;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;

public class PCController {

    @FXML private Slider sliderAX;
    @FXML private Slider sliderAY;
    @FXML private Slider sliderBX;
    @FXML private Slider sliderBY;
    @FXML private Label lblAXVal;
    @FXML private Label lblAYVal;
    @FXML private Label lblBXVal;
    @FXML private Label lblBYVal;
    @FXML private Label lblSubstitution;
    @FXML private Label lblResult;
    @FXML private Label lblArea;

    @FXML
    public void initialize() {
        if (sliderAX != null) { sliderAX.setMin(-10); sliderAX.setMax(10); sliderAX.setValue(0); sliderAX.setBlockIncrement(1); }
        if (sliderAY != null) { sliderAY.setMin(-10); sliderAY.setMax(10); sliderAY.setValue(0); sliderAY.setBlockIncrement(1); }
        if (sliderBX != null) { sliderBX.setMin(-10); sliderBX.setMax(10); sliderBX.setValue(0); sliderBX.setBlockIncrement(1); }
        if (sliderBY != null) { sliderBY.setMin(-10); sliderBY.setMax(10); sliderBY.setValue(0); sliderBY.setBlockIncrement(1); }

        if (sliderAX != null) { sliderAX.setMajorTickUnit(1); sliderAX.setMinorTickCount(0); sliderAX.setSnapToTicks(true); }
        if (sliderAY != null) { sliderAY.setMajorTickUnit(1); sliderAY.setMinorTickCount(0); sliderAY.setSnapToTicks(true); }
        if (sliderBX != null) { sliderBX.setMajorTickUnit(1); sliderBX.setMinorTickCount(0); sliderBX.setSnapToTicks(true); }
        if (sliderBY != null) { sliderBY.setMajorTickUnit(1); sliderBY.setMinorTickCount(0); sliderBY.setSnapToTicks(true); }

        if (lblAXVal != null && sliderAX != null) lblAXVal.textProperty().bind(Bindings.format("%.0f", sliderAX.valueProperty()));
        if (lblAYVal != null && sliderAY != null) lblAYVal.textProperty().bind(Bindings.format("%.0f", sliderAY.valueProperty()));
        if (lblBXVal != null && sliderBX != null) lblBXVal.textProperty().bind(Bindings.format("%.0f", sliderBX.valueProperty()));
        if (lblBYVal != null && sliderBY != null) lblBYVal.textProperty().bind(Bindings.format("%.0f", sliderBY.valueProperty()));

        if (sliderAX != null && sliderAY != null && sliderBX != null && sliderBY != null) {
            sliderAX.valueProperty().addListener((o,old,v) -> updateCalc());
            sliderAY.valueProperty().addListener((o,old,v) -> updateCalc());
            sliderBX.valueProperty().addListener((o,old,v) -> updateCalc());
            sliderBY.valueProperty().addListener((o,old,v) -> updateCalc());
            updateCalc();
        }
    }

    private void updateCalc() {
        
        double ax = Math.round(sliderAX.getValue());
        double ay = Math.round(sliderAY.getValue());
        double bx = Math.round(sliderBX.getValue());
        double by = Math.round(sliderBY.getValue());
        
        if (lblSubstitution != null) {
            String sub = String.format("(%d)(%d) - (%d)(%d)", (int)ax, (int)by, (int)ay, (int)bx);
            lblSubstitution.setText(sub);
        }

        int cross = (int)(ax * by - ay * bx);
        if (lblResult != null) lblResult.setText(String.format("%d", cross));

        int area = Math.abs(cross);
        if (lblArea != null) lblArea.setText(String.format("%d unidades", area));
    }

    @FXML
    private void handleMenu(MouseEvent event) {
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        VistaUtil.cambiarSiguiente("theory/PC");
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
        VistaUtil.cambiarContenido("quiz/quiz1");
    }
}
