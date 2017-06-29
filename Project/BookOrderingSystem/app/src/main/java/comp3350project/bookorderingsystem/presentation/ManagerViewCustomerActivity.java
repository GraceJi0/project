package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Customer;


public class ManagerViewCustomerActivity extends AppCompatActivity {

    private List<Customer> customersList;
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
        logOut();
    }

    /*******************************************************
     set log out button
     ********************************************************/
    public void logOut()
    {
        Button showLogOut=(Button)findViewById(R.id.logOutButton);
        showLogOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                accountName="";;
                Intent intent = new Intent(ManagerViewCustomerActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(ManagerViewCustomerActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************
     set listView for the give customer list
     ********************************************************/
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


