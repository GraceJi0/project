package comp3350project.bookorderingsystem.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350project.bookorderingsystem.tests.objects.TestBook;
import comp3350project.bookorderingsystem.tests.objects.TestCart;
import comp3350project.bookorderingsystem.tests.objects.TestCustomer;
import comp3350project.bookorderingsystem.tests.objects.TestCustomerOrder;
import comp3350project.bookorderingsystem.tests.objects.TestOrder;
import comp3350project.bookorderingsystem.tests.objects.TestPicture;
import comp3350project.bookorderingsystem.tests.objects.TestWishList;

import comp3350project.bookorderingsystem.tests.business.TestAccessBook;
import comp3350project.bookorderingsystem.tests.business.TestAccessCustomer;
import comp3350project.bookorderingsystem.tests.business.TestAccessOrder;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //test objects
        TestBook.class,
        TestCart.class,
        TestCustomer.class,
        TestCustomerOrder.class,
        TestOrder.class,
        TestPicture.class,
        TestWishList.class,


        //test business
        TestAccessBook.class,
        TestAccessCustomer.class,
        TestAccessOrder.class
})

public class AllTests
{
    //remain class empty
}