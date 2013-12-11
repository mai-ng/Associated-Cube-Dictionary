package mai.test;

import mai.models.Cube;
import mai.models.Dictionary;
import mai.process.Sequential;

public class Sequential_Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Cube cube = new Cube("cube_200_associated.txt");
			
		Dictionary dic = new Dictionary("dic_5_20_associated.txt"); 
		
		Sequential seq = new Sequential(cube, dic);
		if(seq.associatedDictionary())
			System.out.println("associated!");
		else
			System.out.println("NOT associated!");
		

	}

}
