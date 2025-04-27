package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import cr.ac.ucr.paraiso.lenguajes.business.ClienteMedidaValorBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.ClienteMedidaValor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clientes/{idCliente}/medidas")
public class ClienteMedidaValorRestController {

    @Autowired
    private ClienteMedidaValorBusiness service;

    /** Obtener los valores (12) de medidas corporales de un cliente */
    @GetMapping
    public ResponseEntity<List<ClienteMedidaValor>> listar(
            @PathVariable int idCliente) {
        return ResponseEntity.ok(service.listarPorCliente(idCliente));
    }

    /**
     * Guardar/actualizar el bloque completo de valores de un cliente.
     * El body lleva un arreglo de objetos { codMedida, valor }
     */
    @PostMapping
    public ResponseEntity<Void> guardar(
            @PathVariable int idCliente,
            @RequestBody List<ClienteMedidaValor> valores) {
        service.guardarValores(idCliente, valores);
        return ResponseEntity.ok().build();
    }
}
