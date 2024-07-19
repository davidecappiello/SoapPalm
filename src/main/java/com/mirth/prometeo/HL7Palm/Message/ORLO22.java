package com.mirth.prometeo.HL7Palm.Message;

import com.mirth.prometeo.HL7Palm.Decoding.ORLDecoding;
import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryORLO22;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.group.ORL_O22_ORDER;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.OBR;
import ca.uhn.hl7v2.model.v25.segment.ORC;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ORLO22 {

    private final ORL_O22 orl = new ORL_O22();
    private final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));
    private final PipeParser pipeParser = new PipeParser();

    public ORL_O22 generateORL_O22FromOML(ACK ackMessage, Message genericMessage, OML_O21 oml_o21) throws HL7Exception, IOException {

        MSH mshSegmentIntegrate = orl.getMSH();
        SegmentFactoryORLO22.createMSHSegmentIntegrateORLO22FromOML(mshSegmentIntegrate, oml_o21, ackMessage);

        MSA msaSegmentIntegrate = orl.getMSA();
        SegmentFactoryORLO22.createMSASegmentIntegrateORLO22(msaSegmentIntegrate, genericMessage);

        int orderReps = oml_o21.getORDERReps();

        for (int i = 0; i < orderReps; i++) {
            ORC orcOld = oml_o21.getORDER(i).getORC();
            ORL_O22_ORDER orlOrder = orl.getRESPONSE().getPATIENT().getORDER(i);

            ORC orcSegmentIntegrate = orlOrder.getORC();
            SegmentFactoryORLO22.createORCSegmentIntegrateORLO22(orcSegmentIntegrate, orcOld, i);

            int orderDetailReps = oml_o21.getORDER().getOBSERVATION_REQUEST().currentReps("OBR");


            for (int j = 0; j < orderDetailReps; j++) {
                OBR obrOld = oml_o21.getORDER().getOBSERVATION_REQUEST().getOBR();
                if (obrOld != null) {
                    OBR obrSegmentIntegrate = orlOrder.getOBSERVATION_REQUEST().getOBR();
                    SegmentFactoryORLO22.createOBRSegmentIntegrateORLO22FromOML(obrSegmentIntegrate, oml_o21, j);
                }
            }
        }

        return orl;
    }

    public ORL_O22 generateORL_O22FromORM(ACK ackMessage, Message genericMessage, ORM_O01 ormMessage) throws HL7Exception, IOException {

        MSH mshSegmentIntegrate = orl.getMSH();
        SegmentFactoryORLO22.createMSHSegmentIntegrateORLO22FromORM(mshSegmentIntegrate, ormMessage, ackMessage);

        MSA msaSegmentIntegrate = orl.getMSA();
        SegmentFactoryORLO22.createMSASegmentIntegrateORLO22(msaSegmentIntegrate, genericMessage);

        int orderReps = ormMessage.getORDERReps();

        for (int i = 0; i < orderReps; i++) {
            ORC orcOld = ormMessage.getORDER(i).getORC();
            ORL_O22_ORDER orlOrder = orl.getRESPONSE().getPATIENT().getORDER(i);

            ORC orcSegmentIntegrate = orlOrder.getORC();
            SegmentFactoryORLO22.createORCSegmentIntegrateORLO22(orcSegmentIntegrate, orcOld, i);

            int orderDetailReps = ormMessage.getORDER().getORDER_DETAIL().currentReps("OBR");

            for (int j = 0; j < orderDetailReps; j++) {
                OBR obrOld = ormMessage.getORDER().getORDER_DETAIL().getOBR();
                if (obrOld != null) {
                    OBR obrSegmentIntegrate = orlOrder.getOBSERVATION_REQUEST().getOBR();
                    SegmentFactoryORLO22.createOBRSegmentIntegrateORLO22FromORM(obrSegmentIntegrate, ormMessage, j);
                }
            }
        }
        return orl;
    }

    public ORL_O22 decodeMessage(ORL_O22 orl) throws HL7Exception, IOException, ParserConfigurationException, SAXException {
        return ORLDecoding.decodeORL_XML(xmlParser.encode(orl));
    }

    public String stringMessageToXML(String hl7Message) throws HL7Exception {
        return ORLDecoding.convertHl7ToXml(hl7Message);
    }
}
