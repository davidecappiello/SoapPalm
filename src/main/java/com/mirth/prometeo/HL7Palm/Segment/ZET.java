package com.mirth.prometeo.HL7Palm.Segment;


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
        return (ST) this.getTypedField(1,0);
    }

    public ST getLabelDescription() {
        return (ST) this.getTypedField(2,0);
    }

    public ID getIdContainer() {
        return (ID) this.getTypedField(3, 0);
    }

    public ST getAcronymContainer() {
        return (ST) this.getTypedField(4, 0);
    }

    public ST getNotesLevy() {
        return (ST) this.getTypedField(5, 0);
    }

    public ID getIdLab() {
        return (ID) this.getTypedField(6,0);
    }

    public ID getIdRequest() {
        return (ID) this.getTypedField(7,0);
    }

    public TS getDateAcceptance() {
        return (TS) this.getTypedField(8,0);
    }

    public ST getEmergencyLevel() {
        return (ST) this.getTypedField(9, 0);
    }

    public ID getIdPatient() {
        return (ID) this.getTypedField(10,0);
    }

    public ST getSurname() {
        return (ST) this.getTypedField(11, 0);
    }

    public ST getNome() {
        return (ST) this.getTypedField(12, 0);
    }

    public IS getAdministrativeSex() {
        return (IS) this.getTypedField(13,0);
    }

    public TS getDateBirth() {
        return (TS) this.getTypedField(14,0);
    }

    public CX getTaxCode() {
        return (CX) this.getTypedField(15,0);
    }

    public ST getHealthCode() {
        return (ST) this.getTypedField(16,0);
    }

    public ID getIdDepartment() {
        return (ID) this.getTypedField(17,0);
    }

    public ST getNameDepartment() {
        return (ST) this.getTypedField(18,0);
    }

    public ID getIdAcceptancePoint() {
        return (ID) this.getTypedField(19,0);
    }

    public ST getMaterial() {
        return (ST) this.getTypedField(20,0);
    }

    public ST getProvenance() {
        return (ST) this.getTypedField(21,0);
    }

    public ST getAnalysisList(int rep) throws HL7Exception {
        return (ST)super.insertRepetition(22, rep);
    }

    public ID getIDSector() {
        return (ID) this.getTypedField(23,0);
    }


    public ST getNameSector() {
        return (ST) this.getTypedField(24,0);
    }

    public TS getWithdrawalDateTime() {
        return (TS) this.getTypedField(25,0);
    }

    public TS getDateDeliveryReport() {
        return (TS) this.getTypedField(26,0);
    }
}
