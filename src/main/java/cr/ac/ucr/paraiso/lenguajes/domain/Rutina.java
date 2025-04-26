package cr.ac.ucr.paraiso.lenguajes.domain;

import java.time.LocalDate;

public class Rutina {
	
	private int codRutina;
	private LocalDate fechaCreacion;
	private LocalDate fechaRenovacion;
	private String objetivoCliente;
	private String enfermedadesCliente;
	private Cliente cliente;
	private Instructor instructor;	
	
	public Rutina() {
	
	}
	
	public Rutina(int codRutina, LocalDate fechaCreacion, LocalDate fechaRenovacion, String objetivoCliente,
			String enfermedadesCliente, Cliente cliente, Instructor instructor) {
		
		this.codRutina = codRutina;
		this.fechaCreacion = fechaCreacion;
		this.fechaRenovacion = fechaRenovacion;
		this.objetivoCliente = objetivoCliente;
		this.enfermedadesCliente = enfermedadesCliente;
		this.cliente = cliente;
		this.instructor = instructor;
	}

	public int getCodRutina() {
		return codRutina;
	}

	public void setCodRutina(int codRutina) {
		this.codRutina = codRutina;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaRenovacion() {
		return fechaRenovacion;
	}

	public void setFechaRenovacion(LocalDate fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	public String getObjetivoCliente() {
		return objetivoCliente;
	}

	public void setObjetivoCliente(String objetivoCliente) {
		this.objetivoCliente = objetivoCliente;
	}

	public String getEnfermedadesCliente() {
		return enfermedadesCliente;
	}

	public void setEnfermedadesCliente(String enfermedadesCliente) {
		this.enfermedadesCliente = enfermedadesCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	

}
