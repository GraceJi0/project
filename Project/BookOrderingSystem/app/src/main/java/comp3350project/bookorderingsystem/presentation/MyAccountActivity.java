package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Book;

/**
 * Created by dinghanji on 2017-06-15.
 */

public class MyAccountActivity extends AppCompatActivity
{
    private String accountName;
    private ArrayList<Book> bookList;
    private ListView cartListView;
    private ListView wishListListView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        AccessCustomer accessCustomer = new AccessCustomer();
        setTextView();
        setCartListView(cartListView, accessCustomer.getCustomerCart(accountName));
        setWishListListView(wishListListView, accessCustomer.getCustomerWishList(accountName));
    }

    public void setTextView()
    {
        TextView account = (TextView)findViewById(R.id.accountText);
        account.setText(accountName);
    }

    public void setCartListView(ListView listView, final ArrayList<Book> bookList)
    {
        //set books' listView
        BookAdapter adapter = new BookAdapter(MyAccountActivity.this,
                R.layout.book_item,bookList);
        listView = (ListView) findViewById(R.id.cartList);
        listView.setAdapter(adapter);

        //set bookList clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                String bookName = book.getName();

                //go to the edit book information page
                Intent intent = new Intent(MyAccountActivity.this, ViewBookActivity.class);
                String[] message = {bookName,accountName};
                intent.putExtra("name and view", message);
                startActivity(intent);
            }
        });
    }

    public void setWishListListView(ListView listView, final ArrayList<Book> bookList)
    {
        //set books' listView
        BookAdapter adapter = new BookAdapter(MyAccountActivity.this,
                R.layout.book_item,bookList);
        listView = (ListView) findViewById(R.id.wishList);
        listView.setAdapter(adapter);

        //set bookList clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);

                String bookName = book.getName();

                //go to the view book information page
                Intent i = new Intent(MyAccountActivity.this, ViewBookActivity.class);
                String[] message = {bookName, accountName};
                i.putExtra("name and view", message);
                startActivity(i);
            }
        });
    }
}
