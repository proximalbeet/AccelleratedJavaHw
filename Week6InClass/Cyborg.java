public class Cyborg {
    protected String model;
    protected String manufacturer;
    public Cyborg(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
    public void displayInfo() {
        System.out.println("Model: " + model);
        System.out.println("Manufacturer: " + manufacturer);
    }
    public void performTask() {
        System.out.println(model + " is performing a basic task.");
    }
}
