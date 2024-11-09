package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Instancia de Enum de materias
 * Las notas se setean en formato double.
 */
public class Materia {
    private MateriaEnum nombre;
    private List<Double> notas = new ArrayList<>();

    public MateriaEnum getNombre() { return nombre; }
    public void setNombre(MateriaEnum nombre) { this.nombre = nombre; }

    public List<Double> getNotas() { return notas; }
    public void setNotas(List<Double> notas) { this.notas = notas; }
}
