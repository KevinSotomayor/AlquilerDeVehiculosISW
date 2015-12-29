package persistencia;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import excepciones.DAOExcepcion;
import persistencia.dto.EntregaDTO;
import persistencia.idao.IEntregaDAO;

public class EntregaDAOImp implements IEntregaDAO{
		
	protected ConnectionManager connManager;

	public EntregaDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}


	@Override
	public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion {
		try{
			DateTimeFormatter formatterR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTimeR = entrega.getFecha();
			String formatterDateTimeR = dateTimeR.format(formatterR);
 
			connManager.connect();
			
			String str = "insert into ENTREGA (ID, FECHA, TIPOSEGURO, KMS, COMBUSTIBLE, "+
						"COCHEASIGNADO, EMPLEADOREALIZA) values ("+ entrega.getEsta_asociada_reserva()+",'"+
						formatterDateTimeR+"','"+
						entrega.getTipoSeguro()+"',"+entrega.getKms()+","+entrega.getCombustible()+",'"+
						entrega.getTiene_asociado_coche()+"','"+ entrega.getRealizada_por_empleado()+"')";
			connManager.queryDB(str);
			connManager.close();
			
			
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
		
	}

}