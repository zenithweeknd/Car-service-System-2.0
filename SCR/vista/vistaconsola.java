package vista;

public class vistaconsola {

    // Imprime una línea divisoria limpia
    public static void imprimirLinea() {
        System.out.println("==================================================");
    }

    // Imprime un título centrado en un recuadro sencillo
    public static void imprimirTitulo(String titulo) {
        imprimirLinea();
        System.out.println("        " + titulo.toUpperCase());
        imprimirLinea();
    }

    // Un atajo directo para no escribir System.out.println en la lógica
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}