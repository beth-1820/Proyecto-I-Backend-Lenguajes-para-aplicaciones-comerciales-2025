package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.lenguajes.data.ItemRutinaEjercicioData;
import cr.ac.ucr.paraiso.lenguajes.data.RutinaData;
import cr.ac.ucr.paraiso.lenguajes.domain.ItemRutinaEjercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;
import cr.ac.ucr.paraiso.lenguajes.dto.ItemRutinaEjercicioDTO;
import cr.ac.ucr.paraiso.lenguajes.dto.RutinaDTO;

@Service
public class RutinaBusiness {

    @Autowired
    private RutinaData rutinaData;
    
    @Autowired
    private ItemRutinaEjercicioData itemRutinaEjercicioData;

    public List<Rutina> listarRutinas() {
        return rutinaData.findAll();
    }

    public Rutina obtenerRutinaPorId(int id) {
        if (id <= 0 || !rutinaData.existsById(id)) {
            throw new IllegalArgumentException("ID de rutina no válido o no existe");
        }
        return rutinaData.findById(id); 
    }

    @Transactional
    public int crearRutinaConItems(RutinaDTO dto) {
        // Crear la entidad Rutina
        Rutina rutina = new Rutina();
        rutina.setFechaCreacion(dto.getFechaCreacion());
        rutina.setFechaRenovacion(dto.getFechaRenovacion());
        rutina.setObjetivoCliente(dto.getObjetivoCliente());
        rutina.setEnfermedadesCliente(dto.getEnfermedadesCliente());
        rutina.setCliente(dto.getCliente());
        rutina.setInstructor(dto.getInstructor());

        // Insertar rutina y obtener el ID
        int codRutina = rutinaData.insertarRutina(rutina);

        // Insertar los ejercicios asociados
        if (dto.getEjercicios() != null) {
            for (ItemRutinaEjercicioDTO itemDTO : dto.getEjercicios()) {
                ItemRutinaEjercicio item = new ItemRutinaEjercicio();
                item.setSeriesEjercicio(itemDTO.getSeriesEjercicio());
                item.setRepeticionesEjercicio(itemDTO.getRepeticionesEjercicio());
                item.setCodEjercicio(itemDTO.getCodEjercicio());
                item.setCodRutina(codRutina);

                itemRutinaEjercicioData.insert(item);
            }
        }

        return codRutina;
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

