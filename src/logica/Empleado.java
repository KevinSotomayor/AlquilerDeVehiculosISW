package logica;

public class Empleado {
	private String dni;
	private String nombre;
	private boolean administrador;
	private Sucursal sucursal;
	
	public Empleado(String dni, String nombre, boolean administrador, Sucursal sucursal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.administrador = administrador;
		this.sucursal = sucursal;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	
	
	
}
