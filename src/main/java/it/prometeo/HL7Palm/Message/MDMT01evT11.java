package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T01;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.*;
import it.prometeo.Entity.WXSDOCUMENT;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryMDMT01evT11;
import java.io.IOException;
import java.sql.SQLException;

public class MDMT01evT11 {

    private static final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));
    private final Parser pipeParser = new PipeParser();
    private static MDM_T01 mdmT01= new MDM_T01();

    public static MDM_T01 generateMDMT01evT11(OML_O21 omlMessage, WXSDOCUMENT entity) throws HL7Exception, IOException, SQLException {

        MSH mshSegmentIntegrate = mdmT01.getMSH();
        SegmentFactoryMDMT01evT11.createMSHSegmentIntegrateMDMT01evT11(mshSegmentIntegrate, omlMessage);

        EVN evnSegmentIntegrate = mdmT01.getEVN();
        SegmentFactoryMDMT01evT11.createEVNSegmentIntegrateMDMT01evT11(evnSegmentIntegrate);

        PID pidSegmentIntegrate = mdmT01.getPID();
        SegmentFactoryMDMT01evT11.createPIDSegmentIntegrateMDMT01evT11(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = mdmT01.getPV1();
        SegmentFactoryMDMT01evT11.createPV1SegmentIntegrateMDMT01evT11(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {
            TXA txaSegmentIntegrate = mdmT01.getTXA();
            SegmentFactoryMDMT01evT11.createTXASegmentIntegrateMDMT01evT11(txaSegmentIntegrate, omlMessage, entity);
        }
        return mdmT01;
    }

    public static String convertMDMT01ToXML(MDM_T02 mdmT02Message) throws HL7Exception {

        return xmlParser.encode(mdmT02Message);
    }
}
