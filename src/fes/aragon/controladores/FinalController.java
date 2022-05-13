package fes.aragon.controladores;

import fes.aragon.extras.EfectosMusica;
import fes.aragon.modelo.Juego;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FinalController {

	@FXML
	private Button btnJugarOtravez;

	@FXML
	private Button btnSalir;

	Thread sonido;
	EfectosMusica gameOver;

	@FXML
	void salirPartida(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void volverAJugar(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
		Juego juego = new Juego();
		try {
			juego.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
