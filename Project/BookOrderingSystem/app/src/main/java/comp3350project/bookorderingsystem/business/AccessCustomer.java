package comp3350project.bookorderingsystem.business;

import java.util.ArrayList;

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

    public boolean addCustomer(Customer newCustomer)
    {
        return dataAccess.addCustomer(newCustomer);
    }

    public ArrayList<Customer> getCustomerList(){return dataAccess.getCustomerList();}

    public double OrderAmount(Customer newCustomer)
    {
        return newCustomer.getOrderAmount();
    }

    public String[] getCustomerAccount()
    {
        ArrayList<Customer> customerList = getCustomerList();
        String[] allCustomerAccount = new String[customerList.size()];
        for(int i = 0; i < customerList.size(); i++)
        {
            allCustomerAccount[i] = customerList.get(i).getName();
        }
        return  allCustomerAccount;
    }
}
