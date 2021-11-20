package QuestionDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	
	private static final String CONN= "jdbc:sqlite:trivia_maze_main.db";
	
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(CONN);
			
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			
		}
		return null;
	}
}