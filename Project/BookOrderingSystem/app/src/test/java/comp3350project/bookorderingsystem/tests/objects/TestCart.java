package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Cart;
import comp3350project.bookorderingsystem.objects.Book;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCart {
	Customer theCus;   //the customer
	Cart theCart;   //cart of the customer
	Book theBook;   //test the cart functionality

	@Before
	public void setUp() {
		//just leave it blank
	}

	@Test
	public void testCart() {
		System.out.println("\nStarting testCart");

		String customerName = "testCart";
		String password = "default";
		theCus = new Customer(customerName, password);   //create a new customer for testing
		theCart = new Cart();   //intialize the cart for this customer

		testObject();
		testTheCart();   //test the setCart and getCart

		System.out.println("Finished testCart");
	}

	public void testObject()
	{
		assertNotNull(theCart);
	}

	public void testTheCart()
	{
		//test the setCart() and getCart() first
		ArrayList<Book> customerCart = new ArrayList<Book>();   //temp list for testing
		theBook = new Book("Name", "Author", "Info", 1.0, "free", 1, 1);    //create a new book
		customerCart.add(theBook);
		theCart.setCart(customerCart);   //setCart()
		List<Book>getCart = (ArrayList)theCart.getCart();   //getCart()
		assertTrue(1 == getCart.size());
		assertTrue(getCart.get(0).getName().equals(theBook.getName()));

		//test addToCart
		theBook = new Book("Name1", "Author", "Info", 1.0, "free", 1, 1);    //create a new book
		theCart.addToCart(theBook);
		getCart = (ArrayList)theCart.getCart();
		assertTrue(2 == getCart.size());   //number of books inside the cart now should be 2

		//test getTotalAmount()
		assertTrue((2.0*1.13) == theCart.getTotalAmount());   //the total amount of price should be 1.0 + 1.0 = 2.0, then add the tax(13%)

		//test deleteFromCart()
		theCart.deleteFromCart(theBook);  //delete the book with name "Name1"
		getCart = (ArrayList)theCart.getCart();
		assertTrue(1 == getCart.size());   //number of books inside the cart now should be 1 since a book was deleted

		//test deleteAllInCart()
		theCart.deleteAllInCart();
		getCart = (ArrayList)theCart.getCart();
		assertTrue(0 == getCart.size());   //should have no book inside the cart now
	}
}