package it.prometeo.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.Entity.*;
import it.prometeo.HL7Palm.Message.MDMT2_T02;
import it.prometeo.HL7Palm.Message.OMLO21;
import it.prometeo.Repository.DocumentDao;
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
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

@Service
public class PollerDocument {

    private PipeParser pipeParser = new PipeParser();

    private XMLParser xmlParser = new DefaultXMLParser();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    MessageEventDao messageEventDao;
    @Autowired
    MessageSegmentDao messageSegmentDao;
    @Autowired
    DocumentDao documentDao;

    @Autowired
    private SoapClient soapClient;

    public PollerDocument(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String QUERY_TO_INSERT_DOCUMENT = "SELECT * FROM WXSDOCUMENT WHERE STATUS = 'P7M'";
    private final String QUERY_TO_CHECK_CHANGES = "SELECT * FROM PRO_DOCUMENT_EVENT WHERE FLAG_INOLTRATO = 0 AND ROWNUM = 1";
    private final String QUERY_TO_CHECK_COUNT_DATA = "SELECT COUNT(*) FROM PRO_DOCUMENT_EVENT WHERE FLAG_INOLTRATO = 0 AND ROWNUM = 1";



    @Scheduled(fixedRate = 30000)
    public void pollDatabase() throws HL7Exception, IOException, InterruptedException {

        List<WXSDOCUMENT> wxsdocuments = jdbcTemplate.query(QUERY_TO_INSERT_DOCUMENT, new RowMapper<WXSDOCUMENT>() {
            @Override
            public WXSDOCUMENT mapRow(ResultSet rs, int rowNum) throws SQLException {

                WXSDOCUMENT result = new WXSDOCUMENT(
                        rs.getInt("ID"),
                        rs.getString("REFID"),
                        rs.getDate("DATA_ACC"),
                        rs.getDate("DATA_REF"),
                        rs.getString("MED"),
                        rs.getString("REP"),
                        rs.getString("NAME"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("SITE"),
                        rs.getString("LABO"),
                        rs.getString("DIPA"),
                        rs.getString("VAL"),
                        rs.getInt("LOCKID"),
                        rs.getDate("LOCKDATE"),
                        rs.getString("METAINFO"),
                        rs.getString("METASIGN"),
                        rs.getString("STATUS"),
                        rs.getBlob("DOCUMENT"),
                        rs.getDate("LOADDATE"),
                        rs.getDate("SIGNDATE"),
                        rs.getString("CATEGORY"),
                        rs.getString("FILE_NAME")
                );
                return result;
            }
        });
        for (int i = 0; i < wxsdocuments.size(); i++) {
            DocumentEvent documentEvent = documentDao.findEvent(wxsdocuments.get(i).getREFID(), wxsdocuments.get(i).getFILE_NAME());

            if(documentEvent == null){
                documentDao.insertEvent(wxsdocuments.get(i).getREFID(), wxsdocuments.get(i).getFILE_NAME());
            }
        }

        int result = jdbcTemplate.queryForObject(QUERY_TO_CHECK_COUNT_DATA, Integer.class);

        System.out.println("result from checkin: " + result);

        if (result > 0) {
            DocumentEvent trigger = jdbcTemplate.queryForObject(QUERY_TO_CHECK_CHANGES, new RowMapper<DocumentEvent>() {

                @Override
                public DocumentEvent mapRow(ResultSet rs, int rowNum) throws SQLException {

                    DocumentEvent result = new DocumentEvent();
                    result.setACCESSNUMBER(rs.getString("ACCESSNUMBER"));
                    result.setFILE_NAME(rs.getString("FILE_NAME"));
                    result.setFLAG_INOLTRATO(rs.getInt("FLAG_INOLTRATO"));
                    return result;
                }
            });


            String placer = messageEventDao.findMessagePlacerGroupNumber(trigger.getACCESSNUMBER());
            System.out.println(placer);

            documentDao.findDocument(trigger.getACCESSNUMBER(), trigger.getFILE_NAME());

            MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);


            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);
            StringBuilder messageBody = new StringBuilder();
            for(int j = 0; j < segmentList.size(); j++) {
                messageBody.append(segmentList.get(j).getBody() + "\r");
                System.out.println(segmentList.get(j).getBody());
            }
            String body = messageBody.toString();
            OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);

            MDMT2_T02 mdmt2_t02 = new MDMT2_T02();
            MDM_T02 mdm_t02 = mdmt2_t02.generateMDM_T02();

            soapClient.sendAcceptMessageDocument(xmlParser.encode(mdm_t02));

            documentDao.updateDocumentEvent(trigger);
        }
    }
}
