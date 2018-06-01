///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MeasuringCupsSolver.java
// File:             MeasuringCupsState.java
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
 * A class representing a state of volumes of cupA and cupB.
 */
public class MeasuringCupsPuzzleState {

	private Cup cupA;
	private Cup cupB;
	private MeasuringCupsPuzzleState parentState;

	/**
	 * Construct an object representing the state of two measuring cups and
	 * their previous state
	 * 
	 * @param CupA
	 *            object containing the currentAmount in CupA {@link Cup}
	 * @param CupB
	 *            see CupA {@link Cup}
	 * @param parentState
	 *            an object from this class representing the previous state
	 */
	public MeasuringCupsPuzzleState(Cup CupA, Cup CupB, MeasuringCupsPuzzleState parentState) {
		this.cupA = CupA;
		this.cupB = CupB;
		this.parentState = parentState;
	}

	/**
	 * @return cupA
	 */
	public Cup getCupA() {
		return cupA;
	}

	/**
	 * @return cupB
	 */
	public Cup getCupB() {
		return cupB;
	}

	/**
	 * @return parentState
	 */
	public MeasuringCupsPuzzleState getParentState() {
		// TODO
		
		return parentState;
		
	}

	/**
	 * Compare this state against another state
	 * 
	 * @param measuringCupsPuzzleState
	 *            another object of the same class to compare this object to
	 * @return true if this state's cupA and cupB are the same as the other
	 *         state's cupA and cupB and false otherwise
	 */
	public boolean equals(MeasuringCupsPuzzleState measuringCupsPuzzleState) {
		//if this state cupA and B and the states that passes through parameter A and B 
		//is equal, return true false otherwise
		if(measuringCupsPuzzleState.cupA.equals(this.cupA)&&measuringCupsPuzzleState.cupB.equals(this.cupB)){
			return true;
		}else{
		return false;
		}
	}

	/**
	 * @return a string containing cupA and cupB string representations as a
	 *         pair: (cupA, cupB)
	 */
	public String toString() {
		return "(" + this.cupA.toString() + ", " + this.cupB.toString() + ")";
	}
}
