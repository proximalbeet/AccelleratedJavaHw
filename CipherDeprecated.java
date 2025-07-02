import java.util.Arrays;
/**
* CLASS DESCRIPTION COMMENT
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
    private char [ ] keyList; // VARIABLE DESCRIPTION COMMENT
    /**
    * METHOD DESCRIPTION COMMENT
    * @param message
    * @return
    */
    public char [ ] getKeyList( ) {
        return keyList;
    }

    private char [ ][ ] cipherTable; // VARIABLE DESCRIPTION COMMENT
    /**
    * METHOD DESCRIPTION COMMENT
    * @param message
    * @return
    */
    public char [ ][ ] getCipherTable( ) {
        return cipherTable;
    }
    // METHODS
    /**
    * METHOD DESCRIPTION COMMENT
    * @param message
    * @return
    */
    public String encode( String message ) {
        String result = "";
        //int[][] cipherTableNew = cipherTable;
        String keyString = new String( keyList );
        message = message.toUpperCase();
        for ( int i = 0; i < message.length(); i++ )
        {
            // If a space is found in the text thats being encrypted, 
            // keep it in the encrypted text
            if ( message.charAt( i ) == ( char ) 32 && keyString.charAt( i ) 
                    == ( char ) 32 ) {
                result += " ";
            }

            // Accesses an element of the table[][] and replaces it with 
            // a letter in the message
            else {
                result += ( char ) cipherTable[ ( int ) message.charAt( i ) 
                    - 'A' ] [ ( int ) keyString.charAt( i ) - 'A'];
            }    
        }
        return result;
    }
    /**
    * METHOD DESCRIPTION COMMENT
    * @param message
    * @return
    */
    public String decode( String message ) {
        String result = "";
        String keyString = new String( keyList );
        message = message.toUpperCase();
        for ( int i = 0; i < message.length(); i++ )
        {
            // If a space is found in the text thats being encrypted, 
            // keep it in the encrypted text
            if ( message.charAt( i ) == ( char ) 32 && keyString.charAt( i ) 
                    == ( char ) 32 ) {
                result += " ";
            }

            // Accesses an element of the table[][] and replaces it with 
            // a letter in the message
            else {
                result += ( char ) cipherTable[ ( int ) message.charAt( i ) 
                    + 'A' ] [ ( int ) keyString.charAt( i ) + 'A' ];
            }    
        }
        return result;
    }
    // CONSTRUCTORS
    /**
    * CONSTRUCTOR DESCRIPTION COMMENT
    * @param code
    * @param key
    */
    public Cipher(char code, String key) {
        // Initialize keyList
        keyList = key.toUpperCase().toCharArray();

        // Initialize cipherTable
        cipherTable = new char[26][26];
        for (int row = 0; row < 26; row++) {
            for (int col = 0; col < 26; col++) {
                int startPoint = code - 'A';
                int shiftValue = startPoint + row + col;
                int wrappedValue = shiftValue % 26;
                char letter = (char)(wrappedValue + 'A');
                cipherTable[row][col] = letter;
            }
        }
    }    // MAIN (TEST) Method
    /**
    * METHOD DESCRIPTION COMMENT
    * @param args
    */
    public static void main( String[ ] args ) {
        // Testing only works if using VM argument: java -ea Cipher
        Cipher self = new Cipher( 'H', "BABBAGE" );

        assert "PHXXF MQYBPKNJ".equals( self.encode( "HAPPY BIRTHDAY" ) );
        assert "HAPPY BIRTHDAY".equals( self.decode( "PHXXF MQYBPKNJ" ) );
    }
} // END OF CLASS --------------------------------------------------------
