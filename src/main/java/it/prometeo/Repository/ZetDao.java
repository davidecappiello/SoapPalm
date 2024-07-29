package it.prometeo.Repository;

import it.prometeo.Entity.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class ZetDao {

    @Autowired
    private DataSource dataSource;

    public void findSPMForZET(String placer_group_number){

        MessageEvent messageEvent = new MessageEvent();

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("Select * " +
                    "FROM PRO_MESSAGE_EVENT " +
                    "WHERE PLACER_GROUP_NUMBER = ? ");
            statement.setString(1, placer_group_number);
            ResultSet results = statement.executeQuery();

            //messageEvent.getId(results.getInt("ID"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }




}
