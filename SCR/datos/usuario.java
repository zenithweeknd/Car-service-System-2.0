package datos;

import java.util.ArrayList;

public class usuario extends persona {
    private String username;
    private String contrasena;
    private String rol;
    private ArrayList<String> historialContrasenas; // Registro requerido por el doc

    public usuario(String nombre, String apellido, String telefono, String dni, String direccion, String correo, String ciudad, String username, String contrasena, String rol) {
        super(nombre, apellido, telefono, dni, direccion, correo, ciudad);
        this.username = username;
        this.contrasena = contrasena;
        this.rol = rol;
        this.historialContrasenas = new ArrayList<>();
        this.historialContrasenas.add(contrasena); // Guardamos la primera clave
    }

    @Override
    public void mostrar() {
        System.out.println("Colaborador: " + nombre + " " + apellido + " [Rol: " + rol + "]");
        System.out.println("Usuario: " + username + " | Contraseña: " + generarAsteriscos(contrasena.length()));
    }

    public String generarAsteriscos(int longitud) {
        String asteriscos = "";
        for (int i = 0; i < longitud; i++) {
            asteriscos += "*";
        }
        return asteriscos;
    }

    // Getters y Setters de control
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getContrasena() { return contrasena; }
    
    public void setContrasena(String nuevaContrasena) { 
        this.contrasena = nuevaContrasena; 
        this.historialContrasenas.add(nuevaContrasena); // Historial automático
    }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public ArrayList<String> getHistorialContrasenas() { return historialContrasenas; }
}