package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Book;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBook
{
	Book theBook;

	@Before
	public void setUp()
	{
		//just leave it blank
	}

	@Test
	public void testBook()
	{
		System.out.println("\nStarting testBook");
		
		theBook = new Book("Name", "Author", "Info", 1.0, "free", 1,1);	//create a new object and check whether it's created successful

		testObject();

		String temp = "temp";
		double tempPrice = 1.0;
		int tempStock = 9;

		testName(temp);
		testAuthor(temp);
		testInfo(temp);
		testPrice(tempPrice);
		testNumStock(tempStock);
		testCategory(temp);
		testCmpName(temp);
		testImageID();
		testStock();

		System.out.println("Finished testBook");
	}

	public void testObject()
	{
		assertNotNull(theBook);
	}

	public void testName(String temp)
	{
		//getter/setter method for name
		theBook.setName(temp);
		assertTrue(temp.equals(theBook.getName()));
	}

	public void testAuthor(String temp)
	{
		//getter/setter method for author name
		theBook.setBookAuthor(temp);
		assertTrue(temp.equals(theBook.getBookAuthor()));
	}

	public void testInfo(String temp)
	{
		//getter/setter method for book information
		theBook.setBookInformation(temp);
		assertTrue(temp.equals(theBook.getBookInformation()));
	}

	public void testPrice(double tempPrice)
	{
		//getter/setter method for book price
		theBook.setBookPrice(tempPrice);
		assertTrue(tempPrice == theBook.getBookPrice());
	}

	public void testNumStock(int tempStock)
	{
		//getter/setter method for number in stock
		theBook.setNumberInStock(tempStock);
		assertTrue(theBook.getNumberInStock() == tempStock);
	}

	public void testCategory(String temp)
	{
		//getter/setter method for Category
		theBook.setCategory(temp);
		assertTrue(temp.equals(theBook.getCategory()));
	}

	public void testCmpName(String temp)
	{
		//test Compare name
		String newName = temp;
		Book cmp = new Book(newName, "Author", "Info", 1.0, "free", 1,1);   //create a new book to compare name
		int same = 0;   //CompareTo returns 0 when the comparing string are the same
		assertTrue(same == theBook.compareName(cmp));
	}

	public void testImageID()
	{
		//test getImageID
		assertTrue(1 == theBook.getImageID());
	}

	public void testStock()
	{
		Book test = new Book("Test", "Author", "Info", 1.0, "free", 1,1);   //create a new book to test stock

		//first test checkStock()
		assertTrue(test.checkStock());   //the book should be in stock

		//check reduceStock()
		test.reduceStock();
		assertTrue(!test.checkStock());   //the book now should not be in stock
	}
}