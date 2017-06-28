package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.objects.Customer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest
{
	@Before
	public void setUp()
	{

	}

	@Test
	public void testCustomer()
	{
		Customer theCus;

		System.out.println("\nStarting testCustomer");

		theCus = new Customer("3350", "default");
		assertNotNull(theCus);
		assertTrue("3350".equals(theCus.getName()));

		System.out.println("Finished testCustomer");
	}
}