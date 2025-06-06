package cr.ac.ucr.paraiso.lenguajes.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import cr.ac.ucr.paraiso.lenguajes.domain.Instructor;

@Repository
public class InstructorData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Instructor> findAll() {
        String sql = """
            SELECT idInstructor, nombreInstructor, apellidosInstructor,
                   telefonoInstructor
            FROM Instructor
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapInstructor(rs));
    }

    public Instructor findById(int id) {
        String sql = """
            SELECT idInstructor, nombreInstructor, apellidosInstructor,
                   telefonoInstructor
            FROM Instructor
            WHERE idInstructor = ?
        """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapInstructor(rs), id);
    }

    public List<Instructor> findByNombre(String nombre) {
        String sql = """
            SELECT idInstructor, nombreInstructor, apellidosInstructor,
                   telefonoInstructor
            FROM Instructor
            WHERE LOWER(nombreInstructor) LIKE LOWER(?)
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapInstructor(rs), "%" + nombre + "%");
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(1) FROM Instructor WHERE idInstructor = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public int update(Instructor i) {
        String sql = """
            UPDATE Instructor SET
              nombreInstructor        = ?,
              apellidosInstructor     = ?,
              telefonoInstructor      = ?
            WHERE idInstructor = ?
        """;
        return jdbcTemplate.update(sql,
            i.getNombreInstructor(),
            i.getApellidosInstructor(),
            i.getTelefonoInstructor(),
            i.getIdInstructor()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Instructor WHERE idInstructor = ?", id);
    }

    private Instructor mapInstructor(java.sql.ResultSet rs) throws java.sql.SQLException {
        Instructor i = new Instructor();
        i.setIdInstructor(rs.getInt("idInstructor"));
        i.setNombreInstructor(rs.getString("nombreInstructor"));
        i.setApellidosInstructor(rs.getString("apellidosInstructor"));
        i.setTelefonoInstructor(rs.getString("telefonoInstructor"));
        return i;
    }
}
