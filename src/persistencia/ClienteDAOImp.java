package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.dto.ClienteDTO;
import persistencia.idao.IClienteDAO;

public class ClienteDAOImp implements IClienteDAO{

	protected ConnectionManager connManager;
	
	public ClienteDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	
	
	@Override
	public ClienteDTO buscarCliente(String dni) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet cliente = connManager.queryDB("select * from CLIENTE where DNI = '"+dni+"'");
			connManager.close();
		
			if (cliente.next()){
				return new ClienteDTO(
							cliente.getString("DNI"),
							cliente.getString("NOMBREAPELLIDOS"),
							cliente.getString("DIRECCION"),
							cliente.getString("POBLACION"),
							cliente.getString("CODPOSTAL"),
							LocalDateTime.of(cliente.getDate("FECHACARNETCONDUCIR").toLocalDate(), cliente.getTime("FECHACARNETCONDUCIR").toLocalTime()),
							cliente.getString("DIGITOSTC"),
							cliente.getInt("MESTC"),
							cliente.getInt("añoTC"),
							cliente.getInt("CVCTC"),
							cliente.getString("TIPOTC"));
			}else
				return null;	
			
		}catch(Exception e){throw new DAOExcepcion(e);}
		
	}

	@Override
	public void crearCliente(ClienteDTO cliente) throws DAOExcepcion {
		try{
			connManager.connect();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = cliente.getFechaCanetConducir();
			String formatterDateTime= dateTime.format(formatter);
			
			String str = "insert into CLIENTE (DNI, NOMBREAPELLIDOS, DIRECCION, POBLACION, CODPOSTAL, "+
						"FECHACARNETCONDUCIR, DIGITOSTC, MESTC, \"añoTC\", CVCTC, TIPOTC) values ('"+ cliente.getDni()+"','"+
						cliente.getNombreyApellidos()+"','"+cliente.getDireccion()+"','"+cliente.getPoblacion()+"','"+cliente.getCodPostal()+"','"+
						formatterDateTime+"',"+cliente.getDigitosTC()+","+cliente.getMesTC()+","+cliente.getAnyoTC()+","+cliente.getCvcTC()+",'"+
						cliente.getTipoTC()+"')";

			connManager.updateDB(str);
			System.out.println("Introducido con éxito.");
			connManager.close();
			
			
		}catch(Exception e){
			throw new DAOExcepcion(e);
		}
		
	}
	
	public List<ClienteDTO> obtenerClientes() throws DAOExcepcion {
		
		try{
			connManager.connect();
			ResultSet cliente = connManager.queryDB("select * from CLIENTE");						
			connManager.close();
	  	  
			List<ClienteDTO> listaClienteDTO = new ArrayList<ClienteDTO>();
				
			try{				
				while (cliente.next()){

					ClienteDTO clienteDTO = new ClienteDTO(cliente.getString("DNI"),
							cliente.getString("NOMBREAPELLIDOS"),
							cliente.getString("DIRECCION"),
							cliente.getString("POBLACION"),
							cliente.getString("CODPOSTAL"),
							LocalDateTime.of(cliente.getDate("FECHACARNETCONDUCIR").toLocalDate(), cliente.getTime("FECHACARNETCONDUCIR").toLocalTime()),
							cliente.getString("DIGITOSTC"),
							cliente.getInt("MESTC"),
							cliente.getInt("añoTC"),
							cliente.getInt("CVCTC"),
							cliente.getString("TIPOTC"));
					
					listaClienteDTO.add(clienteDTO);
				}
				return listaClienteDTO;
			
		}catch(Exception e){ throw new DAOExcepcion(e); }
		
		}catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
}
