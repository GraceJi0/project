package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private String customerName;
    private ArrayList<Book> customerCart;
    private ArrayList<Book> customerWishList;
    private String password;
    private String cardNumber;
    private String email;
    private String address;
    private String RealName;
    private ArrayList<Order> orderList;

    public Customer(String newName,String newPassword)
    {
        customerName = newName;
        password = newPassword;
        customerCart = new ArrayList<Book>();
        customerWishList = new ArrayList<Book>();
        orderList = new ArrayList<Order>();
        RealName="";
    }

    public String getRealName()
    {
        return RealName;
    }

    public void setRealName(String newRealName)
    {
        RealName=newRealName;
    }

    public String getName()
    {
        return customerName;
    }

    public void setPassword(String newPwd)
    {
        password = newPwd;
    }
    public String getPassword()
    {
        return password;
    }

    public void setCardNumber(String newNumber)
    {
        cardNumber = newNumber;
    }
    public String getCardNumber(){return cardNumber;}

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    public String getEmail()
    {
        return email;
    }

    public void setAddress(String newAddress)
    {
        address = newAddress;
    }
    public String getAddress(){return address;}

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

    public void deleteAllInCart()
    {
        customerCart = new ArrayList<Book>();
    }
}

