package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.WishList;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestWishList
{
	Customer theCus;
	WishList theWishList;
	Book theBook;

	@Before
	public void setUp()
	{
		//just leave it blank
	}

	@Test
	public void testWishList()
	{
		System.out.println("\nStarting testWishList");

		String customerName = "testCart";
		String password = "default";

		theCus = new Customer(customerName, password);   //create a new customer for testing
		theWishList = new WishList(customerName);   //intialize the wishlist for this customer

		testObject();
		testTheWishList();

		System.out.println("Finished testWishList");
	}

	public void testObject()
	{
		assertNotNull(theWishList);
	}

	public void testTheWishList()
	{
		//test the setWishList and getWishList first
		ArrayList<Book> customerWishList = new ArrayList<Book>();   //temp list for testing
		theBook = new Book("Name", "Author", "Info", 1.0, "free", 1, 1);    //create a new book to the wishlist
		customerWishList.add(theBook);
		theWishList.setWishList(customerWishList);   //setWishList()
		List<Book> getWishList = (ArrayList)theWishList.getWishList();   //getWishList()
		assertTrue(1 == getWishList.size());
		assertTrue(getWishList.get(0).getName().equals(theBook.getName()));   //check if insert correctly

		//test addToWishList()
		theBook = new Book("Name1", "Author", "Info", 1.0, "free", 1, 1);    //create a new book
		theWishList.addToWishList(theBook);   //add it to the wishlist as well
		getWishList = (ArrayList)theWishList.getWishList();
		assertTrue(2 == getWishList.size());   //number of books inside the wishlist now should be 2

		//test deleteFromWishList()
		theWishList.deleteFromWishList(theBook);  //delete the book with name "Name1"
		getWishList = (ArrayList)theWishList.getWishList();
		assertTrue(1 == getWishList.size());   //number of books inside the wishlist now should be 1 since a book was deleted
	}
}