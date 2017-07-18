package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private String accountName;
    private String password;

    private Cart customerCart;
    private WishList customerWishList;
    private CustomerOrder customerOrderList;

    public Customer(String accountName,String newPassword)
    {
        this.accountName = accountName;
        password = newPassword;
        customerCart = new Cart();
        customerWishList = new WishList();
        customerOrderList = new CustomerOrder();
    }

    //************** getter and setter for account name and password, but the account name is not allowed to be modified
    public String getName() {return accountName;}
    public String getPassword(){return password;}
    public void setPassword(String newPwd){password = newPwd;}

    public void setCart(ArrayList<Book> theList)
    {
        customerCart.setCart(theList);
    }

    public List<Book> getCart()
    {
        return customerCart.getCart();
    }

    public void addToCart(Book newBook)
    {
        customerCart.addToCart(newBook);
    }

    public void deleteFromCart(Book newBook)
    {
        customerCart.deleteFromCart(newBook);
    }

    public void deleteAllInCart()
    {
        customerCart.deleteAllInCart();
    }

    public void setWishList(ArrayList<Book> theList)
    {
        customerWishList.setWishList(theList);
    }

    public List<Book> getWishList()
    {
        return customerWishList.getWishList();
    }

    public void addToWishList(Book newBook)
    {
        customerWishList.addToWishList(newBook);
    }

    public void deleteFromWishList(Book newBook)
    {
        customerWishList.deleteFromWishList(newBook);
    }

    public void addOrder(Order newOrder)
    {
        customerOrderList.addOrder(newOrder);
    }

    public List<Order> getOrderList()
    {
        return customerOrderList.getOrderList();
    }

    public void setOrder(List<Order> orders)
    {
        customerOrderList.setOrder(orders);
    }

    public double getTotalAmount()
    {
        return customerCart.getTotalAmount();
    }
}

