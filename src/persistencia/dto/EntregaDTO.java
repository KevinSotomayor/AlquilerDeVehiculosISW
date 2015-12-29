package persistencia.dto;
import java.time.LocalDateTime;
public class EntregaDTO {
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private int esta_asociada_reserva;
	private String tiene_asociado_coche;
	private String realizada_por_empleado;
	
	
	public EntregaDTO(int id, LocalDateTime fecha, String tipoSeguro, double kms,
			double combustible, String tiene_asociado_coche, String realizada_por_empleado) {
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.esta_asociada_reserva = id;
		this.tiene_asociado_coche = tiene_asociado_coche;
		this.realizada_por_empleado = realizada_por_empleado;
	}
	

	public int getEsta_asociada_reserva() {
		return esta_asociada_reserva;
	}


	public void setEsta_asociada_reserva(int esta_asociada_reserva) {
		this.esta_asociada_reserva = esta_asociada_reserva;
	}


	public String getTiene_asociado_coche() {
		return tiene_asociado_coche;
	}

	public void setTiene_asociado_coche(String tiene_asociado_coche) {
		this.tiene_asociado_coche = tiene_asociado_coche;
	}

	public String getRealizada_por_empleado() {
		return realizada_por_empleado;
	}

	public void setRealizada_por_empleado(String realizada_por_empleado) {
		this.realizada_por_empleado = realizada_por_empleado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public double getKms() {
		return kms;
	}
	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getCombustible() {
		return combustible;
	}
	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}


	@Override
	public String toString() {
		return "EntregaDTO [fecha=" + fecha + ", tipoSeguro=" + tipoSeguro
				+ ", kms=" + kms + ", combustible=" + combustible
				+ ", esta_asociada_reserva=" + esta_asociada_reserva
				+ ", tiene_asociado_coche=" + tiene_asociado_coche
				+ ", realizada_por_empleado=" + realizada_por_empleado + "]";
	}
	
	
}
