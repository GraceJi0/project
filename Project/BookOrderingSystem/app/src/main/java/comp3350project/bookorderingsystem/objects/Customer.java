package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;

public class Customer
{
    private String customerName;
    private ArrayList<Book> customerCart;
    private ArrayList<Book> customerWishList;
    private String password;
    private String cardNumber;
    private String email;
    private String address;

    public Customer(String newName,String newPassword)
    {
        customerName = newName;
        password = newPassword;
        customerCart = new ArrayList<Book>();
        customerWishList = new ArrayList<Book>();
    }

    public String getName()
    {
        return customerName;
    }

    public void setCardNumber(String newNumber)
    {
        cardNumber = newNumber;
    }

    public String getPassword() {return password;}

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public void setAddress(String newAddress)
    {
        address = newAddress;
    }

    public ArrayList<Book> getCart(){return customerCart;}

    public ArrayList<Book> getWishList(){return customerWishList;}

    public void setCart(ArrayList<Book> theList)
    {
        customerCart = new ArrayList<>(theList);
    }

    public void addToCart(Book newBook)
    {
        customerCart.add(newBook);
    }

    /*******************************
     * delete the given book from customer's cart
     ******************************/
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

}

