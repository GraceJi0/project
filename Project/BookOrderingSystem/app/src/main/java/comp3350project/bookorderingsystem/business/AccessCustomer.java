package com.example.dinghanji.a3350_iteration1.application.business;

import com.example.dinghanji.a3350_iteration1.application.objects.Customer;
import com.example.dinghanji.a3350_iteration1.application.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class AccessCustomer
{
    private DataAccessStub dataAccess;

    public AccessCustomer(DataAccessStub newDataAccess)
    {
        dataAccess = newDataAccess;
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

    public String printCart(Customer newCustomer)
    {
        return newCustomer.printCart();
    }

    public String printWishList(Customer newCustomer)
    {
        return newCustomer.printWishList();
    }

}
