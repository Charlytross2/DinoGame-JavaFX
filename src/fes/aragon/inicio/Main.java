package fes.aragon.inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Scene escena;

	@Override // metodo de entrada a las aplicaciones en java
	public void start(Stage ventana) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/fes/aragon/interfaces/Inicio.fxml"));
			escena = new Scene(root);
			escena.getStylesheets().add(getClass().getResource("/fes/aragon/css/Application.css").toExternalForm());
			ventana.setScene(escena);// le asignamos al escenario una escena
			ventana.setTitle("DinoGame");
			ventana.show();// mostramos el escenario
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static void main(String[] args) {
		launch(args); // llama al metodo start por dentro
	}
}
