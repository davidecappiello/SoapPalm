package Prometeo.HL7Palm.Message;

import Prometeo.HL7Palm.SegmentFactory.SegmentFactoryACKResponseORLO22;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;

public class ACKResponse {

    private final ACK ack = new ACK();

    public ACK generateACKResponseORLO22(Message genericMessage, OML_O21 omlMessage) throws HL7Exception {

        MSH mshSegmentIntegrate = ack.getMSH();
        SegmentFactoryACKResponseORLO22.createMSHSegmentIntegrateACKResponse(mshSegmentIntegrate, omlMessage);

        MSA msaSegmentIntegrate = ack.getMSA();
        SegmentFactoryACKResponseORLO22.createMSASegmentIntegrateACKResponse(msaSegmentIntegrate, genericMessage);

        return ack;
    }
}
