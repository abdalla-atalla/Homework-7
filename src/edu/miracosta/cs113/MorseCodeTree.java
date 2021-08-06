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
	BinaryTree<Character> tree;
	Scanner input;

	public MorseCodeTree() {
		try {
			// Variable Set Up
			input = new Scanner(new File("MorseCodeList.txt"));
			String line, morseCode;
			char letter, currentSymbol;
			BinaryTree<Character> currentTree = new BinaryTree<Character>();
			tree = new BinaryTree<Character>(null, new BinaryTree<Character>(), new BinaryTree<Character>());
			currentTree = tree;

		} catch (FileNotFoundException e) {
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

	        //loops through the word array for every word in it
	        for (String word : words) {
	            //start from the top of the tree
	            currentNode = root;

	            //loop through ever character 
	            for (int i = 0; i < word.length(); i++) {
	                //check if * or  -
	                if (word.charAt(i) == '*') {
	                    // * : traverse left
	                    currentNode = currentNode.left;
	                } else if (word.charAt(i) == '-') {
	                    //  - :traverse right
	                    currentNode = currentNode.right;
	                } else {
	                    //if neither throw an exception
	                    throw new Exception(word.charAt(i) + "Not valid ");
	                }
	            }
	            //once done traversing, add the letter to the string builder
	            sb.append(currentNode.data);
	        }
	        return sb.toString();
	    }
}
// End of class MorseCodeTree