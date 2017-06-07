package comp3350project.bookorderingsystem.application;

/**
 * Created by dinghanji on 2017-05-26.
 */

public class Main
{
    public static final String dbName = "BOS";

    public static void main(String[] args)
    {
        startUp();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        Service.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Service.closeDataAccess();
    }
}
