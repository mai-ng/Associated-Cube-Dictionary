package mai.models;

import mai.specs.HasInvariant;
import mai.utils.Utils;

import java.io.*;
import java.util.ArrayList;

/**
 * @author maint <br>
 *         Define a cube 3 dimensions (e.g. 5x5x5). Generate random characters
 *         (based on ASCII) at every position on a cube. Or assign characters to
 *         positions on a cube from a file of characters.
 */
public class Cube implements HasInvariant {
    /**
     * size of a cube, for example 4x4x4
     */
    private int size;

    /**
     * all characters on a cube.
     */
    private char[][][] contentOfCube;

    /**
     * constructor with an integer parameter as the size of a cube
     *
     * @param size
     */
    public Cube(int size) {
        this.size = size;
        contentOfCube = new char[this.size][this.size][this.size];

        for (int z = 0; z < this.size; z++) {
            ArrayList<Character> charsList = new ArrayList<Character>();

            while (charsList.size() < 2) {
                for (int y = 0; y < this.size; y++) {
                    for (int x = 0; x < this.size; x++) {
                        boolean stop = false;

                        while (!stop) {
                            char ranChar = Utils.generateCharRandomly();

                            if (!charsList.contains(ranChar)) {
                                if (charsList.size() < 100) {
                                    charsList.add(ranChar);
                                    contentOfCube[x][y][z] = ranChar;
                                    stop = true;
                                }
                            } else {
                                contentOfCube[x][y][z] = ranChar;
                                stop = true;
                            }

                        }
                    }
                }
            }

        }
    }

    /**
     * constructor with a string as a file path which link to the file contents
     * characters
     *
     * @throws Exception
     */
    public Cube(String filePath) {
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(filePath));
            String sCurrentLine = br.readLine();

            this.size = sCurrentLine.length();
            contentOfCube = new char[this.size][this.size][this.size];

            for (int z = 0; z < this.size; z++) {
                int y = 0;
                while (sCurrentLine != null && y < this.size) {
                    char charsArray[] = sCurrentLine.toCharArray();
                    for (int x = 0; x < charsArray.length; x++) {
                        contentOfCube[x][y][z] = charsArray[x];
                    }
                    sCurrentLine = br.readLine();
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
        return contentOfCube;
    }

    /**
     * check invariant for a cube. A cube is NOT invalid when: <li>the size is
     * smaller than 4 <li>the size is bigger than 1000 <li>there is at least one
     * invalid character <li>the number of different characters on one lattice
     * is smaller than 2 <li>the number of different characters on one lattice
     * is greater than 100
     */
    @Override
    public boolean invariant() {
        if (this.size < 4 || this.size > 1000) {
            System.out.println("The size of cube is invalid: " + this.size);
            return false;
        }

        for (int z = 0; z < this.size; z++) {
            ArrayList<Character> differentCharsList = new ArrayList<Character>();

            for (int y = 0; y < this.size; y++) {
                for (int x = 0; x < this.size; x++) {
                    char charAtxyz = contentOfCube[x][y][z];

                    if (!Utils.validChar(charAtxyz)) {
                        System.out.println("There is an invalid character: "
                                + charAtxyz);
                        return false;
                    }

                    if (!differentCharsList.contains(charAtxyz)) {
                        differentCharsList.add(charAtxyz);
                    }
                }
            }

            if (differentCharsList.size() < 2
                    || differentCharsList.size() > 100) {
                System.out
                        .println("There is an invalid lattice in cube. The size of lattice number "
                                + z + " is " + differentCharsList.size());
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
        return ((x0 >= 0) && (x0 < this.size))
                && ((y0 >= 0) && (y0 < this.size))
                && ((z0 >= 0) && (z0 < this.size));
    }

    /**
     * write the content of a cube to a file
     *
     * @param file
     */
    public void writeToFile(String file) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    for (int k = 0; k < this.size; k++) {
                        out.write(contentOfCube[i][j][k]);
                    }
                    out.write("\n");
                }
            }
            out.close();
        } catch (IOException e) {
        }
    }

}
