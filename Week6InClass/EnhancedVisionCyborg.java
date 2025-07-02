public class EnhancedVisionCyborg extends Cyborg {
    private String visionType;
    public EnhancedVisionCyborg(String model, String manufacturer, String
            visionType) {
        super(model, manufacturer);
        this.visionType = visionType;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Vision Type: " + visionType);
    }
    @Override
    public void performTask() {
        System.out.println(model + " is using " + visionType + " to perform " +
        "a task.");
    }
}
