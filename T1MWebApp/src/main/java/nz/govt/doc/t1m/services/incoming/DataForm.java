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

    private String[][] inf;
    private String[][] ind;


    public void setSli(String sli) { this.sli = sli; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public void setEdt(String edt) { this.edt = edt; }
    public void setObs(String obs) { this.obs = obs; }
    public void setTyp(String typ) { this.typ = typ; }

    public void setDst(String[] dst) { this.dst = dst; }

    public void setFld(String[][] fld) { this.fld = fld; }
    public void setDat(String[][] dat) { this.dat = dat; }

    public void setInf(String[][] inf) { this.inf = inf; }
    public void setInd(String[][] ind) { this.ind = ind; }


    public String getSli() { return sli; }
    public String getSdt() { return sdt; }
    public String getEdt() { return edt; }
    public String getObs() { return obs; }
    public String getTyp() { return typ; }

    public String[] getDst() { return dst; }
    public String getDst(int i) { return dst[i]; }

    public String[][] getFld() { return fld; }
    public String[] getFld(int i) { return fld[i]; }
    public String getFld(int i, int j) { return fld[i][j]; }
    public String[][] getDat() { return dat; }
    public String[] getDat(int i) { return dat[i]; }
    public String getDat(int i, int j) { return dat[i][j]; }

    public String[][] getInf() { return inf; }
    public String[] getInf(int i) { return inf[i]; }
    public String getInf(int i, int j) { return inf[i][j]; }
    public String[][] getInd() { return ind; }
    public String[] getInd(int i) { return ind[i]; }
    public String getInd(int i, int j) { return ind[i][j]; }

}