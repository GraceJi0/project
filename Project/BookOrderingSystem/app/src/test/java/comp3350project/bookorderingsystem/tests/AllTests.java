package comp3350project.bookorderingsystem.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350project.bookorderingsystem.tests.objects.BookTest;
import comp3350project.bookorderingsystem.tests.objects.CustomerTest;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(BookTest.class);
        suite.addTestSuite(CustomerTest.class);
    }
}
