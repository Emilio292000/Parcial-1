import java.util.*;

public class Main {

    private static Map<String, Integer> inventario = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n == Sistema de Gestión de Inventario == ");
            System.out.println("1. Registrar nuevo producto.");
            System.out.println("2. Mostrar inventario completo.");
            System.out.println("3. Realizar una venta.");
            System.out.println("4. Duplicar inventario de producto agotado.");
            System.out.println("5. Salir.");
            System.out.print("Opción: ");

            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERR::Opción inválida.");
                scanner.next();
                continue;
            }

            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    Venta();
                    break;
                case 4:
                    duplicarInventario();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void registrarProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        int cantidad = 0;
        while (true) {
            System.out.print("Ingrese la cantidad inicial: ");
            try {
                cantidad = scanner.nextInt();
                if (cantidad >= 0) {
                    break;
                } else {
                    System.out.println("La cantidad no puede ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.next();
            }
        }
        scanner.nextLine();

        inventario.put(nombre, cantidad);
        System.out.println("Producto registrado exitosamente.");
    }

    private static void mostrarInventario() {
        System.out.println("\n=== Inventario Disponible ===");
        if (inventario.isEmpty()) {
            System.out.println("No hay productos registrados en el inventario.");
        } else {
            for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
                System.out.println("Producto: " + entry.getKey() + " | Cantidad: " + entry.getValue());
            }
        }
    }

    private static void Venta() {
        System.out.print("Ingrese el nombre del producto a vender: ");
        String nombre = scanner.nextLine();

        if (!inventario.containsKey(nombre)) {
            System.out.println("El producto no existe en el inventario.");
            return;
        }

        int cantidad = inventario.get(nombre);
        if (cantidad == 0) {
            System.out.println("No hay stock disponible para este producto.");
            return;
        }

        int cantidadAVender = 0;
        while (true) {
            System.out.print("Ingrese la cantidad a vender: ");
            try {
                cantidadAVender = scanner.nextInt();
                if (cantidadAVender > 0 && cantidadAVender <= cantidad) {
                    break;
                } else if (cantidadAVender <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                } else {
                    System.out.println("No hay suficiente stock para completar la venta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.next();
            }
        }
        scanner.nextLine();

        inventario.put(nombre, cantidad - cantidadAVender);
        System.out.println("Venta realizada exitosamente.");
    }

    private static void duplicarInventario() {
        System.out.print("Ingrese el nombre del producto a duplicar: ");
        String nombre = scanner.nextLine();

        if (!inventario.containsKey(nombre)) {
            System.out.println("El producto no existe en el inventario.");
            return;
        }

        int cantidad = inventario.get(nombre);
        if (cantidad > 0) {
            System.out.println("El producto aún tiene stock disponible. No se puede duplicar.");
        } else {
            inventario.put(nombre, cantidad * 2 + 10);
            System.out.println("El inventario del producto ha sido duplicado exitosamente.");
        }
    }
}