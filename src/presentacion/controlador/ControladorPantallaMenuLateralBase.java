package presentacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorPantallaMenuLateralBase implements Initializable {
	//pasar el stage principal al inicio de la app
	private Stage primaryStage;
	private AlquilerDeVehiculoApp app;
	
	@FXML Text main_menu_cliente;
	@FXML ImageView img_main_menu_cliente;
	
	@FXML Text main_menu_empleado;
	@FXML ImageView img_main_menu_empleado;

	@FXML Text main_menu_administrador;
	@FXML ImageView img_main_menu_admin;
	
	@FXML Text principal_text;
	
	@FXML Text main_menu_acercade;
	
	@FXML Text salir_text;

	
	public void initStage( Stage stage, AlquilerDeVehiculoApp app ) {
		this.primaryStage = stage;
		this.app = app;
	}

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//volver a la pantalla inicial - intro
		principal_text.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					app.cargarPantalladeIntro();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("No se ha podido volver a la pantalla principal");
				}
			}
		});
		
		//Eventos del menú lateral
		main_menu_cliente.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				abrirPantallaCliente();
			}
		});
		//asociar tambien el icono al evento del texto, que actúan como único botón
		img_main_menu_cliente.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				abrirPantallaCliente();
			}
		});
		
		
		main_menu_empleado.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				abrirPantallaEmpleado();
			}
		});
		img_main_menu_empleado.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				abrirPantallaEmpleado();
			}
		});
		
		main_menu_administrador.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				abrirPantallaAdministrador();
			}
		});
		img_main_menu_admin.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				abrirPantallaAdministrador();
			}
		});
	
		main_menu_acercade.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				abrirPantallaaAcercaDe();
			}
		});
		
		salir_text.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				primaryStage.close();
			}
		});
		
		

		
	}
	
	//metodo para llamar al main y cambiar la pantalla principal - cliente
	private void abrirPantallaCliente(){
		app.abrirPantalla(primaryStage, 0);
	}
	
	//metodo para llamar al main y cambiar la pantalla principal - empleado
	private void abrirPantallaEmpleado( ){
		app.abrirPantalla(primaryStage, 1);
	}
		
	//metodo para llamar al main y cambiar la pantalla principal - administrador
	private void abrirPantallaAdministrador(){
		app.abrirPantalla(primaryStage, 2);
	}
	
	//abrir pantalla de acerca de
	private void abrirPantallaaAcercaDe(){
		app.abrirPantalla(primaryStage, 3);
	}

}
