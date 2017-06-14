package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;


public class ManagerViewBooksActivity extends AppCompatActivity {
    private String accountName;
    AccessBook accessBook;
    ListView listView;
    private ArrayList<Book> booksList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_viewbook);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        accessBook = new AccessBook();
        booksList = accessBook.getBookList();

        setListView(booksList);
        setButton();
        doSearch();
    }

    public void setButton()
    {
        //set log out button, click then return to manager main page
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

        //set go back button
        Button goBack = (Button) findViewById(R.id.goBackButton);
        goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerViewBooksActivity.this, ManagerMainActivity.class);
                i.putExtra("name", accountName);
                startActivity(i);
            }
        });

    }

    public  void doSearch()
    {
        Button search = (Button) findViewById(R.id.searchByManagerBut);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText searchKeyText = (EditText)findViewById(R.id.searchByManager);
                String searchKey = searchKeyText.getText().toString();
                ArrayList<Book> found = accessBook.searchBookContain(searchKey);
                if(found.size()==0)
                {
                    TextView message = (TextView)findViewById(R.id.searchResultText);
                    message.setText("No result about: "+searchKey+", try");
                    setListView(booksList);
                }
                else
                {
                    TextView message = (TextView)findViewById(R.id.searchResultText);
                    message.setText("Showing books with: "+searchKey);
                    setListView(found);
                }
            }
        });
    }


    public void setListView(final ArrayList<Book> bookList)
    {
        //set books' listView
        BookAdapter adapter = new BookAdapter(ManagerViewBooksActivity.this,
                R.layout.book_item,bookList);
        listView = (ListView) findViewById(R.id.bookList);
        listView.setAdapter(adapter);

        //set bookList clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                String bookName = book.getName();

                Intent intent = new Intent(ManagerViewBooksActivity.this, EditBookActivity.class);
                String[] message = {bookName,accountName};
                intent.putExtra("name and edit", message);
                startActivity(intent);
            }
        });
    }

}


