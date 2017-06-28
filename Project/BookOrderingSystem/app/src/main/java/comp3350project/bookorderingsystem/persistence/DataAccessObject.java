package comp3350project.bookorderingsystem.persistence;

import android.app.ExpandableListActivity;

import java.sql.Array;
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

import comp3350project.bookorderingsystem.persistence.bookPersistence;
import comp3350project.bookorderingsystem.persistence.customerPersistence;
import comp3350project.bookorderingsystem.business.AccessBook;

public class DataAccessObject implements DataAccess
{
	private bookPersistence BookPersistence;
	private customerPersistence CustomerPersistence;

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

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	private ArrayList<Picture>ImageList;

	public void open(String dbPath)
	{
		String url;
		BookPersistence=new bookPersistence();
		CustomerPersistence=new customerPersistence();
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
		BookPersistence.initialImageList(ImageList);
//		ImageList.add(new Picture(1, R.drawable.book1));
//		ImageList.add(new Picture(2, R.drawable.book2));
//		ImageList.add(new Picture(3, R.drawable.book3));
//		ImageList.add(new Picture(4, R.drawable.book4));
//		ImageList.add(new Picture(5, R.drawable.book5));
//		ImageList.add(new Picture(6, R.drawable.book6));
//		ImageList.add(new Picture(7, R.drawable.book7));
//		ImageList.add(new Picture(8, R.drawable.book8));
//		ImageList.add(new Picture(9, R.drawable.book9));
	}

	public ArrayList<Customer> getCustomerList()
	{
		return CustomerPersistence.getCustomerList(cmdString, st1, rs2, warn);
//		ArrayList<Customer> customerList = new ArrayList<Customer>();   //initialize a new list to store the customers
//		ArrayList<Book>wishlist = new ArrayList<Book>();   //the wish list of one customer
//		ArrayList<Book>cart = new ArrayList<Book>();   //the cart of one customer
//
//		try
//		{
//			cmdString = "select * from customer";
//			rs2 = st1.executeQuery(cmdString);
//
//			System.out.println("%%%%%%trying to get the customer list");
//			while(rs2.next())
//			{
//				String name = rs2.getString("name");  //name of the customer
//				String pwd = rs2.getString("password");  //password
//
//				System.out.println("in the getCustomerList, name now is: "+name);
//
//				Customer theCustomer = new Customer(name, pwd);  //a customer is find and ready to store
//
//				String cardnumber = rs2.getString("cardnumber");
//				if(cardnumber != "")
//					theCustomer.setCardNumber(cardnumber);
//
//				String email = rs2.getString("email");
//				if(email != "")
//					theCustomer.setEmail(email);
//
//				String address = rs2.getString("address");
//				if(address != "")
//					theCustomer.setAddress(address);
//
//					//the wishlist and the cart
//
//				wishlist = getWishList(theCustomer);
//				theCustomer.setWishList(wishlist);
//
//				cart = getCart(theCustomer);
//				theCustomer.setCart(cart);
//
//				customerList.add(theCustomer);
//			}
//		}
//		catch(Exception e)
//		{
//			warn = processSQLError(e);
//		}
//		return customerList;
//	}
//
//	public ArrayList<Book> getCart(Customer theCustomer)
//	{
//		ArrayList<Book> theList = new ArrayList<Book>();
//		AccessBook getBook = new AccessBook();
//		String name = theCustomer.getName();
//
//		try
//		{
//			cmdString = ("select * from cart where customerName='"+name+"'");  //get the cart
//			rs3 = st2.executeQuery(cmdString);
//			while (rs3.next())  //get the cart (the book name)
//			{
//				Book theBook = getBook.searchBook(rs3.getString("bookName"));
//				if(theBook!=null)
//				{
//					theList.add(theBook);
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			warn = processSQLError(e);
//		}
//		return theList;
	}

	public boolean addToCart(Customer customer, Book book)
	{
		return CustomerPersistence.addToCart( customer, book, cmdString, st1, rs2, result , updateCount, warn);
//		if (validCustomer(customer)) //add only when the customer is valid
//		{
//			if (validBook(book)) {
//				String values;
//
//				warn = null;
//				try
//				{
//					values = "'" + customer.getName() + "', '" + book.getName() + "'";  //assign the book to customer cart
//					cmdString = "insert into cart " + "values(" + values + ")";
//					updateCount = st1.executeUpdate(cmdString);
//					result = true;
//					warn = checkWarning(st1, updateCount);
//
//
//					cmdString = "select * from customer";
//					rs2 = st1.executeQuery(cmdString);
//					System.out.println("######################################now is "+customer.getName());
//					while(rs2.next())
//					{
//						System.out.println(rs2.getString("name"));
//					}
//
//				} catch (Exception e) {
//					warn = processSQLError(e);
//					result = false;
//				}
//			}
//			else
//				result = false;
//		}
//		else
//			result = false;
//		return result;
	}

