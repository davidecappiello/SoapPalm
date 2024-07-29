package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SegmentFactoryMDMT02T02 {

    static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryMDMT02T02(HL7Config hl7Config) {
        SegmentFactoryORLO22.hl7Config = hl7Config;
    }


    public static void createMSHSegmentIntegrateMDMT02(MSH mshSegmentIntegrate) throws HL7Exception {

        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue("1");
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue("LIS");
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue("Middleware");
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue("Siena");
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(hl7DateFormat.getCalendar());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("MDM");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("T02");
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("MDM_T02");
        mshSegmentIntegrate.getMessageControlID().setValue("MD");
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue("P");
        mshSegmentIntegrate.getVersionID().getVersionID().setValue("2.5");
    }

    public static void createEVNSegmentIntegrateMDMT02(EVN evnSegmentIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();

        evnSegmentIntegrate.getRecordedDateTime().getTime().setValue(Calendar.getInstance());
    }

    public static void createPIDSegmentIntegrateMDMT02(PID pidSegmentIntegrate) throws HL7Exception {  //DA RIGUARDARE PER SELEZIONE MULTIPLA DI SOTTOCAMPI

        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

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
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationEquipmentType().setValue("PH");
        pidSegmentIntegrate.getPhoneNumberHome(0).getUnformattedTelephoneNumber().setValue("011");
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationEquipmentType().setValue("CP");
        pidSegmentIntegrate.getPhoneNumberHome(0).getUnformattedTelephoneNumber().setValue("34887");
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationUseCode().setValue("NET");
        pidSegmentIntegrate.getPhoneNumberHome(0).getTelecommunicationEquipmentType().setValue("Internet");
        pidSegmentIntegrate.getPhoneNumberHome(0).getUnformattedTelephoneNumber().setValue("davide.cappiello@we-plus.eu");
        pidSegmentIntegrate.getCitizenship(0).getAlternateIdentifier().setValue("IT21");
        pidSegmentIntegrate.getCitizenship(0).getAlternateText().setValue("Italiana");
        pidSegmentIntegrate.getCitizenship(0).getNameOfAlternateCodingSystem().setValue("ISTAT");
    }

    public static void createPV1SegmentIntegrateMDMT02(PV1 pv1SegmentIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        pv1SegmentIntegrate.getPatientClass().setValue("PS");
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue("PS.1");
        pv1SegmentIntegrate.getAssignedPatientLocation().getFacility().getNamespaceID().setValue("Presidio");
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue("Posto fatiscente");
        pv1SegmentIntegrate.getAdmissionType().setValue("2");
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

    public static void createORCSegmentIntegrateMDMT02(ORC orcSegmentIntegrate) throws HL7Exception {

        orcSegmentIntegrate.getOrderControl().setValue("SC");
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue("OBR-2");
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue("12");
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue("NRE");
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue("NRE");
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue("13.PRESTEST");
        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue("SEGORC=");
        orcSegmentIntegrate.getOrderStatus().setValue("CM");
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue("20240613151900");
        orcSegmentIntegrate.getQuantityTiming(0).getPriority().setValue("E");
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue("20240613152056");
        orcSegmentIntegrate.getEnteredBy(0).getIDNumber().setValue("Anna");
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue("BRNFBR");
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue("Bruno");
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue("Fabrizio");
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue("WEPLUS");
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue("PRTCLE");
    }

    public static void createOBRSegmentIntegrateMDMT02(OBR obrSegmentIntegrate) throws HL7Exception {

        obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue("ORC-2");
        obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue("COD14");
        obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue("CODPREST");
        obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue("Descrizione");
        obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue("CODCATALOGO");
        obrSegmentIntegrate.getRelevantClinicalInformation().setValue("Quesito diagnostico");
        obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue("BRNFBR");
        obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue("Bruno");
        obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue("Fabrizio");
        obrSegmentIntegrate.getPlacerField1().setValue("Note");
        obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue("20240614160000");
        obrSegmentIntegrate.getTransportationMode().setValue("Piedi");
        obrSegmentIntegrate.getReasonForStudy(0).getText().setValue("Nota richiesta");
        obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getIdentifier().setValue("DataPresc");
        obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getText().setValue("20240612");
        obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getIdentifier().setValue("CODSTRUEROG");
        obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getText().setValue("Descrizione struttura erogante");
    }

    public static void createTXASegmentIntegrateMDMT02(TXA txaSegmentIntegrate) throws HL7Exception {

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

    public static void createOBXReportSegmentIntegrateMDMT02(OBX obxSegmentIntegrate) throws HL7Exception {

        obxSegmentIntegrate.getValueType().setValue("ED");
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue("Referto");
        obxSegmentIntegrate.getObservationIdentifier().getAlternateIdentifier().setValue("PDF");
        obxSegmentIntegrate.getObservationSubID().setValue("Identificativo referto");
        //obxSegmentIntegrate.getObservationValue(0).getData(); ----> Contiene il documento in formato PDF o P7M o CDA2 secondo le specifiche standard
        obxSegmentIntegrate.getObservationResultStatus().setValue("U");
        obxSegmentIntegrate.getDateTimeOfTheObservation().getTime().setValue("20240613165500");
        obxSegmentIntegrate.getProducerSID().getIdentifier().setValue("1UE");
        obxSegmentIntegrate.getProducerSID().getText().setValue("Descrizione UE");
        obxSegmentIntegrate.getResponsibleObserver(0).getIDNumber().setValue("CPPDVD");
        obxSegmentIntegrate.getResponsibleObserver(0).getFamilyName().getSurname().setValue("Cappiello");
        obxSegmentIntegrate.getResponsibleObserver(0).getGivenName().setValue("Davide");
        obxSegmentIntegrate.getResponsibleObserver(0).getAssigningAuthority().getNamespaceID().setValue("MinFin");
        obxSegmentIntegrate.getResponsibleObserver(0).getAssigningAuthority().getUniversalID().setValue("Ministero Finanze");
        obxSegmentIntegrate.getResponsibleObserver(0).getIdentifierTypeCode().setValue("CF");
    }

    public static void createOBXSegmentIntegrateMDMT02ConsentFSEPubblication(OBX obxSegmentIntegrate) throws HL7Exception {

        obxSegmentIntegrate.getSetIDOBX().setValue("0");
        obxSegmentIntegrate.getValueType().setValue("ST");
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue("ConsPubFSE");
        obxSegmentIntegrate.getObservationIdentifier().getText().setValue("Consenso alla pubblicazione su FSE");
        //obxSegmentIntegrate.getObservationValue(0).getData().      Da controllare perchè non mi fa inserire niente.. Valori 0 o 1 per gestire il consenso
    }

    public static void createOBXSegmentIntegrateMDMT02ReportCollectionMode(OBX obxSegmentIntegrate) throws HL7Exception {

        obxSegmentIntegrate.getSetIDOBX().setValue("0");
        obxSegmentIntegrate.getValueType().setValue("ST");
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue("ModalitàRitiroReferto");
        obxSegmentIntegrate.getObservationIdentifier().getText().setValue("Modalità ritiro referto");
        //obxSegmentIntegrate.getObservationValue(0).getData().    Da controllare perchè non mi fa inserire niente.. Valori -1 o 1 o 2 o 0
    }

    public static void createNTESegmentIntegrateMDMT02TriggerT02(NTE nteSegmentIntegrate) throws HL7Exception {

        nteSegmentIntegrate.getComment(0).setValue("Comment");
    }

}
