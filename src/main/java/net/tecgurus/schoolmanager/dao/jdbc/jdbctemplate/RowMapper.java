package net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
    T execute(ResultSet rs) throws SQLException;
}
