package cr.ac.ucr.paraiso.lenguajes.dto;

public class ItemRutinaEjercicioDTO {
    private int idItemRutinaEjercicio;
    private int seriesEjercicio;
    private int repeticionesEjercicio;
    private int codEjercicio;
    private int codRutina;

    public ItemRutinaEjercicioDTO() {}

    public int getIdItemRutinaEjercicio() {
        return idItemRutinaEjercicio;
    }

    public void setIdItemRutinaEjercicio(int id) {
        this.idItemRutinaEjercicio = id;
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
