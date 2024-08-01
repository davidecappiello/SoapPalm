package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.datatype.*;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.WXSDOCUMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SegmentFactoryMDMT01evT11 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryMDMT01evT11(HL7Config hl7Config) {
        SegmentFactoryMDMT01evT11.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateMDMT01evT11(MSH mshSegmentIntegrate, OML_O21 omlO21) throws DataTypeException {

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getReceivingApplication());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getReceivingFacility());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlO21.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeMdm());
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventT11());
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureMDMT01evT11());
        mshSegmentIntegrate.getMessageControlID().setValue("?");
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(hl7Config.getProcessingID());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(hl7Config.getVersionID());
    }

    public static void createEVNSegmentIntegrateMDMT01evT11(EVN evnSegmentIntegrate) throws DataTypeException {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String currentTimestamp = dateFormat.format(now);

        evnSegmentIntegrate.getRecordedDateTime().getTime().setValue(currentTimestamp);
    }

    public static void createPIDSegmentIntegrateMDMT01evT11(PID pidSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PID pidSource = omlMessage.getPATIENT().getPID();

        ST targetIdentifierIDNumber = pidSegmentIntegrate.getPatientIdentifierList(0).getIDNumber();
        HD targetIdentifierAssigningAuthority = pidSegmentIntegrate.getPatientIdentifierList(0).getAssigningAuthority();
        ID targetIdentifierIdentifierTypeCode = pidSegmentIntegrate.getPatientIdentifierList(0).getIdentifierTypeCode();

        for (int i = 0; i < pidSource.getPatientIdentifierListReps(); i++) {
            CX sourceIdentifier = pidSource.getPatientIdentifierList(i);

            if(targetIdentifierIDNumber.getValue() == null && targetIdentifierAssigningAuthority.getNamespaceID().getValue() == null && targetIdentifierIdentifierTypeCode.getValue() == null) {
                targetIdentifierIDNumber.setValue(sourceIdentifier.getIDNumber().getValue());
                targetIdentifierAssigningAuthority.getNamespaceID().setValue(sourceIdentifier.getAssigningAuthority().getNamespaceID().getValue());
                targetIdentifierIdentifierTypeCode.setValue(sourceIdentifier.getIdentifierTypeCode().getValue());
            }
        }

        if(pidSource.getPatientName(0).getFamilyName().getSurname().getValue() != null)
            pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue(pidSource.getPatientName(0).getFamilyName().getSurname().getValue());
        if(pidSource.getPatientName(0).getGivenName().getValue() != null)
            pidSegmentIntegrate.getPatientName(0).getGivenName().setValue(pidSource.getPatientName(0).getGivenName().getValue());
        if(pidSource.getDateTimeOfBirth().getTime().getValue() != null)
            pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue(pidSource.getDateTimeOfBirth().getTime().getValue());
        if(pidSource.getAdministrativeSex().getValue() != null)
            pidSegmentIntegrate.getAdministrativeSex().setValue(pidSource.getAdministrativeSex().getValue());

        for (int i = 0; i < pidSource.getPatientAddressReps(); i++) {
            XAD sourceAddress = pidSource.getPatientAddress(i);
            XAD targetAddress = pidSegmentIntegrate.getPatientAddress(i);

            if(sourceAddress.getStreetAddress().getStreetName().getValue() != null)
                targetAddress.getStreetAddress().getStreetName().setValue(sourceAddress.getStreetAddress().getStreetName().getValue());
            if(sourceAddress.getCity().getValue() != null)
                targetAddress.getCity().setValue(sourceAddress.getCity().getValue());
            if(sourceAddress.getStateOrProvince().getValue() != null)
                targetAddress.getStateOrProvince().setValue(sourceAddress.getStateOrProvince().getValue());
            if(sourceAddress.getZipOrPostalCode().getValue() != null)
                targetAddress.getZipOrPostalCode().setValue(sourceAddress.getZipOrPostalCode().getValue());
            if(sourceAddress.getAddressType().getValue() != null)
                targetAddress.getAddressType().setValue(sourceAddress.getAddressType().getValue());
            if(sourceAddress.getCountyParishCode().getValue() != null)
                targetAddress.getCountyParishCode().setValue(sourceAddress.getCountyParishCode().getValue());
        }

        for (int i = 0; i < pidSource.getPhoneNumberHomeReps(); i++) {
            ST emailAddress = pidSource.getPhoneNumberHome(i).getEmailAddress();

            if(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue() != null) {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationEquipmentType().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationEquipmentType().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationUseCode().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue());
                if(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue() != null){
                    pidSegmentIntegrate.getPhoneNumberHome(i).getUnformattedTelephoneNumber().setValue(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue());
                }
            } else if(emailAddress.getValue() !=null) {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationUseCode().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationEquipmentType().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationEquipmentType().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getEmailAddress().setValue(pidSource.getPhoneNumberHome(i).getEmailAddress().getValue());
            }
        }

        for (int i = 0; i < pidSource.getCitizenshipReps(); i++) {
            CE sourceCitizenship = pidSource.getCitizenship(i);
            CE targetCitizenship = pidSegmentIntegrate.getCitizenship(i);

            if(sourceCitizenship.getIdentifier().getValue() != null)
                targetCitizenship.getIdentifier().setValue(sourceCitizenship.getIdentifier().getValue());
            if(sourceCitizenship.getText().getValue() != null)
                targetCitizenship.getText().setValue(sourceCitizenship.getText().getValue());
            if(sourceCitizenship.getNameOfCodingSystem().getValue() != null)
                targetCitizenship.getNameOfCodingSystem().setValue(sourceCitizenship.getNameOfCodingSystem().getValue());
        }
    }

    public static void createPV1SegmentIntegrateMDMT01evT11(PV1 pv1SegmentIntegrate, OML_O21 omlO21) throws DataTypeException {

        PV1 pv1Source = omlO21.getPATIENT().getPATIENT_VISIT().getPV1();

        if(pv1Source.getPatientClass().getValue() != null)
            pv1SegmentIntegrate.getPatientClass().setValue(pv1Source.getPatientClass().getValue());
        if(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue());
        if(pv1Source.getAssignedPatientLocation().getFacility().getNamespaceID().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getFacility().getNamespaceID().setValue(pv1Source.getAssignedPatientLocation().getFacility().getNamespaceID().getValue());
        if(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue());
        if(pv1Source.getAdmissionType().getValue() != null)
            pv1SegmentIntegrate.getAdmissionType().setValue(pv1Source.getAdmissionType().getValue());
        if(pv1Source.getReferringDoctor(0).getIDNumber().getValue() != null)
            pv1SegmentIntegrate.getReferringDoctor(0).getIDNumber().setValue(pv1Source.getReferringDoctor(0).getIDNumber().getValue());
        if(pv1Source.getReferringDoctor(0).getFamilyName().getSurname().getValue() != null)
            pv1SegmentIntegrate.getReferringDoctor(0).getFamilyName().getSurname().setValue(pv1Source.getReferringDoctor(0).getFamilyName().getSurname().getValue());
        if(pv1Source.getReferringDoctor(0).getGivenName().getValue() != null)
            pv1SegmentIntegrate.getReferringDoctor(0).getGivenName().setValue(pv1Source.getReferringDoctor(0).getGivenName().getValue());
        if(pv1Source.getReferringDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue() != null)
            pv1SegmentIntegrate.getReferringDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().setValue(pv1Source.getReferringDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
        if(pv1Source.getConsultingDoctor(0).getIDNumber().getValue() != null)
            pv1SegmentIntegrate.getConsultingDoctor(0).getIDNumber().setValue(pv1Source.getConsultingDoctor(0).getIDNumber().getValue());
        if(pv1Source.getConsultingDoctor(0).getFamilyName().getSurname().getValue() != null)
            pv1SegmentIntegrate.getConsultingDoctor(0).getFamilyName().getSurname().setValue(pv1Source.getConsultingDoctor(0).getFamilyName().getSurname().getValue());
        if(pv1Source.getConsultingDoctor(0).getGivenName().getValue() != null)
            pv1SegmentIntegrate.getConsultingDoctor(0).getGivenName().setValue(pv1Source.getConsultingDoctor(0).getGivenName().getValue());
        if(pv1Source.getConsultingDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue() != null)
            pv1SegmentIntegrate.getConsultingDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().setValue(pv1Source.getConsultingDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
        if(pv1Source.getVisitNumber().getIDNumber().getValue() != null)
            pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(pv1Source.getVisitNumber().getIDNumber().getValue());
        if(pv1Source.getVisitNumber().getAssigningAuthority().getNamespaceID().getValue() != null)
            pv1SegmentIntegrate.getVisitNumber().getAssigningAuthority().getNamespaceID().setValue(pv1Source.getVisitNumber().getAssigningAuthority().getNamespaceID().getValue());
        if(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue() != null)
            pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue());
        if(pv1Source.getChargePriceIndicator().getValue() != null)
            pv1SegmentIntegrate.getChargePriceIndicator().setValue(pv1Source.getChargePriceIndicator().getValue());
        if(pv1Source.getAdmitDateTime().getTime().getValue() != null)
            pv1SegmentIntegrate.getAdmitDateTime().getTime().setValue(pv1Source.getAdmitDateTime().getTime().getValue());
        if(pv1Source.getAlternateVisitID().getIDNumber().getValue() != null)
            pv1SegmentIntegrate.getAlternateVisitID().getIDNumber().setValue(pv1Source.getAlternateVisitID().getIDNumber().getValue());
        if(pv1Source.getAlternateVisitID().getAssigningAuthority().getNamespaceID().getValue() != null)
            pv1SegmentIntegrate.getAlternateVisitID().getAssigningAuthority().getNamespaceID().setValue(pv1Source.getAlternateVisitID().getAssigningAuthority().getNamespaceID().getValue());
        if(pv1Source.getAlternateVisitID().getIdentifierTypeCode().getValue() != null)
            pv1SegmentIntegrate.getAlternateVisitID().getIdentifierTypeCode().setValue(pv1Source.getAlternateVisitID().getIdentifierTypeCode().getValue());
    }

    public static void createTXASegmentIntegrateMDMT01evT11(TXA txaSegmentIntegrate, OML_O21 omlO21, WXSDOCUMENT entity) throws HL7Exception {

        txaSegmentIntegrate.getSetIDTXA().setValue(hl7Config.getSetIDTXA());
        txaSegmentIntegrate.getDocumentType().setValue(hl7Config.getDocumentTypeTXA());
        txaSegmentIntegrate.getDocumentContentPresentation().setValue(hl7Config.getDocumentContentPresentation());
        txaSegmentIntegrate.getActivityDateTime().getTime().setValue(entity.getDATA_REF());
        txaSegmentIntegrate.getUniqueDocumentNumber().getEntityIdentifier().setValue(entity.getREFID());
        txaSegmentIntegrate.getPlacerOrderNumber(0).getEntityIdentifier().setValue(omlO21.getORDER().getORC().getPlacerOrderNumber().getEntityIdentifier().getValue());
        txaSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(entity.getREFID());
        txaSegmentIntegrate.getDocumentCompletionStatus().setValue(hl7Config.getDocumentCompletionStatus());
        txaSegmentIntegrate.getDocumentAvailabilityStatus().setValue(hl7Config.getDocumentAvailabilityStatusComplete());
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getIDNumber().setValue("ID MEDICO VALIDATORE");
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getAssigningAuthority().getNamespaceID().setValue(hl7Config.getAuthenticationPersonTimeStampMF());
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getIdentifierTypeCode().setValue(hl7Config.getAuthenticationPersonTimeStampN());
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getDateTimeActionPerformed().getTime().setValue(entity.getSIGNDATE());
    }

}
