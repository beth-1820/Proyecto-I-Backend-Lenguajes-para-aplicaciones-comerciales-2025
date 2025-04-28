package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cr.ac.ucr.paraiso.lenguajes.data.EjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;

public class CategoriaBusiness {
	@Autowired
	private EjercicioData ejercicioData;

	public List<Categoria> obtenerTodasCategorias() {
	    return ejercicioData.listarCategorias();
	}


}
