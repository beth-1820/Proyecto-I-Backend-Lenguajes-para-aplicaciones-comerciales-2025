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

    public Instructor obtenerInstructorPorId(int id, String nombre) { 
        if (id <= 0 || !instructorData.existsByIdOrNombre(id, nombre)) {
            throw new IllegalArgumentException("ID de instructor no válido o no existe");
        }
        return instructorData.findByIdYNombre(id, nombre); // este método debe retornar Instructor
    }

    


    public void actualizarInstructor(Instructor instructor) {
        if (instructor.getIdInstructor() <= 0 || !instructorData.existsByIdOrNombre(instructor.getIdInstructor(), instructor.getNombreInstructor())) {
            throw new IllegalArgumentException("No se puede actualizar: el ID de instructor no existe");
        }
        instructorData.update(instructor);
    }



    public void eliminarInstructor(int id, String nombre) {
        if (!instructorData.existsByIdOrNombre(id, nombre)) {
            throw new IllegalArgumentException("No se puede eliminar: el ID de instructor no existe");
        }
        instructorData.delete(id);
    }
}
