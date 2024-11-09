package servicios;

import java.util.List;

public class PromedioServicioImp {

	/**
	 * 
	 * @param notas contiene (obviamente) las notas necesarias para el cálculo del promedio
	 * @return retorna el promedio calculado.
	 */
    public double calcularPromedio(List<Double> notas) {
        return notas.stream().mapToDouble(n -> n).average().orElse(0.0);
    }
}
