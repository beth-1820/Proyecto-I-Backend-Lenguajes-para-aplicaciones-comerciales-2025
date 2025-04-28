package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    public void insertarCategoria(Categoria categoria) {
        String sql = "INSERT INTO CategoriaEjercicio (cod_categoria, nombre_categoria) VALUES (?, ?)";
        jdbcTemplate.update(sql, categoria.getCodCategoria(), categoria.getNombreCategoria());
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