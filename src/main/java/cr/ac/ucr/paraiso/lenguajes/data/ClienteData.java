
package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.Cliente;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteData {
	


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cliente> findAll() { //Es el listar comun, encontrarlos a todos
        String sql = "SELECT id_cliente, nombre_cliente, apellidos_cliente, fecha_nacimiento, telefono_cliente, direccion, nombre_contacto_emergencia, tel_contacto_emergencia FROM Cliente";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapCliente(rs));
    }

    public Cliente findById(int id) {// Este lo agregue para la parte del modelo de buscar clientes, con este filtramos por id
        String sql = "SELECT id_cliente, nombre_cliente, apellidos_cliente, fecha_nacimiento, telefono_cliente, direccion, nombre_contacto_emergencia, tel_contacto_emergencia FROM Cliente WHERE id_cliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> mapCliente(rs));
    }

    public boolean existsById(int id) { //Este es para comprobar si existe, no para buscar. En caso de que exista al crear o actualizar el id, no permite que se cree (aun no probado)
        String sql = "SELECT COUNT(1) FROM Cliente WHERE id_cliente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public int insert(Cliente cliente) {//El create normal
        String sql = "INSERT INTO Cliente (nombre_cliente, apellidos_cliente, fecha_nacimiento, telefono_cliente, direccion, nombre_contacto_emergencia, tel_contacto_emergencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            cliente.getNombreCliente(),
            cliente.getApellidosCliente(),
            Date.valueOf(cliente.getFechaNacimiento()),
            cliente.getTelefonoCliente(),
            cliente.getDireccion(),
            cliente.getNombreContactoEmergencia(),
            cliente.getTelContactoEmergencia()
        );
    }

    public int update(Cliente cliente) {//El update normla
        String sql = "UPDATE Cliente SET nombre_cliente = ?, apellidos_cliente = ?, fecha_nacimiento = ?, telefono_cliente = ?, direccion = ?, nombre_contacto_emergencia = ?, tel_contacto_emergencia = ? WHERE id_cliente = ?";
        return jdbcTemplate.update(sql,
            cliente.getNombreCliente(),
            cliente.getApellidosCliente(),
            Date.valueOf(cliente.getFechaNacimiento()),
            cliente.getTelefonoCliente(),
            cliente.getDireccion(),
            cliente.getNombreContactoEmergencia(),
            cliente.getTelContactoEmergencia(),
            cliente.getIdCliente()
        );
    }

    public int delete(int id) {//El delete normal
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        return jdbcTemplate.update(sql, id);
    }

    
    private Cliente mapCliente(java.sql.ResultSet rs) throws java.sql.SQLException { //Este metodo lo puse porque a mi me sirvio el lab, maybe nos puede servir. Es para crear los objetos de tipo
    	//cliente por medio de una consulta SQL
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setNombreCliente(rs.getString("nombre_cliente"));
        c.setApellidosCliente(rs.getString("apellidos_cliente"));
        c.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        c.setTelefonoCliente(rs.getString("telefono_cliente"));
        c.setDireccion(rs.getString("direccion"));
        c.setNombreContactoEmergencia(rs.getString("nombre_contacto_emergencia"));
        c.setTelContactoEmergencia(rs.getString("tel_contacto_emergencia"));
        return c;
    }
}