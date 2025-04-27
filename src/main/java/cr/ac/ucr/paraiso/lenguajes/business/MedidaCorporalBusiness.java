package cr.ac.ucr.paraiso.lenguajes.business;

import cr.ac.ucr.paraiso.lenguajes.data.MedidaCorporalData;
import cr.ac.ucr.paraiso.lenguajes.domain.MedidaCorporal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedidaCorporalBusiness {

    @Autowired
    private MedidaCorporalData data;

    public List<MedidaCorporal> listar() { return data.findAll(); }
    public MedidaCorporal obtener(int id) { return data.findById(id); }
    public void crear(MedidaCorporal m) { data.insert(m); }
    public void actualizar(MedidaCorporal m) { data.update(m); }
    public void eliminar(int id) { data.delete(id); }
}