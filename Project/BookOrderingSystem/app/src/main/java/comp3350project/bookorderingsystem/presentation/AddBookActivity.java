package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

/**
 * Created by dinghanji on 2017-06-17.
 */

public class AddBookActivity extends AppCompatActivity {

    private String accountName;
    AccessBook accessBook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        accessBook = new AccessBook();

        setBookInformation();
    }

    public void setBookInformation() {

         EditText name = (EditText) findViewById(R.id.nameEditText);
         EditText author = (EditText) findViewById(R.id.authorEditText);
         EditText price = (EditText) findViewById(R.id.priceEditText);
        //price.setText("0.0");
         EditText description = (EditText) findViewById(R.id.decriptionEditText);
         EditText inStock = (EditText) findViewById(R.id.inStockEditText);
        //inStock.setText("0");
         EditText category = (EditText) findViewById(R.id.categoryEditText);
        saveInformation(name, author, price, description, inStock, category);
    }
    public void saveInformation(final EditText name, final EditText author, final EditText price,
                                final EditText description, final EditText inStock, final EditText category)
    {

        Button save = (Button) findViewById(R.id.saveNewButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String bookName = name.getText().toString();
                String bookAuthor = author.getText().toString();
                double bookPrice = Double.parseDouble(price.getText().toString());
                String bookDescription = description.getText().toString();
                int bookInStock = Integer.parseInt(inStock.getText().toString());
                String bookCategory = category.getText().toString();
                Book book = new Book( bookName, bookAuthor, bookDescription, bookPrice, bookCategory,
                        bookInStock, R.drawable.noimage);
               accessBook.addBook(book);
                Toast.makeText(AddBookActivity.this, "Successfully saved",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
