package comp3350project.bookorderingsystem.objects;

public class Picture{
    int pid;
    int picture;

    public Picture(int pid, int picture)
    {
        this.pid = pid; //the picture id recorded in the Book object
        this.picture = picture;  //the picture
    }

    public int getPID()// return the picture id
    {
        return pid;
    }

    public int getPicture()  ///return the picture
    {
        return picture;
    }

    public void setPicture(int picture)   //set a picture for a book
    {
        this.picture = picture;
    }
}