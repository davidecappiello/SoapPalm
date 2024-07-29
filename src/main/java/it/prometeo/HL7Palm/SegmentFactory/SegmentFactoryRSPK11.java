package it.prometeo.HL7Palm.SegmentFactory;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.PipeParser;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.HL7Palm.Message.Custom.RSP_K11;
import it.prometeo.HL7Palm.Segment.ZET;
import it.prometeo.Repository.MessageEventDao;
import it.prometeo.Repository.MessageSegmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public class SegmentFactoryRSPK11 {

    @Autowired
    private static MessageEventDao eventDao;
    @Autowired
    private static MessageSegmentDao messageSegmentDao;

    private static PipeParser pipeParser = new PipeParser();


    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryRSPK11(HL7Config hl7Config) {
        SegmentFactoryRSPK11.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateRSPK11ToQBP(MSH mshSegmentIntegrate, String triggerEvent, MSH mshQBP) throws HL7Exception {

        Calendar dateTimeOfMessage = Calendar.getInstance();
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDateTime = hl7DateFormat.format(dateTimeOfMessage.getTime());

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
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
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationError()) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationRejected()))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }

    public static void createMSASegmentIntegrateSLI(MSA msaSegmentIntegrate, QBP_Q11 qbpDecoded, boolean flag) throws HL7Exception {

        MSH oldMSH = qbpDecoded.getMSH();

        if(flag == true) {
            msaSegmentIntegrate.getAcknowledgmentCode().setValue(hl7Config.getApplicationAccepted());
        } else {
            msaSegmentIntegrate.getAcknowledgmentCode().setValue(hl7Config.getApplicationError());
        }
        msaSegmentIntegrate.getMessageControlID().setValue(oldMSH.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationError()) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationRejected()))) {
            msaSegmentIntegrate.getTextMessage().setValue(hl7Config.getApplicationError());
        }
    }

    public static void createQAKSegmentIntegrateRSPK11ToQBP(QAK qakSegmentIntegrate, QBP_Q11 qbpDecoded) throws HL7Exception {

        qakSegmentIntegrate.getQueryTag().setValue(qbpDecoded.getQPD().getQueryTag().getValue());
        qakSegmentIntegrate.getQueryResponseStatus().setValue("Esito [Tab HL7 #0208]");
        qakSegmentIntegrate.getMessageQueryName().getIdentifier().setValue(qbpDecoded.getQPD().getMessageQueryName().getText().getValue());

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

    public static void createORCSegmentSCIntegrateRSPK11ToQBP(ORC orcSegmentSCIntegrate, ORC orcFromOML) throws HL7Exception {

        orcSegmentSCIntegrate.getOrc1_OrderControl().setValue(orcFromOML.getOrc1_OrderControl().getValue());
        orcSegmentSCIntegrate.getOrc4_PlacerGroupNumber().getEi1_EntityIdentifier().setValue(orcFromOML.getPlacerGroupNumber().getEntityIdentifier().getValue());
        orcSegmentSCIntegrate.getOrc5_OrderStatus().setValue(orcFromOML.getOrderStatus().getValue());
        orcSegmentSCIntegrate.getOrc9_DateTimeOfTransaction().getTime().setValue(orcFromOML.getDateTimeOfTransaction().getTime().getValue());
    }

    public static void createORCSegmentORIntegrateRSPK11ToQBP(ORC orcSegmentORIntegrate, Message oml) throws HL7Exception {

        ORC orc = (ORC) oml.get("ORC");

        orcSegmentORIntegrate.getOrc1_OrderControl().setValue("SC");
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi1_EntityIdentifier().setValue(orc.getPlacerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi2_NamespaceID().setValue(orc.getOrc2_PlacerOrderNumber().getEi2_NamespaceID().getValue());
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi3_UniversalID().setValue(orc.getOrc2_PlacerOrderNumber().getEi3_UniversalID().getValue());
        orcSegmentORIntegrate.getOrc2_PlacerOrderNumber().getEi4_UniversalIDType().setValue(orc.getOrc2_PlacerOrderNumber().getEi4_UniversalIDType().getValue());
        orcSegmentORIntegrate.getOrc3_FillerOrderNumber().getEi1_EntityIdentifier().setValue(orc.getOrc3_FillerOrderNumber().getEi1_EntityIdentifier().getValue());
        orcSegmentORIntegrate.getOrc3_FillerOrderNumber().getEi2_NamespaceID().setValue(orc.getOrc3_FillerOrderNumber().getEi2_NamespaceID().getValue());
        orcSegmentORIntegrate.getOrc4_PlacerGroupNumber().getEi1_EntityIdentifier().setValue(orc.getOrc4_PlacerGroupNumber().getEi1_EntityIdentifier().getValue());
        orcSegmentORIntegrate.getOrc5_OrderStatus().setValue(orc.getOrc5_OrderStatus().getValue());
        orcSegmentORIntegrate.getOrc7_QuantityTiming(0).getTq4_StartDateTime().getTs1_Time().setValue(orc.getOrc7_QuantityTiming(0).getTq4_StartDateTime().getTs1_Time().getValue());
        orcSegmentORIntegrate.getOrc9_DateTimeOfTransaction().getTime().setValue(orc.getOrc9_DateTimeOfTransaction().getTime().getValue());
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn1_IDNumber().setValue(orc.getOrc12_OrderingProvider(0).getXcn1_IDNumber().getValue());
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn2_FamilyName().getFn1_Surname().setValue(orc.getOrc12_OrderingProvider(0).getXcn2_FamilyName().getFn1_Surname().getValue());
        orcSegmentORIntegrate.getOrc12_OrderingProvider(0).getXcn3_GivenName().setValue(orc.getOrc12_OrderingProvider(0).getXcn3_GivenName().getValue());
        orcSegmentORIntegrate.getOrc21_OrderingFacilityName(0).getXon1_OrganizationName().setValue(orc.getOrc21_OrderingFacilityName(0).getXon1_OrganizationName().getValue());
        orcSegmentORIntegrate.getOrc21_OrderingFacilityName(0).getXon10_OrganizationIdentifier().setValue(orc.getOrc21_OrderingFacilityName(0).getXon10_OrganizationIdentifier().getValue());
    }

    public static void createTQ1SegmentIntegrateRSPK11ToQBP(TQ1 tq1SegmentIntegrate, Message oml) throws HL7Exception {

        TQ1 tq1 = (TQ1) oml.get("TQ1");

//        tq1SegmentIntegrate.getSetIDTQ1().setValue();
//        tq1SegmentIntegrate.getStartDateTime().getTime().setValue();
//        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue();
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

    public static void createSegmentZET(ZET zet, Message orl, OML_O21 oml) throws HL7Exception {

        PID pid = oml.getPATIENT().getPID();
        TQ1 tq1 = (TQ1) oml.getORDER().get("TQ1");
        SPM spm = (SPM) orl.get("SPM");

        zet.getBarcode().setValue(spm.getSpecimenID().getPlacerAssignedIdentifier().getEntityIdentifier().getValue());
        zet.getLabelDescription().setValue(spm.getSpecimenCollectionMethod().getIdentifier().getValue());
        zet.getEmergencyLevel().setValue(tq1.getPriority(0).getIdentifier().getValue());
        zet.getSurname().setValue(pid.getPatientName(0).getFamilyName().getSurname().getValue());
        zet.getNome().setValue(pid.getPatientName(0).getGivenName().getValue());
    }
}
