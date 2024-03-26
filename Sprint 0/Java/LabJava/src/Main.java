import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar cabecera y opciones
            System.out.println("=== Gestión de Curso ===");
            System.out.println("1. Añadir nombres");
            System.out.println("2. Mostrar nombres");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            // Leer la opción seleccionada
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            // El switch llama al método indicado por consola
            switch (opcion) {
                case 1:
                    anyadirNombres();
                    break;
                case 2:
                    mostrarNombres();
                    break;
                case 3:
                    System.out.println("¡Ha finalizado el programa!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    public static void anyadirNombres() {
        try {
            FileWriter fileWriter = new FileWriter("nombres.txt");  // Abre el archivo "nombres.txt" para escribir
            PrintWriter printWriter = new PrintWriter(fileWriter);  // Crea un objeto PrintWriter para escribir en el archivo

            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce los nombres separados por comas: ");
            String nombres = scanner.nextLine().replaceAll("\\s", "");  // Lee los nombres del usuario y elimina espacios
            printWriter.println(nombres);   // Escribe los nombres en el archivo

            printWriter.close();    // Cierra el PrintWriter
            System.out.println("Nombres añadidos correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo."); // Maneja errores de escritura en el archivo
            e.printStackTrace();    // Imprime el rastreo de la pila del error
        }
    }

    public static void mostrarNombres() {
        try {
            FileReader fileReader = new FileReader("nombres.txt");  // Abre el archivo "nombres.txt" para lectura
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Crea un objeto BufferedReader para leer el archivo

            String linea;
            System.out.println("=== Listado de Nombres ===");
            while ((linea = bufferedReader.readLine()) != null) {  // Lee cada línea del archivo
                String[] nombres = linea.split(",");    // Separa los nombres utilizando comas como delimitador
                for (String nombre : nombres) {
                    System.out.println(nombre); // Muestra cada nombre en una línea
                }
            }

            bufferedReader.close(); // Cierra el BufferedReader
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");    // Maneja errores de lectura del archivo
            e.printStackTrace();    // Imprime el rastreo de la pila del error
        }
    }
}