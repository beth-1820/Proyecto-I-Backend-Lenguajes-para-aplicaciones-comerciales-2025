package cr.ac.ucr.paraiso.lenguajes.domain;

public class Instructor {
	
	private int idInstructor;
	private String nombreInstructor;
	private String apellidosInstructor;
	private String telefonoInstructor;
	private Usuario usuario;
	
	
	public Instructor() {
		
	}
	
	public Instructor(int idInstructor, String nombreInstructor, String apellidosInstructor, String telefonoInstructor,
			Usuario usuario) {
		super();
		this.idInstructor = idInstructor;
		this.nombreInstructor = nombreInstructor;
		this.apellidosInstructor = apellidosInstructor;
		this.telefonoInstructor = telefonoInstructor;
		this.usuario = usuario;
	}

	public int getIdInstructor() {
		return idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}

	public String getNombreInstructor() {
		return nombreInstructor;
	}

	public void setNombreInstructor(String nombreInstructor) {
		this.nombreInstructor = nombreInstructor;
	}

	public String getApellidosInstructor() {
		return apellidosInstructor;
	}

	public void setApellidosInstructor(String apellidosInstructor) {
		this.apellidosInstructor = apellidosInstructor;
	}

	public String getTelefonoInstructor() {
		return telefonoInstructor;
	}

	public void setTelefonoInstructor(String telefonoInstructor) {
		this.telefonoInstructor = telefonoInstructor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
