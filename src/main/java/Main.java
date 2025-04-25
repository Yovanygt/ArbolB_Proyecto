import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Paso 1: Preguntar el grado del árbol
        int t;
        do {
            System.out.println("Ingrese el grado del arbol B (debe ser mayor o igual a 2):");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido:");
                scanner.next();
            }
            t = scanner.nextInt();
            if (t < 2) {
                System.out.println("El grado debe ser mayor o igual a 2.");
            }
        } while (t < 2);

        ArbolB arbol = new ArbolB(t);

        // Paso 2: Preguntar cuántos datos desea ingresar y leerlos
        System.out.println("¿Cuantos datos desea ingresar?");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número valido:");
            scanner.next();
        }
        int numDatos = scanner.nextInt();

        if (numDatos > 0) {
            System.out.println("Ingrese los " + numDatos + " datos (numeros enteros):");
            for (int i = 0; i < numDatos; i++) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un numero entero valido:");
                    scanner.next();
                }
                int dato = scanner.nextInt();
                arbol.insertar(dato);
                System.out.println("Dato " + dato + " insertado.");
            }
        } else {
            System.out.println("No se insertaran datos.");
        }

        // Paso 3: Menú interactivo para buscar, eliminar o salir
        int opcion;
        do {
            System.out.println("\n--- Menu del Arbol B ---");
            System.out.println("1. Buscar un nodo");
            System.out.println("2. Eliminar un nodo");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opcion (1-3):");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un numero valido (1-3):");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: // Buscar un nodo
                    System.out.println("Ingrese el nodo a buscar:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, ingrese un numero entero valido:");
                        scanner.next();
                    }
                    int claveBuscar = scanner.nextInt();
                    NodoB resultado = arbol.buscar(claveBuscar);
                    if (resultado != null) {
                        System.out.println("nodo " + claveBuscar + " encontrada en el arbol.");
                    } else {
                        System.out.println("nodo " + claveBuscar + " no encontrada en el arbol.");
                    }
                    break;

                case 2: // Eliminar un nodo
                    System.out.println("Ingrese la clave a eliminar:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, ingrese un numero entero valido:");
                        scanner.next();
                    }
                    int claveEliminar = scanner.nextInt();
                    arbol.eliminar(claveEliminar);
                    System.out.println("Se ha  eliminaDO EL NODO " + claveEliminar + ".");
                    break;

                case 3: // Salir
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 3.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}