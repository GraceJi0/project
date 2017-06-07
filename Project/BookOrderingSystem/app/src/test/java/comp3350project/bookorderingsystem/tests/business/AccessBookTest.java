package comp3350project.bookorderingsystem.tests.business;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

public class AccessBookTest extends TestCase
{
    Book tmp;    //the temp book
    AccessBook testAccess;

    public AccessBookTest(String arg0)
    {
        super(arg0);
        tmp = null;
        testAccess = new AccessBook();
    }


    public void testAccessBook()
    {
        System.out.println("\nStarting testAccessBook");
        assertTrue(!NullBook());
        assertTrue(!NullBookName());
        assertTrue(!EmptyBookName());
        assertTrue(!NullAuthorName());
        assertTrue(!EmptyAuthorName());
        assertTrue(!InvalidPrice());
        assertTrue(!NullCategory());
        assertTrue(!EmptyCategory());
        assertTrue(!InvalidNumberInstock());
        assertTrue(TrueBook());
        System.out.println("Finished testAccessBook");
    }

    public boolean NullBook()   //should return false
    {
        tmp = null;
        return testAccess.addBook(tmp);
    }

    public boolean NullBookName()   //shoould return false
    {
        tmp = new Book(null, "Author", "info", 1.0, "free", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean EmptyBookName()  //should return false
    {
        tmp = new Book("", "Author", "Info", 1.0, "free", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean NullAuthorName() //should return false
    {
        tmp = new Book("Name", null, "Info", 1.0, "free", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean EmptyAuthorName()    //should return false
    {
        tmp = new Book("Name", "", "Info", 1.0, "free", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean InvalidPrice()   //should return false
    {
        tmp = new Book("Name", "Author", "Info", -1.0, "free", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean NullCategory()   //shuold return false
    {
        tmp = new Book("Name", "Author", "Info", 1.0, null, 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean EmptyCategory()  //should return false
    {
        tmp = new Book("Name", "Author", "Info", 1.0, "", 1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean InvalidNumberInstock()   //should return false
    {
        tmp = new Book("Name", "Author", "Info", 1.0, "free", -1, 1);
        return testAccess.addBook(tmp);
    }

    public boolean TrueBook()   //should return true
    {
        tmp = new Book("Name", "Author", "Info", 1.0, "free", 1, 1);   //a right book
        return testAccess.addBook(tmp);
    }

}