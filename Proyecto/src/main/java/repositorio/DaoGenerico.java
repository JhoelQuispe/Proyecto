package repositorio;

import java.util.List;

import domain.EntidadBase;


public interface DaoGenerico<T extends EntidadBase<PK>, PK extends Number>
{
	public long contar();
	
	public void guardar(T entity);

	public boolean existe(PK id);
	
	public T encontrar(PK id);

	public List<T> encontrarTodos();

	public void borrar(T entity);

	public void borrarXid(PK id);

}
