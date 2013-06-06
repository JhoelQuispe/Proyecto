package repositorio;

import java.util.List;
import java.util.Date;
import domain.Tarea;

public interface TareaDao extends DaoGenerico <Tarea, Integer> {
	
	Tarea encontrarXfecha (Date fecha);
	
	List<Tarea> filtrarXfecha (Date fecha);
	
}
