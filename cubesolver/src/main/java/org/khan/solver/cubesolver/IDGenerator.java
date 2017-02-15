package org.khan.solver.cubesolver;

import java.util.HashSet;
import java.util.Set;

/**
 * Singleton class to generate unique id's. Each id will be a four digit number.
 * 
 *
 * @author SDK
 *
 */
public class IDGenerator {

	// set of already generated unique pins 
	private Set<String> uniquePins = new HashSet<String>();
	// genrator instance
	private static IDGenerator generator = null;
	// default constructor
	private IDGenerator(){}
	
	/**
	 * 
	 * @return
	 */
	public static IDGenerator getInstance(){
		if(generator == null)
			generator = new IDGenerator();
		return generator;
	}
	
	/**
	 * 
	 * NOTE: There can be a case that all unique digits are already in the set and then method will go to infinite loop,
	 * It's the responsibility of the caller to use it only if its sure that its application requires less unique id's as 
	 * compared to the total number of possible id with four digits.
	 * 
	 * @return
	 */
	public String generateUniqueID() {
		 int randomPIN = (int)(Math.random()*9000)+1000;
		 String id = String.valueOf(randomPIN);
		 
		 if(uniquePins.contains(id))
			 return generateUniqueID();
		 else
			 return id;
	}
	
}
