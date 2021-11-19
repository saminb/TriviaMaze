package QuestionDatabase;

public abstract class Question {
	private String type;
	private String question;
	private String answer;
	private boolean alreadyAsked;
	private String QID;
	
	
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
	
	public void setAsAsked() {
		this.alreadyAsked = true;
	}
	
	public boolean getAskedStatus() {
		return this.alreadyAsked;
	}
	
	abstract String askQuestion();
	
	abstract boolean processAnswer();
	
	public String getQID() {
		return this.QID;
	}
	
	public void setQID(String theQID) {
		this.QID = theQID;
	}

	
}
