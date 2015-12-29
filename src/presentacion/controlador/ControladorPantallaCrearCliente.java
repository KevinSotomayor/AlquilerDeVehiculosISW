package presentacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import persistencia.dto.ClienteDTO;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import logica.AlquilerVehiculos;

public class ControladorPantallaCrearCliente implements Initializable{
	
	@FXML TextField dni_edittext;
	@FXML TextField nombreapellidos_edittext;
	@FXML TextField direccion_edittext;
	@FXML TextField poblacion_edittext;
	@FXML TextField cod_postal_edittext;

	//fecha
	@FXML DatePicker carnet_conducir_datepicker;
	
	//Apartado tarjeta de credito
	@FXML TextField num_tarjeta_edittext;
	@FXML Label nombre_tarjeta_label;
	@FXML TextField caduca_mes_tarjeta_edittext;
	@FXML TextField caduca_anyo_tarjeta_edittext;
	@FXML TextField cvc_tarjeta_edittext;
	
	@FXML ImageView maestro_img_on;
	@FXML ImageView mastercard_img_on;
	@FXML ImageView visa_img_on;

	@FXML ImageView maestro_img_off;
	@FXML ImageView mastercard_img_off;
	@FXML ImageView visa_img_off;
	private String tipoTC;
	private boolean flagTipoTC;

	
	//boton de guardar
	@FXML Circle guardar_boton_shape;
	@FXML ImageView icon_guardar_button_shape;

	//boton de cancelar
	@FXML Circle cancelar_boton_shape;
	@FXML ImageView icon_cancelar_boton_shape;
	
	//main de la logica
	private AlquilerVehiculos alquilerdevehiculos;
	private AlquilerDeVehiculoApp app;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try{
			alquilerdevehiculos = AlquilerVehiculos.getInstance();	
			AlquilerDeVehiculoApp main = new AlquilerDeVehiculoApp();
			app = main;
		}catch(Exception e){
			app.presentarMensajeEnPantalla(e.getMessage());
		}
		
		
		nombreapellidos_edittext.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		        if(newValue.length()<=17){
		        	nombre_tarjeta_label.setText(newValue.toUpperCase());
		        }else{
		        	nombre_tarjeta_label.setText(newValue.toUpperCase().substring(0, 16));
		        }
		    	
		    }
		});
		
		//Guardar
		guardar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					crearCliente();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}
			
		});
		icon_guardar_button_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					crearCliente();

				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				}
			}
			
		});
		
		//Cancelar
		cancelar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Stage stage = (Stage)cancelar_boton_shape.getScene().getWindow();
				stage.close();
			}
			
		});
		icon_cancelar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Stage stage = (Stage)icon_cancelar_boton_shape.getScene().getWindow();
				stage.close();
			}
			
		});
		
		maestro_img_off.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					mastercard_img_on.setVisible(false);
					mastercard_img_off.setVisible(true);
					visa_img_on.setVisible(false);
					visa_img_off.setVisible(true);
					
					maestro_img_off.setVisible(false);
					maestro_img_on.setVisible(true);
					tipoTC = "Maestro";
				}else{
					maestro_img_off.setVisible(false);
					maestro_img_on.setVisible(true);
					tipoTC = "Maestro";
					flagTipoTC = true;
				}
			}
		});
		maestro_img_on.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					tipoTC = "";
					flagTipoTC = false;
					maestro_img_off.setVisible(true);
					maestro_img_on.setVisible(false);
				}
			}
		});
		
		mastercard_img_off.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					mastercard_img_on.setVisible(true);
					mastercard_img_off.setVisible(false);
					
					visa_img_on.setVisible(false);
					visa_img_off.setVisible(true);
					maestro_img_off.setVisible(true);
					maestro_img_on.setVisible(false);
					tipoTC = "MasterCard";
				}else{
					mastercard_img_off.setVisible(false);
					mastercard_img_on.setVisible(true);
					tipoTC = "MasterCard";
					flagTipoTC = true;
				}
			}
		});
		mastercard_img_on.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					tipoTC = "";
					flagTipoTC = false;
					mastercard_img_off.setVisible(true);
					mastercard_img_on.setVisible(false);
				}
			}
		});
		
		visa_img_off.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					mastercard_img_on.setVisible(false);
					mastercard_img_off.setVisible(true);
					maestro_img_off.setVisible(true);
					maestro_img_on.setVisible(false);
					
					visa_img_on.setVisible(true);
					visa_img_off.setVisible(false);
					tipoTC = "Visa";
				}else{
					visa_img_off.setVisible(false);
					visa_img_on.setVisible(true);
					tipoTC = "Visa";
					flagTipoTC = true;
				}
			}
		});
		visa_img_on.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(flagTipoTC){
					tipoTC = "";
					flagTipoTC = false;
					visa_img_off.setVisible(true);
					visa_img_on.setVisible(false);
				}
			}
		});
		
		
		
		
		
	}
	
	private boolean crearCliente() throws DAOExcepcion, IOException{
		// TODO Auto-generated method stub
		if(alquilerdevehiculos!=null){
			try {
				
				if( dni_edittext.getText().length()>0 &&
						nombreapellidos_edittext.getText().length()>0 &&
						direccion_edittext.getText().length()>0 &&
						poblacion_edittext.getText().length()>0 &&
						cod_postal_edittext.getText().length()>0 &&
						carnet_conducir_datepicker.getValue()!=null && 
								num_tarjeta_edittext.getText().length()>0 &&
								Util.isNumeric(caduca_mes_tarjeta_edittext.getText().toString()) &&
								Util.isNumeric(caduca_anyo_tarjeta_edittext.getText().toString()) &&
								Util.isNumeric(cvc_tarjeta_edittext.getText().toString()) &&
								tipoTC != null && tipoTC.length()>0
						){
					ClienteDTO clienteDTO = alquilerdevehiculos.crearCliente(dni_edittext.getText().toString(),
													nombreapellidos_edittext.getText().toString(), 
													direccion_edittext.getText().toString(), 
													poblacion_edittext.getText().toString(), 
													cod_postal_edittext.getText().toString(),
													Util.transformLocalDate(carnet_conducir_datepicker.getValue()), 
													num_tarjeta_edittext.getText().toString(),
													Integer.parseInt(caduca_mes_tarjeta_edittext.getText().toString()),
													Integer.parseInt(caduca_anyo_tarjeta_edittext.getText().toString()),
													Integer.parseInt(cvc_tarjeta_edittext.getText().toString()),
													tipoTC);
					
					try {
						app.presentarMensajeOK("Cliente " + clienteDTO.getNombreyApellidos() +" creado con éxito");;
						app.abrirVentanaDeClienteAReserva("/presentacion/vista/pantalla_crear_reserva.fxml", "Crear nueva reserva", clienteDTO);
						Stage stage = (Stage)icon_guardar_button_shape.getScene().getWindow();
						stage.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						app.presentarMensajeEnPantalla(e.getMessage());
						
					}
				}else{
					app.presentarMensajeEnPantalla("No se ha podido crear cliente. Verifica los campos.");
				}
				
				return true;
			} catch (LogicaExcepcion e) {
				// TODO Auto-generated catch block
				app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				return false;
			}
		}
		return false;
	
	}

}
