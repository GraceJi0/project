package comp3350project.bookorderingsystem.objects;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {
    private List<Order> customerOrderList;

    public CustomerOrder( )
    {
        customerOrderList = new ArrayList<Order>();
    }

    // ///////////////////////////////////
    // add an order to the order list
    // ///////////////////////////////////
    public void addOrder(Order newOrder)
    {
        customerOrderList.add(newOrder);
    }


    /***************
    get the order list
     *****************/
    public List<Order> getOrderList()
    {
        return customerOrderList;
    }

    /**************
     * set the order list to the given list
     *********************/
    public void setOrder(List<Order> orders)
    {
        this.customerOrderList = orders;
    }

}
