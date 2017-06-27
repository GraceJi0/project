package comp3350project.bookorderingsystem.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Book;

/*when click on an item in the search list, look for the detail information of this book.*/
public class ViewBookActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "comp3010_group10.bookordering.MESSAGE";
    private Book book;
    private String accountName;
    AccessCustomer accessCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        accessCustomer = new AccessCustomer();

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra("name and view");
        String bookName = message[0];
        accountName = message[1];

        AccessBook accessBook = new AccessBook();
        book = accessBook.searchBook(bookName);
        setAllText(bookName);
        setAllButton();
        logOut();

    }

    public void logOut() {
        Button showLogOut = (Button) findViewById(R.id.logOutButton);
        showLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                accountName = "";
                ;
                Intent intent = new Intent(ViewBookActivity.this, MainActivity.class);
                intent.putExtra("name", accountName);
                startActivity(intent);
                Toast.makeText(ViewBookActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAllText(String bookName)
    {
        TextView name = (TextView)findViewById(R.id.bookNameText);
        name.setText(bookName);

        TextView authorName = (TextView)findViewById(R.id.authorText);
        authorName.setText("by:"+ book.getBookAuthor());

        TextView information = (TextView)findViewById(R.id.informationText);
        information.setText("Description: "+book.getBookInformation());

        TextView price = (TextView)findViewById(R.id.priceText);
        price.setText("CDN$"+Double.toString(book.getBookPrice()));

        ImageView iv= (ImageView)findViewById(R.id.picture);
        iv.setImageResource(book.getImageID());

        TextView inStock = (TextView)findViewById(R.id.inStockText);
        int numInStock = book.getNumberInStock();
        if(numInStock == 0)
        {
            inStock.setText("Temporarily out of stock.");
        }
        else if (numInStock <= 3)
        {
            inStock.setText("Only "+ Integer.toString(numInStock)+" in stock.");
        }
        else
        {
            inStock.setText("In stock.");
        }

    }

    public void setAllButton()
    {
        Button addCart = (Button)findViewById(R.id.addToCartButton);
        addCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //set toast
                Toast.makeText(ViewBookActivity.this,"Successfully added to the shopping cart",
                        Toast.LENGTH_SHORT).show();
                //add to customer's cart
                accessCustomer.addToCart(accountName, book);
            }
        });

        Button addWishList = (Button)findViewById(R.id.addWishListButton);
        addWishList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //set toast
                Toast.makeText(ViewBookActivity.this, "Successfully added to the wish list",
                        Toast.LENGTH_SHORT).show();
                //add to customer's wish list
                accessCustomer.addToWishList(accountName, book);
            }
        });

        Button myAccount = (Button)findViewById(R.id.myAccountButton);
        myAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ViewBookActivity.this, MyAccountActivity.class);
                intent.putExtra("name",accountName);
                startActivity(intent);
            }
        });

    }
}
