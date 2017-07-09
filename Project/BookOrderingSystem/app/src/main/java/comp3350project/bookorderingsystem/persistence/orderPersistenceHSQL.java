package comp3350project.bookorderingsystem.persistence;

import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

import comp3350project.bookorderingsystem.business.AccessBook;

/**
 * Created by rico on 2017-07-08.
 */

public class orderPersistenceHSQL
{
    public List<Order> getOrderList(String cmdString, Statement st4, Statement st5, ResultSet rs4, ResultSet rs5, String warn )
    {
        AccessBook accessBook = new AccessBook();
        String where = "";

        List<Order> orders;
        orders= new ArrayList<Order>();   //all orders

        String bookName;
        String accountName;
        String customerName;
        String cardNumber;
        String email;
        String address;
        int orderNumber;
        double price;
        String state;

        try
        {
            cmdString = "select * from orders";
            rs4 = st4.executeQuery(cmdString);

            while(rs4.next())
            {
                orderNumber = rs4.getInt("number");
                where = " where number=" + orderNumber;
                cmdString = "select * from orderDetails" + where;

                try
                {
                    rs5 = st5.executeQuery(cmdString);

                    List<Book> books;
                    books = new ArrayList<Book>();   //get all books in the order

                    while(rs5.next())
                    {
                        bookName = rs5.getString("bookName");
                        Book theBook = accessBook.searchBook(bookName);
                        books.add(theBook);
                    }
                    accountName = rs4.getString("accountName");
                    customerName = rs4.getString("customerName");
                    cardNumber = rs4.getString("cardNumber");
                    email = rs4.getString("email");
                    address = rs4.getString("address");
                    price = rs4.getDouble("price");
                    state = rs4.getString("state");

                    Order theOrder = new Order(orderNumber, books, accountName, customerName, cardNumber, email, address, price, state);
                    orders.add(theOrder);
                }
                catch(Exception e)
                {
                    warn = processSQLError(e);
                }
            }
        }
        catch(Exception e)
        {
            warn = processSQLError(e);
        }
        return orders;
    }

    public boolean validOrder(Order order)
    {
        if(order!=null)
        {
            if(order.getOrderNumber()>0)
            {
                if(order.getCartBooks()!=null)
                    if(order.getAccountName()!=null)
                        return true;
            }
        }
        return false;

    }

    public boolean addOrder(Order order, String warn, String cmdString, Statement st1, int updateCount)
    {
        boolean result;
        if(validOrder(order))
        {
            String values;

            try{
                values = "'" + order.getOrderNumber()
                        + "', '" + order.getAccountName()
                        + "', '" + order.getCustomerName()
                        + "', '" + order.getCardNumber()
                        + "', '" + order.getEmail()
                        + "', '" + order.getAddress()
                        + "', '" + order.getPrice()
                        +"', 'Waiting'";

                cmdString = "Insert into orders " + " Values(" + values + ")";   //insert a new book into the book table

                updateCount = st1.executeUpdate(cmdString);   //execute the command
                warn = checkWarning(st1, updateCount);

                String detail;
                List<Book>cartBooks = order.getCartBooks();   //get all books in the order
                int orderNumber = order.getOrderNumber();
                for(int i=0; i <cartBooks.size(); i++) {
                    detail = "'" + orderNumber
                            + "', '" + cartBooks.get(i).getName()
                            + "'";
                    cmdString = "Insert into orderDetails" + " Values(" + detail + ")";
                    updateCount = st1.executeUpdate(cmdString);
                    warn = checkWarning(st1, updateCount);

                }
                result = true;
            }
            catch (Exception e)
            {
                warn = processSQLError(e);
                result = false;
            }

        }
        else
            result = false;

        return result;
    }

    public boolean updateOrderState(Order order, String warn, String cmdString, Statement st1, int updateCount, boolean result)
    {
        String values = "";
        String where = ("number='"+order.getOrderNumber()+ "';");
        warn = null;

        try
        {   //content to be modified
            values="state='Delivered'";

            cmdString = "UPDATE orders\n " + "SET " + values + " \n WHERE " + where;
            updateCount = st1.executeUpdate(cmdString);
            warn = checkWarning(st1, updateCount);
            result=true;
        }
        catch (Exception e)
        {
            warn = processSQLError(e);
            result=false;
        }
        return result;
    }

    public int getAllOrderSize(String cmdString, Statement st2, Statement st3, ResultSet rs2, ResultSet rs3, String warn )
    {
        List<Order> list=new ArrayList<Order>();
        list = getOrderList(cmdString, st2, st3, rs2, rs3, warn);
        return list.size();
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
