package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cr.ac.ucr.paraiso.lenguajes.business.UsuarioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;
import cr.ac.ucr.paraiso.lenguajes.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {
    private final UsuarioBusiness usuarioBusiness;

    public UsuarioRestController(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioBusiness.autenticarUsuario(usuario.getIdUser(), usuario.getPassword());
        if (usuarioAutenticado != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuarioAutenticado.getIdUser(),
                usuarioAutenticado.getEmail(),
                usuarioAutenticado.getRol().toString()
            );
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.status(401).build();
    }
}