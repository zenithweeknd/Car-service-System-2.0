package logica;

import java.util.ArrayList;
import datos.vehiculo;
import datos.usuario;
import datos.producto;
import vista.lectorconsola;
import vista.vistaconsola;

public class transacciones {
    private static ArrayList<vehiculo> listaVehiculos = new ArrayList<>();
    private static ArrayList<producto> inventario = new ArrayList<>();

    public static void mostrarMenuTransacciones() {
        int opcion = -1;
        while (opcion != 4) {
            vistaconsola.imprimirTitulo("Módulo Operativo de Transacciones");
            System.out.println("[1] 🚗 Gestión de Vehículos (Registrar, Modificar, Asignar, Listar, Eliminar)");
            System.out.println("[2] 💳 Caja - Procesar Registro de Pagos");
            System.out.println("[3] 📦 Almacén - Control de Productos");
            System.out.println("[4] ↩️ Volver al Menú Principal");
            System.out.print("\n👉 Seleccione una opción: ");
            
            opcion = lectorconsola.leerEntero();

            if (opcion == 1) { menuVehiculos(); } 
            else if (opcion == 2) { registrarPago(); } 
            else if (opcion == 3) { menuProductos(); } 
            else if (opcion == 4) { vistaconsola.mostrarMensaje("Regresando..."); }
        }
    }

    private static void menuVehiculos() {
        vistaconsola.imprimirTitulo("Sub-Menú Vehículos");
        System.out.println("[1] Registrar | [2] Modificar | [3] Asignar Mecánico | [4] Listar | [5] Eliminar");
        int sub = lectorconsola.leerEntero();

        if (sub == 1) {
            System.out.print("Placa: "); String pl = lectorconsola.leerTexto().toUpperCase();
            System.out.println("Marcas Disponibles:");
            for (int i=0; i<configuracion.MARCAS.length; i++) System.out.println("["+i+"] "+configuracion.MARCAS[i]);
            System.out.print("Seleccione Marca (Número): "); int mIdx = lectorconsola.leerEntero();
            String marca = (mIdx>=0 && mIdx<configuracion.MARCAS.length) ? configuracion.MARCAS[mIdx] : "Otros";
            System.out.print("Modelo: "); String mod = lectorconsola.leerTexto();
            System.out.print("Año: "); int an = lectorconsola.leerEntero();
            System.out.print("Cilindrada (cc): "); double cil = lectorconsola.leerDecimal();
            listaVehiculos.add(new vehiculo(pl, marca, mod, an, cil));
            System.out.println("✅ Vehículo guardado.");
        }
        else if (sub == 2) {
            System.out.print("Ingrese Placa a modificar: "); String pl = lectorconsola.leerTexto().toUpperCase();
            vehiculo v = buscarVehiculo(pl);
            if (v != null) {
                System.out.print("Nueva Placa: "); v.setPlaca(lectorconsola.leerTexto().toUpperCase());
                System.out.print("Nuevo Modelo: "); v.setModelo(lectorconsola.leerTexto());
                System.out.print("Nueva Cilindrada: "); v.setCilindrada(lectorconsola.leerDecimal());
                System.out.println("✅ Modificado.");
            }
        }
        else if (sub == 3) {
            System.out.print("Placa del auto: "); String pl = lectorconsola.leerTexto().toUpperCase();
            vehiculo v = buscarVehiculo(pl);
            if (v != null) {
                ArrayList<usuario> users = seguridad.getListaUsuarios();
                System.out.println("Mecánicos Disponibles:");
                for (int i=0; i<users.size(); i++) {
                    if (users.get(i).getRol().equals("MECANICO")) {
                        System.out.println("["+i+"] "+users.get(i).getNombre()+" "+users.get(i).getApellido());
                    }
                }
                System.out.print("Seleccione número de mecánico: "); int idx = lectorconsola.leerEntero();
                if (idx>=0 && idx<users.size() && users.get(idx).getRol().equals("MECANICO")) {
                    v.setMecanicoAsignado(users.get(idx));
                    System.out.println("✅ Mecánico asignado.");
                }
            }
        }
        else if (sub == 4) {
            for (vehiculo v : listaVehiculos) { v.mostrarFicha(); System.out.println("---"); }
        }
        else if (sub == 5) {
            System.out.print("Placa a borrar: "); String pl = lectorconsola.leerTexto().toUpperCase();
            for (int i=0; i<listaVehiculos.size(); i++) {
                if (listaVehiculos.get(i).getPlaca().equals(pl)) {
                    listaVehiculos.remove(i);
                    System.out.println("🗑️ Registro de vehículo borrado.");
                    return;
                }
            }
        }
    }

