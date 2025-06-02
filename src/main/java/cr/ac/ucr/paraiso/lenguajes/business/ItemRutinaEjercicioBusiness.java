package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.paraiso.lenguajes.data.ItemRutinaEjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.ItemRutinaEjercicio;

@Service
public class ItemRutinaEjercicioBusiness {

    @Autowired
    private ItemRutinaEjercicioData itemData;

    public List<ItemRutinaEjercicio> listarPorRutina(int codRutina) {
        return itemData.findByCodRutina(codRutina);
    }

    public void agregarItem(ItemRutinaEjercicio item) {
        itemData.insert(item);
    }
    
    public void actualizarItem(ItemRutinaEjercicio item) {
        if (item.getIdItemRutinaEjercicio() <= 0) {
            throw new IllegalArgumentException("El ID del item a actualizar no es válido.");
        }
        itemData.update(item);
    }


    public void eliminarItem(int idItem) {
        itemData.delete(idItem);
    }
}
