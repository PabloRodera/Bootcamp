import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final List<Vehicle> vehiculos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int option = getOption();
            switch (option) {
                case 1:
                    showMenuAdd();
                    break;
                case 2:
                    showMenuShow();
                    break;
                case 3:
                    System.out.println("Programa finalizado.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 3.");
            }
        }
    }
    private static void showMainMenu() {
        System.out.println("Menú principal:");
        System.out.println("1. Añadir vehículos");
        System.out.println("2. Mostrar vehículos");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int getOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();  // Limpiar el buffer
            return -1;  // Opción no válida
        }
    }
    private static void showMenuAdd() {
        System.out.println("Menú añadir vehículo:");
        System.out.println("1. Añadir coche");
        System.out.println("2. Añadir moto");
        System.out.print("Seleccione una opción: ");
        int option = getOption();
        switch (option) {
            case 1:
                addCar();
                break;
            case 2:
                addMotorbike();
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción del 1 al 2.");
        }
    }
    private static void addCar() {
        try {
            scanner.nextLine();  // Limpiar el buffer

            System.out.print("Introduzca la marca del coche: ");
            String brand = scanner.nextLine();

            System.out.print("Introduzca el modelo del coche: ");
            String model = scanner.nextLine();

            System.out.print("Introduzca el año del coche: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Introduzca el número de puertas del coche: ");
            int numberofDoors = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Es descapotable el coche? (si/no): ");
            String text = scanner.nextLine().toLowerCase(); // Convertir la entrada a minúsculas para evitar problemas de capitalización

            boolean isConvertible;
            if (text.equals("si")) {
                isConvertible = true;
            } else if (text.equals("no")) {
                isConvertible = false;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca 'Si' o 'No'.");
                return; // Salir del método si la respuesta no es válida
            }

            vehiculos.add(new Car(brand, model, year, numberofDoors, isConvertible));
            System.out.println("Coche añadido exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Formato de número inválido. Por favor, inténtelo de nuevo.");
        } catch (Exception e) {
            System.out.println("Datos inválidos. Por favor, inténtelo de nuevo.");
        }
    }
    private static void addMotorbike() {
        try {
            scanner.nextLine();  // Limpiar el buffer

            System.out.print("Introduzca la marca de la moto: ");
            String brand = scanner.nextLine();

            System.out.print("Introduzca el modelo de la moto: ");
            String model = scanner.nextLine();

            System.out.print("Introduzca el año de la moto: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Introduzca la cilindrada del motor de la moto: ");
            int engineDisplacement = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Tiene la moto sidecar? (si/no): ");
            String text = scanner.nextLine().toLowerCase(); // Convertir la entrada a minúsculas para evitar problemas de capitalización

            boolean hasSidecar;
            if (text.equals("si")) {
                hasSidecar = true;
            } else if (text.equals("no")) {
                hasSidecar = false;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca 'Si' o 'No'.");
                return; // Salir del método si la respuesta no es válida
            }

            vehiculos.add(new Motorbike(brand, model, year, engineDisplacement, hasSidecar));
            System.out.println("Moto añadida exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Formato de número inválido. Por favor, inténtelo de nuevo.");
        } catch (Exception e) {
            System.out.println("Datos inválidos. Por favor, inténtelo de nuevo.");
        }
    }
    private static void showMenuShow() {
        System.out.println("Menú mostrar vehículos:");
        System.out.println("1. Mostrar todos");
        System.out.println("2. Mostrar por tipo de vehículo");
        System.out.println("3. Mostrar por marca");
        System.out.print("Seleccione una opción: ");
        int option = getOption();
        switch (option) {
            case 1:
                showAllVehicles();
                break;
            case 2:
                showByType();
                break;
            case 3:
                showByBrand();
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción del 1 al 3.");
        }
    }
    private static void showAllVehicles() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("Lista de todos los vehículos:");
            for (Vehicle vehicle : vehiculos) {
                System.out.println(vehicle);
            }
        }
    }
    private static void showByType() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.print("Seleccione el tipo de vehículo (Coche/Moto): ");
            String type = scanner.next().toLowerCase();
            boolean found = false;
            for (Vehicle vehicle : vehiculos) {
                if (type.equals("coche") && vehicle instanceof Car) {
                    System.out.println(vehicle);
                    found = true;
                } else if (type.equals("moto") && vehicle instanceof Motorbike) {
                    System.out.println(vehicle);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No se encontraron vehículos del tipo especificado.");
            }
        }
    }
    private static void showByBrand() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.print("Introduzca la marca a buscar: ");
            String searchBrand = scanner.next();
            boolean found = false;
            for (Vehicle vehicle : vehiculos) {
                if (vehicle.getBrand().equalsIgnoreCase(searchBrand)) {
                    System.out.println(vehicle);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No se encontraron vehículos de la marca especificada.");
            }
        }
    }
}