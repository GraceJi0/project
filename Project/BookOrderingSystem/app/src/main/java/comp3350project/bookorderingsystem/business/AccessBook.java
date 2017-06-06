package comp3350project.bookorderingsystem.business;


import java.util.ArrayList;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-30.
 */

public class AccessBook {
    private DataAccessStub dataAccess;

    public AccessBook(DataAccessStub newDataAccess)
    {
        dataAccess = newDataAccess;
    }

    public ArrayList<Book> getBookList()
    {
        return dataAccess.getBookList();
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
