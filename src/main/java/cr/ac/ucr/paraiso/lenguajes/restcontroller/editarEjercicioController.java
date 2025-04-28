package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin(origins = "*")
public class editarEjercicioController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // GET: Obtener un ejercicio específico (para precargar el formulario)
    @GetMapping("/{id}")
    public Ejercicio obtenerEjercicio(@PathVariable("id") int id) {
        return ejercicioBusiness.obtenerEjercicioPorId(id);
    }

    // PUT: Actualizar un ejercicio
    @PutMapping("/{id}")
    public String actualizarEjercicio(@PathVariable("id") int id, @RequestBody Ejercicio ejercicio) {
        ejercicio.setCodEjercicio(id); // Asegura que el ID es el correcto
        ejercicioBusiness.actualizarEjercicio(ejercicio);
        return "Ejercicio actualizado exitosamente";
    }

    // Opcionales: Para obtener las listas de categorías y equipos
    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias() {
        return ejercicioBusiness.obtenerTodasCategorias();
    }

    @GetMapping("/equipos")
    public List<Equipamiento> obtenerEquipos() {
        return ejercicioBusiness.obtenerTodosEquipos();
    }
}
