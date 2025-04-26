package cr.ac.ucr.paraiso.lenguajes.domain;

public class Administrador {
	
	private int idAdmin;
	private String nombreAdmin;
	private String apellidosAdmin;
	private String telefonoAdmin;
	private Usuario usuario;
	
	public Administrador() {
		
	}
	
	public Administrador(int idAdmin, String nombreAdmin, String apellidosAdmin, String telefonoAdmin,
			Usuario usuario) {
		super();
		this.idAdmin = idAdmin;
		this.nombreAdmin = nombreAdmin;
		this.apellidosAdmin = apellidosAdmin;
		this.telefonoAdmin = telefonoAdmin;
		this.usuario = usuario;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNombreAdmin() {
		return nombreAdmin;
	}

	public void setNombreAdmin(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
	}

	public String getApellidosAdmin() {
		return apellidosAdmin;
	}

	public void setApellidosAdmin(String apellidosAdmin) {
		this.apellidosAdmin = apellidosAdmin;
	}

	public String getTelefonoAdmin() {
		return telefonoAdmin;
	}

	public void setTelefonoAdmin(String telefonoAdmin) {
		this.telefonoAdmin = telefonoAdmin;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
