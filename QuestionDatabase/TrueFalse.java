package QuestionDatabase;

import java.util.Scanner;

public class TrueFalse extends Question {

	private String[] theChoices;
	private String question;

	public TrueFalse(String QID,String type,String question, String answer, String[] trueFalseChoices) {
		super(QID,type, question,answer);
		this.theChoices = new String[2];
		theChoices[0] = "True";
		theChoices[1] = "False";
		this.question= question;
	}
	
	public String askQuestion() {
		String myQuestion = "True or False: " + question + "\n T, \n F";
		return myQuestion;
	}
	
	public String[] getChoices() {
		return theChoices;
	}
	public void setTrueFalseChoice(String trueFalseChoices, int theIndex) {
		theChoices[theIndex] = trueFalseChoices;
	}
	
	public boolean processAnswer(String theAnswer) {
		if (theAnswer == this.getAnswer()) {
			this.setAnsweredResult(true);
			return true;
		}
		this.setAnsweredResult(false);
		return false;
	}

	
}
