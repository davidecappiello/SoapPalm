package com.mirth.prometeo.HL7Palm.Message;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.ORM_O01_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.prometeo.HL7Palm.Decoding.ORMDecoding;
import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryORMOO1;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ORMOO1 {

    private final XMLParser xmlParser = new DefaultXMLParser();
    private final Parser pipeParser = new PipeParser();

    public ORM_O01 generateORM_OO1(OML_O21 omlMessage, String date) throws HL7Exception, IOException {

        ORM_O01 orm = new ORM_O01();

        MSH mshSegmentIntegrate = orm.getMSH();
        SegmentFactoryORMOO1.createMSHSegmentIntegrateORMOO1(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = orm.getPATIENT().getPID();
        SegmentFactoryORMOO1.createPIDSegmentIntegrateORMOO1(pidSegmentIntegrate, omlMessage, date);

        PV1 pv1SegmentIntegrate = orm.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryORMOO1.createPV1SegmentIntegrateORMOO1(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            ORM_O01_ORDER ormOrder = orm.getORDER(orderIndex);

            ORC orc = ormOrder.getORC();
            SegmentFactoryORMOO1.createORCSegmentIntegrateORMOO1(orc, omlMessage, orderIndex);

            OBR obr = ormOrder.getORDER_DETAIL().getOBR();
            SegmentFactoryORMOO1.createOBRSegmentIntegrateORMOO1(obr, omlMessage, orderIndex);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; ++observationIndex) {

                OBX obxSegmentIntegrate = ormOrder.getORDER_DETAIL().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryORMOO1.createOBXSegmentIntegrateORMOO1(obxSegmentIntegrate, omlMessage, orderIndex, observationIndex);
            }
        }

        return orm;
    }

    public ORM_O01 decodeIncomingMessage(ORM_O01 ormO01) throws HL7Exception, IOException, ParserConfigurationException, SAXException {
        return ORMDecoding.decodeORM_XML(xmlParser.encode(ormO01));
    }

    public String convertXMLToPipeFormat(ORM_O01 ormCreated) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return pipeParser.encode(ormCreated);
    }
}
