package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Order;
import comp3350project.bookorderingsystem.objects.CustomerOrder;
import comp3350project.bookorderingsystem.objects.Customer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCustomerOrder
{
	Customer theCus;   //the customer
	Order theOrder1;   //order of the customer
	Order theOrder2;   //order of the customer
	CustomerOrder testOrder;

	@Before
	public void setUp()
	{
		//just leave it blank
	}

	@Test
	public void testCustomerOrder()
	{
		System.out.println("\nStarting testCustomerOrder");

		String customerName = "testCart";
		String password = "default";
		theCus = new Customer(customerName, password);   //create a new customer for testing
		testOrder = new CustomerOrder(theCus.getName());   //create the object

		testObject();
		makeOrder();   //make a temp order first
		testTheCustomerOrder();

		System.out.println("Finished testCustomerOrder");
	}

	public void testObject()
	{
		assertNotNull(testOrder);
	}

	public void makeOrder()   //make a temp order
	{
		List<Book>Order = new ArrayList<Book>();
		//books for test which will be placed as an order
		Book book1 = new Book("Name1", "Author1", "Info1", 11.0, "free1", 11,1);
		Book book2 = new Book("Name2", "Author2", "Info2", 12.0, "free2", 12,2);
		Book book3 = new Book("Name3", "Author3", "Info3", 13.0, "free3", 13,3);
		Order.add(book1);
		Order.add(book2);
		Order.add(book3);

		int number = 3350;   //the temp order number
		String accountName = theCus.getName();
		String customerName = "Test";   //name of the customer who is going to receive the item
		String cardNumber = "1234567";
		String email = "The Email";
		String address = "The Address";
		double price = 123.4;   //assume the total price of the order is $123.4
		String state = "Waiting";   //the order is just made, so the state is "Waiting"

		//assume the customer made 2 orders
		theOrder1 = new Order(number, Order, accountName, customerName, cardNumber, email, address, price, state);   //create a new order
		theOrder2 = new Order(number, Order, accountName, customerName, cardNumber, email, address, price, state);   //create a new order
	}

	public void testTheCustomerOrder()
	{
		//first test the addOrder()
		testOrder.addOrder(theOrder1);
		testOrder.addOrder(theOrder2);

		//then test the getOrderList()
		List<Order>orders = (ArrayList)testOrder.getOrderList();
		assertTrue(2 == orders.size());   //should have 2 orders in the orderlist

		//test setOrder
		List<Order>temp = new ArrayList<Order>();
		temp.add(theOrder1);
		testOrder.setOrder(temp);
		orders = (ArrayList)testOrder.getOrderList();
		assertTrue(temp.size() == orders.size());
	}
}