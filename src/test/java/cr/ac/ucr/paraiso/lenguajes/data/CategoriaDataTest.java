package cr.ac.ucr.paraiso.lenguajes.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;

@SpringBootTest
@Transactional
public class CategoriaDataTest {

    @Autowired
    private CategoriaData categoriaData;

    @Test
    public void testInsertarCategoria() {
        Categoria nueva = new Categoria();
        nueva.setNombreCategoria("InsertarPrueba");
        
        categoriaData.insertarCategoria(nueva);

        int idGenerado = nueva.getCodCategoria();
        assertTrue(idGenerado > 0, "El código generado debe ser > 0");

        Categoria recuperada = categoriaData.buscarCategoriaPorId(idGenerado);
        assertNotNull(recuperada, "Debe encontrarse la categoría insertada");
        assertEquals("InsertarPrueba", recuperada.getNombreCategoria());
    }

    @Test
    public void testBuscarCategoriaPorId() {
        Categoria nueva = new Categoria();
        nueva.setNombreCategoria("BuscarPorIdPrueba");
        categoriaData.insertarCategoria(nueva);
        int idGenerado = nueva.getCodCategoria();

        Categoria porId = categoriaData.buscarCategoriaPorId(idGenerado);
        assertNotNull(porId, "buscarCategoriaPorId no debe devolver null");
        assertEquals(idGenerado, porId.getCodCategoria());
        assertEquals("BuscarPorIdPrueba", porId.getNombreCategoria());

        Categoria inexistente = categoriaData.buscarCategoriaPorId(-1);
        assertNull(inexistente, "Buscar un ID inexistente debe devolver null");
    }

    @Test
    public void testBuscarCategoriaPorNombre() {
        Categoria nueva = new Categoria();
        nueva.setNombreCategoria("BuscarPorNombrePrueba");
        categoriaData.insertarCategoria(nueva);
        int idGenerado = nueva.getCodCategoria();

        Categoria porNombre = categoriaData.buscarCategoriaPorNombre("BuscarPorNombrePrueba");
        assertNotNull(porNombre, "buscarCategoriaPorNombre no debe devolver null");
        assertEquals(idGenerado, porNombre.getCodCategoria());
        assertEquals("BuscarPorNombrePrueba", porNombre.getNombreCategoria());

        Categoria inexistente = categoriaData.buscarCategoriaPorNombre("NombreInexistente");
        assertNull(inexistente, "Buscar un nombre inexistente debe devolver null");
    }

    @Test
    public void testActualizarCategoria() {
        Categoria nueva = new Categoria();
        nueva.setNombreCategoria("OriginalPrueba");
        categoriaData.insertarCategoria(nueva);
        int idGenerado = nueva.getCodCategoria();

        Categoria paraActualizar = categoriaData.buscarCategoriaPorId(idGenerado);
        paraActualizar.setNombreCategoria("ActualizadoPrueba");
        categoriaData.actualizarCategoria(paraActualizar);

        Categoria actualizado = categoriaData.buscarCategoriaPorId(idGenerado);
        assertNotNull(actualizado, "La categoría actualizada no debe ser null");
        assertEquals("ActualizadoPrueba", actualizado.getNombreCategoria());
    }

    @Test
    public void testEliminarCategoria() {
        Categoria nueva = new Categoria();
        nueva.setNombreCategoria("EliminarPrueba");
        categoriaData.insertarCategoria(nueva);
        int idGenerado = nueva.getCodCategoria();

        Categoria antesDeEliminar = categoriaData.buscarCategoriaPorId(idGenerado);
        assertNotNull(antesDeEliminar, "Debe existir antes de eliminar");

        categoriaData.eliminarCategoria(idGenerado);
        Categoria despuesDeEliminar = categoriaData.buscarCategoriaPorId(idGenerado);
        assertNull(despuesDeEliminar, "Después de eliminar, buscar debe devolver null");
    }
}
