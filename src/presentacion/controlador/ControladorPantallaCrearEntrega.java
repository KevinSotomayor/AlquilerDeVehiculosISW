package presentacion.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.dto.CategoriaDTO;
import persistencia.dto.ClienteDTO;
import persistencia.dto.CocheDTO;
import persistencia.dto.EntregaDTO;
import persistencia.dto.ReservaDTO;
import util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaCrearEntrega  implements Initializable{

	@FXML Text cliente_nombre_text;
	@FXML TextField dni_edittext;
	@FXML TextField coche_edittext;
	@FXML DatePicker fecha_datepicker;
	@FXML ChoiceBox<String> tipo_seguro_choicebox;
	@FXML TextField kilometros_edittext;
	@FXML TextField combustible_edittext;
	@FXML TextField empleado_realiza_edittext;

	

	//boton de guardar
	@FXML Circle guardar_boton_shape;
	@FXML ImageView icon_guardar_button_shape;

	//boton de cancelar
	@FXML Circle cancelar_boton_shape;
	@FXML ImageView icon_cancelar_button_shape;
	
	private Stage primaryStage;
	private AlquilerDeVehiculoApp app;
	private CocheDTO coche;
	private String dni_cliente_asociada_reserva;
	private ReservaDTO reserva_asociada;
	private AlquilerVehiculos alquilerVehiculos;

	private List<String> tiposSegurosList;

	
	//Cuando listarvehiculos viene de una reserva
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app, CocheDTO coche, ReservaDTO reserva){
		this.primaryStage = primaryStage;
		this.app = app;
		this.coche = coche;
		this.dni_cliente_asociada_reserva = reserva.getDniCliente();
		this.reserva_asociada = reserva;
		
		if(app==null){
			this.app = new AlquilerDeVehiculoApp();
		}
		
		//Nombre del cliente encontrado:
		try {
			alquilerVehiculos = alquilerVehiculos.getInstance();
			cliente_nombre_text.setText(alquilerVehiculos.buscarCliente(dni_cliente_asociada_reserva).getNombreyApellidos());
			tiposSegurosList = alquilerVehiculos.obtenerTiposDeSeguro();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			this.app.presentarMensajeEnPantalla(e.getLocalizedMessage());
		}

		//NO clickable dni
		dni_edittext.setText(dni_cliente_asociada_reserva);
		dni_edittext.setDisable(true);
		
		//Coche y su matricula
		coche_edittext.setText(coche.getMatricula());
		coche_edittext.setDisable(true);

		//Rellenar los tipos de seguros
		//ChoiceBox tipo de seguro
		ObservableList<String> observableListTiposSeguro = FXCollections.observableArrayList();
		observableListTiposSeguro.addAll(tiposSegurosList);
		tipo_seguro_choicebox.setItems(observableListTiposSeguro);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		guardar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 if(crearEntrega(crearEntregaDTO())){
					 entregaCreada();
					 Stage stage = (Stage)guardar_boton_shape.getScene().getWindow();
					 stage.close();
				 }
			}
		});
		icon_guardar_button_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(crearEntrega(crearEntregaDTO())){
					 entregaCreada();
					 Stage stage = (Stage)icon_guardar_button_shape.getScene().getWindow();
						stage.close();
				 }
			}
		});
		
		
		cancelar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = (Stage)cancelar_boton_shape.getScene().getWindow();
				stage.close();
			}
		});
		icon_cancelar_button_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				Stage stage = (Stage)icon_cancelar_button_shape.getScene().getWindow();
				stage.close();
			}
		});
		
				
		
	}
	
	private EntregaDTO crearEntregaDTO(){
		if( fecha_datepicker.getValue() != null &&
				empleado_realiza_edittext.getText().toString().length()>0 &&
				tipo_seguro_choicebox.getSelectionModel().getSelectedItem() != null &&
						tipo_seguro_choicebox.getSelectionModel().getSelectedIndex()>=0 &&
										kilometros_edittext.getText().toString().length()>0 &&
										Util.isNumericDouble(kilometros_edittext.getText().toString()) && 
										combustible_edittext.getText().toString().length()>0 &&
										Util.isNumericDouble(combustible_edittext.getText().toString())
										
										
				){
		//hacer comprobacion de que si existe el empleado para hacer la entrega
			try {
				if(alquilerVehiculos.buscarEmpleado(empleado_realiza_edittext.getText().toString())!=null){
					EntregaDTO res = new EntregaDTO(reserva_asociada.getId(),
							Util.transformLocalDate(fecha_datepicker.getValue()), tipo_seguro_choicebox.getSelectionModel().getSelectedItem(),
							Double.parseDouble(kilometros_edittext.getText().toString()), 
							Double.parseDouble(combustible_edittext.getText().toString()), coche.getMatricula(),
							empleado_realiza_edittext.getText().toString());

							return res;
					}else{
						app.presentarMensajeEnPantalla("No existe un empleado con identificador: " + empleado_realiza_edittext.getText().toString());
						return null;
					}
			} catch (NumberFormatException | DAOExcepcion e) {
				// TODO Auto-generated catch block
				app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				return null;
			}
			
		}else {
			app.presentarMensajeEnPantalla("No se ha podido crear la entrega. Verifica los datos.");
			return null;
		}

	}
	
	private boolean crearEntrega(EntregaDTO entrega){
		if(alquilerVehiculos!=null && entrega!=null){
					
					try {
						alquilerVehiculos.crearEntrega(entrega);
						return true;
					} catch (LogicaExcepcion | DAOExcepcion e) {
						// TODO Auto-generated catch block
						app.presentarMensajeEnPantalla(e.getLocalizedMessage());
						return false;
					}
				
		}
		return false;
	
	}
	
	private void entregaCreada(){
		app.presentarMensajeOK("Entrega creada con éxito del empleado: "+
 				empleado_realiza_edittext.getText().toString()
 				+ " para "+ cliente_nombre_text.getText().toString() + 
 				" con vehículo " + coche_edittext.getText().toString() + " y reserva " +
 				reserva_asociada.getId());
	}
	
}
