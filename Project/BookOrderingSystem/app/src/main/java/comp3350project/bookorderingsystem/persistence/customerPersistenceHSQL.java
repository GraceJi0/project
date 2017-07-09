package comp3350project.bookorderingsystem.persistence;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

import comp3350project.bookorderingsystem.business.AccessBook;
/**
 * Created by rico on 2017-06-28.
 */

public class customerPersistenceHSQL {
    public customerPersistenceHSQL() {}

    public List<Customer> getCustomerList(String cmdString, Statement st1, ResultSet rs2, String warn )
    {
        ArrayList<Customer>customerList = new ArrayList<Customer>();   //initialize a new list to store the customers
        ArrayList<Book>wishlist = new ArrayList<Book>();   //the wish list of one customer
        ArrayList<Book>cart = new ArrayList<Book>();   //the cart of one customer

        try
        {
            cmdString = "select * from customer";
            rs2 = st1.executeQuery(cmdString);

            while(rs2.next())
            {
                String name = rs2.getString("name");  //name of the customer
                String pwd = rs2.getString("password");  //password

                Customer theCustomer = new Customer(name, pwd);  //a customer is find and ready to store

                //the wishlist and the cart

                wishlist = getWishList(theCustomer, cmdString, st1,rs2, warn);
                theCustomer.setWishList(wishlist);

                cart = getCart(theCustomer,cmdString,st1,rs2,warn);
                theCustomer.setCart(cart);

                customerList.add(theCustomer);
            }
        }
        catch(Exception e)
        {
            warn = processSQLError(e);
        }
        return customerList;
    }

    public ArrayList<Book> getCart(Customer theCustomer, String cmdString, Statement st2, ResultSet rs3, String warn)
    {
        ArrayList<Book> theList = new ArrayList<Book>();
        AccessBook getBook = new AccessBook();
        String name = theCustomer.getName();

        try
        {
            cmdString = ("select * from cart where customerName='"+name+"'");  //get the cart
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next())  //get the cart (the book name)
            {
                Book theBook = getBook.searchBook(rs3.getString("bookName"));
                if(theBook!=null)
                {
                    theList.add(theBook);
                }
            }
        }
        catch(Exception e)
        {
            warn = processSQLError(e);
        }
        return theList;
    }

    public boolean addToCart(Customer customer, Book book, String cmdString, Statement st1, ResultSet rs2, boolean result , int updateCount, String warn)
    {
        if (validCustomer(customer)) //add only when the customer is valid
        {
            if (validBook(book)) {
                String values;

                warn = null;
                try
                {
                    values = "'" + customer.getName() + "', '" + book.getName() + "'";  //assign the book to customer cart
                    cmdString = "insert into cart " + "values(" + values + ")";
                    updateCount = st1.executeUpdate(cmdString);
                    result = true;
                    warn = checkWarning(st1, updateCount);

                } catch (Exception e) {
                    warn = processSQLError(e);
                    result = false;
                }
            }
            else
                result = false;
        }
        else
            result = false;
        return result;
    }

    public boolean deleteFromCart(Customer customer, Book book, String cmdString, Statement st1, ResultSet rs3,  boolean result , int updateCount, String warn)
    {
        if (validCustomer(customer))  //customer and book must be valid for deleting a book from a customer cart
        {
            if (validBook(book)) {
                String where;

                warn = null;
                try
                {
                    where = "where customerName='" + customer.getName() + "' and bookName='" + book.getName() + "'";
                    cmdString = "delete from cart " + where;
                    updateCount = st1.executeUpdate(cmdString);
                    result = true;
                    warn = checkWarning(st1, updateCount);
                } catch (Exception e) {
                    warn = processSQLError(e);
                    result = false;
                }
            }
            else
                result = false;
        }
        else
            result = false;
        return result;
    }

    public ArrayList<Book> getWishList(Customer theCustomer, String cmdString, Statement st2, ResultSet rs3, String warn)
    {
        ArrayList<Book> theList = new ArrayList<Book>();
        AccessBook getBook = new AccessBook();
        String name = theCustomer.getName();
        try
        {
            cmdString = ("select * from wishlist where customerName='"+name+"'");  //get the wishlist
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next())  //get the wishlist (the book name)
            {
                Book theBook = getBook.searchBook(rs3.getString("bookName"));
                if(theBook!=null)
                {
                    theList.add(theBook);
                }
            }
        }
        catch(Exception e)
        {
            warn = processSQLError(e);
        }
        return theList;
    }

    public boolean addToWishList(Customer customer, Book book, String cmdString, Statement st1,  boolean result , int updateCount, String warn)
    {
        if (validCustomer(customer))   //customer must be valid for adding a book to wishlist
        {
            if (validBook(book)) {
                String values;

                warn = null;
                try
                {
                    values = "'" + customer.getName() + "', '" + book.getName() + "'";  //assign the book to customer cart
                    cmdString = "insert into wishlist " + "values(" + values + ")";
                    updateCount = st1.executeUpdate(cmdString);
                    result = true;
                    warn = checkWarning(st1, updateCount);
                } catch (Exception e) {
                    warn = processSQLError(e);
                    result = false;
                }
            }
            else
                result = false;
        }
        else
            result = false;
        return result;
    }

    public boolean deleteFromWishList(Customer customer, Book book, String cmdString, Statement st1, boolean result , int updateCount, String warn)
    {
        if (validCustomer(customer))   //customer and book must be valid for deleting a book from a customer wishlist
        {
            if (validBook(book)) {
                String where;

                warn = null;
                try
                {
                    where = "where customerName='" + customer.getName() + "' and bookName='" + book.getName() + "'";
                    cmdString = "delete from wishlist " + where;
                    updateCount = st1.executeUpdate(cmdString);
                    result = true;
                    warn = checkWarning(st1, updateCount);
                } catch (Exception e) {
                    warn = processSQLError(e);
                    result = false;
                }
            }
            else
                result = false;
        }
        else
            result = false;
        return result;
    }

    public boolean validCustomer(Customer theCustomer)
    {
        if(theCustomer != null)  //customer cannot be null
        {
            String name = theCustomer.getName();  //get the name
            if ((!name.equals("")) && (!name.equals(" ")))
            {
                return true;
            }
            else
                return false;  //the name cannot be empty
        }
        else
            return false;
    }

    public boolean addCustomer(Customer newCustomer, String cmdString, Statement st1, boolean result , int updateCount, String warn)
    {
        if (validCustomer(newCustomer)) //add only when the customer is valid
        {
            String values;

            warn = null;
            try
            {
                values = "'" + newCustomer.getName() + "', '" +newCustomer.getPassword()+"', '', '', ''";  //initial customer card number to be -1(no number)
                cmdString = "insert into customer " + " values(" + values + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = true;
                warn = checkWarning(st1, updateCount);
            } catch (Exception e) {
                warn = processSQLError(e);
                result = false;
            }
        }
        else
            result = false;
        return result;
    }

    public boolean validBook(Book theBook)  //check if a book is valid
    {
        if(theBook != null)
        {
            if ((theBook.getName() != null) && (!theBook.getName().equals("")))
            {
                if ((theBook.getBookAuthor() != null) && (!theBook.getBookAuthor().equals("")))
                {
                    if (theBook.getBookPrice() >= 0)
                    {
                        if (theBook.getNumberInStock() >= 0)
                        {
                            if ((theBook.getCategory() != null) && (!theBook.getCategory().equals("")))
                            {
                                return true;
                            }
                            else
                                return false;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
