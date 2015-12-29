package persistencia.idao;

import persistencia.dto.EmpleadoDTO;
import excepciones.DAOExcepcion;

public interface IEmpleadoDAO {

	public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion;
	
}
