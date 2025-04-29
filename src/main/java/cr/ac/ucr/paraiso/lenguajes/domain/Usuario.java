package cr.ac.ucr.paraiso.lenguajes.domain;

public class Usuario {
	
	private int idUser;
	private String email;
	private String password;
	private int estado;
	private String rol;
	
	public Usuario(){
		
	}
	
	

	public Usuario(int idUser, String email, String password, int estado, String rol) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.estado = estado;
		this.rol = rol;
	}



	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
