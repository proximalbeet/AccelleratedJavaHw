import java.util.Arrays;
/**
 * This program has a bunch of different methods created at the bottom that
 * must run successfully using the test cases at the top of the class.
 *
 * Date Last Modified: 10/13/2024
 * @Author Nathan Kenney
 *
 * CS1131, Fall 2024
 * Lab Section 3
 *
 * Sources used:
 *
 * https://www.geeksforgeeks.org/types-of-exception-in-java-with-examples/
 *
 * https://www.geeksforgeeks.org/java-program-to-check-whether-the-string-
 *     consists-of-special-characters/
 */
public class Week5Lab {
    public static void main( String[] args ) {
        // Our lab class instance
        Week5Lab object = new Week5Lab( );
        // Problem 1: Tests
        System.out.println( "PROBLEM 1 TESTS" );
        System.out.println( "===============" );
        // Test 1
        String secret = "SECRET";
        String encrypted = object.encrypt( secret );
        String expected = "TFDSFU";
        if ( encrypted.equals( expected ) ) {
          System.out.println( "\tTest[01] PASSED" );
        } else {
           System.out.println( "\tTest[01] FAILED: Problem 1: '" + encrypted +
            "' != '" + expected + "'" );
        }
        // Test 2
        String decrypted = object.decrypt( encrypted );
        if ( secret.equals( decrypted ) ) {
            System.out.println( "\tTest[02] PASSED" );
        } else {
            System.out.println( "\tTest[02] FAILED: Problem 1: '" + secret +
            "' != '" + decrypted + "'" );
        }
        // Test 3
        int shift = 5;
        String encryptedBy = object.encrypt( secret, shift );
        String expectedBy = "XJHWJY";
        if ( encryptedBy.equals( expectedBy ) ) {
            System.out.println( "\tTest[03] PASSED" );
        } else {
            System.out.println( "\tTest[03] FAILED: Problem 1: '" +
            encryptedBy + "' != '" + expectedBy + "'" );
        }
        // Test 4
        String decryptedBy = object.decrypt( encryptedBy, shift );
        if ( secret.equals( decryptedBy ) ) {
            System.out.println( "\tTest[04] PASSED" );
        } else {
            System.out.println( "\tTest[04] FAILED: Problem 1: '" + secret +
            "' != '" + decryptedBy + "'" );
        }
        // Problem 2: Tests
        System.out.println( "PROBLEM 2 TESTS" );
        System.out.println( "===============" );
        // Test 5
        double[] array = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0 };
        String arrayStr = object.arrayToString( array );
        String expectedArray = "[ 0.0 | 1.0 | 2.0 | 3.0 | 4.0 | 5.0 ]";
        if ( arrayStr.equals( expectedArray ) ) {
            System.out.println( "\tTest[05] PASSED" );
        } else {
            System.out.println( "\tTest[05] FAILED: Problem 2: '" + arrayStr +
            "' != '" + expectedArray + "'" );
        }
        // Test 6
        double[] arrayRev = { 5.0, 4.0, 3.0, 2.0, 1.0, 0.0 };
        String arrayRevStr = object.arrayToStringRev( arrayRev );
        if ( arrayRevStr.equals( expectedArray ) ) {
            System.out.println( "\tTest[06] PASSED" );
        } else {
            System.out.println( "\tTest[06] FAILED: Problem 2: '" +
            arrayRevStr + "' != '" + expectedArray + "'" );
        }
        // Test 7
        int[][] array2D = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        String array2DStr = object.array2DToString( array2D );
        String expectedArray2D = "[ [ 0 | 1 | 2 ] | [ 3 | 4 | 5 ] " +
        "| [ 6 | 7 | 8 ] ]";
        if ( array2DStr.equals( expectedArray2D ) ) {
            System.out.println( "\tTest[07] PASSED" );
        } else {
            System.out.println( "\tTest[07] FAILED: Problem 2: '" +
            array2DStr + "' != '" + expectedArray2D + "'" );
        }
        // Problem 3: Tests
        System.out.println( "PROBLEM 3 TESTS" );
        System.out.println( "===============" );
        // Test 8
        int[] arrayInts = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] arrayIntsCubed = object.cube( arrayInts );
        int[] arrayIntsCubedExpected = {
        0, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000
        };
        if ( Arrays.equals( arrayIntsCubed, arrayIntsCubedExpected ) ) {
            System.out.println( "\tTest[08] PASSED" );
        } else {
            System.out.println( "\tTest[08] FAILED: Problem 3: '" +
            Arrays.toString( arrayIntsCubed ) + "' != '" +
            Arrays.toString( arrayIntsCubedExpected ) );
        }
        // Test 9
        double[] arrayDouble = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] arrayDoubleCubed = object.cube( arrayDouble );
        double[] arrayDoubleCubedExpected = { 0.0, 1.0, 8.0, 27.0, 64.0 };
        if ( Arrays.equals( arrayDoubleCubed, arrayDoubleCubedExpected ) ) {
            System.out.println( "\tTest[09] PASSED" );
        } else {
            System.out.println( "\tTest[09] FAILED: Problem 3: '" +
            Arrays.toString( arrayDoubleCubed ) + "' != '" +
            Arrays.toString( arrayDoubleCubedExpected ) );
        }
        // Test 10
        int[] arrayIntSquared = object.square( arrayInts );
        int[] arrayIntSquaredExpected = {
        0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100
        };
        if ( Arrays.equals( arrayIntSquared, arrayIntSquaredExpected ) ) {
            System.out.println( "\tTest[10] PASSED" );
        } else {
            System.out.println( "\tTest[10] FAILED: Problem 3: '" +
            Arrays.toString( arrayIntSquared ) + "' != '" +
            Arrays.toString( arrayIntSquaredExpected ) );
        }
    }
    public String encrypt( String s ) {
        String out = "";
        for ( char c : s.toCharArray( ) ) {
            if ( Character.isLetter( c ) ) {
                out += ( char ) ( c + 1 ); 
            }
            else {
                throw new IllegalArgumentException(
                    "Can only contain letters." );
            }

        }
            return out;
    }
    public String encrypt( String s, int shift ) {
        // Your Problem 1 Modify code here
        String out = "";
        for ( char c : s.toCharArray( ) ) {
            if ( Character.isLetter( c ) ) {
                out += ( char ) ( c + shift ); 
            }
            else {
                throw new IllegalArgumentException(
                    "Can only contain letters." );
            }
        }
        return out;
    }
    public String decrypt( String s ) {
        // Your problem 1 Make code here
        String out = "";
        for ( char c : s.toCharArray( ) ) {
            out += ( char ) ( c - 1 );
        }
        return out;
    }
    public String decrypt( String s, int shift ) {
        // Your problem 1 Make code here
        String out = "";
        for ( char c : s.toCharArray( ) ) {
            out += ( char ) ( c - shift );
        }
        return out;
    }
    public String arrayToString( double[] array ) {
        String out = "";
        out += "[ ";
        for ( int i = 0; i < array.length; i++ ) {
            out += array[ i ];
            if ( i != array.length - 1 ) {
                out += " | ";
            }
        }
        out += " ]";
        return out;
        }
    public String arrayToStringRev( double[] array ) {
        // Your Problem 2 Modify code here
        String out = "";
        out += "[ ";
        for ( int i = 0; i < array.length; i++ ) {
            out += array[ array.length - 1 - i ];
            if ( i != array.length - 1 ) {
                out += " | ";
            }
        }
        out += " ]";
        return out;
    }
    public String array2DToString( int[][] array ) {
        // Your Problem 2 Make code here
        String out = "[ ";
        for ( int row = 0; row < array.length; row++ ) {
            out += "[ ";
            for ( int col = 0; col < array[ row ].length; col++ ) {
                out += array[ row ] [ col ];
                if ( col != array.length - 1 ) {
                    out += " | ";
                }
            }
            out += " ]";
            if ( row != array.length - 1 ) {
                out += " | ";
            }
        }
        out += " ]";
        return out;
    }
    public int[] cube( int[] array ) {
        int[] newArray = new int[ array.length ];
        for ( int i = 0; i < array.length; i++ ) {
            if ( array[ i ] >= 0 ) {
                newArray[ i ] = ( int ) Math.pow( array[ i ], 3 );
            }
            else {
                throw new IllegalArgumentException( 
                    "Must be larger than zero" );
            }
        }
        return newArray;
    }
    public double[] cube( double[] array ) {
        // Your Problem 3 Modify code here
        double[] newArray = new double[ array.length ];
        for ( int k = 0; k < array.length; k++ ) {
            if ( array[ k ] >= 0 ) {
                newArray[ k ] = Math.pow( array[ k ], 3.0 );
            }
            else {
                throw new IllegalArgumentException( 
                    "Must be larger than zero" );
            }
        }
        return newArray;

    }
    public int[] square( int[] array ) {
        // Your Problem 3 Make code here
        int[] newArray = new int[ array.length ];
        for ( int i = 0; i < array.length; i++ ) {
            newArray[ i ] = ( int ) Math.pow( array[ i ], 2 );
        }
        return newArray;
    }
}
