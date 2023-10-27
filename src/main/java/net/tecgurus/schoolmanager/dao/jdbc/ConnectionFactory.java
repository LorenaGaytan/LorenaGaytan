package net.tecgurus.schoolmanager.dao.jdbc;

import net.tecgurus.schoolmanager.utils.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public Connection open() throws SQLException, ClassNotFoundException {

        Properties props = PropertiesReader.readPropertiesFile("app.properties");

        String driver = props.getProperty("jdbc.DriverClassName");
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.userName");
        String password = props.getProperty("jdbc.password");

        Class.forName(driver);

        return DriverManager.getConnection(url, user, password);
    }
}
