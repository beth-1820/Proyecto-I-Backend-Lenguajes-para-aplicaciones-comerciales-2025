package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@RestController
@RequestMapping("/api/ejercicios/editar")
@CrossOrigin(origins = "https://vitalitycenter.vercel.app")
public class editarEjercicioController {  // Nombre corregido en mayúscula

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
        ejercicio.setCodEjercicio(id);
        ejercicioBusiness.actualizarEjercicio(ejercicio);
        return "Ejercicio actualizado exitosamente";
    }

    // GET: Obtener todas las categorías
    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias() {
        return ejercicioBusiness.obtenerTodasCategorias();
    }

    // GET: Obtener todos los equipos
    @GetMapping("/equipos")
    public List<Equipamiento> obtenerEquipos() {
        return ejercicioBusiness.obtenerTodosEquipos();
    }
}