package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;

@Controller
public class editarEjercicioController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // Método para mostrar la página de edición del ejercicio
    @GetMapping("/ejercicio/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model) {
        // Obtener el ejercicio desde la base de datos
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);

        // Obtener las categorías y equipos disponibles
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("categorias", ejercicioBusiness.obtenerTodasCategorias());
        model.addAttribute("equipos", ejercicioBusiness.obtenerTodosEquipos());

        return "editarEjercicio";  // Página que creamos para editar el ejercicio
    }

    // Método para manejar la solicitud de actualización del ejercicio
    @PostMapping("/ejercicio/editar/{id}")
    public String actualizarEjercicio(@PathVariable("id") int id,
                                      @RequestParam("nombreEjercicio") String nombreEjercicio,
                                      @RequestParam("descripcion") String descripcion,
                                      @RequestParam("codCategoria") int codCategoria,
                                      @RequestParam("codEquipo") int codEquipo) {
        // Crear el objeto Ejercicio con los datos recibidos
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setCodEjercicio(id);
        ejercicio.setNombreEjercicio(nombreEjercicio);
        ejercicio.setDescripcion(descripcion);
        ejercicio.setCodCategoria(codCategoria);
        ejercicio.setCodEquipo(codEquipo);

        // Llamar al método de negocio para actualizar el ejercicio
        ejercicioBusiness.actualizarEjercicio(ejercicio);

        // Redirigir a la lista de ejercicios después de la actualización
        return "redirect:/listaEjercicios";
    }
}

