package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;

import android.view.MenuItem;
import android.view.Menu;

import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.R;

import java.util.ArrayList;

import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Book> books;
    private ArrayAdapter<Book> bookArrayAdapter;
    private ListView listView;
    private ArrayList<Book> founds;
    private String message;
    private String accountName;

    public final static String EXTRA_MESSAGE = "comp3010_group10.bookordering.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AccessBook accessBook= new AccessBook();
        books=accessBook.getBookList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String search = message;    //get the name of the book
        String output = ""; //display the searching result
        searchBook(search);

        TextView textView = (TextView) findViewById(R.id.Searchkey);
        textView.setTextSize(20);

        String limit = "";   //make only MAX_NUM chars can be view
        char[] splitMessage = search.toCharArray();
        int MAX_NUM = 20;   //max number of char to display
        int i = 0;  //only display first 10 available chars
        while ((i < MAX_NUM) && (i < splitMessage.length)) {
            limit += String.valueOf(splitMessage[i]);
            i++;
        }
        if (splitMessage.length > MAX_NUM)
            limit += "...";

        if(founds.size()==0)
        {
            output="No result about: \"";
            output += limit;
            output += "\", try:";
            textView.setText(output);
        }
        else
        {
            output = "Showing books with: \"";
            output += limit;
            output += "\"";
            textView.setText(output);
        }
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_search);
        //layout.addView(textView);
        ////////////////////
        //set list items to be clickable
        if(founds.size() != 0)
        {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Book book = founds.get(position);
                    String bookName = book.getName();
                    Intent init = new Intent(SearchActivity.this, ViewBookActivity.class);
                    init.putExtra("message", bookName);
                    startActivity(init);
                }
            });
        }
        else
        {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Book book = books.get(position);
                    String bookName = book.getName();
                    Intent init = new Intent(SearchActivity.this, ViewBookActivity.class);
                    init.putExtra("message", bookName);
                    startActivity(init);
                }
            });
        }
    }

    public void sendSearch(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this,SearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.searchText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void searchBook(String message)
    {
        if (message != null && !message.isEmpty())
        {
            int i = 0;
            founds = new ArrayList<Book>();
            while (i < books.size())
            {
                if (books.get(i).getName().contains(message))
                {
                    founds.add(books.get(i));
                }
                i++;
            }
            if(founds.size()!=0)
            {
                ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, android.R.id.text1, founds) {
                    public View getView(int position, View convertView, ViewGroup parent)
                    {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                        text1.setText(founds.get(position).getName());
                        text2.setText("Author: " + founds.get(position).getBookAuthor() + "         $" + founds.get(position).getBookPrice());
                        return view;
                    }
                };
                listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(bookArrayAdapter);
            }
            else
            {
                ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, android.R.id.text1, books) {
                    public View getView(int position, View convertView, ViewGroup parent)
                    {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                        text1.setText(books.get(position).getName());
                        text2.setText("Author: " + books.get(position).getBookAuthor() + "         $" + books.get(position).getBookPrice());
                        return view;
                    }
                };
                listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(bookArrayAdapter);
            }
        } else
            {
            founds = new ArrayList<Book>();
            ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, android.R.id.text1, books) {
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                    text1.setText("Book Name: " + books.get(position).getName());
                    text2.setText("Author: " + books.get(position).getBookAuthor() + "       Price: " + books.get(position).getBookPrice());
                    return view;
                }
            };
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(bookArrayAdapter);
        }
    }

}