package logica;

import java.util.HashMap;

public class Categoria {
	private String nombreCategoria;
	private double precioModLimitada;
	private double precioModKms;
	private double kmModKms;
	private double precioSeguroTRiesgo;
	private double precioSeguroTerceros;
	private HashMap<String, Coche> listaCoches;
	private Categoria categoriaSuperior;
	
	
	public Categoria(String nombreCategoria, double precioModLimitada,
			double precioModKms, double kmModKms, double precioSeguroTRiesgo,
			double precioSeguroTerceros) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.precioModLimitada = precioModLimitada;
		this.precioModKms = precioModKms;
		this.kmModKms = kmModKms;
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
		this.precioSeguroTerceros = precioSeguroTerceros;
	}
	

	public double getPrecioModLimitada() {
		return precioModLimitada;
	}
	public void setPrecioModLimitada(double precioModLimitada) {
		this.precioModLimitada = precioModLimitada;
	}
	public double getPrecioModKms() {
		return precioModKms;
	}
	public void setPrecioModKms(double precioModKms) {
		this.precioModKms = precioModKms;
	}
	public double getKmModKms() {
		return kmModKms;
	}
	public void setKmModKms(double kmModKms) {
		this.kmModKms = kmModKms;
	}
	public double getPrecioSeguroTRiesgo() {
		return precioSeguroTRiesgo;
	}
	public void setPrecioSeguroTRiesgo(double precioSeguroTRiesgo) {
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
	}
	public double getPrecioSeguroTerceros() {
		return precioSeguroTerceros;
	}
	public void setPrecioSeguroTerceros(double precioSeguroTerceros) {
		this.precioSeguroTerceros = precioSeguroTerceros;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public Categoria getCategoriaSuperior() {
		return categoriaSuperior;
	}
	public void setCategoriaSuperior(Categoria categoriaSuperior) {
		this.categoriaSuperior = categoriaSuperior;
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


	@Override
	public String toString() {
		return "\n\t Categoria [nombreCategoria=" + nombreCategoria
				+ "\n\t precioModLimitada=" + precioModLimitada
				+ "\n\t precioModKms=" + precioModKms + ", kmModKms=" + kmModKms
				+ "\n\t precioSeguroTRiesgo=" + precioSeguroTRiesgo
				+ "\n\t precioSeguroTerceros=" + precioSeguroTerceros +" "
						+ "Categoria superior: ] " + categoriaSuperior;
	}
	

}
