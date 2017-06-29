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


    /*******************************************************
     check if the account exit in the database
     ********************************************************/
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

    /*******************************************************
     get all customer's account name and put them in to an array
     ********************************************************/
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


    /*******************************************************
     get customer's cart
     ********************************************************/
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

    /*******************************************************
     add a book to customer's cart
     ********************************************************/
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

    /*******************************************************
     delete a book from customer's cart
     ********************************************************/
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

    /*******************************************************
     get customer's wish list
     ********************************************************/
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

    /*******************************************************
     add a book to customer's wish list
     ********************************************************/
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

    /*******************************************************
     delete a book to customer's wish list
     ********************************************************/
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

    /*******************************************************
     check the user's account information when sign in
     ********************************************************/
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
