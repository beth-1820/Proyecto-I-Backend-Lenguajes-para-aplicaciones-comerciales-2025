package cr.ac.ucr.paraiso.lenguajes.mapper;

import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        
        return new CategoriaDTO(
            categoria.getCodCategoria(),
            categoria.getNombreCategoria()
        );
    }

    public Categoria toDomain(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) {
            return null;
        }
        
        Categoria categoria = new Categoria();
        categoria.setCodCategoria(categoriaDTO.getCodCategoria());
        categoria.setNombreCategoria(categoriaDTO.getNombreCategoria());
        return categoria;
    }
}