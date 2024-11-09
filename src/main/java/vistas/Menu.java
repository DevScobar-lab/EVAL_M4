package vistas;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import servicios.ArchivoServicio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author tomas
 * @version 156483131213
 * Clase que contiene los método y su comportamiento para darle funcionalidad a lo enunciado en la interfaz
 */
public class Menu extends MenuTemplate {
    private AlumnoServicio alumnoServicio = new AlumnoServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciarMenu();
    }

/**
 * Método que genera un archivo con la información generada dentro del programa.
 * Omití la solicitud de datos al usuario para hacerlo más eficiente. lo compensé abriendo automáticamente la ruta de exportacion al final.
 */
    @Override
    public void exportarDatos() {
    	
    	//se crea una ruta absoluta para no tener problemas de permisos
    	String carpeta = System.getProperty("user.home") + "\\Documents\\MisArchivosExportados";
    	String rutaArchivo = carpeta + "/salida.txt";//nombre generico para el archivo (se sobreescribe)
    	
    	File directorio = new File(carpeta);
        
    	// si la carpeta no existe, la crea
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), rutaArchivo);
        System.out.println("Archivo exportado en: " + directorio.getAbsolutePath());
        
        //abre el directorio con el archivo exportado
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
				desktop.open(directorio);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Abre la carpeta que contiene el archivo
        } else {
            System.out.println("no se puede abrir la carpeta.");
        }
    }

    
   /**
    * Métodos relacionados con la creación, y listado de alumnos donde se le piden los datos al usuario y son ingresados por medio de los accesadores
    */
    @Override
    public void crearAlumno() {
        Alumno alumno = new Alumno();
        System.out.print("Ingresa RUT: ");
        alumno.setRut(scanner.nextLine());
        System.out.print("Ingresa nombre: ");
        alumno.setNombre(scanner.nextLine());
        System.out.print("Ingresa apellido: ");
        alumno.setApellido(scanner.nextLine());
        System.out.print("Ingresa dirección: ");
        alumno.setDireccion(scanner.nextLine());
        alumnoServicio.crearAlumno(alumno);
        System.out.println("--- ¡Alumno agregado! ---");
    }

    @Override
    public void agregarMateria() {
        System.out.print("Ingresa RUT del alumno: ");
        String rut = scanner.nextLine();
        System.out.println("Selecciona una Materia:");
        for (MateriaEnum materiaEnum : MateriaEnum.values()) {
            System.out.println((materiaEnum.ordinal() + 1) + ". " + materiaEnum);
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // consume proxima línea
        Materia materia = new Materia();
        materia.setNombre(MateriaEnum.values()[opcion - 1]);
        alumnoServicio.agregarMateria(rut, materia);
        System.out.println("¡Materia agregada!");
    }

    @Override
    public void agregarNotaPasoUno() {
        System.out.print("Ingresa RUT del alumno: ");
        String rut = scanner.nextLine();
        var materias = alumnoServicio.materiasPorAlumno(rut);
        if (materias != null && !materias.isEmpty()) {
            System.out.println("Materias del alumno:");
            for (int i = 0; i < materias.size(); i++) {
                System.out.println((i + 1) + ". " + materias.get(i).getNombre());
            }
            System.out.print("Selecciona materia: ");
            int seleccionMateria = scanner.nextInt();
            System.out.print("Ingresa nota: ");
            double nota = scanner.nextDouble();
            scanner.nextLine(); 

            Materia materiaSeleccionada = materias.get(seleccionMateria - 1);
            materiaSeleccionada.getNotas().add(nota);
            System.out.println("¡Nota agregada a "+materiaSeleccionada.getNombre()+"!");
        } else {
            System.out.println("-- No se encontraron materias para este alumno. --");
        }
    }

    @Override
    public void listarAlumnos() {
        var alumnos = alumnoServicio.listarAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            alumnos.forEach((rut, alumno) -> {
                System.out.println("RUT: " + rut + ", Nombre: " + alumno.getNombre());
                alumno.getMaterias().forEach(materia -> 
                    System.out.println("Materia: " + materia.getNombre() + ", Notas: " + materia.getNotas()));
            });
        }
    }

    @Override
    public void terminarPrograma() {
        System.out.println("Saliendo...");
        System.exit(0);
    }
}
