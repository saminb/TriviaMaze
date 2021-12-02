package QuestionDatabase;

import java.util.*;

public class QGUITester {
	
	//Thoughts: Current answer handling is done by taking the string contents of the input (True, False for TF,
	// the answer in String for MC, the input for SA, and comparing it to the given answer. Should they be int comparisons/other ways?
	public static void main(final String[] theArgs) {
		Scanner input = new Scanner(System.in);
		System.out.print("Which test?: 1) MC, 2) SA, 3) TF, 4) All?");
		int choice = input.nextInt();
		if (choice == 1) {
			MCTest();
		} else if (choice == 2) {
			SATest();
		} else if (choice == 3) {
			TFTest();
		} else {
			MCTest();
			SATest();
			TFTest();
		}
	}
	
	public static void MCTest() {
		
		QuestionLog questionLog = new QuestionLog();
		String[] testMCChoices = new String[4];
		testMCChoices[0] = "Four";
		testMCChoices[1] = "Two";
		testMCChoices[2] = "Three";
		testMCChoices[3] = "Five";
		MultipleChoice testMCQ = new MultipleChoice("123", "MC", "How many fingers do I have?", "Five", testMCChoices);
		
		/*String[] theChoices = testMCQ.getChoices();
		for (String s: theChoices) {
			System.out.println(s);
		}*/
		testMCQ.askQuestion(); //Ask question is void because the way the response is handled is that
							   // The question object's answered correctly field is updated.1
	}
	
	// Thoughts: Should the question type be a parameter or implied by the constructor?
	// Should we do type matching and have "Eight" and "8" be valid answers? Case sensitive?
	public static void SATest() {
		ShortAnswer testSAQ = new ShortAnswer("324", "SA", "How many planets?", "Eight");
		testSAQ.askQuestion();
	}
	
	//Thoughts: Should True and False choices be passed as parameters, or should they be implied/
	// 			hard-coded into the constructor?
	public static void TFTest() {
		String[] choices = new String[2];
		choices[0] = "True";
		choices[1] = "False";
		TrueFalse testTFQ = new TrueFalse("1234", "TF", "I love coding", "False", choices);
		testTFQ.askQuestion();
	}
}
