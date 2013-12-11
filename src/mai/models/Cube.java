/**
 * 
 */
package mai.models;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import mai.specs.HasInvariant;
import mai.utils.Utils;



/**
 * @author maint <br>
 * Define a cube 3 dimensions (e.g. 5x5x5). 
 * Generate random characters (based on ASCII) at every position on a cube.
 * Or assign characters to positions on a cube from a file of characters.
 */
public class Cube implements HasInvariant {
	/**
	 * size of a cube, for example 4x4x4
	 */
	private int size;
	
	/**
	 * characters on a cube.
	 */
	private char[][][] content;

	/**
	 * constructor with an integer parameter as the size of a cube
	 * @param size
	 */
	public Cube(int size) {
		this.size = size;
		content = new char[this.size][this.size][this.size];
		
		for (int z = 0; z < this.size; z++) {
			ArrayList<Character> charsList = new ArrayList<>();
			
			while (charsList.size() < 2) {
				for (int y = 0; y < this.size; y++) {
					for (int x = 0; x < this.size; x++) {
						boolean stop = false;
						
						while (!stop) {
							char ranChar = Utils.generateCharRandomly();
						
							if (!charsList.contains(ranChar)) {
								if (charsList.size() < 100) {
									charsList.add(ranChar);
									content[x][y][z] = ranChar;
									stop = true;
								}
							} else {
								content[x][y][z] = ranChar;
								stop = true;
							}

						}
					}
				}
			}

		}
	}

	/**
	 * constructor with a string as a file path which link to the file contents characters
	 * @throws Exception 
	 */
	public Cube(String path) throws Exception {
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(path));
			String sCurrentLine = br.readLine();
			if (sCurrentLine.length() < 4 || sCurrentLine.length() > 1000) {
				throw new Exception("The size of cube is invalid. Size: " + this.size);
			}
			this.size = sCurrentLine.length();
			content = new char[this.size][this.size][this.size];

			for (int z = 0; z < this.size; z++) {
				int y = 0;
				while (sCurrentLine != null && y < this.size) {
					char charsArray[] = sCurrentLine.toCharArray();
					if (charsArray.length != this.size)
						throw new Exception("The array size is invalid. Array size: " + charsArray.length
								+ "\nSize of cube: " + this.size);
					for (int x = 0; x < charsArray.length; x++) {
						content[x][y][z] = charsArray[x];
					}
					y++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @return the size of a cube
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the content of a cube
	 */
	public char[][][] getContent() {
		return content;
	}

	/**
	 * check invariant for a cube. 
	 * A cube is NOT invalid when:
	 * <li> the size is smaller than 4
	 * <li> the size is bigger than 1000
	 * <li> there is at least one invalid character
	 * <li> the number of different characters on one lattice is smaller than 2
	 * <li> the number of different characters on one lattice is greater than 100
	 */
	@Override
	public boolean invariant() {
		if (this.size < 4 || this.size > 1000) {
			System.out.println("The size of cube is invalid: " + this.size);
			return false;
		}
		for (int z = 0; z < this.size; z++) {
			
			ArrayList<Character> charsList = new ArrayList<>();
			
			for (int y = 0; y < this.size; y++) {
				for (int x = 0; x < this.size; x++) {					
					char charAtxyz = content[x][y][z];
					
					if ( !Utils.validChar( charAtxyz )) {
						System.out.println("There is an invalid character: " + charAtxyz);
						return false;
					}
					
					if (!charsList.contains(charAtxyz)) {
						charsList.add(charAtxyz);
					}
				}
			}
			
			if (charsList.size() < 2 || charsList.size() > 100) {
				System.out.println("There is an invalid lattice in cube. The size of lattice number " + z + " is "
						+ charsList.size());
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @param x0
	 * @param y0
	 * @param z0
	 * @return true if one position is inside the cube
	 */
	public boolean inside(int x0, int y0, int z0) {
		return ((x0 >= 0) && (x0 < this.size)) && ((y0 >= 0) && (y0 < this.size)) && ((z0 >= 0) && (z0 < this.size));
	}
	
	public void writeToFile(String outputFile){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					for (int k = 0; k < this.size; k++) {
						out.write(content[i][j][k]);
					}
					out.write("\n");
				}
			}
			out.close();
		} catch (IOException e) {
		}
	}

}