	public boolean deleteFromCart(Customer customer, Book book)
	{
		return CustomerPersistence.deleteFromCart(customer, book, cmdString, st1, rs3, result , updateCount, warn);
//		if (validCustomer(customer))
//		{
//			if (validBook(book)) {
//				String where;
//
//				warn = null;
//				try
//				{
//					where = "where customerName='" + customer.getName() + "' and bookName='" + book.getName() + "'";
//					cmdString = "delete from cart " + where;
//					updateCount = st1.executeUpdate(cmdString);
//					result = true;
//					warn = checkWarning(st1, updateCount);
//				} catch (Exception e) {
//					warn = processSQLError(e);
//					result = false;
//				}
//			}
//			else
//				result = false;
//		}
//		else
//			result = false;
//		return result;
	}

	public ArrayList<Book> getWishList(Customer theCustomer)
	{
		return CustomerPersistence.getWishList(theCustomer, cmdString, st2, rs3, warn);
//		ArrayList<Book> theList = new ArrayList<Book>();
//		AccessBook getBook = new AccessBook();
//		String name = theCustomer.getName();
//		try
//		{
//			cmdString = ("select * from wishlist where customerName='"+name+"'");  //get the wishlist
//			rs3 = st2.executeQuery(cmdString);
//			while (rs3.next())  //get the wishlist (the book name)
//			{
//				Book theBook = getBook.searchBook(rs3.getString("bookName"));
//				if(theBook!=null)
//				{
//					theList.add(theBook);
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			warn = processSQLError(e);
//		}
//		return theList;
	}

	public boolean addToWishList(Customer customer, Book book)
	{
		return CustomerPersistence.addToWishList(customer,book,cmdString, st1, result , updateCount, warn);
//		if (validCustomer(customer)) //add only when the customer is valid
//		{
//			if (validBook(book)) {
//				String values;
//
//				warn = null;
//				try
//				{
//					values = "'" + customer.getName() + "', '" + book.getName() + "'";  //assign the book to customer cart
//					cmdString = "insert into wishlist " + "values(" + values + ")";
//					updateCount = st1.executeUpdate(cmdString);
//					result = true;
//					warn = checkWarning(st1, updateCount);
//				} catch (Exception e) {
//					warn = processSQLError(e);
//					result = false;
//				}
//			}
//			else
//				result = false;
//		}
//		else
//			result = false;
//		return result;
	}

	public boolean deleteFromWishList(Customer customer, Book book)
	{
		return CustomerPersistence.deleteFromWishList(customer, book, cmdString, st1, result , updateCount, warn);
//		if (validCustomer(customer))
//		{
//			if (validBook(book)) {
//				String where;
//
//				warn = null;
//				try
//				{
//					where = "where customerName='" + customer.getName() + "' and bookName='" + book.getName() + "'";
//					cmdString = "delete from wishlist " + where;
//					updateCount = st1.executeUpdate(cmdString);
//					result = true;
//					warn = checkWarning(st1, updateCount);
//				} catch (Exception e) {
//					warn = processSQLError(e);
//					result = false;
//				}
//			}
//			else
//				result = false;
//		}
//		else
//			result = false;
//		return result;
	}

//	public boolean validCustomer(Customer theCustomer)
//	{
//		if(theCustomer != null)  //customer cannot be null
//		{
//			String name = theCustomer.getName();  //get the name
//			if ((!name.equals("")) && (!name.equals(" ")))
//			{
//				return true;
//			}
//			else
//				return false;  //the name cannot be empty
//		}
//		else
//			return false;
//	}

	public boolean addCustomer(Customer newCustomer)
	{
		return CustomerPersistence.addCustomer(newCustomer, cmdString, st1, result , updateCount, warn);
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$adding customer, name: "+newCustomer.getName());
//		if (validCustomer(newCustomer)) //add only when the customer is valid
//		{
//			String values;
//
//			warn = null;
//			try
//			{
//				values = "'" + newCustomer.getName() + "', '" +newCustomer.getPassword()+"', '', '', ''";  //initial customer card number to be -1(no number)
//				cmdString = "insert into customer " + " values(" + values + ")";
//				updateCount = st1.executeUpdate(cmdString);
//				result = true;
//				warn = checkWarning(st1, updateCount);
//			} catch (Exception e) {
//				warn = processSQLError(e);
//				result = false;
//			}
//		}
//		else
//			result = false;
//		return result;
	}

	/*
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
	}*/

