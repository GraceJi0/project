package comp3350project.bookorderingsystem.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;

public interface DataAccess
{
	void open(String string);

	void close();

    ArrayList<Customer> getCustomerList();

    boolean addCustomer(Customer newCustomer);

    boolean updateCustomer(Customer theCustomer);

    boolean deleteCustomer(Customer theCustomer);

    ArrayList<Book> getBookList();

    boolean addBook(Book newBook);

    boolean updateBook(Book theOld,Book theBook);

    boolean deleteBook(Book theBook);

}
