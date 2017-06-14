package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;

/**
 * Created by dinghanji on 2017-06-13.
 */

public class ManagerMainActivity extends AppCompatActivity
{

    private String accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        //Intent init = getIntent();
        //accountName = init.getStringExtra("go back");
        setText();
        setButton();
    }

    public void setText()
    {
        TextView name = (TextView)findViewById(R.id.welcomeManagerText);
        name.setText(accountName);
    }

    public void setButton()
    {
        Button viewCustomer = (Button)findViewById(R.id.viewCustomerButton);
        viewCustomer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerMainActivity.this, ManagerViewCustomerActivity.class);
                i.putExtra("name", accountName);
                startActivity(i);
            }
        });

        Button viewBook = (Button)findViewById(R.id.viewBookButton);
        viewBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerMainActivity.this, ManagerViewBooksActivity.class);
                i.putExtra("name", accountName);
                startActivity(i);
            }
        });

        Button logOut = (Button)findViewById(R.id.logOutButton);
        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerMainActivity.this, MainActivity.class);
                i.putExtra("exit", "exit");
                startActivity(i);
            }
        });
    }
}
