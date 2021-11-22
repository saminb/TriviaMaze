package QuestionDatabase;

public class TrueFalse extends Question {

	// List of answer choices for this question.
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
	 * Opens the question GUI for a T/F Question and updates the Question's properties. Labels the question as asked.
	 */
	public void askQuestion() {
		TFQuestionGUI questionGUI = new TFQuestionGUI(this);
		this.setAsked();
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
	
	/* public boolean processAnswer(String theAnswer) {
		if (theAnswer == this.getAnswer()) {
			this.setAnsweredResult(true);
			return true;
		}
		this.setAnsweredResult(false);
		return false;
	} Abstract away to the Question abstract class.*/

	
}