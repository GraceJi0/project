package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
    ArrayList<Book> customerCart;

    public Cart()
    {
        customerCart = new ArrayList<Book>();
    }

    /*****************
     * set the cart to the given list
     *****************/
    public void setCart(ArrayList<Book> theList)
    {
        customerCart = new ArrayList<>(theList);
    }

    /*******************
     * get the current cart list
     ***********************/
    public List<Book> getCart(){return customerCart;}

    /************
     * add a book to the cart
     **********************/
    public void addToCart(Book newBook)
    {
        customerCart.add(newBook);
    }

    ///////////////////////////////////
    // delete the given book from customer's cart
    ///////////////////////////////////
    public void deleteFromCart(Book newBook)
    {
        int index;

        index = customerCart.indexOf(newBook);
        if (index >= 0)
        {
            customerCart.remove(index);
        }
    }

    /*************************
     * clear the cart
     ************************/
    public void deleteAllInCart()
    {
        customerCart = new ArrayList<Book>();
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

}
