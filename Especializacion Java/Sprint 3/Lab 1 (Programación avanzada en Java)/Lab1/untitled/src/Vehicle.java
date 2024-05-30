public abstract class Vehicle {
    private String brand;
    private String model;
    private int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters y Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //Métodos
    public void startEngine() {
        System.out.println("El motor del " + brand + " " + model + " del " + year + " ha arrancado.");
    }

    public void accelerate(int speed) {
        System.out.println("El " + brand + " " + model + " del " + year + " está acelerando a " + speed + " km/h.");
    }

    public void brake(int speed) {
        System.out.println("El " + brand + " " + model + " del " + year + " está frenando a " + speed + " km/h.");
    }

    public String toString() {
        return "Marca: " + brand + ", Modelo: " + model + ", Año: " + year;
    }
}
