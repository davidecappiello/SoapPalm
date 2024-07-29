package it.prometeo.HL7Palm.Message;

import it.prometeo.HL7Palm.Decoding.OMLDecoding;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryOMLO21;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OMLO21 {


    private final XMLParser xmlParser = new DefaultXMLParser();
    private final Parser pipeParser = new PipeParser();

    public OML_O21 generateOML_021(OML_O21 omlMessage) throws HL7Exception, IOException {
        OML_O21 oml = new OML_O21();

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21(pv1SegmentIntegrate, omlMessage);

        TQ1 tq1SegmentIntegrate = oml.getORDER().getTIMING().getTQ1();
        SegmentFactoryOMLO21.createTQ1SegmentIntegrateOMLO21(tq1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            OML_O21_ORDER omlOrder = oml.getORDER(orderIndex);

            ORC orc = omlOrder.getORC();
            SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21(orc, omlMessage, orderIndex);

            OBR obr = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
            SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21(obr, omlMessage, orderIndex);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; ++observationIndex) {

                OBX obxSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryOMLO21.createOBXSegmentIntegrateOMLO21(obxSegmentIntegrate, omlMessage, orderIndex, observationIndex);
            }
        }

        return oml;
    }

    public OML_O21 generateOML_021ForTDFromPS(OML_O21 omlMessage) throws HL7Exception, IOException {
        OML_O21 oml = new OML_O21();

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            OML_O21_ORDER omlOrder = oml.getORDER(orderIndex);

            ORC orc = omlOrder.getORC();
            SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21ORCFillerNumberWhitened(orc, omlMessage, orderIndex);

            OBR obr = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
            SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21(obr, omlMessage, orderIndex);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; ++observationIndex) {

                OBX obxSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryOMLO21.createOBXSegmentIntegrateOMLO21(obxSegmentIntegrate, omlMessage, orderIndex, observationIndex);
            }
        }
        return oml;
    }

    public OML_O21 generateOML_021ForTDFromTransfusion(OML_O21 omlMessage) throws HL7Exception, IOException {
        OML_O21 oml = new OML_O21();

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21FromTransfusion(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21(pv1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            OML_O21_ORDER omlOrder = oml.getORDER(orderIndex);

            ORC orc = omlOrder.getORC();
            SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21ORCFillerNumberWhitened(orc, omlMessage, orderIndex);

            OBR obr = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
            SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21(obr, omlMessage, orderIndex);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; ++observationIndex) {

                OBX obxSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryOMLO21.createOBXSegmentIntegrateOMLO21(obxSegmentIntegrate, omlMessage, orderIndex, observationIndex);
            }
        }
        return oml;
    }

    public OML_O21 generateOML_021CheckIn(OML_O21 omlMessage) throws HL7Exception, IOException {
        OML_O21 oml = new OML_O21();

        MSH mshSegmentIntegrate = oml.getMSH();
        SegmentFactoryOMLO21.createMSHSegmentIntegrateOMLO21CheckIn(mshSegmentIntegrate, omlMessage);

        PID pidSegmentIntegrate = oml.getPATIENT().getPID();
        SegmentFactoryOMLO21.createPIDSegmentIntegrateOMLO21CheckIn(pidSegmentIntegrate, omlMessage);

        PV1 pv1SegmentIntegrate = oml.getPATIENT().getPATIENT_VISIT().getPV1();
        SegmentFactoryOMLO21.createPV1SegmentIntegrateOMLO21CheckIn(pv1SegmentIntegrate, omlMessage);

        TQ1 tq1SegmentIntegrate = oml.getORDER().getTIMING().getTQ1();
        SegmentFactoryOMLO21.createTQ1SegmentIntegrateOMLO21CheckIn(tq1SegmentIntegrate, omlMessage);

        int orderReps = omlMessage.getORDERReps();

        for (int orderIndex = 0; orderIndex < orderReps; ++orderIndex) {

            OML_O21_ORDER omlOrder = oml.getORDER(orderIndex);

            ORC orc = omlOrder.getORC();
            SegmentFactoryOMLO21.createORCSegmentIntegrateOMLO21CheckIn(orc, omlMessage, orderIndex);

            OBR obr = oml.getORDER().getOBSERVATION_REQUEST().getOBR();
            SegmentFactoryOMLO21.createOBRSegmentIntegrateOMLO21CheckIn(obr, omlMessage, orderIndex);

            int observationReps = omlMessage.getORDER(orderIndex).getOBSERVATION_REQUEST().getOBSERVATIONReps();

            for (int observationIndex = 0; observationIndex < observationReps; ++observationIndex) {
                OBX obxSegmentIntegrate = oml.getORDER().getOBSERVATION_REQUEST().getOBSERVATION(observationIndex).getOBX();
                SegmentFactoryOMLO21.createOBXSegmentIntegrateOMLO21CheckIn(obxSegmentIntegrate, omlMessage, orderIndex, observationIndex);
            }
        }

        return oml;
    }

    public OML_O21 decodeIncomingMessage(OML_O21 oml) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return OMLDecoding.decodeOML_XML(xmlParser.encode(oml));
    }
    public String convertXMLToPipeFormat(OML_O21 oml) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        return pipeParser.encode(oml);
    }


}
