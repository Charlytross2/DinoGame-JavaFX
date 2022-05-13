package fes.aragon.controladores;

import fes.aragon.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController{

    @FXML
    private Button btnJugar;

    @FXML
    void jugarPartida(ActionEvent event){
    	//cierra la ventana de inicio
    	((Node)(event.getSource())).getScene().getWindow().hide();
    	//creamos una instancia de juego y lo comenzamos
    	Juego juego = new Juego();
    	try {
			juego.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
