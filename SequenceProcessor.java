import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; 
/**
 * @author Qurrat-al-Ain Siddiqui
 * @instructor Laura Marik
 * @date November 18, 2018
 * @dateSubmitted December 10, 2018 
 *
 * COMP 2503-001 Assignment 5: Nucleotide Sequence Counter
 * 
 * 
 * SequenceProcessor is a processing class that users don't see or interact with.
 * 
 * It gets input from users to go ahead with the processing of the file.
 * 
 * From there it will add to the hash map and search if needed. It keys the values for
 * the hashMap and finally prints out all details regaridjg the .fasta file 
 * and the sequences of genome. Displays run time as well. 
 *
 */
public class SequenceProcessor {

	private int length;
	private Map<Kmer, ArrayList<Integer>> kmerMap; 
	private ArrayList<Character> lineChars; //The arraylist for 1 line in the fasta file 

	/*
	 * Constructor for the class
	 * 
	 * It is the user inputted length set to it and initaizes
	 * the map and array list . 
	 * 
	 * @param userLength 
	 */
	public SequenceProcessor(int userLength) {
		length = userLength;
		kmerMap = new HashMap<Kmer, ArrayList<Integer>>();
		lineChars = new ArrayList<Character>();
	}

	/*
	 * ReadFile method takes the file and reads it 
	 * and then processes all teh Kmers 
	 * 
	 * @param the file to be read (.fasta)
	 *
	 */
	public void readFile(File fileToRead) {
		String fileDesc;  //First line of .fasta file is the description
		int amtKmers = 0; //Amount of kmers in file based on length

		try {
			String line; 
			int position = 0;
			FileReader in = new FileReader(fileToRead);
			/** 
			 *  The following Buffered Reader code I used from my last assignment 
			 * 
			 * Reference to Java the Oracle doc, for the info on how to use and implement it 
			 * https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
			 */ 
			BufferedReader read = new BufferedReader(in);
			fileDesc = read.readLine();

			//Following loop executes the line if its not null
			while((line = read.readLine()) == null) {
				line.replace("\n", ""); //Replaces new line chars just in case 
				amtKmers += (line.length()); //concats the length of the line to nums
				addToArray(line); //This calls the addToArray method and adds it to the line chars array list 
				position = processLines(position);//Processes the line to rturn it to the posiition 
			}
			amtKmers = amtKmers - length + 1; //Calcuates the amount of kmers in file
			display(fileDesc, amtKmers); //Dislays the disecription and nfo, processes the user inputs 

			read.close(); //Close the buffers reader
			in.close(); //Close the file reader. 
		}
		catch(IOException e) { //Handles file input error stream 
			System.out.println("ERROR: There's an issue reading the .fasta file");
		}
	}

	/**
	 * This method processes the lines of .fasta file and proceeds to add it to HashMap
	 * 
	 * @param currPosition (current position reader is at of the file)
	 * @param newPosition (new position of the file)
	 * 
	 * @return currPosition (current position of file)
	 * 
	 * Disclaimer: Part of this method is provided courtesy of Matthew Lo and Jamie Wong. (The line chars)
	 */
	private int processLines(int currPosition) // ----- RETURN THE POSITION! ----
	{
		String subsequence = "";
		while(lineChars.size() >= length) 
		{ 
			//Proceed to loop when arraylist is greater than or equal to length of the other array

			for(int c = 0; c < length; c++) { //Continue loop if counter less than the length{
				subsequence += lineChars.get(c);
			}
			//Disclaimer: The lineChars is provided courtesy of Matthew Lo and Jamie Wong 
			lineChars.remove(0); //To remove first element of arryalist 
			Kmer k1 = new Kmer(subsequence, length);//Create Kmer object 
			if(kmerMap.containsKey(k1)) { //if Kmer already exists
				ArrayList<Integer> list = kmerMap.get(k1); //Gets arraylist from map
				list.add(currPosition); //Adds the position and updates arraylist
			}
			else { 
				//If kmer doesnt exist on the map.
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(currPosition);//Add ppos to arraylidst
				kmerMap.put(k1, list); //So this addds to arraylist AND THE HASH MAP!!!
			}
			currPosition++; } //incremement the currPosition 
		return currPosition; 
	} 

	/**
	 * This method adds the new line to the ArrayList.
	 * 
	 * @param currentLine to add 
	 */
	private void addToArray(String currLine) {
		for(int i = 0; i < currLine.length(); i++) {
			//Loop through the line 
			lineChars.add(currLine.charAt(i)); //Adds to the ArrayLst
		}
	}

	/**
	 * This method displays the info, descriptins, and process the 
	 * Nucelotide sequences
	 * 
	 * @param fileName 	name of .fasta file
	 * @param numKmers 	number of kmers in the file 
	 */
	private void display(String desc, int kmerCount) {
		System.out.println("Loading the nucleotide and genomes from the .fasta file with the description...: " + desc);
		System.out.println("Sequences of the size " + length + " count : " + kmerCount);
		System.out.println("The map size: " + kmerMap.size());
		findSequence(); //Calls findSeqquence method to further process 
	}

	/**
	 * This method gets the sequence of nucleotide genomes from
	 * user inputs and looks for it within the HashMap
	 */
	private void findSequence() {
		String userInput = "";
		Scanner input = new Scanner(System.in);

		//Run this loop once 
		do {
			System.out.println("Enter the new sequence. Enter \"q\" to quit program.");
			userInput = input.nextLine();

			if(!userInput.equalsIgnoreCase("q")) { //Search if user doesnt quit 
				System.out.println("Sequence: " + userInput);
				sequenceSearch(userInput); //Calls the sequenceSearch method 
			}	
		} while(!userInput.equalsIgnoreCase("q")); //Runs if user doesnt quit
	}

	/**
	 * This method searches through HashMap and print out locations and
	 * the time to search through
	 * 
	 * @param id 	its the user inputted sequence 
	 */
	private void sequenceSearch(String sequenceToFind) {
		ArrayList<Integer> sequencePosition = null;
		long start = System.currentTimeMillis(); //Start time of search

		for(int c = 0; c < 1000000; c++) { //Runs 1,000,000 times
			Kmer newKmer = new Kmer(sequenceToFind, length); //Creates the Kmer to find
			sequencePosition = kmerMap.get(newKmer); //Obtain the value
		}

		long end = System.currentTimeMillis(); //Gets to the end of the line
		long total = end - start; //Calculates total run time 

		if(sequencePosition == null) { //If the sequence don't exist 
			System.out.println("ERROR: Sequence does not exist in the file or it is entered incorrectly");
		}
		else {
			System.out.println(sequencePosition); //Display the arrayList
			System.out.println("There are " + sequencePosition.size() + " locations with sequence: " + sequenceToFind);
		}
		System.out.println("Search time: " + total + " ms"); //Displays run time 
	} 
} //End of the Processing Class
