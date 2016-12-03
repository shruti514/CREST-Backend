package com.crest.backend.service;

import com.crest.backend.com.crest.backend.dao.DatabaseConnection;
import com.crest.backend.model.CrestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.crest.backend.com.crest.backend.dao.TableNameConstants.USER;

/**
 * Created by Arun on 11/29/16.
 */
@Service
public class UserService {
    protected final Logger log = LoggerFactory.getLogger(getClass());




    public CrestResponse userLogin(String userName, String password) {
        CrestResponse crestResponse = new CrestResponse();
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection.prepareStatement("select ID  as ID from " + USER + " where email_id = ? and password= ? ;");
            p.setString(1, userName);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                result = rs.getString("ID");
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Ok");
                crestResponse.setUserId(result);
            }
        } catch (Exception e) {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
            log.error("Exception at getUserIdFromSessionToken", e);


        } finally {
            dbConnection.closeConnection(connection);
        }
        return crestResponse;
    }


    public CrestResponse careGiverRegister(String firstName, String lastName, String age, String address, String contactNumber, String userName, String password) {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM USERS WHERE email_id =?");
            preparedStatement.setString(1, userName);
            ResultSet r = preparedStatement.executeQuery();
            Integer alreadyPresent = 0;
            while (r.next()) {
                alreadyPresent = r.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                PreparedStatement p = connection.prepareStatement("INSERT INTO CAREGIVER VALUES (?,?,?,?,?,?,?)");
                p.setString(1, firstName);
                p.setString(2, lastName);
                p.setString(3, contactNumber);
                p.setString(4, age);
                p.setString(5, address);
                p.setString(6, userName);
                p.setString(7, password);
                p.executeUpdate();
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton(result);

            } else {
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("User already exists");
            }
        } catch (Exception e) {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
            log.error("Exception at getUserIdFromSessionToken", e);


        } finally {
            dbConnection.closeConnection(connection);
        }
        return crestResponse;
    }

    public CrestResponse userRegister(String firstName, String lastName, String contactNumber, String age, String address, String emergencyContact,
                                      String careGiverEmail, String additionalInfo, String userName, String password) {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM USERS WHERE email_id =?");
            preparedStatement.setString(1, userName);
            ResultSet r = preparedStatement.executeQuery();
            Integer alreadyPresent = 0;
            while (r.next()) {
                alreadyPresent = r.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                PreparedStatement p = connection.prepareStatement("INSERT INTO DEPENDANTS VALUES (?,?,?,?,?,?,?,?,?,?)");
                p.setString(1, firstName);
                p.setString(2, lastName);
                p.setString(3, contactNumber);
                p.setString(4, age);
                p.setString(5, address);
                p.setString(6, emergencyContact);
                p.setString(7, careGiverEmail);
                p.setString(8, additionalInfo);
                p.setString(9, userName);
                p.setString(10, password);
                p.executeUpdate();
                    crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Successful registration");
            } else {
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("UserName already exists");
            }
        } catch (Exception e) {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
            log.error("Exception at getUserIdFromSessionToken", e);


        } finally {
            dbConnection.closeConnection(connection);
        }
        return crestResponse;
    }

    public CrestResponse scheduleTrip(String userNameRider, String userNameScheduler, String tripStartTime, String tripDate, String source, String destination) {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";


        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM TRIP WHERE rider_id= ? AND scheduler_id = ? AND trip_start_time = ? AND trip_start_date = ? AND source_location = ? AND destination_location = ? ;  ");
            p.setString(1, userNameRider);
            p.setString(2, userNameScheduler);
            p.setString(3, tripStartTime);
            p.setString(4, tripDate);
            p.setString(5, source);
            p.setString(6, destination);

            ResultSet rs = p.executeQuery();
            Integer alreadyPresent = 0;
            while ((rs.next())) {
                alreadyPresent = rs.getInt(1);
                System.out.println(alreadyPresent);

            }
            if (alreadyPresent <= 0) {
                p = connection.prepareStatement("INSERT INTO TRIP (rider_id,scheduler_id,trip_start_time,trip_start_date,destination_location,source_location) VALUES(?,?,?,?,?,?) ;");

                p.setString(1, userNameRider);
                p.setString(2, userNameScheduler);
                p.setString(3, tripStartTime);
                p.setString(4, tripDate);
                p.setString(5, destination);
                p.setString(6, source);

                p.executeUpdate();

                p = connection.prepareStatement("Select trip_id from TRIP where rider_id= ? AND scheduler_id = ? AND trip_start_time = ? AND trip_start_date = ? AND source_location = ?  AND destination_location = ? ;  ");
                p.setString(1, userNameRider);
                p.setString(2, userNameScheduler);
                p.setString(3, tripStartTime);
                p.setString(4, tripDate);
                p.setString(5, source);
                p.setString(6, destination);
                ResultSet resultSet = p.executeQuery();
                while (resultSet.next()) {
                    String tripId = resultSet.getString(1);
                    crestResponse.setStatusCode("200");
                    crestResponse.setTripId(tripId);
                }

            } else {
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Trip already exists");
            }

        } catch (Exception e) {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
            log.error("Exception at getUserIdFromSessionToken", e);


        } finally {
            dbConnection.closeConnection(connection);
        }
        return crestResponse;
    }


}
