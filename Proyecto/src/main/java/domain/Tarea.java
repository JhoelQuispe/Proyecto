package domain;

import java.util.Date;

public class Tarea implements EntidadBase<Integer>{
	
	public final static String TABLE_NAME = "Tarea";
	
	private Integer id;
	
	private String descripcion;
	
	private Date fecha;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
