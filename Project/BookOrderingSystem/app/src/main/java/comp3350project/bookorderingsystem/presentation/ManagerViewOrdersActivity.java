package comp3350project.bookorderingsystem.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Order;

public class ManagerViewOrdersActivity extends AppCompatActivity
{
    String accountName;
    List<Order> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_vieworder);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        AccessOrder accessOrder = new AccessOrder();
        orderList = accessOrder.getAllOrder();

        setOrderListView(orderList);
        //logOut();
    }

    public void setOrderListView(final List<Order> orderList)
    {
        //set orders' listView
        OrderAdapter adapter = new OrderAdapter(ManagerViewOrdersActivity.this,
                R.layout.order_item,orderList);
        ListView orderListView = (ListView) findViewById(R.id.orderListView);
        orderListView.setAdapter(adapter);

        //set order list clickable
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Order order = orderList.get(position);
                String orderNumber = Integer.toString(order.getOrderNumber());

                //go to the order details information page.
                Intent intent = new Intent(ManagerViewOrdersActivity.this, ManagerViewOrderDetailActivity.class);
                String[] message = {orderNumber,accountName};
                intent.putExtra("name and view order", message);
                startActivity(intent);
            }
        });
    }
}
