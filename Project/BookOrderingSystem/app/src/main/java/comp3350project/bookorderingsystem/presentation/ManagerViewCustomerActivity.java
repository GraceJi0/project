package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import comp3350project.bookorderingsystem.R;

/**
 * Created by dinghanji on 2017-06-13.
 */

public class ManagerViewCustomerActivity extends AppCompatActivity {

    private String accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_viewcustomer);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        //setText();
        setButton();
    }

    public void setButton()
    {
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
    }

    public void setListView()
    {

    }
}
