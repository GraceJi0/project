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

    boolean addCustomer(Customer newCustomer);

    boolean deleteCustomer(Customer newCustomer);

    boolean addBook(Book newBook);

}
