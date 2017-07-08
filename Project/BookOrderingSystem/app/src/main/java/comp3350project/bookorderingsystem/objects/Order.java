package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017/7/7.
 */

public class Order {
    private String waitingState = "Waiting";
    private String deliveredState = "Delivered";
    private String receivedState = "Received";

    private int orderNumber;
    private List<Book> cartBooks;
    private String accountName;
    private double price;
    private String state;   //state of the order

    public Order(int newOrderNumber, List newCartBooks, String newCustomerName, double newPrice)
    {
        orderNumber=newOrderNumber;
        cartBooks=newCartBooks;
        accountName=newCustomerName;
        price=newPrice;
        state = waitingState;
    }
    public String getAccountName()
    {
        return accountName;
    }

    public int getOrderNumber()
    {
        return orderNumber;
    }

    public List<Book> getCartBooks()
    {
        return cartBooks;
    }

    public double getPrice()
    {
        return price;
    }

    public void setState(String newState)
    {
        if(newState.equals("Delivered"))
            state = deliveredState;
        else if(newState.equals("Received"))
            state = receivedState;
    }
}
