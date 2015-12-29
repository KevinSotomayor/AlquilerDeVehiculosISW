package presentacion.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaInicio implements Initializable {
	private Stage primaryStage;
	private AlquilerDeVehiculoApp app;
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app) {
		this.primaryStage = primaryStage;
		this.app = app;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//cargar por primera vez los datos de la base de datos
		try {
			AlquilerVehiculos alquiler = AlquilerVehiculos.getInstance();
		} catch (DAOExcepcion | LogicaExcepcion e) {
			
			e.printStackTrace();
		}
	}
}
