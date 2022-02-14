package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * com.epam.jwd.onlinetraining.dao.api @FunctionalInterface
 * public interface ResultSetExtractor<T>
 *
 * @param <T>
 * @author Madina Akhrieva
 * @version 1.0
 */
@FunctionalInterface
public interface ResultSetExtractor<T> {

    T extract(ResultSet resultSet) throws EntityExtractionFailedException, SQLException;

    default List<T> extractAll(ResultSet resultSet) throws SQLException, EntityExtractionFailedException {

        List<T> entities = new ArrayList<>();

        while (resultSet.next()) {

            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }

}