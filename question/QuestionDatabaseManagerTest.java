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
			boolean tester = false;
			if( (q1== null) || (q2==null)||(q1 == q2))
			{
				tester= true;
			}
			if(q1 != q2) {
				tester= false;
			}
			after();
			assertTrue(tester);

	}
		
		 
	
	@Test
	public void poseQuestionTester() throws SQLException { 
		Question q1 = test.getQuestion();
		//asks question and checks if your answer is true
//		assertEquals(true,test.poseQuestion());   
		//asks question and checks if your answer is false
//		assertFalse(test.poseQuestion());
		after();
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
		Queue<Question> temp = test.getQuestionsQueue();
		Question[] questions = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions[i] = temp.poll();
		}
		
		after();
	}
	@Test
	public void getQuestionTest() throws SQLException {
		Question[] questions1 = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions1[i] = test.getQuestion();
		}
		Question[] questions2 = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions2[i] = test.getQuestion();
		}
		after();
		assertNotEquals(Arrays.toString(questions1), Arrays.toString(questions2));
	}
	@Test
	public void getQuestionReuseTest() throws SQLException {
		for (int i = 0; i < test.getTotalQuestionCount() * 10; i++) {
			test.getQuestion();
		}
		after();
	}

	
	@After
    public  void after() throws SQLException{
		test.getDB().closeConnection();
    }

}