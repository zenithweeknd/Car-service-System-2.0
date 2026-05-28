package datos;

public class producto {
    private int codigo;
    private String nombre;
    private int stock;
    private double precio;

    public producto(int codigo, String nombre, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public void mostrarProducto() {
        System.out.println("[" + codigo + "] Producto: " + nombre + " | Stock: " + stock + " uds | Precio: S/. " + precio);
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}