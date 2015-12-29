package presentacion.controlador;

import java.io.IOException;

import org.controlsfx.dialog.Dialogs;

import persistencia.dto.ClienteDTO;
import persistencia.dto.CocheDTO;
import persistencia.dto.ReservaDTO;
import persistencia.dto.SucursalDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlquilerDeVehiculoApp extends Application{
	
	private BorderPane root;
	private Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.mainStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/vista/pantalla_menu_lateral_base.fxml"));

			root = (BorderPane)loader.load();
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Alquiler de vehículos");
			
			//acceso al controlador de la primera pantalla
			ControladorPantallaMenuLateralBase pantallaMain = loader.<ControladorPantallaMenuLateralBase>getController();
			pantallaMain.initStage(primaryStage, this);
			
			//presentamos la pantalla despues de pasarle el stage
			primaryStage.show();
			
			//poner la primera pantalla
			cargarPantalladeIntro();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//recibimos la orden de que pantalla queremos abrir 
	// 0 para abrir la pantalla de cliente
	// 1 para abrir el empleado
	// 2 para abrir el administrador
	public void abrirPantalla(Stage primaryStage, int tipoPantalla) {
		 	switch (tipoPantalla) {
			case 0:
				abrirPantallaSeleccionada(primaryStage, "/presentacion/vista/pantalla_cliente.fxml", "Cliente - Crear reserva",tipoPantalla);
				break;
				
			case 1:
				abrirPantallaSeleccionada(primaryStage, "/presentacion/vista/pantalla_empleado.fxml", "Empleado", tipoPantalla);
				break;
			
			case 2:
				abrirPantallaSeleccionada(primaryStage, "/presentacion/vista/pantalla_administrador.fxml", "Administrador",tipoPantalla);
				break;

			case 3:
				abrirPantallaSeleccionada(primaryStage, "/presentacion/vista/pantalla_acerca.fxml", "Acerca de Alquiler de Vehículos",tipoPantalla);
				break;
				
			default:
				//pantalla de error
				abrirPantallaSeleccionada(primaryStage, "/presentacion/vista/pantalla_error.fxml", "Ooops! Ha ocurrido un error :(",tipoPantalla);

				break;
			}
	    }
	
	//desde aqui lanzamos la pantalla y sustituimos la parte central del borderpane
	private void abrirPantallaSeleccionada(Stage primaryStage, String pantallaXML, String titulo, int tipo){
		try {
			 primaryStage.setTitle(titulo);
			 FXMLLoader loader  = new FXMLLoader(getClass().getResource(pantallaXML));
			 AnchorPane a = (AnchorPane) loader.load();
	 
			 if(root!=null){
				 
				 root.setCenter(a);
				 
				 //pasar las referencias al controlador de la pantalla lanzada
				 switch (tipo) {
					case 0:
						ControladorPantallaCliente pantallaCliente = loader.<ControladorPantallaCliente>getController();
						pantallaCliente.initStage(primaryStage, this);
						break;
						
					case 1:
						ControladorPantallaEmpleado pantallaEmpleado = loader.<ControladorPantallaEmpleado>getController();
						pantallaEmpleado.initStage(primaryStage, this);
						break;
						
					case 2:
						ControladorPantallaAdministrador pantallaAdministrador = loader.<ControladorPantallaAdministrador>getController();
						pantallaAdministrador.initStage(primaryStage, this);
						break;
						
					case 3:
						ControladorPantallaAcercaDe pantallaAcercade = loader.<ControladorPantallaAcercaDe>getController();
						pantallaAcercade.initStage(primaryStage, this);
						break;
						
						
	
					default:
						break;
					}
				 
			 }else{
				 System.err.println("Ha habido un error al abrir la pantalla");
			 }
		 } catch (IOException e) {
			 
			 e.printStackTrace();
			 
		 }
	}
	
	//cargar la pantalla inicial
	public void cargarPantalladeIntro() throws IOException{
		//poner la primera pantalla
		FXMLLoader loaderIntro  = new FXMLLoader(getClass().getResource("/presentacion/vista/pantalla_inicio.fxml"));
		BorderPane a = (BorderPane) loaderIntro.load();
 
		 if(root!=null){
			 root.setCenter(a);
		 }else{
			 System.err.println("Ha habido un error al abrir la pantalla");
		 }
	}
	
	//abrir nuevas ventanas
	public void abrirVentanaDe(Stage primaryStage, String pathVentanaXML, String tituloVentana) throws IOException{
		FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
        AnchorPane page = (AnchorPane)loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle(tituloVentana);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(primaryStage);
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.show();
    
	}
	
	//paso de pantalla con objeto
	public void abrirVentanaDeClienteAReserva(String pathVentanaXML, String tituloVentana, ClienteDTO cliente) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
        AnchorPane page = (AnchorPane)loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle(tituloVentana);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(mainStage);
        
        Scene scene = new Scene(page);
        
        ControladorPantallaCrearReserva controladorReserva = loader.getController(); 
        controladorReserva.initStage(mainStage, this, cliente);
        
    
        dialogStage.setScene(scene);
        dialogStage.show();
       
	}
	
	//paso de pantalla con objeto
	public void abrirVentanaDeSucursalAReservas(String pathVentanaXML, String tituloVentana, SucursalDTO sucursal, boolean flag) throws IOException{
			
			FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
	        AnchorPane page = (AnchorPane)loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(tituloVentana);
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainStage);
	        
	        Scene scene = new Scene(page);
	        
	        ControladorListarReservas controladorReserva = loader.getController(); 
	        controladorReserva.initStage(mainStage, this, sucursal, flag);
	        
	    
	        dialogStage.setScene(scene);
	        dialogStage.show();
	       
		}
	
	//paso de pantalla con objeto
	public void abrirVentanaDeCochesPorSucursal(String pathVentanaXML, String tituloVentana, SucursalDTO sucursal) throws IOException{
			
			FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
	        AnchorPane page = (AnchorPane)loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(tituloVentana);
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainStage);
	        
	        Scene scene = new Scene(page);
	        
	        ControladorListarVehiculos controlador = loader.getController(); 
	        controlador.initStage(mainStage, this, sucursal);
	        
	    
	        dialogStage.setScene(scene);
	        dialogStage.show();
	       
		}
	
	//paso de pantalla con objeto de reserva seleccionada, para abrir coches de esa sucursal y categoría de la reserva
	public void abrirVentanaDeCochesPorSucursalReserva(String pathVentanaXML, String tituloVentana, SucursalDTO sucursal, ReservaDTO reserva) throws IOException{
			
			FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
	        AnchorPane page = (AnchorPane)loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(tituloVentana);
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainStage);
	        
	        Scene scene = new Scene(page);
	        
	        ControladorListarVehiculos controladorReserva = loader.getController(); 
	        controladorReserva.initStage(mainStage, this, sucursal, reserva);
	        
	    
	        dialogStage.setScene(scene);
	        dialogStage.show();
	       
		}
	
	//paso de pantalla con objeto
	public void abrirVentanaDeEntregaDesdeCoche(String pathVentanaXML, String tituloVentana, CocheDTO coche, ReservaDTO reserva) throws IOException{
			
			FXMLLoader loader = new FXMLLoader(AlquilerDeVehiculoApp.class.getResource(pathVentanaXML));
	        AnchorPane page = (AnchorPane)loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle(tituloVentana);
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainStage);
	        
	        Scene scene = new Scene(page);
	        
	        ControladorPantallaCrearEntrega controlador = loader.getController(); 
	        controlador.initStage(mainStage, this, coche, reserva);
	        
	    
	        dialogStage.setScene(scene);
	        dialogStage.show();
	       
		}

	
	public void presentarMensajeEnPantalla(String error){
		 Dialogs.create()
          .title("Datos no validos")
          .masthead("Por favor revisa los campos.")
          .message(error)
          .showWarning();
	}
	public void presentarMensajeOK(String ok){
		 Dialogs.create()
         .title("Alquiler de vehículos")
         .masthead("Información añadida correctamente.")
         .message(ok)
         .showInformation();;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
