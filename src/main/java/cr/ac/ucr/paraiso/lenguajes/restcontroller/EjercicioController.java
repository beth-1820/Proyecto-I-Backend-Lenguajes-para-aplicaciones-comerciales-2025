package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin(origins = "*") // ← permite conexión desde Angular
public class EjercicioController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // GET: Para obtener todas las categorías disponibles (si es necesario para un formulario Angular)
    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias() {
        return ejercicioBusiness.obtenerTodasCategorias();
    }

    // POST: Para crear un nuevo ejercicio
    @PostMapping
    public String crearEjercicio(@RequestBody Ejercicio ejercicio) {
        ejercicioBusiness.crearEjercicio(ejercicio);
        return "Ejercicio creado exitosamente";
    }
}
