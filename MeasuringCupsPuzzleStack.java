///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MeasuringCupsSolver.java
// File:             MeasuringCupsStack.java
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
import java.util.Iterator;
import java.util.Stack;

/**
 * A stack of MeasuringCupsPuzzleState nodes
 */
public class MeasuringCupsPuzzleStack implements MeasuringCupsPuzzleADT {

	private Stack<MeasuringCupsPuzzleState> stack;

	/**
	 * Construct a new stack
	 */
	public MeasuringCupsPuzzleStack() {
		this.stack = new Stack<MeasuringCupsPuzzleState>();
	}

	/**
	 * @param state
	 *            the node to add to the stack
	 */
	@Override
	public void add(MeasuringCupsPuzzleState state) {
		stack.push(state);
	}

	/**
	 * @return the node that was inserted into the stack most recently, which
	 *         has been removed from the stack as a result of this function call
	 */
	@Override
	public MeasuringCupsPuzzleState remove() {
		
		return stack.pop();
	}

	/**
	 * @return true if this stack is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// TODO
		return stack.isEmpty();
	}

	/**
	 * Update the stack by removing all of the nodes in it
	 */
	@Override
	public void clear() {
		this.stack.clear();
	}

	/**
	 * @return a string representing the stack by calling each node/member's
	 *         toString (in LIFO order) and joining the resulting strings with a
	 *         space character as a delimiter
	 */
	public String toString() {
		Iterator<MeasuringCupsPuzzleState> stackIterator = this.stack.iterator();
		String result = "";
		while (stackIterator.hasNext()) {
			result += stackIterator.next().toString();
			if (stackIterator.hasNext()) {
				result += " ";
			}
		}
		return result;
	}

}
