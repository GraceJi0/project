package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.application.Main;

import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Order;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCustomer
{
    Customer theCus;
    ArrayList<Book> temp;   //the temp book list

    @Before
	public void setUp()
	{
		//leave it blank
	}

	@Test
	public void testCustomer()
	{
        Service.closeDataAccess();

		System.out.println("\nStarting testCustomer");

        Service.createDataAccess(new DataAccessStub());   //the access connection

		theCus = new Customer("3350", "default");   //create a new Customer object with account name and password

        testObject();
        testName();
        testPassword();

        temp = new ArrayList<Book>();   //temp list of book
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
        testOrder(temp);

        Service.closeDataAccess();

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

        theCus.deleteAllInCart();   //test deleteAllInCart(), delete all books in the cart
        assertTrue(0 == theCus.getCart().size());   //size shouble be 0 since all books in cart deleted

        theCus.setCart(cart);   //set the cart again for testOrder
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

    public void testOrder(List<Book> tempCart)
    {
        //make a new order with all information needed
        int orderNumber = 123456;
        String accountName = theCus.getName();
        String customerName = "tempCustomer";
        String cardNumber = "1234567890";
        String email = "theEmail@gmail.com";
        String address = "U of M";
        double price = 123.45;
        String state = "Waiting";   //status is "Waiting" since the order is just made

        Order tempOrder = new Order(orderNumber, tempCart, accountName, customerName, cardNumber, email, address, price, state);   //make the temp order

        List<Order> theOrders = new ArrayList<Order>();
        theOrders.add(tempOrder);   //assume an order is made
        theCus.addOrder(tempOrder);

        //test setOrderList() and getOrderList()
        theCus.setOrder(theOrders);
        theOrders = theCus.getOrderList();
        assertTrue(theOrders.size() == 1);   //only 1 order for the customer now

        //test addOrder()
        tempOrder = new Order(orderNumber+1, tempCart, accountName, customerName, cardNumber, email, address, price, state);   //make the new temp order with different order number
        theCus.addOrder(tempOrder);
        theOrders = theCus.getOrderList();
        assertTrue(theOrders.size() == 2);   //2 order for the customer now

        double actualTotalPrice = 0;
        int i;
        for(i = 0; i<tempCart.size(); i++)
        {
            actualTotalPrice += tempCart.get(i).getBookPrice();   //sum the price
        }

        //add the tax and round value to 2 decimals
        actualTotalPrice *= 1.13;
        long l = Math.round(actualTotalPrice * 100);
        actualTotalPrice = l / 100.0;

        assertTrue(actualTotalPrice == theCus.getTotalAmount());
    }
}