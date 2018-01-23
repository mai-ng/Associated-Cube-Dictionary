package mai.process;

import mai.models.Cube;
import mai.models.Dictionary;
import mai.specs.HasInvariant;

public class Sequential implements HasInvariant {

    private Cube cube;
    protected Dictionary dictionary;

    /**
     * constructor with a cube and a dictionary as parameters
     *
     * @param cube
     * @param dic
     */
    public Sequential(Cube cube, Dictionary dic) {
        this.cube = cube;
        this.dictionary = dic;
    }

    /**
     * traverse all words word by word in a dictionary and check whether it is found on a
     * cube.
     *
     * @return true if all words in the dictionary were found on a cube.<br>
     * false if found 1 word doesn't exist on a cube.
     */
    public boolean associatedDictionary() {
        if (!this.invariant()) {
            System.out.println("The initial of program is not valid!");
            return false;
        } else {
            for (int word_index = 0; word_index < dictionary.getDicSize(); word_index++) {

                if (!isFoundWord(dictionary.getWordList().get(word_index))) {
                    System.out.println("Cannot find the word: "
                            + dictionary.getWordList().get(word_index)
                            + " on cube!\n NO ASSOCIATED!");
                    return false;
                }
            }
            System.out.println("ASSOCIATED!!!");
            return true;
        }
    }

    /**
     * Check a word is found on a cube. Get the first character of the word,
     * search for all possible positions on a cube where this first character
     * is, use {@link Sequential#associatedPoint(Cube, String, int, int, int)}
     * to check
     *
     * @param word
     * @return true if found the word on a cube.
     */
    boolean isFoundWord(String word) {
        char first_char = word.charAt(0);

        for (int z = 0; z < cube.getSize(); z++) {
            for (int y = 0; y < cube.getSize(); y++) {
                for (int x = 0; x < cube.getSize(); x++) {
                    if (first_char == cube.getContent()[x][y][z]) {

                        if (associatedPoint(word, x, y, z))
                            return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Found a word has the first char at the position (x,y,z), separate the word
     * into an array of chars, check the correspondent of each char of the array
     * with chars on a cube, follow the directions from (x,y,z) to its
     * neighbors. <br>To reduce the number of possible directions, we calculate the
     * position (x_lastChar,y_lastChar,z_lastChar) of the last char of the array
     * by the length of word and the direction.
     * If any of (x_lastChar,y_lastChar,z_lastChar) is out of the cube, skip the direction. <br>
     * Values of (dirX,dirY,dirZ) are (-1, 0, 1) to calculate the next position (next char).
     * <li>dirX = -1, value of next coordinate x is decreased.
     * <li>dirX = 0, value of next coordinate x is constant.
     * <li>dirX = 1, value of next coordinate x is increased.
     *
     * @param cube
     * @param word
     * @param x
     * @param y
     * @param z
     * @return
     */
    private boolean associatedPoint(String word, int x, int y, int z) {
        int delta = word.length() - 1;
        char[] charsOfWord = word.toCharArray();

        for (int z_lastChar = z - delta; z_lastChar <= z + delta; z_lastChar += delta) {
            for (int y_lastChar = y - delta; y_lastChar <= y + delta; y_lastChar += delta) {
                for (int x_lastChar = x - delta; x_lastChar <= x + delta; x_lastChar += delta) {

                    if (cube.inside(x_lastChar, y_lastChar, z_lastChar)
                            && !(x == x_lastChar && y == y_lastChar && z == z_lastChar)) {

                        int dirX = 0;

                        if (x == x_lastChar)
                            dirX = 0;
                        else
                            dirX = (x_lastChar - x) / Math.abs(x_lastChar - x);

                        int dirY = 0;

                        if (y == y_lastChar)
                            dirY = 0;
                        else
                            dirY = (y_lastChar - y) / Math.abs(y_lastChar - y);

                        int dirZ = 0;

                        if (z == z_lastChar)
                            dirZ = 0;
                        else
                            dirZ = (z_lastChar - z) / Math.abs(z_lastChar - z);
                        boolean found = true;
                        for (int i = 0; i < charsOfWord.length; i++) {
                            if (charsOfWord[i] != cube.getContent()[x + dirX
                                    * i][y + dirY * i][z + dirZ * i]) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    /**
     * @return a cube
     */
    public Cube getCube() {
        return cube;
    }

    /**
     * @return a dictionary
     */
    public Dictionary getDic() {
        return dictionary;
    }

    /**
     * check invariant before executing the program. This is NOT invariant when:
     * <li>the length of words in the dictionary is greater than the size of the
     * cube. <li>the cube, the dictionary, or both are NOT invariant
     */
    @Override
    public boolean invariant() {
        if (cube.getSize() < dictionary.getWordLength()) {
            System.out
                    .println("The size of cube is smaller than the word_length of dictionary. The program cannot run!");
            return false;
        }

        return cube.invariant() && dictionary.invariant();
    }

}
