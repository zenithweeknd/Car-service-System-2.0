package logica;

import datos.usuario;
import vista.lectorconsola;
import vista.vistaconsola;


public class menu {

    public static void mostrarMenuPrincipal() {
        usuario activo = login.getUsuarioAutenticado();
        int opcion = -1;

        while (opcion != 5) {
            vistaconsola.imprimirTitulo("Menú Principal - Car Center");
            
            // Seguridad solo visible para Administradores
            if (activo.getRol().equalsIgnoreCase("ADMINISTRADOR")) {
                System.out.println("[0]  Seguridad");
            }
            
            System.out.println("[1]  Configuración (Clientes)");
            System.out.println("[2]  Transacciones (Taller)");
            System.out.println("[3]  Consultas");
            System.out.println("[4]  Reportes");
            System.out.println("[5]  Cerrar Sesión y Salir");
            System.out.print("\n Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 0) {
                if (activo.getRol().equalsIgnoreCase("ADMINISTRADOR")) {
                    // Llamará a la gestión de usuarios del taller
                    seguridad.mostrarMenuSeguridad();
                } else {
                    vistaconsola.mostrarMensaje("\n Acceso Denegado: Su rol no tiene privilegios.");
                }
            } 
            else if (opcion == 1) {
                // Llamará al registro y control de clientes
                configuracion.mostrarMenuConfiguracion();
            } 
            else if (opcion == 2) {
                // Llamará a los flujos del taller (Vehículos, mecánicos, pagos)
                transacciones.mostrarMenuTransacciones();
            } 
            else if (opcion == 3) {
                // Llamará a los filtros de búsqueda por placa o pendientes
                consultas.mostrarMenuConsultas();
            } 
            else if (opcion == 4) {
                // Llamará a las interfaces de impresión simulada de reportes
                reportes.mostrarMenuReportes();
            } 
            else if (opcion == 5) {
                login.cerrarSesion();
            } 
            else {
                vistaconsola.mostrarMensaje("\n Opción inválida. Intente de nuevo.");
            }
        }
    }
}