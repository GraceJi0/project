package comp3350project.bookorderingsystem.presentation;

import android.content.Context;
import android.content.Intent;
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
import comp3350project.bookorderingsystem.objects.Order;

public class OrderAdapter extends ArrayAdapter<Order>
{
    private int resourceId;

    public OrderAdapter(Context context, int textViewResourceId, List<Order> objects)
    {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent)
    {
        Order order = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView orderNum = (TextView) view.findViewById(R.id.orderNumberText);
        TextView customerName = (TextView) view.findViewById(R.id.customerAccountText);
        orderNum.setText(Integer.toString(order.getOrderNumber()));
        customerName.setText(order.getAccountName());
        return view;
    }
}
