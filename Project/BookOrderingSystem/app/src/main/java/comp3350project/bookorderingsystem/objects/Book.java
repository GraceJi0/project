package comp3350project.bookorderingsystem.objects;

public class Book
{
    private String bookName;
    private String bookAuthor;
    private String bookInformation;
    private double bookPrice;
    private String category;
    private int numberInStock;
    private int imageID;

    public Book( String newName, String newBookAuthor, String newInformation, double newPrice,
                 String newCategory, int number, int imageID)
    {
            bookName = newName;
            bookAuthor = newBookAuthor;
            bookInformation = newInformation;
            bookPrice = newPrice;
            numberInStock = number;
            category = newCategory;
            this.imageID = imageID;
    }

    //getter and setter for the name
    public String getName()
    {
        return bookName;
    }
    public void setName(String newName)
    {
        bookName = newName;
    }

    //getter and setter for the author
    public String getBookAuthor()
    {
        return bookAuthor;
    }
    public void setBookAuthor(String newBookAuthor)
    {
        bookAuthor = newBookAuthor;
    }

    //getter and setter for the book information
    public String getBookInformation()
    {
        return bookInformation;
    }
    public void setBookInformation(String newBookInformation)
    {
        bookInformation = newBookInformation;
    }

    //getter and setter for the book price
    public double getBookPrice()
    {
        return bookPrice;
    }
    public void setBookPrice(double newBookPrice)
    {
        bookPrice = newBookPrice;
    }

    //getter and setter for number of books in stock
    public int getNumberInStock()
    {
        return numberInStock;
    }
    public void setNumberInStock(int newInStock)
    {
        numberInStock = newInStock;
    }

    //getter and setter for Category of the book
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String newCategory){category = newCategory;}

    //compare the name of the argument book and 'this' book
    public int compareName(Book newData)
    {
        return this.getName().compareTo(newData.getName());
    }

    //make all information of this book to String
    public String toString()
    {
        return bookName + ", author: " + bookAuthor+", price: "+bookPrice;
    }

    //get the image ID of the book (for displaying the image)
    public int getImageID(){return  imageID;}

    //check the number of 'this' book in stock
    public Boolean checkStock()
    {
        Boolean result;
        if(numberInStock>0)
        {
            result=true;
        }
        else
        {
            result=false;
        }
        return result;
    }

    //reduce the number of 'this' book in stock if it's been ordered by a customer
    public void reduceStock()
    {
        numberInStock=numberInStock-1;
    }
}
