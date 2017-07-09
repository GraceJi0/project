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

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.business.AccessOrder;
import comp3350project.bookorderingsystem.business.AccessBook;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

public class CheckOutActivity extends AppCompatActivity
{
    String accountName;
    private ListView cartListView;
    private AccessCustomer accessCustomer;
    private AccessBook accessBook;
    private AccessOrder accessOrder;
    private List<Book> bookList;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");
        accessOrder = new AccessOrder();
        accessBook=new AccessBook();
        accessCustomer = new AccessCustomer();
        bookList=accessBook.getBookList();
       // customer=accessCustomer.findCustomer(accountName);
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

    public void addnewOrder( String customerName, String cardNumber, String email, String address)
    {
        int orderNumber;
        orderNumber=accessOrder.orderSize();
        order=new Order(orderNumber,accessCustomer.getCustomerCart(accountName),accountName,customerName,cardNumber,email,address,accessCustomer.getTotalPrice(accountName),"Waiting");
        accessOrder.addOrder(order);
        accessCustomer.addOrder(accountName,order);
    }
    public void setTextView()
    {
        TextView account = (TextView)findViewById(R.id.accountText);
        account.setText(accountName);
    }

    public void editPaymentInformation()
    {
        EditText name = (EditText) findViewById(R.id.nameEditText);
        EditText email = (EditText) findViewById(R.id.emailEditText);
        EditText address = (EditText) findViewById(R.id.addressEditText);
        EditText cardNumber = (EditText) findViewById(R.id.cardNumberEditText);
        setButton(name,email,address, cardNumber);
    }

    public Boolean checkStock()
    {
        boolean enoughStock=true;
        List<Book> cartList =accessCustomer.getCustomerCart(accountName);
        List<Book> temp1 = new ArrayList<Book>();
        int i=0;
        for(i=0;i<bookList.size();i++)
        {
            Book newBook=new Book(bookList.get(i).getName(),bookList.get(i).getBookAuthor(),bookList.get(i).getBookInformation(), bookList.get(i).getBookPrice(),
                    bookList.get(i).getCategory(),bookList.get(i).getNumberInStock(),bookList.get(i).getImageID());
            temp1.add(newBook);
        }
        i=0;
        while(enoughStock && i<cartList.size()) {
                String tempName = cartList.get(i).getName();
                for (int j = 0; j < temp1.size(); j++) {
                    Book tempBook = temp1.get(j);
                    if (tempBook.getName().equals(tempName)) {
                        int newStock = tempBook.getNumberInStock() - 1;
                        if (newStock < 0) {
                            enoughStock = false;
                        } else {
                            tempBook.setNumberInStock(newStock);
                        }
                    }
                }
            i++;
        }

            if (enoughStock) {
                for (i = 0; i < cartList.size(); i++) {
                    String tempName = cartList.get(i).getName();
                    for (int j = 0; j < bookList.size(); j++) {
                        Book tempBook = bookList.get(j);
                        if (tempBook.getName().equals(tempName)) {
                            int newStock = tempBook.getNumberInStock() - 1;
                            tempBook.setNumberInStock(newStock);
                        }
                    }
                }
            }
            return enoughStock;
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
                else if(!checkStock())
                {
                    Toast.makeText(CheckOutActivity.this,
                            "not enough stock",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addnewOrder(name.getText().toString(),cardNumber.getText().toString(),email.getText().toString(),address.getText().toString());
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
