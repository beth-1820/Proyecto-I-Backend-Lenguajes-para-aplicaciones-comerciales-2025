package cr.ac.ucr.paraiso.lenguajes.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import cr.ac.ucr.paraiso.lenguajes.data.EjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;

import org.springframework.beans.factory.annotation.Autowired;

import cr.ac.ucr.paraiso.lenguajes.data.EjercicioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;

import cr.ac.ucr.paraiso.lenguajes.data.CategoriaData;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaBusiness {
	@Autowired
	private EjercicioData ejercicioData;

	public List<Categoria> obtenerTodasCategorias() {
	    return ejercicioData.listarCategorias();
	}


    @Autowired
    private CategoriaData categoriaData;

    public List<Categoria> obtenerCategorias() throws Exception {
        try {
            return categoriaData.obtenerTodasCategorias();
        } catch (Exception e) {
            throw new Exception("Error al obtener las categorías: " + e.getMessage());
        }
    }

    public Categoria buscarCategoriaPorId(int codCategoria) throws Exception {
        try {
            Categoria categoria = categoriaData.buscarCategoriaPorId(codCategoria);
            if (categoria == null) {
                throw new Exception("No se encontró la categoría con código: " + codCategoria);
            }
            return categoria;
        } catch (Exception e) {
            throw new Exception("Error al buscar la categoría: " + e.getMessage());
        }
    }
    
    public Categoria buscarCategoriaPorNombre(String nombreCategoria) throws Exception {
        try {
            Categoria categoria = categoriaData.buscarCategoriaPorNombre(nombreCategoria);
            if (categoria == null) {
                throw new Exception("No se encontró la categoría con nombre: " + nombreCategoria);
            }
            return categoria;
        } catch (Exception e) {
            throw new Exception("Error al buscar la categoría por nombre: " + e.getMessage());
        }
    }

    public void guardarCategoria(Categoria categoria) throws Exception {
        try {
            // Validaciones de negocio
            if (categoria.getNombreCategoria() == null || categoria.getNombreCategoria().isEmpty()) {
                throw new Exception("El nombre de la categoría es requerido");
            }

            if (categoria.getCodCategoria() <= 0) {
                // Solo para nuevas categorías (cod_categoria <= 0)
                categoriaData.insertarCategoria(categoria);
            } else {
                categoriaData.actualizarCategoria(categoria);
            }
        } catch (Exception e) {
            throw new Exception("Error al guardar la categoría: " + e.getMessage());
        }
    }

    public void eliminarCategoria(int codCategoria) throws Exception {
        try {
            // Verificar si la categoría existe antes de eliminar
            Categoria categoria = categoriaData.buscarCategoriaPorId(codCategoria);
            if (categoria == null) {
                throw new Exception("No se encontró la categoría a eliminar");
            }

            categoriaData.eliminarCategoria(codCategoria);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la categoría: " + e.getMessage());
        }
    }
}