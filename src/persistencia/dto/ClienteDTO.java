package persistencia.dto;

import java.time.LocalDateTime;

public class ClienteDTO {
	private String dni;
	private String nombreyApellidos;
	private String direccion;
	private String poblacion;
	private String codPostal;
	private LocalDateTime fechaCarnetConducir;
	private String digitosTC;
	private int mesTC;
	private int anyoTC;
	private int cvcTC;
	private String tipoTC;
	
	public ClienteDTO(String dni, String nombreyApellidos,
			String direccion, String poblacion, String codPostal,
			LocalDateTime fechaCanetConducir, String digitosTC, int mesTC,
			int annoTC, int cvcTC, String tipoTC) {
		super();
		this.dni = dni;
		this.nombreyApellidos = nombreyApellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.fechaCarnetConducir = fechaCanetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.anyoTC = annoTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
	}
	
	public String getDni() {
		return dni;
	}
	public void setIdentificador(String dni) {
		this.dni = dni;
	}
	public String getNombreyApellidos() {
		return nombreyApellidos;
	}
	public void setNombreyApellidos(String nombreyApellidos) {
		this.nombreyApellidos = nombreyApellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public LocalDateTime getFechaCanetConducir() {
		return fechaCarnetConducir;
	}
	public void setFechaCanetConducir(LocalDateTime fechaCanetConducir) {
		this.fechaCarnetConducir = fechaCanetConducir;
	}
	public String getDigitosTC() {
		return digitosTC;
	}
	public void setDigitosTC(String digitosTC) {
		this.digitosTC = digitosTC;
	}
	public int getMesTC() {
		return mesTC;
	}
	public void setMesTC(int mesTC) {
		this.mesTC = mesTC;
	}
	public int getAnyoTC() {
		return anyoTC;
	}
	public void setAnyoTC(int anyoTC) {
		this.anyoTC = anyoTC;
	}
	public int getCvcTC() {
		return cvcTC;
	}
	public void setCvcTC(int cvcTC) {
		this.cvcTC = cvcTC;
	}
	public String getTipoTC() {
		return tipoTC;
	}
	public void setTipoTC(String tipoTC) {
		this.tipoTC = tipoTC;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "ClienteDTO [dni=" + dni + ", nombreyApellidos="
				+ nombreyApellidos + ", direccion=" + direccion
				+ ", poblacion=" + poblacion + ", codPostal=" + codPostal
				+ ", fechaCanetConducir=" + fechaCarnetConducir + ", digitosTC="
				+ digitosTC + ", mesTC=" + mesTC + ", anyoTC=" + anyoTC
				+ ", cvcTC=" + cvcTC + ", tipoTC=" + tipoTC + "]";
	}
	
	
}
