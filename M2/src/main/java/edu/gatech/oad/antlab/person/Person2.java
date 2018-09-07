package edu.gatech.oad.antlab.person;

import java.util.ArrayList;
import java.util.Random;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author RyanGadhi
 * @version 1.3
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {

		String output = "";
		char[] arr = input.toCharArray();
		char[] arr2 = new char[arr.length];

		Random ran = new Random();
		ArrayList<Integer> usedIndex = new ArrayList<Integer>();
		int currentRan= ran.nextInt(arr.length);

		for(int i=0 ; i< arr.length ; i++){
			while(usedIndex.contains(currentRan))		// if the random index has been used
				currentRan = ran.nextInt(arr.length);	// it will not use it again

			usedIndex.add(currentRan);
			arr2[i]=arr[currentRan]; 					// building the randomized array
		}
		for (int i =0 ; i< arr2.length ; i++)			// converting the arr2 to a String
			output += arr2[i];

		return output;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
