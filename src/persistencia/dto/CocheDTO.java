package persistencia.dto;

public class CocheDTO {

	private String matricula;
	private double kmsActuales;
	private int esta_en_sucursal;
	private String categoria_asociada;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getKmsActuales() {
		return kmsActuales;
	}
	public void setKmsActuales(double kmsActuales) {
		this.kmsActuales = kmsActuales;
	}
	public int getEsta_en_sucursal() {
		return esta_en_sucursal;
	}
	public void setEsta_en_sucursal(int esta_en_sucursal) {
		this.esta_en_sucursal = esta_en_sucursal;
	}
	public String getCategoria_asociada() {
		return categoria_asociada;
	}
	public void setCategoria_asociada(String categoria_asociada) {
		this.categoria_asociada = categoria_asociada;
	}
	public CocheDTO(String matricula,
			double kmsActuales, 
			int esta_en_sucursal,
			String categoria_asociada) {
		super();
		this.matricula = matricula;
		this.kmsActuales = kmsActuales;
		this.esta_en_sucursal = esta_en_sucursal;
		this.categoria_asociada = categoria_asociada;
	}
	
	
	
	
}
