package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Order;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Book;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOrder
{
    Order newOrder;

    @Before
	public void setUp()
	{
		//leave it blank
	}

	@Test
	public void testCustomer()
	{
		System.out.println("\nStarting testOrder");

		Customer theCus = new Customer("3350", "default");   //create a new Customer object with account name and password
        //assume this customer is making an order

        int number = 3350;   //the temp order number
        String accountName = theCus.getName();
        String customerName = "Test";   //name of the customer who is going to receive the item
        String cardNumber = "1234567";
        String email = "The Email";
        String address = "The Address";
        double price = 123.4;   //assume the total price of the order is $123.4
        String state = "Waiting";   //the order is just made, so the state is "Waiting"

        ArrayList<Book> temp = new ArrayList<Book>();   //temp list of book, assume these 3 are what the custome bought
        Book book1 = new Book("Name1", "Author1", "Info1", 11.0, "free1", 11,1);
        Book book2 = new Book("Name2", "Author2", "Info2", 12.0, "free2", 12,2);
        Book book3 = new Book("Name3", "Author3", "Info3", 13.0, "free3", 13,3);
        Book newBook = new Book("Name4", "Author4", "Info4", 14.0, "free4", 14,4);   //the new book for testing the add method
        temp.add(book1);
        temp.add(book2);
        temp.add(book3);

        newOrder = new Order(number, temp, accountName, customerName, cardNumber, email, address, price, state);   //create a new order

        testObject();
        testAccountName(accountName);
        testCustomerName(customerName);
		testCardNumber(cardNumber);
        testEmail(email);
        testAddress(address);
        testPrice(price);
        testState(state);

		System.out.println("Finished testOrder");
	}

	public void testObject()
    {
        assertNotNull(newOrder);   //make sure the object is created correctly
    }

    public void testAccountName(String accountName)
    {
        //test the getter for account name
        assertTrue(accountName.equals(newOrder.getAccountName()));
    }

    public void testCustomerName(String customerName)
    {
        //test the getter and setter for customer name
        assertTrue(customerName.equals(newOrder.getCustomerName()));
        String newName = "new customer Name";
        newOrder.setCustomerName(newName);
        assertTrue(newName.equals(newOrder.getCustomerName()));
    }

    public void testCardNumber(String cardNumber)
    {
        //setter and getter method for card number
        assertTrue(cardNumber.equals(newOrder.getCardNumber()));
        String card = "1234563350";    //the temp card number
        newOrder.setCardNumber(card);
        assertTrue(card.equals(newOrder.getCardNumber()));
    }

    public void testEmail(String email)
    {
        //setter and getter method for email
        assertTrue(email.equals(newOrder.getEmail()));
        String tempE = "hello@myumanitoba.ca";
        newOrder.setEmail(tempE);
        assertTrue(tempE.equals(newOrder.getEmail()));
    }

    public void testAddress(String address)
    {
        //setter and getter method for address
        assertTrue(address.equals(newOrder.getAddress()));
        String newAddress = "U of M";
        newOrder.setAddress(newAddress);
        assertTrue(newAddress.equals(newOrder.getAddress()));
    }

    public void testPrice(double price)
    {
        //test getter for the price
        assertTrue(price == newOrder.getPrice());
    }

    public void testState(String state)
    {
        //test setter and getter for the state
        assertTrue(state.equals(newOrder.getState()));
        newOrder.setState("Delivered");
        assertTrue("Delivered".equals(newOrder.getState()));
    }
}