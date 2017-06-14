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


        AccessBook accessBook = new AccessBook();
        book = accessBook.searchBook(bookName);
        editBookInformation();
        setImageView();
    }

    public void editBookInformation()
    {
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

        setButton(name, author,price, description, inStock);
    }

    public void setImageView()
    {
        ImageView iv= (ImageView)findViewById(R.id.bookPicture);
        iv.setImageResource(book.getImageID());
    }

    public void setButton(final EditText name, final EditText author, final EditText price,
                          final EditText description, final EditText inStock )
    {
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
                Toast.makeText(EditBookActivity.this,"Successfully saved",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button returnBut = (Button)findViewById(R.id.returnButton);
        returnBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent init = new Intent(EditBookActivity.this, ManagerViewBooksActivity.class);
                init.putExtra("name", accountName);
                startActivity(init);
            }
        });
    }


}
