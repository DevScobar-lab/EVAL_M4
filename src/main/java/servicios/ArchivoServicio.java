package servicios;

import modelos.Alumno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ArchivoServicio {
    private PromedioServicioImp promedioServicioImp = new PromedioServicioImp();
/**
 * 
 * @param alumnos contiene los atributos que son agregados durante la ejecución del progra,a.
 * @param ruta define haciua donde va el archivo.
 */
    public void exportarDatos(Map<String, Alumno> alumnos, String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Alumno alumno : alumnos.values()) {
                writer.write("Alumno: " + alumno.getRut() + " - " + alumno.getNombre() + "\n");
                for (var materia : alumno.getMaterias()) {
                    double promedio = promedioServicioImp.calcularPromedio(materia.getNotas());
                    writer.write("Materia: " + materia.getNombre() + " - Promedio: " + promedio + "\n");
                }
            }
            writer.write("Datos exportados correctamente.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
