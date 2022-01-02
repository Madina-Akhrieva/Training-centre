package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends CommonDao<Task> implements TaskDao {
    private static final String ID_TASK_COLUMN_NAME = "id_task";
    private static final Logger LOGGER = LogManager.getLogger(TaskDaoImpl.class);
    private static final String SQL_DELETE_COURSE = "DELETE FROM task WHERE  id=?";
    private static final String INSERT_TASK_TO_COURSE = "INSERT INTO task( title, description, course_id) values(?,?, ?) ";
    private static final String SUBSCRIPTION_TABLE_NAME = "subscription  join course c on c.id = subscription.course_id inner join task t on t.course_id = subscription.course_id inner join course_user cu on  cu.id = subscription.course_user_id";
    private static final String SELECT_TITLE_DESCRIPTION_FROM_SUBSCRIPTION_WHERE_ID = "select id_task, c.title, c.description from" + " " + SUBSCRIPTION_TABLE_NAME + " " + " where t.course_id=? and course_user_id=?";

    private static final String SELECT_TITLE_DESCRIPTION_FROM_TASK_WHERE_ID = "select id_task, title, description from task t where t.course_id = ?";
    private static final String ID_FIELD_NAME = "task_id";
    private static final String TITLE_FIELD_NAME = "title";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    private static final String TITLE_COLUMN_NAME = "title";
    private static final String DESCRIPTION_COLUMN_NAME = "description";
    private static final String UPDATE_COURSE_WHERE_TITLE = "update course set title = ?, learning_language = ?, description = ? where title = ?";
    private static final String DELETE_COURSE_WHERE_ID = "delete from course where id=?";
    public static final String TASK_TABLE_NAME = "task";
    private static final String FIELD_NAME = "field name";
    private static final String INNER_LOIN_SELECT_FROM_COURSE_AND_COURSE_USER = "select cu.id, first_name, last_name, c.id, title from subscription " +
            "inner join course c on c.id = subscription.course_id\n" +
            "inner join course_user cu on  cu.id = subscription.course_user_id where course_user_id = ?";
    private static final List<String> FIELDS = Arrays.asList(
            "id", "title", "description"
    );
    private static final String UPDATE_SUBSCRIPTION_TABLE = "UPDATE" + " " + SUBSCRIPTION_TABLE_NAME + " " + "SET task_answer=? where course_user_id=? and c.id=? and id_task=?";
    private static final String ADD_ANSWER_SQL_EXPRESSION = "insert into answer(task_id, answer, course_user_id) values (?, ?, ?)";

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
        return TASK_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return FIELD_NAME;
    }

    @Override
    protected Task extractResult(ResultSet rs) {
        return null;
    }


    public static TaskDao getInstance() {
        return TaskDaoImpl.Holder.INSTANCE;
    }

    @Override
    public List<Task> findByTitle(String title) {
        return null;
    }


    @Override
    public List<Task> findTasksByCourseId(long id) {
        List<Task> tasks = new ArrayList<>();
        Task task = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SELECT_TITLE_DESCRIPTION_FROM_TASK_WHERE_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long taskId = resultSet.getLong(ID_TASK_COLUMN_NAME);
                String title = resultSet.getString(TITLE_COLUMN_NAME);
                String description = resultSet.getString(DESCRIPTION_COLUMN_NAME);
                tasks.add(new Task(taskId, id, title, description, null, null));
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return tasks;

    }


    @Override
    public Optional<Task> addTaskToCourse(Task task, long courseId) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_TO_COURSE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet;
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setLong(3, courseId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                task.setId(resultSet.getLong(1));
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.of(task);
    }

    @Override
    public boolean addTaskToAnswer(String answer, long courseUserId, long courseId, long idTask) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ANSWER_SQL_EXPRESSION)) {
            preparedStatement.setLong(1, idTask);
            preparedStatement.setString(2, answer);
            preparedStatement.setLong(3, courseUserId);
            boolean rowUpdated = preparedStatement.executeUpdate() > 0;
            return rowUpdated;

        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Task> readAllTasksByCourseIdAndUserId(long courseId, long userId) {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SELECT_TITLE_DESCRIPTION_FROM_SUBSCRIPTION_WHERE_ID)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long taskId = resultSet.getLong(ID_TASK_COLUMN_NAME);
                String title = resultSet.getString(TITLE_COLUMN_NAME);
                String description = resultSet.getString(DESCRIPTION_COLUMN_NAME);
                tasks.add(new Task(taskId, courseId, title, description, null, null));
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return tasks;
    }

    private static class Holder {
        public static final TaskDao INSTANCE = new TaskDaoImpl(ConnectionPool.instance());
    }

}
