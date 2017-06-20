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

	private String cmdString;
	private int updateCount;

	private boolean result;
	private String warn;  //for storing the warn if there is any

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

	public boolean addCustomer(Customer newCustomer)
	{
		String values;

		warn = null;
		try
		{
			values = "'"+newCustomer.getName()+"', '', '', -1, '', ''";  //initial customer card number to be -1(no number)
			cmdString = "Insert into customer " +" Values(" +values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = true;
			warn = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
			result = false;
		}
		return result;
	}

	public boolean updateCustomer(Customer theCustomer)
	{
		String values;
		String where;

		warn = null;
		try
		{
			// Should check for empty values and not update them... do it later
			/*
				%%%%%%%%%%%%%%%%%the update command!!!!!!!!!!!!!!
			*/
			updateCount = st1.executeUpdate(cmdString);
			warn = checkWarning(st1, updateCount);
		}
		catch(Exception e)
		{
			warn = processSQLError(e);
		}
		return result;
	}

    public boolean deleteCustomer(Customer theCustomer)
	{
		String values;

		warn = null;
		try
		{
			values = theCustomer.getName();
			cmdString = "Delete from customer where customer=" +values;
			updateCount = st1.executeUpdate(cmdString);
			result = true;
			warn = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
			result = false;
		}
		return result;
	}

    public boolean addBook(Book newBook)
    {
    	String values;

		warn = null;
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
			warn = checkWarning(st1, updateCount);
			result = true;
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
			result = false;
		}
		return result;
	}

	public boolean deleteBook(Book theBook)
	{
		String values;

		warn = null;
		try
		{
			values = theBook.getName();
			cmdString = "Delete from book where name=" +values;
			updateCount = st1.executeUpdate(cmdString);
			warn = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
			result = false;
		}
		return result;
	}


	public String checkWarning(Statement st, int updateCount)
	{
		String result;

		result = null;
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		if (updateCount != 1)
		{
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}
}




