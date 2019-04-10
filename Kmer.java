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
 *  Kmer is a processor class that includes the getters and setters for global variables and also includes 
 *  2 Override methods for the hashCode & equals. 
 *  
 *  Moreover, it includes 3 hash code trials and shows the run-time for each.
 *	I commented these 3 hash code trials out at the bottom of my kmer class. 
 */
/**
 * @author ainsiddiqui
 *
 */
public class Kmer {

	public String subsequence;
	public int length;

	public Kmer (String subsequence) {
		this.subsequence = subsequence; 
	}

	public Kmer (String subsequence, int length)
	{
		this.subsequence = subsequence;
		this.length = length;
	}

	public String getSubsequence() {
		return subsequence;
	}

	public void setSubsequence() {
		this.subsequence = subsequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subsequence == null) ? 0 : subsequence.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kmer other = (Kmer) obj;
		if (subsequence == null) {
			if (other.subsequence != null)
				return false;
		} else if (!subsequence.equals(other.subsequence))
			return false;
		return true;
	}

	/**
	 * The toString method that converts subsequence to text.
	 * 
	 * @return subsequence 
	 */
	public String toString() {
		return (subsequence);
	}


	/**
	 * This hashcode returns a value of 1 and I timed it using the sequence "ACCA".
	 * 
	 * Time: 2502 ms
	 * 
	 * The search time of this one is larger than the auto generated hash code of 17ms, 
	 * to 2485 ms. It slows down the run time by adding to the map and searching in the map because
	 * every object is stred in same bucket, and it kind of makes the use of a hashmap not that useful...
	 * Also all of the objects have same hashCode and are stored with the SAME hashCode, making it
	 * slow because it increases collisions. 
	 * 
	 * @Override
	 * public int hashCode(){
	 * 	return 1;
	 * } 
	 */

	/** 
	 * This hashcode I deleted 31 from the auto generated hashCode and I still timed it using sequence "ACCA". 
	 * 
	 * Time: 15 ms
	 * 
	 * This slowed down down run time by ~2 ms, and the 31 I used is a prime number (as stated in powerpoints and Stackoverflow*),
	 *  and it is a larger prime number within the hashCde so the amount of buckets in the hash will most likely not be
	 * divisible by it, thereby reducing number of collisions in the map. Removing the prime number it would take 
	 * longer to initially even load the map, let alone search it. 
	 * 
	 * (The number 31, I found more info on StackOverflow: https://stackoverflow.com/questions/3613102/why-use-a-prime-number-in-a-hashcode
	 * 
	 */

	/**
	 * This hash code returns hashCode of the sequence and I timed it using sequence the same sequence "ACCA".
	 * 
	 * Time: 7 ms. 
	 * 
	 * This hashcode gives each sequence's data type its own hashCode value and makes it faster than the last 2 hashCodes.
	 * This one is the fastest that I got in my trials.
	 * 
	 * @Override
	 * public int hashCode(){
	 * 	return sequence.hashCode();
	 * }
	 * 	
	 */

}//End of the Kmer class.


