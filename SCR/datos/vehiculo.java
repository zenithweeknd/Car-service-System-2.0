package datos;

import java.util.ArrayList;

public class vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private double cilindrada; // Nuevo campo requerido
    private String estado;     // "En espera" o "Atendido"
    private usuario mecanicoAsignado; // Objeto Mecánico asignado
    private ArrayList<String> serviciosRealizados; // Historial de reparaciones
    private double totalPagado;
    private String tipoPago;

    public vehiculo(String placa, String marca, String modelo, int anio, double cilindrada) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.cilindrada = cilindrada;
        this.estado = "En espera"; // Estado inicial por defecto
        this.mecanicoAsignado = null;
        this.serviciosRealizados = new ArrayList<>();
        this.totalPagado = 0.0;
        this.tipoPago = "Ninguno";
    }

    public void mostrarFicha() {
        System.out.println("🚗 Placa: " + placa + " | " + marca + " " + modelo + " (" + anio + ")");
        System.out.println("Cilindrada: " + cilindrada + " cc | Estado: [" + estado + "]");
        if (mecanicoAsignado != null) {
            System.out.println("🔧 Mecánico Asignado: " + mecanicoAsignado.getNombre() + " " + mecanicoAsignado.getApellido());
        } else {
            System.out.println("🔧 Mecánico Asignado: Sin asignar");
        }
        System.out.println("💳 Total Pagado: S/. " + totalPagado + " (Medio: " + tipoPago + ")");
    }

    // Getters y Setters para lógica fluida
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public double getCilindrada() { return cilindrada; }
    public void setCilindrada(double cilindrada) { this.cilindrada = cilindrada; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public usuario getMecanicoAsignado() { return mecanicoAsignado; }
    public void setMecanicoAsignado(usuario mecanicoAsignado) { this.mecanicoAsignado = mecanicoAsignado; }
    public ArrayList<String> getServiciosRealizados() { return serviciosRealizados; }
    public double getTotalPagado() { return totalPagado; }
    public void setTotalPagado(double totalPagado) { this.totalPagado = totalPagado; }
    public String getTipoPago() { return tipoPago; }
    public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }
}