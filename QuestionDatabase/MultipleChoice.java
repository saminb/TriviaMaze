package QuestionDatabase;

public class MultipleChoice extends Question {
	
	// List of answer choices for this question.
	private String[] myChoices;

	/** Constructs a Multiple Choice Question Object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (TF, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 * @param theChoices	The possible answers to choose from.
	 * @param questionLog 
	 */
	public MultipleChoice(String theQID, String theType, String theQuestion, String theAnswer, String[] theChoices) {
		super(theQID,theType, theQuestion, theAnswer);
		this.myChoices = theChoices;
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
	
	/** 
	 * Opens the question GUI for a MC Question and updates the Question's properties. Labels the question as asked.
	 */
	public void askQuestion( QuestionLog questionLog) {
		MCQuestionGUI questionGUI = new MCQuestionGUI(this, questionLog);
		this.setAsked();
		
	}

	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		
	}
	
	/* public boolean processAnswer(String theAnswer) {
		if (theAnswer == this.getAnswer()) {
			this.setAnsweredResult(true);
			return true;
		}
		this.setAnsweredResult(false);
		return false;
	} Abstract away to the Question abstract class. */
	
}