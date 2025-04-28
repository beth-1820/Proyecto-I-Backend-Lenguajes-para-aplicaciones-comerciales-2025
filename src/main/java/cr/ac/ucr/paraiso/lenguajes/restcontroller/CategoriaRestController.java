package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import cr.ac.ucr.paraiso.lenguajes.business.CategoriaBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Categoria;
import cr.ac.ucr.paraiso.lenguajes.dto.CategoriaDTO;
import cr.ac.ucr.paraiso.lenguajes.mapper.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaRestController {

    @Autowired
    private CategoriaBusiness categoriaBusiness;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        try {
            List<CategoriaDTO> categorias = categoriaBusiness.obtenerCategorias()
                    .stream()
                    .map(categoriaMapper::toDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoria(@PathVariable int id) {
        try {
            Categoria categoria = categoriaBusiness.buscarCategoriaPorId(id);
            if (categoria != null) {
                return new ResponseEntity<>(categoriaMapper.toDTO(categoria), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            Categoria categoria = categoriaMapper.toDomain(categoriaDTO);
            categoriaBusiness.guardarCategoria(categoria);
            return new ResponseEntity<>(categoriaMapper.toDTO(categoria), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(
            @PathVariable int id, 
            @RequestBody CategoriaDTO categoriaDTO) {
        try {
            categoriaDTO.setCodCategoria(id); // Asegurar que el ID coincide
            Categoria categoria = categoriaMapper.toDomain(categoriaDTO);
            categoriaBusiness.guardarCategoria(categoria);
            return new ResponseEntity<>(categoriaMapper.toDTO(categoria), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable int id) {
        try {
            categoriaBusiness.eliminarCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}