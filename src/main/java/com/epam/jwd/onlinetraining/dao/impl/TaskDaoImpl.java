package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends CommonDao<Task> implements TaskDao {
    private static final String SQL_SAVE_TASK = "INSERT INTO task (course_id, description, task_number, task_answer, feedback) values (?,?,?,?,?)";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();


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
    public Boolean insert(Task entity) {
        return null;
    }



    @Override
    public Task findById(Long id) {
        return null;
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
    protected Task extractResult(ResultSet rs) throws SQLException, EntityExtractionFailedException {
        return null;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Task entity) throws SQLException {

    }

}
