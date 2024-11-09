package tests;

import org.junit.jupiter.api.Test;

import servicios.PromedioServicioImp;

import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromedioServicioImpTest {
	
	/**
	 * Comprobacion de el resultado de los métodos.
	 */

    private final PromedioServicioImp promedioServicio = new PromedioServicioImp();

    @Test
    public void calcularPromedioTest_conNotas() {
        double resultado = promedioServicio.calcularPromedio(Arrays.asList(5.0, 6.0, 7.0));
        assertEquals(6.0, resultado, 0.001, "El promedio debería ser 6.0");
    }

    @Test
    public void calcularPromedioTest_sinNotas() {
        double resultado = promedioServicio.calcularPromedio(Collections.emptyList());
        assertEquals(0.0, resultado, 0.001, "El promedio debería ser 0.0 para lista vacía");
    }
}
