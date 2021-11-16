package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.Dao;
import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.sql.*;
import java.util.List;

public class TaskDaoImpl implements Dao<Task, Integer>, TaskDao<Task, Integer> {
    private static final String SQL_SAVE_TASK = "INSERT INTO task (course_id, description, task_number, task_answer, feedback) values (?,?,?,?,?)";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Task save(Task task) {
        try (Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_TASK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, task.getCourseId());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getTaskNumber());
            preparedStatement.setString(4, task.getTaskAnswer());
            preparedStatement.setString(5, task.getFeedback());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    task.setId(id);
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return task;
    }

    @Override
    public Boolean update(Task entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
