package comp3350project.bookorderingsystem.tests.business;

import comp3350project.bookorderingsystem.application.Service;

import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessBook;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestAccessCustomer{
    Customer tmp;
    AccessCustomer testAccess;
    List<Customer> testList;   //the temp list of customer for testing
    int number;   //number of customer in the temp list

    @Before
    public void setUp()
    {
        //leave it blank
    }

    @Test
    public void testAccessCustomer()
    {
        Service.closeDataAccess();

        System.out.println("\nStarting testAccessCustomer");

        Service.createDataAccess(new DataAccessStub());

        testAccess = new AccessCustomer();

        //the testing for addCustomer
        testInValidCustomer();
        testValidCustomer();
        testGetCustomerList();
        testCheckAccount();
        testGetCustomer();

        Customer complex = new Customer("complex", "");   //the object for testing the cart and wishlist function
        assertTrue(testAccess.addCustomer(complex));   //add the customer to the DB and test if it's successfully
        AccessBook accessBook = new AccessBook();   //connect to the book stock
        Book tmp = new Book("Name", "Author", "Info", 1.0, "free", 1, 1);   //a right book
        assertTrue(accessBook.addBook(tmp));   //add the book to the DB, test if added successfully

        testFindCustomer(complex.getName());
        testCart(complex, tmp);
        testWishlist(complex, tmp);
        testOrder(complex, tmp);

        testPassword();

        Service.closeDataAccess();

        System.out.println("Finished testAccessCustomer");
    }

    public void testInValidCustomer()
    {
        assertTrue(!NullCustomer());
        assertTrue(!EmptyCustomer());
    }

    public void testValidCustomer()
    {
        assertTrue(TrueCustomer());
    }

    public void testGetCustomerList()
    {
        testList = (ArrayList)testAccess.getCustomerList();
        number = testList.size();   //get the number of customer in the list
    }

    public void testCheckAccount()
    {
        //the customer in the TrueCustomer should now exist in the DB
        assertTrue(testAccess.checkAccount("Customer"));   //the customer with name "Customer" should exist
        assertFalse(testAccess.checkAccount("hello world"));   //this customer should not exist
    }

    public void testGetCustomer()
    {
        String name = "Customer";   //again, the "Customer" should exist in DB
        boolean found = false;
        String[]list = testAccess.getCustomerAccount();
        for(int i = 0; i<list.length; i++)
        {
            if(name.equals(list[i]))
            {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    public void testCart(Customer theCustomer, Book theBook)
    {
        int number = 0;   //number of books in customer cart should be 0 at first
        assertTrue(number == testAccess.getCustomerCart(theCustomer.getName()).size());   //test for getCustomerCart()
        testAccess.addToCart(theCustomer.getName(), theBook);   //add the book to customer's cart
        number++;   //numbers of books in the cart increases(1)
        assertTrue(number == testAccess.getCustomerCart(theCustomer.getName()).size());
        double price = testAccess.getTotalPrice(theCustomer.getName());
        assertTrue(price == (1.0 * 1.13));   //only one book worth $1.0, add the tax
        testAccess.deleteFromCart(theCustomer.getName(), theBook);
        number--;   //numbers of books in customer cart decreases(0)
        assertTrue(number == testAccess.getCustomerCart(theCustomer.getName()).size());
        price = testAccess.getTotalPrice(theCustomer.getName());   //get price again
        assertTrue(price == 0);   //no book in the cart
    }

    public void testWishlist(Customer theCustomer, Book theBook)
    {
        int number = 0;   //number of books in customer wishlist should be 0 at first
        assertTrue(number == testAccess.getCustomerWishList(theCustomer.getName()).size());   //test for getCustomerWishList
        testAccess.addToWishList(theCustomer.getName(), theBook);   //add the book to customer's wishlist
        number++;   //numbers of books in the wishlist increases(1)
        assertTrue(number == testAccess.getCustomerWishList(theCustomer.getName()).size());
        testAccess.deleteFromWishList(theCustomer.getName(), theBook);
        number--;   //numbers of books in customer wishlist decreases(0)
        assertTrue(number == testAccess.getCustomerWishList(theCustomer.getName()).size());
    }

    public void testOrder(Customer theCustomer, Book theBook)
    {
        int number = 0;   //number of order
        assertTrue(number == testAccess.getCustomerOrder(theCustomer.getName()).size());   //should not have any order for this customer
        List<Book>Order = new ArrayList<Book>();
        //books for test which will be placed as an order
        Book book1 = new Book("Name1", "Author1", "Info1", 11.0, "free1", 11,1);
        Book book2 = new Book("Name2", "Author2", "Info2", 12.0, "free2", 12,2);
        Book book3 = new Book("Name3", "Author3", "Info3", 13.0, "free3", 13,3);
        Order.add(book1);
        Order.add(book2);
        Order.add(book3);

        //make a temp order
        int orderNumber = 3350;   //the temp order number
        String accountName = theCustomer.getName();
        String customerName = "Test";   //name of the customer who is going to receive the item
        String cardNumber = "1234567";
        String email = "The Email";
        String address = "The Address";
        double price = 123.4;   //assume the total price of the order is $123.4
        String state = "Waiting";   //the order is just made, so the state is "Waiting"

        //assume the customer made this order
        Order theOrder = new Order(orderNumber, Order, accountName, customerName, cardNumber, email, address, price, state);   //create a new order
        //test addOrder()
        testAccess.addOrder(theCustomer.getName(), theOrder);   //add this order to customer
        number++;
        assertTrue(number == testAccess.getCustomerOrder(theCustomer.getName()).size());   //should have 1 order for this customer
    }

    public void testFindCustomer(String customerName)
    {
        assertNotNull(testAccess.findCustomer(customerName));
        assertTrue(testAccess.findCustomer("hello world") == null);
    }

    public void testPassword()
    {
        Customer test = new Customer("hello", "world");   //the customer for testing the verify
        testAccess.addCustomer(test);   //add the customer to the DB
        assertTrue(-1 == testAccess.verifyCustomer("noName", "world"));   //return -1 when no such a user in the DB
        assertTrue(0 == testAccess.verifyCustomer(test.getName(), "wrong"));   //return 0 when customer exists, but the password is wrong
        assertTrue(1 == testAccess.verifyCustomer(test.getName(), "world"));
    }

    public boolean NullCustomer()   //should return false
    {
        tmp = null;
        return testAccess.addCustomer(tmp);
    }

    public boolean EmptyCustomer()  //should return false
    {
        tmp = new Customer("", "");
        return testAccess.addCustomer(tmp);
    }

    public boolean TrueCustomer()   //should return true
    {
        tmp = new Customer("Customer", "");
        return testAccess.addCustomer(tmp);
    }
}