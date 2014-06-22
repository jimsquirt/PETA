package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import enums.PopUp;

/**
 * Sets up the DB connection. This class also contains querying methods.
 * 
 * @author gindy-chan ^_^
 *
 */
public class SQLConnection {

  // DB Constants
	private final String DRIVER= "com.mysql.jdbc.Driver";
	private final String DATABASE_URL = "jdbc:mysql://localhost/pet_academy";
	private final String DATABASE_USER = "root";
	private final String DATABASE_PWD = "";
	
	private Connection con;
	
	public static SQLConnection instance;
	
	/*  INITIALIZATION METHODS  */
	
	/**
	 * @return an instance of this connection class responsible for performing db actions.
	 */
	public static SQLConnection getInstance(){
		if( instance == null )	instance = new SQLConnection();
		return instance;
	}
	
	/**
	 * Opens the connection to the DB.
	 * DB authentication details are constants defined above.
	 */
	public void openDB() {
		try {
			Class.forName(DRIVER);
			con = (Connection) DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
			con.setAutoCommit(false);
		} catch(ClassNotFoundException classNotFound) {
			
		} catch(SQLException sqlException) {
		  PopUp.DB_NOT_AVAILABLE.show();
		}

	}
	
	/**
	 * Closes the connection to the DB.
	 * Every time a DB operation has ended, the connection  must be closed.
	 */
	public void closeDB(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*  OPERATIONAL METHODS  */
  
	/**
	 * Updates DB (Insert, Update, Delete) using a COMPLETE query.
	 * 
	 * @param query - SQL string.
	 * @throws SQLException
	 */
  public void updateDB(String query) throws SQLException {
    updateDB(query, new String[0]);
  }
  
  /**
   * Updates DB (Insert, Update, Delete) using a PARAMETERIZED query.
   * 
   * @param query - SQL string.
   * @param values - Parameter values to assign.
   * @throws SQLException
   */
  public void updateDB(String query, String... values) throws SQLException {
    openDB();
    try {
      PreparedStatement s = prepareParamStatement(query, values);
      s.execute();
      s.close();
      con.commit();
    } catch (SQLException e) {
      con.rollback();
    }
    closeDB();
  }
  
	/**
	 * Retrieves from DB using a COMPLETE query.
	 * 
	 * @param query - SQL string.
   * @return {@link TableData} object containing results.
	 * @throws SQLException
	 */
  public TableData getFromDB(String query) throws SQLException {
    TableData td = getFromDB(query, new String[0]);
    return td;
  }
  
  /**
   * Retrieves from DB using a PARAMETERIZED query.
   * 
   * @param query - SQL string.
   * @param values - Parameter values to assign.
   * @return {@link TableData} object containing results.
   * @throws SQLException
   */
  public TableData getFromDB(String query, String... values) throws SQLException {
    openDB();
    PreparedStatement s = prepareParamStatement(query, values);
    TableData td = getResults(s);
    s.close();
    closeDB();
    return td;
  }
  
  /**
   * Returns a {@link PreparedStatement} object based on query string and parameter values.
   * 
   * @param query - SQL string.
   * @param values - Parameter values to assign.
   * @return {@link PreparedStatement}
   * @throws SQLException
   */
	public PreparedStatement prepareParamStatement(String query, String... values) throws SQLException {
    PreparedStatement s = con.prepareStatement(query);
    for (int i=0; i<values.length; i++) {
      s.setString(i+1, values[i]);
    }
    return s;
	}
	
	/**
	 * Returns {@link TableData} object containing results from {@link PreparedStatement} given.
	 *
	 * @param s - {@link PreparedStatement} object.
	 * @return {@link TableData} object containing results.
	 * @throws SQLException
	 */
	public TableData getResults(PreparedStatement s) throws SQLException {
    ResultSet rs = s.executeQuery();
    TableData td = new TableData(rs);
    rs.close();
    return td;
	}

}
