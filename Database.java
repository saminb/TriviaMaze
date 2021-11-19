package QuestionDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Database {
	Connection connection;
	private int count;
	public Database() {
		setCount(getInitialQuestionCount());
		try {
			this.connection= dbConnection.getConnection();
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		if(this.connection == null) {
			System.exit(0);
		}
	}
	public boolean isDbConnected() {
		return this.connection !=null;
	}
	
	public int getInitialQuestionCount() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count=-1;
		try {
			String sql = "SELECT count(QID) FROM Question_Bank;";
			rs= stmt.executeQuery(sql);
			count = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.toString());
			count = -1;
		}
		return count;
		}
	
	public Question getQuestion()throws SQLException{
		 	Question result = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
        
			try {
				String sql = "SELECT QID, Questions, QuestionType \r\n"
						+ "FROM Question_Bank"
						+ "ORDER BY RANDOM() LIMIT 1;";
				rs= stmt.executeQuery(sql);
				String QID = rs.getString("QID");
				String question = rs.getString("Question");
				String type= rs.getString("QuestionType");
				switch (type) {
				case "MC":
					sql = "SELECT incorrect_A1 as Option1, incorrect_A2 as Option2, incorrect_A3 as Option3, answer as Option4\r\n"
							+ "FROM Multiple_Choice_A\r\n"
							+ "Where QID= \""+QID+"\";\r\n";
					rs = stmt.executeQuery(sql);
					String answer = rs.getString("Option4");
					String [] choices=  { rs.getString("Option1"), rs.getString("Option2"), rs.getString("Option3"),rs.getString("Option4") };
					List<String> multipleChoices = Arrays.asList(choices);
					Collections.shuffle(multipleChoices);
					multipleChoices.toArray(choices);
					result= new MultipleChoice(question, answer, choices);
							break;
				case "T/F":
				sql ="SELECT answer\r\n"
						+ "FROM True_or_False\r\n"
						+ "Where QID= \""+QID+"\";\r\n";
				rs = stmt.executeQuery(sql);
				answer = rs.getString("answer");
				String[] trueFalseChoices= {"T","F"};
				result= new TrueFalse(question, answer,trueFalseChoices);
				break;
				default:
					sql ="SELECT answer\r\n"
							+ "FROM Short_Answer\r\n"
							+ "Where QID= \""+QID+"\";\r\n";
					rs = stmt.executeQuery(sql);
					answer = rs.getString("answer");
					result= new ShortAnswer(question, answer);
					break;
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
	return result;
}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}