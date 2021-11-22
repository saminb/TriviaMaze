package QuestionDatabase;

import java.util.Scanner;

public class ShortAnswer extends Question {

	public ShortAnswer(String theQID,String theType,String theQuestion, String theAnswer) {
		super(theQID, theType, theQuestion, theAnswer);
		
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
