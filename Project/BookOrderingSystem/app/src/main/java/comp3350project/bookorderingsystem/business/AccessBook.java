package comp3350project.bookorderingsystem.business;


import comp3350project.bookorderingsystem.persistence.DataAccessStub;

/**
 * Created by dinghanji on 2017-05-30.
 */

public class AccessBook {
    private DataAccessStub dataAccess;

    public AccessBook(DataAccessStub newDataAccess)
    {
        dataAccess = newDataAccess;
    }
}
