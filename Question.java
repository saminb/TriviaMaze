package QuestionDatabase;

public abstract class Question {
	private String type;
	private String question;
	private String answer;
	private String QID;
	
	public Question(String QID,String type,String question, String answer) {
		this.type 		= type;
        this.question 	= question;
        this.answer 	= answer;
        this.QID		= QID;
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

	
}
