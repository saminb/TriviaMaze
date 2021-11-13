public abstract class Question {
	protected String type;
	protected String question;
	protected String answer;
	protected String[] choices;
	protected boolean alreadyAsked;
	protected int QID;
	
	
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
	
	public int getQID() {
		return this.QID;
	}
	
	public void setQID(int theQID) {
		this.QID = theQID;
	}
	
}