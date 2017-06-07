package comp3350project.bookorderingsystem.tests.business;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

public class AccessBookTest extends TestCase
{
    public AccessBookTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessBook()
    {
        AccessBook testAccess = new AccessBook();
        Book tmp = new Book("Name", "Author", "Info", 1.0, "free", 1,1);   //initialize a new book to test AccessBook
        

    }

}