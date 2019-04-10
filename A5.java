
import java.io.*;
import java.util.HashMap;
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
 *  A5 is a processor class that acts as the User Interface.
 *  
 *  It checks the command line and ensures input is correct, then moves onto 
 *  implementing the code to test the .fasta file with the amount of kmers.
 *
 */
public class A5 {

	/**
	 * @param args
	 * 
	 * Main method that calls upon run(); but before that it checks the
	 * command line to see if it is the correct argument in the commandline
	 * 
	 * @param fileName (user inputs this value) 
	 * @param Kmer length (user inpuuts this value)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//The args length segment of the code is courtesy of Casey Ryane (I asked for help on that part for the command line)
		String fileName;
		String length; //Corresponds to the kmer length 
		if (args.length == 2) { //Checks if there is only 2 arguments, then proceeds  
			fileName = args[0]; //Since it is an array, the file name is the first index '0'
			length = args[1];//The next index would be the length therefore '1'
			System.out.println(fileName);

			new A5().run(fileName, length);
		}//Ends the if stateemnt 
		else {
			System.out.println("ERROR: Incorrect inputs for the command-line arguments.");
		}
	}//End of my main method 

	/** 
	 * 
	 * Run method to actually implement the code to test the Nucleotides.
	 * 
	 * It processes the file and proceeds to do the other steps of parsing 
	 * to get the sequences. 
	 * 
	 * @param fName 	the file name 
	 * @param length	the length of Kmer
	 */
	private void run(String fName, String length) {
		// TODO Auto-generated method stub
		fName = fName.toLowerCase();
		int kmerLength;
		File inFile;

		if(!fName.contains(".fasta")) { //Checks if the file is the .fasta extension 
			fName += ".fasta"; //Adds the .fasta if its not already present 
		}
		//Begin the try 
		try {
			kmerLength = Integer.parseInt(length);
			inFile = new File(fName);
			if(inFile.exists()) { //Checks if file is valid and exists 
				SequenceProcessor s1 = new SequenceProcessor(kmerLength);
				s1.readFile(inFile); //calls Read File Method to read the acctual .fasta file
			}	
		} //End of the try block 

		//Catching error
		catch(NumberFormatException e) { //If length inputted is not a Int value
			System.out.println("ERROR: The length inputted is incorrect.");
			System.exit(0); //EXITS THE PROGRAM
		} //Ending the catch block 
	} 
}//Ends the A5 class.
