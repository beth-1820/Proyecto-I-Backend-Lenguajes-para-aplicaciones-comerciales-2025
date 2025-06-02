package cr.ac.ucr.paraiso.lenguajes.dto;

import cr.ac.ucr.paraiso.lenguajes.domain.Rutina;
import cr.ac.ucr.paraiso.lenguajes.domain.ItemRutinaEjercicio;

import java.util.List;

public class ReporteRutinaDTO {
    private Rutina rutina;
    private List<ItemRutinaEjercicio> ejercicios;

    public ReporteRutinaDTO() {}

    public ReporteRutinaDTO(Rutina rutina, List<ItemRutinaEjercicio> ejercicios) {
        this.rutina = rutina;
        this.ejercicios = ejercicios;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public List<ItemRutinaEjercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<ItemRutinaEjercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
