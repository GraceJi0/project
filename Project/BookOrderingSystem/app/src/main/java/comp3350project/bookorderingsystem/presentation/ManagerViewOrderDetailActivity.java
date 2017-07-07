package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.objects.Order;

/**
 * Created by dinghanji on 2017-07-07.
 */

public class ManagerViewOrderDetailActivity extends AppCompatActivity
{
    String accountName;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_vieworder_detail);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra("name and view order");
        accountName = message[1];

        int orderNumber = Integer.parseInt(message[0]);

        AccessOrder accessOrder = new AccessOrder();
        List<Order> orderList = accessOrder.getAllOrder();
        order = findTheOrder(orderNumber,orderList);

        //logOut();
    }

    public Order findTheOrder(int orderNumber, List<Order> orderList)
    {
        Order found = null;
        Order theOrder = null;
        for(int i = 0; i < orderList.size(); i++)
        {
            theOrder = orderList.get(i);
            if(orderNumber == theOrder.getOrderNumber())
            {
                found =  theOrder;
            }
        }
        return found;
    }


}

