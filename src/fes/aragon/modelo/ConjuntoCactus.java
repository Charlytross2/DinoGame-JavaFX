package fes.aragon.modelo;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class ConjuntoCactus extends ComponentesJuego{
	
	public String tipoCactus[] = {"cactus1","cactus2"};
	
	public ArrayList<Cactus> cactus = new ArrayList<>();

	public ConjuntoCactus(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		int dibujoCactus = 0;
		String tiposCactus;
		for(int i = 0 ; i < tipoCactus.length ; i++) {
			dibujoCactus = (int) (Math.random() * 2);
			tiposCactus = tipoCactus[dibujoCactus];
			int posX = (int) Math.random() * (1350-1200) + 1350;
			Cactus cactu = new Cactus(posX, "/fes/aragon/recursos" + tiposCactus + ".png", 3);
			cactus.add(cactu);
		}
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pararTodo() {
		// TODO Auto-generated method stub
		
	}

}
