package comp3350project.bookorderingsystem.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.objects.Book;

//*******************************************************
//override adapter class for display books' information with a check box in listView
//the view for each book is booklist_item.xml
//*********************************************************
public class BookWithCheckboxAdapter extends ArrayAdapter<Book>
{
    private int resourceId;
    private ArrayList<Book> selected;
    public BookWithCheckboxAdapter(Context context, int textViewResourceId, List<Book> objects)
    {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
        selected = new ArrayList<Book>();
    }


    /*******************************************************
     *overriden the getView method
     *when user click the check box of a book, add the book to the selected book list,
     * so we can delete them together.
    ********************************************************/
    @Override
    public View getView(int position, View converView, ViewGroup parent)
    {
        final Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView bookImage = (ImageView) view.findViewById(R.id.bookImageView);
        TextView bookName = (TextView) view.findViewById(R.id.bookNameText);
        TextView bookAuthor = (TextView) view.findViewById(R.id.bookAuthorText);
        TextView bookPrice = (TextView) view.findViewById(R.id.bookPriceText);
        CheckBox select = (CheckBox) view.findViewById(R.id.listSelect);
        select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked())
                {
                    selected.add(book);
                }
                else
                {
                    deleteFromSelected(book);
                }
            }
        });
        bookImage.setImageResource(book.getImageID());
        bookName.setText(book.getName());
        bookAuthor.setText("by: "+book.getBookAuthor());
        bookPrice.setText("$"+Double.toString(book.getBookPrice()));
        return view;
    }

    /*******************************************************
     *get the book list that we want to delete
     ********************************************************/
    public ArrayList<Book> getSelectedBooks()
    {
        return selected;
    }

    /*******************************************************
     *delete a book from the selected book list
     ********************************************************/
    public void deleteFromSelected(Book book)
    {
        for(int i = 0; i < selected.size();i++)
        {
            if(selected.get(i).getName().equals(book.getName()))
            {
                selected.remove(i);
            }
        }
    }
}
