package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.paraiso.lenguajes.data.RutinaData;
import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;
import cr.ac.ucr.paraiso.lenguajes.dto.RutinaDTO;

@Service
public class RutinaBusiness {

    @Autowired
    private RutinaData rutinaData;

    public List<Rutina> listarRutinas() {
        return rutinaData.findAll();
    }

    public Rutina obtenerRutinaPorId(int id) {
        if (id <= 0 || !rutinaData.existsById(id)) {
            throw new IllegalArgumentException("ID de rutina no válido o no existe");
        }
        return rutinaData.findById(id); 
    }

    public void crearRutina(RutinaDTO dto) {
        if (dto.getCliente() == null || dto.getInstructor() == null) {
            throw new IllegalArgumentException("Debe proporcionar Cliente e Instructor");
        }

        Rutina rutina = new Rutina();
        rutina.setFechaCreacion(dto.getFechaCreacion());
        rutina.setFechaRenovacion(dto.getFechaRenovacion());
        rutina.setObjetivoCliente(dto.getObjetivoCliente());
        rutina.setEnfermedadesCliente(dto.getEnfermedadesCliente());
        rutina.setCliente(dto.getCliente());
        rutina.setInstructor(dto.getInstructor());

        rutinaData.insertarRutina(rutina);
    }

    public void actualizarRutina(Rutina rutina) {
        if (rutina.getCodRutina() <= 0 || !rutinaData.existsById(rutina.getCodRutina())) {
            throw new IllegalArgumentException("No se puede actualizar: el ID de rutina no existe");
        }
        rutinaData.update(rutina);
    }

    public void eliminarRutina(int id) {
        if (!rutinaData.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: el ID de rutina no existe");
        }
        rutinaData.delete(id);
    }
}

