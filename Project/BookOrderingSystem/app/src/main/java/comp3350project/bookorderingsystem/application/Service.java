package comp3350project.bookorderingsystem.application;

import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-30.
*/

public class Service {
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName)
    {
        if(dataAccessService == null)
        {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.dbName);
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess(String dbName)
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
