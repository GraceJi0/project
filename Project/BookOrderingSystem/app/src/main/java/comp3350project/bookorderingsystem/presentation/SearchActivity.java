package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.R;
import java.util.ArrayList;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Book> books;
    private ListView listView;
    private String accountName;
    private AccessBook accessBook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        accessBook= new AccessBook();
        books = accessBook.getBookList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra("search");
        String search = message[0];
        accountName = message[1];
        doSearch(search);
        setSearchButton();
        setMyAccountButton();
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
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(SearchActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************
     do the search activity.
     display the book list
     ********************************************************/
    public void doSearch(String searchKey)
    {
        ArrayList<Book> found = accessBook.searchBookContain(searchKey);
        if((found == null)||(found.size()==0))
        {
            //if we didn't find any books
            TextView message = (TextView)findViewById(R.id.Searchkey);
            message.setText("No result about: "+searchKey+", try");
            setListView(books);
        }
        else
        {
            TextView message = (TextView)findViewById(R.id.Searchkey);
            message.setText("Showing books with: "+searchKey);
            setListView(found);
        }
    }

    /*******************************************************
     when click on search button, search the book title by the given string.
     if no result found, display all books.
     ********************************************************/
    public  void setSearchButton()
    {
        //set seatch button
        final Button search = (Button) findViewById(R.id.SearchBut);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //get the search key
                    EditText searchKeyText = (EditText) findViewById(R.id.searchText);
                    String searchKey = searchKeyText.getText().toString();
                //search the book
                doSearch(searchKey);
            }
        });
    }



    /*******************************************************
     set the listView for the given book list
     ********************************************************/
    public void setListView(final ArrayList<Book> bookList)
    {
        //set books' listView
        BookAdapter adapter = new BookAdapter(SearchActivity.this,
                R.layout.book_item,bookList);
        listView = (ListView) findViewById(R.id.searchResultList);
        listView.setAdapter(adapter);

        //set bookList clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                String bookName = book.getName();

                //go to the edit book information page
                Intent intent = new Intent(SearchActivity.this, ViewBookActivity.class);
                String[] message = {bookName,accountName};
                intent.putExtra("name and view", message);
                startActivity(intent);
            }
        });
    }

    /*******************************************************
     when click on my account button, go to the my account page
     ********************************************************/
    public void setMyAccountButton()
    {
        Button myAccount = (Button) findViewById(R.id.MyAccountButton);
        myAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SearchActivity.this, MyAccountActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
            }
        });
    }
}