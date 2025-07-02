/**
* This program creates different types of cyborgs using inheritance
* from the original Cyborg subclass.
*       
* Date Last Modified: 10/7/2024
* @author Nathan Kenney
*
* CS1131, Fall 2024
* Lab Section 3
*/
public class Main {
    public static void main(String[] args) {
        Cyborg basicCyborg = new Cyborg( "Basic-01", "CyborgCorp" );
        Cyborg visionCyborg = new EnhancedVisionCyborg( "VisionX", "OptiTech",
            "Night Vision" );
        Cyborg hackerCyborg = new HackingCyborg( "RedHatGPT", "IBM", 
            "Advanced Computing" );
        basicCyborg.displayInfo();
        basicCyborg.performTask();
        System.out.println();
        visionCyborg.displayInfo();
        visionCyborg.performTask();
        System.out.println();
        hackerCyborg.displayInfo();
        hackerCyborg.performTask();
    }
}
