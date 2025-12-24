package com.vektar.util;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class VistaUtil {

    private static final String BASE_FXML = "/com/vektar/views/";
    private static AnchorPane contenedorPrincipal;

    /**
     * Registra el contenedor central (por ejemplo: contenidoPrincipal de Main.fxml)
     */
    public static void setContenedorPrincipal(AnchorPane contenedor) {
        contenedorPrincipal = contenedor;
    }

    /**
     * Cambia el contenido dentro del contenedor principal.
     * @param rutaFXML Ruta relativa desde /view/ (sin extensión)
     */
    public static void cambiarContenido(String rutaFXML) {
        if (contenedorPrincipal == null) {
            System.err.println("[✗] El contenedor principal aún no ha sido inicializado con setContenedorPrincipal.");
            return;
        }

        try {
            URL fxmlUrl = VistaUtil.class.getResource(BASE_FXML + rutaFXML + ".fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML no encontrado: " + BASE_FXML + rutaFXML + ".fxml");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent vista = loader.load();

            contenedorPrincipal.getChildren().setAll(vista);

            // Anclar si el contenedor es AnchorPane
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            System.out.println("[✔] Contenido cargado correctamente: " + rutaFXML);

        } catch (IOException e) {
            System.err.println("[✗] Error al cambiar contenido: " + rutaFXML);
            e.printStackTrace();
        }
    }

    /**
     * Orden de navegación para el botón "Siguiente" basada en el menú lateral.
     * Si se encuentra la ruta actual, devuelve la siguiente; si es la última, vuelve al menú inicio.
     */
    private static final String[] SEQUENCE = new String[]{
            "theory/MagYDirecc",
            "theory/OpBasic",
            "theory/EspVect",
            "theory/PP",
            "theory/PC",
            "theory/Fisica",
            "quiz/quiz1"
    };

    public static String siguiente(String rutaActual) {
        if (rutaActual == null) return SEQUENCE[0];
        for (int i = 0; i < SEQUENCE.length; i++) {
            if (SEQUENCE[i].equals(rutaActual)) {
                if (i + 1 < SEQUENCE.length) return SEQUENCE[i + 1];
                // al final vuelve al inicio del menú
                return "menu/inicio";
            }
        }
        // si no se encuentra, regresar al primer elemento
        return SEQUENCE[0];
    }

    public static void cambiarSiguiente(String rutaActual) {
        cambiarContenido(siguiente(rutaActual));
    }
}