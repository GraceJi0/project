package comp3350project.bookorderingsystem.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private ArrayList<Book> bookList;
	private ArrayList<Customer> customerList;
	//private ArrayList<SC> scs;

	private String cmdString;
	private int updateCount;
	//private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbPath)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();

			/*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/
			
			/*
			 * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
			 * // See also: https://github.com/SQLDroid/SQLDroid
			 * dbType = "SQLite";
			 * Class.forName("SQLite.JDBCDriver").newInstance();
			 * url = "jdbc:sqlite:" + dbPath;
			 * c1 = DriverManager.getConnection(url);     
			 * 
			 * ... create statements
			 */

			/*** The following two work on desktop builds: ***/

			/*
			 * // Setup for Access
			 * dbType = "Access";
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			 * url = "jdbc:odbc:SC";
			 * c1 = DriverManager.getConnection(url,"userid","userpassword");
			 * 
			 * ... create statements
			 */

			/*
			 * //Setup for MySQL
			 * dbType = "MySQL";
			 * Class.forName("com.mysql.jdbc.Driver");
			 * url = "jdbc:mysql://localhost/database01";
			 * c1 = DriverManager.getConnection(url, "root", "");
			 * 
			 * ... create statements
			 */
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	boolean addCustomer(Customer newCustomer)
	{
		String values;
		boolean result = null;	//return true when added successful
		
		try
		{	
			values = "'"+newCustomer.getName()+"', '', '', -1, '', ''";  //initial customer card number to be -1(no number)
			cmdString = "Insert into customer " +" Values(" +values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = true;
		}
		catch (Exception e)
		{
			result = false;
		}
		return result;
	}

    void deleteCustomer(Customer newCustomer);

    ArrayList<Book> getBookList();

    boolean addBook(Book newBook)
    {
    	String values;
		boolean result;
		
		try
		{
			values = "'"+newBook.getName()
			         +"', '" +newBook.getBookAuthor()
			         +"', '" +newBook.getBookInformation()
			         +"', " +newBook.getBookPrice()
			         +", '" +newBook.getCategory()
			         +"', " +newBook.getNumberInStock()
			         +", " +newBook.getImageID();
			cmdString = "Insert into book " +" Values(" +values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = true; // checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = false;
		}
		return result;
	}
}




