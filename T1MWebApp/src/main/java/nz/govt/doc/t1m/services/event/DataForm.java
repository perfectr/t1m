package nz.govt.doc.t1m.services.event;

import nz.govt.doc.t1m.domain.event.EventEntity;

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

    /** converts the DataForm into an EventEntity to be saved to the DB */
    public EventEntity getEE() {
        if(this.sli != null && this.obs != null) {
            EventEntity eventEntity = new EventEntity();
            eventEntity.setObserver(this.obs);
            eventEntity.setLocationId(this.sli);
            eventEntity.setStartD(parseDate(this.sdt));
            eventEntity.setEndD(parseDate(this.edt));
            eventEntity.setReceivedD();
            return eventEntity;
        } else return null;
    }

    /** to parse formatted strings from JSONs into date objects */
    private Date parseDate(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
