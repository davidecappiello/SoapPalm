package Prometeo.HL7Palm.SegmentFactory;

import Prometeo.HL7Palm.Message.Custom.RSP_K11;
import Prometeo.HL7Palm.Segment.ZET;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.model.v25.segment.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SegmentFactoryRSPK11 {

    private static final String separator = "|";
    private static final String encodingCharacters = "^~\\&";
    private static final String applicationError = "AE";
    private static final String applicationRejected = "AR";
    public static void createMSHSegmentIntegrateRSPK11ToQBP(MSH mshSegmentIntegrate, String triggerEvent, MSH mshQBP) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        mshSegmentIntegrate.getFieldSeparator().setValue(separator);
        mshSegmentIntegrate.getEncodingCharacters().setValue(encodingCharacters);
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshQBP.getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshQBP.getReceivingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshQBP.getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshQBP.getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(formattedDateTime);
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("RSP");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(triggerEvent);
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("RSP_K11");
        //RICONTROLLARE
        mshSegmentIntegrate.getMessageControlID().setValue("");
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue("P");
        mshSegmentIntegrate.getVersionID().getVersionID().setValue("2.5");
    }

    //MODIFICARE IL PEZZO
    public static void createMSASegmentIntegrateRSPK11ToQBP(MSA msaSegmentIntegrate, Message genericMessage) throws HL7Exception {

        MSA oldMSA = (MSA) genericMessage.get("MSA");
        MSH oldMSH = (MSH) genericMessage.get("MSH");

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        msaSegmentIntegrate.getMessageControlID().setValue(oldMSH.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(applicationError) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(applicationRejected))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }

    public static void createQAKSegmentIntegrateRSPK11ToQBP(QAK qakSegmentIntegrate, QBP_Q11 qbpDecoded) throws HL7Exception {

        //CONTROLLARE IL TAG
        qakSegmentIntegrate.getQueryTag().setValue(qbpDecoded.getQPD().getQueryTag().getValue());
        qakSegmentIntegrate.getQueryResponseStatus().setValue("Esito [Tab HL7 #0208]");
        //AGGIUNGERE IL CE1-2-3
        qakSegmentIntegrate.getMessageQueryName().getIdentifier().setValue(qbpDecoded.getQPD().getMessageQueryName().getName());

    }

    public static void createSPMSegmentIntegrateRSPK11ToQBP(SPM spmSegmentIntegrate) throws HL7Exception {

        spmSegmentIntegrate.getSetIDSPM().setValue("1");
        spmSegmentIntegrate.getSpecimenID().getPlacerAssignedIdentifier().getEntityIdentifier().setValue("2");
        spmSegmentIntegrate.getSpecimenType().getIdentifier().setValue("3");
        spmSegmentIntegrate.getSpecimenType().getText().setValue("Bello");
        spmSegmentIntegrate.getSpecimenType().getNameOfCodingSystem().setValue("IT");
        spmSegmentIntegrate.getSpecimenSourceSite().getIdentifier().setValue("5");
        spmSegmentIntegrate.getSpecimenSourceSite().getText().setValue("Brutto");
        spmSegmentIntegrate.getSpecimenSourceSite().getNameOfCodingSystem().setValue("ITISTAT");
        spmSegmentIntegrate.getSpecimenCollectionDateTime().getRangeStartDateTime().getTime().setValue("20240614113000");
    }

    public static void createORCSegmentSCIntegrateRSPK11ToQBP(ORC orcSegmentSCIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        //Messaggi SC
        orcSegmentSCIntegrate.getOrc1_OrderControl().setValue("NW");
        orcSegmentSCIntegrate.getOrc4_PlacerGroupNumber().getEi1_EntityIdentifier().setValue("022");
        orcSegmentSCIntegrate.getOrc5_OrderStatus().setValue("SC");
        orcSegmentSCIntegrate.getOrc9_DateTimeOfTransaction().getTime().setValue(formattedDateTime);
    }

    public static void createORCSegmentORIntegrateRSPK11ToQBP(ORC orcSegmentORIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        orcSegmentORIntegrate.getOrc1_OrderControl().setValue("NW");
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi1_EntityIdentifier().setValue("VE");
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi2_NamespaceID().setValue("RO");
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi3_UniversalID().setValue("NRE");
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi4_UniversalIDType().setValue("NRE");
        orcSegmentORIntegrate.getOrc3_FillerOrderNumber().getEi1_EntityIdentifier().setValue("fdf");
        orcSegmentORIntegrate.getOrc3_FillerOrderNumber().getEi2_NamespaceID().setValue("D45");
        orcSegmentORIntegrate.getOrc4_PlacerGroupNumber().getEi1_EntityIdentifier().setValue("022");
        orcSegmentORIntegrate.getOrc5_OrderStatus().setValue("SC");
        orcSegmentORIntegrate.getOrc7_QuantityTiming(0).getTq4_StartDateTime().getTs1_Time().setValue("20240605");
        orcSegmentORIntegrate.getOrc9_DateTimeOfTransaction().getTime().setValue(formattedDateTime);
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn1_IDNumber().setValue("CPPDVD94C21L219F");
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn2_FamilyName().getFn1_Surname().setValue("Cappiello");
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn3_GivenName().setValue("Davide");
        orcSegmentORIntegrate.getOrc21_OrderingFacilityName(0).getXon1_OrganizationName().setValue("Amazon");
        orcSegmentORIntegrate.getOrc21_OrderingFacilityName(0).getXon10_OrganizationIdentifier().setValue("1");
    }

    public static void createTQ1SegmentIntegrateRSPK11ToQBP(TQ1 tq1SegmentIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        tq1SegmentIntegrate.getSetIDTQ1().setValue("");
        tq1SegmentIntegrate.getStartDateTime().getTime().setValue(formattedDateTime);
        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue("R");
    }

    public static void createOBRSegmentIntegrateRSPK11ToQBP(OBR obrSegmentIntegrate, ORC orcSegmentIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        obrSegmentIntegrate.getSetIDOBR().setValue("1");
        obrSegmentIntegrate.getObr2_PlacerOrderNumber().getEi1_EntityIdentifier().setValue(orcSegmentIntegrate.getOrc2_PlacerOrderNumber().getEi1_EntityIdentifier().getValue());
        obrSegmentIntegrate.getObr3_FillerOrderNumber().getEi1_EntityIdentifier().setValue(orcSegmentIntegrate.getOrc3_FillerOrderNumber().getEi1_EntityIdentifier().getValue());
        obrSegmentIntegrate.getObr4_UniversalServiceIdentifier().getCe1_Identifier().setValue("12");
        obrSegmentIntegrate.getObr4_UniversalServiceIdentifier().getCe2_Text().setValue("Prelievo");
        obrSegmentIntegrate.getObr4_UniversalServiceIdentifier().getCe6_NameOfAlternateCodingSystem().setValue("CODCATALOGO");
        obrSegmentIntegrate.getObservationDateTime().getTime().setValue(formattedDateTime);
        obrSegmentIntegrate.getObr13_RelevantClinicalInformation().setValue("Paura siringhe");
        obrSegmentIntegrate.getObr16_OrderingProvider(0).getXcn1_IDNumber().setValue("BRNFBR");
        obrSegmentIntegrate.getObr16_OrderingProvider(0).getXcn2_FamilyName().getFn1_Surname().setValue("Bruno");
        obrSegmentIntegrate.getObr16_OrderingProvider(0).getXcn3_GivenName().setValue("Fabrizio");
        obrSegmentIntegrate.getFillerField2().setValue("F");
        obrSegmentIntegrate.getObr24_DiagnosticServSectID().setValue("LAB");
        obrSegmentIntegrate.getResultStatus().setValue("F");
        obrSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(formattedDateTime);
        obrSegmentIntegrate.getCollectorSComment(0).getIdentifier().setValue("");
        obrSegmentIntegrate.getCollectorSComment(0).getText().setValue("");
        obrSegmentIntegrate.getCollectorSComment(0).getNameOfCodingSystem().setValue("SETTORE");
        obrSegmentIntegrate.getCollectorSComment(0).getAlternateIdentifier().setValue("LOINC");
        obrSegmentIntegrate.getCollectorSComment(0).getAlternateText().setValue("");
        obrSegmentIntegrate.getCollectorSComment(0).getNameOfAlternateCodingSystem().setValue("SETTORE_LOINC");
        obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getIdentifier().setValue("B");
    }

    public static void createOBXSegmentPROIntegrateRSPK11ToQBP(OBX obxSegmentPROIntegrate) throws DataTypeException {

        //Messaggi PRO
        obxSegmentPROIntegrate.getValueType().setValue("ED");
        obxSegmentPROIntegrate.getObservationIdentifier().getIdentifier().setValue("Referto");
        obxSegmentPROIntegrate.getObservationIdentifier().getAlternateIdentifier().setValue("PDF");
        obxSegmentPROIntegrate.getObservationSubID().setValue("");
        //obxSegment.getObservationValue(0).setData(); da decommentare quando avremo un PDF/P7M/CDA2
    }

    public void createOBXSegmentORIntegrateRSPK11ToQBP(OBX obxSegmentORIntegrate) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        final RSP_K11 rspK11Custom = new RSP_K11();

        //Messaggi OR
        obxSegmentORIntegrate.getSetIDOBX().parse("1"); // Progressivo del segmento

        // Tipologia del risultato:
        //“NM” – Numerico
        //“ST” – Alfanumerico
        //“CE” – Codificato
        //“ST” è usato anche nel caso di rifiuto
        //dell’esame da parte del LIS
        obxSegmentORIntegrate.getValueType().setValue("ST"); // Valore: "ST"

        // Observation Identifier
        obxSegmentORIntegrate.getObservationIdentifier().getIdentifier().setValue("PASSED"); // Codice risultato
        obxSegmentORIntegrate.getObservationIdentifier().getText().setValue("Ottimo"); // Descrizione del risultato
        obxSegmentORIntegrate.getObservationIdentifier().getNameOfCodingSystem().setValue("UTF8"); // Sistema di codifica
        obxSegmentORIntegrate.getObservationIdentifier().getAlternateIdentifier().setValue("LOINC"); // Codice della specialità (codifica LOINC)
        obxSegmentORIntegrate.getObservationIdentifier().getAlternateText().setValue("Bravo"); // Descrizione specialita
        obxSegmentORIntegrate.getObservationIdentifier().getNameOfAlternateCodingSystem().setValue("LOINC"); // Sistema di codifica. Valore: “LOINC”

        CE ce = new CE(rspK11Custom);
        ce.getIdentifier().setValue("1"); //CE.1 codice risultato
        ce.getText().setValue("Brutto"); // CE.2 Descrizione risultato
        ce.getNameOfCodingSystem().setValue("Italiano"); // CE.3S istema di codifica
        obxSegmentORIntegrate.getObservationValue(0).setData(ce); // Imposto le varibili CE in OBX.5

        obxSegmentORIntegrate.getUnits().getIdentifier().setValue("IT"); // Unita di misura

        //Intervallo di normalità del risultato
        //nel formato
        //valore_minimo;valore_massimo
        //per i casi in cui non è presente un valore
        //minimo esplicito (es. “minore di 10”) riportare
        //come valore_minimo: “INF”
        //per i casi in cui non è presente un massimo
        //(es. “maggiore di 10”), riportare come
        //valore_massimo: “SUP”
        obxSegmentORIntegrate.getReferencesRange().setValue("SUP");

        // Stato di normalità del risultato:
        //“LL” – molto basso
        //“L” – basso
        //“N” – normale
        //“H” – alto
        //“HH” – molto alto
        //“A” – alterato
        //“AA” – molto alterato
        obxSegmentORIntegrate.getAbnormalFlags(0).setValue("H");

        //Stato del risultato:
        //“F” – validato
        //“C” – correzione di un precedente valore già
        //validato
        //“D” – cancellato
        //“X’ – non eseguito
        obxSegmentORIntegrate.getObservationResultStatus().setValue("0"); // Valore: “O” (Order detail description only (no result))

        //Data di validazione del risultato
        //Formato: “yyyymmddhh24miss”
        obxSegmentORIntegrate.getDateTimeOfTheObservation().getTime().setValue(formattedDateTime);

        //Responsible Observer
        obxSegmentORIntegrate.getResponsibleObserver(0).getIDNumber().setValue("MRGNTN"); // Codice
        obxSegmentORIntegrate.getResponsibleObserver(0).getFamilyName().getSurname().setValue("Miraglia"); // Nome
        obxSegmentORIntegrate.getResponsibleObserver(0).getGivenName().setValue("Antonio"); // Cognome

        //Observation Method
        obxSegmentORIntegrate.getObservationMethod(0).getIdentifier().setValue("16"); // Codice metodo utilizzato
        obxSegmentORIntegrate.getObservationMethod(0).getText().setValue("Bello"); // Descrizione metodo
        obxSegmentORIntegrate.getObservationMethod(0).getNameOfCodingSystem().setValue("IT"); // Tipo di codifica

        obxSegmentORIntegrate.getEquipmentInstanceIdentifier(0).getEntityIdentifier().setValue("F"); // “F” – visibile su referto, “X”– non visibile su referto
    }

    public static void createSegmentZET (ZET zet) throws DataTypeException {

        zet.getBarcode().setValue("10000167-0092");
        zet.getLabelDescription().setValue(" 13X75 VIOLA EMOVES");
        zet.getEmergencyLevel().setValue("URG");
        zet.getSurname().setValue("DOE");
        zet.getNome().setValue("JOHN");
    }

}
