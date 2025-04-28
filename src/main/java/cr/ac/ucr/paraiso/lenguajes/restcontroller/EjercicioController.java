package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin(origins = "http://localhost:4200")
public class EjercicioController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // GET: Obtener todas las categorías disponibles
    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias() {
        return ejercicioBusiness.obtenerTodasCategorias();
    }

    // POST: Crear un nuevo ejercicio
    @PostMapping
    public Map<String, String> crearEjercicio(@RequestBody Ejercicio ejercicio) {
        ejercicioBusiness.crearEjercicio(ejercicio);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Ejercicio creado exitosamente");
        return response;
    }
}