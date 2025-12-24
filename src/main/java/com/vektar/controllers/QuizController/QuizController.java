package com.vektar.controllers.QuizController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.vektar.util.VistaUtil;
import com.vektar.util.QuizState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizController {

	@FXML private ImageView btnX;
	@FXML private ImageView btnOpcion1;
	@FXML private ImageView btnOpcion2;
	@FXML private ImageView btnOpcion3;
	@FXML private ImageView btnOpcion4;
	@FXML private Label lblContador;
	@FXML private Label lblEnunciado;
	@FXML private Label lblOpcion1;
	@FXML private Label lblOpcion2;
	@FXML private Label lblOpcion3;
	@FXML private Label lblOpcion4;

	private static class Question {
		String enunciado;
		String[] opciones = new String[4];
		int correctIndex; // 0..3
		Question(String e, String a, String b, String c, String d, int idx) {
			enunciado = e;
			opciones[0]=a; opciones[1]=b; opciones[2]=c; opciones[3]=d; correctIndex=idx;
		}
	}

	private final int QUESTIONS_PER_TOPIC = 2;
	private final int TOTAL_QUESTIONS = 10; // 5 temas * 2

	private List<Question> questions = new ArrayList<>();
	private int currentIndex = 0;
	private int score = 0;

	@FXML
	public void initialize() {
		buildQuestionPool();
		QuizState.totalQuestions = TOTAL_QUESTIONS;
		QuizState.lastScore = 0;
		if (!questions.isEmpty()) {
			currentIndex = 0;
			score = 0;
			showQuestion(currentIndex);
		}
	}

	private void buildQuestionPool() {
		// Magnitud y Dirección
		List<Question> mag = new ArrayList<>();
		mag.add(new Question("Dado el vector A = (3, 4), ¿cuál es su magnitud?","3","4","5","7",2));
		mag.add(new Question("¿Cuál es la fórmula correcta para calcular la magnitud de un vector A = (ax, ay)?","ax + ay","√(ax² − ay²)","√(ax² + ay²)","ax² + ay²",2));
		mag.add(new Question("Dado el vector B = (-5, 0), ¿cuál es su dirección medida desde el eje X positivo?","0°","90°","180°","270°",2));
		mag.add(new Question("Un vector tiene componentes (0, -7). ¿Cuál es su dirección?","0°","90°","180°","270°",3));
		mag.add(new Question("Si un vector tiene magnitud 10 y dirección 0°, ¿cuáles son sus componentes?","(0, 10)","(10, 0)","(-10, 0)","(0, -10)",1));
		mag.add(new Question("Dado el vector C = (1, √3), ¿cuál es su dirección aproximada?","30°","45°","60°","90°",2));
		mag.add(new Question("Un vector apunta hacia el segundo cuadrante. ¿Cuál de las siguientes opciones es correcta?","ax > 0, ay > 0","ax < 0, ay > 0","ax < 0, ay < 0","ax > 0, ay < 0",1));
		mag.add(new Question("Dos vectores tienen la misma magnitud pero diferente dirección. ¿Qué se puede afirmar?","Son iguales","Son opuestos","Son diferentes vectores","Son perpendiculares",2));
		mag.add(new Question("¿Cuál es la dirección de un vector que apunta exactamente hacia abajo en el plano cartesiano?","0°","90°","180°","270°",3));
		mag.add(new Question("Un vector tiene componentes (-3, -3). ¿En qué cuadrante se encuentra su dirección?","Primer cuadrante","Segundo cuadrante","Tercer cuadrante","Cuarto cuadrante",2));

		// Operaciones Básicas
		List<Question> opb = new ArrayList<>();
		opb.add(new Question("Dados los vectores A = (2, 3) y B = (1, -1), ¿cuál es A + B?","(3, 2)","(1, 4)","(3, 4)","(1, 2)",0));
		opb.add(new Question("Dados los vectores A = (5, -2) y B = (3, 4), ¿cuál es A − B?","(2, 2)","(8, 2)","(2, -6)","(-2, 6)",2));
		opb.add(new Question("Si A = (4, -1), ¿cuál es el vector 2A?","(8, -2)","(2, -1)","(6, -2)","(4, -2)",0));
		opb.add(new Question("Si B = (-3, 5), ¿cuál es el vector −B?","(-3, 5)","(3, -5)","(3, 5)","(-5, 3)",1));
		opb.add(new Question("Dados los vectores A = (1, 2) y B = (3, 4), ¿cuál es 3A + B?","(6, 10)","(3, 6)","(4, 6)","(6, 8)",0));
		opb.add(new Question("Si A = (0, -2) y B = (5, 1), ¿cuál es A + B?","(5, -1)","(5, 1)","(0, -1)","(5, -3)",0));
		opb.add(new Question("¿Cuál es el resultado de restar el vector (2, -3) al vector (6, 1)?","(4, 4)","(8, -2)","(4, -4)","(-4, 4)",2));
		opb.add(new Question("Si A = (−2, 3), ¿cuál es el vector 4A?","(−8, 12)","(8, −12)","(−6, 12)","(−8, 6)",0));
		opb.add(new Question("Dados los vectores A = (7, 0) y B = (−2, 5), ¿cuál es A + B?","(5, 5)","(9, 5)","(5, −5)","(−9, 5)",0));
		opb.add(new Question("Si A = (3, 3) y B = (−3, −3), ¿cuál es A + B?","(0, 0)","(6, 6)","(−6, −6)","(3, −3)",0));

		// Espacios Vectoriales (reemplazadas por nuevas preguntas)
		List<Question> ev = new ArrayList<>();
		ev.add(new Question("¿Qué es un espacio vectorial?","Conjunto de números","Conjunto con suma y escalar","Conjunto de matrices","Conjunto sin reglas",1));
		ev.add(new Question("¿Qué operaciones definen un espacio vectorial?","Producto y división","Resta y división","Suma y escalar","Producto cruzado",2));
		ev.add(new Question("¿Dónde pertenecen los escalares?","ℕ","ℤ","ℝ","ℂ únicamente",2));
		ev.add(new Question("¿Cuál es el vector nulo en ℝ²?","(1, 0)","(0, 1)","(0, 0)","(−1, 1)",2));
		ev.add(new Question("¿Qué propiedad asegura que u + v ∈ V?","Asociativa","Conmutativa","Cierre","Identidad",2));
		ev.add(new Question("¿Qué elemento cumple v + (−v) = 0?","Escalar","Vector identidad","Inverso aditivo","Base",2));
		ev.add(new Question("¿Qué resulta de 0 · v?","v","−v","0","No existe",2));
		ev.add(new Question("¿Qué ocurre con 1 · v?","0","v","−v","2v",1));
		ev.add(new Question("Todo espacio vectorial contiene:","Solo vectores no nulos","El vector 1","El vector 0","Solo vectores unitarios",2));
		ev.add(new Question("¿ℝ³ es un espacio vectorial?","No","Sí","Solo a veces","Depende",1));

		// Producto cruzado
		List<Question> cross = new ArrayList<>();
		cross.add(new Question("A = (1, 2, 0), B = (3, 4, 0)\nA × B = ?","(0, 0, −2)","(0, 0, −4)","(0, 0, −6)","(0, 0, −10)",0));
		cross.add(new Question("A = (2, −1, 0), B = (1, 3, 0)\nA × B = ?","(0, 0, 5)","(0, 0, −5)","(0, 5, 0)","(5, 0, 0)",0));
		cross.add(new Question("A = (4, 0, 0), B = (0, 3, 0)\nA × B = ?","(0, 0, 12)","(0, 12, 0)","(12, 0, 0)","(0, 0, −12)",0));
		cross.add(new Question("A = (−1, 2, 0), B = (3, 1, 0)\nA × B = ?","(0, 0, −7)","(0, 0, 7)","(0, 7, 0)","(7, 0, 0)",0));
		cross.add(new Question("A = (2, 2, 0), B = (1, 1, 0)\nA × B = ?","(0, 0, 0)","(0, 0, 2)","(0, 0, −2)","(2, 0, 0)",0));
		cross.add(new Question("A = (1, 0, 0), B = (0, 1, 0)\nA × B = ?","(0, 0, 1)","(0, 1, 0)","(1, 0, 0)","(0, 0, −1)",0));
		cross.add(new Question("A = (0, 1, 0), B = (1, 0, 0)\nA × B = ?","(0, 0, 1)","(0, 0, −1)","(1, 0, 0)","(0, 1, 0)",1));
		cross.add(new Question("A = (1, 2, 3), B = (2, 1, 0)\nA × B = ?","(−3, 6, −3)","(−3, 6, 3)","(3, −6, −3)","(3, −6, 3)",0));
		cross.add(new Question("A = (2, −1, 1), B = (1, 1, 1)\nA × B = ?","(−2, −1, 3)","(−2, 1, 3)","(1, −3, 2)","(2, −1, −3)",1));
		cross.add(new Question("A = (0, 2, 1), B = (3, 0, −1)\nA × B = ?","(−2, 3, −6)","(2, 3, −6)","(−2, −3, 6)","(2, −3, 6)",1));

		// Producto punto
		List<Question> dot = new ArrayList<>();
		dot.add(new Question("Sean A = (1, 2) y B = (3, 4). ¿A · B = ?","10","11","12","14",1));
		dot.add(new Question("Sean A = (−2, 5) y B = (4, −1). ¿A · B = ?","−13","−9","3","13",0));
		dot.add(new Question("Sean A = (0, 3) y B = (7, 0). ¿A · B = ?","21","7","3","0",3));
		dot.add(new Question("Sean A = (1, −1) y B = (1, 1). ¿A · B = ?","−2","0","1","2",1));
		dot.add(new Question("Sean A = (2, 3) y B = (2, 3). ¿A · B = ?","13","25","√13","6",0));
		dot.add(new Question("Sean A = (4, 0) y B = (2, 0). ¿A · B = ?","0","2","4","8",3));
		dot.add(new Question("Sean A = (1, 2, 3) y B = (4, −1, 2). ¿A · B = ?","8","9","10","11",0));
		dot.add(new Question("Sean A = (−1, 0, 2) y B = (3, 5, 1). ¿A · B = ?","−1","0","1","5",0));
		dot.add(new Question("Si A = (3, 4), ¿A · A = ?","5","7","25","49",2));
		dot.add(new Question("Sean A = (2, −1) y B = (−2, 1). ¿A · B = ?","−5","−4","0","5",0));

		// For each topic pick 2 random questions
		Random rnd = new Random();
		pickRandomInto(questions, mag, rnd);
		pickRandomInto(questions, opb, rnd);
		pickRandomInto(questions, ev, rnd);
		pickRandomInto(questions, cross, rnd);
		pickRandomInto(questions, dot, rnd);

		// shuffle overall order
		Collections.shuffle(questions, rnd);
		// ensure we have exactly TOTAL_QUESTIONS (in case sources changed)
		if (questions.size() > TOTAL_QUESTIONS) questions = questions.subList(0, TOTAL_QUESTIONS);
	}

	private void pickRandomInto(List<Question> target, List<Question> source, Random rnd) {
		List<Question> copy = new ArrayList<>(source);
		Collections.shuffle(copy, rnd);
		for (int i = 0; i < Math.min(QUESTIONS_PER_TOPIC, copy.size()); i++) target.add(copy.get(i));
	}

	private void showQuestion(int index) {
		if (index < 0 || index >= questions.size()) return;
		Question q = questions.get(index);
		if (lblEnunciado != null) lblEnunciado.setText(q.enunciado);
		if (lblOpcion1 != null) lblOpcion1.setText(q.opciones[0]);
		if (lblOpcion2 != null) lblOpcion2.setText(q.opciones[1]);
		if (lblOpcion3 != null) lblOpcion3.setText(q.opciones[2]);
		if (lblOpcion4 != null) lblOpcion4.setText(q.opciones[3]);
		if (lblContador != null) lblContador.setText(String.valueOf(index+1));
	}

	@FXML
	private void handleX(MouseEvent event) {
		VistaUtil.cambiarContenido("menu/menu");
	}

	private void handleAnswer(int optionIndex) {
		Question q = questions.get(currentIndex);
		if (optionIndex == q.correctIndex) score++;

		// next
		if (currentIndex + 1 >= questions.size()) {
			QuizState.lastScore = score;
			QuizState.totalQuestions = questions.size();
			VistaUtil.cambiarContenido("quiz/resultado");
			return;
		}
		currentIndex++;
		showQuestion(currentIndex);
	}

	@FXML private void handleOpcion1(MouseEvent e) { handleAnswer(0); }
	@FXML private void handleOpcion2(MouseEvent e) { handleAnswer(1); }
	@FXML private void handleOpcion3(MouseEvent e) { handleAnswer(2); }
	@FXML private void handleOpcion4(MouseEvent e) { handleAnswer(3); }

}
