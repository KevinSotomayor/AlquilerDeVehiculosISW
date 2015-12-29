package persistencia.idao;

import persistencia.dto.EntregaDTO;
import excepciones.DAOExcepcion;

public interface IEntregaDAO {

	void crearEntrega(EntregaDTO entrega) throws DAOExcepcion;

}
