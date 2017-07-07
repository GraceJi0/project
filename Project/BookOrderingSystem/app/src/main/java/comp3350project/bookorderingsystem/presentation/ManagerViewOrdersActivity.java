package comp3350project.bookorderingsystem.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import comp3350project.bookorderingsystem.R;

public class ManagerViewOrdersActivity extends AppCompatActivity
{
    String accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_vieworder);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        //AccessCustomer accessCustomer = new AccessCustomer();
        //customersList = accessCustomer.getCustomerList();

        //setListView(accessCustomer);
        //logOut();
    }
}
