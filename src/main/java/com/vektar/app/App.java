package com.vektar.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import com.vektar.util.VistaUtil;

/**
 * JavaFX App
 */
public class App extends Application {

   private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // Cargar el layout base Main.fxml con la ruta correcta
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/vektar/Main.fxml"));
        Parent root = loader.load();

        // Register the main container in VistaUtil
        VistaUtil.setContenedorPrincipal((AnchorPane) root);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vektar Desktop");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setMaximized(true);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML("/" + fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}