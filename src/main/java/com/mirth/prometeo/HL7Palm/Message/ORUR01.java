package com.mirth.prometeo.HL7Palm.Message;

import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryOMLO21FromORUR01;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ORUR01 {

    private final ORU_R01 oru = new ORU_R01();
    private final XMLParser xmlParser = new DefaultXMLParser();
    private final Parser pipeParser = new PipeParser();

    public OML_O21 generateOMLO21FromORUR01TD(String oruMessage) throws HL7Exception {

        ORU_R01 parsedORU = (ORU_R01) pipeParser.parse(oruMessage);

        OML_O21 oml = new OML_O21();

        MSH mshSource = parsedORU.getMSH();
        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21FromORUR01.createMSHSegmentIntegrateOMLO21FromORUR01(mshSegmentIntegrate, mshSource);

        PID pidSource = parsedORU.getPATIENT_RESULT().getPATIENT().getPID();
        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21FromORUR01.createPIDSegmentIntegrateOMLO21FromORUR01(pidSegmentIntegrate, pidSource);

        PV1 pv1Source = parsedORU.getPATIENT_RESULT().getPATIENT().getVISIT().getPV1();
        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21FromORUR01.createPV1SegmentIntegrateOMLO21FromORUR01(pv1SegmentIntegrate, pv1Source);

        int orderReps = parsedORU.getPATIENT_RESULT().getORDER_OBSERVATIONReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            ORU_R01_ORDER_OBSERVATION oruOrder = parsedORU.getPATIENT_RESULT().getORDER_OBSERVATION(orderIndex);

            ORC orcSource = oruOrder.getORC();
            ORC orcSegmentIntegrate = oml.getORDER().getORC();
            SegmentFactoryOMLO21FromORUR01.createORCSegmentIntegrateOMLO21FromORUR01(orcSegmentIntegrate, orcSource, orderIndex);

            OBR obrSource = oruOrder.getOBR();
            OBR obrSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
            SegmentFactoryOMLO21FromORUR01.createOBRSegmentIntegrateOMLO21FromORUR01(obrSegmentIntegrate, obrSource, orcSource, orderIndex);

            int observationReps = oruOrder.getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; observationIndex++) {

                OBX obxSource = oruOrder.getOBSERVATION(observationIndex).getOBX();
                OBX obxSegmentIntegrate = oml.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryOMLO21FromORUR01.createOBXSegmentIntegrateOMLO21FromORUR01(obxSegmentIntegrate, obxSource);
            }
        }
        return oml;
    }

    public String convertPIPEToXML(OML_O21 oml) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return xmlParser.encode(oml);
    }

}

