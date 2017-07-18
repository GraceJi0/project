package comp3350project.bookorderingsystem.business;

import java.util.List;

import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Order;
import comp3350project.bookorderingsystem.persistence.DataAccess;

public class AccessOrder
{
    private DataAccess dataAccess;

    public AccessOrder()
    {
        dataAccess = (DataAccess) Service.getDataAccess(Main.dbName);
    }

    public List<Order> getAllOrder()
    {
        return dataAccess.getOrderList();
    }

    /***************************
     * find the order by te given order numeber
     ***************************/
    public Order findTheOrder(int orderNumber)
    {
        List<Order> orderList = getAllOrder();
        Order found = null;
        Order theOrder = null;
        for(int i = 0; i < orderList.size(); i++)
        {
            theOrder = orderList.get(i);
            if(orderNumber == theOrder.getOrderNumber())
            {
                found =  theOrder;
            }
        }
        return found;
    }

    /****************
     * add a new order to the database's order list
     *********************/
    public void addOrder(Order newOrder)
    {
        List<Order> allOrder = getAllOrder();
        allOrder.add(newOrder);
    }
    /****************
     * return the size of order
     *********************/
    public int orderSize()
    {
        int result;
        List<Order> allOrder = getAllOrder();
        result=allOrder.size()+1;
        return result;
    }
    /****************
     * update the order with a new one
     *********************/
    public boolean updateOrderState(Order order)
    {
        return dataAccess.updateOrderState(order);
    }
}
