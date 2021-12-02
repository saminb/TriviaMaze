package question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class QuestionDatabaseManager {
	Connection connection= null;
	private String databaseName;
	private int totalCount;
	private Queue<Question> questionsQueue;
	private LinkedList<Question> askedQuestions;
	
	public QuestionDatabaseManager(String dbName) {
		this.databaseName = dbName;
		
		totalCount = getTotalQuestions();
		questionsQueue = getQuestionsList();
		askedQuestions = new LinkedList<Question>();
	
	}
	
	private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:question/" + this.databaseName);
    }

    private void disconnect(Connection connection, PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    private void disconnect(Connection connection, PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        disconnect(connection, stmt);
    }
    
	
	/**	getting the total number of questions in the database
	 * 
	 * @return count
	 */
	
	private int getTotalQuestions() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count=-1;
		try {
			connection= connect();
			String sql = "SELECT count(QID) FROM Question_Bank;";
			stmt= connection.prepareStatement(sql);
			rs= stmt.executeQuery();
			count = rs.getInt(1);
			} 
		catch (Exception e) {
			System.out.println(e.toString());
			count = -1;
			}
		finally {
            try {
                disconnect(connection, stmt, rs);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return count;
	}
	
	public int getTotalQuestionCount() {
		return totalCount;
	}
	
	/**gets questions without repeating until all questions are done
	 * 
	 * @return question
	 */
	public Question getQuestion() {
		Question question = questionsQueue.poll();
		askedQuestions.add(question);
		if(questionsQueue.isEmpty()) {
			Collections.shuffle(askedQuestions);
			questionsQueue.addAll(askedQuestions);
			askedQuestions.clear();
		}
		return question;
	}
	
	public Question getLastQuestion() {
		return askedQuestions.getLast();
	}
	
	public boolean poseQuestion() { // Method used to initiate the Question Answer process; retur
		Question nextQuestion = this.getQuestion();
		nextQuestion.askQuestion();
		askedQuestions.add(nextQuestion);
		
		return nextQuestion.getAnsweredResult(); 
	}
	
	public Queue<Question>getQuestionsQueue(){
		return questionsQueue;
	}
	
	@SuppressWarnings("resource")
	private Queue<Question> getQuestionsList(){
		Question[] result = new Question[totalCount];
		
		for( int i = 1; i <= totalCount; i++) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
        
			try {
				connection= connect();
				String sql = "SELECT QID, Question, QuestionType FROM "
							+ "Question_Bank WHERE QID = \""+ "Q"+i+"\";";
				stmt = connection.prepareStatement(sql);
				rs= stmt.executeQuery();
				String QID = rs.getString("QID");
				String question = rs.getString("Question");
				String type= rs.getString("QuestionType");
				switch (type) {
					case "MC":
						sql = "SELECT incorrect_A1 as Option1, incorrect_A2 as Option2, incorrect_A3 as Option3, answer as Option4\r\n"
								+ "FROM Multiple_Choice_A\r\n"
								+ "WHERE QID= \""+QID+"\";\r\n";
						stmt = connection.prepareStatement(sql);
						rs = stmt.executeQuery();
						String answer = rs.getString("Option4");
						String [] choices=  { rs.getString("Option1"), rs.getString("Option2"), rs.getString("Option3"),rs.getString("Option4") };
						List<String> multipleChoices = Arrays.asList(choices);
						Collections.shuffle(multipleChoices);
						multipleChoices.toArray(choices);
						result[i-1]= new MultipleChoice(QID,type, question, answer, choices);
								break;
					case "T/F":
					sql ="SELECT answer FROM True_or_False "
							+ "WHERE QID = \""+QID+"\";";
					stmt = connection.prepareStatement(sql);
					rs = stmt.executeQuery();
					answer = rs.getString("answer");
					String[] trueFalseChoices= {"T","F"};
					result[i-1]= new TrueFalse(QID,type,question, answer,trueFalseChoices);
					break;
					default:
						sql ="SELECT answer FROM Short_Answer WHERE QID= \""+QID+"\";";
						stmt = connection.prepareStatement(sql);
						rs = stmt.executeQuery();
						answer = rs.getString("answer");
						result[i-1]= new ShortAnswer(QID,type,question, answer);
						break;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			finally {
	            try {
	                disconnect(connection, stmt, rs);
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
        }
		/** Using random to get a shuffled list for the questions
		 * 
		 */
		Random shuffle= new Random();
		for( int i=0; i<result.length; i++) {
			int position= shuffle.nextInt(result.length);
			Question temp= result[i];
			result[i] = result[position];
			result[position]= temp;
			
		}
		Queue<Question>finalResult = new LinkedList<Question>();
		for( int i=0; i<result.length;i++) {
			finalResult.add(result[i]);
		}
		return finalResult;
	}

}