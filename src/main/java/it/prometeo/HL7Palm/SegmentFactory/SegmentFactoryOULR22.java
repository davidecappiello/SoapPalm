package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.message.OUL_R22;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
@Component
public class SegmentFactoryOULR22 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryOULR22(HL7Config hl7Config) {
        SegmentFactoryORLO22.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateOULR22(MSH mshSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue("MySendingApp"); // Receiving Application HD.1 Applicazione inviante
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue("MyReceivingApp"); // Sending Facility HD.1 Ditta inviante
        mshSegmentIntegrate.getMsh5_ReceivingApplication().getNamespaceID().setValue("Applicazione ricevente"); // MSH.5 227 HD Receiving Application HD.1 Applicazione ricevente
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue("Ditta ricevente");
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(data); // DateTime Of Message TS.1 Data del messaggio. Formato: “yyyymmddhh24miss”

        // Messagge type
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("OUL");  //MSG.1 Valore: “OUL”, “ACK”
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("R22");//MSG.2 Valore: “R22”
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("OUL_R22");//MSG.3 Valore: OUL_R22”, “ACK”

        mshSegmentIntegrate.getMessageControlID().setValue("getMessageControlID"); // Message Control ID Identificativo univoco del messaggio
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue("P"); // Processing ID PT.1 Valore: “P”
        mshSegmentIntegrate.getVersionID().getVersionID().setValue("2.5"); // Version ID VID.1 Valore: “2.5”
    }

    public static void createPIDSegmentIntegrateOULR22(PID pidSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        // Patient Identifier List
        pidSegmentIntegrate.getPatientIdentifierList(0).getIDNumber().setValue("CPPDVD"); // CX.1
        pidSegmentIntegrate.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue("MASSIMA"); // CX.4/HD.1
        pidSegmentIntegrate.getPatientIdentifierList(0).getIdentifierTypeCode().setValue("10"); // CX.5

        pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue("Bruno"); // Patient Name, Cognome assistito
        pidSegmentIntegrate.getPatientName(0).getGivenName().setValue("Fabrizio"); // Patient Name, Nome assistito
        pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue(data); // Date/Time Of Birth
        pidSegmentIntegrate.getAdministrativeSex().setValue("Maschio"); // Administrative Sex

        // Patient Address
        pidSegmentIntegrate.getPatientAddress(0).getStreetAddress().getStreetName().setValue("Via via 14"); // XAD.1 Street Address
        pidSegmentIntegrate.getPatientAddress(0).getCity().setValue("Orbassano"); // XAD.3 City
        pidSegmentIntegrate.getPatientAddress(0).getStateOrProvince().setValue("Torino"); //XAD.4 State or Province
        pidSegmentIntegrate.getPatientAddress(0).getZipOrPostalCode().setValue("10012"); // XAD.5 Zip or Postal Code
        pidSegmentIntegrate.getPatientAddress(0).getAddressType().setValue("C"); // XAD.7 Address Type, valore BR / L / H / C
        pidSegmentIntegrate.getPatientAddress(0).getCountyParishCode().setValue("IT"); // XAD.9 County/Parish Code -
        // - Codice ISTAT del Comune di nascita, Codice ISTAT del Comune di residenza, -
        // - Codice ISTAT del Comune di domicilio -
        // - Codice ISTAT del Comune di reperibilità

        // Phone Number - Home
        pidSegmentIntegrate.getPhoneNumberBusiness(0).getTelecommunicationEquipmentType().setValue("Phone"); // XTN.3 Telecommunication Equipment Type -
        // - valore PH / CP / Internet

        pidSegmentIntegrate.getPhoneNumberBusiness(0).getUnformattedTelephoneNumber().setValue("34857584"); // XTN.12 Unformatted Telephone Number -
        // - numero di telefono

        pidSegmentIntegrate.getPhoneNumberBusiness(0).getTelecommunicationUseCode().setValue("1"); // XTN.2 Telecommunication Equipment Type -
        // - valore NET

        pidSegmentIntegrate.getPhoneNumberBusiness(0).getEmailAddress().setValue("dgdgf@gmail.com"); // XTN.4 Email

        // Citizenship
        pidSegmentIntegrate.getCitizenship(0).getIdentifier().setValue("1"); // CWE.1 Identifier
        pidSegmentIntegrate.getCitizenship(0).getText().setValue("Ciao"); // CWE.2 Text
        pidSegmentIntegrate.getCitizenship(0).getNameOfCodingSystem().setValue("ISTAT"); // CWE.3 Name of Coding System, valore ISTAT
    }

