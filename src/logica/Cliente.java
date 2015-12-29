package logica;

import java.time.LocalDateTime;

public class Cliente {
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
	
	

	public Cliente(String dni, String nombreyApellidos, String direccion, String poblacion,
			String codPostal, LocalDateTime fechaCarnetConducir, String digitosTC,
			int mesTC, int anyoTC, int cvcTC, String tipoTC) {
		super();
		this.dni = dni;
		this.nombreyApellidos = nombreyApellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.fechaCarnetConducir = fechaCarnetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.anyoTC = anyoTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
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

	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public LocalDateTime getFechaCarnetConducir() {
		return fechaCarnetConducir;
	}

	public void setFechaCarnetConducir(LocalDateTime fechaCarnetConducir) {
		this.fechaCarnetConducir = fechaCarnetConducir;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombreyApellidos=" + nombreyApellidos
				+ "\n, direccion=" + direccion + ", poblacion=" + poblacion
				+ "\n, codPostal=" + codPostal + ", fechaCarnetConducir="
				+ fechaCarnetConducir + "]\n";
	}
	
	//imprimir datos del cliente
	
	@Override 
	public boolean equals(Object o){
		if(o instanceof Cliente){
			return dni.equalsIgnoreCase( ((Cliente)o).getDni());
		}
		return false;
		
	}
	
}