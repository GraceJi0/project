package comp3350project.bookorderingsystem.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

public class CheckOutActivity extends AppCompatActivity
{
    String accountName;
    private ListView cartListView;
    private AccessCustomer accessCustomer;
    private Customer customer;
    private AccessOrder accessOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        accessOrder = new AccessOrder();
        accessCustomer = new AccessCustomer();
        customer=accessCustomer.findCustomer(accountName);
        setCartListView(accessCustomer.getCustomerCart(accountName));
        editPaymentInformation();
        setTextView();
        viewPrice();
        logOut();
    }

    public void logOut()
    {
        Button showLogOut=(Button)findViewById(R.id.logOutButton);
        showLogOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                accountName="";;
                Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
                Toast.makeText(CheckOutActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addnewOrder(String name)
    {
        int orderNumber;
        Order newOrder;
        orderNumber=accessOrder.orderSize();
        newOrder= new Order(orderNumber,accessCustomer.getCustomerCart(accountName),name,
                accessCustomer.getTotalPrice(accountName));
        accessOrder.addOrder(newOrder);
        accessCustomer.addOrder(accountName,newOrder);
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
        setButton(name,email,address, cardNumber);
    }

    public void setButton(final EditText name, final EditText email, final EditText address,
                          final EditText cardNumber)
    {
        //set save button
        Button checkOut = (Button)findViewById(R.id.checkbutton);
        checkOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(name.getText().toString().compareTo("")==0 ||email.getText().toString().compareTo("")==0 || address.getText().toString().compareTo("")==0||cardNumber.getText().toString().compareTo("")==0)
                {
                    Toast.makeText(CheckOutActivity.this,
                            "please fill payment information",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    customer.setRealName(name.getText().toString());
                    customer.setEmail(email.getText().toString());
                    customer.setAddress(address.getText().toString());
                    customer.setCardNumber(cardNumber.getText().toString());
                    addnewOrder(name.getText().toString());
                    Intent intent = new Intent(CheckOutActivity.this, MyAccountActivity.class);
                    intent.putExtra("name",accountName );
                    startActivity(intent);
                    Toast.makeText(CheckOutActivity.this,
                            "check out successful",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

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
