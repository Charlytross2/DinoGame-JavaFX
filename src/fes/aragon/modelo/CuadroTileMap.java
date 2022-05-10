package fes.aragon.modelo;

import javafx.scene.shape.Rectangle;

public class CuadroTileMap {
	private int coorXImagenDentro;
	private int coorYImagenDentro;
	private int anchoImagenDentro;
	private int altoImagenDentro;
	private int anchoVentanaPintar;
	private int altoVentanaPintar;
	private int xVentana = 0;
	private int yVentana = 0;
	private Rectangle rectangulo;

	public CuadroTileMap(int coorXImagenDentro, int coorYImagenDentro, int anchoImagenDentro, int altoImagenDentro,
			int anchoVentanaPintar, int altoVentanaPintar) {
		this.coorXImagenDentro = coorXImagenDentro;
		this.coorYImagenDentro = coorYImagenDentro;
		this.anchoImagenDentro = anchoImagenDentro;
		this.altoImagenDentro = altoImagenDentro;
		this.anchoVentanaPintar = anchoVentanaPintar;
		this.altoVentanaPintar = altoVentanaPintar;

	}

	public CuadroTileMap(CuadroTileMap tmp) {
		this.coorXImagenDentro = tmp.coorXImagenDentro;
		this.coorYImagenDentro = tmp.coorYImagenDentro;
		this.anchoImagenDentro = tmp.anchoImagenDentro;
		this.altoImagenDentro = tmp.altoImagenDentro;
		this.anchoVentanaPintar = tmp.anchoVentanaPintar;
		this.altoVentanaPintar = tmp.altoVentanaPintar;
	}

	public int getCoorXImagenDentro() {
		return coorXImagenDentro;
	}

	public void setCoorXImagenDentro(int coorXImagenDentro) {
		this.coorXImagenDentro = coorXImagenDentro;
	}

	public int getCoorYImagenDentro() {
		return coorYImagenDentro;
	}

	public void setCoorYImagenDentro(int coorYImagenDentro) {
		this.coorYImagenDentro = coorYImagenDentro;
	}

	public int getAnchoImagenDentro() {
		return anchoImagenDentro;
	}

	public void setAnchoImagenDentro(int anchoImagenDentro) {
		this.anchoImagenDentro = anchoImagenDentro;
	}

	public int getAltoImagenDentro() {
		return altoImagenDentro;
	}

	public void setAltoImagenDentro(int altoImagenDentro) {
		this.altoImagenDentro = altoImagenDentro;
	}

	public int getAnchoVentanaPintar() {
		return anchoVentanaPintar;
	}

	public void setAnchoVentanaPintar(int anchoVentanaPintar) {
		this.anchoVentanaPintar = anchoVentanaPintar;
	}

	public int getAltoVentanaPintar() {
		return altoVentanaPintar;
	}

	public void setAltoVentanaPintar(int altoVentanaPintar) {
		this.altoVentanaPintar = altoVentanaPintar;
	}

	public int getxVentana() {
		return xVentana;
	}

	public void setxVentana(int xVentana) {
		this.xVentana = xVentana;
	}

	public int getyVentana() {
		return yVentana;
	}

	public void setyVentana(int yVentana) {
		this.yVentana = yVentana;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

}
