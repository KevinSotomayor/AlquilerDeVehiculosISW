package presentacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorPantallaAcercaDe implements Initializable{
	private Stage primaryStage;
	private AlquilerDeVehiculoApp app;
	
	@FXML Button boton_salir;
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app) {
		this.primaryStage = primaryStage;
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		boton_salir.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					app.cargarPantalladeIntro();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
