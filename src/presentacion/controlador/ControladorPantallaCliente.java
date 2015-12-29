package presentacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import persistencia.dto.ClienteDTO;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaCliente implements Initializable {
	@FXML Button boton_crearcliente;
	@FXML TextField dni_edittext;
	
	private Stage primaryStage;
	private AlquilerDeVehiculoApp app;
	private AlquilerVehiculos alquilervehiculos;
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app) {
		this.primaryStage = primaryStage;
		this.app = app;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		dni_edittext.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@SuppressWarnings("static-access")
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER)  {
						if(app!=null){
							try {
								
								 alquilervehiculos = alquilervehiculos.getInstance();

								if(dni_edittext.getText().length()>7 &&  dni_edittext.getText().length()<10){
									ClienteDTO clienteBuscado = alquilervehiculos.buscarCliente(dni_edittext.getText());
									if( clienteBuscado!=null ){
										//si ya existe el cliente, 
										dni_edittext.setText("");
										app.abrirVentanaDeClienteAReserva("/presentacion/vista/pantalla_crear_reserva.fxml", "Crear nueva reserva", clienteBuscado);
									}else{
										//si no existe el cliente, lo creamos
										app.abrirVentanaDe(primaryStage, "/presentacion/vista/pantalla_crear_cliente.fxml", "Crear nuevo cliente");
									}
								}else{
									app.presentarMensajeEnPantalla("Introduce un DNI válido");
								}
								
							} catch (IOException e) {
								app.presentarMensajeEnPantalla(e.getMessage());
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								app.presentarMensajeEnPantalla(e.getMessage());
							} catch (LogicaExcepcion e) {
								app.presentarMensajeEnPantalla(e.getMessage());
							}
							
						} else {
							app.presentarMensajeEnPantalla("No hemos podido abrir, inténtalo de nuevo más tarde");
		
						}
					}
				}
		});
		
		boton_crearcliente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(app!=null){
					try {
				
						app.abrirVentanaDe(primaryStage, "/presentacion/vista/pantalla_crear_cliente.fxml", "Crear nuevo cliente");

					} catch (IOException e) {
						e.printStackTrace();
						System.err.println("No se ha podido lanzar la pantalla");
					}

				} else {
					System.err.println("No se ha podido lanzar la pantalla, app null");

				}
			}
		});
		
	}

	

}
