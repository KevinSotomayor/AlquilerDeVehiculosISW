package persistencia.dto;

public class CategoriaDTO {
	private String nombre;
	private double precioModIlimitada;
	private double precioModKms;
	private double precioKMModKms;
	private double precioSeguroTRiesgo;
	private double precioSeguroTerceros;
	private String nombreCategoriaSuperior;
	
	public CategoriaDTO(String nombre, double precioModIlimitada,
			double precioModKms, double precioKMModKms,
			double precioSeguroTRiesgo, double precioSeguroTerceros,
			String nombreCategoriaSuperior) {
		super();
		this.nombre = nombre;
		this.precioModIlimitada = precioModIlimitada;
		this.precioModKms = precioModKms;
		this.precioKMModKms = precioKMModKms;
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
		this.precioSeguroTerceros = precioSeguroTerceros;
		this.nombreCategoriaSuperior = nombreCategoriaSuperior;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioModIlimitada() {
		return precioModIlimitada;
	}

	public void setPrecioModIlimitada(double precioModIlimitada) {
		this.precioModIlimitada = precioModIlimitada;
	}

	public double getPrecioModKms() {
		return precioModKms;
	}

	public void setPrecioModKms(double precioModKms) {
		this.precioModKms = precioModKms;
	}

	public double getPrecioKMModKms() {
		return precioKMModKms;
	}

	public void setPrecioKMModKms(double precioKMModKms) {
		this.precioKMModKms = precioKMModKms;
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

	public String getNombreCategoriaSuperior() {
		return nombreCategoriaSuperior;
	}

	public void setNombreCategoriaSuperior(String nombreCategoriaSuperior) {
		this.nombreCategoriaSuperior = nombreCategoriaSuperior;
	}

	@Override
	public String toString() {
		return nombre.toUpperCase();
	}
		
		
}
