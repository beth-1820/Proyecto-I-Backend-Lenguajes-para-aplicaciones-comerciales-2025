package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@RestController
@RequestMapping("/api/ejercicios/listar")
@CrossOrigin(origins = "http://localhost:4200")
public class ListarEjerciciosController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // GET: Listar todos los ejercicios
    @GetMapping
    public List<Ejercicio> listarEjercicios() {
        return ejercicioBusiness.obtenerTodosLosEjercicios();
    }

    // GET: Ver detalle de un ejercicio
    @GetMapping("/{id}")
    public Ejercicio verEjercicio(@PathVariable("id") int id) {
        return ejercicioBusiness.obtenerEjercicioPorId(id);
    }

    // DELETE: Eliminar un ejercicio por id
    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable("id") int id) {
        ejercicioBusiness.eliminarEjercicio(id);
    }

    // GET: Obtener la categoría de un ejercicio
    @GetMapping("/{id}/categoria")
    public Categoria obtenerCategoriaDelEjercicio(@PathVariable("id") int id) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        return ejercicioBusiness.obtenerCategoriaPorId(ejercicio.getCodCategoria());
    }

    // GET: Obtener el equipamiento de un ejercicio
    @GetMapping("/{id}/equipamiento")
    public Equipamiento obtenerEquipoDelEjercicio(@PathVariable("id") int id) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        return ejercicioBusiness.obtenerEquipoPorId(ejercicio.getCodEquipo());
    }
}