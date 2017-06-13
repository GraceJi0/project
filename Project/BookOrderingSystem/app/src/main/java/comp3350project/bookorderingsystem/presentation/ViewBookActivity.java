package comp3350project.bookorderingsystem.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Book;

/*when click on an item in the search list, look for the detail information of this book.*/
public class ViewBookActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "comp3010_group10.bookordering.MESSAGE";
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        AccessCustomer accessCustomer = new AccessCustomer();
        Intent intent = getIntent();
        String bookName = intent.getStringExtra("message");
        AccessBook accessBook = new AccessBook();
        book = accessBook.searchBook(bookName);
        setAllText(bookName);
        setAllButton();

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
                Toast.makeText(ViewBookActivity.this,"Successfully added to the shopping cart",
                        Toast.LENGTH_SHORT).show();

            }
        });

        Button addWishList = (Button)findViewById(R.id.addWishListButton);
        addWishList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ViewBookActivity.this, "Successfully added to the wish list",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
