package comp3350project.bookorderingsystem.application;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;
import comp3350project.bookorderingsystem.persistence.DataAccess;
import comp3350project.bookorderingsystem.persistence.DataAccessObject;

/**
 * Created by dinghanji on 2017-05-30.
*/

public class Service
{
    private static DataAccessStub dataAccessService = null;
    //private static DataAccess dataAccessService = null;///////////////////////////////////////

    public static DataAccessStub createDataAccess(String dbName)
    //public static DataAccess createDataAccess(String dbName)//////////////////////////////////////////////
    {
        if(dataAccessService == null)
        {
            dataAccessService = new DataAccessStub(dbName);
            //dataAccessService = new DataAccessObject(dbName);/////////////////////////////
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccessStub createDataAccess(DataAccessStub alternateDataAccessService)
    //public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }      //!!!!!!don't delete it! this is for the testing

    //public static DataAccessStub getDataAccess(String dbName)
    public static DataAccess getDataAccess(String dbName)///////////////////////////////////////////
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
