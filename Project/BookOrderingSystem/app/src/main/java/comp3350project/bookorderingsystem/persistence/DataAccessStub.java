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
        customerList=new ArrayList<Customer>();
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
        else
        {
            System.out.println("delete customer error: customer not exist");
        }
    }

    public ArrayList<Book>getBookList()
    {
        return bookList;
    }

    public void addBook(Book newBook)
    {
        boolean find = false;
        if(newBook != null)
        {
            if(newBook.getName() != null && newBook.getName() != "")
            {
                if(newBook.getBookAuthor() != null && newBook.getBookAuthor() != "")
                {
                    if(newBook.getBookPrice() > 0)
                    {
                        if(newBook.getBookInformation() != null
                                && newBook.getBookInformation() != "")
                        {
                            if(newBook.getCategory() != null && newBook.getCategory() != "")
                            {
                                for (int i = 0; i < bookList.size(); i++)
                                {
                                    if (bookList.get(i).compareName(newBook) == 0)
                                    {
                                        int inStock = 0;
                                        inStock = bookList.get(i).getNumberInStock();
                                        bookList.get(i).setNumberInStock(inStock);
                                        find = true;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("add book error: invalid book category");
                            }
                        }
                        else
                        {
                            System.out.println("add book error: invalid book information");
                        }
                    }
                    else
                    {
                        System.out.println("add book error: invalid price");
                    }
                }
                else
                {
                    System.out.println("add book error: invalid author");
                }
            }
            else
            {
                System.out.println("add book error: invalid name");
            }
            if(find == false)
            {
                bookList.add(newBook);
            }
        }
        else
        {
            System.out.println("add book error: invalid book");
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
