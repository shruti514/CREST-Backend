package com.crest.backend.com.crest.backend.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Arun on 10/13/16.
 */
@Component
public class DatabaseConnection {
    private final static String USER = "root";
    private final static String PASSWORD = "anhadbhasin";
    private final static String URL="URL FOR THE MYSQL IN EC2";
    //private final static String URL="jdbc:oracle:thin:@rdsdb-inst02.clezv3xo9unb.us-west-1.rds.amazonaws.com :3306:RDSDB02";
    private final static String DRIVER = "oracle.jdbc.OracleDriver";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Static method that returns the instance for the singleton
     *
     * @return {Connection} connection
     **/
    public  Connection getConnection() {
        Connection connection=null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException at getConnection",e);
        }catch (SQLException e) {
            log.error("SQL Exception at getConnection",e);
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("SQL Exception at closeConnection",e);

            }
        }
    }

}
