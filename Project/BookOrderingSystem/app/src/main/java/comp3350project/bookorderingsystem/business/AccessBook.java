package comp3350project.bookorderingsystem.business;

import java.util.ArrayList;

import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.persistence.DataAccess;


public class AccessBook {
    private DataAccess dataAccess;

    public AccessBook()
    {
        dataAccess = Service.getDataAccess(Main.dbName);
    }

    /*******************************
     * get the book list from database
     ******************************/
    public ArrayList<Book> getBookList()
    {
        return dataAccess.getBookList();
    }

    /*******************************
     * search the book by the given book title, return the book
     ******************************/
    public Book searchBook(String newName)
    {
        ArrayList<Book> books = dataAccess.getBookList();
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

    /*******************************
     * search the book by the given string, if the book tile contain the given string,
     * put it in to a list an return it.
     ******************************/
    public ArrayList<Book> searchBookContain(String newname)
    {
        ArrayList<Book> books = dataAccess.getBookList();
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


    /*******************************
     * search the book by the given category, return a list of books
     ******************************/
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

    /*******************************
     * add a new book to the database
     ******************************/
    public boolean addBook(Book book)
    {
        return dataAccess.addBook(book);
    }

    /*******************************
     * update book information
     ******************************/
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
