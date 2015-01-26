package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.survey.SurveyEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataForm {

    private String sli;
    private String sdt;
    private String edt;
    private String obs;
    private String typ;
    private String dst[];
    private String fld[][];
    private String dat[][];

    public String getSli() {
        return sli;
    }

    public void setSli(String sli) {
        this.sli = sli;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEdt() {
        return edt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String[] getDst() {
        return dst;
    }

    public void setDst(String[] dst) {
        this.dst = dst;
    }

    public String[][] getDat() {
        return dat;
    }

    public void setDat(String[][] dat) {
        this.dat = dat;
    }

    public String[][] getFld() {
        return fld;
    }

    public void setFld(String[][] fld) {
        this.fld = fld;
    }

    /** converts the DataForm into an SurveyEntity to be saved to the DB */
    public SurveyEntity getEE() {
        if(this.sli != null && this.obs != null) {
            SurveyEntity surveyEntity = new SurveyEntity();
            surveyEntity.setObserver(this.obs);
            surveyEntity.setLocationId(this.sli);
            surveyEntity.setStartD(parseDate(this.sdt));
            surveyEntity.setEndD(parseDate(this.edt));
            surveyEntity.setSurveyType(this.typ);
            surveyEntity.setReceivedD();
            System.out.println(surveyEntity.getReceivedDString());
            for(int i = 0 ; i < dst.length ; i++) {
                System.out.printf("Data Sheet Type: " + this.dst[i]);
                for (int j = 0; j < fld.length; j++) {
                    System.out.printf(", " + this.fld[i][j] + ": " + this.dat[i][j]);
                }
                System.out.printf("\n");
            }
            return surveyEntity;
        } else return null;
    }

    /** to parse formatted strings from JSONs into date objects */
    private Date parseDate(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
