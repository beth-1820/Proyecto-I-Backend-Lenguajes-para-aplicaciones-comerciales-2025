package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@RestController
@RequestMapping("/api/ejercicios")  // Prefijo común para todos los métodos
@CrossOrigin(origins = "*") // ← permite que Angular se conecte desde otro puerto
public class ListarEjerciciosController { 

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // Obtener todos los ejercicios
    @GetMapping
    public List<Ejercicio> listarEjercicios() {
        return ejercicioBusiness.obtenerTodosLosEjercicios();
    }
    
    // Obtener un ejercicio por id
    @GetMapping("/{id}")
    public Ejercicio verEjercicio(@PathVariable("id") int id) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        // Puedes traer más datos si quieres (como categoría y equipo), o crear un DTO si necesitas.
        return ejercicio;
    }

    // Eliminar un ejercicio por id
    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable("id") int id) {
        ejercicioBusiness.eliminarEjercicio(id);
    }
    
    // Extra: si quieres devolver categoría o equipo de un ejercicio
    @GetMapping("/{id}/categoria")
    public Categoria obtenerCategoriaDelEjercicio(@PathVariable("id") int id) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        return ejercicioBusiness.obtenerCategoriaPorId(ejercicio.getCodCategoria());
    }

    @GetMapping("/{id}/equipo")
    public Equipamiento obtenerEquipoDelEjercicio(@PathVariable("id") int id) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        return ejercicioBusiness.obtenerEquipoPorId(ejercicio.getCodEquipo());
    }
}
