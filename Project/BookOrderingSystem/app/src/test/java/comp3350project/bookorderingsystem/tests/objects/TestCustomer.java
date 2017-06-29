package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Book;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCustomer
{
    Customer theCus;

    @Before
	public void setUp()
	{
		//leave it blank
	}

	@Test
	public void testCustomer()
	{
		System.out.println("\nStarting testCustomer");

		theCus = new Customer("3350", "default");   //create a new Customer object with account name and password

        testObject();
        testName();
        testPassword();
		testCardNumber();
        testEmail();
        testAddress();


        ArrayList<Book> temp = new ArrayList<Book>();   //temp list of book
        Book book1 = new Book("Name1", "Author1", "Info1", 11.0, "free1", 11,1);
        Book book2 = new Book("Name2", "Author2", "Info2", 12.0, "free2", 12,2);
        Book book3 = new Book("Name3", "Author3", "Info3", 13.0, "free3", 13,3);
        Book newBook = new Book("Name4", "Author4", "Info4", 14.0, "free4", 14,4);   //the new book for testing the add method
        temp.add(book1);
        temp.add(book2);
        temp.add(book3);   //add 3 books
        int number = temp.size();   ///assume there are 3 books in the temp cart

        testCart(temp, number, newBook);
        testWishlist(temp, number, newBook);

		System.out.println("Finished testCustomer");
	}

	public void testObject()
    {
        assertNotNull(theCus);   //make sure the object is created correctly
    }

    public void testName()
    {
        //test the getter for customer name
        assertTrue("3350".equals(theCus.getName()));
    }

    public void testPassword()
    {
        //setter and getter method for the password
        String tempPwd = "hello";   //the temp password
        theCus.setPassword(tempPwd);
        assertTrue(tempPwd.equals(theCus.getPassword()));
    }

    public void testCardNumber()
    {
        //setter and getter method for card number
        String card = "1234563350";    //the temp card number
        theCus.setCardNumber(card);
        assertTrue(card.equals(theCus.getCardNumber()));
    }

    public void testEmail()
    {
        //setter and getter method for email
        String tempE = "hello@myumanitoba.ca";
        theCus.setEmail(tempE);
        assertTrue(tempE.equals(theCus.getEmail()));
    }

    public void testAddress()
    {
        //setter and getter method for address
        String address = "U of M";
        theCus.setAddress(address);
        assertTrue(address.equals(theCus.getAddress()));
    }

    public void testCart(ArrayList<Book> temp, int number, Book newBook)   //the temp list and number of book inside
    {
        //testing the cart
        ArrayList<Book> cart = new ArrayList(temp);   //the cart for testing, copy the temp

        theCus.setCart(cart);   //set the cart
        assertTrue(number == theCus.getCart().size());

        theCus.addToCart(newBook);   //add one more book
        number++;
        assertTrue(number == theCus.getCart().size());   //number of books in cart = 4
        theCus.deleteFromCart(newBook);
        number--;
        assertTrue(number == theCus.getCart().size());   //number of books in cart = 3
    }

    public void testWishlist(ArrayList<Book> temp, int number, Book newBook)   //the temp list and number of book inside
    {
        //testing the wishlist
        ArrayList<Book> wishlist = new ArrayList(temp);   //the cart for testing, copy the temp

        theCus.setWishList(wishlist);
        assertTrue(number == theCus.getWishList().size());
        theCus.addToWishList(newBook);   //add one more book
        number++;
        assertTrue(number == theCus.getWishList().size());   //number of books in wishlist = 4
        theCus.deleteFromWishList(newBook);
        number--;
        assertTrue(number == theCus.getWishList().size());   //number of books in wishlist = 3
    }
}