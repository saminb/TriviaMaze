package question;

/**
 * TrueFalse represents an implementation of the Question abstract class that has the structure of 
 * a True - False style question. The significant difference in this implementation is that there are
 * two set answer choices and the order of the choices are static: True, False.
 * 
 * TrueFalse will be one of the types of questions that users are prompted with in Trivia Maze.
 * 
 * @author joshu
 * @author samin
 * @version
 *
 */
public class TrueFalse extends Question {

	/**
	 * The list of answer choices for the question: True and False.
	 */
	private String[] myChoices;

	/** Constructs a True - False Question Object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (TF, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 * @param theChoices	The possible answers to choose from.
	 */
	public TrueFalse(String theQID, String theType, String theQuestion, String theAnswer, String[] theChoices) {
		super(theQID, theType, theQuestion, theAnswer);
		this.myChoices = new String[2]; // Should True False choices be decided here or in QDB manager?
		myChoices[0] = "True";
		myChoices[1] = "False";
	}
	
	/**
	 * Retrieves the list of possible answers.
	 * @return	the array of answer choices.
	 */
	public String[] getChoices() {
		return myChoices;
	}
	
	/**
	 * Sets the choice at the given index to be the given choice.
	 * @param theChoice the choice to put into the list.
	 * @param theIndex	the index to put the choice in.
	 */
	public void setChoice(String theChoice, int theIndex) {
		myChoices[theIndex] = theChoice;
	}
}
