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

        setText();
        setButton();
        logOut();
    }

    public void setText()
    {
        //set text to account's name
        TextView name = (TextView)findViewById(R.id.welcomeManagerText);
        name.setText(accountName);
    }

    public void setButton()
    {
        //set view customer button
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

        //set view book button
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
    }

    public void logOut()
    {
        Button showLogOut=(Button)findViewById(R.id.logOutButton);
        showLogOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                accountName="";;
                Intent intent = new Intent(ManagerMainActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(ManagerMainActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
