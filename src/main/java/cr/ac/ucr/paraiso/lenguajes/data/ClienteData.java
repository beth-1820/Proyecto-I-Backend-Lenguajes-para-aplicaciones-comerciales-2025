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

    /* -------------------   CONSULTAS   ------------------- */

    public List<Cliente> findAll() {
        String sql = """
            SELECT idCliente, nombreCliente, apellidosCliente,
                   fechaNacimiento, telefonoCliente, direccion,
                   nombreContactoEmergencia, telContactoEmergencia
            FROM   Cliente
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapCliente(rs));
    }

    public Cliente findById(int id) {
        String sql = """
            SELECT idCliente, nombreCliente, apellidosCliente,
                   fechaNacimiento, telefonoCliente, direccion,
                   nombreContactoEmergencia, telContactoEmergencia
            FROM   Cliente
            WHERE  idCliente = ?
            """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapCliente(rs), id);
    }
    
    public List<Cliente> findByNombre(String nombre) {
        String sql = """
            SELECT idCliente, nombreCliente, apellidosCliente,
                   fechaNacimiento, telefonoCliente, direccion,
                   nombreContactoEmergencia, telContactoEmergencia
            FROM Cliente
            WHERE LOWER(nombreCliente) LIKE LOWER(?)
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapCliente(rs), "%" + nombre + "%");
    }


    public boolean existsById(int id) {
        String sql = "SELECT COUNT(1) FROM Cliente WHERE idCliente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    /* -------------------   CRUD   ------------------- */

    public int insert(Cliente c) {
        String sql = """
            INSERT INTO Cliente
              (nombreCliente, apellidosCliente, fechaNacimiento,
               telefonoCliente, direccion,
               nombreContactoEmergencia, telContactoEmergencia)
            VALUES (?,?,?,?,?,?,?)
            """;
        return jdbcTemplate.update(sql,
            c.getNombreCliente(),
            c.getApellidosCliente(),
            Date.valueOf(c.getFechaNacimiento()),
            c.getTelefonoCliente(),
            c.getDireccion(),
            c.getNombreContactoEmergencia(),
            c.getTelContactoEmergencia()
        );
    }

    public int update(Cliente c) {
        String sql = """
            UPDATE Cliente SET
              nombreCliente             = ?,
              apellidosCliente          = ?,
              fechaNacimiento           = ?,
              telefonoCliente           = ?,
              direccion                 = ?,
              nombreContactoEmergencia  = ?,
              telContactoEmergencia     = ?
            WHERE idCliente = ?
            """;
        return jdbcTemplate.update(sql,
            c.getNombreCliente(),
            c.getApellidosCliente(),
            Date.valueOf(c.getFechaNacimiento()),
            c.getTelefonoCliente(),
            c.getDireccion(),
            c.getNombreContactoEmergencia(),
            c.getTelContactoEmergencia(),
            c.getIdCliente()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Cliente WHERE idCliente = ?", id);
    }

    /* -------------------   MAPPING   ------------------- */

    private Cliente mapCliente(java.sql.ResultSet rs) throws java.sql.SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("idCliente"));
        c.setNombreCliente(rs.getString("nombreCliente"));
        c.setApellidosCliente(rs.getString("apellidosCliente"));
        c.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        c.setTelefonoCliente(rs.getString("telefonoCliente"));
        c.setDireccion(rs.getString("direccion"));
        c.setNombreContactoEmergencia(rs.getString("nombreContactoEmergencia"));
        c.setTelContactoEmergencia(rs.getString("telContactoEmergencia"));
        return c;
    }
}
