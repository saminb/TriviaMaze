/**
 * 
 */
package question;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Queue;

import org.junit.*;
import org.junit.jupiter.api.Test;

/**
 * @author samin
 *
 */
class QuestionDatabaseManagerTest {
	private static final String dbName= "triviamaze_test.db";
	private static QuestionDatabaseManager test;
	private static Connection connection;
	private static PreparedStatement stmt;
	
	@BeforeClass
	public static void setUp() throws SQLException {
		test= new QuestionDatabaseManager(dbName);
		connection= DriverManager.getConnection("jdbc:sqlite:question/" + dbName);
		String sql= "";
		stmt= connection.prepareStatement(sql);
		
	}
	
	@Test
	public void getTotalQuestionTest() {
		int count = test.getTotalQuestionCount();
		test.connection= QuestionDatabaseManagerTest.connection;
		assertEquals(5,count);
		
	}
	@Test
	public void getQuestionQueueTest() {
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
		Question[] questions = new Question[test.getTotalQuestionCount()];
		for (int i = 0; i < test.getTotalQuestionCount(); i++) {
			questions[i] = temp.poll();
		}
		boolean flag1 = true;
		for (int i = 0; i < expected.length; i++) {
			boolean flag2 = false;
			for (int j = 0; j < expected.length; j++) {
				if (questionsEqual(expected[j], questions[i])) {
					flag2 = true;
				}
			}
			if (!flag2) {
				flag1 = false;
			}
		}
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
	
	private boolean questionsEqual(Question question1, Question question2) {
		if (!question1.toString().equals(question2.toString())) {
			return false;
		}
		return true;
	}
	@AfterClass
    public static void tearDown() throws SQLException {
        stmt.close();
        connection.close();
    }

}
