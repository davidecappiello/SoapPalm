package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import it.prometeo.Configuration.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentFactoryACKResponseORLO22 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryACKResponseORLO22(HL7Config hl7Config) {
        SegmentFactoryACKResponseORLO22.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateACKResponse(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(omlMessage.getMSH().getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(omlMessage.getMSH().getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(omlMessage.getMSH().getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlMessage.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOrl());
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO22());
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORLO22());
        mshSegmentIntegrate.getMessageControlID().setValue(omlMessage.getMSH().getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(omlMessage.getMSH().getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(omlMessage.getMSH().getVersionID().getVersionID().getValue());
    }

    public static void createMSASegmentIntegrateACKResponse(MSA msaSegmentIntegrate, Message genericMessage) throws HL7Exception {

        MSA oldMSA = (MSA) genericMessage.get("MSA");

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        msaSegmentIntegrate.getMessageControlID().setValue(oldMSA.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationError()) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationRejected()))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }
}
