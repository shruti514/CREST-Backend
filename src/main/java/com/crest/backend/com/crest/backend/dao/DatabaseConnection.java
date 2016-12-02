package com.crest.backend.com.crest.backend.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String USER = "cense";
    private final static String PASSWORD = "MyNewPass4!";
    private final static String URL = "jdbc:mysql://ec2-54-159-5-230.compute-1.amazonaws.com:3306/CENSE?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("SQL Exception at closePreparedStatement", e);
            }
        }
    }

}
