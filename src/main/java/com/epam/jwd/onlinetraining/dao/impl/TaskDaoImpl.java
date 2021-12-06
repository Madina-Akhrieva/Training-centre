package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.db.LockingConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends CommonDao<Task> {


    protected TaskDaoImpl(ConnectionPool pool) {
        super(pool);
    }

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
    protected Task extractResult(ResultSet rs) {
        return null;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Task entity) {

    }

}
