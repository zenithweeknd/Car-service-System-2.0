import java.util.ArrayList;
import datos.usuario;
import datos.cliente;
import datos.vehiculo;
import logica.seguridad;
import logica.configuracion;
import logica.transacciones;
import logica.login;
import logica.menu;
import vista.vistaconsola;

public class main {
    public static void main(String[] args) {
        ArrayList<usuario> usuarios = seguridad.getListaUsuarios();
        ArrayList<cliente> clientes = configuracion.getListaClientes();
        ArrayList<vehiculo> vehiculos = transacciones.getListaVehiculos();

        // Inicializamos con la nueva estructura de herencia completa
        usuarios.add(new usuario("Brian", "Apaza", "945123456", "77441122", "Jr. Libertad", "brian@mail.com", "Tarapoto", "brian", "123", "ADMINISTRADOR"));
        usuarios.add(new usuario("Carlos", "Gómez", "945987654", "44556633", "Av. Lima", "carlos@mail.com", "Tarapoto", "carlos", "456", "MECANICO"));
        
        clientes.add(new cliente("Juan", "Pérez", "920112233", "44556677", "Jr. España 450", "juan@mail.com", "Tarapoto"));
        vehiculos.add(new vehiculo("MX-4512", "Toyota", "Hilux", 2022, 2500.0));

        boolean ejecucion = true;
        while (ejecucion) {
            boolean acceso = login.iniciarSesion(usuarios);
            if (acceso) {
                menu.mostrarMenuPrincipal();
            } else {
                vistaconsola.mostrarMensaje("🔄 Credenciales incorrectas. Intente de nuevo.\n");
            }
        }
    }
}