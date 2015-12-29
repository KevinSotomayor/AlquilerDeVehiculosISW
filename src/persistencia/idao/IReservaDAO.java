//IReservaDAO
package persistencia.idao;

import java.util.List;

import persistencia.dto.ReservaDTO;
import excepciones.*;

public interface IReservaDAO {
 public ReservaDTO buscarReserva(int identificador)throws DAOExcepcion;

 public List <ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion;
 
 public List <ReservaDTO> obtenerTodasLasReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion;
 
 public void crearReserva(ReservaDTO reserva) throws DAOExcepcion;
}
