package fes.aragon.modelo;

import java.io.IOException;

import fes.aragon.extras.EfectosMusica;
import fes.aragon.extras.MusicaCiclica;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Juego extends Application {
	private GraphicsContext graficos;
	private Group root;// Nodo raiz
	private Scene escena;// Escena de nuestra App en javaFX
	private Canvas hoja;// Hoja en la cual se pintara todo el juego
	private Fondo fondo;
	private Thread hiloCancion;
	private PersonajeAnimacion dinosaurio;
	private Cactus cactus[] = new Cactus[2];
	@SuppressWarnings("unused")
	private CargaTileMap carga = null;
	private int numeroNubes = (int) Math.ceil(Math.random() * (1 - 5) + 5);
	private Nube[] nubes = new Nube[numeroNubes];
	private boolean ventana = true;

	@Override
	public void start(Stage ventana) throws Exception {
		iniciarComponentes();
		pintar();
		eventosTeclado();
		this.ciclo();
		ventana.setScene(escena);// le asignamos al escenario una escena
		ventana.setTitle("VideoJuego");
		ventana.show();// mostramos el escenario
	}

	private void iniciarComponentes() {
		root = new Group();
		escena = new Scene(root, 1200, 900);// definimos el tamaño de la escena
		hoja = new Canvas(1200, 900);// definimos el tamaño de la hoja
		root.getChildren().add(hoja);// agregamos un nuevo nodo al root node
		graficos = hoja.getGraphicsContext2D();
		MusicaCiclica musica = new MusicaCiclica("cancion");
		hiloCancion = new Thread(musica);
		hiloCancion.start();
		fondo = new Fondo(0, 0, "/fes/aragon/recursos/fondo1.jpeg", 5);
		dinosaurio = new PersonajeAnimacion(10, 565, "/fes/aragon/recursos/dino.png", 3);
		carga = new CargaTileMap();
		for (int i = 0; i < cactus.length; i++) {
			int y;
			String ruta1 = "/fes/aragon/recursos/cactus1.png", ruta2 = "/fes/aragon/recursos/cactus2.png";
			if (i == 0) {
				y = 615;
				cactus[i] = new Cactus(y, ruta1, 2);
			} else {
				y = 645;
				cactus[i] = new Cactus(y, ruta2, 2);
			}
		}
		for (int i = 0; i < nubes.length; i++) {
			nubes[i] = new Nube("/fes/aragon/recursos/nube.png", 1);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() throws Exception {
		hiloCancion.stop();
	}

	private void pintar() {
		Text tiempo = new Text(1000, 50, "Tiempo:" + (int)Math.ceil(dinosaurio.getTiempo()));
		tiempo.setStyle("-fx-font-weight: bold");
		tiempo.setFont(Font.font("Victor Mono", 25.0));
		tiempo.setFill(Color.rgb(164, 164, 164));
		root.getChildren().add(tiempo);
		this.fondo.pintar(graficos);
		this.dinosaurio.pintar(graficos);
		for (Cactus cactu : cactus) {
			cactu.pintar(graficos);
		}
		for (Nube nube : nubes) {
			nube.pintar(graficos);
		}
	}

	@SuppressWarnings("deprecation")
	private void calculosLogica() {
		this.fondo.logicaCalculos();
		this.dinosaurio.logicaCalculos();
		for (Cactus cactu : cactus) {
			cactu.logicaCalculos();
			this.dinosaurio.detectarColision(cactu);
			cactu.detectarColision(dinosaurio);
		}
		for (Nube nube : nubes) {
			nube.logicaCalculos();
		}
		if (dinosaurio.isFinalizarJuego() && ventana) {
			nuevaVentana();
			pararTodo();
			hiloCancion.stop();
			ventana = false;
		}
	}

	private void ciclo() {
		long tiempoInicio = System.nanoTime();
		AnimationTimer tiempo = new AnimationTimer() {

			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicio) / 1000000000.0;
				dinosaurio.setTiempo(t);
				calculosLogica();
				pintar();
			}
		};
		tiempo.start();
	}

	private void eventosTeclado() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent teclas) {
				dinosaurio.teclado(teclas, true);
			}

		});
	}

	private void pararTodo() {
		for (Cactus cactu : cactus) {
			cactu.pararTodo();
		}
	}

	private void nuevaVentana() {
		EfectosMusica gameOver;
		Thread sonido;
		escena.getWindow().hide();
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/fes/aragon/interfaces/Final.fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();
			gameOver = new EfectosMusica("game-over");
			sonido = new Thread(gameOver);
			sonido.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}