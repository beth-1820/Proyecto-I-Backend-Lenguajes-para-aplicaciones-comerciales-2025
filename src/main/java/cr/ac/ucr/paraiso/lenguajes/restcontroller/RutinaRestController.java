package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.lenguajes.business.RutinaBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;
import cr.ac.ucr.paraiso.lenguajes.dto.RutinaDTO;

@RestController
@CrossOrigin(origins = "https://vitalitycenter.vercel.app")
@RequestMapping("/api/rutinas")
public class RutinaRestController {

    @Autowired
    private RutinaBusiness rutinaBusiness;

    @GetMapping
    public ResponseEntity<List<Rutina>> getAll() {
        return ResponseEntity.ok(rutinaBusiness.listarRutinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getById(@PathVariable int id) {
        return ResponseEntity.ok(rutinaBusiness.obtenerRutinaPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Integer> crearRutina(@RequestBody RutinaDTO dto) {
        int codRutina = rutinaBusiness.crearRutinaConItems(dto);
        return ResponseEntity.ok(codRutina);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Rutina rutina) {
        rutina.setCodRutina(id);
        rutinaBusiness.actualizarRutina(rutina);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rutinaBusiness.eliminarRutina(id);
        return ResponseEntity.ok().build();
    }
}
