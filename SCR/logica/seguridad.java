package logica;

import java.util.ArrayList;
import datos.usuario;
import vista.lectorconsola;
import vista.vistaconsola;

public class seguridad {
    private static ArrayList<usuario> listaUsuarios = new ArrayList<>();

    public static void mostrarMenuSeguridad() {
        int opcion = -1;
        while (opcion != 5) {
            vistaconsola.imprimirTitulo("Módulo de Seguridad");
            System.out.println("[1]  Crear / Registrar Usuario");
            System.out.println("[2]  Listar Usuarios");
            System.out.println("[3]  Modificar / Editar Usuario");
            System.out.println("[4]  Cambiar, Ver e Historial de Contraseñas");
            System.out.println("[5] ↩ Volver al Menú Principal");
            System.out.print("\n Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 1) { registrarUsuario(); } 
            else if (opcion == 2) { listarUsuarios(); } 
            else if (opcion == 3) { editarUsuario(); } 
            else if (opcion == 4) { gestionarContrasenas(); } 
            else if (opcion == 5) { vistaconsola.mostrarMensaje("Regresando..."); } 
            else { vistaconsola.mostrarMensaje("⚠️ Opción inválida."); }
        }
    }

    private static void registrarUsuario() {
        vistaconsola.imprimirTitulo("Registrar Colaborador");
        System.out.print("Nombre: "); String nom = lectorconsola.leerTexto();
        System.out.print("Apellido: "); String ape = lectorconsola.leerTexto();
        System.out.print("Teléfono: "); String tel = lectorconsola.leerTexto();
        System.out.print("DNI: "); String dni = lectorconsola.leerTexto();
        System.out.print("Dirección: "); String dir = lectorconsola.leerTexto();
        System.out.print("Correo: "); String cor = lectorconsola.leerTexto();
        System.out.print("Ciudad: "); String ciu = lectorconsola.leerTexto();
        System.out.print("Nombre de Usuario: "); String user = lectorconsola.leerTexto();
        System.out.print("Contraseña: "); String pass = lectorconsola.leerTexto();
        System.out.print("Rol (ADMINISTRADOR/MECANICO/RECEPCIONISTA): "); String rol = lectorconsola.leerTexto().toUpperCase();

        listaUsuarios.add(new usuario(nom, ape, tel, dni, dir, cor, ciu, user, pass, rol));
        vistaconsola.mostrarMensaje("✅ Usuario creado con éxito.");
    }

    private static void listarUsuarios() {
        vistaconsola.imprimirTitulo("Personal Registrado");
        if (listaUsuarios.isEmpty()) {
            System.out.println("📭 No hay usuarios registrados.");
        } else {
            for (usuario u : listaUsuarios) {
                u.mostrar();
                System.out.println("----------------------------------------");
            }
        }
    }

    private static void editarUsuario() {
        vistaconsola.imprimirTitulo("Editar Usuario");
        System.out.print("Ingrese el DNI del usuario a modificar: ");
        String dni = lectorconsola.leerTexto();
        usuario u = buscarUsuarioPorDni(dni);

        if (u != null) {
            System.out.print("Nuevo Nombre (" + u.getNombre() + "): "); u.setNombre(lectorconsola.leerTexto());
            System.out.print("Nuevo Apellido (" + u.getApellido() + "): "); u.setApellido(lectorconsola.leerTexto());
            System.out.print("Nuevo Rol (" + u.getRol() + "): "); u.setRol(lectorconsola.leerTexto().toUpperCase());
            vistaconsola.mostrarMensaje("✅ Datos actualizados.");
        } else {
            vistaconsola.mostrarMensaje("❌ Usuario no encontrado.");
        }
    }

    private static void gestionarContrasenas() {
        vistaconsola.imprimirTitulo("Gestión de Contraseñas Seguras");
        if (listaUsuarios.isEmpty()) {
            System.out.println("📭 No hay usuarios registrados.");
            return;
        }

        // Mostrar listado numerado con claves ocultas con asteriscos (Punto 5 del doc)
        for (int i = 0; i < listaUsuarios.size(); i++) {
            usuario u = listaUsuarios.get(i);
            System.out.println("[" + i + "] " + u.getNombre() + " " + u.getApellido() + " | Rol: " + u.getRol() + " | Clave: " + u.generarAsteriscos(u.getContrasena().length()));
        }

        System.out.print("\nSeleccione el número del usuario para administrar clave: ");
        int indice = lectorconsola.leerEntero();

        if (indice >= 0 && indice < listaUsuarios.size()) {
            usuario u = listaUsuarios.get(indice);
            System.out.println("\n🔑 Contraseña Actual Revelada: " + u.getContrasena());
            System.out.println("📜 Historial de claves usadas: " + u.getHistorialContrasenas());
            
            System.out.print("\n¿Desea cambiar la contraseña? (SI/NO): ");
            if (lectorconsola.leerTexto().equalsIgnoreCase("SI")) {
                System.out.print("Ingrese nueva contraseña: ");
                String nueva = lectorconsola.leerTexto();
                u.setContrasena(nueva);
                vistaconsola.mostrarMensaje("✅ Contraseña cambiada.");
            }
        } else {
            vistaconsola.mostrarMensaje("⚠️ Índice inválido.");
        }
    }

    public static usuario buscarUsuarioPorDni(String dni) {
        for (usuario u : listaUsuarios) {
            if (u.getDni().equals(dni)) return u;
        }
        return null;
    }

    public static ArrayList<usuario> getListaUsuarios() { return listaUsuarios; }
}