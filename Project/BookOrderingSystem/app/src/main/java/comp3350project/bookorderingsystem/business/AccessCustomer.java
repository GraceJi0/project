package comp3350project.bookorderingsystem.business;

import java.util.List;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.application.Service;
import comp3350project.bookorderingsystem.objects.Book;
import comp3350project.bookorderingsystem.objects.Customer;
import comp3350project.bookorderingsystem.persistence.DataAccess;

public class AccessCustomer
{
    private DataAccess dataAccess;

    public AccessCustomer()
    {
        dataAccess = (DataAccess) Service.getDataAccess(Main.dbName);
    }

    public boolean addCustomer(Customer newCustomer)
    {
        return dataAccess.addCustomer(newCustomer);
    }

    public List<Customer> getCustomerList(){return dataAccess.getCustomerList();}


    public boolean checkAccount(String account)
    {
        boolean result=false;
        List<Customer> customerList = getCustomerList();
        for(int i=0;i<customerList.size();i++)
        {
            if(customerList.get(i).getName().compareTo(account)==0)
            {
                result=true;
            }
        }
        return result;
    }

    public String[] getCustomerAccount()
    {
        List<Customer> customerList = getCustomerList();
        String[] allCustomerAccount = new String[customerList.size()];
        for(int i = 0; i < customerList.size(); i++)
        {
            allCustomerAccount[i] = customerList.get(i).getName();
        }
        return  allCustomerAccount;
    }

    public void addToCart(String customerName, Book book)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                customer.addToCart(book);
                dataAccess.addToCart(customer, book); //update the database
                break;
            }
            else
            {
                index++;
            }
        }
    }

    public void deleteFromCart(String customerName, Book book)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                customer.deleteFromCart(book);
                dataAccess.deleteFromCart(customer, book);
                break;
            }
            else
            {
                index++;
            }
        }
    }

    public void deleteFromWishList(String customerName, Book book)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                customer.deleteFromWishList(book);
                dataAccess.deleteFromWishList(customer, book);
                break;
            }
            else
            {
                index++;
            }
        }
    }

    public void addToWishList(String customerName, Book book)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                customer.addToWishList(book);
                dataAccess.addToWishList(customer, book); //update the database
                break;
            }
            else
            {
                index++;
            }
        }
    }

    public List<Book> getCustomerCart(String customerName)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                break;
            }
            else
            {
                index++;
            }
        }
        return customer.getCart();
    }

    public List<Book> getCustomerWishList(String customerName)
    {
        List<Customer> customerList = getCustomerList();
        int index = 0;
        Customer customer = null;
        while(index < customerList.size())
        {
            customer = customerList.get(index);
            if(customer.getName().equals(customerName))
            {
                break;
            }
            else
            {
                index++;
            }
        }
        return customer.getWishList();
    }

    public int verifyCustomer(String accountName,String accountPassword)
    {
        int verify = -1;
        Customer customer = null;
        List<Customer> customers = getCustomerList();
        int index = 0;
       while(index < customers.size())
        {
            customer = customers.get(index);
            if(customer.getName().equals(accountName))
            {
                verify = 0;
                if(customer.getPassword().equals(accountPassword))
                {
                    verify = 1;
                    break;
                }
            }
            index++;
        }
        return verify;
    }
}
