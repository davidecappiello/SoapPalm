package it.prometeo.Poller;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Entity.WXSDOCUMENT;
import it.prometeo.HL7Palm.Message.MDMT02evT02;
import it.prometeo.HL7Palm.Message.MDMT02evT10;
import it.prometeo.Repository.MessageEventDao;
import it.prometeo.Repository.MessageSegmentDao;
import it.prometeo.Repository.PRO_TDQ2HL7_Dao;
import it.prometeo.SpringbootSoapClient.SoapClient;
import it.prometeo.Util.Util;
import oracle.sql.BLOB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

@Service
public class Poller_WXSDOCUMENT {

    private final PipeParser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HL7Config hl7Config;

    @Autowired
    MessageEventDao messageEventDao;
    @Autowired
    MessageSegmentDao messageSegmentDao;
    @Autowired
    PRO_TDQ2HL7_Dao proTdq2HL7Dao;
    @Autowired
    Util util;
    @Autowired
    private SoapClient soapClient;
    public Poller_WXSDOCUMENT(JdbcTemplate jdbcTemplate, HL7Config hl7Config) {
        this.jdbcTemplate = jdbcTemplate;
        this.hl7Config = hl7Config;
    }

    @Scheduled(fixedRate = 45000)
    public void pollDatabase() throws HL7Exception, IOException, SQLException {

        String QUERY_REPORTS = hl7Config.getQueryReports();
        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format.format("\n%tl:%tM", gfg_calender, gfg_calender);

        List<WXSDOCUMENT> entities = jdbcTemplate.query(QUERY_REPORTS, new RowMapper<WXSDOCUMENT>() {

            @Override
            public WXSDOCUMENT mapRow(ResultSet rs, int rowNum) throws SQLException {

                System.out.println(format);

                WXSDOCUMENT result = new WXSDOCUMENT(
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
                        (BLOB) rs.getBlob(hl7Config.getColumnDocument()),
                        rs.getDate(hl7Config.getColumnLoadDate()),
                        rs.getDate(hl7Config.getColumnSignDate()),
                        rs.getString(hl7Config.getColumnCategory()),
                        rs.getString(hl7Config.getColumnFileName())
                );
                util.insertLogRowInt(result.getID());
                util.insertLogRow(result.getREFID());
                util.insertLogRowDate(result.getDATA_ACC());
                util.insertLogRowDate(result.getDATA_REF());
                util.insertLogRow(result.getMED());
                util.insertLogRow(result.getREP());
                util.insertLogRow(result.getNAME());
                util.insertLogRow(result.getFIRSTNAME());
                util.insertLogRow(result.getSITE());
                util.insertLogRow(result.getLABO());
                util.insertLogRow(result.getDIPA());
                util.insertLogRow(result.getVAL());
                util.insertLogRowInt(result.getLOCKID());
                util.insertLogRowDate(result.getLOCKDATE());
                util.insertLogRow(result.getMETAINFO());
                util.insertLogRow(result.getMETASIGN());
                util.insertLogRow(result.getSTATUS());
                util.insertLogRowBlob(result.getDOCUMENT());
                util.insertLogRowDate(result.getLOADDATE());
                util.insertLogRowDate(result.getSIGNDATE());
                util.insertLogRow(result.getCATEGORY());
                util.insertLogRow(result.getFILE_NAME());

                return result;
            }
        });

        for(WXSDOCUMENT entity : entities) {
            String placer = messageEventDao.findMessageEventPlacer(entity.getREFID());
            util.insertLogRow(placer);
            MessageEvent message = messageEventDao.findMessageEventPlacerFromOML(placer);
            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(message);

            StringBuilder messageBody = new StringBuilder();
            for (MessageSegment messageSegment : segmentList) {
                messageBody.append(messageSegment.getBody()).append("\r");
                util.insertLogRow(messageSegment.getBody());
            }
            String body = messageBody.toString();
            OML_O21 oml_o21 = (OML_O21) pipeParser.parse(body);
            String orc1 =  oml_o21.getORDER().getORC().getOrderControl().getValue();
            String orc5 = oml_o21.getORDER().getORC().getOrderStatus().getValue();
            if(orc1.equals(hl7Config.getOrc1New()) && orc5.equals(hl7Config.getOrc5Scheduled())) {
                MDM_T02 mdmMessage = MDMT02evT02.generateMDMT02evT02(oml_o21, entity);
                String mdmMessageFinal = MDMT02evT02.convertMDMT02ToXML(mdmMessage);
                soapClient.sendMdmMessage(mdmMessageFinal);
            } else if(orc1.equals(hl7Config.getOrcReplacement()) && orc5.equals(hl7Config.getOrcReplacement())) {
                MDM_T02 mdmMessage = MDMT02evT10.generateMDMT02evT10(oml_o21, entity);
                String mdmMessageFinal = MDMT02evT10.convertMDMT02ToXML(mdmMessage);
                soapClient.sendMdmMessage(mdmMessageFinal);
            } else if(orc1.equals(hl7Config.getOrcCancel()) && orc5.equals(hl7Config.getOrcCancel())) {
                MDM_T02 mdmMessage = MDMT02evT10.generateMDMT02evT10(oml_o21, entity);
                String mdmMessageFinal = MDMT02evT10.convertMDMT02ToXML(mdmMessage);
                soapClient.sendMdmMessage(mdmMessageFinal);
            }
        }
    }

}
