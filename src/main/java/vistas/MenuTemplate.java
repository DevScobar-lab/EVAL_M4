package vistas;

import java.util.Scanner;

public abstract class MenuTemplate {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void exportarDatos();
    public abstract void crearAlumno();
    public abstract void agregarMateria();
    public abstract void agregarNotaPasoUno();
    public abstract void listarAlumnos();
    public abstract void terminarPrograma();
    
    /**
     * @author tomas
     * @version 15648
     * abstracta que grafica al usuario los alcances del programa
     * en la estructura switch llama al método correspondiente según el caso
     * Se utiliza scanner para consumir un salto de linea
     */

    public void iniciarMenu() {
        while (true) {
            System.out.println("1. Crear Alumno");
            System.out.println("2. Listar Alumnos");
            System.out.println("3. Agregar Materia");
            System.out.println("4. Agregar Notas");
            System.out.println("5. Salir");
            System.out.println("6. Exportar Datos");
            System.out.print("Selección: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  

       
            switch (opcion) {
                case 1 -> crearAlumno();
                case 2 -> listarAlumnos();
                case 3 -> agregarMateria();
                case 4 -> agregarNotaPasoUno();
                case 5 -> terminarPrograma();
                case 6 -> exportarDatos();
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
