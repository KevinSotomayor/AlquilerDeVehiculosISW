package logica;

public class Coche {
	private String matricula;
	private double kmsActuales;
	private Sucursal esta_en_sucursal;
	private Categoria categoria;
	
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
	public Sucursal getEsta_en_sucursal() {
		return esta_en_sucursal;
	}
	public void setEsta_en_sucursal(Sucursal esta_en_sucursal) {
		this.esta_en_sucursal = esta_en_sucursal;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
