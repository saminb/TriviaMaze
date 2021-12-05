/**
 * 
 */
package question;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author samin
 *
 */
public class QuestionDatabaseManagerTest {
	private static final String dbName= "triviamaze_test.db";
	private static QuestionDatabaseManager test = null;
	
	@Before
	public void before() throws SQLException {
		test= new QuestionDatabaseManager(dbName);
		
	}
	@Test
	public void getLastQuestionTester() throws SQLException {
		LinkedList<Question> list = new LinkedList<Question>();
			list.add(test.getQuestion());
			Question q1 = list.getLast();
			Question q2 = test.getLastQuestion();
			assertTrue(questionsEqual(q1, q2));
			
		}
		
		 
	
	@Test
	public void poseQuestionTester() throws SQLException { 

	}
	
	
	@Test
	public void getTotalQuestionTest() throws SQLException {
		int count = test.getTotalQuestionCount();
		after();

		assertEquals(6,count);
		
	}
	@Test
	public void getQuestionQueueTest() throws SQLException {
		String testChoices[]= {"Stephen Fry","Bill Gates","Stephen Hawking","Steve Jobs"};
		String testChoices2[]= {"Charlie Puth","Harry Potter","Karl Marx","Charles Babbage"};
		String testTorF[]= {"T","F"};
		Question[] expected= { new ShortAnswer("Q1","SA","World-wide, what language is used the most on the internet?","English"),
							   new ShortAnswer("Q2","SA","In terms of computing, what does CPU stand for? ","Central Processing Unit"),
							   new MultipleChoice("Q46", "MC", "Who founded Apple Computer?","Steve Jobs",testChoices),
							   new MultipleChoice("Q47", "MC", "Who is known as the father of computers?","Charles Babbage",testChoices2),
							   new TrueFalse("Q73", "T/F"," VOIP (Voice Over Internet Protocol) is a type of modem.","F",testTorF),
							   new TrueFalse("Q74", "T/F", " Microcomputers have largely replaced minicomputers ", "T",testTorF)					   
		};
		Queue<Question>temp = test.getQuestionsQueue();
		
	}
	private boolean questionsEqual(Question question1, Question question2) {
		if (!question1.toString().equals(question2.toString())) {
			return false;
		}
		return true;
	}
	@Test
	public void getQuestionTest() {
		Question[] questions1 = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions1[i] = test.getQuestion();
		}
		Question[] questions2 = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions2[i] = test.getQuestion();
		}
		assertNotEquals(Arrays.toString(questions1), Arrays.toString(questions2));
	}
	@Test
	public void getQuestionReuseTest() {
		for (int i = 0; i < test.getTotalQuestionCount() * 10; i++) {
			test.getQuestion();
		}
	}

	
	@After
    public  void after() throws SQLException{
		test.getDB().closeConnection();
    }

}