package question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* DatabaseConnection establishes a connection to the SQLite database.
* 
* @author Joshua Lee, Samin Bahizad, Logan Martinson
*
*/
public class DatabaseConnection {
	
	/**
	 * The connection to the database.
	 */
	private Connection myConnection;

	/**
	 * Constructs a database connection to the given database name.
	 * @param theDbName The name of the database.
	 */
	public DatabaseConnection(String theDbName) {
		myConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			myConnection = DriverManager.getConnection("jdbc:sqlite:question/" + theDbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets and returns the connection object to the database.
	 * @return myConnection
	 */
	public Connection getConnection() {
		return myConnection;
	}
	
	/**
	 * Closes the connection to the database by closing the connection object.
	 */
	public void closeConnection() {
		try {
			myConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




