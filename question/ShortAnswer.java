package question;

public class ShortAnswer extends Question {

	/**
	 * Constructs a Short Answer question object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (T/F, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 */
	public ShortAnswer(String theQID,String theType, String theQuestion, String theAnswer) {
		super(theQID, theType, theQuestion, theAnswer);
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
