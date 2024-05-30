public class Motorbike extends Vehicle {
    private int engineDisplacement;
    private boolean hasSidecar;

    public Motorbike (String brand, String model, int year, int engineDisplacement, boolean hasSidecar) {
        super(brand, model, year);
        this.engineDisplacement = engineDisplacement;
        this.hasSidecar = hasSidecar;
    }

    // Getter y Setter
    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public boolean getHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    // Métodos específicos
    public void wheelie() {
        System.out.println("La moto " + getBrand() + " " + getModel() + " del " + getYear() + " está haciendo un caballito.");
    }

    public void putHelmet() {
        System.out.println("El casco está puesto para la moto " + getBrand() + " " + getModel() + " del " + getYear() + ".");
    }

    @Override
    public String toString() {
        return super.toString() + ", Cilindrada del motor: " + engineDisplacement + ", Tiene sidecar: " + (hasSidecar ? "Sí" : "No");
    }
}
