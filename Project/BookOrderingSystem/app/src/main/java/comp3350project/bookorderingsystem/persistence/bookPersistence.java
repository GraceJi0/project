package comp3350project.bookorderingsystem.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by rico on 2017-06-28.
 */

public class bookPersistence {
    public bookPersistence(){}

    public List<Book> getBookList(List<Book> bookList)
    {
        return bookList;
    }


    public boolean addBook(Book newBook, List<Book> bookList)
    {
        boolean find = false;
        if(newBook != null)
        {
            if(newBook.getName() != null && newBook.getName() != "")
            {
                if(newBook.getBookAuthor() != null && newBook.getBookAuthor() != "")
                {
                    if(newBook.getBookPrice() >= 0)
                    {
                        if(newBook.getNumberInStock() >= 0)
                        {
                            if (newBook.getCategory() != null && newBook.getCategory() != "")
                            {
                                for (int i = 0; i < bookList.size(); i++) {
                                    Book check = bookList.get(i);
                                    if (check.compareName(newBook) == 0)    //if the book already exists
                                    {
                                        find = true;    //the book is found
                                        check.setNumberInStock(check.getNumberInStock() + 1); //increase the number of book in stock by 1
                                    }
                                }
                                if (find == false)   //this is a new book
                                {
                                    bookList.add(newBook);
                                }
                                return true;
                            }
                            else
                            {
                                System.out.println("add book error: invalid book category");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("add book error: number instock cannot less than 0");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("add book error: invalid price");
                        return false;
                    }
                }
                else
                {
                    System.out.println("add book error: invalid author");
                    return false;
                }
            }
            else
            {
                System.out.println("add book error: invalid name");
                return false;
            }
        }
        else
        {
            System.out.println("add book error: invalid book");
            return false;
        }
    }
    public boolean updateBook()
    {
        return true;
    }
}
