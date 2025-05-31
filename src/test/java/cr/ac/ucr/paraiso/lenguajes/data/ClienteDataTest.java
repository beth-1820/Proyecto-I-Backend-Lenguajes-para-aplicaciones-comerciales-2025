package cr.ac.ucr.paraiso.lenguajes.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.paraiso.lenguajes.domain.Cliente;

@SpringBootTest
class ClienteDataTest {

    @Autowired
    private ClienteData clienteData;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNombreCliente("Laura");
        cliente.setApellidosCliente("Mendoza");
        cliente.setFechaNacimiento(LocalDate.of(1995, 5, 20));
        cliente.setTelefonoCliente("88887777");
        cliente.setDireccion("San José, Costa Rica");
        cliente.setNombreContactoEmergencia("Carlos Pérez");
        cliente.setTelContactoEmergencia("89998888");
    }

    @Test
    void testFindAll() {
        List<Cliente> clientes = clienteData.findAll();
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty(), "Debe retornar al menos un cliente");
    }

    @Test
    void testFindById() {
       
        clienteData.insert(cliente);

       
        Cliente clienteInsertado = clienteData.findAll().stream()
            .filter(c -> c.getTelefonoCliente().equals("88887777"))
            .findFirst()
            .orElse(null);

        assertNotNull(clienteInsertado, "Debe existir el cliente insertado");

        // Ahora usamos findById
        Cliente encontrado = clienteData.findById(clienteInsertado.getIdCliente());
        assertNotNull(encontrado);
        assertEquals(clienteInsertado.getIdCliente(), encontrado.getIdCliente());
    }

    @Test
    void testExistsById() {
        clienteData.insert(cliente);
        Cliente clienteInsertado = clienteData.findAll().stream()
            .filter(c -> c.getTelefonoCliente().equals("88887777"))
            .findFirst()
            .orElseThrow();

        assertTrue(clienteData.existsById(clienteInsertado.getIdCliente()));
        assertFalse(clienteData.existsById(-1));
    }

    @Test
    void testUpdateAndDelete() {
        clienteData.insert(cliente);
        Cliente clienteInsertado = clienteData.findAll().stream()
            .filter(c -> c.getTelefonoCliente().equals("88887777"))
            .findFirst()
            .orElseThrow();

       
        clienteInsertado.setNombreCliente("Laura Editada");
        clienteInsertado.setTelefonoCliente("88880000");

        int updated = clienteData.update(clienteInsertado);
        assertEquals(1, updated, "Debe actualizarse una fila");

        Cliente clienteActualizado = clienteData.findById(clienteInsertado.getIdCliente());
        assertEquals("Laura Editada", clienteActualizado.getNombreCliente().trim());
        assertEquals("88880000", clienteActualizado.getTelefonoCliente());

      
        int deleted = clienteData.delete(clienteInsertado.getIdCliente());
        assertEquals(1, deleted, "Debe eliminarse una fila");

        boolean exists = clienteData.existsById(clienteInsertado.getIdCliente());
        assertFalse(exists, "El cliente debe haber sido eliminado");
    }
}
