package vista;

import java.util.Scanner;

public class lectorconsola {
    private static Scanner scanner = new Scanner(System.in);

    // Lee texto de forma simple
    public static String leerTexto() {
        return scanner.nextLine();
    }

    // Lee números enteros controlando que no metan letras por error
    public static int leerEntero() {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print(" Error: Ingrese un número válido: ");
            }
        }
    }

    // Lee números decimales (precios o pagos) de forma segura
    public static double leerDecimal() {
        while (true) {
            try {
                double decimal = Double.parseDouble(scanner.nextLine());
                return decimal;
            } catch (NumberFormatException e) {
                System.out.print(" Error: Ingrese un monto decimal válido: ");
            }
        }
    }
}