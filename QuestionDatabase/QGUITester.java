package QuestionDatabase;

public class QGUITester {
	public static void main(final String[] theArgs) {
		String[] testMCChoices = new String[4];
		testMCChoices[0] = "four";
		testMCChoices[1] = "two";
		testMCChoices[2] = "THree";
		testMCChoices[3] = "Five";
		MultipleChoice testMCQ = new MultipleChoice("123", "MC", "How many fingers do I have?", "Five", testMCChoices);
		
		/*String[] theChoices = testMCQ.getChoices();
		for (String s: theChoices) {
			System.out.println(s);
		}*/
		MCQuestionGUI testMC = new MCQuestionGUI(testMCQ);
	}
}
