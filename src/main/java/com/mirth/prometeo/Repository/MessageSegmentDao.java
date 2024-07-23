package com.mirth.prometeo.Repository;

import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Entity.MessageSegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSegmentDao {
    @Autowired
    private DataSource dataSource;

    public List<MessageSegment> findMessageSegment(MessageEvent messageEvent) {

        List<MessageSegment> messageSegmentList = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("Select * " +
                    "FROM PRO_MESSAGE_SEGMENT " +
                    "WHERE MESSAGE_EVENT_ID = ? ");
            statement.setInt(1, messageEvent.getId());
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                MessageSegment messageSegment = new MessageSegment();
                messageSegment.setId(results.getInt("ID"));
                messageSegment.setCode(results.getString("CODE"));
                messageSegment.setBody(results.getString("BODY"));
                messageSegment.setMessageEventId(messageEvent);

                messageSegmentList.add(messageSegment);
            }
            return messageSegmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
