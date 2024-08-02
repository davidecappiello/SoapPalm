package it.prometeo.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.*;
import it.prometeo.HL7Palm.Message.MDMT02evT02;
import it.prometeo.HL7Palm.Message.MDMT02evT10;
import it.prometeo.HL7Palm.Message.OMLO21;
import it.prometeo.Repository.DocumentDao;
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
    private Util util;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HL7Config hl7Config;
    @Autowired
    MessageEventDao messageEventDao;
    @Autowired
    MessageSegmentDao messageSegmentDao;
    @Autowired
    DocumentDao documentDao;

    public PollerDocument(JdbcTemplate jdbcTemplate, HL7Config hl7Config) {
        this.jdbcTemplate = jdbcTemplate;
        this.hl7Config = hl7Config;
    }


    @Scheduled(fixedRate = 30000)
    public void pollDatabase() throws HL7Exception, IOException, InterruptedException, SQLException {

        final String QUERY_TO_INSERT_DOCUMENT = hl7Config.getQueryReports();
        final String QUERY_TO_CHECK_CHANGES = hl7Config.getQueryChangesDocument();
        final String QUERY_TO_CHECK_COUNT_DATA = hl7Config.getQueryCheckCountData();

        List<WXSDOCUMENT> wxsdocuments = jdbcTemplate.query(QUERY_TO_INSERT_DOCUMENT, new RowMapper<WXSDOCUMENT>() {
            @Override
            public WXSDOCUMENT mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new WXSDOCUMENT(
                        rs.getInt(hl7Config.getColumnID()),
                        rs.getString(hl7Config.getColumnRefID()),
                        rs.getDate(hl7Config.getColumnDataAcc()),
                        rs.getDate(hl7Config.getColumnDataRef()),
                        rs.getString(hl7Config.getColumnMed()),
                        rs.getString(hl7Config.getColumnRep()),
                        rs.getString(hl7Config.getColumnName()),
                        rs.getString(hl7Config.getColumnFirstName()),
                        rs.getString(hl7Config.getColumnSite()),
                        rs.getString(hl7Config.getColumnLabo()),
                        rs.getString(hl7Config.getColumnDipa()),
                        rs.getString(hl7Config.getColumnVal()),
                        rs.getInt(hl7Config.getColumnLockID()),
                        rs.getDate(hl7Config.getColumnLockDate()),
                        rs.getString(hl7Config.getColumnMetaInfo()),
                        rs.getString(hl7Config.getColumnMetaSign()),
                        rs.getString(hl7Config.getColumnStatus()),
                        rs.getBlob(hl7Config.getColumnDocument()),
                        rs.getDate(hl7Config.getColumnLoadDate()),
                        rs.getDate(hl7Config.getColumnSignDate()),
                        rs.getString(hl7Config.getColumnCategory()),
                        rs.getString(hl7Config.getColumnFileName())
                );
            }
        });
        for (WXSDOCUMENT wxsdocument : wxsdocuments) {
            DocumentEvent documentEvent = documentDao.findEvent(wxsdocument.getREFID(), wxsdocument.getFILE_NAME());

            if (documentEvent == null) {
                documentDao.insertEvent(wxsdocument.getREFID(), wxsdocument.getFILE_NAME());
            }
        }

        int result = jdbcTemplate.queryForObject(QUERY_TO_CHECK_COUNT_DATA, Integer.class);

        util.insertLogRow("result from document: " + result);

        if (result > 0) {
            DocumentEvent trigger = jdbcTemplate.queryForObject(QUERY_TO_CHECK_CHANGES, new RowMapper<DocumentEvent>() {

                @Override
                public DocumentEvent mapRow(ResultSet rs, int rowNum) throws SQLException {

                    DocumentEvent result = new DocumentEvent();
                    result.setACCESSNUMBER(rs.getString(hl7Config.getColumnAccessNumber()));
                    result.setFILE_NAME(rs.getString(hl7Config.getColumnFileName()));
                    result.setFLAG_INOLTRATO(rs.getInt(hl7Config.getColumnFlagInoltrato()));
                    return result;
                }
            });


            String placer = messageEventDao.findMessagePlacerGroupNumber(trigger.getACCESSNUMBER());
            util.insertLogRow(placer);
            WXSDOCUMENT entity = documentDao.findDocument(trigger.getACCESSNUMBER(), trigger.getFILE_NAME());
            MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);

            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);
            StringBuilder messageBody = new StringBuilder();
            for (MessageSegment messageSegment : segmentList) {
                messageBody.append(messageSegment.getBody()).append("\r");
                System.out.println(messageSegment.getBody());
            }
            String body = messageBody.toString();
            OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);
            util.sendMDMMessages(oml_o21, entity);

            documentDao.updateDocumentEvent(trigger);
        }
    }
}
