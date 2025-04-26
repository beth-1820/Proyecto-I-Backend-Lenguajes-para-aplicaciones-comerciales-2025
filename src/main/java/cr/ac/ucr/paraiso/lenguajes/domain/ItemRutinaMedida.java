package cr.ac.ucr.paraiso.lenguajes.domain;

public class ItemRutinaMedida {
private int idItemRutinaMedida;
private int codMedida;  //puede ser null
private int codRutina;  //puede ser null

public ItemRutinaMedida() {
	
}

public ItemRutinaMedida(int idItemRutinaMedida, int codMedida, int codRutina) {
	this.idItemRutinaMedida = idItemRutinaMedida;
	this.codMedida = codMedida;
	this.codRutina = codRutina;
}

public int getIdItemRutinaMedida() {
	return idItemRutinaMedida;
}

public void setIdItemRutinaMedida(int idItemRutinaMedida) {
	this.idItemRutinaMedida = idItemRutinaMedida;
}

public int getCodMedida() {
	return codMedida;
}

public void setCodMedida(int codMedida) {
	this.codMedida = codMedida;
}

public int getCodRutina() {
	return codRutina;
}

public void setCodRutina(int codRutina) {
	this.codRutina = codRutina;
}




}
