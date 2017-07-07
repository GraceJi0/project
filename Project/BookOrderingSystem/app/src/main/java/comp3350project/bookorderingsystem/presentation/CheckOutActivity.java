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

/**
 * Created by lee on 2017/7/2.
 */

public class CheckOutActivity extends AppCompatActivity{
    private String accountName;
    private ArrayList<Book> bookList;
    private ListView cartListView;
    private ListView wishListListView;
    private AccessCustomer accessCustomer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        accountName = intent.getStringExtra("name");

        accessCustomer = new AccessCustomer();
    }
}
