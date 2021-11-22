package QuestionDatabase;

public abstract class Question {
	private String myType;
	private String myQuestion;
	private String myAnswer;
	private String myQID;
	private boolean hasBeenAsked;
	private boolean hasCorrectAnswer;
	
	public Question(String theQID,String theType,String theQuestion, String theAnswer) {
		this.myType 	= theType;
        this.myQuestion = theQuestion;
        this.myAnswer 	= theAnswer;
        this.myQID		= theQID;
        hasBeenAsked = false;
    }

	public String getQuestion() {
		return this.myQuestion;
	}
	
	public String getAnswer() {
		return this.myAnswer;
	}
	
	public void setQuestion(String theQuestion) {
		this.myQuestion = theQuestion;
	}
	
	public void setAnswer(String theAnswer) {
		this.myAnswer = theAnswer;
	}
	
	public String getType() {
		return this.myType;
	}
	
	public String getQID() {
		return this.myQID;
	}
	
	public void setQID(String theQID) {
		this.myQID = theQID;
	}
	
	public void setAsked() {
		this.hasBeenAsked = true;
	}
	
	public boolean getAsked() {
		return this.hasBeenAsked;
	}
	
	public void setAnsweredResult(boolean theResult) {
		this.hasCorrectAnswer = theResult;
	}
	
	public boolean getAnsweredResult() {
		return this.hasCorrectAnswer;
	}
	
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
