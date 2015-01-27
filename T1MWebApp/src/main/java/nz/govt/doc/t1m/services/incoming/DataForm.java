package nz.govt.doc.t1m.services.incoming;

/**
 * Created by McCaulC on 27/01/2015.
 */
public class DataForm {

    private String sli;
    private String sdt;
    private String edt;
    private String obs;
    private String typ;
    private String[] dst;
    private String[][] fld;
    private String[][] dat;

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

}
