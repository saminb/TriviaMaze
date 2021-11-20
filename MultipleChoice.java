package QuestionDatabase;
import java.util.*;

public class MultipleChoice extends Question {
	private String[] theChoices;
	private String question;

	public MultipleChoice(String QID,String type,String question, String answer, String[] choices) {
		super(QID,type, question,answer);
		this.theChoices = new String[4];
		this.question= question;
	}
	
	public void setChoice(String choices, int theIndex) {
		theChoices[theIndex] = choices;
	}
	
	public String[] getChoices() {
		return theChoices;
	}
	
	public String askQuestion() {
		String myQuestion = question + " 1): "; 
		return myQuestion;
	}
	
	public boolean processAnswer() {
		Scanner console = new Scanner(System.in);
		int choice = console.nextInt();
		return false;
		
	}
	
}