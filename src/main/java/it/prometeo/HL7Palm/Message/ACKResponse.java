package it.prometeo.HL7Palm.Message;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryACKErrorOMLO21FromPSDecodingError;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryACKResponseORLO22;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryACKResponseORU;

public class ACKResponse {

    private final ACK ack = new ACK();
    private final PipeParser pipeParser = new PipeParser(new CanonicalModelClassFactory("2.5"));
    private final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

    public ACK generateACKResponseORLO22(Message genericMessage, OML_O21 omlMessage) throws HL7Exception {

        MSH mshSegmentIntegrate = ack.getMSH();
        SegmentFactoryACKResponseORLO22.createMSHSegmentIntegrateACKResponse(mshSegmentIntegrate, omlMessage);

        MSA msaSegmentIntegrate = ack.getMSA();
        SegmentFactoryACKResponseORLO22.createMSASegmentIntegrateACKResponse(msaSegmentIntegrate, genericMessage);

        return ack;
    }

    public ACK generateACKResponseORUError(String hl7Message) throws HL7Exception {

        MSH mshSegmentIntegrate = ack.getMSH();
        SegmentFactoryACKResponseORU.createMSHSegmentIntegrateACKResponse(mshSegmentIntegrate, hl7Message);

        MSA msaSegmentIntegrate = ack.getMSA();
        SegmentFactoryACKResponseORU.createMSASegmentIntegrateACKResponse(msaSegmentIntegrate, mshSegmentIntegrate);

        ERR errSegmentIntegrate = ack.getERR();
        SegmentFactoryACKResponseORU.createERRSSegmentIntegrateACKResponse(errSegmentIntegrate);

        return ack;
    }

    public ACK generateACKErrorOMLO21FromPSDecodingError(String hl7Message, Exception e) throws DataTypeException {

        MSH mshSegmentIntegrate = ack.getMSH();
        SegmentFactoryACKErrorOMLO21FromPSDecodingError.createMSHSegmentIntegrateACKResponse(mshSegmentIntegrate, hl7Message);

        MSA msaSegmentIntegrate = ack.getMSA();
        SegmentFactoryACKErrorOMLO21FromPSDecodingError.createMSASegmentIntegrateACKResponse(msaSegmentIntegrate, hl7Message, e);

        return ack;
    }

    public String transformACKIntoStringXML(ACK ackMessage) throws HL7Exception {

        String pipeMessage = pipeParser.encode(ackMessage);
        Message message = pipeParser.parse(pipeMessage);

        return xmlParser.encode(message);
    }
}
