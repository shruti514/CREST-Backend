package com.crest.backend.service;

import com.crest.backend.com.crest.backend.dao.DatabaseConnection;
import com.crest.backend.model.CrestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Arun on 11/29/16.
 */
@Service
public class UserService {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    CrestResponse crestResponse = new CrestResponse();

    public CrestResponse careGiverLogin(String userName, String password) {
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection.prepareStatement("select sessionToken from careGiver where userName = ? and password= ? ;");
            p.setString(1, userName);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Ok");
                crestResponse.setSessionToken(result);
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

    public CrestResponse userLogin(String userName, String password) {
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection.prepareStatement("select sessionToken from user where userName = ? and password= ? ;");
            p.setString(1, userName);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Ok");
                crestResponse.setSessionToken(result);
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

    public CrestResponse careGiverRegister(String firstName, String lastName, String age, String address, String contactNumber, String drivingLicense, String userName, String password) {
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        String sessionToken = firstName + lastName;
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM CAREGIVER WHERE USERNAME =?");
            preparedStatement.setString(1, userName);
            ResultSet r = preparedStatement.executeQuery();
            Integer alreadyPresent = 0;
            while (r.next()) {
                alreadyPresent = r.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                PreparedStatement p = connection.prepareStatement("INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?)");
                p.setString(1, firstName);
                p.setString(2, lastName);
                p.setString(3, contactNumber);
                p.setString(4, age);
                p.setString(5, address);
                p.setString(6, drivingLicense);
                p.setString(7, userName);
                p.setString(8, password);
                p.setString(9, sessionToken);
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    result = rs.getString(1);
                    crestResponse.setStatusCode("200");
                    crestResponse.setStatusDescripton("Ok");
                    crestResponse.setSessionToken(result);
                }
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
                                      String medicalCondition, String medication, String additionalInfo, String userName, String password) {
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        String sessionToken = firstName + lastName;
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM USER WHERE USERNAME =?");
            preparedStatement.setString(1, userName);
            ResultSet r = preparedStatement.executeQuery();
            Integer alreadyPresent = 0;
            while (r.next()) {
                alreadyPresent = r.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                PreparedStatement p = connection.prepareStatement("INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                p.setString(1, firstName);
                p.setString(2, lastName);
                p.setString(3, contactNumber);
                p.setString(4, age);
                p.setString(5, address);
                p.setString(6, emergencyContact);
                p.setString(7, medicalCondition);
                p.setString(8, medication);
                p.setString(9, additionalInfo);
                p.setString(10, userName);
                p.setString(11, password);
                p.setString(12, sessionToken);
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    result = rs.getString(1);
                    crestResponse.setStatusCode("200");
                    crestResponse.setStatusDescripton("Ok");
                    crestResponse.setSessionToken(result);
                }
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
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        String tripId = userNameRider + userNameScheduler + tripStartTime + tripDate;
        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM TRIP WHERE USERNAMERIDER= ? , USERNAMERIDER = ? TRIPSTARTTIME = ?, TRIPDATE = ?, SOURCE =? AND DESTINATION =? ; ");
            p.setString(1, userNameRider);
            p.setString(2, userNameScheduler);
            p.setString(3, tripStartTime);
            p.setString(4, tripDate);
            p.setString(5, source);
            p.setString(6, destination);
            ResultSet rs = p.executeQuery();
            Integer alreadyPresent = 0;
            while ((rs.next())) {
                alreadyPresent = rs.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TRIP VALUES(?,?,?,?,?,?,?) ;");
                preparedStatement.setString(1, userNameRider);
                preparedStatement.setString(2, userNameScheduler);
                preparedStatement.setString(3, tripStartTime);
                preparedStatement.setString(4, tripDate);
                preparedStatement.setString(5, source);
                preparedStatement.setString(6, destination);
                preparedStatement.setString(7, tripId);
                ResultSet r = preparedStatement.executeQuery();
                while (r.next()) {
                    crestResponse.setStatusCode("200");
                    crestResponse.setStatusDescripton(tripId);

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
