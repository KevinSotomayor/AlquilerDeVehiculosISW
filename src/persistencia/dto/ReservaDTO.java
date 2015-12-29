package persistencia.dto;

import java.time.LocalDateTime;

public class ReservaDTO {
	private int id;
	private LocalDateTime fechaRecogida;
	private LocalDateTime fechaDevolucion;
	private int modalidadAlquiler;
	private String dniCliente;
	private String nombreCategoria;
	private int idSucursalRecogida;
	private int idSucursalDevolucion;
	
	public ReservaDTO(int id, LocalDateTime fechaRecogida,
			LocalDateTime fechaDevolucion, int modalidadAlquiler,
			String nombreCategoria, String dniCliente, int idSucursalRecogida,
			int idSucursalDevolucion) {
		super();
		this.id = id;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.modalidadAlquiler = modalidadAlquiler;
		this.dniCliente = dniCliente;
		this.nombreCategoria = nombreCategoria;
		this.idSucursalRecogida = idSucursalRecogida;
		this.idSucursalDevolucion = idSucursalDevolucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaRecogida() {
		return fechaRecogida;
	}

	public void setFechaRecogida(LocalDateTime fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getModalidadAlquiler() {
		return modalidadAlquiler;
	}

	public void setModalidadAlquiler(int modalidadAlquiler) {
		this.modalidadAlquiler = modalidadAlquiler;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public int getIdSucursalRecogida() {
		return idSucursalRecogida;
	}

	public void setIdSucursalRecogida(int idSucursalRecogida) {
		this.idSucursalRecogida = idSucursalRecogida;
	}

	public int getIdSucursalDevolucion() {
		return idSucursalDevolucion;
	}

	public void setIdSucursalDevolucion(int idSucursalDevolucion) {
		this.idSucursalDevolucion = idSucursalDevolucion;
	}

	@Override
	public String toString() {
		return "ReservaDTO [id=" + id + ", fechaRecogida=" + fechaRecogida
				+ ", fechaDevolucion=" + fechaDevolucion
				+ ", modalidadAlquiler=" + modalidadAlquiler + ", dniCliente="
				+ dniCliente + ", nombreCategoria=" + nombreCategoria
				+ ", idSucursalRecogida=" + idSucursalRecogida
				+ ", idSucursalDevolucion=" + idSucursalDevolucion + "]";
	}
		
	
}
