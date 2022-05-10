package fes.aragon.inicio;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.Cactus;
import fes.aragon.modelo.CargaTileMap;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Nube;
import fes.aragon.modelo.PersonajeAnimacion;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
	// creamos las variables globales
	private GraphicsContext graficos;
	private Group root;// Nodo raiz
	private Scene escena;// Escena de nuestra App en javaFX
	private Canvas hoja;// Hoja en la cual se pintara todo el juego
	private Fondo fondo;
	private Thread hiloCancion;
	private PersonajeAnimacion dinosaurio;
	private Cactus cactus[];
	@SuppressWarnings("unused")
	private CargaTileMap carga = null;
	private int numeroNubes = (int) Math.ceil(Math.random() * (1-5) + 5);
	private Nube[] nubes = new Nube[numeroNubes];

	@Override // metodo de entrada a las aplicaciones en java
	public void start(Stage ventana) {
		iniciarComponentes();
		pintar();
		eventosTeclado();
		ventana.setScene(escena);// le asignamos al escenario una escena
		ventana.setTitle("Dinosaurio Chromium");
		ventana.show();// mostramos el escenario
		this.ciclo();
	}

	public static void main(String[] args) {
		launch(args); // llama al metodo start por dentro
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
		dinosaurio = new PersonajeAnimacion(10, 565, "/fes/aragon/recursos/dino.png", 2);
		carga = new CargaTileMap();
		for (Cactus cactu : cactus) {
			cactu = new Cactus();
		}
		for(int i = 0 ; i < nubes.length ; i++) {
			nube[i] = new Nube("/fes/aragon/recursos/nube.png",1);
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
//		graficos.drawImage(new Image("/fes/aragon/recursos/cactus1.png"), (x + 700), (y + 55));
//		graficos.drawImage(new Image("/fes/aragon/recursos/cactus2.png"), (x + 1000), (y + 80));
//		graficos.drawImage(new Image("/fes/aragon/recursos/nube.png"), (x + 200), (y - 370));
	}

	private void calculosLogica() {
		this.fondo.logicaCalculos();
		this.dinosaurio.logicaCalculos();
	}

	private void ciclo() {
		long tiempoInicio = System.nanoTime();
		AnimationTimer tiempo = new AnimationTimer() {

			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicio) / 1000000000.0;
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
}
