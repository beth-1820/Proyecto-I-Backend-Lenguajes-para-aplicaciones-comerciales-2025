package cr.ac.ucr.paraiso.lenguajes.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;

@Repository
public class UsuarioData {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioData.class);
    private final JdbcTemplate jdbcTemplate;

    public UsuarioData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Usuario autenticar(int idUser, String password) {
        String sql = "EXEC autenticar_usuario ?, ?";
        try {
            logger.info("Ejecutando procedimiento almacenado para autenticar usuario: " + idUser);
            return jdbcTemplate.queryForObject(
                sql, 
                new Object[]{idUser, password}, 
                new UsuarioRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Usuario no encontrado o credenciales incorrectas para idUser: " + idUser);
            return null;
        } catch (Exception e) {
            logger.error("Error al autenticar al usuario con idUser: " + idUser, e);
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
            usuario.setRol(rs.getString("rol"));
            return usuario;
        }
    }
}