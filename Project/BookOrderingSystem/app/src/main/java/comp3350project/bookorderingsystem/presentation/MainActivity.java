package comp3350project.bookorderingsystem.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "comp3010_group10.bookordering.MESSAGE";
    private String accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_main);
        setButton();
    }

    /*public void sendSearch(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this,SearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.searchText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/


    public void setButton()
    {

        //test for manager page************************
        Button testButton = (Button) findViewById(R.id.test);
        testButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, ManagerMainActivity.class);
                String account = "dbm001";
                i.putExtra("name", account);
                startActivity(i);
            }
        });
        //end test code**********************************



        //assume accountName for customer is "asdf"
        accountName = "asdf";
        //set search button
        Button searchButton = (Button) findViewById(R.id.SearchBut);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                EditText editText = (EditText) findViewById(R.id.searchText);
                String word = editText.getText().toString();
                String[] message = {word,accountName};
                //intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra("search",message);
                startActivity(intent);
            }
        });

        //set category button
        Button category = (Button) findViewById(R.id.CateBut);
        category.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent init = new Intent(MainActivity.this,SearchCategoryActivity.class);
                init.putExtra("name", accountName);
                startActivity(init);
            }
        });
    }
}
