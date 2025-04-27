package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.MedidaCorporal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MedidaCorporalData {

    @Autowired
    private JdbcTemplate jdbc;

    public List<MedidaCorporal> findAll() {
        String sql = "SELECT codMedida, nombreMedida, unidadMedida FROM MedidaCorporal ORDER BY codMedida";
        return jdbc.query(sql, (rs, row) -> map(rs));
    }

    public MedidaCorporal findById(int id) {
        String sql = "SELECT codMedida, nombreMedida, unidadMedida FROM MedidaCorporal WHERE codMedida = ?";
        return jdbc.queryForObject(sql, (rs, row) -> map(rs), id);
    }

    public int insert(MedidaCorporal m) {
        String sql = "INSERT INTO MedidaCorporal(nombreMedida, unidadMedida) VALUES (?,?)";
        return jdbc.update(sql, m.getNombreMedida(), m.getUnidadMedida());
    }

    public int update(MedidaCorporal m) {
        String sql = "UPDATE MedidaCorporal SET nombreMedida = ?, unidadMedida = ? WHERE codMedida = ?";
        return jdbc.update(sql, m.getNombreMedida(), m.getUnidadMedida(), m.getCodMedida());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM MedidaCorporal WHERE codMedida = ?", id);
    }

    private MedidaCorporal map(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new MedidaCorporal(
            rs.getInt("codMedida"),
            rs.getString("nombreMedida"),
            rs.getString("unidadMedida")
        );
    }
}