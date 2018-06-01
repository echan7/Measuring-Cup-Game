///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MeasuringCupsSolver.java
// File:             MeasuringCupsPuzzle.java
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

/**
 * A class describing the measuring cups puzzle with a startState
 * {@link MeasuringCupsPuzzleState} and a goalState
 * {@link MeasuringCupsPuzzleState}
 */
public class MeasuringCupsPuzzle {

	private MeasuringCupsPuzzleState startState;
	private MeasuringCupsPuzzleState goalState;

	private MeasuringCupsPuzzleADT measuringCupsPuzzleADT;

	private MeasuringCupsPuzzleStateList pathFromStartToGoal;
	private MeasuringCupsPuzzleStateList processedStates;
	private MeasuringCupsPuzzleState foundGoalState;

	/**
	 * Construct a puzzle object by describing the startState and goalState
	 *
	 * @param startState
	 *            a state describing the capacities and initial volumes of
	 *            measuring cups {@link MeasuringCupsPuzzleState}
	 * @param goalState
	 *            a state describing the desired end volumes of measuring cups
	 *            {@link MeasuringCupsPuzzleState}
	 */
	public MeasuringCupsPuzzle(MeasuringCupsPuzzleState startState, MeasuringCupsPuzzleState goalState) {
		this.startState = startState;
		this.goalState = goalState;

		this.pathFromStartToGoal = new MeasuringCupsPuzzleStateList();
		this.processedStates = new MeasuringCupsPuzzleStateList();
		this.foundGoalState = null;
		this.measuringCupsPuzzleADT = null;
	}

	/**
	 * Solve the measuring cups puzzle if it can be solved. Set processedStates
	 * by adding a {@link MeasuringCupsPuzzleState} graph node to the list as
	 * the algorithm visits that node. Set foundGoalState to a
	 * {@link MeasuringCupsPuzzleState} if the graph traversal algorithm labeled
	 * by *algorithm* visits a node with the same values as the desired
	 * goalState
	 *
	 * @param algorithm
	 *            a String describing how the puzzle will be solved; has a value
	 *            equal to the project configuration {@link Config} BFS or DFS;
	 *            e.g. "BFS"
	 * @return true if the puzzle can be solved (and has been solved, see
	 *         {@link retrievePath} to obtain the solution stored in this
	 *         object) and false otherwise
	 */
	public boolean findPathIfExists(String algorithm) {
		//call reset function to reset the variables value to the initial value
			resetCupPuzzle();
		//define the ADT used depending on the algorithm needed
			chooseADT(algorithm);
		//set start state as visited and add to ADT
			measuringCupsPuzzleADT.add(startState);
		//temporary variable to store the current state off the iteration
			MeasuringCupsPuzzleState current;
		/**
		 * remove the first item of the queue and set it as current
		 * if the remove state is the same as the final goal state
		 * inteded to reach. set found goal state to current
		 */
			while(!measuringCupsPuzzleADT.isEmpty()&& foundGoalState == null){
				current = measuringCupsPuzzleADT.remove();
				if(current.equals(goalState)){
					foundGoalState = current;		

				/**
				 * for each unvisited successor of the current state
				 * set it as visited and add to the ADT
				 */
				}else if(!isProcessed(current)){
					
					Iterator<MeasuringCupsPuzzleState> itr = getSuccessors(current).iterator();
					while(itr.hasNext()){
						measuringCupsPuzzleADT.add((MeasuringCupsPuzzleState)itr.next());
					}
					processedStates.add(current);
				}
				
					
			}
			
			/**
			 * if foundgoalstate == null, then return false because there is no path existed
			 * else return true as path does exist
			 */
			if(foundGoalState!=null){
				return true;
			}else{
				return false;
			}
			
	}

