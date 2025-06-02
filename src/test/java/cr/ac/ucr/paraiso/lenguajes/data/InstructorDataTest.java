package cr.ac.ucr.paraiso.lenguajes.data;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.paraiso.lenguajes.domain.Instructor;

@SpringBootTest
public class InstructorDataTest {

    @Autowired
    private InstructorData instructorData;

    @Test
    void testFindAll() {
        List<Instructor> instructors = instructorData.findAll();
        assertNotNull(instructors);
        assertFalse(instructors.isEmpty(), "Debe retornar al menos un instructor");
    }


    @Test
    void testExistsByIdOrNombre() {
        assertTrue(instructorData.existsByIdOrNombre(5, "Carlos"));
        assertFalse(instructorData.existsByIdOrNombre(-1, "NombreInexistente"));
    }
/*
    @Test
    void testUpdateAndDelete() {
        Instructor inst = instructorData.findByIdYNombre(9, "Karla                                             ");
        inst.setTelefonoInstructor("99999999");
        int updated = instructorData.update(inst);
        assertEquals(1, updated);

        int deleted = instructorData.delete(inst.getIdInstructor());
        assertEquals(1, deleted);

        boolean exists = instructorData.existsByIdOrNombre(inst.getIdInstructor(), inst.getNombreInstructor());
        assertFalse(exists);
    }*/
}