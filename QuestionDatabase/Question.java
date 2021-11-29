package QuestionDatabase;

public abstract class Question {
	private String myType;
	private String myQuestion;
	private String myAnswer;
	private String myQID;
	private boolean hasBeenAsked;
	private boolean hasCorrectAnswer;
	
	/**
	 * Abstract constructor for the Question object with the given parameters.
	 * Initializes the other fields of the question object.
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (TF, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 */
	public Question(String theQID,String theType,String theQuestion, String theAnswer) {
		this.myType 	= theType;
        this.myQuestion = theQuestion;
        this.myAnswer 	= theAnswer;
        this.myQID		= theQID;
        this.hasBeenAsked = false;
    }
	
	/**
	 * Retrieves the question to be asked.
	 * 
	 * @return the question.
	 */
	public String getQuestion() {
		return this.myQuestion;
	}
	
	/**
	 * Retrieves the answer to the question.
	 * 
	 * @return the answer.
	 */
	public String getAnswer() {
		return this.myAnswer;
	}
	
	/**
	 * Sets the Question object's question to theQuestion given.
	 * 
	 * @param theQuestion the new question.
	 */
	public void setQuestion(String theQuestion) {
		this.myQuestion = theQuestion;
	}
	
	/**
	 * Sets the Question object's answer to theAnswer given.
	 * 
	 * @param theAnswer the new answer.
	 */
	public void setAnswer(String theAnswer) {
		this.myAnswer = theAnswer;
	}
	
	/**
	 * Retrieves the question type of the question.
	 * 
	 * @return the type (MC, SA, TF).
	 */
	public String getType() {
		return this.myType;
	}
	
	/**
	 * Retrieves the Question ID of the question.
	 * @return the QID.
	 */
	public String getQID() {
		return this.myQID;
	}
	
	/**
	 * Sets the QID of the question to the given QID.
	 * @param theQID the new QID.
	 */
	public void setQID(String theQID) {
		this.myQID = theQID;
	}
	
	public void setAsked() {
		this.hasBeenAsked = true;
	}
	
	public boolean getAsked() {
		return this.hasBeenAsked;
	}
	
	/**
	 * Sets the result of the user's answer to the result; true if the answer was correct, false otherwise.
	 * 
	 * @param theResult the correctness of the user's answer.
	 */
	public void setAnsweredResult(boolean theResult) {
		this.hasCorrectAnswer = theResult;
	}
	
	/**
	 * Retrieves the correctness of the user's answer.
	 * @return the correctness.
	 */
	public boolean getAnsweredResult() {
		return this.hasCorrectAnswer;
	}
	
	/**
	 * Processes the given answer and returns true if the answer is correct (equal to the question
	 * object's answer), and false otherwise. Updates the question object accordingly.
	 * 
	 * @param theAnswer the user's answer.
	 * @return true if the answer is correct, false otherwise.
	 */
	public boolean processAnswer(String theAnswer) {
		if (theAnswer == this.getAnswer()) {
			this.setAnsweredResult(true);
			return true;
		}
		this.setAnsweredResult(false);
		return false;
	}
	
	
	public abstract void askQuestion();
	
}
