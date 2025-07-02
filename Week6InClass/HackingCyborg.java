public class HackingCyborg extends Cyborg {
    private String hackingType;
    public HackingCyborg( String model, String manufacturer, 
            String hackingType ) {
        super(model, manufacturer);
        this.hackingType = hackingType;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hacking Type: " + hackingType);
    }
    @Override
    public void performTask() {
        System.out.println(model + " is using " + hackingType + 
            " to perform " + "a task.");
    }
}
