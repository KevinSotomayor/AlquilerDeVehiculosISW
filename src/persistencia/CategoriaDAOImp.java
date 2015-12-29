package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import persistencia.dto.CategoriaDTO;
import persistencia.idao.*;

import excepciones.DAOExcepcion;

public class CategoriaDAOImp implements ICategoriaDAO {

	protected ConnectionManager connManager;

	public CategoriaDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}

	public CategoriaDTO buscarCategoria(String nombre) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CATEGORIA where NOMBRE= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
				return new CategoriaDTO(
							rs.getString("NOMBRE"),
							rs.getDouble("PRECIOMODILIMITADA"),
							rs.getDouble("PRECIOMODKMS"),
							rs.getDouble("PRECIOKMMODKMS"),
							rs.getDouble("PRECIOSEGUROTRIESGO"),
							rs.getDouble("PRECIOSEGUROTERCEROS"),
							rs.getString("CATEGORIASUPERIOR"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

	
	public List<CategoriaDTO> obtenerCategorias() throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CATEGORIA");						
			connManager.close();
	  	  
			List<CategoriaDTO> listaCategoriaDTO = new ArrayList<CategoriaDTO>();
				
			try{				
				while (rs.next()){

					CategoriaDTO catDTO = new CategoriaDTO(
							rs.getString("NOMBRE"),
							rs.getDouble("PRECIOMODILIMITADA"),
							rs.getDouble("PRECIOMODKMS"),
							rs.getDouble("PRECIOKMMODKMS"),
							rs.getDouble("PRECIOSEGUROTRIESGO"),
							rs.getDouble("PRECIOSEGUROTERCEROS"),
							rs.getString("CATEGORIASUPERIOR"));	 
					listaCategoriaDTO.add(catDTO);
				}
				return listaCategoriaDTO;
			}
			catch (Exception e){	throw new DAOExcepcion(e);}
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
		catch (DAOExcepcion e){		throw e;}

	}

}
