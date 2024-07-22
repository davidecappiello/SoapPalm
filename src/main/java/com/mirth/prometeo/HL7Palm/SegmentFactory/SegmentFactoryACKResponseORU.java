package com.mirth.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;

public class SegmentFactoryACKResponseORU {
    private static final String separator = "|";
    private static final String encodingCharacters = "^~\\&";
    private static final String messageCode = "ACK";
    private static final String triggerEvent = "O01";
    private static final String messageStructure = "ACK_O01";
    private static final String applicationError = "AE";

    public static void createMSHSegmentIntegrateACKResponse(MSH mshSegmentIntegrate, String hl7Message) throws HL7Exception {


            String[] segments = hl7Message.split("\r");
            String mshSegment = null;

            for (String segment : segments) {
                if (segment.startsWith("MSH")) {
                    mshSegment = segment;
                    break;
                }
            }

            if (mshSegment == null) {
                throw new HL7Exception("MSH segment not found in the HL7 message");
            }

            String[] fields = mshSegment.split("\\|");

//            for(int i = 0; i < fields.length; i++)
//            {
//                System.out.println(i + " "+ fields[i]);
//            }

            if (fields.length < 12) {
                throw new HL7Exception("Invalid MSH segment: missing required fields");
            }

            MSH msh = mshSegmentIntegrate;
            msh.getFieldSeparator().setValue(separator);
            msh.getEncodingCharacters().setValue(encodingCharacters);

            msh.getSendingApplication().getNamespaceID().setValue(fields[5]);

            String[] SendingFacility = fields[4].split("\\^");
            msh.getSendingFacility().getNamespaceID().setValue(SendingFacility[0]);
            msh.getSendingFacility().getUniversalID().setValue(SendingFacility[1]);
            msh.getSendingFacility().getUniversalIDType().setValue(SendingFacility[2]);

            msh.getReceivingApplication().getNamespaceID().setValue(fields[3]);


            msh.getDateTimeOfMessage().getTime().setValue(fields[6]);
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(messageCode);
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(triggerEvent);
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(messageStructure);
            msh.getMessageControlID().setValue(fields[10]);
            msh.getProcessingID().getProcessingID().setValue(fields[11]);
            msh.getVersionID().getVersionID().setValue(fields[12]);

    }

    public static void createMSASegmentIntegrateACKResponse(MSA msaSegmentIntegrate, MSH mshSegmentIntegrate) throws HL7Exception {

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(applicationError);
        msaSegmentIntegrate.getMessageControlID().setValue(mshSegmentIntegrate.getMessageControlID().getValue());

    }

    public static void createERRSSegmentIntegrateACKResponse(ERR errSegmentIntegrate)throws HL7Exception {

        errSegmentIntegrate.getHL7ErrorCode().getIdentifier().setValue("1");
        errSegmentIntegrate.getHL7ErrorCode().getText().setValue("Error: Error decoding ORU message");



    }
}
