package cr.ac.ucr.paraiso.lenguajes.business;

import cr.ac.ucr.paraiso.lenguajes.data.ClienteMedidaValorData;
import cr.ac.ucr.paraiso.lenguajes.domain.ClienteMedidaValor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMedidaValorBusiness {

    @Autowired
    private ClienteMedidaValorData data;

    public List<ClienteMedidaValor> listarPorCliente(int idCliente) {
        return data.findByCliente(idCliente);
    }

    public void guardarValores(int idCliente, List<ClienteMedidaValor> valores) {
        data.upsertValores(idCliente, valores);
    }
}