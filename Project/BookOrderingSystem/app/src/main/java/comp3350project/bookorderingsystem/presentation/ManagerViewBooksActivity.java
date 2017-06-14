package comp3350project.bookorderingsystem.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

/**
 * Created by dinghanji on 2017-06-13.
 */

public class ManagerViewBooksActivity extends AppCompatActivity {
    private String accountName;
    private List<Book> booksList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_viewbook);

        //Intent intent = getIntent();
        //accountName = intent.getStringExtra("name");

        AccessBook accessBook = new AccessBook();
        booksList = accessBook.getBookList();

        setListView();
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
                Intent i = new Intent(ManagerViewBooksActivity.this, MainActivity.class);
                i.putExtra("exit", "exit");
                startActivity(i);
            }
        });
    }

    public void setListView()
    {
        BookAdapter adapter = new BookAdapter(ManagerViewBooksActivity.this,
                R.layout.book_item,booksList);
        ListView listView = (ListView) findViewById(R.id.bookList);
        listView.setAdapter(adapter);
    }
}


