package question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * This class gets the connection to the database and gets the data from the database.
 * From the collected data, it gets the total number of question, a random list of questions in a 
 * question queue and keeps track of used question.
 * 
 * @author Joshua Lee, Samin Bahizad, Logan Martinson
 * @version 
 *
 */
public class QuestionDatabaseManager {
	
	/**
	 * the Connection to the database.
	 */
	private Connection myConnection;
	
	/**
	 * the database.
	 */
	private final DatabaseConnection myDatabase;
	
	/**
	 * The total number of questions in the database.
	 */
	private int myTotalCount;
	
	/**
	 * The list of questions remaining in the pool of questions.
	 */
	private final Queue<Question> myQuestionsQueue;
	
	/**
	 * The list of questions that have been asked so far.
	 */
	private LinkedList<Question> myAskedQuestions;
	
	public QuestionDatabaseManager(String theDbName) {
		myDatabase = new DatabaseConnection(theDbName);
		myTotalCount = getTotalQuestions();
		myQuestionsQueue = getQuestionsList();
		setAskedQuestions(new LinkedList<Question>());
	}
	
	/**	
	 * Returns the connection object to the database.
	 * @return connection
	 */
	private Connection connect() throws SQLException {
        return myDatabase.getConnection();
    }

	/**	
	 * Retrieves the total number of questions in the database.
	 * @return count
	 */
	
	private int getTotalQuestions() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = -1;
		try {
			myConnection = connect();
			String sql = "SELECT count(QID) FROM Question_Bank;";
			stmt = myConnection.prepareStatement(sql);
			rs = stmt.executeQuery();
			count = rs.getInt(1);
			} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**	
	 * Gets the total count of questions using the private method getTotalQuestions()
	 * to avoid data leak.
	 * @return totalCount
	 */
	public int getTotalQuestionCount() {
		return myTotalCount;
	}
	
	/**
	 * Retrieves a unique question from the list of questions that has not yet been asked,
	 * and shuffles the remaining list of questions.
	 * @return question
	 */
	public Question getQuestion() {
		Question question = myQuestionsQueue.poll();
		getAskedQuestions().add(question);
		if(myQuestionsQueue.isEmpty()) {
			Collections.shuffle(getAskedQuestions());
			myQuestionsQueue.addAll(getAskedQuestions());
			getAskedQuestions().clear();
		}
		return question;
	}
	
	/**
	 * Retrieves the database (DatabaseConnection object).
	 * @return myDatabase
	 */
	
	public DatabaseConnection getDB() throws SQLException {
		return myDatabase;
	}
	
	/**
	 * Gets the last question asked from myAskedQuestions().
	 * This will function as data for the questionLog.
	 * 
	 * @return The last asked question
	 */
	public Question getLastQuestion() {
		return getAskedQuestions().getLast();
	}
	
	/** 
	 * Method used to initiate the Question Answer process. Returns true
	 * once the process has completed.
	 * 
	 * @return boolean poseQuestion
	 */
	public boolean poseQuestion() { 
		Question nextQuestion = this.getQuestion();
		nextQuestion.askQuestion();
		getAskedQuestions().add(nextQuestion);
		boolean poseQuestion = nextQuestion.getAnsweredResult(); 
		return poseQuestion; 
	}
	
	/** 
	 * Gets and returns the question queue list of questions.
	 * @return questionsQueue
	 */
	public Queue<Question>getQuestionsQueue(){
		return myQuestionsQueue;
	}
	
	/** 
	 * Retrieves all of the questions from the database and populates myQuestionsQueue
	 * in a random order.
	 * @return myQuestionsQueue populated
	 */
	@SuppressWarnings("resource")
	private Queue<Question> getQuestionsList(){
		Question[] result = new Question[myTotalCount];
		
		for( int i = 1; i <= myTotalCount; i++) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
        
			try {
				myConnection = connect();
				String sql = "SELECT QID, Question, QuestionType FROM "
							+ "Question_Bank WHERE QID = \""+ "Q"+i+"\";";
				stmt = myConnection.prepareStatement(sql);
				rs = stmt.executeQuery();
				String QID = rs.getString("QID");
				String question = rs.getString("Question");
				String type= rs.getString("QuestionType");
				switch (type) {
					case "MC":
						sql = "SELECT incorrect_A1 as Option1, incorrect_A2 as Option2, incorrect_A3 as Option3, answer as Option4\r\n"
								+ "FROM Multiple_Choice_A\r\n"
								+ "WHERE QID= \""+QID+"\";\r\n";
						stmt = myConnection.prepareStatement(sql);
						rs = stmt.executeQuery();
						String answer = rs.getString("Option4");
						String [] choices=  { rs.getString("Option1"), rs.getString("Option2"), rs.getString("Option3"),rs.getString("Option4") };
						List<String> multipleChoices = Arrays.asList(choices);
						Collections.shuffle(multipleChoices);
						multipleChoices.toArray(choices);
						result[i-1] = new MultipleChoice(QID,type, question, answer, choices);
								break;
					case "T/F":
					sql = "SELECT answer FROM True_or_False "
							+ "WHERE QID = \""+QID+"\";";
					stmt = myConnection.prepareStatement(sql);
					rs = stmt.executeQuery();
					answer = rs.getString("answer");
					String[] trueFalseChoices = {"T","F"};
					result[i-1] = new TrueFalse(QID,type,question, answer,trueFalseChoices);
					break;
					default:
						sql = "SELECT answer FROM Short_Answer WHERE QID= \""+QID+"\";";
						stmt = myConnection.prepareStatement(sql);
						rs = stmt.executeQuery();
						answer = rs.getString("answer");
						result[i-1] = new ShortAnswer(QID,type,question, answer);
						break;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
        }
		
		/** 
		 * Using random to get a shuffled list for the questions
		 */
		Random shuffle = new Random();
		for( int i = 0; i < result.length; i++) {
			int position = shuffle.nextInt(result.length);
			Question temp = result[i];
			result[i] = result[position];
			result[position]= temp;
			
		}
		Queue<Question> finalResult = new LinkedList<Question>();
		for( int i = 0; i < result.length; i++) {
			finalResult.add(result[i]);
		}
		return finalResult;
	}
	
	/** 
	 * Gets and returns myAskedQuestions.
	 * @return askedQuestions;
	 */
	public LinkedList<Question> getAskedQuestions() {
		return myAskedQuestions;
	}
	
	/** 
	 * Sets the linkedList of myAskedQuestions to theAskedQuestions.
	 * @param theAskedQuestions The list of askedQuestions to be set.
	 */
	public void setAskedQuestions(LinkedList<Question> theAskedQuestions) {
		this.myAskedQuestions = theAskedQuestions;
	}

}