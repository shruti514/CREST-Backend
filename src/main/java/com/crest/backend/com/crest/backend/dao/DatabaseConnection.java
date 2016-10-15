package com.crest.backend.com.crest.backend.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Arun on 10/13/16.
 */

public class DatabaseConnection {
    private final static String USER = "root";
    private final static String PASSWORD = "anhadbhasin";
    private final static String URL = "jdbc:mysql://localhost:3306/crust?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //private final static String URL="jdbc:oracle:thin:@rdsinstance.amazonaws.com :3306:RDSDB02";
    private final static String DRIVER = "com.mysql.jdbc.Driver";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Static method that returns the instance for the singleton
     *
     * @return {Connection} connection
     **/
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException at getConnection", e);
        } catch (SQLException e) {
            log.error("SQL Exception at getConnection", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("SQL Exception at closeConnection", e);

            }
        }
    }

}
