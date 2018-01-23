/**
 *
 */
package mai.JUnit_Test;

import mai.models.Cube;
import mai.models.Dictionary;
import mai.process.Sequential;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sequential_JUnitTest extends CubeAndDic_TestSpec {

    private Dictionary invalidDic;
    private Cube invalidCube;

    private Sequential assoSeq;
    private Sequential notAssoSeq;
    private Sequential notIvariantSeq;

    @Before
    public void setUp() throws Exception {
        invalidDic = new Dictionary("dicKO.txt");
        invalidCube = new Cube("cube_4.txt");

        assoSeq = new Sequential(validCube, associatedDic);
        notAssoSeq = new Sequential(validCube, notAssociatedDic);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        invalidDic = null;
        invalidCube = null;
        assoSeq = null;
        notAssoSeq = null;
    }

    /**
     * constructor OK.
     * Test method for {@link mai.process.Sequential#Sequential(mai.models.Cube, mai.models.Dictionary)}.
     */
    @Test
    public void testSequentialOK() {
        assertTrue(assoSeq.invariant());
        assertTrue(notAssoSeq.invariant());
    }

    /**
     * constructor KO.
     * Cause the cube is NOT valid
     * Test method for {@link mai.process.Sequential#Sequential(mai.models.Cube, mai.models.Dictionary)}.
     */
    @Test
    public void testSequentialKO1() {
        notIvariantSeq = new Sequential(invalidCube, associatedDic);
        assertFalse(notIvariantSeq.invariant());
    }

    /**
     * constructor KO.
     * Cause the dictionary is NOT invariant.
     * Test method for {@link mai.process.Sequential#Sequential(mai.models.Cube, mai.models.Dictionary)}.
     */
    @Test
    public void testSequentialKO2() {
        notIvariantSeq = new Sequential(validCube, invalidDic);
        assertFalse(notIvariantSeq.invariant());
    }

    /**
     * constructor KO.
     * Cause the dictionary and the cube are NOT invariant.
     * Test method for {@link mai.process.Sequential#Sequential(mai.models.Cube, mai.models.Dictionary)}.
     */
    @Test
    public void testSequentialKO3() {
        notIvariantSeq = new Sequential(invalidCube, invalidDic);
        assertFalse(notIvariantSeq.invariant());
    }

    /**
     * Test method for {@link mai.process.Sequential#associatedDictionary()}.
     */
    @Test
    public void testAssociatedDictionaryTrue() {
        assertTrue(assoSeq.associatedDictionary());
        assertFalse(notAssoSeq.associatedDictionary());
    }


    /**
     * Test method for {@link mai.process.Sequential#getCube()}.
     */
    @Test
    public void testGetCube() {
        assertEquals(200, assoSeq.getCube().getSize());
    }

    /**
     * Test method for {@link mai.process.Sequential#getDic()}.
     */
    @Test
    public void testGetDic() {
        assertEquals(5, assoSeq.getDic().getWordLength());
        assertEquals(22, assoSeq.getDic().getDicSize());
    }


}
