/**
 * This program creates a cipher table and then encodes and decodes different 
 * strings using a vigenere-cipher.
 *      
 * Date Last Modified: 10/10/2024
 * @Author Nathan Kenney
 *
 * CS1131, Fall 2024
 * Lab Section 3
 *
 * Sources used:
 *
 * https://www.digitalocean.com/community/tutorials/
 * java-char-to-string-to-char-array
 *
 * https://www.geeksforgeeks.org/convert-character-array-to-string-in-java/
 *
 * https://www.baeldung.com/java-character-ascii-value
 *
 * https://www.geeksforgeeks.org/modulo-or-remainder-operator-in-java/
 *
 * https://www.youtube.com/watch?v=DALwBJw5Or4
 */
public class Cipher {
    // INSTANCE VARIABLES
    private char[] keyList; // creates an empty keyList that will be 
                                // initialized in the constructor

    /**
     * Access modifier to update keyList
     * 
     * @param message
     * @return
     */
    public char[] getKeyList() {
        return keyList;
    }

    private char[][] cipherTable; // Creates an empty cipherTable that will be
                                      // initialized in the constructor

    /**
     * Access modifier to update cipherTable
     *
     * @param message
     * @return
     */
    public char[][] getCipherTable() {
        return cipherTable;
    }

    // METHODS
    /**
     * Encodes a given string using a for loop    
     *
     * @param message
     * @return
     */
    public String encode( String message ) {
        String result = "";
        String keyString = new String( keyList );
        message = message.toUpperCase();
        for ( int i = 0; i < message.length(); i++ ) {    
            // Takes the mod of the current index and keystring so that 
            // when it goes over the keystring length it will
            // loop back to the first character
            int keyStrModLen = i % ( keyString.length() );
            // If a space is found in the text thats being encrypted,
            // keep it in the encrypted text
            if ( message.charAt( i ) == ' '
                    || keyString.charAt( keyStrModLen ) == ' ' ) {
                result += " ";
            }
            // Accesses an element of the table[][] by getting both the string
            // message and the string keyString and looping through them by the
            // current index minus the ascii value of 'A'
            else {
                result += ( char ) cipherTable[ ( char ) message.charAt( i )
                    - 'A' ] [ ( char ) keyString.charAt( keyStrModLen ) 
                        - 'A' ];
            }
        }
        System.out.println( "Encoded Text: " + result );
        return result;
    }

    /**
     * Decodes a given string using a series of for loops
     * 
     * @param message
     * @return
     */
    public String decode( String message ) {
        String result = "";
        String keyString = new String( keyList );
        message = message.toUpperCase();

        for ( int i = 0; i < message.length(); i++ ) {
            // Takes the mod of the current index and keystring so that 
            // when it goes over the keystring length it will
            // loop back to the first character
            int keyStrModLen = i % ( keyString.length() );
            // If a space is found in the text thats being encrypted,
            // keep it in the encrypted text
            if ( message.charAt( i ) == ' ' ||
                    keyString.charAt( keyStrModLen ) ==  ' ' ) {
                result += " ";
            }
            else {
                // Turn the current char into an index we use to access the row
                int keyCharIndex = keyString.charAt( keyStrModLen  ) - 'A';
                // Finds the column that matches the decoded char
                for ( int j = 0; j < 26; j++ ) {
                    // Finds the char in the table and turns it 
                    // back into the original text
                    if ( cipherTable [ keyCharIndex ] [ j ] 
                            == message.charAt( i ) ) {
                        result += ( char ) ( j + 'A' );
                        break;
                    }
                }
            }
        }
        System.out.println( "Decoded Text: " + result );
        return result;
    }

    // CONSTRUCTORS
    /**
     * Initializes keyList and creates a cipherTable.      
     * @param code
     * @param key
     */
    public Cipher( char code, String key ) {
        // Initialize keyList
        keyList = key.toUpperCase().toCharArray();

        // Initialize cipherTable
        cipherTable = new char[26][26];
        for ( int row = 0; row < 26; row++ ) {
            for ( int col = 0; col < 26; col++ ) {
                int startPoint = code - 'A';
                int shiftValue = startPoint + row + col;
                int wrappedValue = shiftValue % 26;
                char letter = ( char ) ( wrappedValue + 'A' );
                cipherTable[row][col] = letter;
            }
        }
    } // MAIN (TEST) Method

    /**
     * Main method where code compiles and is tested using the assert keyword.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Testing only works if using VM argument: java -ea Cipher
        Cipher testOne = new Cipher( 'H', "BABBAGE" );
        assert "PHXXF MQYBPKNJ".equals( testOne.encode( "HAPPY BIRTHDAY" ) );
        assert "HAPPY BIRTHDAY".equals( testOne.decode( "PHXXF MQYBPKNJ" ) );
            
        Cipher testTwo = new Cipher( 'Q', "WATERMELLON" );
        assert "YEWNNQGFSC NKAHZ".equals(testTwo.encode( 
            "MONTGOMERY BURNS" ) );
        assert "MONTGOMERY BURNS".equals(testTwo.decode( 
            "YEWNNQGFSC NKAHZ" ) );

    }
} // END OF CLASS --------------------------------------------------------
