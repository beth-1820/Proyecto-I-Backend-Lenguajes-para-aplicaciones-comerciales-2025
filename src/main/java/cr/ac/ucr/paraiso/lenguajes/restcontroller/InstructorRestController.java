package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.business.InstructorBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Instructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/instructores")
public class InstructorRestController {

    @Autowired
    private InstructorBusiness instructorBusiness;

    @GetMapping
    public ResponseEntity<List<Instructor>> getAll() {
        return ResponseEntity.ok(instructorBusiness.listarInstructores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getById(@PathVariable int id) {
        return ResponseEntity.ok(instructorBusiness.obtenerInstructorPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Instructor>> getByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(instructorBusiness.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Instructor instructor) {
        instructor.setIdInstructor(id);
        instructorBusiness.actualizarInstructor(instructor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        instructorBusiness.eliminarInstructor(id);
        return ResponseEntity.ok().build();
    }
}
