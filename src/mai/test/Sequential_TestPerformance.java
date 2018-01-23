package mai.test;

import mai.models.Cube;
import mai.models.Dictionary;
import mai.process.Sequential;

public class Sequential_TestPerformance {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Dictionary dic_associate = new Dictionary("dic_5_20_associated.txt");
        Cube cube_associate = new Cube("cube_200_associated.txt");

        long startTime = System.currentTimeMillis();

        Sequential seq2 = new Sequential(cube_associate, dic_associate);
        if (seq2.associatedDictionary())
            System.out.println("associated!");
        else
            System.out.println("NOT associated!");
        System.out
                .println("Total time: "
                        + String.valueOf(System.currentTimeMillis() - startTime)
                        + "ms");

    }
}