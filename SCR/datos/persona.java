package datos;

public abstract class persona {
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String dni;
    protected String direccion;
    protected String correo;
    protected String ciudad;

    public persona(String nombre, String apellido, String telefono, String dni, String direccion, String correo, String ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
        this.correo = correo;
        this.ciudad = ciudad;
    }

    public abstract void mostrar();

    // Getters y Setters necesarios para las ediciones
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}