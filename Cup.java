///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MeasuringCupsSolver.java
// File:             Cup.java
// Semester:         Fall 2016
//
// Author:           Eric Chan
// CS Login:         echan
// Lecturer's Name:  Deb Deppeler
// Lab Section:      Lecture 002
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jie Shen Ong
// Email:            jong4@wisc.edu
// CS Login:         jieo
// Lecturer's Name:  Deb Deppeler
// Lab Section:      Lecture 002
//
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * A representation of a measuring cup.
 */
public class Cup {

	private int capacity;
	private int currentAmount;

	/**
	 * Construct a measuring cup
	 * 
	 * @param capacity
	 *            the maximum volume of the measuring cup
	 * @param currentAmount
	 *            the current volume of fluid in the measuring cup
	 * @throws IllegalArgumentException
	 *             when any of these conditions are true: capacity < 0,
	 *             currentAmount < 0, currentAmount > capacity
	 * 
	 */
	public Cup(int capacity, int currentAmount) {
		// check if capacities are out of range. 
		if( capacity < 0|| currentAmount < 0|| currentAmount > capacity){
			// if they are then throw exception
						throw new IllegalArgumentException();
		}else{
			// set capacity according to what is defined by user	
			this.capacity=capacity;
			// set capaacity according to what is defined by user
			this.currentAmount = currentAmount;
		}
	}

	/**
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return currentAmount
	 */
	public int getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * Compare this cup against another cup
	 * 
	 * @param cup
	 *            an other cup to compare against this cup
	 * @return true if the other cup has the same capacity and currentAmount as
	 *         this cup and false otherwise
	 */
	public boolean equals(Cup cup) {
		// if both cup's capacity and current amount are equal, they are equal
		if(cup.capacity==this.capacity&&cup.currentAmount==this.currentAmount){
			// if specifications are equal, return true
			return true;
		}else{
			// return false if cups are not equal
		return false;
		}
	}

	/**
	 * @return a string containing the currentAmount
	 */
	public String toString() {
		return String.valueOf(this.currentAmount);
	}
}
