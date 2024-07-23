package com.mirth.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Entity.MessageSegment;
import com.mirth.prometeo.Entity.PRO_TDQ2HL7;
import com.mirth.prometeo.HL7Palm.Message.OMLO21;
import com.mirth.prometeo.Repository.MessageEventDao;
import com.mirth.prometeo.Repository.MessageSegmentDao;
import com.mirth.prometeo.Repository.PRO_TDQ2HL7_Dao;
import com.mirth.prometeo.SpringbootSoapClient.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class Poller {
    private PipeParser pipeParser = new PipeParser();

    private XMLParser xmlParser = new DefaultXMLParser();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private MessageEventDao messageEventDao;
    @Autowired
    private MessageSegmentDao messageSegmentDao;
    @Autowired
    private PRO_TDQ2HL7_Dao proTdq2HL7Dao;

    @Autowired
    private SoapClient soapClient;

    @Autowired
    public Poller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //flag inoltrato da modificare In caso di errore dal checkin: 510, errore di comunicazione
    //                             In caso di errore del risultati: 520
    //                             In caso di successo: 210 checkin
    //                             In caso di successo: 220 risultati

    private final String QUERY_TO_CHECK_CHANGES = "SELECT * FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH'";
    private final String QUERY_TO_EXECUTE_ON_CHANGE = "YOUR QUERY HERE";



// parametro in secondi ma che va messo nell'.app
    @Scheduled(fixedRate = 60000)
    public void pollDatabase() throws HL7Exception, IOException {

        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format.format("\n%tl:%tM", gfg_calender, gfg_calender);

        List<PRO_TDQ2HL7> entities = jdbcTemplate.query(QUERY_TO_CHECK_CHANGES, new RowMapper<PRO_TDQ2HL7>() {

            @Override
            public PRO_TDQ2HL7 mapRow(ResultSet rs, int rowNum) throws SQLException {

                System.out.println(format);

                PRO_TDQ2HL7 result = new PRO_TDQ2HL7(
                       rs.getString("ACCESSNUMBER"),
                       rs.getString("TUBENUMBER"),
                       rs.getInt("STATO"),
                       rs.getInt("FLAG_INOLTRATO"),
                       rs.getString("LOGUSERID"),
                       rs.getString("HOSTORDERNUMBER"),
                       rs.getString("REPARTO"),
                       rs.getDate("DATE_CHK")
                );
                System.out.println("\n");
                System.out.println(result.getACCESSNUMBER());
                System.out.println(result.getTUBENUMBER());
                System.out.println(result.getSTATO());
                System.out.println(result.getFLAG_INOLTRATO());
                System.out.println(result.getLOGUSERID());
                System.out.println(result.getHOSTORDERNUMBER());
                System.out.println(result.getREPARTO());
                System.out.println(result.getDATE_CHK());
                return result;
            }
        });

        for(int i = 0; i < entities.size(); i++){
            MessageEvent message = messageEventDao.findMessageEventFiller(entities.get(i).getACCESSNUMBER());
            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);

            StringBuilder messageBody = new StringBuilder();
            for (int j = 0; j < segmentList.size(); j++) {
                messageBody.append(segmentList.get(j).getBody() + "\r");
                System.out.println(segmentList.get(j).getBody());
            }
            String body = messageBody.toString();

            OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);

            OMLO21 object = new OMLO21();
            OML_O21 omlCheckIn = object.generateOML_021CheckIn(oml_o21);


           //System.out.println("OML Per PS:\n"+xmlParser.encode(omlCheckIn));

            soapClient.sendAcceptMessage(xmlParser.encode(omlCheckIn));

            proTdq2HL7Dao.updateTDQ2HL7(entities.get(i));
        }
    }
}
