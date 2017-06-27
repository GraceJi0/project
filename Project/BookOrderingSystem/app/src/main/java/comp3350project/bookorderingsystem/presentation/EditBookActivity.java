package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;

/**
 * Created by dinghanji on 2017-06-13.
 */

public class EditBookActivity extends AppCompatActivity
{
    private AccessBook accessBook;
    private String accountName;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra("name and edit");

        String bookName = message[0];
        accountName = message[1];


        accessBook = new AccessBook();

        book = accessBook.searchBook(bookName);

        editBookInformation();
        setImageView();

        //accessBook.editBook(book);
    }

    public void editBookInformation()
    {
        //set book's all information
        EditText name = (EditText) findViewById(R.id.nameEditText);
        name.setText(book.getName());

        EditText author = (EditText) findViewById(R.id.authorEditText);
        author.setText(book.getBookAuthor());

        EditText price = (EditText) findViewById(R.id.priceEdixText);
        price.setText(Double.toString(book.getBookPrice()));

        EditText description = (EditText) findViewById(R.id.decriptionEditText);
        description.setText(book.getBookInformation());

        EditText inStock = (EditText) findViewById(R.id.inStockEditText);
        inStock.setText(Integer.toString(book.getNumberInStock()));

        EditText category = (EditText) findViewById(R.id.categoryEditText);
        category.setText(book.getCategory());

        setButton(name, author,price, description, inStock,category);
    }

    public void setImageView()
    {
        //set book image
        ImageView iv= (ImageView)findViewById(R.id.bookPicture);
        iv.setImageResource(book.getImageID());
    }

    public void setButton(final EditText name, final EditText author, final EditText price,
                          final EditText description, final EditText inStock,final EditText category )
    {
        //set save button
        Button save = (Button)findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                book.setName(name.getText().toString());
                book.setBookAuthor(author.getText().toString());
                book.setBookPrice(Double.parseDouble(price.getText().toString()));
                book.setBookInformation(description.getText().toString());
                book.setNumberInStock(Integer.parseInt(inStock.getText().toString()));
                book.setCategory(category.getText().toString());

                accessBook.editBook(book);

                editBookInformation();
                Toast.makeText(EditBookActivity.this,"Successfully saved",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //set go back button, send back account name information
        /*Button returnBut = (Button)findViewById(R.id.returnButton);
        returnBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent init = new Intent(EditBookActivity.this, ManagerViewBooksActivity.class);
                init.putExtra("name", accountName);
                startActivity(init);
            }
        });*/
    }


}
