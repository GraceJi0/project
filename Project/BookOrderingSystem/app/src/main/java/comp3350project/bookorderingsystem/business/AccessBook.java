package comp3350project.bookorderingsystem.business;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.persistence.DataAccess;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;


public class AccessBook {
    private DataAccess dataAccess;

    public AccessBook()
    {
        dataAccess = Service.getDataAccess(Main.dbName);
    }

    /*******************************************************
     get book list from database
     ********************************************************/
    public List<Book> getBookList()
    {
        return dataAccess.getBookList();
    }

    /*******************************************************
     search book by title
     ********************************************************/
    public Book searchBook(String newName)
    {
        List<Book> books = dataAccess.getBookList();
        Book found = null;
        if(books!=null)
        {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().equals(newName)) {
                    found = books.get(i);
                }
            }
        }
        return found;
    }

    /*******************************************************
     search book using the given string
     ********************************************************/
    public List<Book> searchBookContain(String newname)
    {
        List<Book> books = dataAccess.getBookList();
        ArrayList<Book> found = new ArrayList<Book>();
        if(books!=null)
        {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().toLowerCase().contains(newname.toLowerCase())){
                    found.add(books.get(i));
                }
            }
        }
        return found;
    }


    /*******************************************************
     search book by category
     ********************************************************/
    public List<Book> searchBookCategory(String newCategory)
    {
        List<Book> books = dataAccess.getBookList();
        ArrayList<Book> founds = new ArrayList<Book>();
        for(int i=0; i< books.size(); i++)
        {
            if(books.get(i).getCategory().equals(newCategory))
            {
                founds.add(books.get(i));
            }
        }
        return founds;
    }

    /*******************************************************
     add a book to database
     ********************************************************/
    public boolean addBook(Book book)
    {
        return dataAccess.addBook(book);
    }


    public void editBook(Book old, Book book)
    {
        if(book != null)
        {
            Book temp = searchBook(old.getName());
            if (temp != null)
            {
                dataAccess.updateBook(old, book);
            }
        }
    }

}
