package QuestionDatabase;

import java.util.Scanner;

public class ShortAnswer extends Question {
	
	private String question;
	private String answer;

	public ShortAnswer(String QID,String type,String question, String answer) {
		super(QID,type, question,answer);
		this.question= question;
		this.answer= answer;
		
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