    public static void createPV1SegmentIntegrateOULR22(PV1 pv1SegmentIntegrate) throws HL7Exception {

        // Regime
        // Valore:
        // “I” per Ordinario,
        // “D” per DayHospital,
        // “O” per Esterno (SSN)
        // “N” per Esterno (senza prescrizione)
        // “S” per Screening
        // “PS” per Pronto Soccorso
        pv1SegmentIntegrate.getPatientClass().setValue(""); // PV1.2

        // Assigned Patient Location
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue("Presidio ospedaliero"); // PL.1 Presidio Ospedaliero
        pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue("22"); // PL.8 Codice Aziendale reparto di ricovero per Interni
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue("Reparto ricoveri interno"); // PL.9 Descrizione reparto di ricovero per Interni

        // Visit number
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue("3"); // CX.1 Codice Nosologico, id prericovero o scheda di pronto soccorso
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue("LDA"); // CX.5 , valore :“LDA”: prericovero / “ADT”: ricovero / “PS”: pronto soccorso
    }

    public static void createSPMSegmentIntegrateOULR22(SPM spmSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        spmSegmentIntegrate.getSetIDSPM().setValue("" + 1); // Progressivo numerico del segmento

        // Specimen ID
        spmSegmentIntegrate.getSpecimenID().getPlacerAssignedIdentifier().getEntityIdentifier().setValue("1"); // EIP.1/EI.1
        spmSegmentIntegrate.getSpecimenID().getFillerAssignedIdentifier().getEntityIdentifier().setValue("2"); // EIP.2/EI.1

        // Specimen Type
        spmSegmentIntegrate.getSpecimenType().getIdentifier().setValue("1"); // CWE.1
        spmSegmentIntegrate.getSpecimenType().getText().setValue("Bello"); // CWE.2
        spmSegmentIntegrate.getSpecimenType().getNameOfCodingSystem().setValue("IT"); // CWE.3

        spmSegmentIntegrate.getSpecimenCollectionDateTime().getRangeEndDateTime().getTime().setValue(data); // Specimen Collection Date/Time DR.1/TS.1
    }

    public static void createORCSegmentIntegrateOULR22(ORC orcSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        orcSegmentIntegrate.getOrderControl().setValue("SC"); // Valore: “SC”

        // Placer Order Number
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue("25"); // EI.1 Contiene l’identificativo dell’inviante della -
        // - sezione ORDER (uguale a OBR-2 e diverso per ogni segmento ORC): si tratta dell’identificativo -
        // - univoco della prestazione dell’applicativo inviante
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue("1"); // EI.2 Identificativo inviante
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue("10"); // EI.3
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue("10.3"); // EI.4 Tipo di codice riportato in -
        // - EI.3: “RIC” per ricetta “rossa” / “NRE” per numero ricetta elettronica

        // Order Filler Number
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue("1"); // EI.1 Contiene l’identificativo dell’erogatore della -
        // - sezione ORDER (uguale a OBR-3 e diverso per ogni segmento ORC): si tratta dell’identificativo -
        // - univoco della prestazione dell’applicativo erogante
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue("Erogatore"); // EI.2 Identificativo erogatore

        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue("ORC"); // Identificativo univoco della richiesta uguale per tutti i segmenti ORC
        orcSegmentIntegrate.getOrderStatus().setValue("A"); // Valori: “A"
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(data); // Data inserimento richiesta
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(data); // Data della pianificazione (NW-IP) o della prevista erogazione (SC-SC) o dell’erogazione (SC-CM) o -
        // - data della cancellazione (SC-CA)

        // Ordering Provider - Medico richiedente
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue("BRNFBR"); // XCN.1 Codice fiscale
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue("Bruno"); // XCN.2/FN.1 Cognome
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue("Fabrizio"); // XCN.3 nome

        // Ordering Facility Name - Struttra richiedente
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue("Apple"); // XON.1
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue("APL"); // XON.10
    }

