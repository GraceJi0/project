package comp3350project.bookorderingsystem.application;

import comp3350project.bookorderingsystem.persistence.DataAccess;
//import comp3350project.bookorderingsystem.persistence.DataAccessObject;
import comp3350project.bookorderingsystem.persistence.DataAccessStub;

public class Service
{
    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName)
    {
        if(dataAccessService == null)
        {
            //dataAccessService = new DataAccessObject(dbName);
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }      //!!!!!!don't delete it! this is for the testing

    public static DataAccess getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
