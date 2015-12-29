package persistencia.idao;

import java.util.List;

import persistencia.dto.CategoriaDTO;
import persistencia.dto.CocheDTO;
import excepciones.DAOExcepcion;

public interface ICocheDAO {

	public List <CocheDTO> obtenerCoches(int id_sucursal) throws DAOExcepcion;
	public List <CocheDTO> obtenerCochesCategoria(String nombre_categoria, int id_sucursal) throws DAOExcepcion;

}
