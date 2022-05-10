package fes.aragon.modelo;

import java.util.HashMap;

public class CargaTileMap {
	public static final HashMap<Integer, CuadroTileMap> personajeCaminar = new HashMap<>();

	public CargaTileMap() {
		this.cargarPersonaje();
	}
	
	private void cargarPersonaje() {
		int limiteX = (int) Math.ceil(600 / 150);
		int limiteY = (int) Math.ceil(150 / 150);
		int celda = 0;
		int cordXDentroImagen = 6;
		int cordYDentroImagen = 0;
		int anchoDentroImagen = 150;
		int altoDentroImagen = 150;
		int anchoVentana = 150;
		int altoVentana = 150;

		for (int fila = 0; fila < limiteY; fila++) {
			for (int columna = 0; columna <= limiteX; columna++) {
				celda++;
				CuadroTileMap c = new CuadroTileMap(cordXDentroImagen, cordYDentroImagen, anchoDentroImagen,
						altoDentroImagen, anchoVentana, altoVentana);
				CargaTileMap.personajeCaminar.put(celda, c);

				cordXDentroImagen += anchoDentroImagen;
			}
			cordXDentroImagen = 6;
			cordYDentroImagen += altoDentroImagen;
		}
	}

}
