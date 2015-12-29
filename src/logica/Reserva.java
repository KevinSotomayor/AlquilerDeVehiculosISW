package logica;

import java.time.LocalDateTime;

public class Reserva {
	private int id;
	private LocalDateTime fechaRecogida;
	private LocalDateTime fechaDevolucion;
	private int modalidadAlquiler;
	private Sucursal lugar_recogida_sucursal;
	private Sucursal lugar_devolucion_sucursal;
	private Categoria tiene_asociada_categoria;
	private Entrega tiene_asociada_entrega;
	private Cliente realizada_por_cliente;

	
	public Reserva(int id, LocalDateTime fechaRecogida,
			LocalDateTime fechaDevolucion, int modalidadAlquiler,
			Sucursal lugar_recogida_sucursal,
			Sucursal lugar_devolucion_sucursal,
			Categoria tiene_asociada_categoria, Entrega tiene_asociada_entrega,
			Cliente realizada_por_cliente) {
		super();
		this.id = id;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.modalidadAlquiler = modalidadAlquiler;
		this.lugar_recogida_sucursal = lugar_recogida_sucursal;
		this.lugar_devolucion_sucursal = lugar_devolucion_sucursal;
		this.tiene_asociada_categoria = tiene_asociada_categoria;
		this.tiene_asociada_entrega = tiene_asociada_entrega;
		this.realizada_por_cliente = realizada_por_cliente;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sucursal getLugar_recogida_sucursal() {
		return lugar_recogida_sucursal;
	}
	public void setLugar_recogida_sucursal(Sucursal lugar_recogida_sucursal) {
		this.lugar_recogida_sucursal = lugar_recogida_sucursal;
	}
	public Sucursal getLugar_devolucion_sucursal() {
		return lugar_devolucion_sucursal;
	}
	public void setLugar_devolucion_sucursal(Sucursal lugar_devolucion_sucursal) {
		this.lugar_devolucion_sucursal = lugar_devolucion_sucursal;
	}
	public Categoria getTiene_asociada_categoria() {
		return tiene_asociada_categoria;
	}
	public void setTiene_asociada_categoria(Categoria tiene_asociada_categoria) {
		this.tiene_asociada_categoria = tiene_asociada_categoria;
	}
	public Entrega getTiene_asociada_entrega() {
		return tiene_asociada_entrega;
	}
	public void setTiene_asociada_entrega(Entrega tiene_asociada_entrega) {
		this.tiene_asociada_entrega = tiene_asociada_entrega;
	}
	public Cliente getRealizada_por_cliente() {
		return realizada_por_cliente;
	}
	public void setRealizada_por_cliente(Cliente realizada_por_cliente) {
		this.realizada_por_cliente = realizada_por_cliente;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id
				+ "\n fechaRecogida:" + fechaRecogida
				+ "\n lugar_recogida_sucursal:" + lugar_recogida_sucursal.getDireccion()
				+ "\n fechaDevolucion=" + fechaDevolucion
				+ "\n lugar_devolucion_sucursal:" + lugar_devolucion_sucursal.getDireccion()
				+ "\n modalidad_alquiler:" + modalidadAlquiler
				//+ "\n realizada_por_cliente:" + realizada_por_cliente.getNombreyApellidos() 
				+ "\n tiene_asociada_categoria: " + tiene_asociada_categoria
				+ "]\n";
	}
	
	
}
