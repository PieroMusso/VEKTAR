package com.vektar.controllers.TheoryController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Box;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import com.vektar.util.VistaUtil;

public class Vector3DController implements Initializable {

    @FXML private Pane subsceneContainer;
    @FXML private TextField axField, ayField, azField;
    @FXML private TextField bxField, byField, bzField;
    @FXML private TextArea resultArea;
    @FXML private ImageView btnAgregarA;
    @FXML private ImageView btnAgregarB;
    @FXML private ImageView btnAgregar;
    @FXML private ImageView btnCalcular;
    @FXML private ImageView btnRegresar;
    @FXML private Label lblSuma, lblResta, lblPP, lblPC, lblMgA, lblMgB;
    @FXML private Label lblSuma1;
    @FXML private Label lblFSuma;
    @FXML private Label lblFResta;
    @FXML private Label lblFPP;
    @FXML private Label lblFPC;

    private SubScene subScene;
    private Group world = new Group();
    private Group vectorsGroup = new Group();
    private Group resultVectorsGroup = new Group();
    private final double SCALE = 60.0;
    private final int TICK_RANGE = 10;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);
    private Translate cameraTranslate = new Translate(0, 0, -800);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        world.getTransforms().addAll(rotateX, rotateY);
        buildAxes();
        world.getChildren().add(vectorsGroup);
        vectorsGroup.getChildren().add(resultVectorsGroup);

        
        if (btnAgregar != null) {
            System.out.println("btnAgregar (single) wired");
            btnAgregar.setOnMouseClicked(e -> onAddA());
        }
        if (btnAgregarA != null) {
            System.out.println("btnAgregarA wired");
            btnAgregarA.setOnMouseClicked(e -> onAddA());
        } else {
            System.out.println("btnAgregarA is null in initialize");
        }
        if (btnAgregarB != null) {
            System.out.println("btnAgregarB wired");
            btnAgregarB.setOnMouseClicked(e -> onAddB());
        } else {
            System.out.println("btnAgregarB is null in initialize");
        }
        if (btnCalcular != null) btnCalcular.setOnMouseClicked(e -> calculateAllFormulas());
        if (btnRegresar != null) btnRegresar.setOnMouseClicked(e -> onBack());
        if (lblSuma1 != null) lblSuma1.setOnMouseClicked(e -> onClear());

        subScene = new SubScene(world, 800, 600, true, null);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.getTransforms().add(cameraTranslate);
        subScene.setCamera(camera);
        subScene.setFill(Color.web("#f4f6f8"));

        subsceneContainer.getChildren().add(subScene);

        subsceneContainer.widthProperty().addListener((obs, oldV, newV) -> subScene.setWidth(newV.doubleValue()));
        subsceneContainer.heightProperty().addListener((obs, oldV, newV) -> subScene.setHeight(newV.doubleValue()));

        
        subScene.setOnMousePressed(this::onMousePressed);
        subScene.setOnMouseDragged(this::onMouseDragged);
        subScene.setOnScroll(this::onScroll);
    }

    @FXML
    private void onBack() {
        VistaUtil.cambiarContenido("menu/menu");
    }

    private void buildAxes() {
        double axisLength = (TICK_RANGE + 2) * SCALE;
        PhongMaterial matX = new PhongMaterial(Color.web("#2e8b57"));
        PhongMaterial matY = new PhongMaterial(Color.web("#d64545"));
        PhongMaterial matZ = new PhongMaterial(Color.web("#2f6fd6"));

        Cylinder x = createCylinder(axisLength, 3, matX);
        x.getTransforms().addAll(new Rotate(90, Rotate.Z_AXIS), new Translate(axisLength/2 - SCALE, 0, 0));

        Cylinder y = createCylinder(axisLength, 3, matY);
        y.getTransforms().addAll(new Translate(0, -axisLength/2 + SCALE, 0));

        Cylinder z = createCylinder(axisLength, 3, matZ);
        z.getTransforms().addAll(new Rotate(90, Rotate.X_AXIS), new Translate(0, 0, axisLength/2 - SCALE));

        
        Box plane = new Box(axisLength * 1.1, 2, axisLength * 1.1);
        PhongMaterial planeMat = new PhongMaterial(Color.web("#dddddd"));
        planeMat.setSpecularColor(Color.web("#bbbbbb"));
        plane.setMaterial(planeMat);
        plane.setTranslateY(1); // slight offset
        plane.setOpacity(0.7);

        
        for (int i = -TICK_RANGE; i <= TICK_RANGE; i++) {
            if (i == 0) continue;
            double px = i * SCALE;
            
            Sphere tx = new Sphere(3);
            tx.setMaterial(matX);
            tx.getTransforms().add(new Translate(px, 0, 0));
            Text lx = createLabel3D(Integer.toString(i), px, -8, 0, matX.getDiffuseColor());

            
            Sphere tz = new Sphere(3);
            tz.setMaterial(matZ);
            tz.getTransforms().add(new Translate(0, 0, px));
            Text lz = createLabel3D(Integer.toString(i), 8, -8, px, matZ.getDiffuseColor());

            
            Sphere ty = new Sphere(3);
            ty.setMaterial(matY);
            ty.getTransforms().add(new Translate(0, -px, 0));
            Text ly = createLabel3D(Integer.toString(i), -8, -px, 0, matY.getDiffuseColor());

            world.getChildren().addAll(tx, tz, ty, lx, lz, ly);
        }

        
        MeshView arrowX = createCone(12, 24, matX);
        arrowX.getTransforms().addAll(new Rotate(90, Rotate.Z_AXIS), new Translate(axisLength - SCALE, 0, 0));
        MeshView arrowY = createCone(12, 24, matY);
        arrowY.getTransforms().addAll(new Translate(0, -axisLength + SCALE, 0));
        MeshView arrowZ = createCone(12, 24, matZ);
        arrowZ.getTransforms().addAll(new Rotate(-90, Rotate.X_AXIS), new Translate(0, 0, axisLength - SCALE));

        
        Text labelX = createLabel3D("x", axisLength - 0.5 * SCALE, -12, 0, matX.getDiffuseColor());
        Text labelY = createLabel3D("y", 0, -axisLength + 0.5 * SCALE, 12, matY.getDiffuseColor());
        Text labelZ = createLabel3D("z", 12, -12, axisLength - 0.5 * SCALE, matZ.getDiffuseColor());

        
        AmbientLight ambient = new AmbientLight(Color.color(0.7,0.7,0.7));
        PointLight light = new PointLight(Color.WHITE);
        light.getTransforms().add(new Translate(-axisLength/2, -axisLength/2, -axisLength/2));

        world.getChildren().addAll(plane, x, y, z, arrowX, arrowY, arrowZ, labelX, labelY, labelZ, ambient, light);
    }

    private Text createLabel3D(String text, double x, double y, double z, Color color) {
        Text t = new Text(text);
        t.setFont(Font.font(14));
        t.setFill(color);
        t.getTransforms().add(new Translate(x, y, z));
        return t;
    }

    private MeshView createCone(float radius, float height, PhongMaterial mat) {
        int divisions = 20;
        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(0, 0, 0);
        
        for (int i = 0; i <= divisions; i++) {
            double angle = 2 * Math.PI * i / divisions;
            float px = (float)(radius * Math.cos(angle));
            float pz = (float)(radius * Math.sin(angle));
            mesh.getPoints().addAll(px, height, pz);
        }
        
        mesh.getTexCoords().addAll(0,0);
        
        for (int i = 1; i <= divisions; i++) {
            int p1 = i;
            int p2 = i % divisions + 1;
            mesh.getFaces().addAll(0,0,p2,0,p1,0);
        }
        MeshView cone = new MeshView(mesh);
        cone.setMaterial(mat);
        return cone;
    }

    private Cylinder createCylinder(double height, double radius, PhongMaterial mat) {
        Cylinder c = new Cylinder(radius, height);
        c.setMaterial(mat);
        return c;
    }

    private void onMousePressed(MouseEvent event) {
        anchorX = event.getSceneX();
        anchorY = event.getSceneY();
        anchorAngleX = rotateX.getAngle();
        anchorAngleY = rotateY.getAngle();
    }

    private void onMouseDragged(MouseEvent event) {
        rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()) * 0.4);
        rotateX.setAngle(anchorAngleX + (event.getSceneY() - anchorY) * 0.4);
    }

    private void onScroll(ScrollEvent event) {
        double dz = event.getDeltaY();
        cameraTranslate.setZ(cameraTranslate.getZ() + dz);
    }

    @FXML
    private void onAddA() {
        System.out.println("onAddA invoked");
        try {
            double ax = Double.parseDouble(axField.getText());
            double ay = Double.parseDouble(ayField.getText());
            double az = Double.parseDouble(azField.getText());
            addVector(ax, ay, az, Color.ORANGE, "A");
            StringBuilder out = new StringBuilder();
            out.append(String.format("Vector A = (%.3f, %.3f, %.3f)\n", ax, ay, az));

            // try to add B as well if fields are present and non-empty
            if (bxField != null && byField != null && bzField != null
                    && !bxField.getText().isBlank() && !byField.getText().isBlank() && !bzField.getText().isBlank()) {
                try {
                    double bx = Double.parseDouble(bxField.getText());
                    double by = Double.parseDouble(byField.getText());
                    double bz = Double.parseDouble(bzField.getText());
                    addVector(bx, by, bz, Color.PURPLE, "B");
                    out.append(String.format("Vector B = (%.3f, %.3f, %.3f)\n", bx, by, bz));
                } catch (NumberFormatException ex) {
                    out.append("Valores inválidos para B\n");
                }
            }

            if (resultArea != null) resultArea.appendText(out.toString());
            updateFormulasLabels();
        } catch (NumberFormatException e) {
            if (resultArea != null) resultArea.appendText("Valores inválidos para A\n");
        }
    }

    @FXML
    private void onAddB() {
        try {
            double bx = Double.parseDouble(bxField.getText());
            double by = Double.parseDouble(byField.getText());
            double bz = Double.parseDouble(bzField.getText());
            addVector(bx, by, bz, Color.PURPLE, "B");
            if (resultArea != null) resultArea.appendText(String.format("Vector B = (%.3f, %.3f, %.3f)\n", bx, by, bz));
            updateFormulasLabels();
        } catch (NumberFormatException e) {
            if (resultArea != null) resultArea.appendText("Valores inválidos para B\n");
        }
    }

    @FXML
    private void onDot() {
        try {
            double ax = Double.parseDouble(axField.getText());
            double ay = Double.parseDouble(ayField.getText());
            double az = Double.parseDouble(azField.getText());
            double bx = Double.parseDouble(bxField.getText());
            double by = Double.parseDouble(byField.getText());
            double bz = Double.parseDouble(bzField.getText());
            double dot = ax*bx + ay*by + az*bz;
            if (resultArea != null) resultArea.appendText(String.format("A·B = %.6f\n", dot));
        } catch (NumberFormatException e) {
            if (resultArea != null) resultArea.appendText("Valores inválidos para producto punto\n");
        }
    }

    @FXML
    private void onCross() {
        try {
            double ax = Double.parseDouble(axField.getText());
            double ay = Double.parseDouble(ayField.getText());
            double az = Double.parseDouble(azField.getText());
            double bx = Double.parseDouble(bxField.getText());
            double by = Double.parseDouble(byField.getText());
            double bz = Double.parseDouble(bzField.getText());
            double cx = ay*bz - az*by;
            double cy = az*bx - ax*bz;
            double cz = ax*by - ay*bx;
            addVector(cx, cy, cz, Color.DARKCYAN, "A×B");
            if (resultArea != null) resultArea.appendText(String.format("A×B = (%.6f, %.6f, %.6f)\n", cx, cy, cz));
        } catch (NumberFormatException e) {
            if (resultArea != null) resultArea.appendText("Valores inválidos para producto cruz\n");
        }
    }

    @FXML
    private void onClear() {
        // remove only vectors and clear results and inputs silently
        vectorsGroup.getChildren().clear();
        if (resultArea != null) resultArea.clear();
        // clear input fields
        if (axField != null) axField.clear();
        if (ayField != null) ayField.clear();
        if (azField != null) azField.clear();
        if (bxField != null) bxField.clear();
        if (byField != null) byField.clear();
        if (bzField != null) bzField.clear();
        // reset compact labels
        if (lblSuma != null) lblSuma.setText("0");
        if (lblResta != null) lblResta.setText("0");
        if (lblPP != null) lblPP.setText("0");
        if (lblPC != null) lblPC.setText("0");
        if (lblMgA != null) lblMgA.setText("0");
        if (lblMgB != null) lblMgB.setText("0");
        // restore formula labels to their original templates
        if (lblFSuma != null) lblFSuma.setText("(ax+bx, ay+by, az+bz)");
        if (lblFResta != null) lblFResta.setText("(ax-bx, ay-by, az-bz)");
        if (lblFPP != null) lblFPP.setText("ax·bx + ay·by + az·bz");
        if (lblFPC != null) lblFPC.setText("(ay·bz - az·by, az·bx - ax·bz, ax·by - ay·bx)");
        // mark todo step completed (simple console log)
        try { System.out.println("Formula labels cleared by onClear"); } catch (Throwable t) {}
    }

    private void addVector(double x, double y, double z, Color color, String name) {
        // Convert to JavaFX coordinates: use SCALE and invert Y for intuitive up
        double sx = x * SCALE;
        double sy = -y * SCALE;
        double sz = z * SCALE;

        double len = Math.sqrt(sx*sx + sy*sy + sz*sz);
        if (len < 1e-6) return;

        Cylinder shaft = new Cylinder(4, len);
        PhongMaterial mat = new PhongMaterial(color);
        shaft.setMaterial(mat);

        
        shaft.getTransforms().add(new Translate(sx/2, sy/2, sz/2));
        
        
        double ux = 0, uy = -1, uz = 0;
        double vx = sx/len, vy = sy/len, vz = sz/len;
        
        double cx = uy*vz - uz*vy;
        double cy = uz*vx - ux*vz;
        double cz = ux*vy - uy*vx;
        double crossLen = Math.sqrt(cx*cx+cy*cy+cz*cz);
        double dot = ux*vx + uy*vy + uz*vz;

        if (crossLen > 1e-6) {
            double angle = Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, dot))));
            shaft.getTransforms().add(new Rotate(angle, cx, cy, cz));
        } else if (dot < 0) {
            shaft.getTransforms().add(new Rotate(180, 1, 0, 0));
        }
        
        Sphere tip = new Sphere(8);
        tip.setMaterial(mat);
        tip.getTransforms().add(new Translate(sx, sy, sz));

        vectorsGroup.getChildren().addAll(shaft, tip);
    }

    private void addResultVector(double x, double y, double z, Color color, String name) {
        
        double sx = x * SCALE;
        double sy = -y * SCALE;
        double sz = z * SCALE;

        double len = Math.sqrt(sx*sx + sy*sy + sz*sz);
        if (len < 1e-6) return;

        Cylinder shaft = new Cylinder(3.5, len);
        PhongMaterial mat = new PhongMaterial(color);
        shaft.setMaterial(mat);
        shaft.getTransforms().add(new Translate(sx/2, sy/2, sz/2));

        double ux = 0, uy = -1, uz = 0;
        double vx = sx/len, vy = sy/len, vz = sz/len;
        double cx = uy*vz - uz*vy;
        double cy = uz*vx - ux*vz;
        double cz = ux*vy - uy*vx;
        double crossLen = Math.sqrt(cx*cx+cy*cy+cz*cz);
        double dot = ux*vx + uy*vy + uz*vz;

        if (crossLen > 1e-6) {
            double angle = Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, dot))));
            shaft.getTransforms().add(new Rotate(angle, cx, cy, cz));
        } else if (dot < 0) {
            shaft.getTransforms().add(new Rotate(180, 1, 0, 0));
        }

        Sphere tip = new Sphere(6);
        tip.setMaterial(mat);
        tip.getTransforms().add(new Translate(sx, sy, sz));

        Text lbl = createLabel3D(name, sx + 12, sy + 12, sz + 12, color);

        resultVectorsGroup.getChildren().addAll(shaft, tip, lbl);
    }

    @FXML
    private void onShowFormulas() {
        String formulas = "Operaciones básicas:\n\n" +
                "Suma: A + B = (ax+bx, ay+by, az+bz)\n" +
                "Resta: A - B = (ax-bx, ay-by, az-bz)\n" +
                "Producto punto: A·B = ax*bx + ay*by + az*bz\n" +
                "Producto cruz: A×B = (ay*bz - az*by, az*bx - ax*bz, ax*by - ay*bx)\n" +
                "Magnitud: |A| = sqrt(ax^2 + ay^2 + az^2)\n" +
                "Vector unitario: Â = A / |A|\n";

        // show a modal window with formulas and a Calcular button
        Stage dialog = new Stage();
        dialog.initOwner(subsceneContainer.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Formulas");

        TextArea ta = new TextArea(formulas);
        ta.setEditable(false);
        ta.setWrapText(true);
        ta.setPrefWidth(420);
        ta.setPrefHeight(300);

        Button calc = new Button("Calcular");
        calc.setOnAction(evt -> calculateAllFormulas());

        HBox buttons = new HBox(8, calc);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(8, ta, buttons);
        root.setPadding(new Insets(10));

        Scene sc = new Scene(root);
        dialog.setScene(sc);
        dialog.show();
    }

    private void calculateAllFormulas() {
        // read fields safely; if missing, treat as 0
        double ax = parseOrZero(axField.getText());
        double ay = parseOrZero(ayField.getText());
        double az = parseOrZero(azField.getText());
        double bx = parseOrZero(bxField.getText());
        double by = parseOrZero(byField.getText());
        double bz = parseOrZero(bzField.getText());

        double sx = ax + bx, sy = ay + by, sz = az + bz;
        double dx = ax - bx, dy = ay - by, dz = az - bz;
        double dot = ax*bx + ay*by + az*bz;
        double cx = ay*bz - az*by;
        double cy = az*bx - ax*bz;
        double cz = ax*by - ay*bx;
        double magA = Math.sqrt(ax*ax + ay*ay + az*az);
        double magB = Math.sqrt(bx*bx + by*by + bz*bz);

        // formatting: labels and scalar results must be integers (rounded)
        java.util.Locale loc = java.util.Locale.US;
        java.util.function.Function<Double, String> fmtSmart = (v) -> {
            double rv = Math.rint(v);
            if (Math.abs(rv - v) < 1e-9) return String.format(loc, "%d", (long)rv);
            return String.format(loc, "%.2f", v);
        };
        java.util.function.Function<Double, String> fmtInt = (v) -> String.format(loc, "%.0f", Math.rint(v));

        String unitA = (magA < 1e-9) ? "(0,0,0)" : String.format("(%s, %s, %s)", fmtSmart.apply(ax/magA), fmtSmart.apply(ay/magA), fmtSmart.apply(az/magA));
        String unitB = (magB < 1e-9) ? "(0,0,0)" : String.format("(%s, %s, %s)", fmtSmart.apply(bx/magB), fmtSmart.apply(by/magB), fmtSmart.apply(bz/magB));

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("A = (%s, %s, %s)\n", fmtSmart.apply(ax), fmtSmart.apply(ay), fmtSmart.apply(az)));
        sb.append(String.format("B = (%s, %s, %s)\n\n", fmtSmart.apply(bx), fmtSmart.apply(by), fmtSmart.apply(bz)));
        sb.append(String.format("Suma A+B = (%s, %s, %s)\n", fmtSmart.apply(sx), fmtSmart.apply(sy), fmtSmart.apply(sz)));
        sb.append(String.format("Resta A-B = (%s, %s, %s)\n", fmtSmart.apply(dx), fmtSmart.apply(dy), fmtSmart.apply(dz)));
        // scalar/vector results (use smart formatting for components and magnitudes)
        sb.append(String.format("Producto punto A·B = %s\n", fmtSmart.apply(dot)));
        sb.append(String.format("Producto cruz A×B = (%s, %s, %s)\n", fmtSmart.apply(cx), fmtSmart.apply(cy), fmtSmart.apply(cz)));
        sb.append(String.format("|A| = %s\n", fmtSmart.apply(magA)));
        sb.append(String.format("|B| = %s\n", fmtSmart.apply(magB)));
        sb.append(String.format("Unitario Â = %s\n", unitA));
        sb.append(String.format("Unitario B̂ = %s\n", unitB));

        if (resultArea != null) resultArea.setText(sb.toString());

        // also update the formula labels to reflect current inputs
        updateFormulasLabels();

        // remove previous calculated result vectors
        resultVectorsGroup.getChildren().clear();

        // draw resultant vectors (A+B and A×B)
        if (Math.abs(sx) > 1e-9 || Math.abs(sy) > 1e-9 || Math.abs(sz) > 1e-9) {
            addResultVector(sx, sy, sz, Color.LIMEGREEN, "A+B");
        }
        if (Math.abs(cx) > 1e-9 || Math.abs(cy) > 1e-9 || Math.abs(cz) > 1e-9) {
            addResultVector(cx, cy, cz, Color.DARKCYAN, "A×B");
        }

        // update compact labels: show vectors where appropriate, dot as scalar, magnitudes with 2 decimals
        String sumaVec = String.format("(%s, %s, %s)", fmtSmart.apply(sx), fmtSmart.apply(sy), fmtSmart.apply(sz));
        String restaVec = String.format("(%s, %s, %s)", fmtSmart.apply(dx), fmtSmart.apply(dy), fmtSmart.apply(dz));
        String dotVal = fmtSmart.apply(dot);
        String crossVec = String.format("(%s, %s, %s)", fmtSmart.apply(cx), fmtSmart.apply(cy), fmtSmart.apply(cz));
        String magAVal = fmtSmart.apply(magA);
        String magBVal = fmtSmart.apply(magB);

        if (lblSuma != null) lblSuma.setText(sumaVec);
        if (lblResta != null) lblResta.setText(restaVec);
        if (lblPP != null) lblPP.setText(dotVal);
        if (lblPC != null) lblPC.setText(crossVec);
        if (lblMgA != null) lblMgA.setText(magAVal);
        if (lblMgB != null) lblMgB.setText(magBVal);
    }

    private double parseOrZero(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }

    private String fmtSmart(double v) {
        java.util.Locale loc = java.util.Locale.US;
        double rv = Math.rint(v);
        if (Math.abs(rv - v) < 1e-9) return String.format(loc, "%d", (long)rv);
        return String.format(loc, "%.2f", v);
    }

    private void updateFormulasLabels() {
        // build formulas substituting numeric components but DO NOT evaluate results
        double ax = parseOrZero(axField != null ? axField.getText() : "0");
        double ay = parseOrZero(ayField != null ? ayField.getText() : "0");
        double az = parseOrZero(azField != null ? azField.getText() : "0");
        double bx = parseOrZero(bxField != null ? bxField.getText() : "0");
        double by = parseOrZero(byField != null ? byField.getText() : "0");
        double bz = parseOrZero(bzField != null ? bzField.getText() : "0");

        if (lblFSuma != null) {
            lblFSuma.setText(String.format("(%s+%s, %s+%s, %s+%s)", fmtSmart(ax), fmtSmart(bx), fmtSmart(ay), fmtSmart(by), fmtSmart(az), fmtSmart(bz)));
        }
        if (lblFResta != null) {
            lblFResta.setText(String.format("(%s-%s, %s-%s, %s-%s)", fmtSmart(ax), fmtSmart(bx), fmtSmart(ay), fmtSmart(by), fmtSmart(az), fmtSmart(bz)));
        }
        if (lblFPP != null) {
            lblFPP.setText(String.format("%s·%s + %s·%s + %s·%s", fmtSmart(ax), fmtSmart(bx), fmtSmart(ay), fmtSmart(by), fmtSmart(az), fmtSmart(bz)));
        }
        if (lblFPC != null) {
            lblFPC.setText(String.format("(%s·%s - %s·%s, %s·%s - %s·%s, %s·%s - %s·%s)",
                    fmtSmart(ay), fmtSmart(bz), fmtSmart(az), fmtSmart(by),
                    fmtSmart(az), fmtSmart(bx), fmtSmart(ax), fmtSmart(bz),
                    fmtSmart(ax), fmtSmart(by), fmtSmart(ay), fmtSmart(bx)));
        }
    }

}
