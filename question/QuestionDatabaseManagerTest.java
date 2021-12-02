/**
 * 
 */
package question;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		stmt= null;
	}
	
	@AfterClass
    public static void tearDown() throws SQLException {
        stmt.close();
        connection.close();
    }
	@Test
	public void getTotalQuestionTest() {
		int count= test.getTotalQuestions();
		assertEquals(count,99);
		
	}

}
