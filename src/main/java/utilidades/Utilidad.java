package utilidades;

public class Utilidad {


	/**
	 * se utiliza secuencia de escape ANSI que nos sirve para limpiar la pantalla de manera eficaz.
	 * se incluye en la clase utilidades el método para mostrar mensajes y evitar la sobreescritura de syso
	 */
    public static void limpiarPantalla() {
        // Limpia Pantalla
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
