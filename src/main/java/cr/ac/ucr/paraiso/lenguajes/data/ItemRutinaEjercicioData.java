package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.ItemRutinaEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemRutinaEjercicioData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ItemRutinaEjercicio> findByCodRutina(int codRutina) {
        String sql = """
            SELECT id_item_rutina_ejercicio, series_ejercicio, repeticiones_ejercicio, cod_ejercicio, cod_rutina
            FROM ItemRutinaEjercicio
            WHERE cod_rutina = ?
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapItem(rs), codRutina);
    }

    public int insert(ItemRutinaEjercicio item) {
        String sql = """
            INSERT INTO ItemRutinaEjercicio (series_ejercicio, repeticiones_ejercicio, cod_ejercicio, cod_rutina)
            VALUES (?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
            item.getSeriesEjercicio(),
            item.getRepeticionesEjercicio(),
            item.getCodEjercicio(),
            item.getCodRutina()
        );
    }
    
    public int update(ItemRutinaEjercicio item) {
        String sql = """
            UPDATE ItemRutinaEjercicio SET
                series_ejercicio = ?,
                repeticiones_ejercicio = ?,
                cod_ejercicio = ?,
                cod_rutina = ?
            WHERE id_item_rutina_ejercicio = ?
        """;
        return jdbcTemplate.update(sql,
            item.getSeriesEjercicio(),
            item.getRepeticionesEjercicio(),
            item.getCodEjercicio(),
            item.getCodRutina(),
            item.getIdItemRutinaEjercicio()
        );
    }


    public int delete(int id) {
        String sql = "DELETE FROM ItemRutinaEjercicio WHERE id_item_rutina_ejercicio = ?";
        return jdbcTemplate.update(sql, id);
    }

    private ItemRutinaEjercicio mapItem(ResultSet rs) throws SQLException {
        ItemRutinaEjercicio item = new ItemRutinaEjercicio();
        item.setIdItemRutinaEjercicio(rs.getInt("id_item_rutina_ejercicio"));
        item.setSeriesEjercicio(rs.getInt("series_ejercicio"));
        item.setRepeticionesEjercicio(rs.getInt("repeticiones_ejercicio"));
        item.setCodEjercicio(rs.getInt("cod_ejercicio"));
        item.setCodRutina(rs.getInt("cod_rutina"));
        return item;
    }
}
