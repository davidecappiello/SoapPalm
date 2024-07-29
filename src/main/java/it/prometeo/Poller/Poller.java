package it.prometeo.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Entity.PRO_TDQ2HL7;
import it.prometeo.HL7Palm.Message.OMLO21;
import it.prometeo.Repository.MessageEventDao;
import it.prometeo.Repository.MessageSegmentDao;
import it.prometeo.Repository.PRO_TDQ2HL7_Dao;
import it.prometeo.SpringbootSoapClient.SoapClient;
import it.prometeo.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*@Service
public class Poller {

    private final PipeParser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser();
    private final JdbcTemplate jdbcTemplate;
    private static HL7Config hl7Config = null;
    private static final Util util = new Util();

    @Autowired
    public Poller(JdbcTemplate jdbcTemplate, HL7Config hl7Config) {
        this.jdbcTemplate = jdbcTemplate;
        Poller.hl7Config = hl7Config;
    }
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

    private final String QUERY_TO_CHECK_CHANGES = hl7Config.getQueryChanges();
    private final String QUERY_TO_EXECUTE_ON_CHANGE = "YOUR QUERY HERE";

    @Scheduled(fixedRate = 60000)
    public void pollDatabase() throws HL7Exception, IOException {

        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format.format("\n%tl:%tM", gfg_calender, gfg_calender);

        List<PRO_TDQ2HL7> entities = jdbcTemplate.query(QUERY_TO_CHECK_CHANGES, new RowMapper<PRO_TDQ2HL7>() {

            @Override
            public PRO_TDQ2HL7 mapRow(ResultSet rs, int rowNum) throws SQLException {

                util.insertLogRow(String.valueOf(format));

                PRO_TDQ2HL7 result = new PRO_TDQ2HL7(
                       rs.getString(hl7Config.getColumnAccessNumber()),
                       rs.getString(hl7Config.getColumnTubeNumber()),
                       rs.getInt(hl7Config.getColumnStato()),
                       rs.getInt(hl7Config.getColumnFlagInoltrato()),
                       rs.getString(hl7Config.getColumnLogUserID()),
                       rs.getString(hl7Config.getColumnHostOrderNumber()),
                       rs.getString(hl7Config.getColumnReparto()),
                       rs.getDate(hl7Config.getColumnDateCHK())
                );

                util.insertLogRow(result.getACCESSNUMBER());
                util.insertLogRow(result.getTUBENUMBER());
                util.insertLogRowInt(result.getSTATO());
                util.insertLogRowInt(result.getFLAG_INOLTRATO());
                util.insertLogRow(result.getLOGUSERID());
                util.insertLogRow(result.getHOSTORDERNUMBER());
                util.insertLogRow(result.getREPARTO());
                util.insertLogRowDate(result.getDATE_CHK());

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

            soapClient.sendAcceptMessage(xmlParser.encode(omlCheckIn));

            proTdq2HL7Dao.updateTDQ2HL7(entities.get(i));
        }
    }
}*/
