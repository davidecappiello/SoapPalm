package com.mirth.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.prometeo.HL7Palm.Message.Custom.RSP_K11;
import com.mirth.prometeo.HL7Palm.Segment.ZET;
import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryRSPK11;

import java.io.IOException;

public class RSPK11 {

    private final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));


    public RSP_K11 createEncodedMessage(String triggerEvent, QBP_Q11 qbpDecoded) throws HL7Exception, IOException {

        RSP_K11 rspK11Custom = new RSP_K11();

        if (triggerEvent.equals("PRO")){

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            OBX obxSegmentIntegrate = rspK11Custom.getOBX();
            SegmentFactoryRSPK11.createOBXSegmentPROIntegrateRSPK11ToQBP(obxSegmentIntegrate);
            //segmentFactory.createOBXSegmentORIntegrateRSPK11(obxSegmentIntegrate);


        } else if (triggerEvent.equals("SLI")) {

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            ZET zetSegmentIntegrate = rspK11Custom.getZET();
            SegmentFactoryRSPK11.createSegmentZET(zetSegmentIntegrate);


        } else if (triggerEvent.equals("SC")) {

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            ORC orcSegmentIntegrate = rspK11Custom.getORC();
            SegmentFactoryRSPK11.createORCSegmentSCIntegrateRSPK11ToQBP(orcSegmentIntegrate);
            //segmentFactory.createORCSegmentORIntegrateRSPK11(orcSegmentIntegrate);


        } else if (triggerEvent.equals("OR")) {

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            SPM spmSegmentIntegrate = rspK11Custom.getSPM();
            SegmentFactoryRSPK11.createSPMSegmentIntegrateRSPK11ToQBP(spmSegmentIntegrate);

            ORC orcSegmentIntegrate = rspK11Custom.getORC();
            SegmentFactoryRSPK11.createORCSegmentORIntegrateRSPK11ToQBP(orcSegmentIntegrate);
            //segmentFactory.createORCSegmentORIntegrateRSPK11(orcSegmentIntegrate);

            OBR obrSegmentIntegrate = rspK11Custom.getOBR();
            SegmentFactoryRSPK11.createOBRSegmentIntegrateRSPK11ToQBP(obrSegmentIntegrate, orcSegmentIntegrate);

            TQ1 tq1SegmentIntegrate = rspK11Custom.getTQ1();
            SegmentFactoryRSPK11.createTQ1SegmentIntegrateRSPK11ToQBP(tq1SegmentIntegrate);

            OBX obxSegmentIntegrate= rspK11Custom.getOBX();
            SegmentFactoryRSPK11.createOBXSegmentPROIntegrateRSPK11ToQBP(obxSegmentIntegrate);
            //segmentFactory.createOBXSegmentORIntegrateRSPK11(obxSegmentIntegrate);

        }


        return rspK11Custom;
    }

    public String XmlEncodind (RSP_K11 rspK11) throws HL7Exception {
        return xmlParser.encode(rspK11);
    }

}
