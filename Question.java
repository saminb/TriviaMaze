public abstract class Question {
	private String type;
	protected String question;
	protected String answer;
	protected String[] choices;
	private boolean alreadyAsked;
	
	
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
	
	abstract void askQuestion();
}