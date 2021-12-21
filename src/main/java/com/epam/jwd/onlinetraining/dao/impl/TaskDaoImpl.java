package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends CommonDao<Task> implements TaskDao{


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
    public Task create(Task entity) {
        return null;
    }

    @Override
    public boolean update(Task entity, String param) {
        return false;
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

    public static TaskDao getInstance() {
        return TaskDaoImpl.Holder.INSTANCE;
    }

    @Override
    public List<Task> findByTitle(String title) {
        return null;
    }

    private static class Holder {
        public static final TaskDao INSTANCE = new TaskDaoImpl(ConnectionPool.instance());
    }

}
