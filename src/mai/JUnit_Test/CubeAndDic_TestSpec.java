package mai.JUnit_Test;

import mai.models.Cube;
import mai.models.Dictionary;
import org.junit.Assert;
import org.junit.Test;

public abstract class CubeAndDic_TestSpec {

    protected Cube validCube = new Cube("cube_200_associated.txt");

    protected Dictionary associatedDic = new Dictionary("dic_5_20_associated.txt");

    protected Dictionary notAssociatedDic = new Dictionary("dic_100_100.txt");

    @Test
    public void testInvariant() {
        Assert.assertTrue(validCube.invariant());
        Assert.assertTrue(associatedDic.invariant());
        Assert.assertTrue(associatedDic.invariant());
    }
}
