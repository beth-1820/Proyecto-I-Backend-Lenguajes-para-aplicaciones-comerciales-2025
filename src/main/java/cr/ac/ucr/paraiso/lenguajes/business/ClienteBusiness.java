package cr.ac.ucr.paraiso.lenguajes.business;

import cr.ac.ucr.paraiso.lenguajes.data.ClienteData;
import cr.ac.ucr.paraiso.lenguajes.domain.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteBusiness {

    @Autowired
    private ClienteData clienteData;
    
    ////////////////////////////////
    ///El de buscar todos y buscar solo por id (en caso de que exista el id verda)

    public List<Cliente> listarClientes() { 
        return clienteData.findAll();
    }

    public Cliente obtenerClientePorId(int id) { 
        if (id <= 0 || !clienteData.existsById(id)) {
            throw new IllegalArgumentException("ID de cliente no válido o no existe");
        }
        return clienteData.findById(id);
    }
    
    public List<Cliente> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar un nombre válido");
        }
        return clienteData.findByNombre(nombre.trim());
    }

    
    /////////////////////////////////////
    
    ////////////////////
    ///El crear y actualizar,validan que no exista el ID antes de ser creado

    public void crearCliente(Cliente cliente) {
        if (cliente.getIdCliente() > 0 && clienteData.existsById(cliente.getIdCliente())) {
            throw new IllegalArgumentException("No se puede crear: el ID de cliente ya existe");
        }
        clienteData.insert(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        if (cliente.getIdCliente() <= 0 || !clienteData.existsById(cliente.getIdCliente())) {
            throw new IllegalArgumentException("No se puede actualizar: el ID de cliente no existe");
        }
        clienteData.update(cliente);
    }
    
    //////////////////

    public void eliminarCliente(int id) {
        if (!clienteData.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: el ID de cliente no existe");
        }
        clienteData.delete(id);
    }
}