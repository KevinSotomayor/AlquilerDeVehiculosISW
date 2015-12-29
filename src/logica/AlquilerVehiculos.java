package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.DAL;
import persistencia.dto.CategoriaDTO;
import persistencia.dto.ClienteDTO;
import persistencia.dto.CocheDTO;
import persistencia.dto.EmpleadoDTO;
import persistencia.dto.EntregaDTO;
import persistencia.dto.ReservaDTO;
import persistencia.dto.SucursalDTO;

public class AlquilerVehiculos {
	private HashMap<String, Cliente> listaClientes;
	private HashMap<Integer, Reserva> listaReservas;
	private HashMap<String, Categoria> listaCategorias;
	private HashMap<Integer, Sucursal> listaSucursal;
	private DAL dal;
	private static AlquilerVehiculos instance;
	
	public static AlquilerVehiculos getInstance() throws DAOExcepcion, LogicaExcepcion{
		if(instance==null){
			instance = new AlquilerVehiculos();
		}
		return instance;
	}
	
	@SuppressWarnings("static-access")
	private AlquilerVehiculos() throws DAOExcepcion, LogicaExcepcion{
		//obtener el DAL
		this.dal = dal.dameDAL();
		
		//Inicializar los hashmap
		listaSucursal = new HashMap<Integer, Sucursal>();
		listaCategorias = new HashMap<String, Categoria>();
		listaReservas = new HashMap<Integer, Reserva>();
		listaClientes = new HashMap<String, Cliente>();
		
		cargarSistema();
	}
	
	//metodo cargar sistema
	private void cargarSistema() throws DAOExcepcion{
		cargarCategorias();		
		cargarSucursales();
	}
	
	public List<CategoriaDTO> cargarCategorias() throws DAOExcepcion{
		
		List<CategoriaDTO> listacatDTO = dal.obtenerCategorias();
		
		// Crear y añadir todas las categorias a la colección
		for (CategoriaDTO catDTO : listacatDTO) {
		       anyadirCategoria(
		    		   new Categoria(catDTO.getNombre(),
		    		   catDTO.getPrecioModIlimitada(), catDTO.getPrecioModKms(),
		    		   catDTO.getPrecioKMModKms(), 
		    		   catDTO.getPrecioSeguroTRiesgo(),
		    		   catDTO.getPrecioSeguroTerceros()));
		       
		} 
		// Actualizar los enlaces que representan la relación “superior”
		for (CategoriaDTO catDTO : listacatDTO)
			if (catDTO.getNombreCategoriaSuperior() != null){
			      buscarCategoria(
			    		  catDTO.getNombre()).setCategoriaSuperior(
			    				  buscarCategoria(catDTO.getNombreCategoriaSuperior())
			    				  );
			}

		return listacatDTO;

	}
	
	public List<SucursalDTO> cargarSucursales() throws DAOExcepcion{
		List<SucursalDTO> listaSucDTO = dal.obtenerSucursales();
				
		// Crear y añadir todas las categorias a la colección
		for (SucursalDTO sucDTO : listaSucDTO) {
		       anyadirSucursal( new Sucursal(sucDTO.getId(), sucDTO.getDireccion()) );
		} 
		
		return listaSucDTO;
	}

	
	
	//metodos de caso de uso - practica 3 y para las reservas con entrega -> True para las reservas sin entregas, las disponibles
	public List<ReservaDTO> listarReservaSucursal(int id_sucursal, boolean flag) throws DAOExcepcion{
		List<ReservaDTO> listaReservaSucursal;
	
		if(flag)
			 listaReservaSucursal = dal.obtenerReservasPorSucursalOrigen(id_sucursal);
		else
			 listaReservaSucursal = dal.obtenerTodasLasReservasPorSucursalOrigen(id_sucursal);
		
		// Crear y añadir todas las reservas a la colección
				for (ReservaDTO resDTO : listaReservaSucursal) {
				       anyadirReserva(new Reserva( 
				    		   resDTO.getId(),
				    		   resDTO.getFechaRecogida(),
				    		   resDTO.getFechaDevolucion(),
				    		   resDTO.getModalidadAlquiler(),
				    		   listaSucursal.get(resDTO.getIdSucursalRecogida()),
				    		   listaSucursal.get(resDTO.getIdSucursalDevolucion()),
				    		   listaCategorias.get(resDTO.getNombreCategoria()),
				    		   null /* Entrega */,
				    		   listaClientes.get(resDTO.getDniCliente())
				    		   ));
				} 
				
		return listaReservaSucursal;
	}
	
