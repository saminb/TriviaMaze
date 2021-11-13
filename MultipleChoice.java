import java.util.*;

public class MultipleChoice extends Question {

	public MultipleChoice(String theQuestion, String theAnswer, String[] theChoices) {
		this.question = theQuestion;
		this.answer = theAnswer;
		this.choices = new String[3];
		this.alreadyAsked = false;
		this.type = "Multiple_Choice";
	}
	
	public void setChoice(String theChoice, int theIndex) {
		choices[theIndex] = theChoice;
	}
	
	public String askQuestion() {
		String myQuestion = question + " 1): "; // How do we want to display the choices?
												// Should the answer be doubly present?
		return myQuestion;
	}
	
	public boolean processAnswer() {
		Scanner console = new Scanner(System.in); // Should MC questions be an int or a string?
		int choice = console.nextInt();
		return false;
		
	}
	
}
