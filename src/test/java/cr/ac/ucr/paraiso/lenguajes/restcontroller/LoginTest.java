package cr.ac.ucr.paraiso.lenguajes.restcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cr.ac.ucr.paraiso.lenguajes.autentificacion.JwtTokenUtil;
import cr.ac.ucr.paraiso.lenguajes.business.UsuarioBusiness;
import cr.ac.ucr.paraiso.lenguajes.domain.Usuario;

public class LoginTest {

    @Mock
    private UsuarioBusiness usuarioBusiness;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UsuarioRestController usuarioRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void LoginAdmin() {
        // Arrange 
        Usuario usuarioMock = new Usuario();
        usuarioMock.setIdUser(386428683);
        usuarioMock.setEmail("beth.madrigal@gmail.com");
        usuarioMock.setRol("admin");
        usuarioMock.setEstado(1);

        when(usuarioBusiness.autenticarUsuario(386428683, "123456")).thenReturn(usuarioMock);
        when(jwtTokenUtil.generateToken(usuarioMock)).thenReturn("token_jwt_mock");

        Usuario usuarioRequest = new Usuario();
        usuarioRequest.setIdUser(386428683);
        usuarioRequest.setPassword("123456");

        // Act 
        ResponseEntity<?> response = usuarioRestController.login(usuarioRequest);

        // Assert 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("token_jwt_mock", responseBody.get("token"));
        assertEquals(true, responseBody.get("esAdmin"));
        assertEquals("admin", responseBody.get("rol"));
        assertEquals("beth.madrigal@gmail.com", responseBody.get("email"));
    }

    @Test
    public void LoginInstructor() {
        // Arrange
        Usuario usuarioMock = new Usuario();
        usuarioMock.setIdUser(302580147);
        usuarioMock.setEmail("julia.mendez@gmail.com");
        usuarioMock.setRol("instructor");
        usuarioMock.setEstado(1);

        when(usuarioBusiness.autenticarUsuario(302580147, "789456")).thenReturn(usuarioMock);
        when(jwtTokenUtil.generateToken(usuarioMock)).thenReturn("token_jwt_mock");

        Usuario usuarioRequest = new Usuario();
        usuarioRequest.setIdUser(302580147);
        usuarioRequest.setPassword("789456");

        // Act
        ResponseEntity<?> response = usuarioRestController.login(usuarioRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("token_jwt_mock", responseBody.get("token"));
        assertEquals("instructor", responseBody.get("rol"));
        assertEquals(false, responseBody.get("esAdmin"));
        assertEquals(302580147, responseBody.get("idUser"));
    }

    @Test
    public void LoginInvalido() {
        // Arrange
        when(usuarioBusiness.autenticarUsuario(anyInt(), anyString())).thenReturn(null);

        Usuario usuarioRequest = new Usuario();
        usuarioRequest.setIdUser(999999999);
        usuarioRequest.setPassword("contraseña_incorrecta");

        // Act
        ResponseEntity<?> response = usuarioRestController.login(usuarioRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales inválidas", ((Map<?, ?>) response.getBody()).get("mensaje"));
    }
}