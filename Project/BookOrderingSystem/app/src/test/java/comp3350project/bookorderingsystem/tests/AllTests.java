package comp3350project.bookorderingsystem.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350project.bookorderingsystem.tests.objects.TestBook;
import comp3350project.bookorderingsystem.tests.objects.TestCustomer;
import comp3350project.bookorderingsystem.tests.objects.TestPicture;
import comp3350project.bookorderingsystem.tests.objects.TestOrder;

import comp3350project.bookorderingsystem.tests.business.TestAccessBook;
import comp3350project.bookorderingsystem.tests.business.TestAccessCustomer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //test objects
        TestBook.class,
        TestCustomer.class,
        TestPicture.class,
        TestOrder.class,

        //test business
        TestAccessBook.class,
        TestAccessCustomer.class
})

public class AllTests
{
    //remain class empty
}