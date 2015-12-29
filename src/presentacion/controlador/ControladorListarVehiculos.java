package presentacion.controlador;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import persistencia.dto.CocheDTO;
import persistencia.dto.ReservaDTO;
import persistencia.dto.SucursalDTO;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorListarVehiculos implements Initializable{
	
	private AlquilerDeVehiculoApp app;
	private Stage primaryStage;
	private SucursalDTO sucursal;
	
	@FXML
	private TableView<CocheDTO> vehiculos; 
	@FXML
	private TableColumn<CocheDTO, String> matricula_column; 
	@FXML
	private TableColumn<CocheDTO, Double> kms_column;
	@FXML
	private TableColumn<CocheDTO, String> categoria_column; 
	
	@FXML Button boton_aceptar;
	
	private ObservableList<CocheDTO> listaVehiculos;
	private AlquilerVehiculos alquilerVehiculos;
	
	//Ver vehículos disponibles de una sucursal, TODOS los vehiculos
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app, SucursalDTO sucursal) {
		this.primaryStage = primaryStage;
		this.app = app;
		if(app==null){
			app = new AlquilerDeVehiculoApp();
		}
		this.sucursal = sucursal;
		listaVehiculos = FXCollections.observableArrayList();
		
		try {
			
			alquilerVehiculos = alquilerVehiculos.getInstance();
			
			matricula_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getMatricula())); 
			
			kms_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getKmsActuales()));
			
			categoria_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(alquilerVehiculos.buscarCategoria(param.getValue().getCategoria_asociada()).getNombreCategoria()));
			
			listaVehiculos.addAll(alquilerVehiculos.obtenerCochesPorsucursal(sucursal.getId()));
			vehiculos.setItems(listaVehiculos);
			
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		}
		
		
	}
	
	//Cuando se hace click una reserva para poder ver el vehiculo asociado a una sucursal y categoría de esa reserva
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app, SucursalDTO sucursal, ReservaDTO reserva) {
		this.primaryStage = primaryStage;
		this.app = app;
		if(app==null){
			app = new AlquilerDeVehiculoApp();
		}
		this.sucursal = sucursal;
		listaVehiculos = FXCollections.observableArrayList();
		
		try {
			
			alquilerVehiculos = alquilerVehiculos.getInstance();
			
			matricula_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getMatricula())); 
			
			kms_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getKmsActuales()));
			
			categoria_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(alquilerVehiculos.buscarCategoria(param.getValue().getCategoria_asociada()).getNombreCategoria()));
			
			listaVehiculos.addAll(alquilerVehiculos.obtenerCochesPorCategoria(reserva.getNombreCategoria(), reserva.getIdSucursalRecogida()));
			vehiculos.setItems(listaVehiculos);
			
			vehiculos.setFocusTraversable(false);
			vehiculos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
				if(newSelection != null){
					if(primaryStage!=null)
						try {
							this.app.abrirVentanaDeEntregaDesdeCoche("/presentacion/vista/pantalla_crear_entrega.fxml", "Lista de reservas", newSelection, reserva);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							this.app.presentarMensajeEnPantalla(e.getLocalizedMessage());
						}

				}
			});
			
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		}
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boton_aceptar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = (Stage)boton_aceptar.getScene().getWindow();
				stage.close();
			}
		});
		
		
	}
	
}
