package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T extends Entity<Integer>> {

    T extract(ResultSet resultSet) throws EntityExtractionFailedException;

    default List<T> extractAll(ResultSet resultSet) throws SQLException, EntityExtractionFailedException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }

}