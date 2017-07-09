package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;


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

        AccessCustomer accessCustomer = new AccessCustomer();
        AccessOrder accessOrder = new AccessOrder();
        order = accessOrder.findTheOrder(orderNumber);
        List<Book> bookList = order.getCartBooks();
        String accountName = order.getAccountName();
        Customer customer =  accessCustomer.findCustomer(accountName);

        setBookListView(bookList);
        setTextView(orderNumber, order.getAccountName());
        setCustomerInfromation();
        logOut();
    }

    /***********************
     * set list view for books that currently in this order.
     ********************/
    public void setBookListView(final List<Book> bookList)
    {
        //set books' listView
        BookAdapter adapter = new BookAdapter(ManagerViewOrderDetailActivity.this,
                R.layout.book_item,bookList);
        ListView listView = (ListView) findViewById(R.id.orderBooks);
        listView.setAdapter(adapter);
    }

    /***********************
     * set text view for customer name and order number.
     ********************/
    public void setTextView(int orderNumber, String customerName)
    {
        TextView orderNumberText = (TextView)findViewById(R.id.orderNumberText);
        orderNumberText.setText(Integer.toString(orderNumber));

        TextView customerNameText = (TextView)findViewById(R.id.customerAccountText);
        customerNameText.setText(customerName);
    }

    /*******************************************************
     * set log out button
     ********************************************************/
    public void logOut()
    {
        Button showLogOut=(Button)findViewById(R.id.logOutButton);
        showLogOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                accountName="";
                Intent intent = new Intent(ManagerViewOrderDetailActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(ManagerViewOrderDetailActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************
     * set text view for customer's personal infromation
     *********************/
    public void setCustomerInfromation()
    {
        TextView nameText = (TextView)findViewById(R.id.customerNameText);
        nameText.setText(order.getCustomerName());

        TextView emailText = (TextView)findViewById(R.id.emailText);
        emailText.setText(order.getEmail());

        TextView cardNumberText = (TextView)findViewById(R.id.cardNumberText);
        cardNumberText.setText(order.getCardNumber());

        TextView addressText = (TextView)findViewById(R.id.addressText);
        addressText.setText(order.getAddress());
    }
}

