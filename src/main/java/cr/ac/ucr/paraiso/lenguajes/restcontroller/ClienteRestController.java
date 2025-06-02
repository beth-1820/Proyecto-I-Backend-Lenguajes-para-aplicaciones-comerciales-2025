package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import cr.ac.ucr.paraiso.lenguajes.business.ClienteBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Cliente;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  
@RequestMapping(value= "/api/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteBusiness clienteBusiness;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteBusiness.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        return ResponseEntity.ok(clienteBusiness.obtenerClientePorId(id));
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> getByName(@RequestParam String nombre) {
        return ResponseEntity.ok(clienteBusiness.buscarPorNombre(nombre));
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cliente cliente) {
        clienteBusiness.crearCliente(cliente);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        clienteBusiness.actualizarCliente(cliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        clienteBusiness.eliminarCliente(id);
        return ResponseEntity.ok().build();
    }
}