	//Modalidades de reserva, lista cno modalidades, se guarda su identificador
	public List<String> obtenerModalidadesDeReserva(){
		List<String> listaModalidades = new ArrayList<String>();
		listaModalidades.add("RENTING");
		listaModalidades.add("LEASING");
		
		return listaModalidades;
	}
	
	//Tipo de seguros en la Entrega:
	public List<String> obtenerTiposDeSeguro(){
		List<String> listaModalidades = new ArrayList<String>();
		listaModalidades.add("A TERCEROS");
		listaModalidades.add("T. RIESGO");
		
		return listaModalidades;
	}
	
	public String obtenerModalidad(int id_modalidad){
		String res = "";
		res = obtenerModalidadesDeReserva().get(id_modalidad);
		return res;
	}
	
	//crear cliente
	public ClienteDTO crearCliente(String dni,
			String nombreyApellidos, 
			String direccion,
			String poblacion,
			String codPostal, 
			LocalDateTime fechaCarnetConducir,
			String digitosTC,
			int mesTC, int anyoTC, int cvcTC, String tipoTC) throws LogicaExcepcion{
		
		
		ClienteDTO clienteDTO = new ClienteDTO(dni,
				nombreyApellidos, direccion,
				poblacion, codPostal, 
				fechaCarnetConducir,
				digitosTC, mesTC, anyoTC, cvcTC, tipoTC);
		
		dal.crearCliente(clienteDTO);
		listaClientes.put(dni,new Cliente(dni, nombreyApellidos, direccion, 
				poblacion, codPostal, fechaCarnetConducir, digitosTC, 
				mesTC, anyoTC, cvcTC, tipoTC) );
		

		
		return clienteDTO;
	}
	
	//Crear reserva con id de cliente
	public void crearReserva(String dni, ReservaDTO reservaDTO) throws LogicaExcepcion, DAOExcepcion{
		
		Reserva reservaLogica = new Reserva(
				reservaDTO.getId(), 
				reservaDTO.getFechaRecogida(), 
				reservaDTO.getFechaRecogida(),
				reservaDTO.getModalidadAlquiler(), 
				listaSucursal.get(reservaDTO.getIdSucursalRecogida()),
				listaSucursal.get(reservaDTO.getIdSucursalDevolucion()),
				listaCategorias.get(reservaDTO.getNombreCategoria()),
				null, 
				listaClientes.get(reservaDTO.getDniCliente())
				);
		
		listaSucursal.get(reservaDTO.getIdSucursalRecogida()).anyadirReservaRecogida(reservaLogica);
		listaSucursal.get(reservaDTO.getIdSucursalDevolucion()).anyadirReservaDevolucion(reservaLogica);
		
		dal.crearReserva(reservaDTO);
		
		listaReservas.put(reservaDTO.getId(), reservaLogica);
						
	}
	
	
	//Lista clientes methods
		public void anyadirCliente(Cliente cliente){
			listaClientes.put(cliente.getDni(), cliente);
		}
		
		public Cliente eliminarCliente(String dni){
			return listaClientes.remove(dni);
		}
		
