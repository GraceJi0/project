package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by dinghanji on 2017-06-13.
 */

public class ManagerViewCustomerActivity extends AppCompatActivity {

    private ArrayList<Customer> customersList;
    private String accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_viewcustomer);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        AccessCustomer accessCustomer = new AccessCustomer();
        customersList = accessCustomer.getCustomerList();

        setListView(accessCustomer);
        setButton();
    }

    public void setButton()
    {
        //set log out button
        Button logOut = (Button)findViewById(R.id.logOutButton);
        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerViewCustomerActivity.this, MainActivity.class);
                i.putExtra("exit", "exit");
                startActivity(i);
            }
        });

        //set go back button
        Button goBack = (Button)findViewById(R.id.goBackButton);
        goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerViewCustomerActivity.this, ManagerMainActivity.class);
                i.putExtra("name", accountName);
                startActivity(i);
            }
        });
    }

    public void setListView(AccessCustomer accessCustomer)
    {
        //set customers' listView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ManagerViewCustomerActivity.this, android.R.layout.simple_list_item_1,
                accessCustomer.getCustomerAccount());
        ListView listView = (ListView) findViewById(R.id.customerList);
        listView.setAdapter(adapter);
    }
}


