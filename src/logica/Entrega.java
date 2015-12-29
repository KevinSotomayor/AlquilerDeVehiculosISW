package logica;

import java.time.LocalDateTime;
import java.util.List;

public class Entrega {
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private Reserva esta_asociada_reserva;
	private List<Danyo> listaDanyos;
	private Coche tiene_asociado_coche;
	private Empleado realizada_por_empleado;
	private Devolucion devolucion_asociada;
	

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public double getKms() {
		return kms;
	}
	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getCombustible() {
		return combustible;
	}
	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}
	public Reserva getEsta_asociada_reserva() {
		return esta_asociada_reserva;
	}
	public void setEsta_asociada_reserva(Reserva esta_asociada_reserva) {
		this.esta_asociada_reserva = esta_asociada_reserva;
	}
	public Coche getTiene_asociado_coche() {
		return tiene_asociado_coche;
	}
	public void setTiene_asociado_coche(Coche tiene_asociado_coche) {
		this.tiene_asociado_coche = tiene_asociado_coche;
	}
	public Empleado getRealizada_por_empleado() {
		return realizada_por_empleado;
	}
	public void setRealizada_por_empleado(Empleado realizada_por_empleado) {
		this.realizada_por_empleado = realizada_por_empleado;
	}
	public Devolucion getDevolucion_asociada() {
		return devolucion_asociada;
	}
	public void setDevolucion_asociada(Devolucion devolucion_asociada) {
		this.devolucion_asociada = devolucion_asociada;
	}
	
	public void anyadirDanyo(Danyo d){
		listaDanyos.add(d);
	}
	
	public Danyo eliminarDanyo(int id){
		Danyo danyo_eliminado=null;
		int i= 0;
		boolean b = true;
		while(b){
			if(listaDanyos.get(i).getId_danyo()==id){
				danyo_eliminado = listaDanyos.remove(i);
				b=false;
			}
			i++;
		}
		return danyo_eliminado;
	}

	
	public Danyo buscaDanyo(int id){
		Danyo danyo_seleccionado=null;
		int i= 0;
		boolean b = true;
		while(b){
			if(listaDanyos.get(i).getId_danyo()==id){
				danyo_seleccionado = listaDanyos.get(i);
				b=false;
			}
			i++;
		}
		return danyo_seleccionado;
	}
}