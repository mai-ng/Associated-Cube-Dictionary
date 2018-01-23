/**
 *
 */
package mai.JUnit_Test;

import mai.models.Dictionary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Dictionary_JUnitTest {

    /**
     * A dictionary is not valid:
     * <li> length of a word is smaller then 2.
     * <li> length of a word is bigger than 100.
     * <li> number of words in a dictionary is smaller than 3.
     * <li> number of words in a dictionary is bigger than 1000.
     * <li> there is at least a word whose length is different from other's.
     * <li> there is at least an invalid character of a word
     */
    private Dictionary dicKO;
    private Dictionary dicWithIntOK;
    private Dictionary dicWithFileOK;
    private Dictionary dicWithListOK;

    private ArrayList<String> wordsListOK;
    private ArrayList<String> wordsListKO;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dicWithIntOK = new Dictionary(10, 100);

        wordsListOK = new ArrayList<String>();
        wordsListOK.add("ASDF");
        wordsListOK.add("QWER");
        wordsListOK.add("HGFR");
        wordsListOK.add("KJHG");
        wordsListOK.add("POIU");
        dicWithListOK = new Dictionary(wordsListOK);

        wordsListKO = new ArrayList<String>();
        wordsListKO.add("ASDF");
        wordsListKO.add("QWERD");
        wordsListKO.add("HGFR");
        wordsListKO.add("KJHGDD");
        wordsListKO.add("POIU");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

        dicWithIntOK = null;
        dicWithListOK = null;
        dicWithFileOK = null;

        dicKO = null;

        wordsListOK = null;
        wordsListKO = null;

    }

    /**
     * constructor OK with integers as parameters
     * Test method for {@link mai.models.Dictionary#Dictionary(int, int)}.
     */
    @Test
    public void testDictionaryIntIntOK() {
        assertTrue(dicWithIntOK.invariant());
        assertEquals(10, dicWithIntOK.getWordLength());
        assertEquals(100, dicWithIntOK.getDicSize());

    }

    /**
     * constructor Not OK with integers as parameters.
     * The size of the dic is too big.
     * Test method for {@link mai.models.Dictionary#Dictionary(int, int)}.
     */
    @Test
    public void testDictionaryIntIntKO1() {
        dicKO = new Dictionary(10, 1001);
        assertFalse(dicKO.invariant());

    }

    /**
     * constructor Not OK with integers as parameters.
     * Coz size of the dic is too small.
     * Test method for {@link mai.models.Dictionary#Dictionary(int, int)}.
     */
    @Test
    public void testDictionaryIntIntKO2() {
        dicKO = new Dictionary(10, 2);
        assertFalse(dicKO.invariant());

    }

    /**
     * constructor Not OK with integers as parameters.
     * Coz the length of words in the dictionary is smaller than 2.
     * Test method for {@link mai.models.Dictionary#Dictionary(int, int)}.
     */
    @Test
    public void testDictionaryIntIntKO3() {
        dicKO = new Dictionary("dicTooShortWords.txt");
        assertFalse(dicKO.invariant());

    }

    /**
     * constructor Not OK with integers as parameters.
     * Coz the length of words in the dictionary is bigger than 100.
     * Test method for {@link mai.models.Dictionary#Dictionary(int, int)}.
     */
    @Test
    public void testDictionaryIntIntKO4() {
        dicKO = new Dictionary(101, 100);
        assertFalse(dicKO.invariant());

    }

    /**
     * Test method for {@link mai.models.Dictionary#Dictionary(java.util.ArrayList)}.
     */
    @Test
    public void testDictionaryArrayListOfStringOK() {

        assertTrue(dicWithListOK.invariant());

    }

    /**
     * constructor Not OK with ArrayList as parameter.
     * there is at least a word whose length is different from other's.
     * Test method for {@link mai.models.Dictionary#Dictionary(ArrayList)}.
     */
    @Test
    public void testDictionaryArrayListOfStringKO() {
        dicKO = new Dictionary(wordsListKO);
        assertFalse(dicKO.invariant());

    }


    /**
     * constructor with a file as a parameter OK
     * Test method for {@link mai.models.Dictionary#Dictionary(java.lang.String)}.
     */
    @Test
    public void testDictionaryStringOK() {
        dicWithFileOK = new Dictionary("dic_5_100.txt");
        assertTrue(dicWithFileOK.invariant());
    }


    /**
     * constructor with a file as a parameter KO.
     * There is at least an invalid word.
     * Test method for {@link mai.models.Dictionary#Dictionary(java.lang.String)}.
     */
    @Test
    public void testDictionaryStringKO() {
        dicKO = new Dictionary("dicKO.txt");
        assertFalse(dicKO.invariant());
    }


    /**
     * Test method for {@link mai.models.Dictionary#getWordList()}.
     */
    @Test
    public void testGetWordList() {
        ArrayList<String> list = dicWithListOK.getWordList();
        assertEquals(list.size(), dicWithListOK.getDicSize());
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                assertTrue(list.get(i).charAt(j) == wordsListOK.get(i).charAt(j));
            }
        }
    }


    @Test
    public void testGetWordLength() {
        assertEquals(10, dicWithIntOK.getWordLength());

    }


    @Test
    public void testGetDicSize() {
        assertEquals(100, dicWithIntOK.getDicSize());

    }


}
