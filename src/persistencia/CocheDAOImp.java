package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.dto.CocheDTO;
import persistencia.idao.ICocheDAO;

public class CocheDAOImp implements ICocheDAO{

	protected ConnectionManager connManager;
	
	public CocheDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	
	
	
	public List<CocheDTO> obtenerCoches(int id_sucursal) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet coche = connManager.queryDB("select * from COCHE where SUCURSAL=  " +id_sucursal+  " and MATRICULA not in (select COCHEASIGNADO from ENTREGA)");			
			connManager.close();
			
			List<CocheDTO> listaCochesSucursal = new ArrayList<CocheDTO>();
		
			while (coche.next()){
				
				CocheDTO cocheDTO = new CocheDTO(
						coche.getString("MATRICULA"),
						coche.getDouble("KMSACTUALES"),
						coche.getInt("SUCURSAL"),
						coche.getString("NOMBRE"));
				listaCochesSucursal.add(cocheDTO);
			}	
			return listaCochesSucursal;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);
		}	
	}
	
	@Override
	public List<CocheDTO> obtenerCochesCategoria(String nombre_categoria, int id_sucursal) throws DAOExcepcion {
		
			try{
				connManager.connect();
				ResultSet coche = connManager.queryDB("Select * FROM Coche where sucursal = "+id_sucursal+" and nombre = '" + nombre_categoria+"' and MATRICULA not in (select COCHEASIGNADO from ENTREGA);");
				connManager.close();
				
				List<CocheDTO> listaCochesCategoria = new ArrayList<CocheDTO>();
		
				while (coche.next()){
					
					CocheDTO cocheDTO = new CocheDTO(
							coche.getString("MATRICULA"),
							coche.getDouble("KMSACTUALES"),
							coche.getInt("SUCURSAL"),
							coche.getString("NOMBRE"));
					listaCochesCategoria.add(cocheDTO);
				}	
			
			return listaCochesCategoria;}
			
			catch (SQLException e){	throw new DAOExcepcion(e);
			}	
	}
}



