package com.epam.jwd.onlinetraining.dao.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * com.epam.jwd.onlinetraining.dao.api @FunctionalInterface
 * public interface StatementPreparator
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
@FunctionalInterface
public interface StatementPreparator {

    void accept(PreparedStatement t) throws SQLException;

}
