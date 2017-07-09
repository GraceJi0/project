package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017/7/7.
 */

public class Order {
    private String waitingState = "Waiting";
    private String deliveredState = "Delivered";
    //private String receivedState = "Received";

    private int number;
    private String accountName;
    private String customerName;
    private String cardNumber;
    private String email;
    private String address;
    private double price;
    private String state;   //state of the order

    private List<Book> cartBooks;

    public Order(int orderNumber, List newCartBooks, String accountName, String customerName, String cardNumber, String email, String address, double price, String state)
    {
        number = orderNumber;
        cartBooks = newCartBooks;
        this.accountName = accountName;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.email = email;
        this.address = address;
        this.price = price;
        this.state = state;
    }

    //getter methods
    public int getOrderNumber() {return number;}
    public String getAccountName(){return accountName;}
    public String getCustomerName() {return customerName;}
    public String getCardNumber(){return cardNumber;}
    public String getEmail(){return email;}
    public String getAddress(){return address;}
    public double getPrice(){return price;}
    public String getState(){return state;}
    public List<Book> getCartBooks()
    {
        return cartBooks;
    }

    public void setCustomerName(String customerName){this.customerName = customerName;}
    public void setCardNumber(String cardNumber){this.cardNumber = cardNumber;}
    public void setEmail(String email){this.email = email;}
    public void setAddress(String address){this.address = address;}
    public void setState(String newState)
    {
        /*if(newState.equals("Delivered"))
            state = deliveredState;
        else if(newState.equals("Received"))
            state = receivedState;
        else
            state = waitingState;*/
        if(newState.equals("Delivered"))
            state = deliveredState;
        else
            state = waitingState;
    }
}
