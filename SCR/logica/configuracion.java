package logica;

import java.util.ArrayList;
import datos.cliente;
import datos.usuario;
import vista.lectorconsola;
import vista.vistaconsola;

public class configuracion {
    private static ArrayList<cliente> listaClientes = new ArrayList<>();
    
    // Arrays maestros requeridos por el doc
    public static final String[] SERVICIOS = {
        "Mantenimiento preventivo", "Reparaciones mecanicas", "Servicios electricos y electronicos",
        "Servicios de carroceria y pintura", "Servicios de neumaticos", "Servicios de diagnostico y asesoria",
        "Servicios especializados", "Servicios adicionales"
    };
    
    public static final double[] PRECIOS = { 120.0, 250.0, 180.0, 450.0, 80.0, 50.0, 300.0, 60.0 };
    public static final String[] MARCAS = { "Toyota", "Volkswagen", "Chevrolet", "Honda", "Yamaha", "Bajaj", "Otros" };

    public static void mostrarMenuConfiguracion() {
        int opcion = -1;
        while (opcion != 8) {
            vistaconsola.imprimirTitulo("Módulo de Configuración e Información");
            System.out.println("[1] 🏢 Ver Datos del Taller (Sede)");
            System.out.println("[2] 🛠️ Ver Tarifario de Servicios");
            System.out.println("[3] ➕ Agregar / Listar Clientes");
            System.out.println("[4] ✏️ Editar Datos de un Cliente");
            System.out.println("[5] ❌ Eliminar Cliente (Lista Numerada)");
            System.out.println("[6] 👨‍🔧 Agregar / Mostrar Mecánicos");
            System.out.println("[7] 🗑️ Eliminar Mecánico");
            System.out.println("[8] ↩️ Volver al Menú Principal");
            System.out.print("\n👉 Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 1) { mostrarDatosTaller(); }
            else if (opcion == 2) { mostrarTarifario(); }
            else if (opcion == 3) { gestionarClientes(); }
            else if (opcion == 4) { editarCliente(); }
            else if (opcion == 5) { eliminarCliente(); }
            else if (opcion == 6) { gestionarMecanicos(); }
            else if (opcion == 7) { eliminarMecanico(); }
            else if (opcion == 8) { vistaconsola.mostrarMensaje("Regresando..."); }
            else { vistaconsola.mostrarMensaje("⚠️ Opción no válida."); }
        }
    }

    private static void mostrarDatosTaller() {
        vistaconsola.imprimirTitulo("INFORMACIÓN INSTITUCIONAL");
        System.out.println("[INFO] CAR CENTER TARAPOTO");
        System.out.println("[INFO] Ubicacion: Jr. libertad 238, tarapoto");
        System.out.println("[INFO] Descripcion: Taller de coches con rapida solucion de problemas y acceso a repuestos.");
        vistaconsola.imprimirLinea();
    }

    private static void mostrarTarifario() {
        vistaconsola.imprimirTitulo("TARIFARIO DE SERVICIOS");
        for (int i = 0; i < SERVICIOS.length; i++) {
            System.out.println("[" + i + "] " + SERVICIOS[i] + " : S/. " + PRECIOS[i]);
        }
        vistaconsola.imprimirLinea();
    }

    private static void gestionarClientes() {
        vistaconsola.imprimirTitulo("Gestión de Clientes");
        System.out.println("[1] Registrar Cliente | [2] Mostrar Lista");
        int sub = lectorconsola.leerEntero();
        if (sub == 1) {
            System.out.print("DNI: "); String dni = lectorconsola.leerTexto();
            System.out.print("Nombre: "); String nom = lectorconsola.leerTexto();
            System.out.print("Apellido: "); String ape = lectorconsola.leerTexto();
            System.out.print("Dirección: "); String dir = lectorconsola.leerTexto();
            System.out.print("Correo: "); String cor = lectorconsola.leerTexto();
            System.out.print("Ciudad: "); String ciu = lectorconsola.leerTexto();
            System.out.print("Teléfono: "); String tel = lectorconsola.leerTexto();
            listaClientes.add(new cliente(nom, ape, tel, dni, dir, cor, ciu));
            System.out.println("✅ Cliente guardado.");
        } else {
            for (cliente c : listaClientes) { c.mostrar(); System.out.println("---"); }
        }
    }

    private static void editarCliente() {
        vistaconsola.imprimirTitulo("Editar Datos de Cliente");
        System.out.print("Ingrese DNI del cliente a editar: ");
        String dni = lectorconsola.leerTexto();
        for (cliente c : listaClientes) {
            if (c.getDni().equals(dni)) {
                System.out.print("Nuevo Nombre: "); c.setNombre(lectorconsola.leerTexto());
                System.out.print("Nuevo Apellido: "); c.setApellido(lectorconsola.leerTexto());
                System.out.print("Nueva Dirección: "); c.setDireccion(lectorconsola.leerTexto());
                System.out.print("Nuevo Correo: "); c.setCorreo(lectorconsola.leerTexto());
                System.out.println("✅ Cliente editado.");
                return;
            }
        }
        System.out.println("❌ No encontrado.");
    }

    private static void eliminarCliente() {
        vistaconsola.imprimirTitulo("Eliminar Cliente");
        if (listaClientes.isEmpty()) { System.out.println("No hay clientes."); return; }
        for (int i = 0; i < listaClientes.size(); i++) {
            cliente c = listaClientes.get(i);
            System.out.println("[" + i + "] DNI: " + c.getDni() + " | " + c.getNombre() + " " + c.getApellido());
        }
        System.out.print("Seleccione el número a borrar: ");
        int index = lectorconsola.leerEntero();
        if (index >= 0 && index < listaClientes.size()) {
            listaClientes.remove(index);
            System.out.println("🗑️ Cliente removido.");
        }
    }

    private static void gestionarMecanicos() {
        vistaconsola.imprimirTitulo("Mecánicos del Taller");
        System.out.println("[1] Agregar Mecánico | [2] Mostrar Lista");
        int sub = lectorconsola.leerEntero();
        ArrayList<usuario> usuarios = seguridad.getListaUsuarios();
        
        if (sub == 1) {
            System.out.print("DNI: "); String dni = lectorconsola.leerTexto();
            System.out.print("Nombre: "); String nom = lectorconsola.leerTexto();
            System.out.print("Apellido: "); String ape = lectorconsola.leerTexto();
            System.out.print("Usuario Sistema: "); String us = lectorconsola.leerTexto();
            System.out.print("Contraseña: "); String ps = lectorconsola.leerTexto();
            usuarios.add(new usuario(nom, ape, "0000", dni, "Taller", "taller@center.com", "Tarapoto", us, ps, "MECANICO"));
            System.out.println("✅ Mecánico agregado.");
        } else {
            for (usuario u : usuarios) {
                if (u.getRol().equals("MECANICO")) {
                    System.out.println("👨‍🔧 " + u.getNombre() + " " + u.getApellido() + " | Usuario: " + u.getUsername() + " | Clave: " + u.generarAsteriscos(u.getContrasena().length()));
                }
            }
        }
    }

    private static void eliminarMecanico() {
        vistaconsola.imprimirTitulo("Eliminar Mecánico");
        ArrayList<usuario> usuarios = seguridad.getListaUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            usuario u = usuarios.get(i);
            if (u.getRol().equals("MECANICO")) {
                System.out.println("[" + i + "] DNI: " + u.getDni() + " | " + u.getNombre() + " " + u.getApellido());
            }
        }
        System.out.print("Seleccione el número del mecánico a eliminar: ");
        int idx = lectorconsola.leerEntero();
        if (idx >= 0 && idx < usuarios.size() && usuarios.get(idx).getRol().equals("MECANICO")) {
            usuarios.remove(idx);
            System.out.println("🗑️ Mecánico retirado de las listas.");
        }
    }

    public static ArrayList<cliente> getListaClientes() { return listaClientes; }
}