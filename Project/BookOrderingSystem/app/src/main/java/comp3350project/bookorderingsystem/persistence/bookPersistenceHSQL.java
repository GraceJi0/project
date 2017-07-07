package comp3350project.bookorderingsystem.persistence;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import android.media.Image;
import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Picture;


/**
 * Created by rico on 2017-06-28.
 */

public class bookPersistenceHSQL {

    private List<Picture> ImageList;

   public bookPersistenceHSQL()   //the constructor
   {
       ImageList = new ArrayList<Picture>();
       initialImageList();   //initalize the image ilst, which stores the pictures of the books in the DB
   }

    public List<Book> getBookList(String cmdString, Statement st3, ResultSet rs5, String warn )
    {
        List<Book> bookList;
        bookList = new ArrayList<Book>();

        try
        {
            cmdString = "select * from book";
            rs5 = st3.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            warn = processSQLError(e);
        }

        try
        {
            while(rs5.next())   //get the book one by one
            {
                String name = rs5.getString("name");
                String author = rs5.getString("author");
                String info = rs5.getString("info");
                double price = rs5.getDouble("price");
                String category = rs5.getString("category");
                int instock = rs5.getInt("numberinstock");
                int pid = rs5.getInt("pictureid");	//the picture id

                int picture = R.drawable.noimage;	//try to get the picture, but assume not found at the beginning

                for(int i=0;i<ImageList.size();i++){
                    if(ImageList.get(i).getPID() == pid){
                        picture = ImageList.get(i).getPicture();
                        break;
                    }
                }

                Book theBook = new Book(name, author, info, price, category, instock, picture);   //create the book object
                bookList.add(theBook);   //store to the search book result before returning the list
            }
        }
        catch(Exception e)
        {
            warn = processSQLError(e);
        }
        return bookList;
    }

    public boolean validBook(Book theBook)  //check if a book is valid
    {
        if(theBook != null)
        {
            if ((theBook.getName() != null) && (!theBook.getName().equals("")))   //there must have something in the name
            {
                if ((theBook.getBookAuthor() != null) && (!theBook.getBookAuthor().equals("")))   //must have author
                {
                    if (theBook.getBookPrice() >= 0)   //price can never be less than 0
                    {
                        if (theBook.getNumberInStock() >= 0)   //number in stock cannot less than 0
                        {
                            if ((theBook.getCategory() != null) && (!theBook.getCategory().equals("")))   //must have a category
                            {
                                return true;
                            }
                            else
                                return false;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    public boolean addBook(Book newBook, String warn, String cmdString, Statement st1, ResultSet rs5, int updateCount, boolean result)//tested, no problem
    {
        if(validBook(newBook))   //check book validation before adding it
        {
            String values;

            warn = null;
            try {
                values = "'" + newBook.getName()
                        + "', '" + newBook.getBookAuthor()
                        + "', '" + newBook.getBookInformation()
                        + "', " + newBook.getBookPrice()
                        + ", '" + newBook.getCategory()
                        + "', " + newBook.getNumberInStock()
                        + ", " + newBook.getImageID();
                cmdString = "Insert into book " + " Values(" + values + ")";   //insert a new book into the book table
                updateCount = st1.executeUpdate(cmdString);   //execute the command
                warn = checkWarning(st1, updateCount);
                result = true;
            } catch (Exception e) {
                warn = processSQLError(e);
                result = false;
            }
        }
        else
            result = false;
        return result;
    }

    public boolean updateBook(Book old, Book theBook,String warn, String cmdString, Statement st1, int updateCount, boolean result)
    {
        if(validBook(theBook))   //check validation of a book before updating it
        {
            String values = "";
            String where = ("name='"+old.getName()+ "';");
            int pre = 0;

            warn = null;
            try
            {   //content to be modified
                if(!theBook.getName().equals(old.getName()))
                {
                    values += ("name='" + theBook.getName() + "'");
                    pre = 1;
                }
                if(!theBook.getBookAuthor().equals(old.getBookAuthor()))
                {
                    if(pre == 1)
                    {
                        values += ", ";
                    }
                    values += ("author='" + theBook.getBookAuthor() + "'");
                    pre = 1;
                }
                if (theBook.getBookPrice() != old.getBookPrice())
                {
                    if(pre == 1)
                    {
                        values += ", ";
                    }
                    values += ("price=" + theBook.getBookPrice());  //the number
                    pre = 1;
                }
                if(!theBook.getBookInformation().equals(old.getBookInformation()))
                {
                    if(pre == 1)
                    {
                        values += ", ";
                    }
                    values += ("info='" + theBook.getBookInformation() + "'");
                    pre = 1;
                }
                if(theBook.getNumberInStock()!= old.getNumberInStock())
                {
                    if(pre == 1)
                    {
                        values += ", ";
                    }
                    values += ("numberinstock=" + theBook.getNumberInStock());
                    pre = 1;
                }
                if(!theBook.getCategory().equals(old.getCategory()))
                {
                    if(pre == 1)
                    {
                        values += ", ";
                    }
                    values += ("category ='" + theBook.getCategory() + "'");
                    pre = 1;
                }
                cmdString = "UPDATE book\n " + "SET " + values + " \n WHERE " + where;

                updateCount = st1.executeUpdate(cmdString);
                warn = checkWarning(st1, updateCount);
                result=true;
            } catch (Exception e) {
                warn = processSQLError(e);
                result=false;
            }
        }
        else
            result = false;
        return result;
    }

    public void initialImageList()
    {

        ImageList.add(new Picture(1, R.drawable.book1));
        ImageList.add(new Picture(2, R.drawable.book2));
        ImageList.add(new Picture(3, R.drawable.book3));
        ImageList.add(new Picture(4, R.drawable.book4));
        ImageList.add(new Picture(5, R.drawable.book5));
        ImageList.add(new Picture(6, R.drawable.book6));
        ImageList.add(new Picture(7, R.drawable.book7));
        ImageList.add(new Picture(8, R.drawable.book8));
        ImageList.add(new Picture(9, R.drawable.book9));
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}