package comp3350project.bookorderingsystem.objects;

import java.util.ArrayList;
import java.util.List;


public class WishList
{
    private ArrayList<Book> customerWishList;

    public WishList()
    {
        customerWishList = new ArrayList<Book>();
    }

    /////////////////////////////////////
    //set the customer's wish list
    /////////////////////////////////////
    public void setWishList(ArrayList<Book> theList)
    {
        customerWishList = new ArrayList<>(theList);
    }

    /************
     * get the current wish list
     *******************/
    public List<Book> getWishList(){return customerWishList;}

    /////////////////////////////////////
    //add the given book to customer's wish list
    /////////////////////////////////////
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
}
