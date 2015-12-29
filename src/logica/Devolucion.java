package logica;

import java.time.LocalDateTime;
import java.util.List;

public class Devolucion {
	private LocalDateTime fecha;
	private double totalAcobrar;
	private double cobrado;
	private double kms;
	private double combustible;
	private List<Danyo> listaDanyos;
	private Empleado realizada_por_empleado;
	private Entrega entrega_asociada;
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getTotalAcobrar() {
		return totalAcobrar;
	}
	public void setTotalAcobrar(double totalAcobrar) {
		this.totalAcobrar = totalAcobrar;
	}
	public double getCobrado() {
		return cobrado;
	}
	public void setCobrado(double cobrado) {
		this.cobrado = cobrado;
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
	public Empleado getRealizada_por_empleado() {
		return realizada_por_empleado;
	}
	public void setRealizada_por_empleado(Empleado realizada_por_empleado) {
		this.realizada_por_empleado = realizada_por_empleado;
	}
	public Entrega getEntrega_asociada() {
		return entrega_asociada;
	}
	public void setEntrega_asociada(Entrega entrega_asociada) {
		this.entrega_asociada = entrega_asociada;
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