		public ClienteDTO buscarCliente(String dni) throws LogicaExcepcion{
			if(listaClientes.get(dni)==null){
				ClienteDTO clienteDTO = dal.buscarCliente(dni);
				if(clienteDTO!=null){
					Cliente cliente = new Cliente(
							clienteDTO.getDni(),
							clienteDTO.getNombreyApellidos(),
							clienteDTO.getDireccion(),
							clienteDTO.getPoblacion(),
							clienteDTO.getCodPostal(),
							clienteDTO.getFechaCanetConducir(),
							clienteDTO.getDigitosTC(),
							clienteDTO.getMesTC(),
							clienteDTO.getAnyoTC(),
							clienteDTO.getCvcTC(),
							clienteDTO.getTipoTC()
							);
					listaClientes.put(dni, cliente);
					return clienteDTO;
				}
				return null;
			}else{
				Cliente aux = listaClientes.get(dni);

				ClienteDTO clienteEncontrado = new ClienteDTO(aux.getDni(),
						aux.getNombreyApellidos(), aux.getDireccion(), aux.getPoblacion(), 
						aux.getCodPostal(), aux.getFechaCarnetConducir(), aux.getDigitosTC(),
						aux.getMesTC(), aux.getAnyoTC(), aux.getCvcTC(), aux.getTipoTC());

				return clienteEncontrado;

			}
		}
		
		//recoge los coches que lee de la base de datos que están disponibles por sucursal
		public List<CocheDTO> obtenerCochesPorsucursal(int id_sucursal) throws DAOExcepcion{
			List<CocheDTO> listaCoches = dal.obtenerCochesPorSucursal(id_sucursal);
			
			return listaCoches;
		}
		
		public List<CocheDTO> obtenerCochesPorCategoria(String categoria, int sucursal) throws DAOExcepcion{
			categoria = categoria.replace(" ", "");
			List<CocheDTO> listaCoches = dal.obtenerCochesPorCategoria(categoria, sucursal);
			
			Categoria cat = buscarCategoria(categoria);

			while(listaCoches!= null && listaCoches.size()==0){

				if(cat!=null){
					cat = buscarCategoria(cat.getCategoriaSuperior().getNombreCategoria());
					listaCoches = dal.obtenerCochesPorCategoria(cat.getNombreCategoria(), sucursal);
					
				}

			}
			
			return listaCoches;
		}
		
		//Buscar empleado para asociar en la entrega
		public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion{			
				return dal.buscarEmpleado(dni);
		}
		
		//Crear entrega 
		public void crearEntrega(EntregaDTO entrega) throws LogicaExcepcion, DAOExcepcion{	
			dal.crearEntrega(entrega);				
		}
		
		//lista reserva methods
		public void anyadirReserva(Reserva reserva){
			listaReservas.put(reserva.getId(), reserva);
		}
		
		public Reserva eliminarCliente(int id){
			return listaReservas.remove(id);
		}
		
		public Reserva buscarReserva(int id){
			return listaReservas.get(id);
		}
		
		//lista categoria methods
		public void anyadirCategoria(Categoria categoria){
			listaCategorias.put(categoria.getNombreCategoria(), categoria);
		}
		
		public Categoria eliminarCategoria(String nombreCategoria){
			return listaCategorias.remove(nombreCategoria);
		}
		
		public Categoria buscarCategoria(String nombreCategoria){
			return listaCategorias.get(nombreCategoria);
		}

		
		//lista reserva methods
		public void anyadirSucursal(Sucursal sucursal){
			listaSucursal.put(sucursal.getId(), sucursal);
		}
		
		public Sucursal eliminarSucursal(int id){
			return listaSucursal.remove(id);
		}
		
		public Sucursal buscarSucursal(int id){
			return listaSucursal.get(id);
		}

		//getters
		public HashMap<String, Cliente> getListaClientes() {
			return listaClientes;
		}

		public HashMap<Integer, Reserva> getListaReservas() {
			return listaReservas;
		}

		public HashMap<String, Categoria> getListaCategorias() {
			return listaCategorias;
		}

		public HashMap<Integer, Sucursal> getListaSucursal() {
			return listaSucursal;
		}
	
}
