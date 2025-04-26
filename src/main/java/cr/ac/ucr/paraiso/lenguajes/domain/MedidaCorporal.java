package cr.ac.ucr.paraiso.catalogo.gimnacio.domain;

public class MedidaCorporal {
private int codMedida;
private String nombreMedida;
private String unidadMedida;



public MedidaCorporal() {
	
}



public MedidaCorporal(int codMedida, String nombreMedida, String unidadMedida) {
	this.codMedida = codMedida;
	this.nombreMedida = nombreMedida;
	this.unidadMedida = unidadMedida;
}



public int getCodMedida() {
	return codMedida;
}



public void setCodMedida(int codMedida) {
	this.codMedida = codMedida;
}



public String getNombreMedida() {
	return nombreMedida;
}



public void setNombreMedida(String nombreMedida) {
	this.nombreMedida = nombreMedida;
}



public String getUnidadMedida() {
	return unidadMedida;
}



public void setUnidadMedida(String unidadMedida) {
	this.unidadMedida = unidadMedida;
}





}
