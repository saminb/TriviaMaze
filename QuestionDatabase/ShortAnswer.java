package QuestionDatabase;

public class ShortAnswer extends Question {

	/**
	 * Constructs a Short Answer question object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (TF, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 */
	public ShortAnswer(String theQID,String theType, String theQuestion, String theAnswer) {
		super(theQID, theType, theQuestion, theAnswer);
	}
	
	/** 
	 * Opens the question GUI for a SA Question and updates the Question's properties. Labels the question as asked.
	 */
	public void askQuestion() {
		SAQuestionGUI questionGUI = new SAQuestionGUI(this);
		this.setAsked();
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
