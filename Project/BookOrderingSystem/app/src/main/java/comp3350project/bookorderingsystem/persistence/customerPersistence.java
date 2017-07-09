package comp3350project.bookorderingsystem.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;
/**
 * Created by rico on 2017-06-28.
 */

public class customerPersistence {
    public customerPersistence(){}

    public List<Customer> getCustomerList(List<Customer> customerList)
    {
        return customerList;
    }

    public boolean addToCart()
    {
        return true;
    }

    public boolean deleteFromCart()
    {
        return true;
    }

    public boolean addToWishList()
    {
        return true;
    }

    public boolean deleteFromWishList()
    {
        return true;
    }

    public List<Order> getOrder(Customer customer){return null;}

    public boolean addCustomer(Customer newCustomer,List<Customer> customerList)
    {
        if(newCustomer != null)
        {
            if (newCustomer.getName() != null && newCustomer.getName() != "") {
                customerList.add(newCustomer);
                return true;
            }
            else
            {
                System.out.println("add customer error: information error");
                return false;
            }
        }
        else
        {
            System.out.println("add customer error: invalid customer ");
            return false;
        }
    }
}
