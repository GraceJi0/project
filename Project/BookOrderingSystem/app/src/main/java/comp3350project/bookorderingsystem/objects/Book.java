package comp3350project.bookorderingsystem.objects;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class Book
{
    private String bookName;
    private String bookAuthor;
    private String bookInformation;
    private double bookPrice;
    private int numberInStock;
    private String category;
    private int imageID;

    public Book( String newName, String newBookAuthor, String newInformation, double newPrice,
                 String newCategory, int number,int imageID)
    {
            bookName = newName;
            bookAuthor = newBookAuthor;
            bookInformation = newInformation;
            bookPrice = newPrice;
            numberInStock = number;
            category = newCategory;
            this.imageID = imageID;
    }

    public String getName()
    {
        return bookName;
    }

    public void setName(String newName)
    {

        bookName = newName;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public void setBookAuthor(String newBookAuthor)
    {
        bookAuthor = newBookAuthor;
    }

    public String getBookInformation()
    {
        return bookInformation;
    }

    public void setBookInformation(String newBookInformation)
    {
        bookInformation = newBookInformation;
    }

    public double getBookPrice()
    {
        return bookPrice;
    }

    public void setBookPrice(double newBookPrice)
    {
        bookPrice = newBookPrice;
    }

    public int getNumberInStock()
    {
        return numberInStock;
    }

    public void setNumberInStock(int newInStock)
    {
        newInStock = newInStock;
    }

    public String getCategory()
    {
        return category;
    }

    public int compareName(Book newData)
    {
        return this.getName().compareTo(newData.getName());
    }

    public String toString()
    {
        return bookName + ", author: " + bookAuthor+", price: "+bookPrice;
    }

    public int getImageID(){return  imageID;}
}
