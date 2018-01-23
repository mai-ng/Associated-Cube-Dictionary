package mai.test;

import mai.models.Cube;
import mai.models.Dictionary;
import mai.process.Parallel;

public class Parrallel_TestPerformance {
    public static void main(String[] args) throws Exception {

        Dictionary dic_associate = new Dictionary("dic_5_20_associated.txt");
        Cube cube_associate = new Cube("cube_200_associated.txt");

        long startTime = System.currentTimeMillis();

        Parallel par = new Parallel(cube_associate, dic_associate);
        if (par.associatedDictionary())
            System.out.println("associated!");
        else
            System.out.println("NOT associated!");
        System.out
                .println("Total time: "
                        + String.valueOf(System.currentTimeMillis() - startTime)
                        + "ms");

    }
}
