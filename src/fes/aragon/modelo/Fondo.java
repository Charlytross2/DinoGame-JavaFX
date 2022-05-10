package fes.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Fondo extends ComponentesJuego{
	
	private int xx = -1200;
	private Image imagen;
	private Image imagen2;

	public Fondo(int x, int y, String imagen,int velocidad) {
		super(x, y, imagen,velocidad);
		this.imagen = new Image(imagen);
		this.imagen2 = new Image(imagen.replace("1", "2"));
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(imagen, x, y);
		graficos.drawImage(imagen2, xx, y);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		x += velocidad;
		xx += velocidad;
		if (x == 1200) {
			x = -1200;
		}
		if (xx == 1200) {
			xx = -1200;
		}
	}

}
