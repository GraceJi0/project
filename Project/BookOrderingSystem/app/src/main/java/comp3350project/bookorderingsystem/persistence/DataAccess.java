package comp3350project.bookorderingsystem.persistence;

import java.util.List;

import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.objects.Order;

public interface DataAccess   //shows the function of the DB
{
    void open(String string);   //turn on the connection to the database and do some preparation

    void close();    //close the DB

    List<Customer> getCustomerList();   //get all customers from the DB, form as a list

    boolean addToCart(Customer customer, Book book);   //add the "book" to the "customer"'s cart

    boolean deleteFromCart(Customer customer, Book book);   //delete the "book" from the cart of the "customer"

    boolean addToWishList(Customer customer, Book book);   //add the "book" to the "customer"'s wishlist

    boolean deleteFromWishList(Customer customer, Book book);  //delete the "book" from the wishlist of the "customer"

    List<Order> getOrder(Customer customer);

    boolean addCustomer(Customer newCustomer);   //add a new customer to the DB

    List<Book> getBookList();   //get all books from the DB, form as a list

    boolean addBook(Book newBook);   //add a new book to the DB

    boolean updateBook(Book old, Book theBook);  //old represents the object contains old data, theBook object contains the new data, update the old

    int getAllOrderSize();
    List<Order> getOrderList();
    boolean addOrder(Order order);
    boolean updateOrderState(Order order);
    boolean deleteFromCart(Order order);   //delete from cart once the order is made
}