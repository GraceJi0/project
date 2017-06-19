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

        saveInformation();
    }

    public void saveInformation()
    {

        final EditText name = (EditText)findViewById(R.id.nameEditText);
        final String bookName = name.getText().toString();

        final EditText author = (EditText)findViewById(R.id.authorEditText);
        final String bookAuthor = author.getText().toString();


        final EditText price = (EditText)findViewById(R.id.priceEditText);
        price.setText("0.0");
        final double bookPrice= Double.parseDouble(price.getText().toString());


        final EditText description = (EditText)findViewById(R.id.decriptionEditText);
        final String bookDescription = description.getText().toString();

        final EditText inStock = (EditText)findViewById(R.id.inStockEditText);
        inStock.setText("0");
        final int bookInStock = Integer.parseInt(inStock.getText().toString());

        final EditText category = (EditText)findViewById(R.id.categoryEditText);
        final String bookCategory = category.getText().toString();


        Button save = (Button) findViewById(R.id.saveNewButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               accessBook.addBook(new Book( bookName, bookAuthor, bookDescription, bookPrice, bookCategory,
                bookInStock, R.drawable.noimage));
                Toast.makeText(AddBookActivity.this, "Successfully saved",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
