package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017/7/7.
 */

public class Order {
    private int orderNumber;
    private List<Book> cartBooks;
    private String customerName;
    private double price;
    public Order(int newOrderNumber, List newCartBooks, String newCustomerName, double newPrice)
    {
        orderNumber=newOrderNumber;
        cartBooks=newCartBooks;
        customerName=newCustomerName;
        price=newPrice;
    }
    public String getCustomerName()
    {
        return customerName;
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
}
