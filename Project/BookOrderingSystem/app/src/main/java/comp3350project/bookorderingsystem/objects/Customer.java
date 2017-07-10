package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private String accountName;
    private String password;

    private ArrayList<Book> customerCart;
    private ArrayList<Book> customerWishList;
    private List<Order> orderList;

    public Customer(String accountName,String newPassword)
    {
        this.accountName = accountName;
        password = newPassword;
        customerCart = new ArrayList<Book>();
        customerWishList = new ArrayList<Book>();
        orderList = new ArrayList<Order>();
    }

    //************** getter and setter for account name and password, but the account name is not allowed to be modified
    public String getName() {return accountName;}
    public String getPassword(){return password;}
    public void setPassword(String newPwd){password = newPwd;}



    //************** belows are the lists: Cart, Wishlist, Order
    public void setCart(ArrayList<Book> theList)
    {
        customerCart = new ArrayList<>(theList);
    }
    public List<Book> getCart(){return customerCart;}
    /*******************************
     * delete the given book from customer's cart
     ******************************/
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


    /*******************************
     * set the customer's wish list
     ******************************/
    public void setWishList(ArrayList<Book> theList)
    {
        customerWishList = new ArrayList<>(theList);
    }
    public List<Book> getWishList(){return customerWishList;}
    /*******************************
     * add the given book to customer's wish list
     ******************************/
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
    /*******************************
     * delete the given book from customer's wish list
     ******************************/
    public void deleteFromWishList(Book newBook)
    {
        int index;

        index = customerWishList.indexOf(newBook);
        if (index >= 0)
        {
            customerWishList.remove(index);
        }
    }

    /*******************************
     * add an order to the order list
     ******************************/
    public void addOrder(Order newOrder)
    {
        orderList.add(newOrder);
    }
    /*public void deleteOrder(Order newOrder)
    {
        for(int i = 0; i < orderList.size(); i++)
        {
            Order order = orderList.get(i);
            if(order.getOrderNumber() == newOrder.getOrderNumber())
            {
                orderList.remove(i);
            }
        }
    }*/

    /*******************************
     * add an order to the order list
     ******************************/
    public List<Order> getOrderList()
    {
        return orderList;
    }
    public void setOrder(List<Order> orders)
    {
        this.orderList = orders;
    }

    /**********************************
     * get total amount of books in customer's cart
     ***********************************/
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
}

