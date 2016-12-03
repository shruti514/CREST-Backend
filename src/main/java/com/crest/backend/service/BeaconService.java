package com.crest.backend.service;

import com.crest.backend.com.crest.backend.dao.DatabaseConnection;
import com.crest.backend.model.CrestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.crest.backend.com.crest.backend.dao.FieldNamesConstant.BUS_STOP;
import static com.crest.backend.com.crest.backend.dao.TableNameConstants.BEACON_REGISTRY;


public class BeaconService {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    CrestResponse crestResponse = new CrestResponse();

    public CrestResponse getStationFromBeaconId(String id) {
        Connection connection = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        PreparedStatement preparedStatement = null;
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement("select"+ BUS_STOP+" from "+ BEACON_REGISTRY+" where BEACON_ID = ?;");
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Ok");
                crestResponse.setBusNumber(result);
            }
        } catch (Exception e) {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
            log.error("Exception at getUserIdFromSessionToken", e);
        } finally {
            dbConnection.closePreparedStatement(preparedStatement);
            dbConnection.closeConnection(connection);
        }
        return crestResponse;
    }


    public CrestResponse getStationFromBeaconId(String busStopId1, String busStopId2) throws Exception {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();
        String result = "";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection
                    .prepareStatement("select NAVIGATION_INFO from INTERNAL_NAVIGATION where SOURCE_BUS_STOP_ID = ? and DESTINATION_BUS_STOP_ID = ? ;");
            p.setString(1, busStopId1);
            p.setString(2, busStopId2);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                crestResponse.setStatusCode("200");
                crestResponse.setStatusDescripton("Ok");
                crestResponse.setRouteDescription(result);
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

    public CrestResponse getUserLocation(String userId, String busNumber, String tripStatus) throws Exception {
        Connection connection = null;
        CrestResponse crestResponse = new CrestResponse();
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
            connection = dbConnection.getConnection();
            PreparedStatement p = connection
                    .prepareStatement("INSERT INTO USER_LOCATION VALUES (?,?,?) ;");
            p.setString(1, userId);
            p.setString(2, busNumber);
            p.setString(3, tripStatus);
            p.executeUpdate();
            crestResponse.setStatusCode("200");
            crestResponse.setStatusDescripton("Ok");

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
