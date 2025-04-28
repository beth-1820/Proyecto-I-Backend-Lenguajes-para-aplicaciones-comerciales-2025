package cr.ac.ucr.paraiso.lenguajes.dto;

public class CategoriaDTO {
    private int codCategoria;
    private String nombreCategoria;

    // Constructores
    public CategoriaDTO() {
    	// TODO Auto-generated constructor stub
    }

    public CategoriaDTO(int codCategoria, String nombreCategoria) {
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    // Getters y Setters
    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}