package comp3350project.bookorderingsystem.tests.business;

import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.application.Main;

import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAccessBook
{
    Book tmp;    //the temp book
    AccessBook testAccess;

    private static String dbName = Main.dbName;

    @Before
    public void setUp()
    {
        //leave it blank
    }

    @Test
    public void testAccessBook()
    {

        Service.closeDataAccess();

        System.out.println("\nStarting testAccessBook");

        Service.createDataAccess(new DataAccessStub());

        testAccess = new AccessBook();

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

        Service.closeDataAccess();

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