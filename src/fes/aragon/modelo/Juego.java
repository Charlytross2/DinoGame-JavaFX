package fes.aragon.modelo;

import java.io.IOException;

import fes.aragon.extras.EfectosMusica;
import fes.aragon.extras.MusicaCiclica;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.stage.WindowEvent;

public class Juego extends Application {
	private GraphicsContext graficos;
	private Group root;// Nodo raiz
	private Scene escena;// Escena de nuestra App en javaFX
	private Canvas hoja;// Hoja en la cual se pintara todo el juego
	private Fondo fondo;
	private Thread hiloCancion;
	private PersonajeAnimacion dinosaurio;
	private ConjuntoCactus tiposCactus;
	@SuppressWarnings("unused")
	private CargaTileMap carga = null;
	private int numeroNubes = (int) Math.ceil(Math.random() * (1 - 5) + 5);
	private Nube[] nubes = new Nube[numeroNubes];
	private boolean ventana = true;
	private StringProperty valor = new SimpleStringProperty("Tiempo: 0");

	@Override
	public void start(Stage ventana) throws Exception {
		iniciarComponentes();
		pintar();
		eventosTeclado();
		this.ciclo();
		ventana.setScene(escena);// le asignamos al escenario una escena
		ventana.setTitle("VideoJuego");
		ventana.show();// mostramos el escenario
		// detiene la ejecucion por completo al oprimir el boton de salida
		ventana.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		tiposCactus = new ConjuntoCactus(0, 0, "", 0);
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
		this.fondo.pintar(graficos);
		this.dinosaurio.pintar(graficos);
		this.tiposCactus.pintar(graficos);
		for (Nube nube : nubes) {
			nube.pintar(graficos);
		}
		this.texto();
	}

	@SuppressWarnings("deprecation")
	private void calculosLogica() {
		this.fondo.logicaCalculos();
		this.dinosaurio.logicaCalculos();
		this.dinosaurio.detectarColision(tiposCactus.getActualCactus());
		this.tiposCactus.getActualCactus().detectarColision(dinosaurio);
		this.tiposCactus.logicaCalculos();
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
				valor.set("Tiempo:" + String.valueOf((int) dinosaurio.getTiempo()));
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
		this.tiposCactus.getActualCactus().pararTodo();
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

	private void texto() {
		Text tiempo = new Text();
		tiempo.setX(1000);
		tiempo.setY(50);
		tiempo.setStyle("-fx-font-weight: bold");
		tiempo.setFont(Font.font("Victor Mono", 30.0));
		tiempo.setFill(Color.rgb(0, 0, 0));
		root.getChildren().add(tiempo);
		tiempo.textProperty().bind(valor);
	}
}
