package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

/**
 * Created by dinghanji on 2017-06-15.
 */

public class MyAccountActivity extends AppCompatActivity
{
    private String accountName;
    private ArrayList<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");


        //setListView(bookList);
        //setCategoryButton(accessBook);
    }
}
