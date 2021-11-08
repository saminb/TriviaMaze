import java.util.Scanner;

public class TrueFalse extends Question {

	public TrueFalse(String theQuestion, String theAnswer, String[] theChoices) {
		this.question = theQuestion;
		this.answer = theAnswer;
		this.choices = new String[2];
	}
	
	public void askQuestion() {
		String intro = "True or False: " + question + "\n T, \n F";
		System.out.println(intro);
	}
	
	private boolean processAnswer() {
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

