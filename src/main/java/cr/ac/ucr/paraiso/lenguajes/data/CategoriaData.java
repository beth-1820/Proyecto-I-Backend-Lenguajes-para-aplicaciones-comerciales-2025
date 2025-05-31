package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CategoriaData {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Categoria> obtenerTodasCategorias() {
        String sql = "SELECT * FROM CategoriaEjercicio"; 
        return jdbcTemplate.query(sql, new CategoriaExtractor());
    }

    public Categoria buscarCategoriaPorId(int codCategoria) {
        try {
            String sql = "SELECT * FROM CategoriaEjercicio WHERE cod_categoria = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{codCategoria}, categoriaMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public Categoria buscarCategoriaPorNombre(String nombreCategoria) {
        try {
            String sql = "SELECT * FROM CategoriaEjercicio WHERE nombre_categoria = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{nombreCategoria}, categoriaMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int obtenerUltimoCodCategoria() {
        String sql = "SELECT MAX(cod_categoria) FROM CategoriaEjercicio";
        Integer maxCod = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxCod != null ? maxCod : 0;
    }

    public void insertarCategoria(Categoria categoria) {
        //Obtiene el siguiente código disponible
        int siguienteCod = obtenerUltimoCodCategoria() + 1;
        categoria.setCodCategoria(siguienteCod);
        
        String sql = "INSERT INTO CategoriaEjercicio (nombre_categoria) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"cod_categoria"});
            ps.setString(1, categoria.getNombreCategoria());
            return ps;
        }, keyHolder);

        //Ahora recupera el valor generado por la base:
        Integer idGenerado = keyHolder.getKey().intValue();
        categoria.setCodCategoria(idGenerado);
    }

    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE CategoriaEjercicio SET nombre_categoria = ? WHERE cod_categoria = ?";
        jdbcTemplate.update(sql, categoria.getNombreCategoria(), categoria.getCodCategoria());
    }

    public void eliminarCategoria(int codCategoria) {
        String sql = "DELETE FROM CategoriaEjercicio WHERE cod_categoria = ?";
        jdbcTemplate.update(sql, codCategoria);
    }

    private final RowMapper<Categoria> categoriaMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria();
        categoria.setCodCategoria(rs.getInt("cod_categoria"));
        categoria.setNombreCategoria(rs.getString("nombre_categoria"));
        return categoria;
    };
}