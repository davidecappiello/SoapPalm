package com.mirth.prometeo.HL7Palm.Message;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.prometeo.HL7Palm.Decoding.OMLDecoding;
import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryOMLO21;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OMLO21 {

    private static final OML_O21 oml = new OML_O21();
    private final XMLParser xmlParser = new DefaultXMLParser();
    private final Parser pipeParser = new PipeParser();

    public OML_O21 generateOML_021(OML_O21 omlMessage, String date) throws HL7Exception, IOException {

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21(pidSegmentIntegrate, omlMessage, date);

        PD1 pd1SegmentIntegrate = oml.getPATIENT().getPD1();
        SegmentFactoryOMLO21.createPD1SegmentIntegrateOMLO21(pd1SegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21(pv1SegmentIntegrate, omlMessage);

        ORC orcSegmentIntegrate = oml.getORDER().getORC();
        SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21(orcSegmentIntegrate, omlMessage);

        TQ1 tq1SegmentIntegrate = oml.getORDER().getTIMING().getTQ1();
        SegmentFactoryOMLO21.createTQ1SegmentIntegrateOMLO21(tq1SegmentIntegrate, omlMessage);

        OBR obrSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
        SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21(obrSegmentIntegrate, omlMessage);

        OBX obxSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX();
        SegmentFactoryOMLO21.createOBXSegmentIntegrateOMLO21(obxSegmentIntegrate, omlMessage);

        return oml;
    }

    public static OML_O21 generateOML_021ForTD(OML_O21 omlMessage, String date) throws HL7Exception, IOException {

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21(pidSegmentIntegrate, omlMessage, date);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21(pv1SegmentIntegrate, omlMessage);

        ORC orcSegmentIntegrate = oml.getORDER().getORC();
        SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21(orcSegmentIntegrate, omlMessage);

        OBR obrSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
        SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21(obrSegmentIntegrate, omlMessage);


        return oml;
    }

    public OML_O21 decodeIncomingMessage(OML_O21 oml) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return OMLDecoding.decodeOML_XML(xmlParser.encode(oml));
    }
    public String convertXMLToPipeFormat(OML_O21 oml) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return pipeParser.encode(oml);
    }


}
