package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import cr.ac.ucr.paraiso.lenguajes.business.MedidaCorporalBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.MedidaCorporal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://vitalitycenter.vercel.app")
@RequestMapping("/api/medidas")
public class MedidaCorporalRestController {

    @Autowired
    private MedidaCorporalBusiness service;

    /** Listar catálogo completo de medidas corporales */
    @GetMapping
    public ResponseEntity<List<MedidaCorporal>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /** Obtener una medida por su código */
    @GetMapping("/{id}")
    public ResponseEntity<MedidaCorporal> obtener(@PathVariable int id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    /** Crear nueva medida corporal */
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody MedidaCorporal m) {
        service.crear(m);
        return ResponseEntity.ok().build();
    }

    /** Actualizar medida corporal */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable int id,
                                           @RequestBody MedidaCorporal m) {
        m.setCodMedida(id);
        service.actualizar(m);
        return ResponseEntity.ok().build();
    }

    /** Eliminar medida corporal */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
