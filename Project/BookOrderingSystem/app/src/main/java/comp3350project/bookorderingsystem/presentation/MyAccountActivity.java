package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;


public class MyAccountActivity extends AppCompatActivity
{
    private String accountName;
    private ArrayList<Book> bookList;
    private ListView cartListView;
    private ListView wishListListView;
    private AccessCustomer accessCustomer;
    private Customer customer;
    private Button viewOrders;
    private Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        accessCustomer = new AccessCustomer();
        customer=accessCustomer.findCustomer(accountName);
        setTextView();
        setCartListView(accessCustomer.getCustomerCart(accountName));
        setWishListListView(accessCustomer.getCustomerWishList(accountName));
        checkOut();
        logOut();
        viewOrder();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        setCartListView(accessCustomer.getCustomerCart(accountName));
        setWishListListView(accessCustomer.getCustomerWishList(accountName));
    }

    public void setTextView()
    {
        TextView account = (TextView)findViewById(R.id.accountText);
        account.setText(accountName);
    }

    public void checkOut()
    {
        checkout=(Button)findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                customer=accessCustomer.findCustomer(accountName);
                if(customer.getCart().size()==0)
                {
                    Toast.makeText(MyAccountActivity.this,
                            "your cart is empty!!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(MyAccountActivity.this, CheckOutActivity.class);
                    i.putExtra("name", accountName);
                    startActivity(i);
                }
            }
        });
    }

    public void viewOrder()
    {
        viewOrders=(Button)findViewById(R.id.viewOrderButton);
        viewOrders.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(customer.getOrderList().size()==0)
                {
                    Toast.makeText(MyAccountActivity.this,
                            "You don't have any orders",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MyAccountActivity.this, CustomerOrderActivity.class);
                    intent.putExtra("name", accountName);
                    startActivity(intent);
                }
            }
        });

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
                Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(MyAccountActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************
     set the cart listView for the given book list
     ********************************************************/
    public void setCartListView(final List<Book> bookList)
    {
        //set books' listView
        BookWithCheckboxAdapter adapter = new BookWithCheckboxAdapter(MyAccountActivity.this,
                R.layout.booklist_item,bookList);
        cartListView = (ListView) findViewById(R.id.cartList);
        cartListView.setAdapter(adapter);
        setCartDeleteButton( adapter);

        //set bookList clickable
        cartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    /*******************************************************
     set the wish list listView for the given book list
     ********************************************************/
    public void setWishListListView(final List<Book> bookList)
    {
        //set books' listView
        BookWithCheckboxAdapter adapter = new BookWithCheckboxAdapter(MyAccountActivity.this,
                R.layout.booklist_item,bookList);
        wishListListView = (ListView) findViewById(R.id.wishList);
        wishListListView.setAdapter(adapter);
        setWishListListDeleteButton(adapter);
        //set bookList clickable
        wishListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    /*******************************************************
     when click on delete button, delete from the customer's cart
     ********************************************************/
    public void setCartDeleteButton(final BookWithCheckboxAdapter adapter)
    {
        Button deleteCart = (Button)findViewById(R.id.delete1But);
        deleteCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ArrayList<Book> selected = adapter.getSelectedBooks();
                for(int i = 0; i < selected.size();i++)
                {
                    accessCustomer.deleteFromCart(accountName,selected.get(i));
                }
                setCartListView(accessCustomer.getCustomerCart(accountName));
            }
        });
    }

    /*******************************************************
     when click on delete button, delete from the customer's wish list
     ********************************************************/
    public void setWishListListDeleteButton(final BookWithCheckboxAdapter adapter)
    {
        Button deleteWishList = (Button)findViewById(R.id.delete2But);
        deleteWishList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ArrayList<Book> selected = adapter.getSelectedBooks();
                for(int i = 0; i < selected.size();i++)
                {
                    accessCustomer.deleteFromWishList(accountName,selected.get(i));
                }
                setWishListListView(accessCustomer.getCustomerWishList(accountName));
            }
        });
    }


}
