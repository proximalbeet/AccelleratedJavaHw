/**
 * @author Adyn Skowronski - CS1131
 */
public class CountVowels {

    /**
     * Get the number of uppercase or lowercase vowels (AEIOU) in a string.
     * @param word The string to count the vowels of.
     * @return An int representing the number of vowels in the word.
     */
    int vowelsInString( String word ){
        int count = 0;
        for( char c : word.toCharArray() ) {
            if( "AEIOUaeiou".indexOf(c) != -1 ) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        CountVowels self = new CountVowels ( );

        String text = "Hello";
        int cCount = 0;
        int vCount = self.vowelsInString( text );
        for(char c : text.toCharArray()) {
            if("BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(c) != -1) {
                cCount++;
            }
        }
        System.out.println("The number of vowels in the string is: " + vCount +
                " and the number of consonants in the string is: " + cCount);
    }
   
   
}
