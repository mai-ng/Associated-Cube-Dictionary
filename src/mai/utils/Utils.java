
package mai.utils;

import java.util.Random;

/**
 * @author maint
 *	To randomly generate a latin character according to the ASCII table.
 *	Also generate a word randomly.
 */
public class Utils{
	
	/**
	 * generate randomly a word.
	 * @param wordLen is the length of a word
	 * @return a word with the length is wordLen.
	 */
	public static String generateWordRandomly(int wordLen) {
		StringBuffer bf = new StringBuffer();
		
		while (bf.length() < wordLen) {			
			bf.append(generateCharRandomly());
		}
		
		return bf.toString();
	}
	
	/**
	 * generate a char randomly
	 * @return a char (a-z, A-Z)
	 */
	public static char generateCharRandomly(){
		Random random = new Random();
		int charCode = 65 + random.nextInt(90-65);
		
		return (char)charCode;
	}

	/**
	 * @param c is a character
	 * @return true if the code of this character is between 65 to 90 (a-z, A-Z in ASCII) <br>
	 * else false
	 */
	public static boolean validChar( char c ) {
		int code = (int)c;
		
		return ( code >= 65 ) && ( code <= 90 );
	}
	
	
	/**
	 * @param w is a word
	 * @return true if all characters of checking word are valid {@link Utils#validChar(char)}} <br>
	 * else false.
	 */
	public static boolean validWord( String w) {		
		char [] charsArray = w.toCharArray();
		
		for (int i = 0; i < charsArray.length; i++) {			
			if ( !validChar(charsArray[i]) ) {
				System.out.println("\nThere is an invalid character in word: " + charsArray[i]);
				
				return false;
			}
		}
		
		return true;
	}
}
