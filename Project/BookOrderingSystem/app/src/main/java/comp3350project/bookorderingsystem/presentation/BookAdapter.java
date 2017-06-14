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

/**
 * Created by dinghanji on 2017-06-13.
 */

//*******************************************************
//override adapter class for display books' information in listView
//*********************************************************
public class BookAdapter extends ArrayAdapter<Book>
{
        private int resourceId;

        public BookAdapter(Context context, int textViewResourceId, List<Book> objects)
        {
            super(context, textViewResourceId,objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent)
        {
            Book book = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            ImageView bookImage = (ImageView) view.findViewById(R.id.bookImageView);
            TextView bookName = (TextView) view.findViewById(R.id.bookNameText);
            TextView bookAuthor = (TextView) view.findViewById(R.id.bookAuthorText);
            bookImage.setImageResource(book.getImageID());
            bookName.setText(book.getName());
            bookAuthor.setText("by: "+book.getBookAuthor());
            return view;
        }
}
