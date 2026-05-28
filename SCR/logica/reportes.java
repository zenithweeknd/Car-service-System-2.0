package logica;

import datos.vehiculo;
import datos.producto;
import vista.vistaconsola;
import vista.lectorconsola;

public class reportes {

    public static void mostrarMenuReportes() {
        int opcion = -1;
        while (opcion != 3) {
            vistaconsola.imprimirTitulo("Módulo Consolidado de Reportes");
            System.out.println("[1] 💰 Reporte de Caja (Finanzas General)");
            System.out.println("[2] 📦 Reporte de Stock de Productos");
            System.out.println("[3] ↩️ Volver al Menú Principal");
            System.out.print("\n👉 Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 1) { generarCaja(); } 
            else if (opcion == 2) { generarStock(); }
        }
    }

    private static void generarCaja() {
        vistaconsola.imprimirTitulo("Reporte de Caja Total");
        double acumulado = 0;
        for (vehiculo v : transacciones.getListaVehiculos()) {
            acumulado += v.getTotalPagado();
        }
        System.out.println("💰 Total Dinero en Efectivo/Medios Electrónicos: S/. " + acumulado);
        vistaconsola.imprimirLinea();
    }

    private static void generarStock() {
        vistaconsola.imprimirTitulo("Reporte de Stock e Inventario");
        if (transacciones.getInventario().isEmpty()) {
            System.out.println("📭 Almacén vacío. Registre repuestos en Transacciones.");
        } else {
            for (producto p : transacciones.getInventario()) {
                p.mostrarProducto();
            }
        }
        vistaconsola.imprimirLinea();
    }
}