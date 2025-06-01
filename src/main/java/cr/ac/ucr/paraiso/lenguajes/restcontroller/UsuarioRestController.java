package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cr.ac.ucr.paraiso.lenguajes.autentificacion.JwtTokenUtil;
import cr.ac.ucr.paraiso.lenguajes.business.UsuarioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;
import cr.ac.ucr.paraiso.lenguajes.dto.UsuarioDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioBusiness usuarioBusiness;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioBusiness.autenticarUsuario(
            usuario.getIdUser(), 
            usuario.getPassword()
        );
        
        if (usuarioAutenticado != null) {
            String token = jwtTokenUtil.generateToken(usuarioAutenticado);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("idUser", usuarioAutenticado.getIdUser());
            response.put("email", usuarioAutenticado.getEmail());
            response.put("rol", usuarioAutenticado.getRol());
            response.put("esAdmin", "admin".equalsIgnoreCase(usuarioAutenticado.getRol()));
            response.put("esInstructor", "instructor".equalsIgnoreCase(usuarioAutenticado.getRol()));
            response.put("tieneAcceso", usuarioBusiness.tieneAcceso(usuarioAutenticado));
            
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("mensaje", "Credenciales inválidas"));
    }
}