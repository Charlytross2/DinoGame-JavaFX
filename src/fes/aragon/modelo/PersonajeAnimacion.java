package fes.aragon.modelo;

import fes.aragon.extras.EfectosMusica;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class PersonajeAnimacion extends ComponentesJuego {
	private int gravedad = 3;
	private boolean desciende = false;
	private boolean salto = false;
	private Image imagen;
	private int indice = 1;
	private double tiempo = 0;
	private Rectangle rectangulo;
	private final int piso = 715;
	private boolean colision = false;
	private boolean finalizarJuego = false;

	public PersonajeAnimacion(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		Thread sonido;
		EfectosMusica gameOver;
		this.imagen = new Image(imagen);
		gameOver = new EfectosMusica("game-over");
		sonido = new Thread(gameOver);
		rectangulo = new Rectangle(x, y, this.imagen.getWidth() / 4, this.imagen.getHeight());
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		int cantidadFrames = CargaTileMap.personajeCaminar.size() - 1;
		this.indice = (int) ((tiempo % (cantidadFrames * .1)) / .1) + 1;
		CuadroTileMap cuadro = null;
		cuadro = CargaTileMap.personajeCaminar.get(indice);
		if (!colision) {
			this.indice = (int) ((tiempo % (cantidadFrames * .1)) / .1) + 1;
		} else {
			if (colision) {
				cuadro = CargaTileMap.personajeCaminar.get(4);
			}
			if (salto) {
				cuadro = CargaTileMap.personajeCaminar.get(1);
			}
		}

		graficos.drawImage(imagen, cuadro.getCoorXImagenDentro(), cuadro.getCoorYImagenDentro(),
				cuadro.getAnchoImagenDentro(), cuadro.getAltoImagenDentro(), x, y, cuadro.getAnchoVentanaPintar(),
				cuadro.getAltoImagenDentro());
		graficos.strokeRect(rectangulo.getX(), rectangulo.getY(), rectangulo.getWidth() - 5, rectangulo.getHeight());
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		if (presiona) {
			switch (evento.getCode().toString()) {
			case "SPACE":
				if((y + imagen.getHeight()) != piso && salto) {
					break;
				}
				if(y + imagen.getHeight() <= piso && !colision) {
					y += velocidad * 110;
					this.salto = true;
					this.desciende = true;
				}else {
					this.desciende = false;
					this.salto = false;
				}
				break;
			}
		}
	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		if (desciende == true || salto == true) {
			y += gravedad;
		}
		if (y + imagen.getHeight() > piso) {
			y = 565;
		}
		if (y + imagen.getHeight() == piso) {
			salto = false;
		}
		if (colision) {
			this.finalizarJuego = true;
		}
		this.rectangulo.setX(x);
		this.rectangulo.setY(y);
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void detectarColision(Cactus cactus) {
		if (this.getRectangulo().getBoundsInLocal().intersects(cactus.getRectangulo().getBoundsInLocal())) {
			this.colision = true;
		}
	}

	public boolean isFinalizarJuego() {
		return finalizarJuego;
	}

	public void setFinalizarJuego(boolean finalizarJuego) {
		this.finalizarJuego = finalizarJuego;
	}

}
