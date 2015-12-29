package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.dto.ReservaDTO;
import persistencia.idao.IReservaDAO;

public class ReservaDAOImp implements IReservaDAO{
		
	protected ConnectionManager connManager;

	public ReservaDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}

	public ReservaDTO buscarReserva(int id) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet reserva = connManager.queryDB("select * from RESERVA where ID= '"+id+"'");
			connManager.close();
		
			if (reserva.next())
				return new ReservaDTO(
						reserva.getInt("ID"),
						LocalDateTime.of(reserva.getDate("FECHARECOGIDA").toLocalDate(), reserva.getTime("FECHARECOGIDA").toLocalTime()),
						LocalDateTime.of(reserva.getDate("FECHADEVOLUCION").toLocalDate(), reserva.getTime("FECHADEVOLUCION").toLocalTime()),
						reserva.getInt("MODALIDADALQUILER"),
						reserva.getString("CATEGORIA"),
						reserva.getString("CLIENTEREALIZA"),
						reserva.getInt("SUCURSALRECOGIDA"),
						reserva.getInt("SUCURSALDEVOLUCION"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

	@Override
	public List<ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal)
			throws DAOExcepcion {
		
		try{
			connManager.connect();
			ResultSet reserva = connManager.queryDB("select * from RESERVA where SUCURSALRECOGIDA= '"+idSucursal+"'" + " AND NOT EXISTS"
			+"(SELECT * FROM ENTREGA WHERE RESERVA.ID = ENTREGA.ID);");
			connManager.close();
			
			List<ReservaDTO> listaReservasRecogida = new ArrayList<ReservaDTO>();
		
			while (reserva.next()){
				
				ReservaDTO reservaDTO = new ReservaDTO(
						reserva.getInt("ID"),
						LocalDateTime.of(reserva.getDate("FECHARECOGIDA").toLocalDate(), reserva.getTime("FECHARECOGIDA").toLocalTime()),
						LocalDateTime.of(reserva.getDate("FECHADEVOLUCION").toLocalDate(), reserva.getTime("FECHADEVOLUCION").toLocalTime()),
						reserva.getInt("MODALIDADALQUILER"),
						reserva.getString("CATEGORIA"),
						reserva.getString("CLIENTEREALIZA"),
						reserva.getInt("SUCURSALRECOGIDA"),
						reserva.getInt("SUCURSALDEVOLUCION"));
				listaReservasRecogida.add(reservaDTO);
			}	
			return listaReservasRecogida;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);
		}	
	}
	
	@Override
	public List<ReservaDTO> obtenerTodasLasReservasPorSucursalOrigen(int idSucursal)
			throws DAOExcepcion {
		
		try{
			connManager.connect();
			ResultSet reserva = connManager.queryDB("select * from RESERVA where SUCURSALRECOGIDA= '"+idSucursal+"'" +";");
			connManager.close();
			
			List<ReservaDTO> listaReservasRecogida = new ArrayList<ReservaDTO>();
		
			while (reserva.next()){
				
				ReservaDTO reservaDTO = new ReservaDTO(
						reserva.getInt("ID"),
						LocalDateTime.of(reserva.getDate("FECHARECOGIDA").toLocalDate(), reserva.getTime("FECHARECOGIDA").toLocalTime()),
						LocalDateTime.of(reserva.getDate("FECHADEVOLUCION").toLocalDate(), reserva.getTime("FECHADEVOLUCION").toLocalTime()),
						reserva.getInt("MODALIDADALQUILER"),
						reserva.getString("CATEGORIA"),
						reserva.getString("CLIENTEREALIZA"),
						reserva.getInt("SUCURSALRECOGIDA"),
						reserva.getInt("SUCURSALDEVOLUCION"));
				listaReservasRecogida.add(reservaDTO);
			}	
			return listaReservasRecogida;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);
		}	
	}
	

	@Override
	public void crearReserva(ReservaDTO reserva) throws DAOExcepcion {
		try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTimeR = reserva.getFechaRecogida();
			String formatterDateTimeR = dateTimeR.format(formatter);

			LocalDateTime dateTimeD = reserva.getFechaDevolucion();
			String formatterDateTimeD = dateTimeD.format(formatter);
			
			connManager.connect();
			
			String str = "insert into RESERVA (ID, FECHARECOGIDA, FECHADEVOLUCION, MODALIDADALQUILER, CATEGORIA, "+
						"CLIENTEREALIZA, SUCURSALRECOGIDA, SUCURSALDEVOLUCION) values ("+ reserva.getId()+",'"+
						formatterDateTimeR+"','"+formatterDateTimeD+"',"+reserva.getModalidadAlquiler()+",'"+
						reserva.getNombreCategoria()+"','"+reserva.getDniCliente()+"',"+reserva.getIdSucursalRecogida()+","+
						reserva.getIdSucursalDevolucion()+")";
			connManager.queryDB(str);
			connManager.close();
			
			
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
		
	}

}
