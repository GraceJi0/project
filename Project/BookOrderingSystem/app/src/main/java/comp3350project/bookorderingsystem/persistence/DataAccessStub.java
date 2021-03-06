package comp3350project.bookorderingsystem.persistence;
import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class DataAccessStub
{
    private ArrayList<Book> bookList;
    private ArrayList<Customer> customerList;
    private String dbName;
    private String dbType = "stub";

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String dbName)
    {
        bookList = new ArrayList<Book>();
        customerList=new ArrayList<Customer>();
        addBook(new Book("The Orphan's Tale: A Novel", " Pam Jenoff",
                "A powerful novel of friendship set in a traveling circus during World War II ",
                10.49,"Fiction", 1, R.drawable.book1));

        addBook(new Book("The German Girl: A Novel","Armando Lucas Correa",
                "A stunningly ambitious and beautiful novel.",29.50,"Fiction",2, R.drawable.book2));

        addBook(new Book("Ginny Moon: A Novel"," Benjamin Ludwig",
                "“A brilliant debut.” —Graeme Simsion, New York Times bestselling author of The " +
                        "Rosie Project",30.45,"Fiction", 4, R.drawable.book3));

        addBook(new Book("Neonatal Resuscitation"," Gary M Weiner",
                "Powerful resource for interactive, simulation-based teaching and learning.",
                82.32,"TextBooks", 5, R.drawable.book4));

        addBook(new Book("he Magical Zoo #1"," Dan Jackson","Containing creative illustrations " +
                "and endless imagination, this book will entertain your child and you.",
                20.35,"Children & Young Adult", 2, R.drawable.book5));

        addBook((new Book("Minecraft Steve Vs Herobrine: Herobrine Attacks!",
                " Diary Wimpy","Herobrine has kidnapped Felicia, can Minecraft Steve save " +
                "her in time?",10.86,"Comics & Graphic Novels", 1, R.drawable.book6)));

        addBook(new Book("Great Food Fast"," Martha Stewart Living Magazine",
                " 250 Recipes for Easy, Delicious Meals All Year Long ",22.16,"Magazines", 3, R.drawable.book7));

        addBook(new Book("Out of Africa","Isak Dinesen",
                "Selected by the Modern Library as one of the 100 best nonfiction books of " +
                        "all time\n",23.5,"Non-fiction", 4, R.drawable.book8));

        addBook(new Book("A Book That Takes Its Time"," Irene Smit, Astrid van der Hulst ",
                "An Unhurried Adventure in Creative.",26.38,"other", 1, R.drawable.book9));

        addCustomer(new Customer("dmb001"));
        addCustomer(new Customer("tyui"));
        addCustomer(new Customer("ghjk"));
        addCustomer(new Customer("szxcv"));
        addCustomer(new Customer("asdf"));
        addCustomer(new Customer("qwer"));
        addCustomer(new Customer("svbnm"));

        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public boolean addCustomer(Customer newCustomer)
    {
        if(newCustomer != null)
        {
            if (newCustomer.getName() != null && newCustomer.getName() != "") {
                customerList.add(newCustomer);
                return true;
            }
            else
            {
                System.out.println("add customer error: information error");
                return false;
            }
        }
        else
        {
            System.out.println("add customer error: invalid customer ");
            return false;
        }
    }

    public void deleteCustomer(Customer newCustomer)
    {
        int index;

        index = customerList.indexOf(newCustomer);
        if (index >= 0)
        {
            customerList.remove(index);
        }
        else
        {
            System.out.println("delete customer error: customer not exist");
        }
    }

    public ArrayList<Book>getBookList()
    {
        return bookList;
    }

    public boolean addBook(Book newBook)
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

    public void deleteBook(Book newBook)
    {
        int index;

        index = bookList.indexOf(newBook);
        if (index >= 0)
        {
            bookList.remove(index);
        }
        else
        {
            System.out.println("delete book error: book not exist");
        }
    }
}
