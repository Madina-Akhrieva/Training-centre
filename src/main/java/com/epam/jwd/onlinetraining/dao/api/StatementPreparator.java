package com.epam.jwd.onlinetraining.dao.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementPreparator {

    void accept(PreparedStatement statement) throws SQLException;

}
