package QuestionDatabase;
import java.util.*;

public class MultipleChoice extends Question {
	private String[] myChoices;
	private String myQuestion;

	public MultipleChoice(String theQID,String theType,String theQuestion, String theAnswer, String[] theChoices) {
		super(theQID,theType, theQuestion, theAnswer);
		this.myChoices = new String[4];
		this.myQuestion = theQuestion;
	}
	
	public void setChoice(String choices, int theIndex) {
		myChoices[theIndex] = choices;
	}
	
	public String[] getChoices() {
		return myChoices;
	}
	
	public void askQuestion() {
		MCQuestionGUI myGUI = new MCQuestionGUI(this);
		this.setAsked();
		
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