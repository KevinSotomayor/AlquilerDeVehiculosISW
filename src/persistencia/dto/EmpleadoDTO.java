package persistencia.dto;

public class EmpleadoDTO {
	private String dni;
	private String nombre;
	private boolean administrador;
	private int sucursal;
	
	public EmpleadoDTO(String dni, String nombre, boolean administrador,
			int sucursal) {
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
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	
}
