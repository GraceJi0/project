package comp3350project.bookorderingsystem.persistence;
import java.util.ArrayList;
import java.util.List;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class DataAccessStub
{
    private ArrayList<Book> bookList;
    private ArrayList<Customer> customerList;


    public DataAccessStub() {
    }

    public void open()
    {
        bookList = new ArrayList<Book>();
        Book book;
        Customer cusomter;
        bookList.add(new Book("The Orphan's Tale: A Novel", " Pam Jenoff",
                "A powerful novel of friendship set in a traveling circus during World War II ",
                10.49,"Fiction"));

        bookList.add(new Book("The German Girl: A Novel","Armando Lucas Correa",
                "A stunningly ambitious and beautiful novel.",29.50,"Fiction"));

        bookList.add(new Book("Ginny Moon: A Novel"," Benjamin Ludwig",
                "“A brilliant debut.” —Graeme Simsion, New York Times bestselling author of The " +
                        "Rosie Project",30.45,"Fiction"));

        bookList.add(new Book("Neonatal Resuscitation"," Gary M Weiner",
                "Powerful resource for interactive, simulation-based teaching and learning.",
                82.32,"TextBooks"));

        bookList.add(new Book("he Magical Zoo #1"," Dan Jackson","Containing creative illustrations " +
                "and endless imagination, this book will entertain your child and you.",
                20.35,"Children & Young Adult"));

        bookList.add((new Book("Minecraft Steve Vs Herobrine: Herobrine Attacks!",
                " Diary Wimpy","Herobrine has kidnapped Felicia, can Minecraft Steve save " +
                "her in time?",10.86,"Comics & Graphic Novels")));

        bookList.add(new Book("Great Food Fast"," Martha Stewart Living Magazine",
                " 250 Recipes for Easy, Delicious Meals All Year Long ",22.16,"Magazines"));

        bookList.add(new Book("Out of Africa","Isak Dinesen",
                "Selected by the Modern Library as one of the 100 best nonfiction books of " +
                        "all time\n",23.5,"Non-fiction"));

        bookList.add(new Book("A Book That Takes Its Time"," Irene Smit, Astrid van der Hulst ",
                "An Unhurried Adventure in Creative.",26.38,"other"));

        customerList=new ArrayList<Customer>();
        customerList.add(new Customer("dmb001"));
        customerList.add(new Customer("tyui"));
        customerList.add(new Customer("ghjk"));
        customerList.add(new Customer("szxcv"));
        customerList.add(new Customer("asdf"));
        customerList.add(new Customer("qwer"));
        customerList.add(new Customer("svbnm"));
    }

    public void addCustomer(Customer newCustomer)
    {
        customerList.add(newCustomer);
    }

    public void deleteCustomer(Customer newCustomer)
    {
        int index;

        index = customerList.indexOf(newCustomer);
        if (index >= 0)
        {
            customerList.remove(index);
        }
    }

    public ArrayList<Book>getBookList()
    {
        return bookList;
    }

    public void addBook(Book newBook)
    {
        bookList.add(newBook);
    }

    public void deleteBook(Book newBook)
    {
        int index;

        index = bookList.indexOf(newBook);
        if (index >= 0)
        {
            bookList.remove(index);
        }
    }
}
