package question;

/**
 * ShortAnswer represents an implementation of the Question abstract class that has the structure of 
 * a Short Answer style question. The significant difference in this implementation is that there are
 * no set answer choices; ShortAnswer instead takes users input for the answer.
 * 
 * ShortAnswer will be one of the types of questions that users are prompted with in Trivia Maze.
 * 
 * @author joshu
 * @version
 *
 */
public class ShortAnswer extends Question {

	/**
	 * Constructs a Short Answer question object with the given parameters:
	 * 
	 * @param theQID		The unique ID of the question.
	 * @param theType		The type of the question (T/F, MC, SA).
	 * @param theQuestion	The actual question to be asked.
	 * @param theAnswer		The answer to the question.
	 */
	public ShortAnswer(String theQID,String theType, String theQuestion, String theAnswer) {
		super(theQID, theType, theQuestion, theAnswer);
	}
}
