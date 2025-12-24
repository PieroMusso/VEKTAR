package com.vektar.controllers.QuizController;

import com.vektar.util.VistaUtil;
import com.vektar.util.QuizState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ResultadoController {

	@FXML private Label lblScore;

	@FXML private Label lblRegresar;
	@FXML private Label lblMensaje;

	@FXML
	public void initialize() {
		if (lblScore != null) {
			lblScore.setText(String.valueOf(QuizState.lastScore));
		}

		int s = QuizState.lastScore;
		String mensaje = "";
		if (s >= 0 && s <= 3) mensaje = "Sigue Practicando";
		else if (s >= 4 && s <= 6) mensaje = "Bien, pero puedes hacerlo mejor";
		else if (s >= 7 && s <= 9) mensaje = "Muy Bien, sigue así";
		else if (s >= 10) mensaje = "Excelente, eres un crack!";
		if (lblMensaje != null) lblMensaje.setText(mensaje);

		if (lblRegresar != null) {
			lblRegresar.setUnderline(true);
			lblRegresar.setOnMouseClicked(this::handleVolver);
			// change cursor via style
			String prev = lblRegresar.getStyle() == null ? "" : lblRegresar.getStyle();
			lblRegresar.setStyle(prev + "; -fx-cursor: hand;");
		}
	}

	@FXML
	private void handleVolver(MouseEvent e) {
		QuizState.lastScore = 0;
		QuizState.totalQuestions = 0;
		VistaUtil.cambiarContenido("menu/menu");
	}

}
