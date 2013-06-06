package domain;

import repositorio.TareaDao;
import java.util.Date;
import repositorio.jdbc.DaoTareaJdbc;


public class Administrador implements EntidadBase<Integer>  {
	
	public final static String TABLE_NAME = "Administrador";
	
	private Integer id;
	
	private String nombre;
	
	private String password;
	
	
	public Administrador (){
		
	}
	
	public String toString() {
		return "{id: " +  getId()+ ", Nombre: " + nombre + "}";
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id=id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@SuppressWarnings("null")
	public void revisarTareas (Date fecha){
		TareaDao tarea = null;
		tarea.filtrarXfecha(fecha);
	}
	
	////////////////////////////////////////////////////////////////////////
	
	/*public void crearCategoria (Categoria categoria){
		
	}
	
	public void registrarTarea (Tarea tarea){

	}
	
	public void subirContenido (Contenido contenido){
		
	}
	
	public void cargarSaldo (Integer cantidad, Usuario usuario){
		
	}
	
	public void crearPromocion (Promocion promocion){
		
	}
	
	public void borrarContenido (Contenido contenido){
		
	}*/
	

}
