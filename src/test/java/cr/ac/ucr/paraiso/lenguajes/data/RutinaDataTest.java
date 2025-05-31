package cr.ac.ucr.paraiso.lenguajes.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import cr.ac.ucr.paraiso.lenguajes.domain.Cliente;
import cr.ac.ucr.paraiso.lenguajes.domain.Instructor;
import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;

@SpringBootTest
public class RutinaDataTest {

    @Autowired
    private RutinaData rutinaData;

    @Autowired
    private ClienteData clienteData;

    @Autowired
    private InstructorData instructorData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
 
        jdbcTemplate.update("DELETE FROM Rutina");
        jdbcTemplate.update("DELETE FROM Instructor");
        jdbcTemplate.update("DELETE FROM Cliente");
        jdbcTemplate.update("DELETE FROM Usuario");

     
        jdbcTemplate.update(
            "INSERT INTO Usuario (idUser, email, password, estado, rol) VALUES (?, ?, ?, ?, ?)",
            1, "instructor@example.com", "password123", 0, "INSTRUCTOR"
        );


        jdbcTemplate.update(
            "INSERT INTO Cliente (nombreCliente, apellidosCliente, fechaNacimiento, telefonoCliente, direccion, nombreContactoEmergencia, telContactoEmergencia) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)",
            "Juan", "Pérez", LocalDate.of(1990, 5, 15), "88881234", "Calle 123, Ciudad", "Maria Pérez", "88885678"
        );

      
        jdbcTemplate.update(
            "INSERT INTO Instructor (nombreInstructor, apellidosInstructor, telefonoInstructor, idUser) VALUES (?, ?, ?, ?)",
            "Carlos", "Gómez", "88881234", 1
        );
    }


    @Test
    public void testCrearRutina() {
        Cliente cliente = clienteData.findAll().get(0); 

        Instructor instructor = instructorData.findAll().get(0); 

        Rutina rutina = new Rutina();
        rutina.setFechaCreacion(LocalDate.now());
        rutina.setFechaRenovacion(LocalDate.now().plusMonths(1));
        rutina.setObjetivoCliente("Bajar de peso");
        rutina.setEnfermedadesCliente("Ninguna");
        rutina.setCliente(cliente);
        rutina.setInstructor(instructor);

        int filasAfectadas = rutinaData.insertarRutina(rutina);
        assertEquals(1, filasAfectadas, "La rutina debería haberse insertado correctamente");
    }

   
    @Test
    public void testEditarRutina() {
        testCrearRutina();

        Integer codRutina = jdbcTemplate.queryForObject("SELECT TOP 1 codRutina FROM Rutina", Integer.class);

        Cliente cliente = clienteData.findAll().get(0);
        Instructor instructor = instructorData.findAll().get(0);

        Rutina rutina = new Rutina();
        rutina.setCodRutina(codRutina);
        rutina.setFechaCreacion(LocalDate.now());
        rutina.setFechaRenovacion(LocalDate.now().plusMonths(2));
        rutina.setObjetivoCliente("Ganar masa muscular");
        rutina.setCliente(cliente);
        rutina.setInstructor(instructor);

        int filasAfectadas = rutinaData.update(rutina);
        assertEquals(1, filasAfectadas);
    }

    @Test
    public void testEliminarRutina() {
        testCrearRutina();

        Integer codRutina = jdbcTemplate.queryForObject("SELECT TOP 1 codRutina FROM Rutina", Integer.class);

        int filasAfectadas = rutinaData.delete(codRutina);
        assertEquals(1, filasAfectadas);
    }

    
}
