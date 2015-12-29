package presentacion.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.dto.ClienteDTO;
import persistencia.dto.ReservaDTO;
import persistencia.dto.CategoriaDTO;
import persistencia.dto.SucursalDTO;
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

public class ControladorPantallaCrearReserva implements Initializable{
	//objetos que utilizamos en la ventana
	@FXML Text cliente_nombre_text;
	@FXML TextField dni_edittext;
	
	@FXML ChoiceBox<CategoriaDTO> categoria_choicebox;
	@FXML ChoiceBox<String> modalidad_choicebox;
	@FXML ChoiceBox<SucursalDTO> suc_recogida_choicebox;
	@FXML ChoiceBox<SucursalDTO> suc_devolucion_choicebox;
	
	private List<CategoriaDTO> categoriasDTO;
	private List<String> modalidadDeReserva;
	private List<SucursalDTO> suc_rec;
	private List<SucursalDTO> suc_dev;
	
	//fecha
	@FXML DatePicker fecha_recogida_datepicker;
	@FXML DatePicker fecha_devolucion_datepicker;
	
	//boton de guardar
	@FXML Circle guardar_boton_shape;
	@FXML ImageView icon_guardar_button_shape;
	
	//boton de cancelar
	@FXML Circle cancelar_boton_shape;
	@FXML ImageView icon_cancelar_button_shape;
	
	//main de la logica
	private AlquilerVehiculos alquilerdevehiculos;
	private Stage primaryStage;
	private ClienteDTO cliente;
	private AlquilerDeVehiculoApp app; 
	
	public void initStage(Stage primaryStage, AlquilerDeVehiculoApp app, ClienteDTO cliente){
		this.primaryStage = primaryStage;
		this.app = app;
		this.cliente = cliente;
		
		if(app==null){
			this.app = new AlquilerDeVehiculoApp();
		}
		//Nombre del cliente encontrado:
		cliente_nombre_text.setText(cliente.getNombreyApellidos());

		//NO clickable dni
		dni_edittext.setText(cliente.getDni());
		dni_edittext.setDisable(true);
	}

	public void initialize(URL location, ResourceBundle resources) {

		try{
			alquilerdevehiculos = AlquilerVehiculos.getInstance();
			this.categoriasDTO = alquilerdevehiculos.cargarCategorias();
			this.modalidadDeReserva = alquilerdevehiculos.obtenerModalidadesDeReserva();
			this.suc_rec = alquilerdevehiculos.cargarSucursales();
			this.suc_dev = alquilerdevehiculos.cargarSucursales();
		
		}catch(Exception e){
			app.presentarMensajeEnPantalla("No se ha podido mostrar la pantalla de reserva\n"+e.getLocalizedMessage());
		}
		
		
		//boton guardar
		guardar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ReservaDTO res = crearReservaDTO();
				if(res!=null){
					crearReserva(res);
					app.presentarMensajeOK("Reserva creada con éxtio para " + cliente.getNombreyApellidos());;
					Stage stage = (Stage)icon_guardar_button_shape.getScene().getWindow();
					stage.close();
				}

			}
			
		});
		
		icon_guardar_button_shape.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ReservaDTO res = crearReservaDTO();
				if(res!=null){
					crearReserva(res);
					app.presentarMensajeOK("Reserva creada con éxtio para " + cliente.getNombreyApellidos());;
					Stage stage = (Stage)icon_guardar_button_shape.getScene().getWindow();
					stage.close();
				}
			}
		});
		
		//boton cancelar
		cancelar_boton_shape.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Stage stage = (Stage)cancelar_boton_shape.getScene().getWindow();
				stage.close();
				}
			
		});
		
		icon_cancelar_button_shape.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Stage stage = (Stage)icon_cancelar_button_shape.getScene().getWindow();
				stage.close();
			}
		});
		
		//ChoiceBox Categoria
		ObservableList<CategoriaDTO> observableListCategoria = FXCollections.observableArrayList();
		observableListCategoria.addAll(categoriasDTO);
		categoria_choicebox.setItems(observableListCategoria);
		
		//ChoiceBox Modalidad
		ObservableList<String> observableListModalidad = FXCollections.observableArrayList();
		observableListModalidad.addAll(modalidadDeReserva);
		modalidad_choicebox.setItems(observableListModalidad);
		
		//ChoiceBox Sucursales de Recogida
		ObservableList<SucursalDTO> observableListSucRecogida = FXCollections.observableArrayList();
		observableListSucRecogida.addAll(suc_rec);
		suc_recogida_choicebox.setItems(observableListSucRecogida);
		
		//ChoiceBox Sucursales de Devolucion
		ObservableList<SucursalDTO> observableListSucDevolucion = FXCollections.observableArrayList();
		observableListSucDevolucion.addAll(suc_dev);
		suc_devolucion_choicebox.setItems(observableListSucDevolucion);
	}
	
	private ReservaDTO crearReservaDTO(){
		if( fecha_recogida_datepicker.getValue()!= null &&
				fecha_devolucion_datepicker.getValue() != null &&
						modalidad_choicebox.getSelectionModel().getSelectedIndex()>=0 &&
								categoria_choicebox.getSelectionModel().getSelectedItem() != null &&
										suc_recogida_choicebox.getSelectionModel().getSelectedItem() != null &&
												suc_devolucion_choicebox.getSelectionModel().getSelectedItem() != null
				){
		ReservaDTO res = new ReservaDTO(
				obtenerIdReserva(cliente.getDni(), 
						suc_recogida_choicebox.getSelectionModel().getSelectedItem().getId(), 
						suc_devolucion_choicebox.getSelectionModel().getSelectedItem().getId()),
				Util.transformLocalDate(fecha_recogida_datepicker.getValue()),
				Util.transformLocalDate(fecha_devolucion_datepicker.getValue()),
				modalidad_choicebox.getSelectionModel().getSelectedIndex(),
				categoria_choicebox.getSelectionModel().getSelectedItem().getNombre(),cliente.getDni(),
				suc_recogida_choicebox.getSelectionModel().getSelectedItem().getId(),
				suc_devolucion_choicebox.getSelectionModel().getSelectedItem().getId());
		System.out.println(res);
				return res;
		}else {
			app.presentarMensajeEnPantalla("No se ha podido crear la reserva. Verifica los datos.");
			return null;
		}
	}
	
	private int obtenerIdReserva(String dni,int suc_rec, int suc_dev){
		int x = 0;
		String aux = dni.substring(1, dni.length()-2);

		if(Util.isNumeric(aux)){
			x = Integer.parseInt(aux);
			x += (int) (Math.random() * ((suc_rec*suc_dev)*999));
			x += x+suc_rec-suc_dev;

		}else{
			x = (int) (Math.random() * ((suc_rec*suc_dev)*999));
			x += x+suc_rec-suc_dev;
		}
		
		return x;
	}
	
	private boolean crearReserva(ReservaDTO reserva) {

		if(alquilerdevehiculos!=null && reserva!=null){
			
			try {
				alquilerdevehiculos.crearReserva(cliente.getDni(), reserva );
				return true;
			} catch (LogicaExcepcion | DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				app.presentarMensajeEnPantalla(e.getLocalizedMessage());
				return false;
			}
		
		}
		return false;
	
	}
}
