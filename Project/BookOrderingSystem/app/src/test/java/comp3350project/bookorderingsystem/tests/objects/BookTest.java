package comp3350project.bookorderingsystem.tests.objects;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.objects.Book;

public class BookTest extends TestCase
{
	public BookTest(String arg0)
	{
		super(arg0);
	}
	
	public void testBook()
	{
		Book theBook;
		
		System.out.println("\nStarting testBook");
		
		theBook = new Book("Name", "Author", "Info", 1.0, "free", 1,1);	//create a new object and check whether it's created successful
		assertNotNull(theBook);
		assertTrue("Name".equals(theBook.getName()));
		assertTrue("Author".equals(theBook.getBookAuthor()));
		assertTrue("Info".equals(theBook.getBookInformation()));
		assertTrue(1.0 == theBook.getBookPrice());
		assertTrue("free".equals(theBook.getCategory()));
		assertTrue(1 == theBook.getNumberInStock());

		System.out.println("Finished testBook");
	}
}