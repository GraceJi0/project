package comp3350project.bookorderingsystem.application;

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

    /*******************************
    start up a database
     ******************************/
    public static void startUp()
    {
        Service.createDataAccess(dbName);
    }

    /*******************************
     shut down a database
     ******************************/
    public static void shutDown()
    {
        Service.closeDataAccess();
    }

    /*******************************
     get database path name
     ******************************/
    public static String getDBPathName()
    {
        if(dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    /*******************************
     set database path name
     ******************************/
    public static void setDBPathName(String pathName)
    {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
