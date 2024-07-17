package Prometeo.HL7Palm.Segment;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.AbstractSegment;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.v25.datatype.*;
import ca.uhn.hl7v2.parser.ModelClassFactory;

public class ZET extends AbstractSegment {

    public ZET(Group parent, ModelClassFactory factory) {
        super(parent, factory);
        this.init(factory);
    }

    public void init(ModelClassFactory factory) {
        try {
            this.add(ST.class, true, 1, 250, new Object[] { this.getMessage()}, "Barcode");
            this.add(ST.class, true, 1, 250, new Object[] { this.getMessage()}, "Label description");
            this.add(ID.class, false, 1, 8, new Object[] { this.getMessage()}, "Id Container");
            this.add(ST.class, false, 1, 6, new Object[] { this.getMessage()}, "Acronym Container");
            this.add(ST.class, false, 1, 40, new Object[] { this.getMessage()}, "Notes to Levy");
            this.add(ID.class, false, 1, 8, new Object[] { this.getMessage()}, "Id Lab");
            this.add(ID.class, false, 1, 8, new Object[] { this.getMessage()}, "Id Request");
            this.add(TS.class, false, 1, 250, new Object[] { this.getMessage()}, "Date Acceptance");
            this.add(ST.class, true, 1, 250, new Object[] { this.getMessage()}, "Emergency Level");
            this.add(ST.class, false, 1, 8, new Object[] { this.getMessage()}, "Id Patient ");
            this.add(ST.class, true, 1, 250, new Object[] { this.getMessage()}, "Surname");
            this.add(ST.class, true, 1, 250, new Object[] { this.getMessage()}, "Nome");
            this.add(IS.class, false, 1, 250, new Object[] { this.getMessage()}, "Administrative Sex");
            this.add(TS.class, false, 1, 250, new Object[] { this.getMessage()}, "Date of birth");
            this.add(CX.class, false, 1, 16, new Object[] { this.getMessage()}, "Tax Code");
            this.add(ST.class, false, 1, 16, new Object[] { this.getMessage()}, "Health Code");
            this.add(ID.class, false, 1, 250, new Object[] { this.getMessage()}, "Id Department Rich");
            this.add(ST.class, false, 1, 250, new Object[] { this.getMessage()}, "Name Department Rich");
            this.add(ID.class, false, 1, 250, new Object[] { this.getMessage()}, "Id Acceptance Point");
            this.add(ST.class, false, 1, 250, new Object[] { this.getMessage()}, "Material");
            this.add(ST.class, false, 1, 250, new Object[] { this.getMessage()}, "Provenance");
            this.add(ST.class, false, 1, 2000, new Object[] { this.getMessage()}, "Analysis List");
            this.add(ID.class, false, 1, 2000, new Object[] { this.getMessage()}, "Id Sector");
            this.add(ST.class, false, 1, 2000, new Object[] { this.getMessage()}, "Name Sector");
            this.add(TS.class, false, 1, 250, new Object[] { this.getMessage()}, "Withdrawal date/time");
            this.add(TS.class, false, 1, 250, new Object[] { this.getMessage()}, "Date of Delivery Report");
        } catch (HL7Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ST getBarcode() {
        ST barcode = (ST) this.getTypedField(1,0);
        return barcode;
    }

    public ST getLabelDescription() {
        ST labelDescription = (ST) this.getTypedField(2,0);
        return labelDescription;
    }

    public ID getIdContainer() {
        ID IdDontainer = (ID) this.getTypedField(3, 0);
        return IdDontainer;
    }

    public ST getAcronymContainer() {
        ST acronymContainer = (ST) this.getTypedField(4, 0);
        return acronymContainer;
    }

    public ST getNotesLevy() {
        ST notesLevy = (ST) this.getTypedField(5, 0);
        return notesLevy;
    }

    public ID getIdLab() {
        ID IdLab = (ID) this.getTypedField(6,0);
        return IdLab;
    }

    public ID getIdRequest() {
        ID IdRequest = (ID) this.getTypedField(7,0);
        return IdRequest;
    }

    public TS getDateAcceptance() {
        TS dateAcceptance = (TS) this.getTypedField(8,0);
        return dateAcceptance;
    }

    public ST getEmergencyLevel() {
        ST emergencyLevel = (ST) this.getTypedField(9, 0);
        return emergencyLevel;
    }

    public ID getIdPatient() {
        ID idPatient = (ID) this.getTypedField(10,0);
        return idPatient;
    }

    public ST getSurname() {
        ST surname = (ST) this.getTypedField(11, 0);
        return surname;
    }

    public ST getNome() {
        ST nome = (ST) this.getTypedField(12, 0);
        return nome;
    }

    public IS getAdministrativeSex() {
        IS administrativeSex = (IS) this.getTypedField(13,0);
        return administrativeSex;
    }

    public TS getDateBirth() {
        TS dateBirth = (TS) this.getTypedField(14,0);
        return dateBirth;
    }

    public CX getTaxCode() {
        CX taxCode = (CX) this.getTypedField(15,0);
        return taxCode;
    }

    public ST getHealthCode() {
        ST  healthCode= (ST) this.getTypedField(16,0);
        return healthCode;
    }

    public ID getIdDepartment() {
        ID IdRequest = (ID) this.getTypedField(17,0);
        return IdRequest;
    }

    public ST getNameDepartment() {
        ST nameDepartment = (ST) this.getTypedField(18,0);
        return nameDepartment;
    }

    public ID getIdAcceptancePoint() {
        ID idAcceptancePoint= (ID) this.getTypedField(19,0);
        return idAcceptancePoint;
    }

    public ST getMaterial() {
        ST material = (ST) this.getTypedField(20,0);
        return material;
    }

    public ST getProvenance() {
        ST provenance = (ST) this.getTypedField(21,0);
        return provenance;
    }

    public ST getAnalysisList(int rep) throws HL7Exception {
        return (ST)super.insertRepetition(22, rep);
    }

    public ID getIDSector() {
        ID idSector = (ID) this.getTypedField(23,0);
        return idSector;
    }


    public ST getNameSector() {
        ST nameSector = (ST) this.getTypedField(24,0);
        return nameSector;
    }

    public TS getWithdrawalDateTime() {
        TS withdrawalDateTime = (TS) this.getTypedField(25,0);
        return withdrawalDateTime;
    }

    public TS getDateDeliveryReport() {
        TS dateDeliveryReport = (TS) this.getTypedField(26,0);
        return dateDeliveryReport;
    }
}
