package cr.ac.ucr.paraiso.lenguajes.data;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testFindAll_noLanzaError() {
        List<Instructor> lista = instructorData.findAll();
        assertNotNull(lista);
    }

    @Test
    public void testFindById_existente() {
        int idExistente = 1; // Asegúrate que exista en tu base de datos
        Instructor instructor = instructorData.findById(idExistente);
        assertNotNull(instructor);
        assertEquals(idExistente, instructor.getIdInstructor());
    }

    @Test
    public void testFindByNombre_coincidencia() {
        String nombreParcial = "ana"; // Debe haber un instructor con este nombre parcial
        List<Instructor> resultados = instructorData.findByNombre(nombreParcial);
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertTrue(resultados.stream().anyMatch(i -> i.getNombreInstructor().toLowerCase().contains("ana")));
    }

    @Test
    public void testExistsById_trueSiExiste() {
        int idExistente = 1; // Asegúrate que este ID exista en la base
        assertTrue(instructorData.existsById(idExistente));
    }

    @Test
    public void testExistsById_falseSiNoExiste() {
        int idNoExistente = 9999;
        assertFalse(instructorData.existsById(idNoExistente));
    }
}
