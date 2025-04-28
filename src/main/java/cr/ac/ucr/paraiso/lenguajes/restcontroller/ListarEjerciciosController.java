package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@Controller
public class ListarEjerciciosController { 
    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    @RequestMapping(value = "/listaEjercicios", method = RequestMethod.GET)
    public String listarEjercicios(Model model) {
    	model.addAttribute("ejercicios", ejercicioBusiness.obtenerTodosLosEjercicios());
        return "listaEjercicios";  // nombre del HTML que vas a crear
    }
    
    @RequestMapping(value = "/listaEjercicios/{id}", method = RequestMethod.GET)
    public String verEjercicio(@PathVariable("id") int id, Model model) {
        Ejercicio ejercicio = ejercicioBusiness.obtenerEjercicioPorId(id);
        Categoria categoria = ejercicioBusiness.obtenerCategoriaPorId(ejercicio.getCodCategoria());
        Equipamiento equipo = ejercicioBusiness.obtenerEquipoPorId(ejercicio.getCodEquipo());
        model.addAttribute("ejercicio", ejercicio);  //
        model.addAttribute("categoria", categoria);  // La categoría completa
        model.addAttribute("equipo", equipo);        // El equipo completo
        
        return "detalleEjercicio";  // nombre del HTML para el detalle 
    }
    
    @PostMapping("/ejercicio/{id}")
    public String eliminarEjercicio(@PathVariable("id") int id) {
        ejercicioBusiness.eliminarEjercicio(id);  // Llamada al método de negocio para eliminar el ejercicio
        return "redirect:/listaEjercicios";  // Redirigir de nuevo a la lista de ejercicios
    }
    
}
