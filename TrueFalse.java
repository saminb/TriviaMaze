package QuestionDatabase;

import java.util.Scanner;

public class TrueFalse extends Question {

	private String question;
	private String[] choices;
	private String answer;
	private boolean alreadyAsked;
	private String type;

	public TrueFalse(String theQuestion, String theAnswer, String[] theChoices) {
		this.question = theQuestion;
		this.answer = theAnswer;
		this.choices = new String[2];
		this.alreadyAsked = false;
		this.type = "True_False";
	}
	
	public String askQuestion() {
		String myQuestion = "True or False: " + question + "\n T, \n F";
		return myQuestion;
	}
	
	public void setChoice(String theChoice, int theIndex) {
		choices[theIndex] = theChoice;
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
