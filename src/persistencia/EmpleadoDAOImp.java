package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.dto.ClienteDTO;
import persistencia.dto.EmpleadoDTO;
import persistencia.idao.IClienteDAO;
import persistencia.idao.IEmpleadoDAO;

public class EmpleadoDAOImp implements IEmpleadoDAO{

	protected ConnectionManager connManager;
	
	public EmpleadoDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	
	
	@Override
	public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet empleado = connManager.queryDB("select * from EMPLEADO where DNI = '"+dni+"'");
			connManager.close();
		
			if (empleado.next()){
				return new EmpleadoDTO(
						empleado.getString("DNI"),
						empleado.getString("NOMBRE"),
						empleado.getBoolean("ADMINISTRADOR"),
						empleado.getInt("SUCURSAL"));
				
			}else
				return null;	
			
		}catch(Exception e){throw new DAOExcepcion(e);}
		
		
	}

	
}
