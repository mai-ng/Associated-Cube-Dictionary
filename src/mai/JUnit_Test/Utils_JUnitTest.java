/**
 *
 */
package mai.JUnit_Test;

import mai.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Utils_JUnitTest {

    /**
     * valid chars are A-Z
     */
    private char validChar;
    /**
     * invalid chars are chars not A-Z (e.g. ?, /, a...)
     */
    private char invalidChar;

    /**
     * a valid word contains valid chars.
     */
    private String validWord;
    /**
     * an invalid word contains at least 1 invalid char.
     */
    private String invalidWord;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validChar = 'A';
        invalidChar = '.';
        validWord = "ASDF";
        invalidWord = "AC?D";
    }


    /**
     * Test method for {@link mai.utils.Utils#generateWordRandomly(int)}.
     */
    @Test
    public void testGenerateWordRandomly() {
        System.out.println("Random word: " + Utils.generateWordRandomly(5));
    }

    /**
     * Test method for {@link mai.utils.Utils#generateCharRandomly()}.
     */
    @Test
    public void testGenerateCharRandomly() {
        System.out.println("Random char: " + Utils.generateCharRandomly());
    }

    /**
     * Test method for {@link mai.utils.Utils#validChar(char)}.
     * Test a char is valid
     */
    @Test
    public void testValidCharOK() {
        assertTrue(Utils.validChar(validChar));
    }

    /**
     * Test method for {@link mai.utils.Utils#validChar(char)}.
     * Test a char is not valid.
     */
    @Test
    public void testValidCharKO() {
        assertFalse(Utils.validChar(invalidChar));
    }

    /**
     * Test method for {@link mai.utils.Utils#validWord(java.lang.String)}.
     * Test a word is valid.
     */
    @Test
    public void testValidWordOK() {
        assertTrue(Utils.validWord(validWord));
    }

    /**
     * Test method for {@link mai.utils.Utils#validWord(java.lang.String)}.
     * Test a word is not valid.
     */
    @Test
    public void testValidWordKO() {
        assertFalse(Utils.validWord(invalidWord));
    }

}
