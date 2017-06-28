package comp3350project.bookorderingsystem.business;

import android.util.IntProperty;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<Book> getBookList()
    {
        return dataAccess.getBookList();
    }

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

    public ArrayList<Book> sortBookBy(String byThis, ArrayList<Book> books)
    {
        //to sort the book list

        //ArrayList<Book> books = dataAccess.getBookList();
        ArrayList<Book> sort;
        //ArrayList<Integer> temp = new ArrayList<Integer>();
        sort = books;

            if(byThis.equals("BookName"))
            {
                Log.d("Selected BookName","work");
                sort = insertionSortName(sort);
            }
            if(byThis.equals("AuthorName"))
            {
                Log.d("Selected Author Name","work");
                insertionSortAuthorName(sort);

            }
          if(byThis.equals("Price Low"))
            {
                Log.d("Selected Price Low","work");
                insertionSortPriceLow(sort);

             }
        if(byThis.equals("Price High"))
        {
            Log.d("Selected Price High","work");
            insertionSortPriceHigh(sort);

        }



        return sort;
    }
    public ArrayList<Book> insertionSortPriceHigh(ArrayList<Book> books)
    {
        int j;
        Book insertVal;
        ArrayList<Book> temp = books;
        Double price;
        for(int i=1; i< temp.size(); i++) {
            insertVal = temp.get(i);
            price = insertVal.getBookPrice();
            for(j = i-1; (j >= 0 && (temp.get(j).getBookPrice())<price);j--)
            {
                temp.set(j+1,temp.get(j));

            }
            temp.set(j+1,insertVal);
        }

        return temp;
    }
    public ArrayList<Book> insertionSortPriceLow(ArrayList<Book> books)
    {
        int j;
        Book insertVal;
        ArrayList<Book> temp = books;
        Double price;
        for(int i=1; i< temp.size(); i++) {
            insertVal = temp.get(i);
            price = insertVal.getBookPrice();
            for(j = i-1; (j >= 0 && (temp.get(j).getBookPrice())>price);j--)
            {
                temp.set(j+1,temp.get(j));

            }
            temp.set(j+1,insertVal);
        }

        return temp;
    }

    public ArrayList<Book> insertionSortAuthorName(ArrayList<Book> books)
    {
        int j;
        Book insertVal;
        ArrayList<Book> temp = books;
        String author;
        for(int i=1; i< temp.size(); i++) {
            insertVal = temp.get(i);
            author = insertVal.getBookAuthor();
            for(j = i-1; (j >= 0 && (temp.get(j).getBookAuthor()).compareTo(author)>0);j--)
            {
                temp.set(j+1,temp.get(j));

            }
            temp.set(j+1,insertVal);
        }

        return temp;
    }
    public ArrayList<Book> insertionSortName(ArrayList<Book> books)
    {
        int j;
        Book insertVal;
        ArrayList<Book> temp = books;

        for(int i=1; i< temp.size(); i++) {
            insertVal = temp.get(i);

            for(j = i-1; (j >= 0 && temp.get(j).compareName(insertVal)<0);j--)
            {
                temp.set(j+1,temp.get(j));

            }
            temp.set(j+1,insertVal);
        }

        return temp;
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
