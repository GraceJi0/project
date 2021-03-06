package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class Customer
{
    private String customerName;
    private ArrayList<Book> customerCart;
    private ArrayList<Book> customerWishList;
    private String cardNumber;
    private String email;
    private String address;

    public Customer(String newName)
    {
        customerName = newName;
        customerCart = new ArrayList<Book>();
        customerWishList = new ArrayList<Book>();
    }

    public String getName()
    {
        return customerName;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String newNumber)
    {
        cardNumber = newNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String newAddress)
    {
        address = newAddress;
    }

    public void addToCart(Book newBook)
    {
        customerCart.add(newBook);
    }

    public void deleteFromCart(Book newBook)
    {
        int index;

        index = customerCart.indexOf(newBook);
        if (index >= 0)
        {
            customerCart.remove(index);
        }
    }

    public void addToWishList(Book newBook)
    {
        customerWishList.add(newBook);
    }

    public void deleteFromWishList(Book newBook)
    {
        int index;

        index = customerWishList.indexOf(newBook);
        if (index >= 0)
        {
            customerWishList.remove(index);
        }
    }

    public double getOrderAmount()
    {
        double total = 0.0;
        for(int i = 0; i < customerCart.size(); i++)
        {
            total += customerCart.get(i).getBookPrice();
        }
        total += total * 0.13;
        return total;
    }

}

