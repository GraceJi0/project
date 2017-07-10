package comp3350project.bookorderingsystem.tests.business;

import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.application.Main;

import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessOrder;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestAccessOrder {

    AccessBook accessBook;
    AccessCustomer accessCustomer;
    AccessOrder accessOrder;

    private static String dbName = Main.dbName;

    private List<Book> tempCart;   //temp list of book, assume this is the customer's cart
    private Customer tempCus;
    private Order tempOrder;

    private List<Order> allOrders;   //all orders in the database

    //all customer informations used to make the order
    private int orderNumber = 123456;
    private String accountName;
    private String customerName = "tempCustomer";
    private String cardNumber = "1234567890";
    private String email = "theEmail@gmail.com";
    private String address = "U of M";
    private double price = 123.45;

    @Before
    public void setUp()
    {
        //leave it blank
    }

    @Test
    public void testAccessOrder()
    {
        Service.closeDataAccess();

        System.out.println("\nStarting testAccessOrder");

        Service.createDataAccess(new DataAccessStub());   //the access connection

        accessBook = new AccessBook();   //access to the elements
        accessCustomer = new AccessCustomer();
        accessOrder = new AccessOrder();

        makeTempCart();   //make a temp cart
        makeTempCustomer();;   //make the temp customer

        testAddOrder();
        testGetAllOrder();
        testOrderSize();
        testFindTheOrder();
        testUpdateOrderState();

        Service.closeDataAccess();

        System.out.println("Finished testAccessOrder");
    }

    public void testAddOrder()
    {
        //the state is "Waiting" since the order is just made
        String state = "Waiting";

        tempOrder = new Order(orderNumber, tempCart, accountName, customerName, cardNumber, email, address, price, state);   //make the temp order
        accessOrder.addOrder(tempOrder);   //add the order to the database
    }

    public void testGetAllOrder()
    {
        allOrders = accessOrder.getAllOrder();   //get all order from the database
    }

    public void testOrderSize()
    {
        assertTrue(allOrders.size() == 1);   //there should be only 1 order in the system
    }

    public void testFindTheOrder()
    {
        Order result = accessOrder.findTheOrder(orderNumber);
        assertTrue(result != null);   //the result cannot be null since the order is actually in the system
        assertTrue(result.getCustomerName().equals(customerName));   //confirm this is that order
    }

    public void testUpdateOrderState()
    {
        accessOrder.updateOrderState(tempOrder);
        System.out.println(tempOrder.getState());
        assertTrue(tempOrder.getState().equals("Delivered"));
    }

    public void makeTempCustomer()
    {
        tempCus = new Customer("temp", "default");
        accountName = tempCus.getName();
    }

    public void makeTempCart()
    {
        tempCart = new ArrayList<Book>();
        Book book1 = new Book("Name1", "Author1", "Info1", 11.0, "free1", 11,1);
        Book book2 = new Book("Name2", "Author2", "Info2", 12.0, "free2", 12,2);
        Book book3 = new Book("Name3", "Author3", "Info3", 13.0, "free3", 13,3);

        accessBook.addBook(book1);
        accessBook.addBook(book2);
        accessBook.addBook(book3);   //add the books to the database

        tempCart.add(book1);
        tempCart.add(book2);
        tempCart.add(book3);   //add 3 books to the customer cart
    }
}