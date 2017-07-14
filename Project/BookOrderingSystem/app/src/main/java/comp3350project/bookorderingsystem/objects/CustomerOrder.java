package comp3350project.bookorderingsystem.objects;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinghanji on 2017-07-14.
 */

public class CustomerOrder {
    private String customerName;
    private List<Order> customerOrderList;

    public CustomerOrder(String newName)
    {
        customerName = newName;
        customerOrderList = new ArrayList<Order>();
    }

    // ///////////////////////////////////
    // add an order to the order list
    // ///////////////////////////////////
    public void addOrder(Order newOrder)
    {
        customerOrderList.add(newOrder);
    }


    /// ///////////////////////////////////
    // add an order to the order list
    // ///////////////////////////////////
    public List<Order> getOrderList()
    {
        return customerOrderList;
    }
    public void setOrder(List<Order> orders)
    {
        this.customerOrderList = orders;
    }

}
