public class Car extends Vehicle {
    private int numberOfDoors;
    private boolean isConvertible;

    public Car(String brand, String model, int year, int numberOfDoors, boolean isConvertible){
        super(brand,model,year);
        this.numberOfDoors = numberOfDoors;
        this.isConvertible = isConvertible;
    }

    // Getter y Setter
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isConvertible() {
        return isConvertible;
    }

    public void setIsConvertible(boolean isConvertible) {
        this.isConvertible = isConvertible;
    }

    // Métodos específicos
    public void honk() {
        System.out.println("El coche " + getBrand() + getModel() + " del " + getYear() + " está tocando la bocina.");
    }

    public void putSeatbelt() {
        System.out.println("El coche " + getBrand() + " " + getModel() + " del " + getYear() + " tiene puesto del cinturón.");
    }

    public void drift() {
        System.out.println("El coche " + getBrand() + " " + getModel() + " del " + getYear() + " está haciendo drift.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Número de puertas: " + numberOfDoors + ", Es descapotable: " + (isConvertible ? "Sí" : "No");
    }
}
