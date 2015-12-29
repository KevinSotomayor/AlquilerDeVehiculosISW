package presentacion.controlador;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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

public class ControladorListarReservas implements Initializable{
	
	private AlquilerDeVehiculoApp app;
	private Stage primaryStage;
	private SucursalDTO sucursalDeReservas;
	
	@FXML
	private TableView<ReservaDTO> reservas; 
	@FXML
	private TableColumn<ReservaDTO, Integer> id_column; 
	@FXML
	private TableColumn<ReservaDTO, LocalDateTime> fechaRecogida_column;
	@FXML
	private TableColumn<ReservaDTO, String> lugar_recogida_column; 
	@FXML
	private TableColumn<ReservaDTO, LocalDateTime> fechaDevolucion_column;
	
	@FXML private TableColumn<ReservaDTO, String> lugar_devolucion_column;

	@FXML
	private TableColumn<ReservaDTO, String> modalidad_column; 
	
	@FXML private TableColumn<ReservaDTO, String> cliente_column;

	@FXML private TableColumn<ReservaDTO, String> categoria_column;

	
	@FXML Button boton_aceptar;
	
	private ObservableList<ReservaDTO> listaReservas;
	private AlquilerVehiculos alquilerVehiculos;
	private boolean flag;
	
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app, SucursalDTO sucursal, boolean flag) {
		this.primaryStage = primaryStage;
		this.flag = flag;
		this.app = app;
		if(app==null){
			app = new AlquilerDeVehiculoApp();
		}
		this.sucursalDeReservas = sucursal;
		listaReservas = FXCollections.observableArrayList();
		
		try {
			
			alquilerVehiculos = alquilerVehiculos.getInstance();
			
			id_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getId())); 
			
			fechaRecogida_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getFechaRecogida()));
			
			lugar_recogida_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(alquilerVehiculos.buscarSucursal(param.getValue().getIdSucursalRecogida()).getDireccion()));
			
			fechaDevolucion_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));
			
			lugar_devolucion_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(alquilerVehiculos.buscarSucursal(param.getValue().getIdSucursalDevolucion()).getDireccion()));
			
			modalidad_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(alquilerVehiculos.obtenerModalidad(param.getValue().getModalidadAlquiler())));
			
			cliente_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>(  param.getValue().getDniCliente() ) );
			
			categoria_column.setCellValueFactory(param -> new
					ReadOnlyObjectWrapper<>( param.getValue().getNombreCategoria() ));
			
			listaReservas.addAll(alquilerVehiculos.listarReservaSucursal(sucursalDeReservas.getId(), flag));
			reservas.setItems(listaReservas);
			
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
		
		
		reservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
			if(newSelection != null){
				if(primaryStage!=null)
					try {
						app.abrirVentanaDeCochesPorSucursalReserva ("/presentacion/vista/pantalla_listaVehiculos.fxml", "Lista de vehiculos de " + sucursalDeReservas.getDireccion(), sucursalDeReservas ,newSelection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						app.presentarMensajeEnPantalla(e.getLocalizedMessage());
					}

			}
		});
		
	}
	
}
