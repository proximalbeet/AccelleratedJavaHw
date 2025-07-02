import java.util.Arrays;
public class test {


    public static void main( String[] args ) {
        


    }

    public int findSmallestElement( double [ ] xs ) { // YOUR CODE HERE

    double [ ] minimum = xs[ 0 ];

    for ( int i =0; i < xs.length; i++ ) {
        if ( xs[ i ] < minimum ) {

            return minimum = xs[ i ];

        }
    }

    System.out.println( "The smallest element in this list is " + minimum );


    }
} 
