package fes.aragon.modelo;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class ConjuntoCactus extends ComponentesJuego {

	private String tipoCactus[] = { "cactus1", "cactus2" };
	private int coorY[] = { 620, 645 };

	public ArrayList<Cactus> cactus = new ArrayList<>();
	private int numeroActual = (int) (Math.random() * 2);
	Image imagen;

	public ConjuntoCactus(int x, int y, String imagen, int velocidad) {
		// inicializamos en el arrayList a los cactus
		super(x, y, imagen, velocidad);
		for (int i = 0; i < tipoCactus.length; i++) {
			String ruta = "/fes/aragon/recursos/" + tipoCactus[i] + ".png";
			Cactus cactu = new Cactus(coorY[i], ruta, 5);
			cactus.add(cactu);
		}
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		this.cactus.get(numeroActual).pintar(graficos);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		imagen = new Image(this.cactus.get(numeroActual).getImagen());
		if (this.cactus.get(numeroActual).getX() + imagen.getWidth() < 0) {
			numeroActual = (int) (Math.random() * 2);
		}
		getActualCactus().logicaCalculos();
	}

	@Override
	public void pararTodo() {

	}

	public Cactus getActualCactus() {
		return cactus.get(numeroActual);
	}

}
