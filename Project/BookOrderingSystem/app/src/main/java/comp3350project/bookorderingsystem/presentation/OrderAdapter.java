package comp3350project.bookorderingsystem.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by dinghanji on 2017-07-07.
 */

public class OrderAdapter extends ArrayAdapter<Customer>
{
    private int resourceId;

    public OrderAdapter(Context context, int textViewResourceId, List<Customer> objects)
    {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent)
    {
        Customer customer = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //ImageView bookImage = (ImageView) view.findViewById(R.id.bookImageView);
        TextView order = (TextView) view.findViewById(R.id.orderNumberText);
        TextView customerName = (TextView) view.findViewById(R.id.customerNameText);
        //TextView bookPrice = (TextView) view.findViewById(R.id.bookPriceText);
        //bookImage.setImageResource(book.getImageID());
        customerName.setText(customer.getName());
        //bookAuthor.setText("by: "+book.getBookAuthor());
        //bookPrice.setText("$"+Double.toString(book.getBookPrice()));
        return view;
    }
}
