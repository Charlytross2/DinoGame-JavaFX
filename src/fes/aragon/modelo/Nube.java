package fes.aragon.modelo;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Nube extends ComponentesJuego{
	
	private Image imagen;
	private int x;
	private int y;
	private Rectangle rectangulo;
	@SuppressWarnings("unused")
	private boolean colision = false;
	
	public Nube(String imagen, int velocidad) {
		super(imagen, velocidad);
		this.imagen = new Image(imagen);
		rectangulo = new Rectangle(this.imagen.getWidth(), this.imagen.getHeight());
		this.x = posicionX();
		this.y = posicionY();
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
		if(x + imagen.getWidth() == 0) {
			this.x = posicionX();
			this.y = posicionY();
		}
		this.x -= velocidad;
		this.rectangulo.setX(x);
		this.rectangulo.setY(y);
	}
	
	private int posicionY() {
		Random random = new Random();
		return random.nextInt(100,450);
	}
	
	private int posicionX() {
		Random random = new Random();
		return random.nextInt(1210,1900);
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void detectarColision(Nube nube) {
		if (this.getRectangulo().getBoundsInLocal().intersects(nube.getRectangulo().getBoundsInLocal())) {
			this.colision = true;
		}
	}
	
	@Override
	public void pararTodo() {
	}
	
}
