package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaExtractor implements ResultSetExtractor<List<Categoria>> {

    @Override
    public List<Categoria> extractData(ResultSet rs) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setCodCategoria(rs.getInt("cod_categoria"));
            categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            categorias.add(categoria);
        }
        return categorias;
    }
}