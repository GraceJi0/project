package comp3350project.bookorderingsystem.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

public class CheckOutActivity extends AppCompatActivity
{
    String accountName;
    private ListView cartListView;
    private AccessCustomer accessCustomer;
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        accessCustomer = new AccessCustomer();
        customer=accessCustomer.findCustomer(accountName);
        setCartListView(accessCustomer.getCustomerCart(accountName));
        editPaymentInformation();
        setTextView();
        viewPrice();
    }

    public void setTextView()
    {
        TextView account = (TextView)findViewById(R.id.accountText);
        account.setText(accountName);
    }

    public void editPaymentInformation()
    {
        EditText name = (EditText) findViewById(R.id.nameEditText);
        name.setText(customer.getRealName());
        EditText email = (EditText) findViewById(R.id.emailEditText);
        email.setText(customer.getEmail());
        EditText address = (EditText) findViewById(R.id.addressEditText);
        address.setText(customer.getAddress());
        EditText cardNumber = (EditText) findViewById(R.id.cardNumberEditText);
        cardNumber.setText(customer.getCardNumber());
    }
    
    public void setCartListView(final List<Book> bookList)
    {
        //view bookList
        BookAdapter adapter = new BookAdapter(CheckOutActivity.this,
                R.layout.book_item,bookList);
        cartListView= (ListView) findViewById(R.id.cartList);
        cartListView.setAdapter(adapter);

        //set bookList clickable
        cartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                String bookName = book.getName();

                Intent intent = new Intent(CheckOutActivity.this, ViewBookActivity.class);
                String[] message = {bookName, accountName};
                intent.putExtra("name and view", message);
                startActivity(intent);
            }
        });
    }
    public void viewPrice()
    {
        TextView price = (TextView)findViewById(R.id.priceText);
        String prices = "Total prices is: $"+String.valueOf(accessCustomer.getTotalPrice(accountName))+" (included taxes)";
        price.setText(prices);
    }
}
