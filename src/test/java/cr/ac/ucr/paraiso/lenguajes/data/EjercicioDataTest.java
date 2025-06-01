package cr.ac.ucr.paraiso.lenguajes.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import cr.ac.ucr.paraiso.lenguajes.data.EjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.domain.Ejercicio;
import cr.ac.ucr.paraiso.lenguajes.domain.Equipamiento;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EjercicioData.class)
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EjercicioDataTest {

    @Autowired
    private EjercicioData ejercicioData;

    @Test
    void listarCategorias_deberiaRetornarListaCategorias() {
        // Arrange
        // (Datos cargados con @Sql)

        // Act
        List<Categoria> categorias = ejercicioData.listarCategorias();

        // Assert
        assertThat(categorias).isNotEmpty();
        assertThat(categorias.get(0).getNombreCategoria()).isEqualTo("Espalda");
    }

    @Test
    void listarEjercicios_deberiaRetornarEjerciciosDePrueba() {
        // Arrange

        // Act
        List<Ejercicio> ejercicios = ejercicioData.listarEjercicios();

        // Assert
        assertThat(ejercicios).hasSize(1);
        assertThat(ejercicios.get(0).getNombreEjercicio()).isEqualTo("Press Banca");
    }

    @Test
    void obtenerEjercicioPorId_deberiaRetornarEjercicioCorrecto() {
        // Arrange
        int id = 1;

        // Act
        Ejercicio ejercicio = ejercicioData.obtenerEjercicioPorId(id);

        // Assert
        assertThat(ejercicio).isNotNull();
        assertThat(ejercicio.getCodEjercicio()).isEqualTo(id);
        assertThat(ejercicio.getNombreEjercicio()).isEqualTo("Press Banca");
    }

    @Test
    void obtenerCategoriaPorId_deberiaRetornarCategoriaCorrecta() {
        // Arrange
        int id = 1;

        // Act
        Categoria categoria = ejercicioData.obtenerCategoriaPorId(id);

        // Assert
        assertThat(categoria).isNotNull();
        assertThat(categoria.getCodCategoria()).isEqualTo(id);
        assertThat(categoria.getNombreCategoria()).isEqualTo("Espalda");
    }

    @Test
    void obtenerEquipoPorId_deberiaRetornarEquipoCorrecto() {
        // Arrange
        int id = 1;

        // Act
        Equipamiento equipo = ejercicioData.obtenerEquipoPorId(id);

        // Assert
        assertThat(equipo).isNotNull();
        assertThat(equipo.getCodEquipo()).isEqualTo(id);
        assertThat(equipo.getNombreEquipo()).isEqualTo("Mancuernas");
    }

    @Test
    void listarEquipos_deberiaRetornarListaEquipos() {
        // Arrange

        // Act
        List<Equipamiento> equipos = ejercicioData.listarEquipos();

        // Assert
        assertThat(equipos).hasSize(1);
        assertThat(equipos.get(0).getNombreEquipo()).isEqualTo("Mancuernas");
    }
}
