package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.business.ItemRutinaEjercicioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.ItemRutinaEjercicio;

@RestController
@CrossOrigin(origins = "https://vitalitycenter.vercel.app")
@RequestMapping("/api/item-rutina-ejercicio")
public class ItemRutinaEjercicioRestController {

    @Autowired
    private ItemRutinaEjercicioBusiness itemBusiness;

    @GetMapping("/rutina/{codRutina}")
    public ResponseEntity<List<ItemRutinaEjercicio>> listarPorRutina(@PathVariable int codRutina) {
        return ResponseEntity.ok(itemBusiness.listarPorRutina(codRutina));
    }

    @PostMapping
    public ResponseEntity<Void> agregar(@RequestBody ItemRutinaEjercicio item) {
        itemBusiness.agregarItem(item);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable int id, @RequestBody ItemRutinaEjercicio item) {
        item.setIdItemRutinaEjercicio(id);
        itemBusiness.actualizarItem(item);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        itemBusiness.eliminarItem(id);
        return ResponseEntity.ok().build();
    }
}
