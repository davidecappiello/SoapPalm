package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.*;
import it.prometeo.Entity.WXSDOCUMENT;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryMDMT02evT02;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryMDMT02evT10;

import java.io.IOException;
import java.sql.SQLException;

public class MDMT02evT10 {

    private static final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));
    private final Parser pipeParser = new PipeParser();
    private static MDM_T02 mdmT02 = new MDM_T02();

    public static MDM_T02 generateMDMT02evT10(OML_O21 omlMessage, WXSDOCUMENT entity) throws HL7Exception, IOException, SQLException {

        MSH mshSegmentIntegrate = mdmT02.getMSH();
        SegmentFactoryMDMT02evT10.createMSHSegmentIntegrateMDMT02evT02(mshSegmentIntegrate, omlMessage);

        EVN evnSegmentIntegrate = mdmT02.getEVN();
        SegmentFactoryMDMT02evT10.createEVNSegmentIntegrateMDMT02evT10(evnSegmentIntegrate);

        PID pidSegmentIntegrate = mdmT02.getPID();
        SegmentFactoryMDMT02evT10.createPIDSegmentIntegrateMDMT02evT10(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = mdmT02.getPV1();
        SegmentFactoryMDMT02evT10.createPV1SegmentIntegrateMDMT02evT10(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            ORC orcSegmentIntegrate = mdmT02.getCOMMON_ORDER().getORC();
            SegmentFactoryMDMT02evT10.createORCSegmentIntegrateMDMT02evT10(orcSegmentIntegrate, omlMessage, orderIndex);

            OBR obrSegmentIntegrate = mdmT02.getCOMMON_ORDER().getOBR();
            SegmentFactoryMDMT02evT10.createOBRSegmentIntegrateMDMT02evT10(obrSegmentIntegrate, omlMessage, orderIndex);

            TXA txaSegmentIntegrate = mdmT02.getTXA();
            SegmentFactoryMDMT02evT10.createTXASegmentIntegrateMDMT02evT10(txaSegmentIntegrate, omlMessage, entity);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();
            int observationIndex = 0;

            do {
                OBX obxSegmentIntegrate = mdmT02.getOBXNTE().getOBX();
                SegmentFactoryMDMT02evT10.createOBXSegmentIntegrateMDMT02ev10(obxSegmentIntegrate, omlMessage, entity);
                observationIndex++;
            } while (observationIndex < observationReps);
        }

        return mdmT02;
    }

    public static String convertMDMT02ToXML(MDM_T02 mdmT02Message) throws HL7Exception {

        return xmlParser.encode(mdmT02Message);
    }

}

