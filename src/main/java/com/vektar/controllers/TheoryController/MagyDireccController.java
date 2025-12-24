package com.vektar.controllers.TheoryController;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import com.vektar.util.VistaUtil;

public class MagyDireccController {

    @FXML private Slider sliderX;
    @FXML private Slider sliderY;
    @FXML private Label lblX;
    @FXML private Label lblY;
    @FXML private Label lblMag;
    @FXML private Label lblDir;
    @FXML private Label lblMagValue;
    @FXML private Label lblDirValue;
    @FXML private Canvas vectorCanvas;
    @FXML private Label lblVectorHeader;
    

    @FXML
    public void initialize() {
        // Configure sliders
        sliderX.setMin(-5);
        sliderX.setMax(5);
        sliderX.setValue(0);
        sliderX.setMajorTickUnit(1);
        sliderX.setBlockIncrement(0.1);

        sliderY.setMin(-5);
        sliderY.setMax(5);
        sliderY.setValue(0);
        sliderY.setMajorTickUnit(1);
        sliderY.setBlockIncrement(0.1);

        // Bind labels to slider values
        lblX.textProperty().bind(Bindings.format("%.2f", sliderX.valueProperty()));
        lblY.textProperty().bind(Bindings.format("%.2f", sliderY.valueProperty()));

        // Bind header to show current vector components
        if (lblVectorHeader != null) {
            lblVectorHeader.textProperty().bind(
                Bindings.format("Vector A = (%.2f, %.2f)", sliderX.valueProperty(), sliderY.valueProperty())
            );
        }

        // Redraw on changes
        sliderX.valueProperty().addListener((o,old,v) -> draw());
        sliderY.valueProperty().addListener((o,old,v) -> draw());
        vectorCanvas.widthProperty().addListener((o,old,v) -> draw());
        vectorCanvas.heightProperty().addListener((o,old,v) -> draw());

        // Initial draw
        draw();
    }

    private void draw() {
        double x = sliderX.getValue();
        double y = sliderY.getValue();

        double w = vectorCanvas.getWidth();
        double h = vectorCanvas.getHeight();
        if (w <= 0 || h <= 0) return;

        GraphicsContext gc = vectorCanvas.getGraphicsContext2D();
        gc.clearRect(0,0,w,h);

        // background
        gc.setFill(Color.web("#ffffff"));
        gc.fillRect(0,0,w,h);

        double cx = w/2.0;
        double cy = h/2.0;

        // logical span [-5,5] => 10 units
        double span = 10.0;
        double scale = Math.min(w, h) / (span * 1.1); // leave margin

        // draw axes
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);
        // vertical
        gc.strokeLine(cx, 0, cx, h);
        // horizontal
        gc.strokeLine(0, cy, w, cy);

        // ticks
        gc.setStroke(Color.web("#e0e0e0"));
        for (int i=-5;i<=5;i++){
            double tx = cx + i*scale;
            gc.strokeLine(tx, cy-5, tx, cy+5);
            double ty = cy - i*scale;
            gc.strokeLine(cx-5, ty, cx+5, ty);
        }

        // compute pixel coords
        double px = cx + x * scale;
        double py = cy - y * scale;

        // draw vector
        gc.setStroke(Color.DODGERBLUE);
        gc.setLineWidth(4);
        gc.strokeLine(cx, cy, px, py);

        // arrow head
        double angle = Math.atan2(py - cy, px - cx);
        double head = 12; // px
        double phi = Math.toRadians(25);
        double x1 = px - head * Math.cos(angle - phi);
        double y1 = py - head * Math.sin(angle - phi);
        double x2 = px - head * Math.cos(angle + phi);
        double y2 = py - head * Math.sin(angle + phi);
        gc.setFill(Color.DODGERBLUE);
        gc.fillPolygon(new double[]{px, x1, x2}, new double[]{py, y1, y2}, 3);

        // center point
        gc.setFill(Color.BLACK);
        gc.fillOval(cx-3, cy-3, 6, 6);

        // labels
        double mag = Math.hypot(x,y);
        double deg = Math.toDegrees(Math.atan2(y, x));
        if (lblMagValue != null) lblMagValue.setText(String.format("%.2f", mag));
        if (lblDirValue != null) lblDirValue.setText(String.format("%.2f°", deg));

        gc.setFill(Color.web("#0b66d0"));
        gc.fillText(String.format("(%.2f, %.2f)", x, y), px + 8, py - 8);
    }

    @FXML
    private void handleMenu(MouseEvent event) {
        System.out.println("handleMenu invoked in MagyDireccController");
        VistaUtil.cambiarContenido("menu/menu");
    }

    @FXML
    private void handleSiguiente(MouseEvent event) {
        System.out.println("handleSiguiente invoked in MagyDireccController");
        VistaUtil.cambiarSiguiente("theory/MagYDirecc");
    }

    @FXML
    private void handleInicio(MouseEvent event) {
        System.out.println("handleInicio invoked in MagyDireccController");
        VistaUtil.cambiarContenido("menu/inicio");
    }

    @FXML
    private void handleMagnitud(MouseEvent event) {
        // Manejo del botón Magnitud — por ahora recarga la misma vista
        System.out.println("handleMagnitud invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/MagYDirecc");
    }

    @FXML
    private void handleOB(MouseEvent event) {
        System.out.println("handleOB invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/OpBasic");
    }

    @FXML
    private void handleEV(MouseEvent event) {
        System.out.println("handleEV invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/EspVect");
    }

    @FXML
    private void handlePP(MouseEvent event) {
        System.out.println("handlePP invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/PP");
    }

    @FXML
    private void handlePC(MouseEvent event) {
        System.out.println("handlePC invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/PC");
    }

    @FXML
    private void handleFisica(MouseEvent event) {
        System.out.println("handleFisica invoked in MagyDireccController");
        VistaUtil.cambiarContenido("theory/Fisica");
    }

    @FXML
    private void handleQuiz(MouseEvent event) {
        System.out.println("handleQuiz invoked in MagyDireccController");
        VistaUtil.cambiarContenido("quiz/quiz1");
    }
}