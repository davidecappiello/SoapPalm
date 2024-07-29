package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SegmentFactoryMDMT01T11 {

    static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryMDMT01T11(HL7Config hl7Config) {
        SegmentFactoryORLO22.hl7Config = hl7Config;
    }

    public static void createMShSegmentIntegrateMDMT02TriggerT11(MSH mshSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue("MySendingApp"); // Receiving Application HD.1 Applicazione inviante
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue("MyReceivingApp"); // Sending Facility HD.1 Ditta inviante
        mshSegmentIntegrate.getMsh5_ReceivingApplication().getNamespaceID().setValue("Applicazione ricevente"); // MSH.5 227 HD Receiving Application HD.1 Applicazione ricevente
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue("Ditta ricevente");
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(data); // DateTime Of Message TS.1 Data del messaggio. Formato: “yyyymmddhh24miss”

        // Messagge type
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("MDM");  //MSG.1 Valore: “OUL”, “ACK”
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("T11");//MSG.2 Valore: “R22”
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("MDM_T11");//MSG.3 Valore: OUL_R22”, “ACK”

        mshSegmentIntegrate.getMessageControlID().setValue("getMessageControlID"); // Message Control ID Identificativo univoco del messaggio
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue("P"); // Processing ID PT.1 Valore: “P”
        mshSegmentIntegrate.getVersionID().getVersionID().setValue("2.5");
    }

    public static void createEVNSegmentIntegrateMDMT02TriggerT11(EVN evnSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        evnSegmentIntegrate.getRecordedDateTime().getTime().setValue(data);
    }

    public static void createPIDSegmentIntegrateMDMT02TriggerT11(PID pidSegmentIntegrate) throws HL7Exception {

        pidSegmentIntegrate.getPatientIdentifierList(0).getIDNumber().setValue("CPPDVD94C21L219F");
        pidSegmentIntegrate.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue("Ministero Finanze");
        pidSegmentIntegrate.getPatientIdentifierList(0).getIdentifierTypeCode().setValue("NNITA");
        pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue("Cappiello");
        pidSegmentIntegrate.getPatientName(0).getGivenName().setValue("Davide");
        pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue("19940321030000"); //DA CAPIRE ANCHE IL DISCORSO DATE DA DOVE LE DOBBIAMO PRENDERE
        pidSegmentIntegrate.getAdministrativeSex().setValue("M");
        pidSegmentIntegrate.getPatientAddress(0).getCity().setValue("Torino");
        pidSegmentIntegrate.getPatientAddress(0).getCountry().setValue("Italia");
        pidSegmentIntegrate.getPatientAddress(0).getAddressType().setValue("Residenza");
        pidSegmentIntegrate.getPatientAddress(0).getCountyParishCode().setValue("IT");
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationUseCode().setValue("NET");
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationEquipmentType().setValue("Internet");
        pidSegmentIntegrate.getPhoneNumberHome(0).getUnformattedTelephoneNumber().setValue("davide.cappiello@we-plus.eu");
        pidSegmentIntegrate.getCitizenship(0).getAlternateIdentifier().setValue("IT21");
        pidSegmentIntegrate.getCitizenship(0).getAlternateText().setValue("Italiana");
        pidSegmentIntegrate.getCitizenship(0).getNameOfAlternateCodingSystem().setValue("ISTAT");
    }

    public static void createPV1SegmentIntegrateMDMT02TriggerT11(PV1 pv1SegmentIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        //Regime
        //Valore:
        //“I” per Ordinario,
        //“D” per DayHospital,
        //“O” per Esterno (SSN)
        //“N” per Esterno (senza prescrizione)
        //“S” per Screening
        //“PS” per Pronto Soccorso
        pv1SegmentIntegrate.getPatientClass().setValue("I");
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue("");
        pv1SegmentIntegrate.getAssignedPatientLocation().getFacility().getNamespaceID().setValue("");
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue("");
        pv1SegmentIntegrate.getAdmissionType().setValue("1");
        pv1SegmentIntegrate.getReferringDoctor(0).getIDNumber().setValue("BRNFBR");
        pv1SegmentIntegrate.getReferringDoctor(0).getFamilyName().getSurname().setValue("Bruno");
        pv1SegmentIntegrate.getReferringDoctor(0).getGivenName().setValue("Fabrizio");
        pv1SegmentIntegrate.getReferringDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().setValue("Antonio");
        pv1SegmentIntegrate.getConsultingDoctor(0).getIDNumber().setValue("MRGNTN");
        pv1SegmentIntegrate.getConsultingDoctor(0).getFamilyName().getSurname().setValue("Miraglia");
        pv1SegmentIntegrate.getConsultingDoctor(0).getGivenName().setValue("Antonio");
        pv1SegmentIntegrate.getConsultingDoctor(0).getSecondAndFurtherGivenNamesOrInitialsThereof().setValue("CODMEDCOMP");
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue("89");
        pv1SegmentIntegrate.getVisitNumber().getAssigningAuthority().getNamespaceID().setValue("Codice presidio");
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue("ADT");
        pv1SegmentIntegrate.getChargePriceIndicator().setValue("PIP");
        pv1SegmentIntegrate.getAdmitDateTime().getTime().setValue(formattedDateTime);
        pv1SegmentIntegrate.getAlternateVisitID().getIDNumber().setValue("CODUNIENTECLI");
        pv1SegmentIntegrate.getAlternateVisitID().getAssigningAuthority().getNamespaceID().setValue("Aosp");
        pv1SegmentIntegrate.getAlternateVisitID().getIdentifierTypeCode().setValue("NOSOLOGICO");
    }

    public static void createTXASegmentIntegrateMDMT02TriggerT11(TXA txaSegmentIntegrate) throws HL7Exception {

        txaSegmentIntegrate.getSetIDTXA().setValue("1");
        txaSegmentIntegrate.getDocumentType().setValue("LAB");
        txaSegmentIntegrate.getDocumentContentPresentation().setValue("Multipart");
        txaSegmentIntegrate.getActivityDateTime().getTime().setValue("20240613");
        txaSegmentIntegrate.getEditDateTime(0).getTime().setValue("20240613");
        txaSegmentIntegrate.getUniqueDocumentNumber().getEntityIdentifier().setValue("15T01");
        //txaSegmentIntegrate.getParentDocumentNumber().getEntityIdentifier().setValue(""); Codice del referto da sostituire per il messaggio T10
        txaSegmentIntegrate.getPlacerOrderNumber(0).getEntityIdentifier().setValue("AccessionNumber");
        txaSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue("Codice prenotazione/accettazione");
        txaSegmentIntegrate.getDocumentCompletionStatus().setValue("LA");
        txaSegmentIntegrate.getDocumentAvailabilityStatus().setValue("CM");
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getIDNumber().setValue("CODFISC Medico validatore");
        txaSegmentIntegrate.getAuthenticationPersonTimeStamp(0).getAssigningAuthority().getNamespaceID().setValue("MINISTERO FINANZE");
    }
}
