package it.prometeo.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Entity.PRO_TDQ2HL7;
import it.prometeo.HL7Palm.Message.OMLO21;
import it.prometeo.Repository.MessageEventDao;
import it.prometeo.Repository.MessageSegmentDao;
import it.prometeo.Repository.PRO_TDQ2HL7_Dao;
import it.prometeo.SpringbootSoapClient.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class PollerCheckIn {

    private static Object LOCK = new Object();
    private PipeParser pipeParser = new PipeParser();

    private XMLParser xmlParser = new DefaultXMLParser();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  DataSource dataSource;

    @Autowired
    MessageEventDao messageEventDao;
    @Autowired
    MessageSegmentDao messageSegmentDao;
    @Autowired
    PRO_TDQ2HL7_Dao proTdq2HL7Dao;

    @Autowired
    private SoapClient soapClient;

    public PollerCheckIn(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //flag inoltrato da modificare In caso di errore dal checkin: 510, errore di comunicazione
    //                             In caso di errore del risultati: 520
    //                             In caso di successo: 210 checkin
    //                             In caso di successo: 220 risultati

    private final String QUERY_TO_CHECK_CHANGES = "SELECT * FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH'";
    private final String QUERY_TO_CHECK_CHANGES_ONE_DATA = "SELECT * FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH' AND ROWNUM = 1";
    private final String QUERY_TO_CHECK_COUNT_DATA = "SELECT COUNT(*) FROM PRO_TDQ2HL7 pth WHERE FLAG_INOLTRATO = 0 AND REPARTO = 'PNGH' AND ROWNUM = 1";




// parametro in secondi ma che va messo nell'.app
    /* metodo normale
    @Scheduled(fixedRate = 30000)
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
            String placer = messageEventDao.findMessageEventPlacer(entities.get(i).getACCESSNUMBER());
            System.out.println(placer);
            MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);
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
    }*/

    /*
    //Metodo con il lock e sleep
    @Scheduled(fixedRate = 30000)
    public void pollDatabase() throws HL7Exception, IOException, InterruptedException {

        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format.format("\n%tl:%tM", gfg_calender, gfg_calender);

        synchronized (LOCK) {
            LOCK.wait(60000);
            System.out.println("Object '" + LOCK + "' is woken after" +
                    " waiting for 1 second");
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
//                    System.out.println("\n");
//                    System.out.println(result.getACCESSNUMBER());
//                    System.out.println(result.getTUBENUMBER());
//                    System.out.println(result.getSTATO());
//                    System.out.println(result.getFLAG_INOLTRATO());
//                    System.out.println(result.getLOGUSERID());
//                    System.out.println(result.getHOSTORDERNUMBER());
//                    System.out.println(result.getREPARTO());
//                    System.out.println(result.getDATE_CHK());
                    return result;
                }
            });

            for(int i = 0; i < entities.size(); i++){
                String placer = messageEventDao.findMessageEventPlacer(entities.get(i).getACCESSNUMBER());
                System.out.println(placer);
                MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);
                List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);

                StringBuilder messageBody = new StringBuilder();
                for (int j = 0; j < segmentList.size(); j++) {
                    messageBody.append(segmentList.get(j).getBody() + "\r");
                    //System.out.println(segmentList.get(j).getBody());
                }
                String body = messageBody.toString();

                OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);

                OMLO21 object = new OMLO21();
                OML_O21 omlCheckIn = object.generateOML_021CheckIn(oml_o21);


                //System.out.println("OML Per PS:\n"+xmlParser.encode(omlCheckIn));

                soapClient.sendAcceptMessage(xmlParser.encode(omlCheckIn));

                proTdq2HL7Dao.updateTDQ2HL7(entities.get(i));

                Thread.sleep(60000);
                System.out.println("Wake up");
            }
        }
    }*/

    //poller che prende un dato alla volta
    @Scheduled(fixedRate = 30000)
    public void pollDatabase() throws HL7Exception, IOException {


        int result = jdbcTemplate.queryForObject(QUERY_TO_CHECK_COUNT_DATA, Integer.class);

        System.out.println("result from checkin: " +result);

        if(result > 0){
            PRO_TDQ2HL7 proTdq2HL7 = jdbcTemplate.queryForObject(QUERY_TO_CHECK_CHANGES_ONE_DATA, new RowMapper<PRO_TDQ2HL7>() {

                @Override
                public PRO_TDQ2HL7 mapRow(ResultSet rs, int rowNum) throws SQLException {

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
                    return result;
                }
            });

            String placer = messageEventDao.findMessagePlacerGroupNumber(proTdq2HL7.getACCESSNUMBER());
            System.out.println(placer);
            MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);
            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);

            StringBuilder messageBody = new StringBuilder();
            for(int j = 0; j < segmentList.size(); j++) {
                messageBody.append(segmentList.get(j).getBody() + "\r");
                System.out.println(segmentList.get(j).getBody());
            }
            String body = messageBody.toString();

            OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);

            OMLO21 object = new OMLO21();
            OML_O21 omlCheckIn = object.generateOML_021CheckIn(oml_o21);

            soapClient.sendAcceptMessage(xmlParser.encode(omlCheckIn));

            proTdq2HL7Dao.updateTDQ2HL7(proTdq2HL7);
        }
    }
}