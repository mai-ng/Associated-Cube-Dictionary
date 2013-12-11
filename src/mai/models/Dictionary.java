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
 * @author maint
 *
 */
public class Dictionary implements HasInvariant {
	
	/**
	 * the length of the word in a dictionary (every word in a dictionary has the same length)
	 */
	private int wordLength;
	
	/**
	 * number of words in one dictionary
	 */
	private int dicSize;
	
	/**
	 * list of words in a dictionary
	 */
	private ArrayList<String> wordsList;

	/**
	 * constructor with inputs are the length of a word and the number of words in a dictionary.
	 * generate random words, add to list of words
	 * @param word_length
	 * @param dic_size
	 */
	public Dictionary(int word_length, int dic_size) {
		this.wordLength = word_length;
		this.dicSize = dic_size;
		wordsList = new ArrayList<>();
		while (wordsList.size() < dic_size) {
			String word = Utils.generateWordRandomly(word_length);
			if (!wordsList.contains(word))
				wordsList.add(word);
		}
	}

	/**
	 * constructor with a list of words as the input.
	 * all words in the list becomes the content of the dictionary
	 * @param word_list
	 */
	public Dictionary(ArrayList<String> word_list) {
		this.wordsList = word_list;
		this.dicSize = this.wordsList.size();
		this.wordLength = word_list.get(0).length();
	}

	/**
	 * constructor with a file path as an input.
	 * the file contents words, and the content of the file will become the content of a dictionary.
	 * @param filepath
	 */
	public Dictionary(String filepath) {

		BufferedReader br = null;

		try {

			String sCurrentLine;
			this.wordsList = new ArrayList<>();

			br = new BufferedReader(new FileReader(filepath));

			while ((sCurrentLine = br.readLine()) != null) {
				if (!this.wordsList.contains(sCurrentLine)) {
					this.wordsList.add(sCurrentLine);
				}
			}
			this.wordLength = this.wordsList.get(0).length();
			this.dicSize = this.wordsList.size();

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
	 * @return the length of a word
	 */
	public int getWordLength() {
		return wordLength;
	}

	/**
	 * @return number of words in a dictionary
	 */
	public int getDicSize() {
		return dicSize;
	}

	/**
	 * @return list of words in a dictionary
	 */
	public ArrayList<String> getWordList() {
		return wordsList;
	}

	/**
	 * check invariant for a dictionary.
	 * A dictionary is NOT invariant when:
	 * <li> length of a word is smaller then 2.
	 * <li> length of a word is bigger than 100.
	 * <li> number of words in a dictionary is smaller than 3.
	 * <li> number of words in a dictionary is bigger than 1000.
	 * <li> number of words and length of list of words are not equal.
	 * <li> there is at least a word whose length is different from other's.
	 * <li> there is at least an invalid character of a word
	 */
	@Override
	public boolean invariant() {
		if (this.wordLength < 2 || this.wordLength > 100) {
			System.out.println("The word length is not valid: " + this.wordLength);
			return false;
		}

		if (this.dicSize < 3 || this.dicSize > 1000) {
			System.out.println("The size of dictionary is not valid: " + this.dicSize);
			return false;
		}

		if (this.dicSize != this.wordsList.size()) {
			System.out.println("The size of dictionary and the size of word list are not the same! \nDic size: "
					+ this.dicSize + "\nWord list size: " + this.wordsList.size());
			return false;
		}

		for (int i = 0; i < this.wordsList.size(); i++) {
			
			if (wordsList.get(i).length() != this.wordLength) {
				System.out
						.println("There is an invalid word in dictionary. The length of word and the word_length of dictionary are not the same!\nInvalid word: "
								+ this.wordsList.get(i)
								+ "\nLength of word: "
								+ wordsList.get(i).length()
								+ "\nword_length of dictionary: " + this.wordLength);
				return false;
			}
			
			if ( !Utils.validWord(wordsList.get(i)) )
				return false;
			
		}

		return true;
	}

	public void writeToFile(String outputFile){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			
			for (int i = 0; i < wordsList.size(); i++) {
				out.write(wordsList.get(i) + "\n");
			}
			out.close();
		} catch (IOException e) {
		}
	}
}
