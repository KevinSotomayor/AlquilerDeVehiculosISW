package presentacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import persistencia.dto.SucursalDTO;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaEmpleado implements Initializable{
	private AlquilerDeVehiculoApp app;
	private Stage primaryStage;
	@FXML Text crear_cliente;
	@FXML Text large_text_cliente;
	@FXML ImageView crear_cliente_imagen;
	
	@FXML Text listar_reservas;
	@FXML ImageView listar_reservas_imagen;

	
	@FXML
	private TableView<SucursalDTO> tabla_sucursales; 
	@FXML
	private TableColumn<SucursalDTO, Integer> columna_id_sucursales; 
	@FXML private TableColumn<SucursalDTO, String> columna_direccion_sucursales;
	
	@FXML Button boton_listar_coches;
	@FXML Button boton_listar_reservas;
	@FXML Button boton_listar_reservas_entregas;
	
	private ObservableList<SucursalDTO> observableListSucursales= FXCollections.observableArrayList();
	private AlquilerVehiculos alquilerVehiculos;
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app) {
		this.primaryStage = primaryStage;
		this.app = app;
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		try {
			alquilerVehiculos = alquilerVehiculos.getInstance();
			observableListSucursales.addAll(alquilerVehiculos.cargarSucursales());
			tabla_sucursales.setItems(observableListSucursales);
			
			//cargar tabla
			columna_id_sucursales.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getId())); 
			columna_direccion_sucursales.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getDireccion()));
		} catch (DAOExcepcion | LogicaExcepcion e1) {
			// TODO Auto-generated catch block
			app.presentarMensajeEnPantalla(e1.getLocalizedMessage());
		}
		
		crear_cliente_imagen.setOnMouseClicked(new EventHandler<Event>(){
			
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					app.abrirVentanaDe(primaryStage, "/presentacion/vista/pantalla_crear_cliente.fxml", "Crear nuevo cliente");
				} catch (IOException e) {
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}
		});
		crear_cliente.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					try {
						app.abrirVentanaDe(primaryStage, "/presentacion/vista/pantalla_crear_cliente.fxml", "Crear nuevo cliente");
					} catch (IOException e) {
						app.presentarMensajeEnPantalla(e.getLocalizedMessage());
					}
				}

				
			});
		large_text_cliente.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					app.abrirVentanaDe(primaryStage, "/presentacion/vista/pantalla_crear_cliente.fxml", "Crear nuevo cliente");
				} catch (IOException e) {
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}

			
		});


	
		tabla_sucursales.setFocusTraversable(false);
		tabla_sucursales.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
			if(newSelection != null){
				if(primaryStage!=null)
					try {
						//app.abrirVentanaDeSucursalAReservas("/presentacion/vista/pantalla_listaReservas.fxml", "Lista de reservas", newSelection);
						habilitarBotones(newSelection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						app.presentarMensajeEnPantalla(e.getLocalizedMessage());
					}

			}
		});
		
		
		
		
}
	
	private void habilitarBotones(SucursalDTO newSelection){
		boton_listar_coches.setVisible(true);
		boton_listar_reservas.setVisible(true);
		boton_listar_reservas_entregas.setVisible(true);
		
		boton_listar_coches.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					listarCoches(newSelection);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		boton_listar_reservas.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					listarReservas(newSelection, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}
		});
		boton_listar_reservas_entregas.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					listarReservas(newSelection, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}
		});
	}
	
	private void listarCoches (SucursalDTO newSelection) throws IOException {
		app.abrirVentanaDeCochesPorSucursal("/presentacion/vista/pantalla_listaVehiculos.fxml", "Lista de reservas", newSelection);

	}
	
	private void listarReservas (SucursalDTO newSelection, boolean flag) throws IOException{
		if(flag)
			app.abrirVentanaDeSucursalAReservas("/presentacion/vista/pantalla_listaReservas.fxml", "Lista de reservas", newSelection, flag);
		else
			app.abrirVentanaDeSucursalAReservas("/presentacion/vista/pantalla_listaReservas.fxml", "Lista de reservas", newSelection, flag);

	}
	
}	

