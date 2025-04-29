package cr.ac.ucr.paraiso.lenguajes.dto;

public class UsuarioDTO {
    private int idUser;
    private String email;
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(int idUser, String email, String rol) {
        this.idUser = idUser;
        this.email = email;
        this.rol = rol;
    }

    // Getters y Setters
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}