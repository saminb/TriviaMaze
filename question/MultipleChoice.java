package question;

/**
 * MultipleChoice represents an implementation of the Question abstract class that has the structure of 
 * a Multiple Choice style question, in which the user has four choices to choose from with only one correct
 * choice. 
 * 
 * MultipleChoice will be one of the types of questions that users are prompted with in Trivia Maze.
 * 
 * @author Joshua Lee, Samin Bahizad, Logan Martinson
 * @version
 *
 */
public class MultipleChoice extends Question {
	
	/**
	 * The list of four choices for the question.
	 */
	private String[] myChoices;

	/** Constructs a Multiple Choice Question Object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (TF, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 * @param theChoices	The possible answers to choose from.
	 */
	public MultipleChoice(String theQID, String theType, String theQuestion, String theAnswer, String[] theChoices) {
		super(theQID,theType, theQuestion, theAnswer);
		myChoices = theChoices;
	}
	
	/**
	 * Sets the choice at the given index to be the given choice.
	 * @param theChoice the choice to put into the list.
	 * @param theIndex	the index to put the choice in.
	 */
	public void setChoice(String theChoices, int theIndex) {
		myChoices[theIndex] = theChoices;
	}
	
	/**
	 * Retrieves the list of possible answers.
	 * @return	the array of answer choices.
	 */
	public String[] getChoices() {
		return myChoices;
	}
}