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

public class orderPersistenceHSQL {
    public List<Order> getOrderList(String cmdString, Statement st1, ResultSet rs2, String warn )
    {
        return null;
    }
    public boolean addOrder(Order order, String warn, String cmdString, Statement st1, ResultSet rs5, int updateCount, boolean result)
    {
        return true;
    }
    public boolean updateOrderState(Order order,String warn, String cmdString, Statement st1, int updateCount, boolean result)
    {
        return true;
    }

    public int getAllOrderSize()
    {
        return -1;
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
