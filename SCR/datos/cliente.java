package datos;

public class cliente extends persona {

    public cliente(String nombre, String apellido, String telefono, String dni, String direccion, String correo, String city) {
        super(nombre, apellido, telefono, dni, direccion, correo, city);
    }

    @Override
    public void mostrar() {
        System.out.println("DNI: " + dni + " | Cliente: " + nombre + " " + apellido);
        System.out.println("Dirección: " + direccion + " | Correo: " + correo);
        System.out.println("Ciudad: " + ciudad + " | Teléfono: " + telefono);
    }
}