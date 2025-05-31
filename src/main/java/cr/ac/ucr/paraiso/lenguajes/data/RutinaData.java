package cr.ac.ucr.paraiso.lenguajes.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RutinaData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClienteData clienteData;

    @Autowired
    private InstructorData instructorData;

    /* -------------------   CONSULTAS   ------------------- */
    public int insertarRutina(Rutina rutina) {
        String sql = """
            INSERT INTO Rutina (fechaCreacion, fechaRenovacion, objetivoCliente, enfermedadesCliente, idCliente, idInstructor)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                Date.valueOf(rutina.getFechaCreacion()),
                Date.valueOf(rutina.getFechaRenovacion()),
                rutina.getObjetivoCliente(),
                rutina.getEnfermedadesCliente(),
                rutina.getCliente().getIdCliente(),
                rutina.getInstructor().getIdInstructor()
        );
    }

    public List<Rutina> findAll() {
        String sql = """
            SELECT codRutina, fechaCreacion, fechaRenovacion,
                   objetivoCliente, enfermedadesCliente,
                   idCliente, idInstructor
            FROM Rutina
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRutina(rs));
    }

    public Rutina findById(int codRutina) {
        String sql = """
            SELECT codRutina, fechaCreacion, fechaRenovacion,
                   objetivoCliente, enfermedadesCliente,
                   idCliente, idInstructor
            FROM Rutina
            WHERE codRutina = ?
            """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRutina(rs), codRutina);
    }

    public boolean existsById(int codRutina) {
        String sql = "SELECT COUNT(1) FROM Rutina WHERE codRutina = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, codRutina);
        return count != null && count > 0;
    }

    /* -------------------   CRUD   ------------------- */

    public int insert(Rutina r) {
        String sql = """
            INSERT INTO Rutina
              (fechaCreacion, fechaRenovacion, objetivoCliente,
               enfermedadesCliente, idCliente, idInstructor)
            VALUES (?,?,?,?,?,?)
            """;
        return jdbcTemplate.update(sql,
            Date.valueOf(r.getFechaCreacion()),
            Date.valueOf(r.getFechaRenovacion()),
            r.getObjetivoCliente(),
            r.getEnfermedadesCliente(),
            r.getCliente().getIdCliente(),
            r.getInstructor().getIdInstructor()
        );
    }

    public int update(Rutina r) {
        String sql = """
            UPDATE Rutina SET
              fechaCreacion        = ?,
              fechaRenovacion      = ?,
              objetivoCliente      = ?,
              enfermedadesCliente  = ?,
              idCliente            = ?,
              idInstructor         = ?
            WHERE codRutina = ?
            """;
        return jdbcTemplate.update(sql,
            Date.valueOf(r.getFechaCreacion()),
            Date.valueOf(r.getFechaRenovacion()),
            r.getObjetivoCliente(),
            r.getEnfermedadesCliente(),
            r.getCliente().getIdCliente(),
            r.getInstructor().getIdInstructor(),
            r.getCodRutina()
        );
    }

    public int delete(int codRutina) {
        return jdbcTemplate.update("DELETE FROM Rutina WHERE codRutina = ?", codRutina);
    }

    /* -------------------   MAPPING   ------------------- */

    private Rutina mapRutina(ResultSet rs) throws SQLException {
        Rutina r = new Rutina();
        r.setCodRutina(rs.getInt("codRutina"));
        r.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
        r.setFechaRenovacion(rs.getDate("fechaRenovacion").toLocalDate());
        r.setObjetivoCliente(rs.getString("objetivoCliente"));
        r.setEnfermedadesCliente(rs.getString("enfermedadesCliente"));

        int idCliente = rs.getInt("idCliente");
        int idInstructor = rs.getInt("idInstructor");
        String nombreInstructor = rs.getString("nombreInstructor");

        // Busca los objetos relacionados
        r.setCliente(clienteData.findById(idCliente));
        r.setInstructor(instructorData.findByIdYNombre(idInstructor, nombreInstructor));

        return r;
    }
}
