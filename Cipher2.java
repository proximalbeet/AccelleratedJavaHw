/**
 * CLASS DESCRIPTION COMMENT
 * Sources used:
 * <p>
 * https://www.digitalocean.com/community/tutorials/
 * java-char-to-string-to-char-array
 * <p>
 * https://www.geeksforgeeks.org/convert-character-array-to-string-in-java/
 * <p>
 * https://www.baeldung.com/java-character-ascii-value
 * <p>
 * https://www.geeksforgeeks.org/modulo-or-remainder-operator-in-java/
 * <p>
 * https://www.youtube.com/watch?v=DALwBJw5Or4
 */
public class Cipher2 {
    // INSTANCE VARIABLES
    private char[] keyList; // VARIABLE DESCRIPTION COMMENT
    private char[][] cipherTable; // VARIABLE DESCRIPTION COMMENT

    /**
     * METHOD DESCRIPTION COMMENT
     *
     * @param message
     * @return
     */
    public char[] getKeyList() {
        return keyList;
    }

    /**
     * METHOD DESCRIPTION COMMENT
     *
     * @param message
     * @return
     */
    public char[][] getCipherTable() {
        return cipherTable;
    }
    // METHODS

    /**
     * METHOD DESCRIPTION COMMENT
     *
     * @param message
     * @return
     */
     public String encode(String message) {
        String result = "";
        String keyString = new String(keyList);
        message = message.toUpperCase();
        for (int i = 0; i < message.length(); i++) {
            // If a space is found in the text thats being encrypted,
            // keep it in the encrypted text
            int keyStrModLen = i % (keyString.length());
            if (message.charAt(i) == ' '
                    || keyString.charAt(keyStrModLen) == ' ') {
                result += " ";
            }

            // Accesses an element of the table[][] and replaces it with
            // a letter in the message
            else {
                result += (char) cipherTable[(int) message.charAt(i)
                        - 'A'][(int) keyString.charAt(keyStrModLen) - 'A'];
            }
        }
        return result;
    }

    /**
     * METHOD DESCRIPTION COMMENT
     *
     * @param message
     * @return
     */
    public String decode(String message) {
        String result = "";
        String keyString = new String(keyList);
        message = message.toUpperCase();

        for (int i = 0; i < message.length(); i++) {
            // If a space is found in the text thats being encrypted,
            // keep it in the encrypted text
            int keyStrModLen = i % (keyString.length());
            if (message.charAt(i) == ' ' ||
                    keyString.charAt(keyStrModLen) ==  ' ') {
                result += " ";
            }

            // Accesses an element of the table[][] and replaces it with
            // a letter in the message
            else {
                // Find the row corresponding to the key character
                int keyCharIndex = keyString.charAt(keyStrModLen) - 'A';

                // Find the column in the row that matches the encoded character
                for (int j = 0; j < 26; j++) {
                    if (cipherTable[keyCharIndex][j] == message.charAt(i)) {
                        // The column index corresponds to the decoded character
                        result += (char) (j + 'A');
                        break;
                    }
                }
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
    public Cipher( char code, String key){
                // Initialize keyList
                keyList = key.toUpperCase().toCharArray();

                // Initialize cipherTable
                cipherTable = new char[26][26];
                for (int row = 0; row < 26; row++) {
                    for (int col = 0; col < 26; col++) {
                        int startPoint = code - 'A';
                        int shiftValue = startPoint + row + col;
                        int wrappedValue = shiftValue % 26;
                        char letter = (char) (wrappedValue + 'A');
                        cipherTable[row][col] = letter;
                    }
                }
            }
            // MAIN (TEST) Method
            /**
             * METHOD DESCRIPTION COMMENT
             * @param args
             */
            public static void main(String[] args) {
                Cipher self = new Cipher('H', "BABBAGE");

                System.out.println("Cipher Table:");
                for (int i = 0; i < 26; i++) {
                    System.out.println(new String(self.cipherTable[i]));
                }

                String originalMessage = "HAPPY BIRTHDAY";
                String expectedEncoded = "PHXXF MQYBPKNJ";

                String encoded = self.encode(originalMessage);
                System.out.println("Original message: " + originalMessage);
                System.out.println("Expected encoded: " + expectedEncoded);
                System.out.println("Actual encoded:   " + encoded);

                if (!expectedEncoded.equals(encoded)) {
                    System.out.println("Encoding mismatch. Comparing character by character:");
                    for (int i = 0; i < expectedEncoded.length(); i++) {
                        if (i < encoded.length()) {
                            System.out.printf("Position %d: Expected '%c', Got '%c'%n",
                                    i, expectedEncoded.charAt(i), encoded.charAt(i));
                        } else {
                            System.out.printf("Position %d: Expected '%c', Got end of string%n",
                                    i, expectedEncoded.charAt(i));
                        }
                    }
                }

                String decoded = self.decode(encoded);
                System.out.println("Decoded message: " + decoded);
                assert originalMessage.equals(decoded) : "Decoding failed";

                System.out.println("All tests passed successfully!");
            }
        } // END OF CLASS --------------------------------------------------------
