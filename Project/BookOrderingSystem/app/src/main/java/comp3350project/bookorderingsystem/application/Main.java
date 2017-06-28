package comp3350project.bookorderingsystem.application;

/**
 * Created by dinghanji on 2017-05-26.
 */

public class Main
{
    public static final String dbName = "BOS";
    private static String dbPathName = "database/BOS";

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

    public static String getDBPathName()
    {
        if(dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName)
    {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
