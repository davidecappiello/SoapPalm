package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.v25.datatype.*;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.WXSDOCUMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class SegmentFactoryMDMT02evT02 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryMDMT02evT02(HL7Config hl7Config) {
        SegmentFactoryMDMT02evT02.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateMDMT02evT02(MSH mshSegmentIntegrate, OML_O21 omlO21) throws DataTypeException {

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getReceivingApplication());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getReceivingFacility());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlO21.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeMdm());
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventT02());
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureMDMT02evT02());
        mshSegmentIntegrate.getMessageControlID().setValue("?");
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(hl7Config.getProcessingID());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(hl7Config.getVersionID());
    }

    public static void createEVNSegmentIntegrateMDMT02evT02(EVN evnSegmentIntegrate) throws DataTypeException {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String currentTimestamp = dateFormat.format(now);

        evnSegmentIntegrate.getRecordedDateTime().getTime().setValue(currentTimestamp);
    }

    public static void createPIDSegmentIntegrateMDMT02evT02(PID pidSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

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

    public static void createPV1SegmentIntegrateMDMT02evT02(PV1 pv1SegmentIntegrate, OML_O21 omlO21) throws DataTypeException {

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

    public static void createORCSegmentIntegrateMDMT02evT02(ORC orcSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {

        OML_O21_ORDER order = omlMessage.getORDER(index);
        ORC orcSource = order.getORC();

        if(orcSource.getOrderControl().getValue() != null)
            orcSegmentIntegrate.getOrderControl().setValue(hl7Config.getCheckIn());
        if(orcSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(orcSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        if(orcSource.getPlacerOrderNumber().getNamespaceID().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(orcSource.getPlacerOrderNumber().getNamespaceID().getValue());
        if(orcSource.getPlacerOrderNumber().getUniversalID().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(orcSource.getPlacerOrderNumber().getUniversalID().getValue());
        if(orcSource.getPlacerOrderNumber().getUniversalIDType().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(orcSource.getPlacerOrderNumber().getUniversalIDType().getValue());
        if(orcSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(orcSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        if(orcSource.getPlacerGroupNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(orcSource.getPlacerGroupNumber().getEntityIdentifier().getValue());
        if(orcSource.getOrderStatus().getValue() != null)
            orcSegmentIntegrate.getOrderStatus().setValue(hl7Config.getOrderStatusMDMT02evT02());
        if(orcSource.getQuantityTiming(0).getStartDateTime().getTime().getValue() != null)
            orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(orcSource.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        if(orcSource.getQuantityTiming(0).getPriority().getValue() != null)
            orcSegmentIntegrate.getQuantityTiming(0).getPriority().setValue(orcSource.getQuantityTiming(0).getPriority().getValue());
        if(orcSource.getDateTimeOfTransaction().getTime().getValue() != null)
            orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(orcSource.getDateTimeOfTransaction().getTime().getValue());
        if(orcSource.getEnteredBy(0).getIDNumber().getValue() != null)
            orcSegmentIntegrate.getEnteredBy(0).getIDNumber().setValue(orcSource.getEnteredBy(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(orcSource.getOrderingFacilityName(0).getOrganizationName().getValue() != null)
            orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(orcSource.getOrderingFacilityName(0).getOrganizationName().getValue());
        if(orcSource.getOrderingFacilityName(0).getOrganizationIdentifier().getValue() != null)
            orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(orcSource.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
    }

    public static void createOBRSegmentIntegrateMDMT02evT02(OBR obrSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {

        if (index < omlMessage.getORDERReps()) {
            OML_O21_ORDER observationRequest = omlMessage.getORDER(index);
            OBR obrSource = observationRequest.getOBSERVATION_REQUEST().getOBR();

            if(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
                obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
            if(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
                obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
            if(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
            if(obrSource.getUniversalServiceIdentifier().getText().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
            if(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
            if(obrSource.getRelevantClinicalInformation().getValue() != null)
                obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
            if(obrSource.getOrderingProvider(0).getIDNumber().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(obrSource.getOrderingProvider(0).getIDNumber().getValue());
            if(obrSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(obrSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
            if(obrSource.getOrderingProvider(0).getGivenName().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(obrSource.getOrderingProvider(0).getGivenName().getValue());
            if(obrSource.getPlacerField1().getValue() != null)
                obrSegmentIntegrate.getPlacerField1().setValue(obrSource.getPlacerField2().getValue());
            if(obrSource.getResultsRptStatusChngDateTime().getTime().getValue() != null)
                obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
            if(obrSource.getTransportationMode().getValue() != null)
                obrSegmentIntegrate.getTransportationMode().setValue(obrSource.getTransportationMode().getValue());
            if(obrSource.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue() != null)
                obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getIdentifier().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue());
            if(obrSource.getPlacerSupplementalServiceInformation(0).getText().getValue() != null)
                obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getText().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getText().getValue());
            if(obrSource.getFillerSupplementalServiceInformation(0).getIdentifier().getValue() != null)
                obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getIdentifier().setValue(obrSource.getFillerSupplementalServiceInformation(0).getIdentifier().getValue());
            if(obrSource.getFillerSupplementalServiceInformation(0).getText().getValue() != null)
                obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getText().setValue(obrSource.getFillerSupplementalServiceInformation(0).getText().getValue());
        } else {
            throw new IllegalArgumentException("Index out of bounds for observation requests in OML message");
        }
    }

    public static void createTXASegmentIntegrateMDMT02evT02(TXA txaSegmentIntegrate, OML_O21 omlO21, WXSDOCUMENT entity) throws HL7Exception {

        txaSegmentIntegrate.getSetIDTXA().setValue(hl7Config.getSetIDTXA());
        txaSegmentIntegrate.getDocumentType().setValue(hl7Config.getDocumentTypeTXA());
        txaSegmentIntegrate.getDocumentContentPresentation().setValue(hl7Config.getDocumentContentPresentation());
        txaSegmentIntegrate.getActivityDateTime().getTime().setValue(entity.getDATA_REF());
        txaSegmentIntegrate.getEditDateTime(0).getTime().setValue(entity.getDATA_REF());
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

    public static void createOBXSegmentIntegrateMDMT02ev02(OBX obxSegmentIntegrate, OML_O21 omlO21, WXSDOCUMENT entity) throws HL7Exception, SQLException, IOException {

        Blob documentBlob = entity.getDOCUMENT();
        byte[] blobAsBytes = convertBlobToBytes(documentBlob);
        ED ed = new ED(obxSegmentIntegrate.getMessage());
        ed.getData().setValue(Arrays.toString(blobAsBytes));
        ed.getEncoding().setValue("B64");

        obxSegmentIntegrate.getValueType().setValue(hl7Config.getValueTypeOBX());
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue(hl7Config.getObservationIdentifierCE1());
        obxSegmentIntegrate.getObservationIdentifier().getAlternateIdentifier().setValue(hl7Config.getObservationIdentifierCE4());
        obxSegmentIntegrate.getObservationSubID().setValue(entity.getREFID());
        obxSegmentIntegrate.getObservationValue(0).setData(ed);
        obxSegmentIntegrate.getObservationResultStatus().setValue(omlO21.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getObservationResultStatus().getValue());
        obxSegmentIntegrate.getDateTimeOfTheObservation().getTime().setValue(entity.getDATA_REF());
        obxSegmentIntegrate.getProducerSID().getIdentifier().setValue("CUE");
        obxSegmentIntegrate.getProducerSID().getText().setValue("DUE");
        obxSegmentIntegrate.getResponsibleObserver(0).getIDNumber().setValue("CODICE FISCALE");
        obxSegmentIntegrate.getResponsibleObserver(0).getFamilyName().getSurname().setValue("COGNOME");
        obxSegmentIntegrate.getResponsibleObserver(0).getGivenName().setValue("NOME");
        obxSegmentIntegrate.getResponsibleObserver(0).getAssigningAuthority().getNamespaceID().setValue(hl7Config.getResponsibleObserveHD1());
        obxSegmentIntegrate.getResponsibleObserver(0).getAssigningAuthority().getUniversalID().setValue(hl7Config.getResponsibleObserveHD2());
    }

    private static byte[] convertBlobToBytes(Blob blob) throws SQLException, IOException {
        try (InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }

}
