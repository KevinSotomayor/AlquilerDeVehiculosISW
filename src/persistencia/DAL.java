package persistencia;

import java.util.List;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.dto.CategoriaDTO;
import persistencia.dto.ClienteDTO;
import persistencia.dto.CocheDTO;
import persistencia.dto.EmpleadoDTO;
import persistencia.dto.EntregaDTO;
import persistencia.dto.ReservaDTO;
import persistencia.dto.SucursalDTO;
import persistencia.idao.ICategoriaDAO;
import persistencia.idao.IClienteDAO;
import persistencia.idao.ICocheDAO;
import persistencia.idao.IEmpleadoDAO;
import persistencia.idao.IEntregaDAO;
import persistencia.idao.IReservaDAO;
import persistencia.idao.ISucursalDAO;

public class DAL  {
	private static DAL instance;
	private ICategoriaDAO catDAO;
	private IClienteDAO clienteDAO;
	private ISucursalDAO sucDAO;
	private IReservaDAO resDAO;
	private ICocheDAO cocheDAO;
	private IEntregaDAO entregaDAO;
	private IEmpleadoDAO empleadoDAO;
	
	private DAL() {
	      // Exists only to defeat instantiation.
		catDAO = new ICategoriaDAO() {
			
			@Override
			public List<CategoriaDTO> obtenerCategorias() throws DAOExcepcion {
				return new CategoriaDAOImp().obtenerCategorias();
			}
			
			@Override
			public CategoriaDTO buscarCategoria(String nombre) throws DAOExcepcion {
				return new CategoriaDAOImp().buscarCategoria(nombre);
			}
		};
		
		sucDAO = new ISucursalDAO() {
			
			@Override
			public List<SucursalDTO> obtenerSucursales() throws DAOExcepcion {
				return new SucursalDAOImp().obtenerSucursales();
			}
					
		};
				
		clienteDAO = new IClienteDAO() {
			
			@Override
			public void crearCliente(ClienteDTO cliente) throws DAOExcepcion {
				new ClienteDAOImp().crearCliente(cliente);
			}
			
			@Override
			public ClienteDTO buscarCliente(String dni) throws DAOExcepcion {
				return new ClienteDAOImp().buscarCliente(dni);
			}
		 };
		
		resDAO = new IReservaDAO() {
			
			@Override
			public ReservaDTO buscarReserva(int id) throws DAOExcepcion{
				return new ReservaDAOImp().buscarReserva(id);
			}
			
			@Override
			public List<ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal)throws DAOExcepcion{
				return new ReservaDAOImp().obtenerReservasPorSucursalOrigen(idSucursal);
			}
			
			@Override
			public void crearReserva(ReservaDTO res) throws DAOExcepcion{
				new ReservaDAOImp().crearReserva(res);
			}

			@Override
			public List<ReservaDTO> obtenerTodasLasReservasPorSucursalOrigen(
					int idSucursal) throws DAOExcepcion {
				return new ReservaDAOImp().obtenerTodasLasReservasPorSucursalOrigen(idSucursal);
			}
			
		};
		
		cocheDAO = new ICocheDAO() {
			
			@Override
			public List<CocheDTO> obtenerCoches(int id_sucursal) throws DAOExcepcion {
				
				return new CocheDAOImp().obtenerCoches(id_sucursal);
			}

			@Override
			public List<CocheDTO> obtenerCochesCategoria(
					String nombre_categoria, int id_sucursal)
					throws DAOExcepcion {
				return new CocheDAOImp().obtenerCochesCategoria(nombre_categoria, id_sucursal);
			}
		};
		
		entregaDAO = new IEntregaDAO() {
			
			@Override
			public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion{
				new EntregaDAOImp().crearEntrega(entrega);
			}
			
		};
		
		empleadoDAO = new IEmpleadoDAO() {
			
			@Override
			public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion {
				return new EmpleadoDAOImp().buscarEmpleado(dni);
			}
		};
	}
	
   public static DAL dameDAL() {
      if(instance == null) {
         instance = new DAL();
      }
      return instance;
   }
   
   public List<CategoriaDTO> obtenerCategorias() throws DAOExcepcion {
	   try { 
		   return catDAO.obtenerCategorias(); 
	   } catch (DAOExcepcion e) {
		   throw new DAOExcepcion(e); 
		}
   }
   
   public CategoriaDTO buscarCategoria(String nombre) throws LogicaExcepcion {
	   try { 
		   return catDAO.buscarCategoria(nombre); 
	   } catch (DAOExcepcion e) {
		   throw new LogicaExcepcion(e); 
	   }
   }
   
   public List<SucursalDTO> obtenerSucursales() throws DAOExcepcion {
	   try { 
		   return sucDAO.obtenerSucursales(); 
		   } catch (DAOExcepcion e) {
			   throw new DAOExcepcion(e);  
	       }
   }
   
   public void crearCliente(ClienteDTO cl) throws LogicaExcepcion {
	   try { 
		   clienteDAO.crearCliente(cl); 
		   } catch (DAOExcepcion e) {
			    throw new LogicaExcepcion(e);
		   }
   }
   
   public ClienteDTO buscarCliente(String dni) throws LogicaExcepcion {
	   try { 
		   return clienteDAO.buscarCliente(dni); 
		   } catch (DAOExcepcion e) {
			   throw new LogicaExcepcion(e); 
			   }
   }
   
   public ReservaDTO buscarReserva(int id) throws LogicaExcepcion{
	   try { 
		   return resDAO.buscarReserva(id); 
		   } catch (DAOExcepcion e) {
			   throw new LogicaExcepcion(e);  
		   }
	}
	
	public List<ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion{
		 try { 
			   return resDAO.obtenerReservasPorSucursalOrigen(idSucursal); 
			   } catch (DAOExcepcion e) {
				   throw new DAOExcepcion(e);  
				   }
	}
	public List<ReservaDTO> obtenerTodasLasReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion{
		 try { 
			   return resDAO.obtenerTodasLasReservasPorSucursalOrigen(idSucursal); 
			   } catch (DAOExcepcion e) {
				   throw new DAOExcepcion(e);  
				   }
	}
	
	public void crearReserva(ReservaDTO res) throws LogicaExcepcion{
		 try { 
			  resDAO.crearReserva(res); 
			   } catch (DAOExcepcion e) {
				  throw new LogicaExcepcion(e);
				   }
	}

	public List<ClienteDTO> obtenerListaClientes() throws DAOExcepcion{
		try {
			return new ClienteDAOImp().obtenerClientes();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			   throw new DAOExcepcion(e);  
		}
	}
	
	public List<CocheDTO> obtenerCochesPorSucursal(int idSucursal) throws DAOExcepcion{
		 try { 
			   return cocheDAO.obtenerCoches(idSucursal); 
			   } catch (DAOExcepcion e) {
				   throw new DAOExcepcion(e);  
				   }
	}
	
	public List<CocheDTO> obtenerCochesPorCategoria(String categoria, int sucursal) throws DAOExcepcion{
		 try { 
			   return cocheDAO.obtenerCochesCategoria(categoria, sucursal); 
			   } catch (DAOExcepcion e) {
				   throw new DAOExcepcion(e);  
				   }
	}
	
	public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion{
		 try { 
			  entregaDAO.crearEntrega(entrega); 
			   } catch (DAOExcepcion e) {
				  throw new DAOExcepcion(e);
		 }
	}
	
	public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion {
		try {
			return empleadoDAO.buscarEmpleado(dni);
		} catch (DAOExcepcion e) {
		    throw new DAOExcepcion(e);
		}
	}

}
