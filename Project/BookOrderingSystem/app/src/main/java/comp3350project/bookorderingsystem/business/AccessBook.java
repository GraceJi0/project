package comp3350project.bookorderingsystem.business;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-30.
 */

public class AccessBook {
    private DataAccessStub dataAccess;

    public AccessBook()
    {
        dataAccess = (DataAccessStub) Service.getDataAccess(Main.dbName);
    }

    public ArrayList<Book> getBookList()
    {
        return dataAccess.getBookList();
    }

    public Book searchBook(String newName)
    {
        ArrayList<Book> books = dataAccess.getBookList();
        Book found = null;
        for(int i = 0; i < books.size();i++)
        {
            if(books.get(i).getName().equals(newName))
            {
                found = books.get(i);
            }
        }
        return found;
    }

    public ArrayList<Book> searchBookContain(String newname)
    {
        ArrayList<Book> books = dataAccess.getBookList();
        ArrayList<Book> found = new ArrayList<Book>();
        for(int i = 0; i < books.size();i++)
        {
            if(books.get(i).getName().contains(newname))
            {
                found.add(books.get(i));
            }
        }
        return found;
    }

    public ArrayList<Book> searchBookCategory(String newCategory)
    {
        ArrayList<Book> books = dataAccess.getBookList();
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



    /*public String printAllBooks()
    {
        return dataAccess.printAllBooks();
    }

    public String printBooksInCategory(String newCategory)
    {
        return dataAccess.printBooksInCategory(newCategory);
    }*/

}
