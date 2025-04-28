package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.paraiso.lenguajes.data.EjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@Service
public class EjercicioBusiness {

    @Autowired
    private EjercicioData ejercicioData;

    public void crearEjercicio(Ejercicio ejercicio) {
        ejercicioData.insertarEjercicio(ejercicio);
    }
    
    public List<Categoria> obtenerTodasCategorias() {
	    return ejercicioData.listarCategorias();
	}
    
    public List<Ejercicio> obtenerTodosLosEjercicios() {
	    return ejercicioData.listarEjercicios();
	}
    
    public Ejercicio obtenerEjercicioPorId(int id) {
	    return ejercicioData.obtenerEjercicioPorId(id);
	}
    
    public Categoria obtenerCategoriaPorId(int id) {
    	return ejercicioData.obtenerCategoriaPorId(id);
    }
    
    public Equipamiento obtenerEquipoPorId(int id) {
    	return ejercicioData.obtenerEquipoPorId(id);
    }
    
    public void eliminarEjercicio(int id) {
    	ejercicioData.eliminarEjercicio(id); 
    }
    
    public List<Equipamiento> obtenerTodosEquipos() { 
        return  ejercicioData.listarEquipos();  // Usamos findAll() para obtener todos los registros de equipos
    }
    
    public void actualizarEjercicio(Ejercicio ejercicio) {
    	ejercicioData.actualizarEjercicio(ejercicio); 
    }
    
}
