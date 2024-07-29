package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentFactoryACKErrorOMLO21FromPSDecodingError {

    private static HL7Config hl7Config = null;
    private static final Util util = new Util();

    @Autowired
    public SegmentFactoryACKErrorOMLO21FromPSDecodingError(HL7Config hl7Config) {
        SegmentFactoryACKErrorOMLO21FromPSDecodingError.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateACKResponse(MSH mshSegmentIntegrate, String hl7Message) throws DataTypeException {

        String ts1 = util.extractTS1Value(hl7Message);
        String msh10 = util.extractMSH10Value(hl7Message);

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getReceivingApplication());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getReceivingFacility());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(ts1);
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageCodeAck());
        mshSegmentIntegrate.getMessageControlID().setValue(msh10);
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(hl7Config.getProcessingID());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(hl7Config.getVersionID());
    }

    public static void createMSHSegmentIntegrateACKResponseTD(MSH mshSegmentIntegrate, String hl7Message) throws DataTypeException {

        String ts1 = util.extractTS1Value(hl7Message);
        String msh10 = util.extractMSH10Value(hl7Message);

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getReceivingApplication());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getReceivingFacility());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(ts1);
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageCodeAck());
        mshSegmentIntegrate.getMessageControlID().setValue(msh10);
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(hl7Config.getProcessingID());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(hl7Config.getVersionID());
    }

    public static void createMSASegmentIntegrateACKResponse(MSA msaSegmentIntegrate, String hl7Message, Exception e) throws DataTypeException {

        String msh10 = util.extractMSH10Value(hl7Message);

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(hl7Config.getApplicationError());
        msaSegmentIntegrate.getMessageControlID().setValue(msh10);
        msaSegmentIntegrate.getTextMessage().setValue(e.getMessage());
    }
}
