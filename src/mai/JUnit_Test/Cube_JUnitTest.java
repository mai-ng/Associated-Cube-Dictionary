/**
 *
 */
package mai.JUnit_Test;

import mai.models.Cube;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Cube_JUnitTest {

    /**
     * <li>size of a cube is smaller than 4
     * <li>size of a cube is bigger than 1000
     * <li>cube has less than 2 different characters
     * <li>cube has more than 100 different characters
     * <li>cube has at least 1 invalid char.
     */
    private Cube invalidCube;


    /**
     * cube with the size as an input
     */
    private Cube validCubeWithSize;

    /**
     * cube with a file as an input
     */
    private Cube validCubeWithFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validCubeWithSize = new Cube(10);
        validCubeWithFile = new Cube("cube_100.txt");


    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        invalidCube = null;
        validCubeWithSize = null;
        validCubeWithFile = null;
    }

    /**
     * constructor OK with the size of cube 4-1000.
     * Test method for {@link mai.models.Cube#Cube(int)}.
     */
    @Test
    public void testCubeIntOK() {
        Assert.assertTrue(validCubeWithSize.invariant());
    }

    /**
     * constructor KO because the size is too small (<4)
     * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testCubeIntKO1() throws Exception {
        invalidCube = new Cube(2);
        Assert.assertFalse(invalidCube.invariant());

    }

//	/**
//	 * constructor KO because the size is too big (>1000)
//	 * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testCubeIntKO2() throws Exception {
//		cubeKO = new Cube(1001);
//		Assert.assertFalse(cubeKO.invariant());
//		
//	}

    /**
     * constructor OK
     * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testCubeStringOK() throws Exception {
        Assert.assertTrue(validCubeWithFile.invariant());

    }

    /**
     * constructor KO because the there are too little different chars on a lattice
     * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testCubeStringKO1() throws Exception {
        invalidCube = new Cube("cubeHasTooFewDifferentChars.txt");
        Assert.assertFalse(invalidCube.invariant());

    }

//	/**
//	 * constructor KO because the there are too many different chars on a lattice
//	 * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testCubeStringKO2() throws Exception {
//		cubeKO = new Cube("cubeHasTooManyDifferentChars.txt");
//		
//	}

    /**
     * constructor KO .
     * because the there are at list an invalid char on the cube.
     * Test method for {@link mai.models.Cube#Cube(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testCubeStringKO3() throws Exception {
        invalidCube = new Cube("cubeHasInvalidChar.txt");
        Assert.assertFalse(invalidCube.invariant());

    }

    /**
     * Test method for {@link mai.models.Cube#inside(int, int, int)}.
     */
    @Test
    public void testInside() {
        Assert.assertTrue(validCubeWithSize.inside(4, 3, 2));

        Assert.assertFalse(validCubeWithSize.inside(-1, 1, 1));
        Assert.assertFalse(validCubeWithSize.inside(1, -1, 1));
        Assert.assertFalse(validCubeWithSize.inside(1, 1, -1));

        Assert.assertFalse(validCubeWithSize.inside(-1, -1, 1));
        Assert.assertFalse(validCubeWithSize.inside(-1, 1, -1));
        Assert.assertFalse(validCubeWithSize.inside(1, -1, -1));

        Assert.assertFalse(validCubeWithSize.inside(-1, -1, -1));
        Assert.assertFalse(validCubeWithSize.inside(10, 10, 10));

        Assert.assertFalse(validCubeWithSize.inside(10, 9, 9));
        Assert.assertFalse(validCubeWithSize.inside(9, 10, 9));
        Assert.assertFalse(validCubeWithSize.inside(9, 9, 10));

        Assert.assertFalse(validCubeWithSize.inside(10, 10, 9));
        Assert.assertFalse(validCubeWithSize.inside(10, 9, 10));
        Assert.assertFalse(validCubeWithSize.inside(9, 10, 10));
    }


    @Test
    public void testGetSize() {
        Assert.assertEquals(10, validCubeWithSize.getSize());

    }


    @Test
    public void testGetContent() {
        Assert.assertNotNull(validCubeWithSize.getContent());

    }


}
