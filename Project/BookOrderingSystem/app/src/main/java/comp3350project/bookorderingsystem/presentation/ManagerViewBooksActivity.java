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
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;


public class ManagerViewBooksActivity extends AppCompatActivity {
    private String accountName;
    private AccessBook accessBook;
    private ListView listView;
    private List<Book> booksList;
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
        logOut();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        accessBook = new AccessBook();
        booksList = accessBook.getBookList();
        setListView(booksList);
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
                Intent intent = new Intent(ManagerViewBooksActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(ManagerViewBooksActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************
     set add book button
     ********************************************************/
    public void setButton()
    {
        Button addBook = (Button)findViewById(R.id.addBookButton);
        addBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(ManagerViewBooksActivity.this, AddBookActivity.class);
                i.putExtra("name", accountName);
                startActivity(i);
            }
        });
    }

    /*******************************************************
     allow the manager to search the book by name
     ********************************************************/
    public  void doSearch()
    {
        //set seatch button
        Button search = (Button) findViewById(R.id.searchByManagerBut);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //get the search key
                EditText searchKeyText = (EditText)findViewById(R.id.searchByManager);
                String searchKey = searchKeyText.getText().toString();
                //search the book
                List<Book> found = accessBook.searchBookContain(searchKey);
                if(found.size()==0)
                {
                    //if we didn't find any books
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


<<<<<<< HEAD
    public void setListView(final List<Book> bookList)
=======
    /*******************************************************
     set the listView for the given book list.
     ********************************************************/
    public void setListView(final ArrayList<Book> bookList)
>>>>>>> fa387a19406f7f2c37c4e28665cda06bf5e2089a
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

                //go to the edit book information page
                Intent intent = new Intent(ManagerViewBooksActivity.this, EditBookActivity.class);
                String[] message = {bookName,accountName};
                intent.putExtra("name and edit", message);
                startActivity(intent);
            }
        });
    }

}


