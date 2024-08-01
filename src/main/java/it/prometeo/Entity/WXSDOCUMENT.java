package it.prometeo.Entity;

import oracle.sql.BLOB;

import java.sql.Blob;
import java.util.Date;

public class WXSDOCUMENT {

    private Integer ID;
    private String REFID;
    private Date DATA_ACC;
    private Date DATA_REF;
    private String MED;
    private String REP;
    private String NAME;
    private String FIRSTNAME;
    private String SITE;
    private String LABO;
    private String DIPA;
    private String VAL;
    private Integer LOCKID;
    private Date LOCKDATE;
    private String METAINFO;
    private String METASIGN;
    private String STATUS;
    private Blob DOCUMENT;
    private Date LOADDATE;
    private Date SIGNDATE;
    private String CATEGORY;
    private String FILE_NAME;

    public WXSDOCUMENT(Integer ID, String REFID, Date DATA_ACC, Date DATA_REF, String MED, String REP, String NAME, String FIRSTNAME, String SITE, String LABO, String DIPA, String VAL, Integer LOCKID, Date LOCKDATE, String METAINFO, String METASIGN, String STATUS, BLOB DOCUMENT, Date LOADDATE, Date SIGNDATE, String CATEGORY, String FILE_NAME) {
        this.ID = ID;
        this.REFID = REFID;
        this.DATA_ACC = DATA_ACC;
        this.DATA_REF = DATA_REF;
        this.MED = MED;
        this.REP = REP;
        this.NAME = NAME;
        this.FIRSTNAME = FIRSTNAME;
        this.SITE = SITE;
        this.LABO = LABO;
        this.DIPA = DIPA;
        this.VAL = VAL;
        this.LOCKID = LOCKID;
        this.LOCKDATE = LOCKDATE;
        this.METAINFO = METAINFO;
        this.METASIGN = METASIGN;
        this.STATUS = STATUS;
        this.DOCUMENT = DOCUMENT;
        this.LOADDATE = LOADDATE;
        this.SIGNDATE = SIGNDATE;
        this.CATEGORY = CATEGORY;
        this.FILE_NAME = FILE_NAME;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getREFID() {
        return REFID;
    }

    public void setREFID(String REFID) {
        this.REFID = REFID;
    }

    public Date getDATA_ACC() {
        return DATA_ACC;
    }

    public void setDATA_ACC(Date DATA_ACC) {
        this.DATA_ACC = DATA_ACC;
    }

    public Date getDATA_REF() {
        return DATA_REF;
    }

    public void setDATA_REF(Date DATA_REF) {
        this.DATA_REF = DATA_REF;
    }

    public String getMED() {
        return MED;
    }

    public void setMED(String MED) {
        this.MED = MED;
    }

    public String getREP() {
        return REP;
    }

    public void setREP(String REP) {
        this.REP = REP;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getSITE() {
        return SITE;
    }

    public void setSITE(String SITE) {
        this.SITE = SITE;
    }

    public String getLABO() {
        return LABO;
    }

    public void setLABO(String LABO) {
        this.LABO = LABO;
    }

    public String getDIPA() {
        return DIPA;
    }

    public void setDIPA(String DIPA) {
        this.DIPA = DIPA;
    }

    public String getVAL() {
        return VAL;
    }

    public void setVAL(String VAL) {
        this.VAL = VAL;
    }

    public Integer getLOCKID() {
        return LOCKID;
    }

    public void setLOCKID(Integer LOCKID) {
        this.LOCKID = LOCKID;
    }

    public Date getLOCKDATE() {
        return LOCKDATE;
    }

    public void setLOCKDATE(Date LOCKDATE) {
        this.LOCKDATE = LOCKDATE;
    }

    public String getMETAINFO() {
        return METAINFO;
    }

    public void setMETAINFO(String METAINFO) {
        this.METAINFO = METAINFO;
    }

    public String getMETASIGN() {
        return METASIGN;
    }

    public void setMETASIGN(String METASIGN) {
        this.METASIGN = METASIGN;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public Blob getDOCUMENT() {
        return DOCUMENT;
    }

    public void setDOCUMENT(Blob DOCUMENT) {
        this.DOCUMENT = DOCUMENT;
    }

    public Date getLOADDATE() {
        return LOADDATE;
    }

    public void setLOADDATE(Date LOADDATE) {
        this.LOADDATE = LOADDATE;
    }

    public Date getSIGNDATE() {
        return SIGNDATE;
    }

    public void setSIGNDATE(Date SIGNDATE) {
        this.SIGNDATE = SIGNDATE;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }
}
