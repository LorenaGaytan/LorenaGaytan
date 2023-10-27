package net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate;

import net.tecgurus.schoolmanager.dao.jdbc.ConnectionFactory;
import net.tecgurus.schoolmanager.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JdbcTemplate<T> {

    private static final Logger LOGGER = Logger.getLogger(JdbcTemplate.class.getName());
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public T queryForObject(final String query, PreparedStatementFunction psf, RowMapper<T> rowMapper) throws DAOException {
        List<T> response = this.queryForList(query, psf, rowMapper);
        return response.isEmpty() ? null : response.get(0);
    }

    public List<T> queryForList(final String query, RowMapper<T> rowMapper) throws DAOException {
        return this.queryForList(query, null, rowMapper);
    }

    public List<T> queryForList(final String query, PreparedStatementFunction psf, RowMapper<T> rowMapper) throws DAOException {

        List<T> objectList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionFactory.open();
            if (connection != null) {
                ps = connection.prepareStatement(query);
                if (psf != null) {
                    psf.execute(ps);
                }

                rs = ps.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        T t = rowMapper.execute(rs);
                        objectList.add(t);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException exception) {
            throw new DAOException("DAO Exception: " + exception.getMessage(), exception);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
            }
        }

        return objectList;
    }

    public void update(String query, PreparedStatementFunction psf) throws DAOException {
        this.update(query, psf, null);
    }

    public void update(String query, PreparedStatementFunction psf, KeyHolder keyHolder) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        try {
            connection = connectionFactory.open();
            if (connection != null) {
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                psf.execute(ps);
                ps.executeUpdate();
                if (keyHolder != null) {
                    generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        keyHolder.setKey(generatedKeys.getLong(1));
                    }
                }

            }
        } catch (ClassNotFoundException | SQLException exception) {
            throw new DAOException("DAO Exception: " + exception.getMessage(), exception);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
                if (generatedKeys != null) generatedKeys.close();
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }
}
