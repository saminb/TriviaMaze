package QuestionDatabase;

import java.util.Scanner;

public class ShortAnswer extends Question {
	
	private String question;
	private String answer;
	private boolean alreadyAsked;
	private String type;

	public ShortAnswer(String theQuestion, String theAnswer) {
		this.question = theQuestion;
		this.answer = theAnswer;
		this.alreadyAsked = false;
		this.type = "Short_Answer";
	}
	
	public String askQuestion() {
		return this.question;
	}
	
	public boolean processAnswer() {
		Scanner console = new Scanner(System.in);
		String theResponse = console.next();
		if (theResponse.equals(this.answer)) {
			return true;
		}
		return false;
	}

}
