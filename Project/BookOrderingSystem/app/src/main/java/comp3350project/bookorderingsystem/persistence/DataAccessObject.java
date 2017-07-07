package comp3350project.bookorderingsystem.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;

import java.util.List;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

public class DataAccessObject implements DataAccess
{
	private bookPersistenceHSQL BookPersistence;
	private customerPersistenceHSQL CustomerPersistence;

	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;   //name of the DB
	private String dbType;   //type of the DB

	private String cmdString;   //the SQL command for implementing the DB
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
		BookPersistence=new bookPersistenceHSQL();
		CustomerPersistence=new customerPersistenceHSQL();
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

	public List<Customer> getCustomerList()
	{
		return CustomerPersistence.getCustomerList(cmdString, st1, rs2, warn);
	}

	public boolean addToCart(Customer customer, Book book)
	{
		return CustomerPersistence.addToCart( customer, book, cmdString, st1, rs2, result , updateCount, warn);
	}

	public boolean deleteFromCart(Customer customer, Book book)
	{
		return CustomerPersistence.deleteFromCart(customer, book, cmdString, st1, rs3, result , updateCount, warn);
	}

	public List<Book> getWishList(Customer theCustomer)
	{
		return CustomerPersistence.getWishList(theCustomer, cmdString, st2, rs3, warn);
	}

	public boolean addToWishList(Customer customer, Book book)
	{
		return CustomerPersistence.addToWishList(customer,book,cmdString, st1, result , updateCount, warn);
	}

	public boolean deleteFromWishList(Customer customer, Book book)
	{
		return CustomerPersistence.deleteFromWishList(customer, book, cmdString, st1, result , updateCount, warn);
	}

	public boolean addCustomer(Customer newCustomer)
	{
		return CustomerPersistence.addCustomer(newCustomer, cmdString, st1, result , updateCount, warn);
	}

	public List<Book> getBookList()
	{
		return BookPersistence.getBookList(cmdString, st3, rs5, warn);
	}


    public boolean addBook(Book newBook)//tested, no problem
    {
		return BookPersistence.addBook(newBook, warn, cmdString, st1, rs5, updateCount, result);
	}

	public boolean updateBook(Book old, Book theBook)
	{
		return BookPersistence.updateBook(old, theBook, warn, cmdString, st1, updateCount, result);
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
	public int getAllOrderSize() {return 0;}
}




