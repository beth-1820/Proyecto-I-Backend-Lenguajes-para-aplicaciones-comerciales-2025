package cr.ac.ucr.paraiso.lenguajes.domain;

public class Ejercicio {
private int codEjercicio;
private String nombreEjercicio;
private String descripcion;
private int codCategoria;
private int codEquipo;



public Ejercicio() {

}



public Ejercicio(int codEjercicio, String nombreEjercicio, String descripcion, int codCategoria, int codEquipo) {
	this.codEjercicio = codEjercicio;
	this.nombreEjercicio = nombreEjercicio;
	this.descripcion = descripcion;
	this.codCategoria = codCategoria;
	this.codEquipo = codEquipo;
}



public int getCodEjercicio() {
	return codEjercicio;
}



public void setCodEjercicio(int codEjercicio) {
	this.codEjercicio = codEjercicio;
}



public String getNombreEjercicio() {
	return nombreEjercicio;
}



public void setNombreEjercicio(String nombreEjercicio) {
	this.nombreEjercicio = nombreEjercicio;
}



public String getDescripcion() {
	return descripcion;
}



public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}



public int getCodCategoria() {
	return codCategoria;
}



public void setCodCategoria(int codCategoria) {
	this.codCategoria = codCategoria;
}



public int getCodEquipo() {
	return codEquipo;
}



public void setCodEquipo(int codEquipo) {
	this.codEquipo = codEquipo;
}






}
