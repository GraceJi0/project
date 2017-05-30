package com.example.dinghanji.a3350_iteration1.application.business;

import com.example.dinghanji.a3350_iteration1.application.persistence.DataAccessStub;

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
