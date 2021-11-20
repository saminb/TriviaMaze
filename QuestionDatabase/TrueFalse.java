package QuestionDatabase;

import java.util.Scanner;

public class TrueFalse extends Question {

	private String[] theChoices;
	private String question;

	public TrueFalse(String QID,String type,String question, String answer, String[] trueFalseChoices) {
		super(QID,type, question,answer);
		this.theChoices = new String[2];
		this.question= question;
	}
	
	public String askQuestion() {
		String myQuestion = "True or False: " + question + "\n T, \n F";
		return myQuestion;
	}
	
	public String[] getTrueFalseChoices() {
		return theChoices;
	}
	public void setTrueFalseChoice(String trueFalseChoices, int theIndex) {
		theChoices[theIndex] = trueFalseChoices;
	}
	
	public boolean processAnswer() {
		Scanner console = new Scanner(System.in);
		int choice = console.nextInt();
		String correctAns = this.getAnswer();
		if (choice == 1) {
			if (correctAns.equals("True")) {
				return true;
			}
			return false;
		} else {
			if (correctAns.equals("False")) {
				return false;
			}
			return true;
		}
	}

	
}
