package comp3350project.bookorderingsystem.tests.business;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Customer;

public class AccessCustomerTest extends TestCase
{
    public AccessCustomerTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessCustomer()
    {
        AccessCustomer testAccess = new AccessCustomer();
        Customer temp = new Customer("Customer");   //a temp Customer to test the AccessCustomer


    }


}