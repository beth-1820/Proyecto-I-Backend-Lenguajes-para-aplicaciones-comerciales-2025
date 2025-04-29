package cr.ac.ucr.paraiso.lenguajes.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;

@Repository
public class UsuarioData {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Usuario autenticar(int idUser, String password) {
        String sql = "SELECT idUser, email, password, estado, rol FROM Usuario WHERE idUser = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idUser, password}, new UsuarioRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setIdUser(rs.getInt("idUser"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPassword(rs.getString("password"));
            usuario.setEstado(rs.getInt("estado"));
            // Asumiendo que modificaremos la clase Usuario para tener rol como String
            usuario.setRol(rs.getString("rol"));
            return usuario;
        }
    }
}