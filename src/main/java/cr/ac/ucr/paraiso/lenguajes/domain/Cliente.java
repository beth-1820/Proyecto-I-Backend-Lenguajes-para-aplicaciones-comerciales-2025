package cr.ac.ucr.paraiso.lenguajes.domain;

import java.time.LocalDate;

public class Cliente {
	
	private int idCliente;
	private String nombreCliente;
	private String apellidosCliente;
	private LocalDate fechaNacimiento;
	private String telefonoCliente;
	private String direccion;
	private String nombreContactoEmergencia;
	private String telContactoEmergencia;
	
	public Cliente () {
		
	}
	
	public Cliente(int idCliente, String nombreCliente, String apellidosCliente, LocalDate fechaNacimiento,
			String telefonoCliente, String direccion, String nombreContactoEmergencia, String telContactoEmergencia) {
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.fechaNacimiento = fechaNacimiento;
		this.telefonoCliente = telefonoCliente;
		this.direccion = direccion;
		this.nombreContactoEmergencia = nombreContactoEmergencia;
		this.telContactoEmergencia = telContactoEmergencia;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreContactoEmergencia() {
		return nombreContactoEmergencia;
	}

	public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
		this.nombreContactoEmergencia = nombreContactoEmergencia;
	}

	public String getTelContactoEmergencia() {
		return telContactoEmergencia;
	}

	public void setTelContactoEmergencia(String telContactoEmergencia) {
		this.telContactoEmergencia = telContactoEmergencia;
	}
	
}
