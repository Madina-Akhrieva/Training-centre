package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends CommonDao<Task> {

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Boolean delete(Long id) {
        return null;
    }


    @Override
    public List<Task> read() {
        return null;
    }

    @Override
    public Optional<Task> read(Long id) {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected List<String> getFields() {
        return null;
    }

    @Override
    protected String getIdFieldName() {
        return null;
    }

    @Override
    protected Task extractResult(ResultSet rs){ return null; }

    @Override
    protected void fillEntity(PreparedStatement statement, Task entity){

    }

}
