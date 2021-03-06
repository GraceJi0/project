package comp3350project.bookorderingsystem.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350project.bookorderingsystem.tests.objects.BookTest;
import comp3350project.bookorderingsystem.tests.objects.CustomerTest;
import comp3350project.bookorderingsystem.tests.business.AccessBookTest;
import comp3350project.bookorderingsystem.tests.business.AccessCustomerTest;

import comp3350project.bookorderingsystem.application.Service;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        Service temp = new Service();
        temp.createDataAccess("temp");

        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(BookTest.class);
        suite.addTestSuite(CustomerTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessBookTest.class);
        suite.addTestSuite(AccessCustomerTest.class);
    }
}
