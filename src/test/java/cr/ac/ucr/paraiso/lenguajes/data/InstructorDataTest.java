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
        assertNotNull(instructors, "La lista de instructores no debe ser null");
        assertFalse(instructors.isEmpty(), "Debe retornar al menos un instructor");
    }


    @Test
    void testFindByNombre() {
        // Suponiendo que hay un instructor con nombre "Carlos"
        List<Instructor> resultados = instructorData.findByNombre("Carlos");
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty(), "Debe encontrar al menos un instructor llamado Carlos");

        // Buscar nombre que no existe
        List<Instructor> vacio = instructorData.findByNombre("NombreInexistente");
        assertNotNull(vacio);
        assertTrue(vacio.isEmpty(), "No debe encontrar instructores con nombre inexistente");
    }

    @Test
    void testExistsById() {
        assertTrue(instructorData.existsById(35), "Debe existir instructor con ID 35");
        assertFalse(instructorData.existsById(-1), "No debe existir instructor con ID -1");
    }
/*
    @Test
    void testUpdateAndDelete() {
        // Buscar uno existente y modificarlo
        Instructor instructor = instructorData.findById(1); // Usa un ID que esté seguro que existe
        String telefonoAnterior = instructor.getTelefonoInstructor();
        instructor.setTelefonoInstructor("88888888");

        int updateResult = instructorData.update(instructor);
        assertEquals(1, updateResult, "Debe haber actualizado un registro");

        Instructor actualizado = instructorData.findById(instructor.getIdInstructor());
        assertEquals("88888888", actualizado.getTelefonoInstructor(), "Teléfono debe haber sido actualizado");

        // Restaurar estado original si se desea
        instructor.setTelefonoInstructor(telefonoAnterior);
        instructorData.update(instructor);
    }

    @Test
    void testDelete() {
        // Insertar un instructor dummy (esto depende de si tienes un método para insertar, si no lo tienes, deberás insertar manualmente en la BD o agregar el método)

        // Supongamos que hay un instructor de prueba con ID 999 (insertado manualmente antes del test)
        int deleted = instructorData.delete(999);
        assertEquals(1, deleted, "Debe eliminar un instructor con ID 999");

        assertFalse(instructorData.existsById(999), "Instructor con ID 999 ya no debe existir");
    }*/
}