	/**
	 * Set member measuringCupsPuzzleADT {@link MeasuringCupsPuzzleADT} with a
	 * data type that will be used to solve the puzzle.
	 *
	 * @param algorithm
	 *            a String describing how the puzzle will be solved; has a value
	 *            equal to the project configuration {@link Config} BFS or DFS;
	 *            e.g. "BFS"
	 */
	private void chooseADT(String algorithm) {
		if(algorithm == Config.DFS){
			//declare new stack for the ADT for DFS algorithm
			measuringCupsPuzzleADT = new MeasuringCupsPuzzleStack();
		}else if(algorithm == Config.BFS){
			//declare new queue for the ADT for BFS algorithm
			measuringCupsPuzzleADT = new MeasuringCupsPuzzleQueue();
		}else{
			//throw illegal argument exception for wrong parameters
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Reset the puzzle by erasing all member variables which store some aspect
	 * of the solution (pathFromStartToGoal, processedStates, and
	 * foundGoalState) and setting them to their initial values
	 */
	private void resetCupPuzzle() {
		//clear the path and visited states, and foundgoal state to null
		pathFromStartToGoal.clear();
		processedStates.clear();
		foundGoalState = null;
	}

	/**
	 * Mark the graph node represented by currentState as visited for the
	 * purpose of the graph traversal algorithm being used to solve the puzzle
	 * (set by {@link chooseADT})
	 *
	 * @param currentState
	 *            {@link MeasuringCupsPuzzleState}
	 * @return true if the currentState has been visited and false otherwise
	 */
	private boolean isProcessed(MeasuringCupsPuzzleState currentState) {
			Iterator<MeasuringCupsPuzzleState> processedStateItr = processedStates.iterator();
			
			while(processedStateItr.hasNext()){
				//check if the states passes through the parameter is in the list, return true
				//if found
				if(processedStateItr.next().equals(currentState)){
					return true;
				}
			}
		return false;
	}

	/**
	 * Assuming {@link findPathIfExists} returns true, return the solution that
	 * was found. Set pathFromStartToGoal by starting at the foundGoalState and
	 * accessing/setting the current node to the parentState
	 * {@link MeasuringCupsPuzzleState#getParentState} until reaching the
	 * startState
	 *
	 * @return a list of states {@link MeasuringCupsPuzzleStateList}
	 *         representing the changes in volume of cupA and cupB from the
	 *         initial state to the goal state.
	 */
	public MeasuringCupsPuzzleStateList retrievePath() {
		//declare a new list to stored the path in reversed order
		MeasuringCupsPuzzleStateList reversedPath = new MeasuringCupsPuzzleStateList();
		
		/**
		 * used foundGoalState as a temporary variable for current state 
		 * if foundGoal stae is not the start state, add it into the list
		 * foundgoalstate assigned to its parent state
		 */
		while(!foundGoalState.equals(startState)){
			reversedPath.add(foundGoalState);
		    foundGoalState = foundGoalState.getParentState();
			if(foundGoalState.equals(startState)){
				//if the parent state is the start state, add it into
				//the list
				reversedPath.add(startState);
			}
		}
		//reverse the path calling the reverse method
		reversedPath.reverse();
		//add everythiung in the list into pathFrom start to goal
		Iterator<MeasuringCupsPuzzleState> itr = reversedPath.iterator();
		while(itr.hasNext()){
			pathFromStartToGoal.add((MeasuringCupsPuzzleState)itr.next());
		}
	
		//return the list
		return pathFromStartToGoal;
	}

	/**
	 * Enumerate all possible states that can be reached from the currentState
	 *
	 * @param currentState
	 *            the current volumes of cupA and cupB
	 * @return a list of states {@link MeasuringCupsPuzzleStateList} that can bepathFromStartToGoal.add(current);
	 *         reached by emptying cupA or cupB, pouring from cupA to cupB and
	 *         vice versa, and filling cupA or cupB to its max capacity
	 */
	public MeasuringCupsPuzzleStateList getSuccessors(MeasuringCupsPuzzleState currentState) {
		MeasuringCupsPuzzleStateList successors = new MeasuringCupsPuzzleStateList();
		
	
		if (currentState == null) {                                                                                                                                 
			return successors;
		}
		
		/**
		 * for every state, add its succesor by calling the fillCup method
		 * only if its not equal to its current state
		 */
		// only add states if they do not equal to the current state
		// Fill CupA
		if(!fillCupA(currentState).equals(currentState)){
		successors.add(fillCupA(currentState));
		}
		// Fill CupB
		if(!fillCupB(currentState).equals(currentState)){
		successors.add(fillCupB(currentState));
		}

		// Empty CupA
		if(!emptyCupA(currentState).equals(currentState)){
		successors.add(emptyCupA(currentState));
		}
		// Empty CupB
		if(!emptyCupB(currentState).equals(currentState)){
		successors.add(emptyCupB(currentState));
		}

		// Pour from CupA to CupB
		if(!pourCupAToCupB(currentState).equals(currentState)){
		successors.add(pourCupAToCupB(currentState));
		}

		// Pour from CupB to CupA
		if(!pourCupBToCupA(currentState).equals(currentState)){
		successors.add(pourCupBToCupA(currentState));
		}
		

		
		return successors;
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState by filling cupA to its max
	 *         capacity
	 */
	public MeasuringCupsPuzzleState fillCupA(MeasuringCupsPuzzleState currentState) {
		// create a new cup that his filled and call cup A
		Cup A = new Cup(currentState.getCupA().getCapacity(),currentState.getCupA().getCapacity());
		// create a new cup that is the same as current state's cup B
		Cup B = new Cup(currentState.getCupB().getCapacity(),currentState.getCupB().getCurrentAmount());
		
		// return a new state that results from filling cup A of the current state
		return new MeasuringCupsPuzzleState( A, B, currentState) ;
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState by filling cupB to its max
	 *         capacity
	 */
	public MeasuringCupsPuzzleState fillCupB(MeasuringCupsPuzzleState currentState) {
		// create a new cup that his filled and call cup B
		Cup B = new Cup(currentState.getCupB().getCapacity(),currentState.getCupB().getCapacity());
		// create a new cup that is the same as current state's cup A
		Cup A = new Cup(currentState.getCupA().getCapacity(),currentState.getCupA().getCurrentAmount());
		// return a new state that results from filling cup B of the current state
		return new MeasuringCupsPuzzleState( A, B, currentState) ;
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState by emptying cupA
	 */
	public MeasuringCupsPuzzleState emptyCupA(MeasuringCupsPuzzleState currentState) {
		//create a new cup that is empty and call cupA
		Cup A = new Cup(currentState.getCupA().getCapacity(),0);
		//create a new cup that is the same as current state's cup B
		Cup B = new Cup(currentState.getCupB().getCapacity(),currentState.getCupB().getCurrentAmount());
		//return a new state that results form emptying cupA of the state
		return new MeasuringCupsPuzzleState( A, B, currentState) ;
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState by emptying cupB
	 */
	public MeasuringCupsPuzzleState emptyCupB(MeasuringCupsPuzzleState currentState) {
		//create a new cup that is empty and call cupB
		Cup B = new Cup(currentState.getCupB().getCapacity(),0);
		//create a new cup that is the same as current state's cup A
		Cup A = new Cup(currentState.getCupA().getCapacity(),currentState.getCupA().getCurrentAmount());
		//return a new state that results form emptying cupB of the state
		return new MeasuringCupsPuzzleState( A, B, currentState) ;
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState pouring the currentAmount
	 *         of cupA into cupB until either cupA is empty or cupB is full
	 */
	public MeasuringCupsPuzzleState pourCupAToCupB(MeasuringCupsPuzzleState currentState) {
		
		/**
		 * store the current amount in cupA and cupB in new integers variable
		 * use simple arrithmethic to add cup B amount and cupA
		 * cupA new amount = cupB new amount - its capacity
		 * if negative set cupA to 0
		 */
		int amountCupA = currentState.getCupA().getCurrentAmount();
		int amountCupB = currentState.getCupB().getCurrentAmount();
		int newAmountCupB = amountCupA+amountCupB;
		int newAmountCupA = newAmountCupB - currentState.getCupB().getCapacity();
		if(newAmountCupA<=0){
			newAmountCupA = 0; 
		}
		 //new cupB amount - newcupA amount 
		 newAmountCupB -=newAmountCupA;
		 
		//create a new CupA of its capacity and its new amount
		Cup A = new Cup(currentState.getCupA().getCapacity(), newAmountCupA);
		//create a new CupB of its capacity and its new amount
		Cup B = new Cup(currentState.getCupB().getCapacity(), newAmountCupB);
		//return a new state of new A, B and current state as parent state
		return new MeasuringCupsPuzzleState(A, B, currentState);
	}

	/**
	 * @param currentState
	 * @return a new state obtained from currentState pouring the currentAmount
	 *         of cupB into cupA until either cupB is empty or cupA is full
	 */
	public MeasuringCupsPuzzleState pourCupBToCupA(MeasuringCupsPuzzleState currentState) {
		/**
		 * store the current amount in cupA and cupB in new integers variable
		 * use simple arrithmethic to add cup B amount and cupA
		 * cupB new amount = cupA new amount - its capacity
		 * if negative set cupB to 0
		 */
		int amountCupA = currentState.getCupA().getCurrentAmount();
		int amountCupB = currentState.getCupB().getCurrentAmount();
		int newAmountCupA = amountCupA+amountCupB;
		int newAmountCupB = newAmountCupA - currentState.getCupA().getCapacity();
		if(newAmountCupB<0){
			newAmountCupB = 0; 
		}
		 //new cupA amount - newcupA amount 
		 newAmountCupA-=newAmountCupB;

		 
		//create a new CupA of its capacity and its new amount
		Cup A = new Cup(currentState.getCupA().getCapacity(), newAmountCupA);
		//create a new CupB of its capacity and its new amount
		Cup B = new Cup(currentState.getCupB().getCapacity(), newAmountCupB);
		//return a new state of new A, B and current state as parent state
		return new MeasuringCupsPuzzleState(A, B, currentState);
	}
}


