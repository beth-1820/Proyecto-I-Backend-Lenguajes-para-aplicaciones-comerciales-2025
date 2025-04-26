package cr.ac.ucr.paraiso.lenguajes.domain;

public class ItemRutinaEjercicio {
private int idItemRutinaEjercicio;
private int seriesEjercicio;
private int repeticionesEjercicio;
private int codEjercicio; //puede ser null
private int codRutina;   //puede ser null




public ItemRutinaEjercicio() {
	
}




public ItemRutinaEjercicio(int idItemRutinaEjercicio, int seriesEjercicio, int repeticionesEjercicio, int codEjercicio,
		int codRutina) {
	this.idItemRutinaEjercicio = idItemRutinaEjercicio;
	this.seriesEjercicio = seriesEjercicio;
	this.repeticionesEjercicio = repeticionesEjercicio;
	this.codEjercicio = codEjercicio;
	this.codRutina = codRutina;
}




public int getIdItemRutinaEjercicio() {
	return idItemRutinaEjercicio;
}




public void setIdItemRutinaEjercicio(int idItemRutinaEjercicio) {
	this.idItemRutinaEjercicio = idItemRutinaEjercicio;
}




public int getSeriesEjercicio() {
	return seriesEjercicio;
}




public void setSeriesEjercicio(int seriesEjercicio) {
	this.seriesEjercicio = seriesEjercicio;
}




public int getRepeticionesEjercicio() {
	return repeticionesEjercicio;
}




public void setRepeticionesEjercicio(int repeticionesEjercicio) {
	this.repeticionesEjercicio = repeticionesEjercicio;
}




public int getCodEjercicio() {
	return codEjercicio;
}




public void setCodEjercicio(int codEjercicio) {
	this.codEjercicio = codEjercicio;
}




public int getCodRutina() {
	return codRutina;
}




public void setCodRutina(int codRutina) {
	this.codRutina = codRutina;
}





}
