package comp3350project.bookorderingsystem.persistence;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class DataAccessStub
{
    private LinkedList bookList;
    private LinkedList customerList;

    public DataAccessStub()
    {
        bookList = new LinkedList();
        customerList = new LinkedList();
    }

    public void open()
    {
         addBook(new Book("The Orphan's Tale: A Novel", " Pam Jenoff",
                 "A powerful novel of friendship set in a traveling circus during World War II ",
                 10.49,"Fiction"));

        addBook(new Book("The German Girl: A Novel","Armando Lucas Correa",
                "A stunningly ambitious and beautiful novel.",29.50,"Fiction"));

        addBook(new Book("Ginny Moon: A Novel"," Benjamin Ludwig",
                "“A brilliant debut.” —Graeme Simsion, New York Times bestselling author of The " +
                        "Rosie Project",30.45,"Fiction"));

        addBook(new Book("Neonatal Resuscitation"," Gary M Weiner",
                "Powerful resource for interactive, simulation-based teaching and learning.",
                82.32,"TextBooks"));

        addBook(new Book("he Magical Zoo #1"," Dan Jackson","Containing creative illustrations " +
                "and endless imagination, this book will entertain your child and you.",
                20.35,"Children & Young Adult"));

        addBook((new Book("Minecraft Steve Vs Herobrine: Herobrine Attacks!",
                " Diary Wimpy","Herobrine has kidnapped Felicia, can Minecraft Steve save " +
                "her in time?",10.86,"Comics & Graphic Novels")));

        addBook(new Book("Great Food Fast"," Martha Stewart Living Magazine",
                " 250 Recipes for Easy, Delicious Meals All Year Long ",22.16,"Magazines"));

        addBook(new Book("Out of Africa","Isak Dinesen",
                "Selected by the Modern Library as one of the 100 best nonfiction books of " +
                        "all time\n",23.5,"Non-fiction"));

        addBook(new Book("A Book That Takes Its Time"," Irene Smit, Astrid van der Hulst ",
                "An Unhurried Adventure in Creative.",26.38,"other"));

        addCustomer(new Customer("dmb001"));
        addCustomer(new Customer("tyui"));
        addCustomer(new Customer("ghjk"));
        addCustomer(new Customer("szxcv"));
        addCustomer(new Customer("asdf"));
        addCustomer(new Customer("qwer"));
        addCustomer(new Customer("svbnm"));
    }

    public void addCustomer(Customer newCustomer)
    {
        customerList.insert(newCustomer);
    }

    public void deleteCustomer(Customer newCustomer)
    {
        customerList.delete(newCustomer);
    }

    public void addBook(Book newBook)
    {
        bookList.insert(newBook);
    }

    public void deleteBook(Book newBook)
    {
        bookList.delete(newBook);
    }

}
