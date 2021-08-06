package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each
 * letter of the English alphabet, and a means of traversal to be used to
 * decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {
	

	public MorseCodeTree() {
		super(new Node());

		readMorseCodeTree();
	}

	public void readMorseCodeTree() {
		try {
			Scanner scan = new Scanner(new File("./src/edu/miracosta/cs113/morsecodefile.txt"));

			String line;
			String[] data;
			Node currentNode;

			while (scan.hasNext()) {
				currentNode = root;
				line = scan.nextLine();
				data = line.split(" ");

				for (int i = 0; i < data[1].length(); i++) {
					if (data[1].charAt(i) == '*') {
						if (currentNode.left == null) {
							currentNode.left = new Node();
						}
						currentNode = currentNode.left;
					} else {
						if (currentNode.right == null) {
							currentNode.right = new Node();
						}
						currentNode = currentNode.right;
					}
				}

				currentNode.data = data[0].charAt(0);

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	// TODO:
	// Build this class, which includes the parent BinaryTree implementation in
	// addition to
	// the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation
	// has been suggested for the former,
	// where said exceptional cases are to be handled according to the corresponding
	// unit tests.

	/**
	 * Non-recursive method for translating a String comprised of morse code values
	 * through traversals in the MorseCodeTree.
	 *
	 * The given input is expected to contain morse code values, with '*' for dots
	 * and '-' for dashes, representing only letters in the English alphabet.
	 *
	 * This method will also handle exceptional cases, namely if a given token's
	 * length exceeds that of the tree's number of possible traversals, or if the
	 * given token contains a character that is neither '*' nor '-'.
	 *
	 * @param morseCode The given input representing letters in Morse code
	 * @return a String representing the decoded values from morseCode
	 * @throws Exception
	 */
	public String translateFromMorseCode(String morseCode) throws Exception {
		String[] words = morseCode.split(" ");
		Node<Character> currentNode;
		StringBuilder sb = new StringBuilder();

		// loops through the word array for every word in it
		for (String word : words) {
			// start from the top of the tree
			currentNode = root;

			// loop through ever character
			for (int i = 0; i < word.length(); i++) {
				// check if * or -
				if (word.charAt(i) == '*') {
					// * : traverse left
					currentNode = currentNode.left;
				} else if (word.charAt(i) == '-') {
					// - :traverse right
					currentNode = currentNode.right;
				} else {
					// if neither throw an exception
					throw new Exception(word.charAt(i) + "Not valid ");
				}
			}
			// once done traversing, add the letter to the string builder
			sb.append(currentNode.data);
		}
		return sb.toString();
	}
}
// End of class MorseCodeTree