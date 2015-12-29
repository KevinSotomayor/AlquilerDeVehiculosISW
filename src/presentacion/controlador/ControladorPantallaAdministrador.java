package presentacion.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.dto.SucursalDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaAdministrador implements Initializable{

	@FXML private ListView<SucursalDTO> ListViewSuc;
	
	@FXML Pane pane_no_selected_item;
	@FXML Pane pane_selected_item;
	
	@FXML Text id_sucursal_text;
	@FXML Text direccion_sucursal_text;

	
	private List<SucursalDTO> sucDTO;
	private int idSuc;
	private String dirSuc;
   
  //main de la logica
  	private AlquilerVehiculos alquilerdevehiculos;
  	private Stage primaryStage;
  	private AlquilerDeVehiculoApp app; 
  	
  	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app){
		this.primaryStage = primaryStage;
		this.app = app; 
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			alquilerdevehiculos = alquilerdevehiculos.getInstance();
			this.sucDTO = alquilerdevehiculos.cargarSucursales();

			
		}catch(DAOExcepcion | LogicaExcepcion e){
			app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		}
		
		ObservableList<SucursalDTO> observableListSucursales = FXCollections.observableArrayList();
		observableListSucursales.addAll(sucDTO);
		ListViewSuc.setItems(observableListSucursales);
		
		ListViewSuc.setFocusTraversable(false);
		
		ListViewSuc.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				idSuc = ListViewSuc.getSelectionModel().getSelectedItem().getId();
				dirSuc = ListViewSuc.getSelectionModel().getSelectedItem().getDireccion();
		
				pane_no_selected_item.setVisible(false);
				pane_selected_item.setVisible(true);
				
				id_sucursal_text.setText("Id: " +idSuc);
				direccion_sucursal_text.setText("Dirección:\n" + dirSuc);

			}
		});
	}
}
