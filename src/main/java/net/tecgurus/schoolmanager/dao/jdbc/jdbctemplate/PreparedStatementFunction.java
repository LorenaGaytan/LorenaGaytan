package net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementFunction {
    void execute(PreparedStatement ps) throws SQLException;
}
