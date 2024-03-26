import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("=== Gestión de Curso ===");
            System.out.println("1. Añadir nombres");
            System.out.println("2. Mostrar nombres");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");


            opcion = scanner.nextInt();
            scanner.nextLine();

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
            FileWriter fileWriter = new FileWriter("nombres.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce los nombres separados por comas: ");
            String nombres = scanner.nextLine().replaceAll("\\s", "");
            printWriter.println(nombres);

            printWriter.close();
            System.out.println("Nombres añadidos correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    public static void mostrarNombres() {
        try {
            FileReader fileReader = new FileReader("nombres.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            System.out.println("=== Listado de Nombres ===");
            while ((linea = bufferedReader.readLine()) != null) {
                String[] nombres = linea.split(",");
                for (String nombre : nombres) {
                    System.out.println(nombre);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }
}