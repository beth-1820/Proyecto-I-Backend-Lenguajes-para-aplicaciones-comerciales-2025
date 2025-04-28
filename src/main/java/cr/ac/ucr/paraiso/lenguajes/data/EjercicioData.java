package cr.ac.ucr.paraiso.lenguajes.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@Repository
public class EjercicioData {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //listar generos para combobox
    public List<Categoria> listarCategorias() {
        String sql = "SELECT cod_categoria, nombre_categoria FROM CategoriaEjercicio";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria();
            categoria.setCodCategoria(rs.getInt("cod_categoria"));
            categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            return categoria;
        });
    }

 
    // insertar ejercicios 
    public void insertarEjercicio(Ejercicio ejercicio) {
        String sql = "INSERT INTO ejercicio (nombre_ejercicio, descripcion, cod_categoria, cod_equipo) " +
                     "VALUES ( ?, ?, ?, ?)";

        jdbcTemplate.update(sql, 
            ejercicio.getNombreEjercicio(), 
            ejercicio.getDescripcion(), 
            ejercicio.getCodCategoria(), 
            ejercicio.getCodEquipo()
        );
    }
    
    public List<Ejercicio> listarEjercicios() {
    	String sql = "SELECT cod_ejercicio, nombre_ejercicio, descripcion, cod_categoria, cod_equipo FROM Ejercicio";

        return jdbcTemplate.query(sql, new RowMapper<Ejercicio>() {
            @Override
            public Ejercicio mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ejercicio ejercicio = new Ejercicio();
                ejercicio.setCodEjercicio(rs.getInt("cod_ejercicio"));
                ejercicio.setNombreEjercicio(rs.getString("nombre_ejercicio"));
                ejercicio.setDescripcion(rs.getString("descripcion"));
                ejercicio.setCodCategoria(rs.getInt("cod_categoria"));
                ejercicio.setCodEquipo(rs.getInt("cod_equipo"));
                return ejercicio;
            }
        });
    }
    
    public Ejercicio obtenerEjercicioPorId(int id) {
        String sql = "SELECT * FROM Ejercicio WHERE cod_ejercicio = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setCodEjercicio(rs.getInt("cod_ejercicio"));
            ejercicio.setNombreEjercicio(rs.getString("nombre_ejercicio"));
            ejercicio.setDescripcion(rs.getString("descripcion"));
            ejercicio.setCodCategoria(rs.getInt("cod_categoria"));
            ejercicio.setCodEquipo(rs.getInt("cod_equipo"));
            return ejercicio;
        });
    }
    
    public Equipamiento obtenerEquipoPorId(int id) {
        String sql = "SELECT * FROM Equipamiento WHERE cod_equipo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            Equipamiento equipo = new Equipamiento();
            equipo.setCodEquipo(rs.getInt("cod_equipo"));
            equipo.setNombreEquipo(rs.getString("nombre_equipo"));
            equipo.setTipo(rs.getString("tipo"));
            return equipo;
        });
    }
    
    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT * FROM CategoriaEjercicio WHERE cod_categoria = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            Categoria categoria = new Categoria();
            categoria.setCodCategoria(rs.getInt("cod_categoria"));
            categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            return categoria;
        });
    }
    
    public void eliminarEjercicio(int id) {
        String sql = "DELETE FROM Ejercicio WHERE cod_ejercicio = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public List<Equipamiento> listarEquipos() {
        String sql = "SELECT cod_equipo, nombre_equipo, tipo FROM Equipamiento";
        
        return jdbcTemplate.query(sql, new RowMapper<Equipamiento>() {
            @Override
            public Equipamiento mapRow(ResultSet rs, int rowNum) throws SQLException {
                Equipamiento equipo = new Equipamiento();
                equipo.setCodEquipo(rs.getInt("cod_equipo"));
                equipo.setNombreEquipo(rs.getString("nombre_equipo"));
                equipo.setTipo(rs.getString("tipo"));
                return equipo;
            }
        });
    } 
    
    // Método para actualizar un ejercicio
    public void actualizarEjercicio(Ejercicio ejercicio) {
        // SQL para actualizar los datos del ejercicio en la base de datos
        String sql = "UPDATE Ejercicio SET nombre_ejercicio = ?, descripcion = ?, cod_categoria = ?, cod_equipo = ? WHERE cod_ejercicio = ?";

        // Ejecutar la actualización
        jdbcTemplate.update(sql, 
            ejercicio.getNombreEjercicio(),
            ejercicio.getDescripcion(),
            ejercicio.getCodCategoria(),
            ejercicio.getCodEquipo(),
            ejercicio.getCodEjercicio()  // id del ejercicio a actualizar
        );
    }
 
 
    
}