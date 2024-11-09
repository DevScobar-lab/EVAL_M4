package tests;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoServicioTest {

    private AlumnoServicio alumnoServicio;
    private AlumnoServicio alumnoServicioMock;
    private Materia matematicas;
    private Materia lenguaje;
    private Alumno pupilo;

    /**
     * Se preconfiguran los elementos para su utilización en las pruebas unitarias
     */
    @BeforeEach
    public void setup() {
        alumnoServicio = new AlumnoServicio();
        alumnoServicioMock = Mockito.mock(AlumnoServicio.class);

        matematicas = new Materia();
        matematicas.setNombre(MateriaEnum.MATEMATICAS);

        lenguaje = new Materia();
        lenguaje.setNombre(MateriaEnum.LENGUAJE);

        pupilo = new Alumno();
        pupilo.setRut("12345678-9");
        pupilo.setNombre("Tomás");
        pupilo.setApellido("Escobar");
        pupilo.setDireccion("Av Providencia 1208");
    }

    /**
     * Pruebas que aseguran que el resultado de las pruebas cumplan con lo esperado
     */
    @Test
    public void crearAlumnoTest() {
        alumnoServicio.crearAlumno(pupilo);
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertTrue(alumnos.containsKey("24112949-7"));
        assertEquals("Alumno", alumnos.get("24112949-7").getNombre());
    }

    @Test
    public void agregarMateriaTest() {
        alumnoServicio.crearAlumno(pupilo);
        alumnoServicio.agregarMateria("12345678-9", matematicas);
        List<Materia> materias = alumnoServicio.materiasPorAlumno("12345678-9");
        assertEquals(1, materias.size());
        assertEquals(MateriaEnum.MATEMATICAS, materias.get(0).getNombre());
    }

    @Test
    public void materiasPorAlumnoTest() {
        when(alumnoServicioMock.materiasPorAlumno("12345678-9")).thenReturn(List.of(matematicas, lenguaje));
        
        List<Materia> materias = alumnoServicioMock.materiasPorAlumno("12345678-9");
        assertNotNull(materias);
        assertEquals(2, materias.size());
        assertEquals(MateriaEnum.MATEMATICAS, materias.get(0).getNombre());
        assertEquals(MateriaEnum.LENGUAJE, materias.get(1).getNombre());
    }

    @Test
    public void listarAlumnosTest() {
        alumnoServicio.crearAlumno(pupilo);
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertFalse(alumnos.isEmpty());
        assertEquals(1, alumnos.size());
        assertEquals("Alumno", alumnos.get("12345678-9").getNombre());
    }
}
