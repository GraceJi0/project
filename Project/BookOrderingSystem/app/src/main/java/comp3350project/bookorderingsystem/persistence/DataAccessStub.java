package comp3350project.bookorderingsystem.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

public class DataAccessStub implements DataAccess
{
    bookPersistence BookPersistence=new bookPersistence();
    customerPersistence CustomerPersistence=new customerPersistence();
    private String dbName;
    private String dbType = "stub";

    private List<Book> bookList;
    private List<Customer> customerList;
    private List<Order> allOrder;

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
        allOrder = new ArrayList<Order>();
        //insert a series of data into the DB as initial datas
        //the Books
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

        addBook(new Book("The Magical Zoo #1"," Dan Jackson","Containing creative illustrations " +
                "and endless imagination, this book will entertain your child and you.",
                20.35,"Children and Young Adult", 2, R.drawable.book5));

        addBook((new Book("Minecraft Steve Vs Herobrine: Herobrine Attacks!",
                " Diary Wimpy","Herobrine has kidnapped Felicia, can Minecraft Steve save " +
                "her in time?",10.86,"Comics and Graphic Novels", 1, R.drawable.book6)));

        addBook(new Book("Great Food Fast"," Martha Stewart Living Magazine",
                " 250 Recipes for Easy, Delicious Meals All Year Long ",22.16,"Magazines", 3, R.drawable.book7));

        addBook(new Book("Out of Africa","Isak Dinesen",
                "Selected by the Modern Library as one of the 100 best nonfiction books of " +
                        "all time\n",23.5,"Non-fiction", 4, R.drawable.book8));

        addBook(new Book("A Book That Takes Its Time"," Irene Smit, Astrid van der Hulst ",
                "An Unhurried Adventure in Creative.",26.38,"other", 1, R.drawable.book9));

        //the Customers
        addCustomer(new Customer("dmb001","0000"));
        addCustomer(new Customer("tyui","1111"));
        addCustomer(new Customer("ghjk","2222"));
        addCustomer(new Customer("szxcv","3333"));
        addCustomer(new Customer("asdf","1234"));
        addCustomer(new Customer("qwer","4444"));
        addCustomer(new Customer("1","1"));
        addCustomer(new Customer("svbnm","5555"));
        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public List<Customer> getCustomerList(){
        return CustomerPersistence.getCustomerList(customerList);
    }

    public boolean addToCart(Customer customerName, Book bookName)
    {
        return CustomerPersistence.addToCart();
    }

    public boolean deleteFromCart(Customer customer, Book book)
    {
        return CustomerPersistence.deleteFromCart();
    }

    public boolean addToWishList(Customer customer, Book book)
    {
        return CustomerPersistence.addToWishList();
    }

    public boolean deleteFromWishList(Customer customer, Book book)
    {
        return CustomerPersistence.deleteFromWishList();
    }

    public boolean addCustomer(Customer newCustomer)
    {
        return CustomerPersistence.addCustomer(newCustomer,customerList);
    }

    public List<Book> getBookList()
    {
        return BookPersistence.getBookList(bookList);
    }

    public boolean addBook(Book newBook)
    {
        return BookPersistence.addBook(newBook, bookList);
    }

    public boolean updateBook(Book old, Book theBook)
    {
        return BookPersistence.updateBook();
    }

    /*******************************
     * get the size of database's order list size
     *****************************/
    public int getAllOrderSize()
    {
        return allOrder.size();
    }

    /****************
     * add a new order to the database's order list
     *********************/
    public void addOrder(Order newOrder)
    {
        allOrder.add(newOrder);
    }

    public List<Order> getAllOrder()
    {
        return allOrder;
    }
}
