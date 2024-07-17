package Prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;

public class SegmentFactoryACKResponseORLO22 {

    private static final String separator = "|";
    private static final String encodingCharacters = "^~\\&";
    private static final String messageCode = "ORL";
    private static final String triggerEvent = "O22";
    private static final String messageStructure = "ORL_O22";
    private static final String applicationError = "AE";
    private static final String applicationRejected = "AR";

    public static void createMSHSegmentIntegrateACKResponse(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        mshSegmentIntegrate.getFieldSeparator().setValue(separator);
        mshSegmentIntegrate.getEncodingCharacters().setValue(encodingCharacters);
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(omlMessage.getMSH().getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(omlMessage.getMSH().getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(omlMessage.getMSH().getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlMessage.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(messageCode);
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(triggerEvent);
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(messageStructure);
        mshSegmentIntegrate.getMessageControlID().setValue(omlMessage.getMSH().getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(omlMessage.getMSH().getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(omlMessage.getMSH().getVersionID().getVersionID().getValue());
    }

    public static void createMSASegmentIntegrateACKResponse(MSA msaSegmentIntegrate, Message genericMessage) throws HL7Exception {

        MSA oldMSA = (MSA) genericMessage.get("MSA");

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        msaSegmentIntegrate.getMessageControlID().setValue(oldMSA.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(applicationError) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(applicationRejected))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }
}
