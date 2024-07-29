package it.prometeo.Repository;

import it.prometeo.Entity.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class MessageEventDao {

    private final DataSource dataSource;

    public MessageEventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String findMessageEventPlacer(String accessNumber) {

        String result = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT " +
                    "HOSTORDERNUMBER " +
                    "FROM SP_REQUESTS " +
                    "WHERE SP_ACCESSNUMBER = ?");
            statement.setString(1, accessNumber);
            ResultSet results = statement.executeQuery();


            if (!results.next())
                return result;
            else {
                result = results.getString("HOSTORDERNUMBER");
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public MessageEvent findMessageEventFiller(String filler_group_number) {

        MessageEvent messageEvent = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("Select * " +
                    "FROM PRO_MESSAGE_EVENT " +
                    "WHERE FILLER_ORDER_NUMBER = ? " +
                    "AND CODE = ?");
            statement.setString(1, filler_group_number);
            statement.setString(2, "OML_O21");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                messageEvent = new MessageEvent();
                messageEvent.setId(results.getInt("ID"));
                messageEvent.setCode(results.getString("CODE"));
                messageEvent.setSource(results.getString("SOURCE"));
                messageEvent.setPlacerGroupNumber(results.getString("PLACER_GROUP_NUMBER"));
                messageEvent.setFillerOrderNumber(results.getString("FILLER_ORDER_NUMBER"));
                messageEvent.setStatus(results.getString("STATUS"));
                messageEvent.setCreationDate(results.getTimestamp("CREATION_DATE").toLocalDateTime());
                messageEvent.setUpdateDate(results.getTimestamp("UPDATE_DATE").toLocalDateTime());
            }
            return messageEvent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MessageEvent findMessageEventPlacerFromOML(String placer_group_number) {

        MessageEvent messageEvent = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("Select * " +
                    "FROM PRO_MESSAGE_EVENT " +
                    "WHERE PLACER_GROUP_NUMBER = ? " +
                    "AND CODE = ?");
            statement.setString(1, placer_group_number);
            statement.setString(2, "OML_O21");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                messageEvent = new MessageEvent();
                messageEvent.setId(results.getInt("ID"));
                messageEvent.setCode(results.getString("CODE"));
                messageEvent.setSource(results.getString("SOURCE"));
                messageEvent.setPlacerGroupNumber(results.getString("PLACER_GROUP_NUMBER"));
                messageEvent.setFillerOrderNumber(results.getString("FILLER_ORDER_NUMBER"));
                messageEvent.setStatus(results.getString("STATUS"));
                messageEvent.setCreationDate(results.getTimestamp("CREATION_DATE").toLocalDateTime());
                messageEvent.setUpdateDate(results.getTimestamp("UPDATE_DATE").toLocalDateTime());
            }
            return messageEvent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MessageEvent findMessageEventPlacerFromORL(String placer_group_number) {

        MessageEvent messageEvent = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("Select * " +
                    "FROM PRO_MESSAGE_EVENT " +
                    "WHERE PLACER_GROUP_NUMBER = ? " +
                    "AND CODE = ?");
            statement.setString(1, placer_group_number);
            statement.setString(2, "ORL_O22");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                messageEvent = new MessageEvent();
                messageEvent.setId(results.getInt("ID"));
                messageEvent.setCode(results.getString("CODE"));
                messageEvent.setSource(results.getString("SOURCE"));
                messageEvent.setPlacerGroupNumber(results.getString("PLACER_GROUP_NUMBER"));
                messageEvent.setFillerOrderNumber(results.getString("FILLER_ORDER_NUMBER"));
                messageEvent.setStatus(results.getString("STATUS"));
                messageEvent.setCreationDate(results.getTimestamp("CREATION_DATE").toLocalDateTime());
                messageEvent.setUpdateDate(results.getTimestamp("UPDATE_DATE").toLocalDateTime());
            }
            return messageEvent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}