	public ArrayList<Book> getBookList()
	{
		return BookPersistence.getBookList(cmdString, st3, rs5, ImageList, warn);
//		ArrayList<Book> bookList;
//		bookList = new ArrayList<Book>();
//
//		try
//		{
//			cmdString = "select * from book";
//			rs5 = st3.executeQuery(cmdString);
//		}
//		catch (Exception e)
//		{
//			warn = processSQLError(e);
//		}
//
//		try
//		{
//			while(rs5.next())
//			{
//				String name = rs5.getString("name");
//				String author = rs5.getString("author");
//				String info = rs5.getString("info");
//				double price = rs5.getDouble("price");
//				String category = rs5.getString("category");
//				int instock = rs5.getInt("numberinstock");
//				int pid = rs5.getInt("pictureid");	//the picture id
//
//				int picture = R.drawable.noimage;	//try to get the picture, but assume not found at the beginning
//
//				for(int i=0;i<ImageList.size();i++){
//					if(ImageList.get(i).getPID() == pid){
//						picture = ImageList.get(i).getPicture();
//						break;
//					}
//				}
//
//				Book theBook = new Book(name, author, info, price, category, instock, picture);
//				bookList.add(theBook);
//			}
//		}
//		catch(Exception e)
//		{
//			warn = processSQLError(e);
//		}
//		return bookList;
	}

//	public boolean validBook(Book theBook)  //check if a book is valid
//	{
//		if(theBook != null)
//		{
//			if ((theBook.getName() != null) && (!theBook.getName().equals("")))
//			{
//				if ((theBook.getBookAuthor() != null) && (!theBook.getBookAuthor().equals("")))
//				{
//					if (theBook.getBookPrice() >= 0)
//					{
//						if (theBook.getNumberInStock() >= 0)
//						{
//							if ((theBook.getCategory() != null) && (!theBook.getCategory().equals("")))
//							{
//								return true;
//							}
//							else
//								return false;
//						}
//						else
//							return false;
//					}
//					else
//						return false;
//				}
//				else
//					return false;
//			}
//			else
//				return false;
//		}
//		else
//			return false;
//	}

    public boolean addBook(Book newBook)//tested, no problem
    {
		return BookPersistence.addBook(newBook, warn, cmdString, st1, rs5, updateCount, result);
//		if(validBook(newBook))
//		{
//			String values;
//
//			warn = null;
//			try {
//				values = "'" + newBook.getName()
//						+ "', '" + newBook.getBookAuthor()
//						+ "', '" + newBook.getBookInformation()
//						+ "', " + newBook.getBookPrice()
//						+ ", '" + newBook.getCategory()
//						+ "', " + newBook.getNumberInStock()
//						+ ", " + newBook.getImageID();
//				cmdString = "Insert into book " + " Values(" + values + ")";
//				System.out.println((st1 == null));//////////////////////////////////////////////////////
//				updateCount = st1.executeUpdate(cmdString);
//				//System.out.println(cmdString);/////////////////////////////////////////////////////////
//				warn = checkWarning(st1, updateCount);
//				result = true;
//			} catch (Exception e) {
//				warn = processSQLError(e);
//				result = false;
//			}
//		}
//		else
//			result = false;
//		return result;
	}

	public boolean updateBook(Book old, Book theBook)
	{
		return BookPersistence.updateBook(old, theBook, warn, cmdString, st1, updateCount, result);
//		if(validBook(theBook))
//		{
//			String values = "";
//			String where = ("name='"+old.getName()+ "';");
//			int pre = 0;
//
//			warn = null;
//			try
//			{
//				if(!theBook.getName().equals(old.getName()))
//				{
//					values += ("name='" + theBook.getName() + "'");
//					pre = 1;
//				}
//				if(!theBook.getBookAuthor().equals(old.getBookAuthor()))
//				{
//					if(pre == 1)
//					{
//						values += ", ";
//					}
//					values += ("author='" + theBook.getBookAuthor() + "'");
//					pre = 1;
//				}
//				if (theBook.getBookPrice() != old.getBookPrice())
//				{
//					if(pre == 1)
//					{
//						values += ", ";
//					}
//					values += ("price=" + theBook.getBookPrice());  //the number
//					pre = 1;
//				}
//				if(!theBook.getBookInformation().equals(old.getBookInformation()))
//				{
//					if(pre == 1)
//					{
//						values += ", ";
//					}
//					values += ("info='" + theBook.getBookInformation() + "'");
//					pre = 1;
//				}
//				if(theBook.getNumberInStock()!= old.getNumberInStock())
//				{
//					if(pre == 1)
//					{
//						values += ", ";
//					}
//					values += ("numberinstock=" + theBook.getNumberInStock());
//					pre = 1;
//				}
//				if(!theBook.getCategory().equals(old.getCategory()))
//				{
//					if(pre == 1)
//					{
//						values += ", ";
//					}
//					values += ("category ='" + theBook.getCategory() + "'");
//					pre = 1;
//				}
//				cmdString = "UPDATE book\n " + "SET " + values + " \n WHERE " + where;
//
//				System.out.println(cmdString + "in DataAccessObject###################################################################");
//
//				updateCount = st1.executeUpdate(cmdString);
//				warn = checkWarning(st1, updateCount);
//				result=true;
//			} catch (Exception e) {
//				warn = processSQLError(e);
//				result=false;
//			}
//		}
//		else
//			result = false;
//		return result;
	}
/*
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
*/

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




