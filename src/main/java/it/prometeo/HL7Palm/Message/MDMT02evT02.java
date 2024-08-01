package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.*;
import it.prometeo.Entity.WXSDOCUMENT;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryMDMT02evT02;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryOMLO21;

import java.io.IOException;
import java.sql.SQLException;

public class MDMT02evT02 {

    private static final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));
    private final Parser pipeParser = new PipeParser();
    private static MDM_T02 mdmT02 = new MDM_T02();

    public static MDM_T02 generateMDMT02evT02(OML_O21 omlMessage, WXSDOCUMENT entity) throws HL7Exception, IOException, SQLException {

        MSH mshSegmentIntegrate = mdmT02.getMSH();
        SegmentFactoryMDMT02evT02.createMSHSegmentIntegrateMDMT02evT02(mshSegmentIntegrate, omlMessage);

        EVN evnSegmentIntegrate = mdmT02.getEVN();
        SegmentFactoryMDMT02evT02.createEVNSegmentIntegrateMDMT02evT02(evnSegmentIntegrate);

        PID pidSegmentIntegrate = mdmT02.getPID();
        SegmentFactoryMDMT02evT02.createPIDSegmentIntegrateMDMT02evT02(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = mdmT02.getPV1();
        SegmentFactoryMDMT02evT02.createPV1SegmentIntegrateMDMT02evT02(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            ORC orcSegmentIntegrate = mdmT02.getCOMMON_ORDER().getORC();
            SegmentFactoryMDMT02evT02.createORCSegmentIntegrateMDMT02evT02(orcSegmentIntegrate, omlMessage, orderIndex);

            OBR obrSegmentIntegrate = mdmT02.getCOMMON_ORDER().getOBR();
            SegmentFactoryMDMT02evT02.createOBRSegmentIntegrateMDMT02evT02(obrSegmentIntegrate, omlMessage, orderIndex);

            TXA txaSegmentIntegrate = mdmT02.getTXA();
            SegmentFactoryMDMT02evT02.createTXASegmentIntegrateMDMT02evT02(txaSegmentIntegrate, omlMessage, entity);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();
            int observationIndex = 0;

            do {
                OBX obxSegmentIntegrate = mdmT02.getOBXNTE().getOBX();
                SegmentFactoryMDMT02evT02.createOBXSegmentIntegrateMDMT02ev02(obxSegmentIntegrate, omlMessage, entity);
                observationIndex++;
            } while (observationIndex < observationReps);
        }

        return mdmT02;
    }

    public static String convertMDMT02ToXML(MDM_T02 mdmT02Message) throws HL7Exception {

        return xmlParser.encode(mdmT02Message);
    }
}
