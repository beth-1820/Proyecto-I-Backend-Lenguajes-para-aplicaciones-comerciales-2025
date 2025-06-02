package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cr.ac.ucr.paraiso.lenguajes.data.InstructorData;
import cr.ac.ucr.paraiso.lenguajes.domain.Instructor;

@Service
public class InstructorBusiness {

    @Autowired
    private InstructorData instructorData;

    public List<Instructor> listarInstructores() {
        return instructorData.findAll();
    }

    public Instructor obtenerInstructorPorId(int id) {
        if (id <= 0 || !instructorData.existsById(id)) {
            throw new IllegalArgumentException("ID de instructor no válido o no existe");
        }
        return instructorData.findById(id);
    }

    public List<Instructor> buscarPorNombre(String nombre) {
        return instructorData.findByNombre(nombre);
    }

    public void actualizarInstructor(Instructor instructor) {
        if (instructor.getIdInstructor() <= 0 || !instructorData.existsById(instructor.getIdInstructor())) {
            throw new IllegalArgumentException("No se puede actualizar: el ID de instructor no existe");
        }
        instructorData.update(instructor);
    }

    public void eliminarInstructor(int id) {
        if (!instructorData.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: el ID de instructor no existe");
        }
        instructorData.delete(id);
    }
}
