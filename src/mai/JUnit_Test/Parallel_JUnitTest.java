/**
 * 
 */
package mai.JUnit_Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import mai.models.Cube;
import mai.models.Dictionary;
import mai.process.Parallel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author maint
 *
 */
public class Parallel_JUnitTest extends CubeAndDic_TestSpec {
	
	private Dictionary invalidDic;
    private Cube invalidCube;

    private Parallel assoPar;
    private Parallel notAssoPar;
    private Parallel notIvariantPar;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		invalidDic = new Dictionary("dicKO.txt");
		invalidCube = new Cube("cube_4.txt");

		assoPar = new Parallel(validCube, associatedDic);
		notAssoPar = new Parallel(validCube, notAssociatedDic);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		invalidCube = null;
		invalidCube = null;
		assoPar = null;
		notAssoPar = null;
	}

	/**
	 * Test method for {@link mai.process.Parallel#associatedDictionary()}.
	 */
	@Test
	public void testAssociatedDictionary() {
		fail("Not yet implemented");
	}

	/**
	 * constructor OK.
	 * Test method for {@link mai.process.Parallel#Parallel(mai.models.Cube, mai.models.Dictionary)}.
	 */
	@Test
	public void testParallelOK() {
		assertTrue(assoPar.invariant());
		assertTrue(notAssoPar.invariant());
	}
	
	/**
	 * constructor KO because of the invalid cube.
	 * Test method for {@link mai.process.Parallel#Parallel(mai.models.Cube, mai.models.Dictionary)}.
	 */
	@Test
	public void testParallelKO1() {
		notIvariantPar = new Parallel(invalidCube, associatedDic);
		Assert.assertFalse(notIvariantPar.invariant());
	}
	
	/**
	 * constructor KO because of the invalid dictionary.
	 * Test method for {@link mai.process.Parallel#Parallel(mai.models.Cube, mai.models.Dictionary)}.
	 */
	@Test
	public void testParallelKO2() {
		notIvariantPar = new Parallel(validCube, invalidDic);
		Assert.assertFalse(notIvariantPar.invariant());
	}
	
	/**
	 * constructor KO because of the invalid dictionary and cube.
	 * Test method for {@link mai.process.Parallel#Parallel(mai.models.Cube, mai.models.Dictionary)}.
	 */
	@Test
	public void testParallelKO3() {
		notIvariantPar = new Parallel(invalidCube, invalidDic);
		Assert.assertFalse(notIvariantPar.invariant());
	}

    /**
     * Test method for {@link mai.process.Parallel#associatedDictionary()}.
     */
    @Test
    public void testAssociatedDictionaryTrue() {
        assertTrue(assoPar.associatedDictionary());
        assertFalse(notAssoPar.associatedDictionary());
    }


}
