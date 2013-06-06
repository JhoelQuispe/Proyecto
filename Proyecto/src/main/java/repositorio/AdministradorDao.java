package repositorio;

import java.util.List;

import domain.Administrador;

public interface AdministradorDao extends DaoGenerico<Administrador, Integer> {
	
	Administrador encontrarXnombre(String nombre);

	List<Administrador> filtrarXnombre(String nombre);
	
}


