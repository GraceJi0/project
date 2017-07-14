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
    //private ArrayList<Book> customerCart;
    //private ArrayList<Book> customerWishList;
    //private List<Order> orderList;

    public Customer(String accountName,String newPassword)
    {
        this.accountName = accountName;
        password = newPassword;
        customerCart = new Cart(accountName);
        customerWishList = new WishList(accountName);
        customerOrderList = new CustomerOrder(accountName);
        //customerCart = new ArrayList<Book>();
        //customerWishList = new ArrayList<Book>();
        //orderList = new ArrayList<Order>();
    }

    //************** getter and setter for account name and password, but the account name is not allowed to be modified
    public String getName() {return accountName;}
    public String getPassword(){return password;}
    public void setPassword(String newPwd){password = newPwd;}


    //add new code
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
/*
    //************** belows are the lists: Cart, Wishlist, Order
    public void setCart(ArrayList<Book> theList)
    {
        customerCart = new ArrayList<>(theList);
    }
    public List<Book> getCart(){return customerCart;}
    ///////////////////////////////////
    // delete the given book from customer's cart
     ///////////////////////////////////
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
    public void deleteAllInCart()
    {
        customerCart = new ArrayList<Book>();
    }


    // ///////////////////////////////////
      //set the customer's wish list
     // ///////////////////////////////////
    public void setWishList(ArrayList<Book> theList)
    {
        customerWishList = new ArrayList<>(theList);
    }
    public List<Book> getWishList(){return customerWishList;}
    // ///////////////////////////////////
    //add the given book to customer's wish list
    //  ///////////////////////////////////
    public void addToWishList(Book newBook)
    {
        boolean found = false;
        for(int i = 0; i < customerWishList.size(); i++)
        {
            if(customerWishList.get(i).getName().equals(newBook.getName()))
            {
                //if there is duplicate
                found = true;
            }
        }
        if(found == false)
        {
            customerWishList.add(newBook);
        }
    }
    // ///////////////////////////////////
     //delete the given book from customer's wish list
    //  ///////////////////////////////////
    public void deleteFromWishList(Book newBook)
    {
        int index;

        index = customerWishList.indexOf(newBook);
        if (index >= 0)
        {
            customerWishList.remove(index);
        }
    }

    // ///////////////////////////////////
     // add an order to the order list
     // ///////////////////////////////////
    public void addOrder(Order newOrder)
    {
        orderList.add(newOrder);
    }


public void addOrder(Order newOrder)
public List<Order> getOrderList()
public void setOrder(List<Order> orders)
public double getTotalAmount()
    /// ///////////////////////////////////
     // add an order to the order list
    // ///////////////////////////////////
    public List<Order> getOrderList()
    {
        return orderList;
    }
    public void setOrder(List<Order> orders)
    {
        this.orderList = orders;
    }

    //**********************************
     // get total amount of books in customer's cart
     //***********************************
    public double getTotalAmount()
    {
        double total = 0;
        for(int i = 0 ; i < customerCart.size(); i++)
        {
            total += customerCart.get(i).getBookPrice();
        }
        total += total * 0.13;
        long l = Math.round(total * 100);
        total= l / 100.0;
        return total;
    }
    */
}

