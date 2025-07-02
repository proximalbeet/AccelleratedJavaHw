import java.util.Objects;

/**
 *This program calculates all possible IceCream combinations.
 *
 *Date Last Modified: 10/12/2024
 *
 *@author Nathan Kenney
 *
 *CS1131, Fall 2024
 */

// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html

public class IceCream {
    //Variables for flavors
    public String[] flavors = { "", "Chocolate", "Vanilla", "Strawberry" };
    public float[] priceOfFlavors = { 0.00f, 1.75f, 1.15f, 1.15f };

    //Variables for toppings
    public String[] toppings = { "", "Sprinkles", "Whipped Cream", 
        "Chocolate Chip", "Sprinkles and Whipped Cream",
            "Sprinkles and Chocolate Chip", "Whipped Cream and Chocolate Chip",
            "Sprinkles, Whipped Cream, and Chocolate Chip" };

    public float[] priceOfToppings = { 0.00f, 0.15f, 0.35f, 0.40f, 0.50f, 
        0.55f, 0.55f, 0.90f };

    //Main method
    public static void main( String [ ] args ) {
        IceCream obj = new IceCream( );
        long count = obj.printMenu( );
        assert count == 32;
    }

    // gets every combination of toppings and ice cream without having more
    // than one flavor of ice cream in a combo
    public long printMenu() {
        int itemInList = 0;
            for ( int i = 0; i < flavors.length; i++ ) {
                for ( int j = 0; j < toppings.length; j++ ) {
                    float totalCost = 0.00f;
                    String b = "";

                    b += itemInList;
                    b += " ";

                    totalCost += priceOfFlavors[ i ];
                    if ( flavors[ i ] != "" ) {
                        b += flavors[ i ];
                        b += " ";
                    }

                    totalCost += priceOfToppings[ j ];
                    if ( toppings[ j ] != "" ) {
                        b += toppings[ j ];
                        b += " ";
                    }

                    b += "$";
                    b += totalCost;

                    System.out.println( b );
                    ++itemInList;
                }
            }
        return itemInList;
    }
}
