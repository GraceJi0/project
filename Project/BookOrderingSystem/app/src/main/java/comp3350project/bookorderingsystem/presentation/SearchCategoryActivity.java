package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;


public class SearchCategoryActivity extends AppCompatActivity {
    private String accountName;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category);
        //setTestButton();
        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        AccessBook accessBook = new AccessBook();
        ArrayList<Book> bookList = accessBook.getBookList();
        setListView(bookList);
        setButton(accessBook);
    }

    public void setButton(final AccessBook accessBook)
    {
        //set fiction button
        Button fiction = (Button)findViewById(R.id.fictionBut);
        fiction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               setListView(accessBook.searchBookCategory("Fiction"));
            }
        });

        Button textBook = (Button)findViewById(R.id.textBookBut);
        textBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("TextBooks"));
            }
        });

        Button children = (Button)findViewById(R.id.childrenbBut);
        children.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("Children and Young Adult"));
            }
        });

        Button comic = (Button)findViewById(R.id.comicsBut);
        comic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("Comics and Graphic Novels"));
            }
        });

        Button magazine = (Button)findViewById(R.id.magazinebut);
        magazine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("Magazines"));
            }
        });

        Button nonFiction = (Button)findViewById(R.id.nonFictionBut);
        nonFiction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("Non-fiction"));
            }
        });

        Button other = (Button)findViewById(R.id.otherBut);
        other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setListView(accessBook.searchBookCategory("other"));
            }
        });
    }

    public void setListView(final ArrayList<Book> booksList)
    {
        BookAdapter adapter = new BookAdapter(SearchCategoryActivity.this,
                R.layout.book_item,booksList);
        listView = (ListView) findViewById(R.id.booklist);
        listView.setAdapter(adapter);

        //set bookList clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = booksList.get(position);
                String bookName = book.getName();

                Intent intent = new Intent(SearchCategoryActivity.this, ViewBookActivity.class);
                String[] message = {bookName, accountName};
                intent.putExtra("view", message);
                startActivity(intent);
            }
        });
    }
}
