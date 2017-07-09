package comp3350project.bookorderingsystem.persistence;

import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;
/**
 * Created by rico on 2017-07-08.
 */

public class orderPersistenceHSQL
{
    public List<Order> getOrderList(String cmdString, Statement st1, ResultSet rs2, String warn )
    {
        List<Order> orders = new ArrayList<Order>();   //all orders


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

    public boolean addOrder(Order order, String warn, String cmdString, Statement st1, ResultSet rs5, int updateCount, boolean result)
    {
        if(validOrder(order))
        {
            String values;
            warn = null;

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
            }catch (Exception e) {
                warn = processSQLError(e);
                result = false;
            }

        }else
        { result = false;}



        return result;
    }

    public boolean updateOrderState(Order order,String newState, String warn, String cmdString, Statement st1, int updateCount, boolean result)
    {
        String values = "";
        String where = ("number='"+order.getOrderNumber()+ "';");
        warn = null;

        try {   //content to be modified
            values="state= '"+newState+"' ";

            cmdString = "UPDATE orders\n " + "SET " + values + " \n WHERE " + where;
            updateCount = st1.executeUpdate(cmdString);
            warn = checkWarning(st1, updateCount);
            result=true;
        }catch (Exception e) {
            warn = processSQLError(e);
            result=false;
        }
        return result;
    }

    public int getAllOrderSize(String cmdString, Statement st1, ResultSet rs2, String warn )
    {
        List<Order> list=new ArrayList<Order>();
        list = getOrderList(cmdString,st1,rs2,warn);
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
