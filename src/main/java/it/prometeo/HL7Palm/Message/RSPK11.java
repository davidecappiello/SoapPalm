package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.GenericMessage;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.*;

import it.prometeo.Repository.MessageEventDao;
import it.prometeo.Repository.MessageSegmentDao;
import it.prometeo.Repository.RSPDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.HL7Palm.Message.Custom.RSP_K11;
import it.prometeo.HL7Palm.Segment.ZET;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryRSPK11;

import java.io.IOException;
import java.util.List;

@Service
public class RSPK11 {

    @Autowired
    private RSPDao rspDao;

    @Autowired
    private MessageEventDao eventDao;
    @Autowired
    private MessageSegmentDao messageSegmentDao;

    private PipeParser pipeParser = new PipeParser();

    private final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));


    public RSP_K11 createEncodedMessage(String triggerEvent, QBP_Q11 qbpDecoded) throws HL7Exception, IOException {

        RSP_K11 rspK11Custom = new RSP_K11();

        //Richiesta Referti
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

        //Richiesta Etichette
        } else if (triggerEvent.equals("SLI")) {

            boolean flag = true;

            String bodyORL = rspDao.findORLforZET(qbpDecoded);
            String bodyOml = rspDao.findOMLForRSP(qbpDecoded);


            Message orl = null;
            OML_O21 oml = null;
            if (bodyORL != null && bodyOml != null) {
                orl = pipeParser.parse(bodyORL);
                oml = (OML_O21) pipeParser.parse(bodyOml);
            } else {
                flag = false;
            }

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateSLI(msaSegmentIntegrate, qbpDecoded, flag);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            ZET zetSegmentIntegrate = rspK11Custom.getZET();
            SegmentFactoryRSPK11.createSegmentZET(zetSegmentIntegrate, orl, oml);

            //Richiesta Stato
        } else if (triggerEvent.equals("SC")) {

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            ORC orcFromOML = rspDao.findORCForRSP(qbpDecoded);

            ORC orcSegmentIntegrate = rspK11Custom.getORC();
            SegmentFactoryRSPK11.createORCSegmentSCIntegrateRSPK11ToQBP(orcSegmentIntegrate, orcFromOML);
            //segmentFactory.createORCSegmentORIntegrateRSPK11(orcSegmentIntegrate);

        //Richiesta Risultati
        } else if (triggerEvent.equals("OR")) {

            String bodyOml = rspDao.findOMLForRSP(qbpDecoded);
            Message oml = pipeParser.parse(bodyOml);

            MSH mshSegmentIntegrate = rspK11Custom.getMSH();
            SegmentFactoryRSPK11.createMSHSegmentIntegrateRSPK11ToQBP(mshSegmentIntegrate, triggerEvent, qbpDecoded.getMSH());

            MSA msaSegmentIntegrate = rspK11Custom.getMSA();
            SegmentFactoryRSPK11.createMSASegmentIntegrateRSPK11ToQBP(msaSegmentIntegrate,qbpDecoded);

            QAK qakSegmentIntegrate = rspK11Custom.getQAK();
            SegmentFactoryRSPK11.createQAKSegmentIntegrateRSPK11ToQBP(qakSegmentIntegrate, qbpDecoded);

            //MANCANZA DI SPM
            SPM spmSegmentIntegrate = rspK11Custom.getSPM();
            SegmentFactoryRSPK11.createSPMSegmentIntegrateRSPK11ToQBP(spmSegmentIntegrate);

            ORC orcSegmentIntegrate = rspK11Custom.getORC();
            SegmentFactoryRSPK11.createORCSegmentORIntegrateRSPK11ToQBP(orcSegmentIntegrate, oml);

            OBR obrSegmentIntegrate = rspK11Custom.getOBR();
            SegmentFactoryRSPK11.createOBRSegmentIntegrateRSPK11ToQBP(obrSegmentIntegrate, orcSegmentIntegrate);

            TQ1 tq1SegmentIntegrate = rspK11Custom.getTQ1();
            SegmentFactoryRSPK11.createTQ1SegmentIntegrateRSPK11ToQBP(tq1SegmentIntegrate, oml);

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
