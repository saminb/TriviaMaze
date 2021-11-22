package QuestionDatabase;

public abstract class Question {
	private String type;
	private String question;
	private String answer;
	private String QID;
	private boolean hasBeenAsked;
	private boolean answeredCorrectly;
	
	public Question(String QID,String type,String question, String answer) {
		this.type 		= type;
        this.question 	= question;
        this.answer 	= answer;
        this.QID		= QID;
        hasBeenAsked = false;
    }

	public String getQuestion() {
		return this.question;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void setQuestion(String theQuestion) {
		this.question = theQuestion;
	}
	
	public void setAnswer(String theAnswer) {
		this.answer = theAnswer;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getQID() {
		return this.QID;
	}
	
	public void setQID(String theQID) {
		this.QID = theQID;
	}
	
	public void setAsked() {
		this.hasBeenAsked = true;
	}
	
	public boolean getAsked() {
		return this.hasBeenAsked;
	}
	
	public void setAnsweredResult(boolean theResult) {
		this.answeredCorrectly = theResult;
	}
	
	public boolean getAnsweredResult() {
		return this.answeredCorrectly;
	}
	

	
}