    public static void createTQ1SegmentIntegrateOULR22(TQ1 tq1SegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue("E"); // Urgenza della richiesta “R” – Routine / “U” – Urgente / “E” - Emergenza
        //CONTINUARE SU TQ1 PAGINA 54\

        tq1SegmentIntegrate.getSetIDTQ1().setValue("" + 1);
        tq1SegmentIntegrate.getStartDateTime().getTime().setValue(data);

        //Urgenza della richiesta
        //“R” – Routine
        //“U” – Urgente
        //“E” - Emergenza
        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue("1");
    }

    public static void createOBRSegmentIntegrateOULR22(OBR obrSegmentIntegrate) throws HL7Exception {

        Calendar data = Calendar.getInstance();

        obrSegmentIntegrate.getSetIDOBR().setValue("" + 1);
        obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue("ORC2"); // Deve essere uguale a ORC-2 e diverso per i vari segmenti OBR -
        // - della stessa richiesta

        obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue("ORC3"); // Deve essere uguale a ORC-3 e diverso per i vari segmenti OBR -
        // - della stessa richiesta

        // Universal Service Indetifier
        obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue("14"); // CE.1 Codice Catalogo della prestazione
        obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue("Brutto"); // CE.2 Descrizione della prestazione
        obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue("CODCATALOGO"); // CE.3 Valore: CODCATALOGO"

        obrSegmentIntegrate.getObservationDateTime().getTime().setValue(data); // Data di validazione dei risultati

        obrSegmentIntegrate.getRelevantClinicalInformation().setValue("Centro prelievo ASTI"); // Codice Centro di Prelievo - Descrizione Centro di Prelievo

        // Ordering Provider - Medico Curante
        obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue("CPPDVD"); // XCN.1 Codice fiscale
        obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue("Cappiello"); // XCN.2/FN.1 Cognome
        obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue("Davide"); // XCN.3 nome

        obrSegmentIntegrate.getPlacerField2().setValue("F"); // “F” – visibile su referto, “X”– non visibile su referto

        obrSegmentIntegrate.getDiagnosticServSectID().setValue("LAB"); // "LAB"

        //Result Satus - Stato del risultati
        //- F (Final) per l’invio di risultati validati;
        //- C (Correction) per correggere risultati già
        //validati;
        //- X (No results) per il caso di cancellazione
        //dell’ordine.
        obrSegmentIntegrate.getResultStatus().setValue("OK");

        obrSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(data); // data dell'errogazione dei risultati

        // Collector's Comment
        obrSegmentIntegrate.getCollectorSComment(0).getIdentifier().setValue("5"); // Codice settore
        obrSegmentIntegrate.getCollectorSComment(0).getText().setValue("Ciao"); // Descrizione settore
        obrSegmentIntegrate.getCollectorSComment(0).getNameOfCodingSystem().setValue("SETTORE"); // valore "SETTORE"
        obrSegmentIntegrate.getCollectorSComment(0).getAlternateIdentifier().setValue("LOINC");  // Codice LOINC associato al settore
        obrSegmentIntegrate.getCollectorSComment(0).getAlternateText().setValue("Corto"); // Descrizione codice LOINC
        obrSegmentIntegrate.getCollectorSComment(0).getNameOfCodingSystem().setValue("SETTORE_LOINC"); // Valore "SETTORE_LOINC"

        obrSegmentIntegrate.getFillerSupplementalServiceInformation(0).getIdentifier().setValue("1");
        //Valore: “B” per identificare i risultati di microbiologia (la sezione ORDER relativa fa riferimento a prestazioni ed risultati legati ad
        //antibiotici testati su germi individuati)
    }

