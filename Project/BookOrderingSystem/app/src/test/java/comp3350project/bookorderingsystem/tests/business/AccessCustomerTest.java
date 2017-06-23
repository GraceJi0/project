package comp3350project.bookorderingsystem.tests.business;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.application.Main;

import comp3350project.bookorderingsystem.business.AccessCustomer;

import comp3350project.bookorderingsystem.objects.Customer;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

public class AccessCustomerTest extends TestCase {
    Customer tmp;
    AccessCustomer testAccess;

    private static String dbName = Main.dbName;

    public AccessCustomerTest(String arg0) {
        super(arg0);
    }

    public void test1()
    {
        Service.closeDataAccess();

        System.out.println("\nStarting testAccessCustomer");

        Service.createDataAccess(new DataAccessStub());

        testAccess = new AccessCustomer();

        assertTrue(!NullCustomer());
        assertTrue(!EmptyCustomer());
        assertTrue(TrueCustomer());

        Service.closeDataAccess();

        System.out.println("Finished testAccessCustomer");
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