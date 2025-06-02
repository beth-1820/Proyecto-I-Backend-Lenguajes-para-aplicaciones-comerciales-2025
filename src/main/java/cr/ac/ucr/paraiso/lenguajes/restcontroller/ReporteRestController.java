package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.business.RutinaBusiness;
import cr.ac.ucr.paraiso.lenguajes.dto.ReporteRutinaDTO;

@RestController
@CrossOrigin(origins = "https://vitalitycenterproy.vercel.app")
@RequestMapping("/api/reportes")
public class ReporteRestController {

    @Autowired
    private RutinaBusiness rutinaBusiness;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ReporteRutinaDTO> getReportePorCliente(@PathVariable int idCliente) {
        ReporteRutinaDTO reporte = rutinaBusiness.obtenerReportePorCliente(idCliente);
        return ResponseEntity.ok(reporte);
    }
}