    private static void registrarPago() {
        vistaconsola.imprimirTitulo("REGISTRO DE CAJA Y FACTURACIÓN");
        System.out.print("Ingrese Placa del vehículo: ");
        String pl = lectorconsola.leerTexto().toUpperCase();
        vehiculo v = buscarVehiculo(pl);

        if (v != null) {
            System.out.println("Seleccione el Tipo de Servicio realizado:");
            for (int i=0; i<configuracion.SERVICIOS.length; i++) {
                System.out.println("["+i+"] "+configuracion.SERVICIOS[i]+" (S/. "+configuracion.PRECIOS[i]+")");
            }
            System.out.print("Opción: "); int sIdx = lectorconsola.leerEntero();
            if (sIdx>=0 && sIdx<configuracion.SERVICIOS.length) {
                double precio = configuracion.PRECIOS[sIdx];
                v.getServiciosRealizados().add(configuracion.SERVICIOS[sIdx]);
                
                System.out.println("\n[Monto a Pagar: S/. " + precio + "]");
                System.out.print("Seleccione Medio de Pago (YAPE, PLIN, CREDITO, DEBITO, EFECTIVO): ");
                String medio = lectorconsola.leerTexto().toUpperCase();
                
                v.setTotalPagado(precio);
                v.setTipoPago(medio);
                v.setEstado("Atendido");
                System.out.println("✅ Pago Procesado. El vehículo ahora está [Atendido].");
            }
        } else {
            System.out.println("❌ Vehículo no encontrado.");
        }
    }

    private static void menuProductos() {
        vistaconsola.imprimirTitulo("Almacén de Repuestos");
        System.out.println("[1] Registrar Producto | [2] Modificar | [3] Eliminar");
        int sub = lectorconsola.leerEntero();
        if (sub == 1) {
            System.out.print("Código entero: "); int cod = lectorconsola.leerEntero();
            System.out.print("Descripción/Nombre: "); String nom = lectorconsola.leerTexto();
            System.out.print("Stock Inicial: "); int st = lectorconsola.leerEntero();
            System.out.print("Precio Unitario: "); double pr = lectorconsola.leerDecimal();
            inventario.add(new producto(cod, nom, st, pr));
            System.out.println("✅ Producto en stock.");
        }
        else if (sub == 2) {
            System.out.print("Código del producto: "); int cod = lectorconsola.leerEntero();
            for (producto p : inventario) {
                if (p.getCodigo() == cod) {
                    System.out.print("Nuevo Stock: "); p.setStock(lectorconsola.leerEntero());
                    System.out.print("Nuevo Precio: "); p.setPrecio(lectorconsola.leerDecimal());
                    System.out.println("✅ Modificado.");
                }
            }
        }
        else if (sub == 3) {
            System.out.print("Código a eliminar: "); int cod = lectorconsola.leerEntero();
            for (int i=0; i<inventario.size(); i++) {
                if (inventario.get(i).getCodigo() == cod) { inventario.remove(i); System.out.println("🗑️ Borrado."); return; }
            }
        }
    }

    public static vehiculo buscarVehiculo(String placa) {
        for (vehiculo v : listaVehiculos) { if (v.getPlaca().equals(placa)) return v; }
        return null;
    }

    public static ArrayList<vehiculo> getListaVehiculos() { return listaVehiculos; }
    public static ArrayList<producto> getInventario() { return inventario; }
}