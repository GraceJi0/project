package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
    private String customerName;
    ArrayList<Book> customerCart;

    public Cart(String newName)
    {
        customerName = newName;
        customerCart = new ArrayList<Book>();
    }

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
