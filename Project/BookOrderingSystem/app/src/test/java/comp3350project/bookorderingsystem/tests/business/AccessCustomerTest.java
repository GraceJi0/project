package comp3350project.bookorderingsystem.tests.business;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Customer;

public class AccessCustomerTest extends TestCase {
    Customer tmp;
    AccessCustomer testAccess;

    public AccessCustomerTest(String arg0) {
        super(arg0);
        tmp = null;
        testAccess = new AccessCustomer();
    }

    public void testAccessCustomer() {
        System.out.println("\nStarting testAccessCustomer");
        assertTrue(!NullCustomer());
        assertTrue(!EmptyCustomer());
        assertTrue(TrueCustomer());
        System.out.println("Finished testAccessCustomer");
    }

    public boolean NullCustomer()   //should return false
    {
        tmp = null;
        return testAccess.addCustomer(tmp);
    }

    public boolean EmptyCustomer()  //should return false
    {
        tmp = new Customer("");
        return testAccess.addCustomer(tmp);
    }

    public boolean TrueCustomer()   //should return true
    {
        tmp = new Customer("Customer");
        return testAccess.addCustomer(tmp);
    }
}