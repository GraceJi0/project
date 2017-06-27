package comp3350project.bookorderingsystem.persistence;

import android.app.ExpandableListActivity;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import android.media.Image;
import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Picture;

import comp3350project.bookorderingsystem.business.AccessBook;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private String cmdString;
	private int updateCount;
	private boolean result;
	private String warn;  //for storing the warn if there is any

	private static String EOF = "  ";

	private ArrayList<Book> bookList;
	private ArrayList<Customer> customerList;

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	private ArrayList<Picture>ImageList;

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

			bookList = new ArrayList<Book>();
			customerList = new ArrayList<Customer>();
			ImageList = new ArrayList<Picture>();
			initialImageList();
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

	public void initialImageList()
	{
		ImageList.add(new Picture(1, R.drawable.book1));
		ImageList.add(new Picture(2, R.drawable.book2));
		ImageList.add(new Picture(3, R.drawable.book3));
		ImageList.add(new Picture(4, R.drawable.book4));
		ImageList.add(new Picture(5, R.drawable.book5));
		ImageList.add(new Picture(6, R.drawable.book6));
		ImageList.add(new Picture(7, R.drawable.book7));
		ImageList.add(new Picture(8, R.drawable.book8));
		ImageList.add(new Picture(9, R.drawable.book9));
	}

	public ArrayList<Customer> getCustomerList()
	{
		customerList.clear();

		ArrayList<String> customerCart = new ArrayList<String>();
		ArrayList<String> customerWishList = new ArrayList<String>();  //initialize to null

		try
		{
			cmdString = "select * from customer";
			rs2 = st1.executeQuery(cmdString);
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
		}
		try
		{
			while(rs2.next())
			{
				String name = rs2.getString("name");  //name of the customer
				String pwd = rs2.getString("password");  //password

				Customer theCustomer = new Customer(name, pwd);  //a customer is find and ready to store

				customerList.add(theCustomer);
				/*	//the wishlist and the cart
				try
				{
					cmdString = ("select wishlist from customer where name ='"+name+"'");  //get the wishlist (title of the books)
					rs3 = st2.executeQuery(cmdString);
					while(rs3.next())  //get the wishlist (the book name)
					{
						customerWishList.add(rs3.getString("wishlist"));//////////////////////////////////////////////////////////
					}

					try
					{
						cmdString = ("select cart from customer where name ='"+name+"'");  //get the cart
						rs3 = st2.executeQuery(cmdString);
						while (rs3.next())  //get the wishlist (the book name)
						{
							customerCart.add(rs3.getString("cart"));/////////////////////////////////////////////////////////////
						}

						String cardnumber = rs2.getString("cardnumber");
						String email = rs2.getString("email");
						String address = rs2.getString("address");
						theCustomer.setCardNumber(cardnumber);
						theCustomer.setEmail(email);
						theCustomer.setAddress(address);
						customerList.add(theCustomer);
					}
					catch(Exception e)
					{
						warn = processSQLError(e);
					}
				}
				catch(Exception e)
				{
					warn = processSQLError(e);
				}*/
			}
		}
		catch(Exception e)
		{
			warn = processSQLError(e);
		}
		return customerList;
	}

	public boolean validCustomer(Customer theCustomer)
	{
		if(theCustomer != null)  //customer cannot be null
		{
			if (!theCustomer.getName().equals("")) {
				String name = theCustomer.getName();  //get the name
				if ((!name.equals("")) && (!name.equals(" "))) {
					return true;
				}
				else
					return false;  //the name cannot be empty
			}
			else
				return false;  //null name, considered invalid
		}
		else
			return false;
	}

	public boolean addCustomer(Customer newCustomer) {//tested, no problem
		if (validCustomer(newCustomer)) //add only when the customer is valid
		{
			String values;

			warn = null;
			try
			{
				values = "'" + newCustomer.getName() + "', '" +newCustomer.getPassword()+"', '', '', '', '', ''";  //initial customer card number to be -1(no number)
				cmdString = "Insert into customer " + " Values(" + values + ")";
				updateCount = st1.executeUpdate(cmdString);
				result = true;
				warn = checkWarning(st1, updateCount);
			} catch (Exception e) {
				warn = processSQLError(e);
				result = false;
			}
		}
		else
			result = false;
		return result;
	}

	public boolean updateCustomer(Customer theCustomer)
	{
		if(validCustomer(theCustomer))
		{
			String values = "";
			String where = "name = '"+theCustomer.getName() + "'";

			warn = null;
			try {
				if (!theCustomer.getCardNumber().equals(""))
				{
					values += ("cardnumber ='" + theCustomer.getCardNumber() + "'");  //the number
				}
				if (!theCustomer.getEmail().equals(""))
				{
					values += (", email ='" + theCustomer.getEmail() + "'");
				}
				if(!theCustomer.getAddress().equals(""))
				{
					values += (", address ='" + theCustomer.getAddress() + "'");
				}
				//////////////////////////////////////////////////////////////////////////////////////////////////update the password
				if(!theCustomer.getPassword().equals(""))
				{
					values+=(", password='" + theCustomer.getPassword() + "'");
				}
				cmdString = "UPDATE customer\n " + "SET" + values + " \n WHERE" + where;
				updateCount = st1.executeUpdate(cmdString);
				warn = checkWarning(st1, updateCount);
			}
			catch (Exception e) {
				warn = processSQLError(e);
			}
		}
		else
			result = false;
		return result;
	}

    public boolean deleteCustomer(Customer theCustomer)
	{
		String values;

		warn = null;
		try
		{
			values = "'"+theCustomer.getName()+"'";
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

	public ArrayList<Book> getBookList() {
		bookList.clear();
		try
		{
			cmdString = "select * from book";
			rs2 = st1.executeQuery(cmdString);
		}
		catch (Exception e)
		{
			warn = processSQLError(e);
		}

		try
		{
			while(rs2.next())
			{
				String name = rs2.getString("name");
				String author = rs2.getString("author");
				String info = rs2.getString("info");
				double price = rs2.getDouble("price");
				String category = rs2.getString("category");
				int instock = rs2.getInt("numberinstock");
				int pid = rs2.getInt("pictureid");	//the picture id

				int picture = R.drawable.noimage;	//try to get the picture, but assume not found at the beginning

				for(int i=0;i<ImageList.size();i++){
					if(ImageList.get(i).getPID() == pid){
						picture = ImageList.get(i).getPicture();
						break;
					}
				}

				Book theBook = new Book(name, author, info, price, category, instock, picture);
				bookList.add(theBook);
			}
		}
		catch(Exception e)
		{
			warn = processSQLError(e);
		}
		return bookList;
	}

	public boolean validBook(Book theBook)  //check if a book is valid
	{
		if(theBook != null)
		{
			if ((theBook.getName() != null) && (!theBook.getName().equals("")))
			{
				if ((theBook.getBookAuthor() != null) && (!theBook.getBookAuthor().equals("")))
				{
					if (theBook.getBookPrice() >= 0)
					{
						if (theBook.getNumberInStock() >= 0)
						{
							if ((theBook.getCategory() != null) && (!theBook.getCategory().equals("")))
							{
								return true;
							}
							else
								return false;
						}
						else
							return false;
					}
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}

    public boolean addBook(Book newBook)//tested, no problem
    {
		if(validBook(newBook))
		{
			String values;

			warn = null;
			try {
				values = "'" + newBook.getName()
						+ "', '" + newBook.getBookAuthor()
						+ "', '" + newBook.getBookInformation()
						+ "', " + newBook.getBookPrice()
						+ ", '" + newBook.getCategory()
						+ "', " + newBook.getNumberInStock()
						+ ", " + newBook.getImageID();
				cmdString = "Insert into book " + " Values(" + values + ")";
				System.out.println((st1 == null));//////////////////////////////////////////////////////
				updateCount = st1.executeUpdate(cmdString);
				//System.out.println(cmdString);/////////////////////////////////////////////////////////
				warn = checkWarning(st1, updateCount);
				result = true;
			} catch (Exception e) {
				warn = processSQLError(e);
				result = false;
			}
		}
		else
			result = false;
		return result;
	}

	public boolean updateBook(Book theBook)
	{
		System.out.println("in updateBook in the object#####################################################################");

		AccessBook getOld = new AccessBook();
		Book old = getOld.searchBook(theBook.getName());

		if(validBook(theBook))
		{
			String values = "";
			String where = ("name ='"+theBook.getName()+ "';");
			int pre = 0;

			warn = null;
			try
			{

				if (theBook.getBookPrice() != old.getBookPrice())
				{
					values += ("price=" + theBook.getBookPrice());  //the number
					pre = 1;
				}
				if(!theBook.getBookInformation().equals(old.getBookInformation()))
				{
					if(pre == 1)
					{
						values += ", ";
					}
					values += ("info='" + theBook.getBookInformation() + "'");
					pre = 1;
				}
				if(theBook.getNumberInStock()!= old.getNumberInStock())
				{
					if(pre == 1)
					{
						values += ", ";
					}
					values += ("numberinstock=" + theBook.getNumberInStock());
					pre = 1;
				}
				if(!theBook.getCategory().equals(old.getCategory()))
				{
					if(pre == 1)
					{
						values += ", ";
					}
					values += ("category ='" + theBook.getCategory() + "'");
					pre = 1;
				}
				cmdString = "UPDATE book\n " + "SET " + values + " \n WHERE " + where;

				System.out.println(cmdString + "in DataAccessObject###################################################################");

				updateCount = st1.executeUpdate(cmdString);
				warn = checkWarning(st1, updateCount);
				result=true;
			} catch (Exception e) {
				warn = processSQLError(e);
				result=false;
			}
		}
		else
			result = false;
		return result;
	}

	public boolean deleteBook(Book theBook)
	{
		String values;

		warn = null;
		try
		{
			values = "'"+theBook.getName()+"'";
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




