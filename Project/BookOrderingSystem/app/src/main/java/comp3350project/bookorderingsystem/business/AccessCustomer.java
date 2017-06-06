package comp3350project.bookorderingsystem.business;

import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class AccessCustomer
{
    private DataAccessStub dataAccess;

    public AccessCustomer()
    {
        dataAccess = (DataAccessStub) Service.getDataAccess(Main.dbName);
    }

    public Customer getCustomer()
    {
        Customer customer = null;

        return customer;
    }

    public double OrderAmount(Customer newCustomer)
    {
        return newCustomer.getOrderAmount();
    }

    /*public String printCart(Customer newCustomer)
    {
        return newCustomer.printCart();
    }*/

    /*public String printWishList(Customer newCustomer)
    {
        return newCustomer.printWishList();
    }*/

}
