package comp3350project.bookorderingsystem.objects;

import comp3350project.bookorderingsystem.persistence.LinkedList;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class Customer extends Data
{
    private String customerName;
    private LinkedList customerCart;
    private LinkedList customerWishList;
    private String cardNumber;
    private String email;
    private String address;

    public Customer(String newName)
    {
        customerName = newName;
        customerCart = new LinkedList();
        customerWishList = new LinkedList();
    }

    public String getName(){return customerName;}
    //public void setCustomerName(){}

    public String getCardNumber(){return cardNumber;}

    public void setCardNumber(String newNumber){cardNumber = newNumber;}

    public String getEmail(){return email;}

    public void setEmail(String newEmail){email = newEmail;}

    public String getAddress(){return address;}

    public void setAddress(String newAddress){address = newAddress;}

    public void addToCart(Book newBook)
    {
        customerCart.insert(newBook);
    }

    public void deleteFromCart(Book newBook)
    {
        customerCart.delete(newBook);
    }

    public void addToWishList(Book newBook)
    {
        customerWishList.insert(newBook);
    }

    public void deleteFromWishList(Book newBook)
    {
        customerWishList.delete(newBook);
    }

    public int compareName(Data newData)
    {
        return this.getName().compareTo(newData.getName());
    }

    public double getOrderAmount()
    {
        double result;
        result = customerCart.getOrderAmount();
        return result;
    }

    public String printCart()
    {
        return "\nCart:" + customerCart.printBooks();
    }

    public String printWishList()
    {
        return "\nWishList:" + customerWishList.printBooks();
    }
}

