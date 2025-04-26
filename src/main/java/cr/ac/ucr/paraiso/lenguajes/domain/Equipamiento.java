package cr.ac.ucr.paraiso.catalogo.gimnacio.domain;

public class Equipamiento {
private int codEquipo;
private String nombreEquipo;
private String tipo;



public Equipamiento() {
	
}



public Equipamiento(int codEquipo, String nombreEquipo, String tipo) {
	this.codEquipo = codEquipo;
	this.nombreEquipo = nombreEquipo;
	this.tipo = tipo;
}



public int getCodEquipo() {
	return codEquipo;
}



public String getNombreEquipo() {
	return nombreEquipo;
}



public String getTipo() {
	return tipo;
}






}
