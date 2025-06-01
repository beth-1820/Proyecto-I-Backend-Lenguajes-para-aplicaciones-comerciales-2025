package cr.ac.ucr.paraiso.lenguajes.business;

import org.springframework.stereotype.Service;
import cr.ac.ucr.paraiso.lenguajes.data.UsuarioData;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;

@Service
public class UsuarioBusiness {
    private final UsuarioData usuarioData;

    public UsuarioBusiness(UsuarioData usuarioData) {
        this.usuarioData = usuarioData;
    }

    public Usuario autenticarUsuario(int idUser, String password) {
        Usuario usuario = usuarioData.autenticar(idUser, password);
        if (usuario == null || usuario.getEstado() != 1) { 
            return null;
        }
        return usuario;
    }
    
 // Método para verificar si es admin o instructor
    public boolean tieneAcceso(Usuario usuario) {
        return usuario != null && 
               ("admin".equalsIgnoreCase(usuario.getRol()) || 
                "instructor".equalsIgnoreCase(usuario.getRol()));
    }
}