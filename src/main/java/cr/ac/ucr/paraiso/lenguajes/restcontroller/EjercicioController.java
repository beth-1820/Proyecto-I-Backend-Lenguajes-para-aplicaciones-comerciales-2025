package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.business.EjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;

@Controller
public class EjercicioController {

    @Autowired
    private EjercicioBusiness ejercicioBusiness;

    // GET: para mostrar la página crearEjercicio.html
    @RequestMapping(value = "/ejercicio", method = RequestMethod.GET)
    public String iniciar(Model model) {
    	model.addAttribute("categorias", ejercicioBusiness.obtenerTodasCategorias());
        return "crearEjercicio";  
    }

    // POST: para recibir el JSON y guardar el ejercicio
    @ResponseBody
    @RequestMapping(value = "/ejercicio", method = RequestMethod.POST, consumes = "application/json")
    public String crearEjercicio(@RequestBody Ejercicio ejercicio) {
        ejercicioBusiness.crearEjercicio(ejercicio);
        return "Ejercicio creado exitosamente"; // solo respuesta de texto
    }
}
