package comp3350project.bookorderingsystem.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.objects.Order;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by lee on 2017/7/9.
 */

public class CustomerOrderActivity extends AppCompatActivity {

    private Customer customer;
    private AccessCustomer accessCustomer;
    String accountName;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerorder);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        accessCustomer=new AccessCustomer();
        customer=accessCustomer.findCustomer(accountName);
        List<Order> orderList=customer.getOrderList();
        setOrderListView(orderList);
    }
    /**********************
     * set the list view for all orders in database, display the order number and customer.
     ******************************/
    public void setOrderListView(final List<Order> orderList)
    {
        //set orders' listView
        OrderAdapter adapter = new OrderAdapter(CustomerOrderActivity.this,
                R.layout.order_item,orderList);
        ListView orderListView = (ListView) findViewById(R.id.orderListView);
        orderListView.setAdapter(adapter);

        //set order list clickable
      /* orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Order order = orderList.get(position);
                String orderNumber = Integer.toString(order.getOrderNumber());

                //go to the order details information page.
                Intent intent = new Intent(CustomerOrderActivity.this, ManagerViewOrderDetailActivity.class);
                String[] message = {orderNumber,accountName};
                intent.putExtra("name and view order", message);
                startActivity(intent);
            }
        });*/
    }
}
