package logica;

import java.util.HashMap;

public class Sucursal {
	private int id;
	private String direccion;
	
	private HashMap<String, Coche> listaCoches;
	private HashMap<String, Empleado> listaEmpleados;
	private HashMap<Integer, Reserva> listaReservasRecogida;
	private HashMap<Integer, Reserva> listaReservasDevolucion;

	

	public Sucursal(int id, String direccion) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.listaCoches = new HashMap<String, Coche>();
		this.listaEmpleados = new HashMap<String, Empleado>();
		this.listaReservasRecogida = new HashMap<Integer, Reserva>();
		this.listaReservasDevolucion = new HashMap<Integer, Reserva>();
		
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void anyadirCoche(Coche coche){
		listaCoches.put(coche.getMatricula(), coche);
	}
	
	public Coche eliminarCoche(String matricula){
		Coche coche_devuelto = listaCoches.remove(matricula);
		return coche_devuelto;
	}
	
	public Coche buscaCoche(String matricula){
		return listaCoches.get(matricula);
	}
	
	public void anyadirEmpleado(Empleado empleado){
		listaEmpleados.put(empleado.getNombre(), empleado);
	}
	
	public Empleado eliminarEmpleado(String nombre){
		Empleado empleado_despedido = listaEmpleados.remove(nombre);
		return empleado_despedido;
	}
	
	public Empleado buscaEmpleado(String nombre){
		return listaEmpleados.get(nombre);
	}

	public void anyadirReservaRecogida(Reserva reserva){
		listaReservasRecogida.put(reserva.getId(), reserva);
	}
	
	public Reserva eliminarReservaRecogida(int id){
		Reserva reserva_recogida = listaReservasRecogida.remove(id);
		return reserva_recogida;
	}
	
	public Reserva buscaReservaRecogida(int id){
		return listaReservasRecogida.get(id);
	}

	public void anyadirReservaDevolucion(Reserva reserva){
		listaReservasDevolucion.put(reserva.getId(), reserva);
	}
	
	public Reserva eliminarReservaDevolucion(int id){
		Reserva reserva_recogida = listaReservasDevolucion.remove(id);
		return reserva_recogida;
	}
	
	public Reserva buscaReservaDevolucion(int id){
		return listaReservasDevolucion.get(id);
	}

	
	public HashMap<String, Coche> getListaCoches() {
		return listaCoches;
	}

	public HashMap<String, Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public HashMap<Integer, Reserva> getListaReservasRecogida() {
		return listaReservasRecogida;
	}

	public HashMap<Integer, Reserva> getListaReservasDevolucion() {
		return listaReservasDevolucion;
	}

	@Override
	public String toString() {
		return "\nSucursal [id=" + id + ",direccion=" + direccion + "]";
	}


	
	
	
	
}
