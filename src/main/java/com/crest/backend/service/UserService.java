package com.crest.backend.service;

import com.crest.backend.com.crest.backend.dao.DatabaseConnection;
import com.crest.backend.model.CrestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

import static com.crest.backend.com.crest.backend.dao.TableNameConstants.CAREGIVER;
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


    private int saveUser(String emailId, String password, String role) throws SQLException {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO USERS (email_id,password,role) VALUES(?,?,?)");
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

            return getUserIdByUserEmail(emailId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return 0;
    }


    public int getUserIdByUserEmail(String emailId) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        int userIdToReturn=0;
        try {
            String query = "SELECT * FROM USERS where email_id=?";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;

            while( resultSet.next() ) {
                String id = resultSet.getString(1);
                String email = resultSet.getString(2);
                String role = resultSet.getString(4);
                String output = "User #%d: %s - %s - %s";
                System.out.println(String.format(output, ++count, id, email, role));

                userIdToReturn = Integer.parseInt(id);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdToReturn;
    }

    public CrestResponse careGiverRegister(String firstName, String lastName, String age, String address, String contactNumber, String emailId, String password) {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS COUNT FROM USERS WHERE email_id =?");
            preparedStatement.setString(1, emailId);
            ResultSet r = preparedStatement.executeQuery();
            Integer alreadyPresent = 0;
            while (r.next()) {
                alreadyPresent = r.getInt("COUNT");
            }
            if (alreadyPresent <= 0) {
                int userId = saveUser(emailId, password, CAREGIVER);

                PreparedStatement p = connection.prepareStatement("INSERT INTO CAREGIVER VALUES (?,?,?,?,?,?,?)");
                p.setString(1, Integer.toString(userId));
                p.setString(2, firstName);
                p.setString(3, lastName);
                p.setString(4, address);
                p.setString(5, contactNumber);
                p.setString(6, age);
                p.setString(7, emailId);
                p.executeUpdate();
                crestResponse.setStatusCode("200");
                crestResponse.setUserId(Integer.toString(userId));
                crestResponse.setStatusDescripton("User id contains a user id of a newly created user.");

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


    private enum Role{
        CAREGIVER, DEPENDANT
    }

}
