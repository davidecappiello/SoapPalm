package com.mirth.prometeo.Entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class PRO_TDQ2HL7 {
    private String ACCESSNUMBER;
    private String TUBENUMBER;
    private Integer STATO;
    private Integer FLAG_INOLTRATO;
    private String LOGUSERID;
    private String HOSTORDERNUMBER;
    private String REPARTO;
    private Date DATE_CHK;


    public PRO_TDQ2HL7(String ACCESSNUMBER, String TUBENUMBER, Integer STATO, Integer FLAG_INOLTRATO, String LOGUSERID, String HOSTORDERNUMBER, String REPARTO, Date DATE_CHK) {
        this.ACCESSNUMBER = ACCESSNUMBER;
        this.TUBENUMBER = TUBENUMBER;
        this.STATO = STATO;
        this.FLAG_INOLTRATO = FLAG_INOLTRATO;
        this.LOGUSERID = LOGUSERID;
        this.HOSTORDERNUMBER = HOSTORDERNUMBER;
        this.REPARTO = REPARTO;
        this.DATE_CHK = DATE_CHK;
    }

    public String getACCESSNUMBER() {
        return ACCESSNUMBER;
    }

    public void setACCESSNUMBER(String ACCESSNUMBER) {
        this.ACCESSNUMBER = ACCESSNUMBER;
    }

    public String getTUBENUMBER() {
        return TUBENUMBER;
    }

    public void setTUBENUMBER(String TUBENUMBER) {
        this.TUBENUMBER = TUBENUMBER;
    }

    public Integer getSTATO() {
        return STATO;
    }

    public void setSTATO(Integer STATO) {
        this.STATO = STATO;
    }

    public Integer getFLAG_INOLTRATO() {
        return FLAG_INOLTRATO;
    }

    public void setFLAG_INOLTRATO(Integer FLAG_INOLTRATO) {
        this.FLAG_INOLTRATO = FLAG_INOLTRATO;
    }

    public String getLOGUSERID() {
        return LOGUSERID;
    }

    public void setLOGUSERID(String LOGUSERID) {
        this.LOGUSERID = LOGUSERID;
    }

    public String getHOSTORDERNUMBER() {
        return HOSTORDERNUMBER;
    }

    public void setHOSTORDERNUMBER(String HOSTORDERNUMBER) {
        this.HOSTORDERNUMBER = HOSTORDERNUMBER;
    }

    public String getREPARTO() {
        return REPARTO;
    }

    public void setREPARTO(String REPARTO) {
        this.REPARTO = REPARTO;
    }

    public Date getDATE_CHK() {
        return DATE_CHK;
    }

    public void setDATE_CHK(Date DATE_CHK) {
        this.DATE_CHK = DATE_CHK;
    }
}
