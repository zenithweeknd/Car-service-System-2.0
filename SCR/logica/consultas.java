package logica;

import java.util.ArrayList;
import datos.vehiculo;
import datos.usuario;
import vista.lectorconsola;
import vista.vistaconsola;

public class consultas {

    public static void mostrarMenuConsultas() {
        int opcion = -1;
        while (opcion != 6) {
            vistaconsola.imprimirTitulo("Módulo Avanzado de Consultas");
            System.out.println("[1] 🔍 Buscar Vehículo por Placa");
            System.out.println("[2] ⏳ Ver Vehículos en Espera (Mecánico Sin Asignar)");
            System.out.println("[3] 📜 Ver Historial de Reparaciones de un Vehículo");
            System.out.println("[4] 👨‍🔧 Ver Mecánicos Disponibles");
            System.out.println("[5] 📊 Consultas de Servicios Más Solicitados");
            System.out.println("[6] ↩️ Volver al Menú Principal");
            System.out.print("\n👉 Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 1) { buscarPorPlaca(); } 
            else if (opcion == 2) { verEnEspera(); } 
            else if (opcion == 3) { verHistorialAuto(); } 
            else if (opcion == 4) { verMecanicosDisponibles(); } 
            else if (opcion == 5) { consultarServiciosFrecuentes(); }
        }
    }

    private static void buscarPorPlaca() {
        vistaconsola.imprimirTitulo("Consulta por Placa");
        System.out.print("Placa: "); String pl = lectorconsola.leerTexto().toUpperCase();
        vehiculo v = transacciones.buscarVehiculo(pl);
        if (v != null) {
            System.out.println("Placa: " + v.getPlaca() + " | Modelo: " + v.getModelo() + " | Año: " + v.getAnio() + " | Cilindrada: " + v.getCilindrada() + " cc | Estado: " + v.getEstado());
        } else {
            System.out.println("❌ No registrado.");
        }
    }

    private static void verEnEspera() {
        vistaconsola.imprimirTitulo("Vehículos en Espera");
        for (vehiculo v : transacciones.getListaVehiculos()) {
            if (v.getEstado().equalsIgnoreCase("En espera") && v.getMecanicoAsignado() == null) {
                System.out.println("🚗 [" + v.getPlaca() + "] " + v.getModelo() + " | Cilindrada: " + v.getCilindrada() + " cc | Mecánico: Sin asignar");
            }
        }
    }

    private static void verHistorialAuto() {
        vistaconsola.imprimirTitulo("Historial Clínico del Vehículo");
        System.out.print("Placa: "); String pl = lectorconsola.leerTexto().toUpperCase();
        vehiculo v = transacciones.buscarVehiculo(pl);
        if (v != null) {
            System.out.println("🚗 Datos: " + v.getMarca() + " " + v.getModelo() + " (" + v.getCilindrada() + " cc)");
            System.out.println("👨‍🔧 Mecánico Encargado: " + (v.getMecanicoAsignado() != null ? v.getMecanicoAsignado().getNombre() : "Ninguno"));
            System.out.println("📜 Historial de Servicios Realizados: " + v.getServiciosRealizados());
        }
    }

    private static void verMecanicosDisponibles() {
        vistaconsola.imprimirTitulo("Mecánicos de Planta");
        for (usuario u : seguridad.getListaUsuarios()) {
            if (u.getRol().equals("MECANICO")) {
                System.out.println("👨‍🔧 Mecánico: " + u.getNombre() + " " + u.getApellido());
            }
        }
    }

    private static void consultarServiciosFrecuentes() {
        vistaconsola.imprimirTitulo("Servicios Solicitados en Sistema");
        // Simulación estadística directa basada en el tarifario fijo
        for (String s : configuracion.SERVICIOS) {
            System.out.println("🛠️ Servicio: " + s + " -> Solicitado");
        }
    }
}