    public static void createOBXSegmentIntegrateOULR22(OBX obxSegmentIntegrate) throws HL7Exception {

        OUL_R22 oul = new OUL_R22();
        Calendar data = Calendar.getInstance();

        // fare la logica

        obxSegmentIntegrate.getSetIDOBX().parse("1"); // Progressivo del segmento

        // Tipologia del risultato:
        //“NM” – Numerico
        //“ST” – Alfanumerico
        //“CE” – Codificato
        //“ST” è usato anche nel caso di rifiuto
        //dell’esame da parte del LIS
        obxSegmentIntegrate.getValueType().setValue("ST"); // Valore: "ST"

        // Observation Identifier
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue("PASSED"); // Codice risultato
        obxSegmentIntegrate.getObservationIdentifier().getText().setValue("Ottimo"); // Descrizione del risultato
        obxSegmentIntegrate.getObservationIdentifier().getNameOfCodingSystem().setValue("UTF8"); // Sistema di codifica
        obxSegmentIntegrate.getObservationIdentifier().getAlternateIdentifier().setValue("LOINC"); // Codice della specialità (codifica LOINC)
        obxSegmentIntegrate.getObservationIdentifier().getAlternateText().setValue("Bravo"); // Descrizione specialita
        obxSegmentIntegrate.getObservationIdentifier().getNameOfAlternateCodingSystem().setValue("LOINC"); // Sistema di codifica. Valore: “LOINC”

        CE ce = new CE(oul);
        ce.getIdentifier().setValue("1"); //CE.1 codice risultato
        ce.getText().setValue("Brutto"); // CE.2 Descrizione risultato
        ce.getNameOfCodingSystem().setValue("Italiano"); // CE.3S istema di codifica
        obxSegmentIntegrate.getObservationValue(0).setData(ce); // Imposto le varibili CE in OBX.5

        obxSegmentIntegrate.getUnits().getIdentifier().setValue("IT"); // Unita di misura

        //Intervallo di normalità del risultato
        //nel formato
        //valore_minimo;valore_massimo
        //per i casi in cui non è presente un valore
        //minimo esplicito (es. “minore di 10”) riportare
        //come valore_minimo: “INF”
        //per i casi in cui non è presente un massimo
        //(es. “maggiore di 10”), riportare come
        //valore_massimo: “SUP”
        obxSegmentIntegrate.getReferencesRange().setValue("SUP");

        // Stato di normalità del risultato:
        //“LL” – molto basso
        //“L” – basso
        //“N” – normale
        //“H” – alto
        //“HH” – molto alto
        //“A” – alterato
        //“AA” – molto alterato
        obxSegmentIntegrate.getAbnormalFlags(0).setValue("H");

        //Stato del risultato:
        //“F” – validato
        //“C” – correzione di un precedente valore già
        //validato
        //“D” – cancellato
        //“X’ – non eseguito
        obxSegmentIntegrate.getObservationResultStatus().setValue("0"); // Valore: “O” (Order detail description only (no result))

        //Data di validazione del risultato
        //Formato: “yyyymmddhh24miss”
        obxSegmentIntegrate.getDateTimeOfTheObservation().getTime().setValue(data);

        //Responsible Observer
        obxSegmentIntegrate.getResponsibleObserver(0).getIDNumber().setValue("MRGNTN"); // Codice
        obxSegmentIntegrate.getResponsibleObserver(0).getFamilyName().getSurname().setValue("Miraglia"); // Nome
        obxSegmentIntegrate.getResponsibleObserver(0).getGivenName().setValue("Antonio"); // Cognome

        //Observation Method
        obxSegmentIntegrate.getObservationMethod(0).getIdentifier().setValue("16"); // Codice metodo utilizzato
        obxSegmentIntegrate.getObservationMethod(0).getText().setValue("Bello"); // Descrizione metodo
        obxSegmentIntegrate.getObservationMethod(0).getNameOfCodingSystem().setValue("IT"); // Tipo di codifica

        obxSegmentIntegrate.getEquipmentInstanceIdentifier(0).getEntityIdentifier().setValue("F"); // “F” – visibile su referto, “X”– non visibile su referto
    }

}
