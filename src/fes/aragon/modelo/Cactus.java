package fes.aragon.modelo;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Cactus extends ComponentesJuego {

	private Rectangle rectangulo;
	private int x;
	private Image imagen;
	private boolean colision = false;

	public Cactus(int y, String imagen, int velocidad) {
		super(y, imagen, velocidad);
		this.x = numeroAleatorio();
		this.imagen = new Image(imagen);
		rectangulo = new Rectangle(this.x, this.y, this.imagen.getWidth(), this.imagen.getHeight());
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(imagen, x, y);
		graficos.strokeRect(rectangulo.getX(), rectangulo.getY(), rectangulo.getWidth(), rectangulo.getHeight());
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		if (x + imagen.getWidth() == 0) {
			x = numeroAleatorio();
		}
		if(colision == false) {
			this.x -= velocidad;
			this.rectangulo.setX(x);
			this.rectangulo.setY(y);
		}
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	private int numeroAleatorio() {
		Random random = new Random();
		return random.nextInt(1200, 1350);
	}
	
	public void detectarColision(PersonajeAnimacion dinosaurio) {
		if (this.getRectangulo().getBoundsInLocal().intersects(dinosaurio.getRectangulo().getBoundsInLocal())) {
			this.colision = true;
			pararTodo();
		}
	}

	@Override
	public void pararTodo() {
		 this.x = x;
	}
}
