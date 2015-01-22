package nz.govt.doc.t1m.services.event;

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

    public String details() {
        return "location: " + this.sli + ", start: " + this.sdt + ", end: " + this.edt + ", observer: " + this.obs;
    }
}
