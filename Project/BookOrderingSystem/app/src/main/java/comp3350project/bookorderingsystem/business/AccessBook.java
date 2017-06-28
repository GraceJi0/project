package comp3350project.bookorderingsystem.business;

import android.util.IntProperty;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.persistence.DataAccess;
import comp3350project.bookorderingsystem.persistence.DataAccessObject;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-30.
 */

public class AccessBook {
    //private DataAccessStub dataAccess;
    private DataAccess dataAccess;///////////////////////////////////////////////////////////////

    public AccessBook()
    {
        dataAccess = Service.getDataAccess(Main.dbName);////////////////////////////////////
        //dataAccess = (DataAccessStub) Service.getDataAccess(Main.dbName);
    }

    public List<Book> getBookList()
    {
        return dataAccess.getBookList();
    }

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

    public List<Book> searchBookContain(String newname)
    {
        List<Book> books = dataAccess.getBookList();
        ArrayList<Book> found = new ArrayList<Book>();
        if(books!=null)
        {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().contains(newname)) {
                    found.add(books.get(i));
                }
            }
        }
        return found;
    }

    public List<Book> sortBookByNameUp(String byThis)
    {
        //to sort the book list
        
        List<Book> books = dataAccess.getBookList();
        ArrayList<Book> sort = new ArrayList<Book>();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        Log.d("aaaaaa","----------");
        if (books!=null)
        {
            if(byThis.equals("BookName"))
            {
               Log.d("bbbbbbbbbb","!!!!!!!!!1");

                for(int i=0; i< books.size(); i++) {

                    temp.add(books.get(i).compareName(books.get(0)));
                }

            }


        }



        return sort;
    }

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

    /*public String printAllBooks()
    {
        return dataAccess.printAllBooks();
    }

    public String printBooksInCategory(String newCategory)
    {
        return dataAccess.printBooksInCategory(newCategory);
    }*/

}
