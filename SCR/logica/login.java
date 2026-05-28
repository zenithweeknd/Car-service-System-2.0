package logica;

import java.util.ArrayList;
import datos.usuario;
import vista.lectorconsola;
import vista.vistaconsola;

public class login {
    // Almacena la referencia al usuario que logre iniciar sesión con éxito
    private static usuario usuarioAutenticado = null;

    // Método que procesa el inicio de sesión comparando los inputs con la lista de usuarios
    public static boolean iniciarSesion(ArrayList<usuario> listaUsuarios) {
        vistaconsola.imprimirTitulo("Inicio de Sesión - Car Center");
        
        System.out.print("👤 Usuario: ");
        String user = lectorconsola.leerTexto();
        
        System.out.print(" Contraseña: ");
        String pass = lectorconsola.leerTexto();

        // Recorremos la lista de usuarios del taller usando un foreach básico
        for (usuario u : listaUsuarios) {
            if (u.getUsername().equals(user) && u.getContrasena().equals(pass)) {
                usuarioAutenticado = u; // Guardamos el usuario activo
                vistaconsola.mostrarMensaje("\n ¡Acceso concedido! Bienvenido: " + u.getNombre() + " (" + u.getRol() + ")");
                return true;
            }
        }

        vistaconsola.mostrarMensaje("\n Error: Usuario o contraseña incorrectos.");
        return false;
    }

    // Permite que otras clases (como el Menú) sepan quién está usando el sistema
    public static usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    // Método para cerrar sesión de forma limpia
    public static void cerrarSesion() {
        usuarioAutenticado = null;
        vistaconsola.mostrarMensaje(" Sesión cerrada con éxito.");
    }
}