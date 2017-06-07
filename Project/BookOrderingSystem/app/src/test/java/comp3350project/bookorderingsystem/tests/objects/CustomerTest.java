package comp3350project.bookorderingsystem.tests.objects;

import junit.framework.TestCase;

import comp3350project.bookorderingsystem.objects.Customer;

public class CustomerTest extends TestCase
{
	public CustomerTest(String arg0)
	{
		super(arg0);
	}

	public void testCustomer()
	{
		Customer theCus;

		System.out.println("\nStarting testCustomer");

		theCus = new Customer("3350");
		assertNotNull(theCus);
		assertTrue("3350".equals(theCus.getName()));

		System.out.println("Finished testCustomer");
	}
}