package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryMDMT02T02;

import java.io.IOException;


public class MDMT2_T02 {


    public MDM_T02 generateMDM_T02() throws HL7Exception, IOException {

        MDM_T02 mdm = new MDM_T02();

        MSH mshSegment = mdm.getMSH();
        SegmentFactoryMDMT02T02.createMSHSegmentIntegrateMDMT02(mshSegment);

        EVN evnSegment = mdm.getEVN();
        SegmentFactoryMDMT02T02.createEVNSegmentIntegrateMDMT02(evnSegment);

        PID pidSegment = mdm.getPID();
        SegmentFactoryMDMT02T02.createPIDSegmentIntegrateMDMT02(pidSegment);

        PV1 pv1Segment = mdm.getPV1();
        SegmentFactoryMDMT02T02.createPV1SegmentIntegrateMDMT02(pv1Segment);

        ORC orcSegment = mdm.getCOMMON_ORDER(0).getORC();
        SegmentFactoryMDMT02T02.createORCSegmentIntegrateMDMT02(orcSegment);

        OBR obrSegment = mdm.getCOMMON_ORDER(0).getOBR();
        SegmentFactoryMDMT02T02.createOBRSegmentIntegrateMDMT02(obrSegment);

        TXA txaSegment = mdm.getTXA();
        SegmentFactoryMDMT02T02.createTXASegmentIntegrateMDMT02(txaSegment);

        OBX obxSegment = mdm.getOBXNTE(0).getOBX();
        SegmentFactoryMDMT02T02.createOBXReportSegmentIntegrateMDMT02(obxSegment);
        SegmentFactoryMDMT02T02.createOBXSegmentIntegrateMDMT02ConsentFSEPubblication(obxSegment);
        SegmentFactoryMDMT02T02.createOBXSegmentIntegrateMDMT02ReportCollectionMode(obxSegment);

        NTE nteSegment = mdm.getOBXNTE().getNTE();
        SegmentFactoryMDMT02T02.createNTESegmentIntegrateMDMT02TriggerT02(nteSegment);


        return mdm;
    }

    public ACK ackResponseToMDMT02(MDM_T02 mdm) throws HL7Exception, IOException {

        ACK generatedACK = (ACK) mdm.generateACK();
        generatedACK.getMSA().getTextMessage().setValue("bla bla");

        return generatedACK;
    }

}
